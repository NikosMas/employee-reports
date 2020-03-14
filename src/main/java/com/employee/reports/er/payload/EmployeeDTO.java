package com.employee.reports.er.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {

    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String genderType;
    private String title;
    private ReportDTO report;

}
