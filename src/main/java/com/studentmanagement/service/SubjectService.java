package com.studentmanagement.service;

import com.studentmanagement.model.Subject;
import com.studentmanagement.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {
    
    @Autowired
    private SubjectRepository subjectRepository;
    
    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }
    
    public Optional<Subject> getSubjectById(Long id) {
        return subjectRepository.findById(id);
    }
    
    public Subject saveSubject(Subject subject) {
        return subjectRepository.save(subject);
    }
    
    public void deleteSubject(Long id) {
        subjectRepository.deleteById(id);
    }
    
    public Optional<Subject> findBySubjectCode(String subjectCode) {
        return subjectRepository.findBySubjectCode(subjectCode);
    }
}