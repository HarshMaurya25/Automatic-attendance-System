package com.project.Attendance_System.Controller;

import com.project.Attendance_System.Domain.Dtos.Attendance.AttendanceResponseByCourseDto;
import com.project.Attendance_System.Service.Interface.TeacherServiceInterface;
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
public class TeacherController {

    private final TeacherServiceInterface teacherService;

    @PreAuthorize("!hasRole('STUDENT')")
    @GetMapping("/private/teacher/course/{courseId}/division/{divisionId}")
    public ResponseEntity<List<AttendanceResponseByCourseDto>> getAttendanceByCourseAndDivision(
            @PathVariable UUID courseId,
            @PathVariable UUID divisionId
    ) {
        return teacherService.getAttendanceByCourse(courseId, divisionId);
    }
}
