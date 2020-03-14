package com.employee.reports.er.controller;

import com.employee.reports.er.entity.Priority;
import com.employee.reports.er.payload.EmployeeDTO;
import com.employee.reports.er.service.EmployeeService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("employee")
    public ResponseEntity findReportByUserName(@RequestParam(name = "userName") String userName) {
        var reportInfo = employeeService.findByEmployeeUserName(userName);

        return ResponseEntity.ok().body(reportInfo);
    }

    @GetMapping("employees")
    public ResponseEntity findEmployeesByPriority(Pageable pageable, @RequestParam(name = "priority") String priority) {
        var employee = employeeService.findByPriority(Priority.valueOf(priority), pageable);

        return ResponseEntity.ok().body(employee);
    }

    @GetMapping("employees/paging")
    public ResponseEntity findEmployeesWithPaging(Pageable paging) {
        var employees = employeeService.findAll(paging);

        return ResponseEntity.ok().body(employees);
    }

    @PostMapping("employee")
    public void create(@RequestBody EmployeeDTO dto) {
        employeeService.create(dto);
    }

}
