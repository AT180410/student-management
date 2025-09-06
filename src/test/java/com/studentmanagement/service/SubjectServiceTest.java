package com.studentmanagement.service;

import com.studentmanagement.model.Subject;
import com.studentmanagement.repository.SubjectRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SubjectServiceTest {
    
    @Mock
    private SubjectRepository subjectRepository;
    
    @InjectMocks
    private SubjectService subjectService;
    
    @Test
    void testGetAllSubjects() {
        // Given
        Subject subject1 = new Subject("CS101", "Java", 3, "Java course");
        Subject subject2 = new Subject("CS102", "Database", 4, "DB course");
        when(subjectRepository.findAll()).thenReturn(Arrays.asList(subject1, subject2));
        
        // When
        List<Subject> result = subjectService.getAllSubjects();
        
        // Then
        assertEquals(2, result.size());
        assertEquals("CS101", result.get(0).getSubjectCode());
        assertEquals("CS102", result.get(1).getSubjectCode());
    }
    
    @Test
    void testSaveSubject() {
        // Given
        Subject subject = new Subject("CS101", "Java", 3, "Java course");
        when(subjectRepository.save(subject)).thenReturn(subject);
        
        // When
        Subject result = subjectService.saveSubject(subject);
        
        // Then
        assertEquals("CS101", result.getSubjectCode());
        assertEquals("Java", result.getSubjectName());
    }
}