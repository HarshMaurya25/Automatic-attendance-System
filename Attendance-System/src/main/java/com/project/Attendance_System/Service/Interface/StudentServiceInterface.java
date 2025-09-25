package com.project.Attendance_System.Service.Interface;

import com.project.Attendance_System.Domain.Dtos.Attendance.AttendanceRespondDto;
import com.project.Attendance_System.Domain.Dtos.Student.StudentLoginRequestDto;
import com.project.Attendance_System.Domain.Dtos.Student.StudentResponseDto;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface StudentServiceInterface {

    ResponseEntity<StudentResponseDto> createNewStudent(StudentLoginRequestDto studentLoginRequestDto);
    ResponseEntity<StudentResponseDto> getStudentDetail(UUID id);
    ResponseEntity<List<AttendanceRespondDto>> getStudentAttendance(UUID id , LocalDate start , LocalDate end);
}
