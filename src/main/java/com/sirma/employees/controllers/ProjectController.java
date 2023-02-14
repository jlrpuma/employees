package com.sirma.employees.controllers;

import com.sirma.employees.models.ProjectEmployee;
import com.sirma.employees.service.ProjectEmployeeService;
import com.sirma.employees.utils.CVSReader;
import com.sirma.employees.utils.EmployeePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class ProjectController {

    @Autowired
    private ProjectEmployeeService projectEmployeeService;

    @PostMapping("/upload")
    public ResponseEntity<EmployeePair> handleFileUpload(@RequestParam("file") MultipartFile file) {
        try {

            List<String[]> rows = CVSReader.readCSV(file.getInputStream());
            List<ProjectEmployee> projectEmployees = projectEmployeeService.rowsToProjectEmployees(rows);
            EmployeePair pairEmployeesWithCommonProjects = projectEmployeeService.pairEmployeesWithCommonProjects(projectEmployees);

            return ResponseEntity.ok().body(pairEmployeesWithCommonProjects);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getMessage());
        }
    }

}
