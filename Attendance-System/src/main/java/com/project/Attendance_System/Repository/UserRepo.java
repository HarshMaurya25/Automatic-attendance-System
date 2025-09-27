package com.project.Attendance_System.Repository;

import com.project.Attendance_System.Domain.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepo extends JpaRepository<User , String> {

    User findByEmail(String email);
}
