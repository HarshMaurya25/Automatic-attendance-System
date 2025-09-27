package com.project.Attendance_System.Controller;

import com.project.Attendance_System.Domain.Dtos.Attendance.StudentAttendanceSummaryDto;
import com.project.Attendance_System.Service.AttendanceService;
import com.project.Attendance_System.Service.Interface.AttendanceServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.UUID;

@Controller
@AllArgsConstructor
@RequestMapping("/api/v1")
public class AttendanceController {

    private final AttendanceServiceInterface attendanceService;

    @PreAuthorize("!hasRole('STUDENT')")
    @GetMapping("/private/attendance/division/{division_id}")
    public ResponseEntity<List<StudentAttendanceSummaryDto>> getStudentAttendancePercentageByDivision(
            @PathVariable UUID division_id
            ){
        return ResponseEntity.ok().body(attendanceService.getDivisionAttendanceSummary(division_id));
    }

    @PreAuthorize("!(hasRole('STUDENT') and #studentId != principal.id)")
    @GetMapping("/private/attendance/student/{student_id}")
    public ResponseEntity<StudentAttendanceSummaryDto> getStudentAttendancePercentageById(
            @PathVariable UUID student_id
    ){
        return attendanceService.getStudentAttendanceSummary(student_id);
    }

}
