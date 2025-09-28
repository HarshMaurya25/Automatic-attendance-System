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
@Table(name = "students", indexes = {
        @Index(name = "idx_student_email", columnList = "email")
})
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

    @Temporal(TemporalType.DATE)
    private LocalDate date_of_birth;


    private String acadmic_year;

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
