package com.project.Attendance_System.Domain.Dtos.Lecture;

import lombok.Data;

import java.util.UUID;

@Data
public class GetAttendanceDto {
    private UUID studentId;

    private UUID sessionID;

    private String qr_id;
}
