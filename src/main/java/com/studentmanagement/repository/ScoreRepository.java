package com.studentmanagement.repository;

import com.studentmanagement.model.Score;
import com.studentmanagement.model.Student;
import com.studentmanagement.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ScoreRepository extends JpaRepository<Score, Long> {
    List<Score> findByStudent(Student student);
    List<Score> findBySubject(Subject subject);
    List<Score> findByStudentAndSubject(Student student, Subject subject);
}