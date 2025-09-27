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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@AllArgsConstructor
@RequestMapping("/api/v1")
public class HODController {

    private final HODServiceInterface hodService;

    @PreAuthorize("hasAnyRole('HOD' , 'PRINCIPAL' , 'ADMIN')")
    @PostMapping("/private/hod/session-id/create")
    public ResponseEntity<LoginSessionRespondDto> createSessionID(
            @Valid @RequestBody LoginSessionRequestDto loginSessionRequestDto
            ){
        return hodService.createSessionLogin(loginSessionRequestDto);
    }

    @PreAuthorize("hasAnyRole('HOD' , 'PRINCIPAL' , 'ADMIN')")
    @PostMapping("/private/hod/division/create")
    public ResponseEntity<DivisionResponseDto> createDivision(
            @Valid @RequestBody DivisionRequestDto dto
            ){
        return hodService.createDivision(dto);
    }

    @PreAuthorize("hasAnyRole('HOD' , 'PRINCIPAL' , 'ADMIN')")
    @GetMapping("/private/hod/division/{id}")
    public ResponseEntity<List<DivisionResponseDto>> getAllDivision(
            @PathVariable UUID id
            ){
        return hodService.getDivisionInDepartment(id);
    }

    @PreAuthorize("hasAnyRole( 'PRINCIPAL' , 'ADMIN')")
    @PostMapping("/private/hod/department/create")
    public ResponseEntity<DepartmentResponseDto> createDepartment(
            @Valid @RequestBody DepartmentRequestDto dto
    ) {
        return hodService.createDepartment(dto);
    }

    @PreAuthorize("hasAnyRole('PRINCIPAL' , 'ADMIN')")
    @GetMapping("/private/hod/department/{id}")
    public ResponseEntity<DepartmentResponseDto> getDepartment(
            @PathVariable UUID id
    ) {
        return hodService.getDepartment(id);
    }

}
