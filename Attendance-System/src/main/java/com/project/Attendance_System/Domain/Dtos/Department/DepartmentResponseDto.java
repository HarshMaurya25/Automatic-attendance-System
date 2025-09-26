package com.project.Attendance_System.Domain.Dtos.Department;

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
public class DepartmentResponseDto {

    private UUID id;
    private String name;

    private String collegeName;        // College name instead of ID
    private String HOD;

    private List<String> courseNames;  // Course names
    private List<String> staffNames;   // Staff names
    private List<String> divisionNames; // Division names
}

