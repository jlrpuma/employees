package com.sirma.employees.service;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.sirma.employees.models.ProjectEmployee;
import com.sirma.employees.utils.EmployeePair;
import com.sirma.employees.utils.EmployeePairProject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ProjectEmployeeServiceTests {

    @InjectMocks
    private ProjectEmployeeServiceImpl projectEmployeeServicel;


    @DisplayName("Check multiple overlapped times")
    @Test
    public void givenProjectEmployeeList_whenOverlappedTimes_returnEmployeesOverlapped() {
        List<ProjectEmployee> list = new ArrayList<>();
        list.add(ProjectEmployee.builder().empId(143).projectId(12).dateFrom(LocalDate.parse("2013-11-01")).dateTo(LocalDate.parse("2014-01-05")).build());
        list.add(ProjectEmployee.builder().empId(218).projectId(10).dateFrom(LocalDate.parse("2011-03-27")).dateTo(LocalDate.now()).build());
        list.add(ProjectEmployee.builder().empId(143).projectId(10).dateFrom(LocalDate.parse("2009-01-01")).dateTo(LocalDate.parse("2011-04-27")).build());
        list.add(ProjectEmployee.builder().empId(145).projectId(10).dateFrom(LocalDate.parse("2011-02-27")).dateTo(LocalDate.now()).build());
        list.add(ProjectEmployee.builder().empId(219).projectId(10).dateFrom(LocalDate.parse("2009-01-01")).dateTo(LocalDate.parse("2011-04-27")).build());
        try {
            List<EmployeePairProject> employeePair = projectEmployeeServicel.listEmployeesWithCommonProjects(list);
            assertThat(employeePair).hasSizeGreaterThan(0);
            assertThat(employeePair.get(0).getMaxWorkingDays()).isEqualTo(4342);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @DisplayName("Check overlapped time")
    @Test
    public void givenProjectEmployeeList_whenOverlappedTime_returnEmployeesOverlapped() {
        List<ProjectEmployee> list = new ArrayList<>();
        list.add(ProjectEmployee.builder().empId(143).projectId(12).dateFrom(LocalDate.parse("2013-11-01")).dateTo(LocalDate.parse("2014-01-05")).build());
        list.add(ProjectEmployee.builder().empId(218).projectId(10).dateFrom(LocalDate.parse("2011-03-27")).dateTo(LocalDate.now()).build());
        list.add(ProjectEmployee.builder().empId(143).projectId(10).dateFrom(LocalDate.parse("2009-01-01")).dateTo(LocalDate.parse("2011-04-27")).build());
        list.add(ProjectEmployee.builder().empId(145).projectId(10).dateFrom(LocalDate.parse("2011-02-27")).dateTo(LocalDate.now()).build());
        list.add(ProjectEmployee.builder().empId(219).projectId(10).dateFrom(LocalDate.parse("2009-01-01")).dateTo(LocalDate.parse("2011-04-27")).build());
        try {
            EmployeePair employeePair = projectEmployeeServicel.pairEmployeesWithCommonProjects(list);
            assertThat(employeePair.getEmpId1()).isEqualTo(218);
            assertThat(employeePair.getEmpId2()).isEqualTo(145);
            assertThat(employeePair.getMaxWorkingDays()).isEqualTo(4341);
        } catch (Exception e) {
          e.printStackTrace();
        }
    }

    @DisplayName("Check NO overlapped time")
    @Test
    public void givenProjectEmployeeList_whenNoOverlappedTime_returnEmployeesNotOverlapped() {
        List<ProjectEmployee> list = new ArrayList<>();
        list.add(ProjectEmployee.builder().empId(143).projectId(12).dateFrom(LocalDate.parse("2013-11-01")).dateTo(LocalDate.parse("2014-01-05")).build());
        list.add(ProjectEmployee.builder().empId(218).projectId(10).dateFrom(LocalDate.parse("2014-01-05")).dateTo(LocalDate.parse("2015-01-05")).build());
        list.add(ProjectEmployee.builder().empId(143).projectId(10).dateFrom(LocalDate.parse("2015-01-05")).dateTo(LocalDate.parse("2016-01-05")).build());
        list.add(ProjectEmployee.builder().empId(145).projectId(10).dateFrom(LocalDate.parse("2016-01-05")).dateTo(LocalDate.parse("2017-01-05")).build());
        list.add(ProjectEmployee.builder().empId(219).projectId(10).dateFrom(LocalDate.parse("2017-01-05")).dateTo(LocalDate.parse("2018-01-05")).build());

        Exception thrown = assertThrows(
                Exception.class,
                () -> projectEmployeeServicel.pairEmployeesWithCommonProjects(list),
                "Expected pairEmployeesWithCommonProjects() to throw, but it didn't"
        );

        assertTrue(thrown.getMessage().contentEquals("There is no overlapped times between the employees on csv"));

    }

}
