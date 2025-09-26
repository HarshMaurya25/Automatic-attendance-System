package com.project.Attendance_System.Service.Interface;

import com.project.Attendance_System.Domain.Dtos.Department.DepartmentRequestDto;
import com.project.Attendance_System.Domain.Dtos.Department.DepartmentResponseDto;
import com.project.Attendance_System.Domain.Dtos.Division.DivisionRequestDto;
import com.project.Attendance_System.Domain.Dtos.Division.DivisionResponseDto;
import com.project.Attendance_System.Domain.Dtos.LoginSession.LoginSessionRequestDto;
import com.project.Attendance_System.Domain.Dtos.LoginSession.LoginSessionRespondDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface HODServiceInterface {

    ResponseEntity<LoginSessionRespondDto> createSessionLogin(LoginSessionRequestDto loginSessionRequestDto);
    ResponseEntity<DivisionResponseDto> createDivision(DivisionRequestDto divisionRequestDto);
    ResponseEntity<List<DivisionResponseDto>> getDivisionInDepartment(UUID department);
    ResponseEntity<DepartmentResponseDto> createDepartment(DepartmentRequestDto dto);
    ResponseEntity<DepartmentResponseDto> getDepartment(UUID id);
}
