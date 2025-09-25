package com.project.Attendance_System.Mapper;

import com.project.Attendance_System.Domain.Dtos.LoginSession.LoginSessionRequestDto;
import com.project.Attendance_System.Domain.Dtos.LoginSession.LoginSessionRespondDto;
import com.project.Attendance_System.Domain.Entity.College;
import com.project.Attendance_System.Domain.Entity.Department;
import com.project.Attendance_System.Domain.Entity.Division;
import com.project.Attendance_System.Domain.Entity.LoginSessions;
import com.project.Attendance_System.Domain.Enum.SessionType;
import com.project.Attendance_System.Repository.CollegeRepo;
import org.springframework.stereotype.Component;

@Component
public class LoginSessionMapper {

    public LoginSessionRespondDto toDtoForStudent(LoginSessions loginSessions , Division division){
        return LoginSessionRespondDto.builder()
                .id(loginSessions.getId())
                .department_name(division.getDepartment().getName())
                .college_name(loginSessions.getCollege().getName())
                .division_name(division.getName())
                .sessionType(loginSessions.getSession_type())
                .build();
    }

    public LoginSessionRespondDto toDtoForTeacher(LoginSessions loginSessions , Department department){
        return LoginSessionRespondDto.builder()
                .id(loginSessions.getId())
                .department_name(department.getName())
                .college_name(loginSessions.getCollege().getName())
                .sessionType(loginSessions.getSession_type())
                .build();
    }

    public LoginSessions toLoginSessionForStudent(College college ,Division division){
        return LoginSessions.builder()
                .college(college)
                .place_identifier(division.getId())
                .session_type(SessionType.STUDENT)
                .build();
    }

    public LoginSessions toLoginSessionForTeacher(College college , Department department){
        return LoginSessions.builder()
                .college(college)
                .place_identifier(department.getId())
                .session_type(SessionType.TEACHER)
                .build();
    }
}
