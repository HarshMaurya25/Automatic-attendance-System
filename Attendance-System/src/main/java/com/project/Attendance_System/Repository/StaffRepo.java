package com.project.Attendance_System.Repository;

import com.project.Attendance_System.Domain.Entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StaffRepo extends JpaRepository< Staff,UUID> {

    @Query("SELECT s.id FROM Staff s WHERE s.email = :email")
    UUID getStaffIdByEmail(@Param("email") String email);
}
