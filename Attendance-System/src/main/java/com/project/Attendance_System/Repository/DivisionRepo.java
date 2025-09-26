package com.project.Attendance_System.Repository;

import com.project.Attendance_System.Domain.Entity.Department;
import com.project.Attendance_System.Domain.Entity.Division;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DivisionRepo extends JpaRepository<Division , UUID> {

    List<Division> findAllByDepartment(Department department);
}
