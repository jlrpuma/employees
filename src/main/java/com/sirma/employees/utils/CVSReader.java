package com.sirma.employees.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CVSReader {

    public static List<String[]> readCSV(InputStream inputStream) throws IOException  {
        List<String[]> rows = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line = reader.readLine()) != null) {
            rows.add(line.split(","));
        }
        return rows;
    }
}
