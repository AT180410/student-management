package com.studentmanagement.controller;

import com.studentmanagement.model.Score;
import com.studentmanagement.model.Student;
import com.studentmanagement.model.Subject;
import com.studentmanagement.service.ScoreService;
import com.studentmanagement.service.StudentService;
import com.studentmanagement.service.SubjectService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ScoreController.class)
class ScoreControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ScoreService scoreService;

    @MockBean
    private StudentService studentService;

    @MockBean
    private SubjectService subjectService;

    @Test
    void testListScores() throws Exception {
        mockMvc.perform(get("/scores"))
                .andExpect(status().isOk())
                .andExpect(view().name("scores/list"))
                .andExpect(model().attributeExists("scores"));
    }

    @Test
    void testAddScoreForm() throws Exception {
        mockMvc.perform(get("/scores/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("scores/add"))
                .andExpect(model().attributeExists("score"))
                .andExpect(model().attributeExists("students"))
                .andExpect(model().attributeExists("subjects"));
    }

    @Test
    void testAddScore() throws Exception {
        // Mock student và subject trả về Optional hợp lệ
        when(studentService.getStudentById(1L)).thenReturn(Optional.of(new Student()));
        when(subjectService.getSubjectById(1L)).thenReturn(Optional.of(new Subject()));

        mockMvc.perform(post("/scores/add")
                .param("studentId", "1")
                .param("subjectId", "1")
                .param("score", "8.5")
                .param("semester", "1")
                .param("year", "2023"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/scores"));
    }
}

