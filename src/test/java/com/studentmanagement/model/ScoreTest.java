package com.studentmanagement.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ScoreTest {
    
    @Test
    void testCreateScore() {
        Student student = new Student("SV001", "Test Student", "test@email.com", "123456789", "Test Address");
        Subject subject = new Subject("CS101", "Java", 3, "Java course");
        Score score = new Score(student, subject, 8.5, "1", 2023);
        
        assertEquals(8.5, score.getScore());
        assertEquals("1", score.getSemester());
        assertEquals(2023, score.getYear());
    }
    
    @Test
    void testScoreSetters() {
        Score score = new Score();
        score.setScore(9.0);
        score.setSemester("2");
        score.setYear(2024);
        
        assertEquals(9.0, score.getScore());
        assertEquals("2", score.getSemester());
        assertEquals(2024, score.getYear());
    }
}
