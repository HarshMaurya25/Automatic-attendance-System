package com.project.Attendance_System.Domain.Dtos.Attendance;

import com.project.Attendance_System.Domain.Enum.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceResponseByCourseDto {
    private UUID id;
    private String studentName;
    private Status status;
    private LocalDate date;
    private LocalTime time;
}
