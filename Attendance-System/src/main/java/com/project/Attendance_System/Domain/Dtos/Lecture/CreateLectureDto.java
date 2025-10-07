package com.project.Attendance_System.Domain.Dtos.Lecture;

import com.project.Attendance_System.Domain.Entity.Staff;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class CreateLectureDto {
    @NotBlank
    private String qr_id;

    @NotNull
    private UUID teacher_id;

    @NotNull
    private UUID course_id;

    @NotNull
    private UUID division_id;
}
