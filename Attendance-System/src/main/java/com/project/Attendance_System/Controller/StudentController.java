package com.project.Attendance_System.Controller;

import com.project.Attendance_System.Domain.Dtos.Attendance.AttendanceRespondDto;
import com.project.Attendance_System.Domain.Dtos.Lecture.GetAttendanceDto;
import com.project.Attendance_System.Domain.Dtos.Student.StudentLoginRequestDto;
import com.project.Attendance_System.Domain.Dtos.Student.StudentResponseDto;
import com.project.Attendance_System.Service.Interface.StudentServiceInterface;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Slf4j
@Controller
@AllArgsConstructor
@RequestMapping("/api/v1")
public class StudentController {

    private final StudentServiceInterface studentService;

    @PreAuthorize("hasAnyRole('HOD' , 'STUDENT' , 'TEACHER')")
    @PostMapping("/private/student/register")
    public ResponseEntity<StudentResponseDto> createNewStudent(
            @Valid @RequestBody StudentLoginRequestDto studentRequestDto
    ){
        return studentService.createNewStudent(studentRequestDto);

    }

    @GetMapping("/private/student/{id}")
    public ResponseEntity<StudentResponseDto> getStudentDetail(
            @PathVariable UUID id
            ){
        return studentService.getStudentDetail(id);
    }

    @GetMapping("/private/student/attendance/{studentId}")
    public ResponseEntity<List<AttendanceRespondDto>> getAttendanceByStudentAndDateRange(
            @PathVariable UUID studentId,
            @RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end
    ) {
        log.warn("Date is {} to {} for Student {}" , start , end , studentId);
        return studentService.getStudentAttendance(studentId , start , end);
    }

    @PostMapping("/private/student/lecture/get-attendance")
    public ResponseEntity<String> getAttendance(
            @Valid @RequestBody GetAttendanceDto dto
            ){
        return ResponseEntity.ok().body(studentService.getAttendance(dto));
    }

}
