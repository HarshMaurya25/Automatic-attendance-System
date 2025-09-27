package com.project.Attendance_System.Mapper;

import com.project.Attendance_System.Domain.Dtos.User.LoginRequestDto;
import com.project.Attendance_System.Domain.Entity.User;
import com.project.Attendance_System.Domain.Enum.Authority;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User fromStudentDto(LoginRequestDto Dto){
        return User.builder()
                .email(Dto.getEmail())
                .authority(Authority.STUDENT)
                .password(Dto.getPassword())
                .build();

    }
}
