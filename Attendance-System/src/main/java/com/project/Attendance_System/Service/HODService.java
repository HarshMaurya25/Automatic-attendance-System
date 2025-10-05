package com.project.Attendance_System.Service;

import com.project.Attendance_System.Domain.Dtos.Department.DepartmentRequestDto;
import com.project.Attendance_System.Domain.Dtos.Department.DepartmentResponseDto;
import com.project.Attendance_System.Domain.Dtos.Division.DivisionRequestDto;
import com.project.Attendance_System.Domain.Dtos.Division.DivisionResponseDto;
import com.project.Attendance_System.Domain.Dtos.LoginSession.LoginSessionRequestDto;
import com.project.Attendance_System.Domain.Dtos.LoginSession.LoginSessionRespondDto;
import com.project.Attendance_System.Domain.Entity.*;
import com.project.Attendance_System.Domain.Enum.SessionType;
import com.project.Attendance_System.ExceptionHandler.Custom.VariableNotFound;
import com.project.Attendance_System.Mapper.DepartmentMapper;
import com.project.Attendance_System.Mapper.DivisionMapper;
import com.project.Attendance_System.Mapper.LoginSessionMapper;
import com.project.Attendance_System.Repository.*;
import com.project.Attendance_System.Service.Interface.HODServiceInterface;
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
public class HODService implements HODServiceInterface {

    private final LoginSessionMapper loginSessionMapper;
    private final DivisionMapper divisionMapper;
    private final DepartmentMapper departmentMapper;

    private final LoginSessionRepo loginSessionRepo;
    private final CollegeRepo collegeRepo;
    private final DepartmentRepo departmentRepo;
    private final DivisionRepo divisionRepo;
    private final CourseRepo courseRepo;

    public ResponseEntity<LoginSessionRespondDto> createSessionLogin(LoginSessionRequestDto loginSessionRequestDto) {
        UUID college_id = loginSessionRequestDto.getCollege();

        College college = collegeRepo.findById(college_id)
                .orElseThrow(() -> new RuntimeException("College not found"));

        LoginSessions loginSessions = null;
        LoginSessionRespondDto loginSessionRespondDto = null;

        if(loginSessionRequestDto.getSessionType().equals(SessionType.STUDENT)){
            Division division =divisionRepo.findById(loginSessionRequestDto.getPlace_identifier())
                    .orElseThrow(()-> new RuntimeException("Division Not Found"));

            loginSessions = loginSessionMapper.toLoginSessionForStudent(college , division);
            loginSessionRepo.save(loginSessions);
            loginSessionRespondDto= loginSessionMapper.toDtoForStudent(loginSessions , division);

        } else if (loginSessionRequestDto.getSessionType().equals(SessionType.TEACHER)) {
            Department department = departmentRepo.findById(loginSessionRequestDto.getPlace_identifier())
                    .orElseThrow(()-> new RuntimeException("Department Not Found"));
            loginSessions = loginSessionMapper.toLoginSessionForTeacher(college,department);

            loginSessionRespondDto = loginSessionMapper.toDtoForTeacher(loginSessions , department);
        }

        if(loginSessions == null){
            throw new RuntimeException("Error Creating Session");
        }
        return ResponseEntity.ok().body(loginSessionRespondDto);
    }

    public ResponseEntity<DivisionResponseDto> createDivision(DivisionRequestDto divisionRequestDto){
        Department department = departmentRepo.findById(divisionRequestDto.getDepartmentId())
                .orElseThrow(() -> new VariableNotFound("Department not found"));

        College college = department.getCollege();

        Division division = divisionMapper.toDivision(divisionRequestDto, department);

        List<Course> courses = courseRepo.findAllById(divisionRequestDto.getCourseIds());
        if (courses.isEmpty()) {
            throw new VariableNotFound("No valid courses found for provided IDs");
        }

        division.setCourses(courses);

        log.info("Division {} is created in College {} under Department {} with {} course(s)",
                division.getName(), college.getName(), department.getName(), courses.size());

        Division savedDivision = divisionRepo.save(division);

        DivisionResponseDto responseDto = divisionMapper.toDto(savedDivision);
        return ResponseEntity.ok(responseDto);
    }

    public ResponseEntity<List<DivisionResponseDto>> getDivisionInDepartment(UUID department_id){
        Department department = departmentRepo.findById(department_id).orElseThrow(
                () -> new VariableNotFound("Department")
        );
        List<Division> divisions = divisionRepo.findAllByDepartment(department);

        List<DivisionResponseDto> divisionResponseDtos = divisionMapper.toDtoList(divisions);

        return ResponseEntity.ok().body(divisionResponseDtos);

    }

        @Override
        public ResponseEntity<DepartmentResponseDto> createDepartment(DepartmentRequestDto dto) {
            College college = collegeRepo.findById(dto.getCollegeId())
                    .orElseThrow(() -> new VariableNotFound("College"));

            Department department = departmentMapper.toEntity(dto , college);
            departmentRepo.save(department);

            DepartmentResponseDto departmentResponseDto = departmentMapper.toDto(department);

            return ResponseEntity.ok().body(departmentResponseDto);
        }

        @Override
        public ResponseEntity<DepartmentResponseDto> getDepartment(UUID id) {
            Department department = departmentRepo.findById(id)
                    .orElseThrow(() -> new VariableNotFound("Department"));

            DepartmentResponseDto departmentResponseDto = departmentMapper.toDto(department);
            return ResponseEntity.ok().body(departmentResponseDto);
        }
}
