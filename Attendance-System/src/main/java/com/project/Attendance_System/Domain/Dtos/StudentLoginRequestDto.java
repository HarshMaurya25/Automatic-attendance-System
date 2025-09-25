package com.project.Attendance_System.Domain.Dtos;

import com.project.Attendance_System.Domain.Enum.Gender;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class StudentLoginRequestDto {

    @NotBlank
    @Size(max = 15, message = "First name can't be larger than 15 letters")
    private String first_name;

    @NotBlank
    @Size(max = 15, message = "Surname can't be larger than 15 letters")
    private String surname;

    @NotBlank
    @Size(max = 20, message = "Father name can't be larger than 20 letters")
    private String father_name;

    @NotNull
    @Min(value = 1, message = "Roll number should be positive")
    private Integer roll_number;

    @NotNull
    private Gender gender;

    @NotNull
    @Past
    private LocalDate date_of_birth;

    @NotBlank
    @Size(max = 10, message = "Year can't be larger than 10 letters")
    private String year;

    @NotBlank
    @Size(min = 8, max = 14)
    private String phone_number;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String address;

    @NotBlank
    private String city;

    @NotBlank
    private String state;

    @NotBlank
    private String session_code;
}
