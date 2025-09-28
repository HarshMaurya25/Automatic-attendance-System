package com.project.Attendance_System.Domain.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "lecture_logs")
public class LectureLogs {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private LocalDateTime localDate;

    @OneToMany(mappedBy = "lectureLog", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Attendance> attendances = new ArrayList<>();

    @PrePersist
    public void onCreate() {
        localDate = LocalDateTime.now();
    }
}


