package com.project.Attendance_System.Service;

import com.project.Attendance_System.Domain.Dtos.User.LoginRequestDto;
import com.project.Attendance_System.Domain.Entity.User;
import com.project.Attendance_System.Mapper.UserMapper;
import com.project.Attendance_System.Repository.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserMapper userMapper;

    private final UserRepo userRepo;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);

    public Boolean createuser(LoginRequestDto dto){
        User user = userMapper.fromLoginRequestDto(dto);

        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        userRepo.save(user);
        return true;
    }
}
