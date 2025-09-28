package com.project.Attendance_System.Domain.Dtos.Lecture;

import com.project.Attendance_System.Domain.Entity.Staff;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class CreateLectureDto {
    @NotNull
    private String qr_id;

    @NotBlank
    private UUID teacher_id;

    @NotBlank
    private UUID course_id;

    @NotBlank
    private UUID division_id;
}
