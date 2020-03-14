package com.employee.reports.er.repository;

import com.employee.reports.er.entity.Employee;
import com.employee.reports.er.entity.Priority;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Page<Employee> findByReportPriority(Priority priority, Pageable pageable);

}
