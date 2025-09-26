package com.project.Attendance_System.Mapper;

import com.project.Attendance_System.Domain.Dtos.Department.DepartmentRequestDto;
import com.project.Attendance_System.Domain.Dtos.Department.DepartmentResponseDto;
import com.project.Attendance_System.Domain.Entity.Department;
import com.project.Attendance_System.Domain.Entity.College;
import org.springframework.stereotype.Component;

@Component
public class DepartmentMapper {

    public Department toEntity(DepartmentRequestDto dto, College college) {
        Department department = new Department();
        department.setName(dto.getName());
        department.setCollege(college);
        return department;
    }

    public DepartmentResponseDto toDto(Department department) {
        return DepartmentResponseDto.builder()
                .id(department.getId())
                .name(department.getName())
                .collegeName(department.getCollege() != null ? department.getCollege().getName() : null)
                .HOD(department.getHOD())
                .build();
    }
}
