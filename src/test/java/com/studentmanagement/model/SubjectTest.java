package com.studentmanagement.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SubjectTest {
    
    @Test
    void testCreateSubject() {
        Subject subject = new Subject("CS101", "Java Programming", 3, "Basic Java");
        
        assertEquals("CS101", subject.getSubjectCode());
        assertEquals("Java Programming", subject.getSubjectName());
        assertEquals(3, subject.getCredits());
    }
    
    @Test
    void testSubjectSetters() {
        Subject subject = new Subject();
        subject.setSubjectCode("CS102");
        subject.setSubjectName("Database");
        subject.setCredits(4);
        
        assertEquals("CS102", subject.getSubjectCode());
        assertEquals("Database", subject.getSubjectName());
        assertEquals(4, subject.getCredits());
    }
}