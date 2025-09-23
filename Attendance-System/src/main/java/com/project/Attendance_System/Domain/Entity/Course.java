package com.project.Attendance_System.Domain.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    private Integer course_code;

    @ManyToMany(mappedBy = "courses" , cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    private List<Staff> teachers;

    @ManyToOne
    @JoinColumn(name = "college")
    private College college;

    private Integer semester;

    @ManyToOne
    @JoinColumn(name = "department")
    private Department department;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "attendance")
    private Attendance attendance;

}
