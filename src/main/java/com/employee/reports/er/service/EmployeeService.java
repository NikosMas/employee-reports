package com.employee.reports.er.service;

import com.employee.reports.er.entity.Priority;
import com.employee.reports.er.payload.EmployeeDTO;
import com.employee.reports.er.payload.ReportDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmployeeService {

    ReportDTO findByEmployeeUserName(String userName);

    List<EmployeeDTO> findAll(Pageable pageable);

    List<EmployeeDTO> findByPriority(Priority priority, Pageable pageable);

    void create(EmployeeDTO dto);
}
