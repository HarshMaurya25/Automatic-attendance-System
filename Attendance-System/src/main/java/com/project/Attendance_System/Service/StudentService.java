package com.project.Attendance_System.Service;

import com.project.Attendance_System.Domain.Dtos.Attendance.AttendanceRespondDto;
import com.project.Attendance_System.Domain.Dtos.Student.StudentLoginRequestDto;
import com.project.Attendance_System.Domain.Dtos.Student.StudentResponseDto;
import com.project.Attendance_System.Domain.Entity.Attendance;
import com.project.Attendance_System.Domain.Entity.Division;
import com.project.Attendance_System.Domain.Entity.LoginSessions;
import com.project.Attendance_System.Domain.Entity.Student;
import com.project.Attendance_System.Domain.Enum.SessionType;
import com.project.Attendance_System.Mapper.AttendanceMapper;
import com.project.Attendance_System.Mapper.StudentMapper;
import com.project.Attendance_System.Repository.AttendanceRepo;
import com.project.Attendance_System.Repository.DivisionRepo;
import com.project.Attendance_System.Repository.LoginSessionRepo;
import com.project.Attendance_System.Repository.StudentsRepo;
import com.project.Attendance_System.Service.Interface.StudentServiceInterface;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@Data
public class StudentService implements StudentServiceInterface {

    private final StudentMapper studentMapper;
    private final AttendanceMapper attendanceMapper;

    private final StudentsRepo studentsRepo;
    private final LoginSessionRepo loginSessionRepo;
    private final AttendanceRepo attendanceRepo;
    private final DivisionRepo divisionRepo;

    public ResponseEntity<StudentResponseDto> createNewStudent(StudentLoginRequestDto studentLoginRequestDto) {
        UUID session_id = UUID.fromString(studentLoginRequestDto.getSession_code());
        LoginSessions loginSessions = loginSessionRepo.getReferenceById(session_id);
        if(!loginSessions.getSession_type().equals(SessionType.STUDENT)){
            throw new IllegalArgumentException("This is not a Correct Session Id");
        }

        Student student = studentMapper.ToStudent(studentLoginRequestDto);

        Division division = divisionRepo.findById(loginSessions.getPlace_identifier())
                        .orElseThrow(()-> new RuntimeException("Division Not Found"));

        student.setCollege(loginSessions.getCollege());
        student.setDivision(division);
        student.setDepartment(division.getDepartment());

        studentsRepo.save(student);
        StudentResponseDto studentResponseDto = studentMapper.ToStudentResponseDto(student);

        return ResponseEntity.ok().body(studentResponseDto);
    }

    public ResponseEntity<StudentResponseDto> getStudentDetail(UUID id){
        Student student = studentsRepo.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Such Student Not Exist"));

        StudentResponseDto studentResponseDto = studentMapper.ToStudentResponseDto(student);

        return ResponseEntity.ok().body(studentResponseDto);
    }

    public ResponseEntity<List<AttendanceRespondDto>> getStudentAttendance(UUID id , LocalDate start , LocalDate end){

        Student student = studentsRepo.findById(id).orElseThrow(()-> new IllegalArgumentException("Such Student Not Exist"));

        List<Attendance> attendances = attendanceRepo
                .findByStudentIdAndDateBetweenOrderByDateAscTimeAsc( student, start , end);

        List<AttendanceRespondDto> attendanceRespondDto = attendances.stream()
                .map(attendanceMapper::toDto)
                .toList();

        return ResponseEntity.ok().body(attendanceRespondDto);
    }
}
