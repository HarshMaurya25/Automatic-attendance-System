package com.project.Attendance_System.Service.Interface;

import com.project.Attendance_System.Domain.Dtos.Attendance.StudentAttendanceSummaryDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface AttendanceServiceInterface {
    ResponseEntity<StudentAttendanceSummaryDto> getStudentAttendanceSummary(UUID studentId);
    List<StudentAttendanceSummaryDto> getDivisionAttendanceSummary(UUID divisionId);
}
