package com.project.Attendance_System.Controller;

import com.project.Attendance_System.Domain.Dtos.User.LoginRequestDto;
import com.project.Attendance_System.Domain.Dtos.User.LoginResponse;
import com.project.Attendance_System.Domain.Dtos.User.RegisterRequestDto;
import com.project.Attendance_System.Service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/public")
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;

    @PostMapping("/new")
    public ResponseEntity<String> createUser(
            @Valid @RequestBody RegisterRequestDto dto
    ){
        return ResponseEntity.ok().body(userService.createuser(dto));

    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(
            @Valid @RequestBody LoginRequestDto dto
            ){
        return ResponseEntity.ok().body(userService.verify(dto));
    }
}
