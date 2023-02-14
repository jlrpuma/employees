package com.sirma.employees.models;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProjectEmployee {

    @NonNull
    private Integer empId;
    @NonNull
    private Integer projectId;
    @NonNull
    private LocalDate dateFrom;

    private LocalDate dateTo;


}
