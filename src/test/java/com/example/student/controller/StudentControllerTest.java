// src/test/java/com/example/student/StudentControllerTest.java
package com.example.student.controller;

import com.example.student.model.Student;
import com.example.student.service.StudentService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StudentController.class)
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private StudentService studentService;

    @Test
    void getAllStudents_returnsList() throws Exception {
        Mockito.when(studentService.getAllStudents()).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/api/students"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    void getStudentById_found() throws Exception {
        Student student = new Student();
        student.setId(1L);
        Mockito.when(studentService.getStudentById(1L)).thenReturn(Optional.of(student));
        mockMvc.perform(get("/api/students/1"))
                .andExpect(status().isOk());
    }

    @Test
    void getStudentById_notFound() throws Exception {
        Mockito.when(studentService.getStudentById(2L)).thenReturn(Optional.empty());
        mockMvc.perform(get("/api/students/2"))
                .andExpect(status().isNotFound());
    }
}
