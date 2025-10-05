package com.project.Attendance_System.Service;

import com.project.Attendance_System.Domain.Dtos.Attendance.AttendanceResponseByCourseDto;
import com.project.Attendance_System.Domain.Dtos.Teacher.TeacherResponseDto;
import com.project.Attendance_System.Domain.Entity.Attendance;
import com.project.Attendance_System.Domain.Entity.Course;
import com.project.Attendance_System.Domain.Entity.Division;
import com.project.Attendance_System.Domain.Entity.Staff;
import com.project.Attendance_System.ExceptionHandler.Custom.VariableNotFound;
import com.project.Attendance_System.Mapper.*;
import com.project.Attendance_System.Repository.*;
import com.project.Attendance_System.Service.Interface.TeacherServiceInterface;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@Data
@AllArgsConstructor
public class TeacherService implements TeacherServiceInterface {

    private final LoginSessionMapper loginSessionMapper;
    private final DivisionMapper divisionMapper;
    private final DepartmentMapper departmentMapper;
    private final AttendanceMapper attendanceMapper;
    private final TeacherMapper teacherMapper;

    private final LoginSessionRepo loginSessionRepo;
    private final CollegeRepo collegeRepo;
    private final DepartmentRepo departmentRepo;
    private final DivisionRepo divisionRepo;
    private final AttendanceRepo attendanceRepo;
    private final CourseRepo courseRepo;
    private final StaffRepo staffRepo;

    public ResponseEntity<List<AttendanceResponseByCourseDto>> getAttendanceByCourse(UUID division_id, UUID course_id) {
        Course course = courseRepo.findById(course_id)
                .orElseThrow(() -> new VariableNotFound("Course"));

        Division division = divisionRepo.findById(division_id)
                .orElseThrow(() -> new VariableNotFound("Division"));

        List<Attendance> attendanceList = attendanceRepo.findAllByCourseAndDivisionOrderByDateAscTimeAsc(course, division);

        List<AttendanceResponseByCourseDto> attendanceResponseByCourseDtos =  attendanceList.stream()
                .map(attendanceMapper::toDtoForCourse)
                .toList();

        if(attendanceResponseByCourseDtos.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        log.info("Attendance of College : {} ,Division : {} and Course : {} is Accessed" ,division.getCollege().getName(), division.getName(), course.getName());
        return ResponseEntity.ok().body(attendanceResponseByCourseDtos);
    }

    public TeacherResponseDto getTeacher(UUID id){
        Staff teacher = staffRepo.findById(id)
                .orElseThrow(()-> new VariableNotFound("Teacher"));

        log.info("Teacher {} is accessed" , teacher.getEmail());
        return teacherMapper.toDto(teacher);
    }
}
