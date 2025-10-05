package com.project.Attendance_System.Service;

import com.project.Attendance_System.Domain.Dtos.Lecture.CreateLectureDto;
import com.project.Attendance_System.Domain.Dtos.Lecture.CreateLectureResponse;
import com.project.Attendance_System.Domain.Dtos.Lecture.QrUpdateDto;
import com.project.Attendance_System.Domain.Entity.*;
import com.project.Attendance_System.ExceptionHandler.Custom.VariableNotFound;
import com.project.Attendance_System.Mapper.LectureMapper;
import com.project.Attendance_System.Repository.*;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
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
            throw new VariableNotFound("Lecture is not found during the QR update");
        }
        activeSession.put(dto.getSession_id(), dto.getQr_id());
        log.info("{} lecture is updated to {}" , dto.getSession_id() , dto.getQr_id());
    }

    @Transactional
    public CreateLectureResponse createLecture(CreateLectureDto dto) {
        Division division = divisionRepo.findById(dto.getDivision_id())
                .orElseThrow(() -> new VariableNotFound("Division"));

        Course course = courseRepo.findById(dto.getCourse_id())
                .orElseThrow(() -> new VariableNotFound("Course"));

        Staff teacher = staffRepo.findById(dto.getTeacher_id())
                .orElseThrow(() -> new VariableNotFound("Teacher"));

        LectureLogs lectureLogs = LectureLogs.builder().build();
        lectureLogsRepo.save(lectureLogs);

        log.info("Lecture is Creating where College : {} , division : {} , teacher : {} and Course : {}" , division.getCollege().getName() , division.getName() , teacher.getEmail() , course.getName());

        Map<UUID , String> students = new HashMap<>();
        List<Attendance> attendances = new ArrayList<>();
        for (Student student : division.getStudent()) {
            Attendance attendance = lectureMapper.toEntity(course, division, teacher);
            attendance.setStudent(student);
            attendance.setLectureLog(lectureLogs);
            attendances.add(attendance);
            students.put(student.getId() , student.getFirst_name()+ " " + student.getSurname());
        }

        attendanceRepo.saveAll(attendances);

        lectureLogs.setAttendances(attendances);
        lectureLogsRepo.save(lectureLogs);
        activeSession.put(lectureLogs.getId(), dto.getQr_id());

        CreateLectureResponse createLectureResponse = CreateLectureResponse.builder()
                .id(lectureLogs.getId())
                .students(students)
                .build();

        log.info("Lecture for Teacher : {} and Course ; {} is created where ID ; {}" , teacher.getEmail() , course.getName() , lectureLogs.getId());
        return createLectureResponse;
    }

    public boolean checkLecture(UUID id, String qr_id) {
        if (!activeSession.containsKey(id)) {
            throw new VariableNotFound("Lecture");
        }

        System.out.println("ID have " + activeSession.get(id));
        if (activeSession.get(id).equals(qr_id)) {
            return true;
        } else {
            return false;
        }
    }

}
