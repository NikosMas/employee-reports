package com.employee.reports.er.service;

import com.employee.reports.er.entity.Employee;
import com.employee.reports.er.entity.Gender;
import com.employee.reports.er.entity.Priority;
import com.employee.reports.er.entity.Report;
import com.employee.reports.er.exception.ERNotFoundException;
import com.employee.reports.er.payload.EmployeeDTO;
import com.employee.reports.er.payload.ReportDTO;
import com.employee.reports.er.repository.EmployeeRepository;
import com.employee.reports.er.repository.ReportRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ReportRepository reportRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, ReportRepository reportRepository) {
        this.employeeRepository = employeeRepository;
        this.reportRepository = reportRepository;
    }

    @Override
    public ReportDTO findByEmployeeUserName(String userName) {
        var report = reportRepository.findByEmployeeUserName(userName)
                .orElseThrow(() -> new ERNotFoundException(userName + " not found in database"));

        return toReportDTO(report);
    }

    @Override
    public List<EmployeeDTO> findAll(Pageable pageable) {
        return employeeRepository.findAll(pageable)
                .getContent()
                .stream()
                .map(this::toEmployeeDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<EmployeeDTO> findByPriority(Priority priority, Pageable pageable) {
        return employeeRepository.findByReportPriority(priority, pageable)
                .getContent()
                .stream()
                .map(this::toEmployeeDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void create(EmployeeDTO dto) {
        employeeRepository.save(toEmployee(dto));
    }


    private EmployeeDTO toEmployeeDTO(Employee e) {
        var dto = new EmployeeDTO();
        dto.setFirstName(e.getFirstName());
        dto.setLastName(e.getLastName());
        dto.setEmail(e.getEmail());
        dto.setGenderType(e.getGender().name());
        dto.setTitle(e.getTitle());
        dto.setUserName(e.getUserName());

        var reportDTO = new ReportDTO();
        reportDTO.setReportDescription(e.getReport().getDescription());
        reportDTO.setReportPriority(e.getReport().getPriority().name());
        reportDTO.setReportTitle(e.getReport().getTitle());

        dto.setReport(reportDTO);
        return dto;
    }

    private Employee toEmployee(EmployeeDTO dto) {
        var employee = new Employee();
        employee.setFirstName(dto.getFirstName());
        employee.setLastName(dto.getLastName());
        employee.setUserName(dto.getUserName());
        employee.setEmail(dto.getEmail());
        employee.setGender(Gender.valueOf(dto.getGenderType().toUpperCase()));
        employee.setTitle(dto.getTitle());

        var report = new Report();
        report.setDescription(dto.getReport().getReportDescription());
        report.setPriority(Priority.valueOf(dto.getReport().getReportPriority().toUpperCase()));
        report.setTitle(dto.getReport().getReportTitle());

        employee.setReport(report);
        return employee;
    }

    private ReportDTO toReportDTO(Report report) {
        var dto = new ReportDTO();
        dto.setReportTitle(report.getTitle());
        dto.setReportPriority(report.getPriority().name());
        dto.setReportDescription(report.getDescription());
        return dto;
    }
}
