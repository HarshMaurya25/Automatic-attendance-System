package com.project.Attendance_System.Domain.Dtos.LoginSession;

import com.project.Attendance_System.Domain.Enum.SessionType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class LoginSessionRequestDto {

    private UUID college;

    private UUID place_identifier;

    @NotNull
    @Enumerated(EnumType.STRING)
    private SessionType sessionType;
}
