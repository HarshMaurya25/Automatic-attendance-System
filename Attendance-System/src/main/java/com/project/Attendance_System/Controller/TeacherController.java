package com.project.Attendance_System.Controller;

import com.project.Attendance_System.Domain.Dtos.Attendance.AttendanceResponseByCourseDto;
import com.project.Attendance_System.Domain.Dtos.Lecture.CreateLectureDto;
import com.project.Attendance_System.Domain.Dtos.Teacher.TeacherResponseDto;
import com.project.Attendance_System.Service.Interface.TeacherServiceInterface;
import com.project.Attendance_System.Service.LectureService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@AllArgsConstructor
@RequestMapping("/api/v1")
public class TeacherController {

    private final TeacherServiceInterface teacherService;
    private final LectureService lectureService;

    @PreAuthorize("!hasRole('STUDENT')")
    @GetMapping("/private/teacher/course/{courseId}/division/{divisionId}")
    public ResponseEntity<List<AttendanceResponseByCourseDto>> getAttendanceByCourseAndDivision(
            @PathVariable UUID courseId,
            @PathVariable UUID divisionId
    ) {
        return teacherService.getAttendanceByCourse(courseId, divisionId);
    }

    @PreAuthorize("!hasRole('STUDENT')")
    @GetMapping("/private/teacher/{id}")
    public ResponseEntity<TeacherResponseDto> getTeacher(
            @PathVariable UUID id
    ){
        return ResponseEntity.ok().body(teacherService.getTeacher(id));
    }

    @PreAuthorize("!hasRole('STUDENT')")
    @PostMapping("/private/lecture/create")
    public ResponseEntity<UUID> createLecture(
            @RequestBody CreateLectureDto dto
            ){
        return ResponseEntity.ok().body(lectureService.createLecture(dto));
    }


}
