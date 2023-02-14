package com.sirma.employees.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class TimeUtil {

    public static boolean wasTimeOverlapped(LocalDate fromEmp1, LocalDate toEmp1, LocalDate fromEmp2, LocalDate toEmp2) {
        return !fromEmp1.isAfter(toEmp2) && !fromEmp2.isAfter(toEmp1);
    }

    public static long getOverlapDays(LocalDate start1, LocalDate end1, LocalDate start2, LocalDate end2) {
        LocalDate latestStart = start1.isAfter(start2) ? start1 : start2;
        LocalDate earliestEnd = end1.isBefore(end2) ? end1 : end2;
        long overlapDays = Math.max(0, ChronoUnit.DAYS.between(latestStart, earliestEnd));
        return overlapDays;
    }

    public static LocalDate parseDate(String dateString) {
        String[] dateFormats = {"yyyy-MM-dd", "MM/dd/yyyy", "dd/MM/yyyy", "MM-dd-yyyy", "dd-MM-yyyy"};

        for (String format : dateFormats) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
                LocalDate date = LocalDate.parse(dateString, formatter);
                return date;
            } catch (Exception e) {
            }
        }

        throw new IllegalArgumentException("Invalid date format: " + dateString);
    }
}
