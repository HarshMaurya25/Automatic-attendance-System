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
@Table(name = "departments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    @ManyToOne
    @JoinColumn(
            name = "college"
    )
    private College college;

    @OneToMany(mappedBy = "department" , cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    private List<Course> courses;

    @OneToMany(mappedBy = "department" , cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    private List<Staff> staffs;

    private String HOD;

    @JsonIgnore
    @OneToMany(mappedBy = "department" , cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    private List<Student> students;

    @OneToMany(mappedBy = "department" , cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    private List<Division> divisions;

}
