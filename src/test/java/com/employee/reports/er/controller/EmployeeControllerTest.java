package com.employee.reports.er.controller;

import com.employee.reports.er.entity.Gender;
import com.employee.reports.er.entity.Priority;
import com.employee.reports.er.payload.EmployeeDTO;
import com.employee.reports.er.payload.ReportDTO;
import com.employee.reports.er.service.EmployeeService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = EmployeeController.class)
public class EmployeeControllerTest {

    @MockBean
    private EmployeeService employeeService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void findReportByValidUserName() throws Exception {
        var employee = new EmployeeDTO();
        employee.setUserName("Pablo");

        var report = setReportOfEmployee();

        when(employeeService.findByEmployeeUserName(employee.getUserName())).thenReturn(report);

        var mvcResult = mockMvc.perform(get("/employee")
                .accept(MediaType.APPLICATION_JSON)
                .param("userName", "Pablo"))
                .andExpect(status().isOk())
                .andReturn();

        ReportDTO response = objectMapper.readValue(mvcResult.getResponse().getContentAsString(),
                new TypeReference<ReportDTO>() {
                });

        assertNotNull(response);
        assertEquals(response.getReportDescription(), report.getReportDescription());
    }

    @Test
    public void findEmployeesByPriority() throws Exception {

        var employees = setEmployees();

        when(employeeService.findByPriority(Priority.HIGH, PageRequest.of(0, 2))).thenReturn(employees);

        MvcResult mvcResult = mockMvc.perform(get("/employees")
                .accept(MediaType.APPLICATION_JSON)
                .param("priority", Priority.HIGH.name())
                .param("page", "0")
                .param("size", "2"))
                .andExpect(status().isOk())
                .andReturn();

        List<EmployeeDTO> response = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<List<EmployeeDTO>>() {
        });

        assertNotNull(response);
        assertEquals(2, response.size());
    }


    private List<EmployeeDTO> setEmployees() {
        List<EmployeeDTO> employees = new ArrayList<>();

        var firstEmployee = new EmployeeDTO();
        firstEmployee.setUserName("murphy");
        firstEmployee.setTitle("Peaky Blinders");
        firstEmployee.setGenderType(Gender.MALE.name());
        firstEmployee.setEmail("thomas@scl.com");
        firstEmployee.setFirstName("Thomas");
        firstEmployee.setLastName("Shelby");

        var reportOfFirstEmployee = new ReportDTO();
        reportOfFirstEmployee.setReportTitle("Birmingham");
        reportOfFirstEmployee.setReportPriority(Priority.HIGH.name());
        reportOfFirstEmployee.setReportDescription("Boss");

        firstEmployee.setReport(reportOfFirstEmployee);
        employees.add(firstEmployee);

        var secondEmployee = new EmployeeDTO();
        secondEmployee.setUserName("something");
        secondEmployee.setTitle("Fight Club");
        secondEmployee.setGenderType(Gender.FEMALE.name());
        secondEmployee.setEmail("marla@fc.com");
        secondEmployee.setFirstName("Marla");
        secondEmployee.setLastName("Singer");

        var reportOfSecondEmployee = new ReportDTO();
        reportOfSecondEmployee.setReportTitle("Chemistry");
        reportOfSecondEmployee.setReportPriority(Priority.HIGH.name());
        reportOfSecondEmployee.setReportDescription("Player");

        secondEmployee.setReport(reportOfSecondEmployee);
        employees.add(secondEmployee);
        return employees;
    }

    private ReportDTO setReportOfEmployee() {
        var report = new ReportDTO();
        report.setReportDescription("Colombia");
        report.setReportPriority(Priority.HIGH.name());
        report.setReportTitle("Narcos");

        return report;
    }

}