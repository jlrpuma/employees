package com.sirma.employees.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmployeePair {
    private Integer empId1;
    private Integer empId2;
    private Long maxWorkingDays;
}
