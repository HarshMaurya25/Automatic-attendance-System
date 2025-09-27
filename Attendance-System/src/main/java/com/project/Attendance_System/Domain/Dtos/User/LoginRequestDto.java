package com.project.Attendance_System.Domain.Dtos.User;

import com.project.Attendance_System.Domain.Enum.Authority;
import lombok.Data;

@Data
public class LoginRequestDto {
    private String email;
    private String password;
    private Authority authority;
}
