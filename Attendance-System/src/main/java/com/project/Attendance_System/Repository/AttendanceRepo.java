package com.project.Attendance_System.Repository;

import com.project.Attendance_System.Domain.Entity.Attendance;
import com.project.Attendance_System.Domain.Entity.Course;
import com.project.Attendance_System.Domain.Entity.Division;
import com.project.Attendance_System.Domain.Entity.Student;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface AttendanceRepo extends JpaRepository<Attendance , UUID> {

    List<Attendance> findByStudentIdAndDateBetweenOrderByDateAscTimeAsc(
            UUID student, LocalDate start, LocalDate end);

    @Query("SELECT a FROM Attendance a WHERE a.course.id = :courseId AND a.division.id = :divisionId ORDER BY a.date ASC, a.time ASC")
    List<Attendance> findAllByCourseAndDivisionOrderByDateAscTimeAsc(Course course, Division division);

    List<Attendance> findAllByStudent(Student student);

    @Modifying
    @Transactional
    @Query("UPDATE Attendance a SET a.status = 'PRESENT' " +
            "WHERE a.lectureLog.id = :lectureLogId AND a.student.id = :studentId")
    int markPresent(@Param("lectureLogId") UUID lectureLogId,
                    @Param("studentId") UUID studentId);

    Attendance findByLectureLogIdAndStudentId(UUID sessionID, UUID studentId);
}
