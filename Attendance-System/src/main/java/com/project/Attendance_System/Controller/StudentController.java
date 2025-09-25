package com.project.Attendance_System.Controller;

import com.project.Attendance_System.Domain.Dtos.AttendanceRespondDto;
import com.project.Attendance_System.Domain.Dtos.StudentLoginRequestDto;
import com.project.Attendance_System.Domain.Dtos.StudentResponseDto;
import com.project.Attendance_System.Service.Interface.StudentServiceInterface;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Controller
@AllArgsConstructor
@RequestMapping("/api/v1/student")
public class StudentController {

    private final StudentServiceInterface studentService;

    @PostMapping("/public/register")
    public ResponseEntity<StudentResponseDto> createNewStudent(
            @Valid @RequestBody StudentLoginRequestDto studentRequestDto
    ){
        return studentService.createNewStudent(studentRequestDto);

    }

    @GetMapping("/private/{id}")
    public ResponseEntity<StudentResponseDto> getStudentDetail(
            @PathVariable UUID id
            ){
        return studentService.getStudentDetail(id);
    }

    @GetMapping("/private/attendance/{studentId}")
    public ResponseEntity<List<AttendanceRespondDto>> getAttendanceByStudentAndDateRange(
            @PathVariable UUID studentId,
            @RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end
    ) {

        return studentService.getStudentAttendance(studentId , start , end);
    }
}
