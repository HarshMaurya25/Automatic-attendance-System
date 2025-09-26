package com.project.Attendance_System.Domain.Dtos.Division;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DivisionRequestDto {

    @NotBlank(message = "Division name is required")
    private String name;

    @NotNull(message = "Department ID is required")
    private UUID departmentId;

    @NotNull(message = "College ID is required")
    private UUID collegeId;

    @NotEmpty(message = "At least one course ID must be provided")
    private List<UUID> courseIds;

    @NotBlank(message = "Class teacher name is required")
    private String classTeacher;
}
