package com.studentmanagement.service;

import com.studentmanagement.model.Score;
import com.studentmanagement.model.Student;
import com.studentmanagement.model.Subject;
import com.studentmanagement.repository.ScoreRepository;
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
class ScoreServiceTest {
    
    @Mock
    private ScoreRepository scoreRepository;
    
    @InjectMocks
    private ScoreService scoreService;
    
    @Test
    void testGetAllScores() {
        // Given
        Student student = new Student("SV001", "Student", "test@email.com", "123", "Address");
        Subject subject = new Subject("CS101", "Java", 3, "Java course");
        Score score1 = new Score(student, subject, 8.5, "1", 2023);
        Score score2 = new Score(student, subject, 7.0, "2", 2023);
        when(scoreRepository.findAll()).thenReturn(Arrays.asList(score1, score2));
        
        // When
        List<Score> result = scoreService.getAllScores();
        
        // Then
        assertEquals(2, result.size());
        assertEquals(8.5, result.get(0).getScore());
        assertEquals(7.0, result.get(1).getScore());
    }
    
    @Test
    void testSaveScore() {
        // Given
        Student student = new Student("SV001", "Student", "test@email.com", "123", "Address");
        Subject subject = new Subject("CS101", "Java", 3, "Java course");
        Score score = new Score(student, subject, 8.5, "1", 2023);
        when(scoreRepository.save(score)).thenReturn(score);
        
        // When
        Score result = scoreService.saveScore(score);
        
        // Then
        assertEquals(8.5, result.getScore());
        assertEquals("1", result.getSemester());
    }
}
