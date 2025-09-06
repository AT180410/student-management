package com.studentmanagement.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StudentTest {
    
    @Test
    void testCreateStudent() {
        Student student = new Student("SV001", "Nguyen Van A", "test@email.com", "0123456789", "Ha Noi");
        
        assertEquals("SV001", student.getStudentCode());
        assertEquals("Nguyen Van A", student.getFullName());
        assertEquals("test@email.com", student.getEmail());
    }
    
    @Test
    void testStudentSetters() {
        Student student = new Student();
        student.setStudentCode("SV002");
        student.setFullName("Tran Thi B");
        
        assertEquals("SV002", student.getStudentCode());
        assertEquals("Tran Thi B", student.getFullName());
    }
}