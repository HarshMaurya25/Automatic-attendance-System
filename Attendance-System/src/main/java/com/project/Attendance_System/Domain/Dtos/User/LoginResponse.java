package com.project.Attendance_System.Domain.Dtos.User;

import com.project.Attendance_System.Domain.Enum.SessionType;
import lombok.Data;
import lombok.Builder;

import java.util.UUID;

@Data
@Builder
public class LoginResponse {
    private String token;
    private UUID uuid;
    private SessionType sessionType;
    private String gmail;
}
