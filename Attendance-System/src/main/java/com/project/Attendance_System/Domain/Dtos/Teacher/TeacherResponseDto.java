package com.project.Attendance_System.Domain.Dtos.Teacher;

import com.project.Attendance_System.Domain.Entity.College;
import com.project.Attendance_System.Domain.Entity.Department;
import com.project.Attendance_System.Domain.Enum.Gender;

import java.time.LocalDate;
import java.util.UUID;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeacherResponseDto {

    private UUID id;

    private String name;

    private LocalDate dateOfBirth;

    private Gender gender;

    private String department;

    private UUID department_id;

    private String college;

    private UUID college_id;

    private String position;

    private String phoneNumber;

    private String email;

    private String address;

    private String city;

    private String state;

}