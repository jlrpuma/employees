package com.sirma.employees.controller;

import java.io.File;
import java.nio.file.Files;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProjectControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @DisplayName("Check csv upload and data returned")
    @Test
    public void givenCSV_whenOverlappedTimes_returnEmployeesOverlapped() throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("example.csv").getFile());

        MockMultipartFile multipartFile = new MockMultipartFile("file", "example.csv", "text/csv", Files.readAllBytes(file.toPath()));

        mockMvc.perform(MockMvcRequestBuilders.multipart("/multipleOverlaps")
                        .file(multipartFile))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$[0].empId1").value("218"))
                .andExpect(jsonPath("$[0].empId2").value("143"))
                .andExpect(jsonPath("$[0].project").value(10));
    }
}
