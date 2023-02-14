package com.sirma.employees.service;

import com.sirma.employees.models.ProjectEmployee;
import com.sirma.employees.utils.EmployeePair;

import java.util.List;

public interface ProjectEmployeeService {

    EmployeePair pairEmployeesWithCommonProjects(List<ProjectEmployee> projectEmployees) throws Exception;

    List<ProjectEmployee> rowsToProjectEmployees(List<String[]> rows);
}
