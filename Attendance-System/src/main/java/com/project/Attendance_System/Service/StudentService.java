package com.project.Attendance_System.Service;

import com.project.Attendance_System.Domain.Dtos.Attendance.AttendanceRespondDto;
import com.project.Attendance_System.Domain.Dtos.Student.StudentLoginRequestDto;
import com.project.Attendance_System.Domain.Dtos.Student.StudentResponseDto;
import com.project.Attendance_System.Domain.Entity.*;
import com.project.Attendance_System.Domain.Enum.SessionType;
import com.project.Attendance_System.ExceptionHandler.Custom.LoginSessionIncorrectException;
import com.project.Attendance_System.ExceptionHandler.Custom.VariableNotFound;
import com.project.Attendance_System.Mapper.AttendanceMapper;
import com.project.Attendance_System.Mapper.StudentMapper;
import com.project.Attendance_System.Mapper.UserMapper;
import com.project.Attendance_System.Repository.*;
import com.project.Attendance_System.Service.Interface.StudentServiceInterface;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@Data
@AllArgsConstructor
public class StudentService implements StudentServiceInterface {

    private final StudentMapper studentMapper;
    private final AttendanceMapper attendanceMapper;
    private final UserMapper userMapper;

    private final StudentsRepo studentsRepo;
    private final LoginSessionRepo loginSessionRepo;
    private final AttendanceRepo attendanceRepo;
    private final DivisionRepo divisionRepo;
    private final UserRepo userRepo;

    @Override
    public ResponseEntity<StudentResponseDto> createNewStudent(StudentLoginRequestDto dto) {
        UUID sessionId = UUID.fromString(dto.getSession_code());
        LoginSessions loginSession = loginSessionRepo.findById(sessionId)
                .orElseThrow(() -> new LoginSessionIncorrectException("Login Session not found"));


        if (!loginSession.getSession_type().equals(SessionType.STUDENT)) {
            throw new LoginSessionIncorrectException(
                    "Login Session is Incorrect: " + loginSession.getSession_type()
            );
        }

        Student student = studentMapper.ToStudent(dto);

        Division division = divisionRepo.findById(loginSession.getPlace_identifier())
                .orElseThrow(() -> new VariableNotFound("Division"));

        student.setCollege(loginSession.getCollege());
        student.setDivision(division);
        student.setDepartment(division.getDepartment());

        User user = userRepo.findById(dto.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + dto.getEmail()));
        if(user == null){
            throw new UsernameNotFoundException("user is not found " + dto.getEmail());
        }

        student.setUser(user);

        Student savedStudent = studentsRepo.save(student);

        StudentResponseDto responseDto = studentMapper.ToStudentResponseDto(savedStudent);
        return ResponseEntity.ok(responseDto);
    }

    public ResponseEntity<StudentResponseDto> getStudentDetail(UUID id){
        Student student = studentsRepo.findById(id)
                .orElseThrow(()-> new VariableNotFound("Student"));

        StudentResponseDto studentResponseDto = studentMapper.ToStudentResponseDto(student);

        return ResponseEntity.ok().body(studentResponseDto);
    }

    public ResponseEntity<List<AttendanceRespondDto>> getStudentAttendance(UUID id , LocalDate start , LocalDate end){

        Student student = studentsRepo.findById(id).orElseThrow(()-> new VariableNotFound("Student"));

        List<Attendance> attendances = attendanceRepo
                .findByStudentIdAndDateBetweenOrderByDateAscTimeAsc( student.getId(), start , end);

        List<AttendanceRespondDto> attendanceRespondDto = attendances.stream()
                .map(attendanceMapper::toDto)
                .toList();

        return ResponseEntity.ok().body(attendanceRespondDto);
    }
}
