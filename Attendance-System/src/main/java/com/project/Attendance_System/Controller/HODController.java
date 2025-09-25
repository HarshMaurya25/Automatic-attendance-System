package com.project.Attendance_System.Controller;

import com.project.Attendance_System.Domain.Dtos.LoginSession.LoginSessionRequestDto;
import com.project.Attendance_System.Domain.Dtos.LoginSession.LoginSessionRespondDto;
import com.project.Attendance_System.Service.Interface.HODServiceInterface;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/api/v1")
public class HODController {

    private final HODServiceInterface hodService;

    @PostMapping("/private/create-session-id")
    public ResponseEntity<LoginSessionRespondDto> createSessionID(
            @Valid @RequestBody LoginSessionRequestDto loginSessionRequestDto
            ){
        return hodService.createSessionLogin(loginSessionRequestDto);
    }
}
