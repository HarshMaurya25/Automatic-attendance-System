package com.project.Attendance_System.Repository;

import com.project.Attendance_System.Domain.Entity.College;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CollegeRepo extends JpaRepository<College , UUID> {
}
