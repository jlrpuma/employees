package com.sirma.employees.controllers;

import com.sirma.employees.models.ProjectEmployee;
import com.sirma.employees.service.ProjectEmployeeService;
import com.sirma.employees.utils.CVSReader;
import com.sirma.employees.utils.EmployeePair;
import com.sirma.employees.utils.EmployeePairProject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/greaterOverlap")
    public ResponseEntity<EmployeePair> greaterOverlap(@RequestParam("file") MultipartFile file) {
        try {

            List<String[]> rows = CVSReader.readCSV(file.getInputStream());
            List<ProjectEmployee> projectEmployees = projectEmployeeService.rowsToProjectEmployees(rows);
            EmployeePair pairEmployeesWithCommonProjects = projectEmployeeService.pairEmployeesWithCommonProjects(projectEmployees);

            return ResponseEntity.ok().body(pairEmployeesWithCommonProjects);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getMessage());
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/multipleOverlaps")
    public ResponseEntity<List<EmployeePairProject>> multipleOverlaps(@RequestParam("file") MultipartFile file) {
        try {

            List<String[]> rows = CVSReader.readCSV(file.getInputStream());
            List<ProjectEmployee> projectEmployees = projectEmployeeService.rowsToProjectEmployees(rows);
            List<EmployeePairProject> listEmployeesWithCommonProjects = projectEmployeeService.listEmployeesWithCommonProjects(projectEmployees);

            return ResponseEntity.ok().body(listEmployeesWithCommonProjects);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getMessage());
        }
    }

}
