package com.studentmanagement.model;

import jakarta.persistence.*;

@Entity
@Table(name = "scores")
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;
    
    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;
    
    @Column(nullable = false)
    private Double score;
    
    private String semester;
    private Integer year;
    
    // Constructors
    public Score() {}
    
    public Score(Student student, Subject subject, Double score, String semester, Integer year) {
        this.student = student;
        this.subject = subject;
        this.score = score;
        this.semester = semester;
        this.year = year;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }
    
    public Subject getSubject() { return subject; }
    public void setSubject(Subject subject) { this.subject = subject; }
    
    public Double getScore() { return score; }
    public void setScore(Double score) { this.score = score; }
    
    public String getSemester() { return semester; }
    public void setSemester(String semester) { this.semester = semester; }
    
    public Integer getYear() { return year; }
    public void setYear(Integer year) { this.year = year; }
}