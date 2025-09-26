package com.project.Attendance_System.Mapper;

import com.project.Attendance_System.Domain.Dtos.Attendance.AttendanceRespondDto;
import com.project.Attendance_System.Domain.Dtos.Attendance.AttendanceResponseByCourseDto;
import com.project.Attendance_System.Domain.Entity.Attendance;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
public class AttendanceMapper {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public AttendanceRespondDto toDto(Attendance attendance){
        return AttendanceRespondDto.builder()
                .course_name(attendance.getCourse().getName())
                .teacher_name(attendance.getTeacher().getName())
                .division_name(attendance.getDivision().getName())
                .date(attendance.getDate().format(formatter))
                .time(attendance.getTime().format(formatter))
                .status(attendance.getStatus().name())
                .build();
    }

    public AttendanceResponseByCourseDto toDtoForCourse(Attendance attendance) {
        return AttendanceResponseByCourseDto.builder()
                .id(attendance.getId())
                .studentName(attendance.getStudent().getFirst_name()+ " " + attendance.getStudent().getSurname())
                .status(attendance.getStatus())
                .date(attendance.getDate())
                .time(attendance.getTime())
                .build();
    }
}
