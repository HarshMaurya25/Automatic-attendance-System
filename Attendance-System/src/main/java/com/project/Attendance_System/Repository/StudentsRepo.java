package com.project.Attendance_System.Repository;

import com.project.Attendance_System.Domain.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StudentsRepo extends JpaRepository<Student, UUID> {

    boolean existsByEmail(String email);

    @Query("SELECT s.id FROM Student s WHERE s.email = :email")
    UUID getIdByEmail(@Param("email") String email);

    boolean existsByEmailIgnoreCase(String email);
}
