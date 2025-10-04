package com.project.Attendance_System.Mapper;

import com.project.Attendance_System.Domain.Dtos.Teacher.TeacherResponseDto;
import com.project.Attendance_System.Domain.Entity.Staff;
import org.springframework.stereotype.Component;

@Component
public class TeacherMapper {
    public TeacherResponseDto toDto(Staff staff) {
        if (staff == null) return null;

        return TeacherResponseDto.builder()
                .id(staff.getId())
                .name(staff.getName())
                .dateOfBirth(staff.getDate_of_birth())
                .gender(staff.getGender())
                .department(staff.getDepartment().getName())
                .department_id(staff.getDepartment().getId())
                .college(staff.getCollege().getName())
                .college_id(staff.getCollege().getId())
                .position(staff.getPosition())
                .phoneNumber(staff.getPhone_number())
                .email(staff.getEmail())
                .address(staff.getAddress())
                .city(staff.getCity())
                .state(staff.getState())
                .build();
    }
}
