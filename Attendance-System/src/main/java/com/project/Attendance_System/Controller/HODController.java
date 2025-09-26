package com.project.Attendance_System.Controller;

import com.project.Attendance_System.Domain.Dtos.Department.DepartmentRequestDto;
import com.project.Attendance_System.Domain.Dtos.Department.DepartmentResponseDto;
import com.project.Attendance_System.Domain.Dtos.Division.DivisionRequestDto;
import com.project.Attendance_System.Domain.Dtos.Division.DivisionResponseDto;
import com.project.Attendance_System.Domain.Dtos.LoginSession.LoginSessionRequestDto;
import com.project.Attendance_System.Domain.Dtos.LoginSession.LoginSessionRespondDto;
import com.project.Attendance_System.Service.Interface.HODServiceInterface;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@AllArgsConstructor
@RequestMapping("/api/v1")
public class HODController {

    private final HODServiceInterface hodService;

    @PostMapping("/private/session-id/create")
    public ResponseEntity<LoginSessionRespondDto> createSessionID(
            @Valid @RequestBody LoginSessionRequestDto loginSessionRequestDto
            ){
        return hodService.createSessionLogin(loginSessionRequestDto);
    }

    @PostMapping("/private/division/create")
    public ResponseEntity<DivisionResponseDto> createDivision(
            @Valid @RequestBody DivisionRequestDto dto
            ){
        return hodService.createDivision(dto);
    }

    @GetMapping("/private/division/{id}")
    public ResponseEntity<List<DivisionResponseDto>> getAllDivision(
            @PathVariable UUID id
            ){
        return hodService.getDivisionInDepartment(id);
    }

    @PostMapping("/private/department/create")
    public ResponseEntity<DepartmentResponseDto> createDepartment(
            @Valid @RequestBody DepartmentRequestDto dto
    ) {
        return hodService.createDepartment(dto);
    }

    @GetMapping("/private/department/{id}")
    public ResponseEntity<DepartmentResponseDto> getDepartment(
            @PathVariable UUID id
    ) {
        return hodService.getDepartment(id);
    }

}
