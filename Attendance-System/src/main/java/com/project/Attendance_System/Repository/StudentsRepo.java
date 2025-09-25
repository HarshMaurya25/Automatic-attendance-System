package com.project.Attendance_System.Repository;

import com.project.Attendance_System.Domain.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StudentsRepo extends JpaRepository<Student, UUID> {
}
