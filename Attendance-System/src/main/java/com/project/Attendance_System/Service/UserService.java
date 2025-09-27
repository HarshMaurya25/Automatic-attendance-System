package com.project.Attendance_System.Service;

import com.project.Attendance_System.Domain.Dtos.User.LoginRequestDto;
import com.project.Attendance_System.Domain.Dtos.User.RegisterRequestDto;
import com.project.Attendance_System.Domain.Entity.User;
import com.project.Attendance_System.Mapper.UserMapper;
import com.project.Attendance_System.Repository.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserMapper userMapper;

    private final UserRepo userRepo;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);

    private final AuthenticationManager authmanager;

    private final JwtService jwtService;

    public String createuser(RegisterRequestDto dto){
        User user = userMapper.fromLoginRequestDto(dto);

        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        userRepo.save(user);

        Authentication authentication = authmanager.authenticate(new UsernamePasswordAuthenticationToken(dto.getEmail() , dto.getPassword()));

        if(authentication.isAuthenticated()){
            return jwtService.generateToken(dto.getEmail());
        }else{
            throw new UsernameNotFoundException("UserName/Email and password is not found");
        }
    }

    public String verify(LoginRequestDto dto) {
        Authentication authentication = authmanager.authenticate(new UsernamePasswordAuthenticationToken(dto.getEmail() , dto.getPassword()));

        if(authentication.isAuthenticated()){
            return jwtService.generateToken(dto.getEmail());
        }else{
            throw new UsernameNotFoundException("UserName/Email and password is not found");
        }
    }
}
