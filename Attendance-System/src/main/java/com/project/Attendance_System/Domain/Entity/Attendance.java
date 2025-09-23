package com.project.Attendance_System.Domain.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
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

    @OneToOne(mappedBy = "attendance" , cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    private Student student;

    @OneToOne(mappedBy = "attendance" , cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    private Course course;

    @OneToOne(mappedBy = "attendance" , cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    private Division division;

    @OneToOne(mappedBy = "attendance" , cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    private Staff teacher;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime date_time;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "session")
    private Session session;

}
