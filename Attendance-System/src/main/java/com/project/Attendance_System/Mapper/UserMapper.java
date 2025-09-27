package com.project.Attendance_System.Mapper;

import com.project.Attendance_System.Domain.Dtos.User.RegisterRequestDto;
import com.project.Attendance_System.Domain.Entity.User;
import com.project.Attendance_System.Domain.Enum.Authority;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User fromLoginRequestDto(RegisterRequestDto Dto){
        return User.builder()
                .email(Dto.getEmail())
                .authority(Dto.getAuthority())
                .build();
    }
}
