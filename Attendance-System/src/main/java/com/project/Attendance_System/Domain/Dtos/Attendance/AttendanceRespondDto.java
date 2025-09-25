package com.project.Attendance_System.Domain.Dtos.Attendance;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class AttendanceRespondDto {
    private String course_name;

    private String teacher_name;

    private String division_name;

    private String date;

    private String time;

    private String status;
}
