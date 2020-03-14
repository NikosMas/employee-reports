package com.employee.reports.er.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportDTO {

    private String reportTitle;
    private String reportDescription;
    private String reportPriority;

}
