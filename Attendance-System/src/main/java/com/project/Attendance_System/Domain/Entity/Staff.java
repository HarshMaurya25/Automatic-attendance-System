package com.project.Attendance_System.Domain.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.Attendance_System.Domain.Enum.Authority;
import com.project.Attendance_System.Domain.Enum.Gender;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "staffs")
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    @Temporal(TemporalType.DATE)
    private LocalDate date_of_birth;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @ManyToOne
    @JoinColumn(name = "department")
    private Department department;

    @ManyToMany
    @JoinTable(
            name = "teacher_course",
            joinColumns = @JoinColumn(name = "teachers"),
            inverseJoinColumns = @JoinColumn(name = "courses")
    )
    private List<Course> courses;

    @ManyToOne
    @JoinColumn(name = "college")
    private College college;

    private String position;

    @Enumerated(EnumType.STRING)
    private Authority authority;

    private String phone_number;

    @UniqueElements
    private String email;

    private String address;

    private String city;

    private String state;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "attendance")
    private Attendance attendance;

}
