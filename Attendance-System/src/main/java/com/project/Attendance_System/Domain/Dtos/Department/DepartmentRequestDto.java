package com.project.Attendance_System.Domain.Dtos.Department;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentRequestDto {

    @NotBlank(message = "Department name is required")
    private String name;

    @NotNull(message = "College ID is required")
    private UUID collegeId;
}

