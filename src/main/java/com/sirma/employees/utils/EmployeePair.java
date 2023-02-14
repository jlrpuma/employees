package com.sirma.employees.utils;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class EmployeePair {
    private Integer empId1;
    private Integer empId2;
    private Long maxWorkingDays;
}
