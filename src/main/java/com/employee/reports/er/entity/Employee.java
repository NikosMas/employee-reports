package com.employee.reports.er.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employees", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_name"})})
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "report_id", referencedColumnName = "id")
    private Report report;

    @NotNull
    @Length(max = 30)
    @Column(name = "first_name")
    private String firstName;

    @NotNull
    @Length(max = 30)
    @Column(name = "last_name")
    private String lastName;

    @NotNull
    @Length(max = 20)
    @Column(name = "user_name")
    private String userName;

    @NotNull
    @Length(max = 30)
    @Email
    private String email;

    @NotNull
    @Enumerated(EnumType.STRING)
//    Safely add new enum value -- rename will still break
    private Gender gender;

    @NotNull
    @Length(max = 30)
    private String title;

}
