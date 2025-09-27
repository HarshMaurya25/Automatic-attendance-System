package com.project.Attendance_System.Controller;

import com.project.Attendance_System.Domain.Dtos.User.LoginRequestDto;
import com.project.Attendance_System.Domain.Entity.User;
import com.project.Attendance_System.Repository.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/api/v1/public")
public class Register {

    private final UserRepo userRepo;

    @PostMapping("/new")
    public Boolean Register(
            @RequestBody LoginRequestDto dto
            ){

        User user = User.builder()
                .email(dto.getEmail())
                .password(dto.getPassword())
                .authority(dto.getAuthority())
                .build();

        userRepo.save(user);

        return true;
    }
}
