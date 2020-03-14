package com.employee.reports.er.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reports")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "report")
    private Employee employee;

    @NotNull
    @Length(max = 30)
    private String title;

    @NotNull
    @Length(max = 50)
    private String description;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Priority priority;

}
