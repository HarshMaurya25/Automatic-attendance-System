package com.project.Attendance_System.Domain.Dtos.Lecture;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class GetAttendanceDto {
    @NotNull
    private UUID studentId;

    @NotNull
    private UUID sessionID;

    @NotBlank
    private String qr_id;
}
