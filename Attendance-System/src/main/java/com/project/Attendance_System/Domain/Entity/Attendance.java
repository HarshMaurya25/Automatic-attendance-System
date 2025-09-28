package com.project.Attendance_System.Domain.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.Attendance_System.Domain.Enum.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "attendances")
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "student")
    private Student student;

    @OneToOne(mappedBy = "attendance" , cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    private Course course;

    @OneToOne(mappedBy = "attendance" , cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    private Division division;

    @OneToOne(mappedBy = "attendance" , cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    private Staff teacher;

    @Temporal(TemporalType.TIME)
    private LocalTime time;

    @Temporal(TemporalType.DATE)
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "lecture_log_id")
    private LectureLogs lectureLog;
}
