package com.project.Attendance_System.Domain.Dtos.Attendance;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentAttendanceSummaryDto {

    private String student_name;
    private Integer roll_number;
    private long total_lectures;
    private long presents;
    private long absents;
    private double attendance_percentage;  // e.g., 85.0 for 85%
}
