package com.project.Attendance_System.Repository;

import com.project.Attendance_System.Domain.Entity.Attendance;
import com.project.Attendance_System.Domain.Entity.Course;
import com.project.Attendance_System.Domain.Entity.Division;
import com.project.Attendance_System.Domain.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface AttendanceRepo extends JpaRepository<Attendance , UUID> {

    List<Attendance> findByStudentIdAndDateBetweenOrderByDateAscTimeAsc(
            UUID student, LocalDate start, LocalDate end);

    List<Attendance> findAllByCourseAndDivisionOrderByDateAscTimeAsc(Course course, Division division);

    List<Attendance> findAllByStudent(Student student);
}
