package com.project.Attendance_System.Service;

import com.project.Attendance_System.Domain.Dtos.Lecture.CreateLectureDto;
import com.project.Attendance_System.Domain.Dtos.Lecture.QrUpdateDto;
import com.project.Attendance_System.Domain.Entity.*;
import com.project.Attendance_System.ExceptionHandler.Custom.VariableNotFound;
import com.project.Attendance_System.Mapper.LectureMapper;
import com.project.Attendance_System.Repository.*;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
@AllArgsConstructor
public class LectureService {

    private final LectureMapper lectureMapper;

    private final LoginSessionRepo loginSessionRepo;
    private final CollegeRepo collegeRepo;
    private final DepartmentRepo departmentRepo;
    private final DivisionRepo divisionRepo;
    private final AttendanceRepo attendanceRepo;
    private final CourseRepo courseRepo;
    private final StaffRepo staffRepo;
    private final LectureLogsRepo lectureLogsRepo;

    private final Map<UUID, String> activeSession = new ConcurrentHashMap<>();

    public void qrupdate(QrUpdateDto dto) {
        if (!activeSession.containsKey(dto.getSession_id())) {
            throw new VariableNotFound("Lecture");
        }
        activeSession.put(dto.getSession_id(), dto.getQr_id());
    }

    @Transactional
    public UUID createLecture(CreateLectureDto dto) {
        Division division = divisionRepo.findById(dto.getDivision_id())
                .orElseThrow(() -> new VariableNotFound("Division"));

        Course course = courseRepo.findById(dto.getCourse_id())
                .orElseThrow(() -> new VariableNotFound("Course"));

        Staff teacher = staffRepo.findById(dto.getTeacher_id())
                .orElseThrow(() -> new VariableNotFound("Teacher"));

        LectureLogs lectureLogs = LectureLogs.builder().build();
        lectureLogsRepo.save(lectureLogs);

        List<Attendance> attendances = new ArrayList<>();
        for (Student student : division.getStudent()) {
            Attendance attendance = lectureMapper.toEntity(course, division, teacher);
            attendance.setStudent(student);
            attendance.setLectureLog(lectureLogs);
            attendances.add(attendance);
        }

        attendanceRepo.saveAll(attendances);

        lectureLogs.setAttendances(attendances);
        lectureLogsRepo.save(lectureLogs);

        activeSession.put(lectureLogs.getId(), dto.getQr_id());

        return lectureLogs.getId();
    }

    public boolean checkLecture(UUID id, String qr_id) {
        if (!activeSession.containsKey(id)) {
            throw new VariableNotFound("Lecture");
        }
        if (activeSession.get(id).equals(qr_id)) {
            return true;
        } else {
            return false;
        }
    }

}
