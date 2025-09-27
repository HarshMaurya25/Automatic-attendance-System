package com.project.Attendance_System.Controller;

import com.project.Attendance_System.Domain.Dtos.User.LoginRequestDto;
import com.project.Attendance_System.Domain.Entity.User;
import com.project.Attendance_System.Repository.UserRepo;
import com.project.Attendance_System.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/api/v1/public")
public class UserController {

    private final UserService userService;

    @PostMapping("/new")
    public ResponseEntity<Boolean> createUser(
            @RequestBody LoginRequestDto dto
    ){
        return ResponseEntity.ok().body(userService.createuser(dto));

    }
}
