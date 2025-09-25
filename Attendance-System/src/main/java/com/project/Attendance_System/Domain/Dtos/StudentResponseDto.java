package com.project.Attendance_System.Domain.Dtos;

import com.project.Attendance_System.Domain.Enum.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class StudentResponseDto {
    private UUID id;

    private String first_name;

    private String surname;

    private String father_name;

    private Integer roll_number;

    private Gender gender;

    private LocalDate date_of_birth;

    private String year;

    private String division_name;

    private String department_name;

    private String college_name;

    private UUID division_id;

    private String phone_number;

    private String email;

    private String address;

    private String city;

    private String State;
}
