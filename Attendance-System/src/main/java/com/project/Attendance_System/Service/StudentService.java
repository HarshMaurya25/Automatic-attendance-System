package com.project.Attendance_System.Service;

import com.project.Attendance_System.Domain.Dtos.Attendance.AttendanceRespondDto;
import com.project.Attendance_System.Domain.Dtos.Lecture.GetAttendanceDto;
import com.project.Attendance_System.Domain.Dtos.Student.StudentLoginRequestDto;
import com.project.Attendance_System.Domain.Dtos.Student.StudentResponseDto;
import com.project.Attendance_System.Domain.Entity.*;
import com.project.Attendance_System.Domain.Enum.SessionType;
import com.project.Attendance_System.Domain.Enum.Status;
import com.project.Attendance_System.ExceptionHandler.Custom.LoginSessionIncorrectException;
import com.project.Attendance_System.ExceptionHandler.Custom.VariableNotFound;
import com.project.Attendance_System.Mapper.AttendanceMapper;
import com.project.Attendance_System.Mapper.StudentMapper;
import com.project.Attendance_System.Mapper.UserMapper;
import com.project.Attendance_System.Repository.*;
import com.project.Attendance_System.Service.Interface.StudentServiceInterface;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.rmi.ServerError;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@Data
@AllArgsConstructor
public class StudentService implements StudentServiceInterface {

    private final StudentMapper studentMapper;
    private final AttendanceMapper attendanceMapper;
    private final UserMapper userMapper;
    private final SimpMessagingTemplate messagingTemplate;

    private final SessionMappingService sessionMappingService;

    private final LectureService lectureService;

    private final StudentsRepo studentsRepo;
    private final LoginSessionRepo loginSessionRepo;
    private final AttendanceRepo attendanceRepo;
    private final DivisionRepo divisionRepo;
    private final UserRepo userRepo;

    @Transactional
    public ResponseEntity<StudentResponseDto> createNewStudent(StudentLoginRequestDto dto) {
        String email = dto.getEmail().trim().toLowerCase();

        log.info("Student {} is trying to Create a account with gmail {}" , dto.getFirst_name() + " " + dto.getSurname() , dto.getEmail());

        if (studentsRepo.existsByEmailIgnoreCase(email)) {
            throw new VariableNotFound("Student with email already exists");
        }

        UUID sessionId;
        try {
            sessionId = UUID.fromString(dto.getSession_code());
        } catch (IllegalArgumentException ex) {
            throw new RuntimeException("Invalid session_code UUID");
        }

        LoginSessions loginSession = loginSessionRepo.findById(sessionId)
                .orElseThrow(() -> new VariableNotFound("Login session not found"));

        if (loginSession.getSession_type() != SessionType.STUDENT) {
            throw new LoginSessionIncorrectException("Login session is not for STUDENT");
        }

        Division division = divisionRepo.findById(loginSession.getPlace_identifier())
                .orElseThrow(() -> new VariableNotFound("Division"));
        College college = loginSession.getCollege();

        User user = userRepo.findById(email)
                .orElseThrow(() -> new VariableNotFound("Email"));

        Student student = studentMapper.ToStudent(dto);
        student.setEmail(email);
        student.setCollege(college);
        student.setDivision(division);
        student.setDepartment(division.getDepartment());
        student.setUser(user);

        Student saved = studentsRepo.save(student);

        log.info("Student with email {} is registered in college : {} and division : {} with id : {}", dto.getEmail() , college.getName() , division.getName() , student.getId());

        StudentResponseDto responseDto = studentMapper.ToStudentResponseDto(saved);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();

        return ResponseEntity.created(location).body(responseDto);
    }

    public ResponseEntity<StudentResponseDto> getStudentDetail(UUID id) {
        Student student = studentsRepo.findById(id)
                .orElseThrow(() -> new VariableNotFound("Student"));

        StudentResponseDto studentResponseDto = studentMapper.ToStudentResponseDto(student);

        return ResponseEntity.ok().body(studentResponseDto);
    }

    public ResponseEntity<List<AttendanceRespondDto>> getStudentAttendance(UUID id, LocalDate start, LocalDate end) {
        Student student = studentsRepo.findById(id).orElseThrow(() -> new VariableNotFound("Student"));

        log.info("Student {} is Accessing between {} and {} " , student.getEmail() , start , end);
        List<Attendance> attendances = attendanceRepo
                .findByStudentIdAndDateBetweenOrderByDateAscTimeAsc(student.getId(), start, end);

        if (attendances.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        System.out.println(attendances.toString());
        List<AttendanceRespondDto> attendanceRespondDto = attendances.stream()
                .map(attendanceMapper::toDto)
                .toList();

        log.info("Student with email {} is delivered the atteadance" , student.getEmail());
        return ResponseEntity.ok().body(attendanceRespondDto);
    }

    public String getAttendance(GetAttendanceDto dto) {
        if(!lectureService.checkLecture(dto.getSessionID() , dto.getQr_id())){
            throw new RuntimeException("Wrong QR ID");
        }
        log.info("Student {} wants to get attendance at {}", dto.getStudentId(), dto.getSessionID());
        int updated = attendanceRepo.markPresent(dto.getSessionID(), dto.getStudentId());
        log.info("Student {} update {} row", dto.getStudentId(), updated);

        if (updated == 0) {
            throw new RuntimeException("Attendance record not found or already marked.");
        }
        if (dto.getSessionID() != null) {
            messagingTemplate.convertAndSend(
                    "/topic/" + dto.getSessionID(),
                    dto.getStudentId() + " marked present"
            );
            System.out.println("Sent message to session " + dto.getSessionID().toString()+ ": " + dto.getStudentId() + " marked present");
        } else {
            log.warn("No username mapped for session {}", dto.getSessionID());
        }

        return "Attendance marked successfully";
    }

}
