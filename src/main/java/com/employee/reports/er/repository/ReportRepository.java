package com.employee.reports.er.repository;

import com.employee.reports.er.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {

    Optional<Report> findByEmployeeUserName(String userName);
}
