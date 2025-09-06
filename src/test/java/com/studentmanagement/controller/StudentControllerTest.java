package com.studentmanagement.controller;

import com.studentmanagement.model.Student;
import com.studentmanagement.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StudentController.class)
class StudentControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private StudentService studentService;
    
    @Test
    void testListStudents() throws Exception {
        // Given
        Student student = new Student("SV001", "Test Student", "test@email.com", "123", "Address");
        when(studentService.getAllStudents()).thenReturn(Arrays.asList(student));
        
        // When & Then
        mockMvc.perform(get("/students"))
                .andExpect(status().isOk())
                .andExpect(view().name("students/list"))
                .andExpect(model().attributeExists("students"));
    }
    
    @Test
    void testAddStudentForm() throws Exception {
        mockMvc.perform(get("/students/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("students/add"))
                .andExpect(model().attributeExists("student"));
    }
    
    @Test
    void testAddStudent() throws Exception {
        mockMvc.perform(post("/students/add")
                .param("studentCode", "SV001")
                .param("fullName", "Test Student")
                .param("email", "test@email.com")
                .param("phone", "123456789")
                .param("address", "Test Address"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/students"));
    }
}
