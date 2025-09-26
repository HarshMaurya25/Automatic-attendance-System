package com.project.Attendance_System.Domain.Dtos.Division;
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
public class DivisionResponseDto {

    private UUID id;
    private String name;

    private String departmentName;
    private String collegeName;
    private List<String> courseNames;

    private String classTeacher;
}

