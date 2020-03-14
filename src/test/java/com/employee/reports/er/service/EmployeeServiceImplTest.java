package com.employee.reports.er.service;

import com.employee.reports.er.entity.Employee;
import com.employee.reports.er.entity.Priority;
import com.employee.reports.er.entity.Report;
import com.employee.reports.er.exception.ERNotFoundException;
import com.employee.reports.er.repository.ReportRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class EmployeeServiceImplTest {

    @Mock
    private ReportRepository reportRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Test(expected = ERNotFoundException.class)
    public void NotExistingUserName() {
        var dto = setEmployee();

        when(reportRepository.findByEmployeeUserName(dto.getEmployee().getUserName())).thenReturn(Optional.empty());

        employeeService.findByEmployeeUserName(dto.getEmployee().getUserName());
    }

    @Test
    public void findExistingUserName() {
        var dto = setEmployee();

        when(reportRepository.findByEmployeeUserName(dto.getEmployee().getUserName())).thenReturn(Optional.of(dto));

        var reportOfExistingEmployee = employeeService.findByEmployeeUserName(dto.getEmployee().getUserName());

        assertEquals(dto.getTitle(), reportOfExistingEmployee.getReportTitle());
    }

    private Report setEmployee() {
        var employee = new Employee();
        employee.setUserName("donnie");

        var report = new Report();
        report.setPriority(Priority.LOW);
        report.setTitle("title");
        report.setDescription("description");
        report.setEmployee(employee);

        return report;
    }

}