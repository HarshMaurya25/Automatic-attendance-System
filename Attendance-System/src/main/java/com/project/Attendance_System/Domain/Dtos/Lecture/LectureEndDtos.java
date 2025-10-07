package com.project.Attendance_System.Domain.Dtos.Lecture;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class LectureEndDtos {
    long total;
    long present;
    long absent;
}
