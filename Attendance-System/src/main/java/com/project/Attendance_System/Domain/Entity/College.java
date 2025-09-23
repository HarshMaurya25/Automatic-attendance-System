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
@Table(name = "colleges")
public class College {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    private String college_code;

    @OneToMany(mappedBy = "college" , cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    private List<Department> department;

    @OneToMany(mappedBy = "college" , cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    private List<Staff> staffs;

    @JsonIgnore
    @OneToMany(mappedBy = "college" , cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    private List<Course> courses;

    @JsonIgnore
    @OneToMany(mappedBy = "college" , cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    private List<Division> divisions;

    @JsonIgnore
    @OneToMany(mappedBy = "college" , cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    private List<Student> students;

    @JsonIgnore
    @OneToMany(mappedBy = "college" , cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    private List<LoginSessions> loginSessions;
}
