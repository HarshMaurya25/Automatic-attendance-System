package com.project.Attendance_System.Mapper;

import com.project.Attendance_System.Domain.Dtos.Division.DivisionRequestDto;
import com.project.Attendance_System.Domain.Dtos.Division.DivisionResponseDto;
import com.project.Attendance_System.Domain.Entity.Course;
import com.project.Attendance_System.Domain.Entity.Department;
import com.project.Attendance_System.Domain.Entity.Division;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DivisionMapper {

    public Division toDivision(
            DivisionRequestDto dto,
            Department department
    ) {
        Division division = new Division();
        division.setName(dto.getName());
        division.setDepartment(department);
        division.setCollege(department.getCollege());
        division.setClass_Teacher(dto.getClassTeacher());
        return division;
    }

    public DivisionResponseDto toDto(Division division) {
        List<String> courseNames = division.getCourses() != null
                ? division.getCourses().stream().map(Course::getName).collect(Collectors.toList())
                : List.of();

        return DivisionResponseDto.builder()
                .id(division.getId())
                .name(division.getName())
                .departmentName(
                        division.getDepartment() != null ? division.getDepartment().getName() : null
                )
                .collegeName(
                        division.getCollege() != null ? division.getCollege().getName() : null
                )
                .courseNames(courseNames)
                .classTeacher(division.getClass_Teacher())
                .build();
    }

    public List<DivisionResponseDto> toDtoList(List<Division> divisions) {
        return divisions.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
