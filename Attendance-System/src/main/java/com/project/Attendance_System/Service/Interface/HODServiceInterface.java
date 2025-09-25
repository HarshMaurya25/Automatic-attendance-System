package com.project.Attendance_System.Service.Interface;

import com.project.Attendance_System.Domain.Dtos.LoginSession.LoginSessionRequestDto;
import com.project.Attendance_System.Domain.Dtos.LoginSession.LoginSessionRespondDto;
import org.springframework.http.ResponseEntity;

public interface HODServiceInterface {

    ResponseEntity<LoginSessionRespondDto> createSessionLogin(LoginSessionRequestDto loginSessionRequestDto);
}
