package com.project.Attendance_System.Mapper;

import com.project.Attendance_System.Domain.Dtos.Student.StudentLoginRequestDto;
import com.project.Attendance_System.Domain.Dtos.Student.StudentResponseDto;
import com.project.Attendance_System.Domain.Entity.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {

    public Student ToStudent(StudentLoginRequestDto dto){

        return Student.builder()
                .first_name(dto.getFirst_name())
                .surname(dto.getSurname())
                .father_name(dto.getFather_name())
                .gender(dto.getGender())
                .roll_number(dto.getRoll_number())
                .year(dto.getYear())
                .date_of_birth(dto.getDate_of_birth())
                .address(dto.getAddress())
                .State(dto.getState())
                .city(dto.getCity())
                .phone_number(dto.getPhone_number())
                .email(dto.getEmail())
                .build();
    }

    public StudentResponseDto ToStudentResponseDto(Student student){
        return StudentResponseDto.builder()
                .id(student.getId())
                .first_name(student.getFirst_name())
                .surname(student.getSurname())
                .father_name(student.getFather_name())
                .gender(student.getGender())
                .roll_number(student.getRoll_number())
                .year(student.getYear())
                .division_name(student.getDivision().getName())
                .department_name(student.getDepartment().getName())
                .college_name(student.getCollege().getName())
                .division_id(student.getDivision().getId())
                .date_of_birth(student.getDate_of_birth())
                .address(student.getAddress())
                .State(student.getState())
                .city(student.getCity())
                .phone_number(student.getPhone_number())
                .email(student.getEmail())
                .build();
    }
}
