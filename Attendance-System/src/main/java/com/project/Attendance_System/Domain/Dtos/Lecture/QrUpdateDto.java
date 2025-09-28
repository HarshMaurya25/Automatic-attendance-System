package com.project.Attendance_System.Domain.Dtos.Lecture;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class QrUpdateDto {
    @NotBlank
    private String type;

    @NotNull
    private UUID session_id;

    @NotNull
    private String qr_id;
}
