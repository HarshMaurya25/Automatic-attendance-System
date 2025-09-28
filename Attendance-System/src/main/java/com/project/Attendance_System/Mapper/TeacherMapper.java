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
                .department(staff.getDepartment())
                .college(staff.getCollege())
                .position(staff.getPosition())
                .phoneNumber(staff.getPhone_number())
                .email(staff.getEmail())
                .address(staff.getAddress())
                .city(staff.getCity())
                .state(staff.getState())
                .build();
    }
}
