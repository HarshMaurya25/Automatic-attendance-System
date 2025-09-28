package com.project.Attendance_System.Mapper;

import com.project.Attendance_System.Domain.Dtos.Lecture.CreateLectureDto;
import com.project.Attendance_System.Domain.Entity.*;
import com.project.Attendance_System.Domain.Enum.Status;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

@Component
public class LectureMapper {

    public Attendance toEntity(Course c, Division d , Staff s ){
        return Attendance.builder()
                .date(LocalDate.now())
                .time(LocalTime.now())
                .course(c)
                .teacher(s)
                .division(d)
                .status(Status.ABSENT)
                .build();

    }
}
