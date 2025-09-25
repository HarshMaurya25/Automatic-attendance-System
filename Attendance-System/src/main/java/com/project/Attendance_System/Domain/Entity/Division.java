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
@Table(name = "divisions")
public class Division {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "department")
    private Department department;


    @ManyToOne
    @JoinColumn(name = "college")
    private College college;

    @ManyToMany
    @JoinTable(
            name = "division_course",
            joinColumns = @JoinColumn(name = "divisions"),
            inverseJoinColumns = @JoinColumn(name = "courses")
    )
    private List<Course> courses;

    private String class_Teacher;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "attendance")
    private Attendance attendance;

    @JsonIgnore
    @OneToOne(mappedBy = "division" , cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    private LoginSessions loginSessions;
}
