package com.project.Attendance_System.Service.Interface;

import com.project.Attendance_System.Domain.Dtos.Attendance.AttendanceResponseByCourseDto;
import com.project.Attendance_System.Domain.Dtos.Teacher.TeacherResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface TeacherServiceInterface {

    ResponseEntity<List<AttendanceResponseByCourseDto>> getAttendanceByCourse(UUID division_id , UUID course_id);

    TeacherResponseDto getTeacher(UUID id);
}
