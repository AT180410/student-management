package com.studentmanagement.service;

import com.studentmanagement.model.Student;
import com.studentmanagement.repository.StudentRepository;
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
class StudentServiceTest {
    
    @Mock
    private StudentRepository studentRepository;
    
    @InjectMocks
    private StudentService studentService;
    
    @Test
    void testGetAllStudents() {
        // Given
        Student student1 = new Student("SV001", "Student 1", "test1@email.com", "123", "Address 1");
        Student student2 = new Student("SV002", "Student 2", "test2@email.com", "456", "Address 2");
        when(studentRepository.findAll()).thenReturn(Arrays.asList(student1, student2));
        
        // When
        List<Student> result = studentService.getAllStudents();
        
        // Then
        assertEquals(2, result.size());
        assertEquals("SV001", result.get(0).getStudentCode());
        assertEquals("SV002", result.get(1).getStudentCode());
    }
    
    @Test
    void testSaveStudent() {
        // Given
        Student student = new Student("SV001", "Test Student", "test@email.com", "123", "Address");
        when(studentRepository.save(student)).thenReturn(student);
        
        // When
        Student result = studentService.saveStudent(student);
        
        // Then
        assertEquals("SV001", result.getStudentCode());
        assertEquals("Test Student", result.getFullName());
    }
}
