package com.project.Attendance_System.Domain.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String first_name;

    private String surname;

    private String father_name;

    private Integer roll_number;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private LocalDate date_of_birth;

<<<<<<< HEAD
    private String acadmic_year;
=======
    private String academic_year;
>>>>>>> H2_Data

    @ManyToOne
    @JoinColumn(name = "division")
    private Division division;

    @ManyToOne
    @JoinColumn(name = "departments")
    private Department department;

    @ManyToOne
    @JoinColumn(name = "college")
    private College college;

    private String phone_number;

    @Column(unique = true)
    private String email;

    private String address;

    private String city;

    private String State;

    @OneToMany(mappedBy = "student" , cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    private List<Attendance> attendance;

    @OneToOne(mappedBy = "student" , cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    private User user;
}
