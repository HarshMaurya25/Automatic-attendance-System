package com.project.Attendance_System.Domain.Dtos.LoginSession;

import com.project.Attendance_System.Domain.Enum.SessionType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
public class LoginSessionRespondDto {
    private UUID id;

    private String college_name;

    private String department_name;

    private String division_name;

    @Enumerated(EnumType.STRING)
    private SessionType sessionType;
}