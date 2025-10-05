package com.project.Attendance_System.Service;

import com.project.Attendance_System.Domain.Dtos.Attendance.StudentAttendanceSummaryDto;
import com.project.Attendance_System.Domain.Entity.Attendance;
import com.project.Attendance_System.Domain.Entity.Division;
import com.project.Attendance_System.Domain.Entity.Student;
import com.project.Attendance_System.ExceptionHandler.Custom.VariableNotFound;
import com.project.Attendance_System.Mapper.AttendanceMapper;
import com.project.Attendance_System.Mapper.DepartmentMapper;
import com.project.Attendance_System.Mapper.DivisionMapper;
import com.project.Attendance_System.Mapper.LoginSessionMapper;
import com.project.Attendance_System.Repository.*;
import com.project.Attendance_System.Service.Interface.AttendanceServiceInterface;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
@Data
public class AttendanceService implements AttendanceServiceInterface {

    private final LoginSessionMapper loginSessionMapper;
    private final DivisionMapper divisionMapper;
    private final DepartmentMapper departmentMapper;
    private final AttendanceMapper attendanceMapper;

    private final LoginSessionRepo loginSessionRepo;
    private final CollegeRepo collegeRepo;
    private final StudentsRepo studentsRepo;
    private final DepartmentRepo departmentRepo;
    private final DivisionRepo divisionRepo;
    private final AttendanceRepo attendanceRepo;
    private final CourseRepo courseRepo;
    
    @Override
    public ResponseEntity<StudentAttendanceSummaryDto> getStudentAttendanceSummary(UUID studentId) {
        Student student = studentsRepo.findById(studentId)
                .orElseThrow(() -> new VariableNotFound("Student"));

        log.info("Attendance Summary for student : {} is Accessing", student.getEmail());

        List<Attendance> attendances = attendanceRepo.findAllByStudent(student);

        long total = attendances.size();
        long presents = attendances.stream()
                .filter(a -> a.getStatus() != null && a.getStatus().name().equals("PRESENT"))
                .count();
        long absents = attendances.stream()
                .filter(a -> a.getStatus() != null && a.getStatus().name().equals("ABSENT"))
                .count();

        double percentage = total > 0 ? (presents * 100.0 / total) : 0;

        StudentAttendanceSummaryDto studentAttendanceSummaryDto = StudentAttendanceSummaryDto.builder()
                .student_name(student.getFirst_name() + " " + student.getSurname())
                .roll_number(student.getRoll_number())
                .total_lectures(total)
                .presents(presents)
                .absents(absents)
                .attendance_percentage(percentage)
                .build();

        log.info("Attendance Summary for student : {} is given", student.getEmail());

        return ResponseEntity.ok().body(studentAttendanceSummaryDto);
    }

    public List<StudentAttendanceSummaryDto> getDivisionAttendanceSummary(UUID divisionId) {
        Division division = divisionRepo.findById(divisionId)
                .orElseThrow(() -> new VariableNotFound("Division"));

        List<Student> students = division.getStudent();

        log.info("Attendance Summary for Division : {} where college is : {} is given",division.getName() , division.getCollege().getName());

        return students.stream().map(student -> {
            List<Attendance> attendances = attendanceRepo.findAllByStudent(student);

            long total = attendances.size();
            long presents = attendances.stream()
                    .filter(a -> a.getStatus() != null && a.getStatus().name().equals("PRESENT"))
                    .count();
            long absents = attendances.stream()
                    .filter(a -> a.getStatus() != null && a.getStatus().name().equals("ABSENT"))
                    .count();

            double percentage = total > 0 ? (presents * 100.0 / total) : 0;

            return StudentAttendanceSummaryDto.builder()
                    .student_name(student.getFirst_name() + " " + student.getSurname())
                    .roll_number(student.getRoll_number())
                    .total_lectures(total)
                    .presents(presents)
                    .absents(absents)
                    .attendance_percentage(percentage)
                    .build();
        }).toList();
    }

}
