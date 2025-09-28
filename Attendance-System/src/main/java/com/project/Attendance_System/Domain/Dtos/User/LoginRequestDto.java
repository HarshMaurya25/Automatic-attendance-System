package com.project.Attendance_System.Domain.Dtos.User;

import com.project.Attendance_System.Domain.Enum.Authority;
import com.project.Attendance_System.Domain.Enum.SessionType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginRequestDto {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;

    @NotNull
    private SessionType authority;
}
