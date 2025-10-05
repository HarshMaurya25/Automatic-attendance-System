package com.project.Attendance_System.Domain.Dtos.Lecture;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Map;
import java.util.UUID;

@AllArgsConstructor
@Data
@Builder
public class CreateLectureResponse {
    private UUID id;
    Map<UUID , String> students;
}
