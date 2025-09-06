package com.studentmanagement.service;

import com.studentmanagement.model.Score;
import com.studentmanagement.model.Student;
import com.studentmanagement.model.Subject;
import com.studentmanagement.repository.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ScoreService {
    
    @Autowired
    private ScoreRepository scoreRepository;
    
    public List<Score> getAllScores() {
        return scoreRepository.findAll();
    }
    
    public Optional<Score> getScoreById(Long id) {
        return scoreRepository.findById(id);
    }
    
    public Score saveScore(Score score) {
        return scoreRepository.save(score);
    }
    
    public void deleteScore(Long id) {
        scoreRepository.deleteById(id);
    }
    
    public List<Score> getScoresByStudent(Student student) {
        return scoreRepository.findByStudent(student);
    }
    
    public List<Score> getScoresBySubject(Subject subject) {
        return scoreRepository.findBySubject(subject);
    }
}