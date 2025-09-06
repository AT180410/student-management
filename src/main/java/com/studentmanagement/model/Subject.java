package com.studentmanagement.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "subjects")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String subjectCode;
    
    @Column(nullable = false)
    private String subjectName;
    
    private Integer credits;
    private String description;
    
    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL)
    private List<Score> scores;
    
    // Constructors
    public Subject() {}
    
    public Subject(String subjectCode, String subjectName, Integer credits, String description) {
        this.subjectCode = subjectCode;
        this.subjectName = subjectName;
        this.credits = credits;
        this.description = description;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getSubjectCode() { return subjectCode; }
    public void setSubjectCode(String subjectCode) { this.subjectCode = subjectCode; }
    
    public String getSubjectName() { return subjectName; }
    public void setSubjectName(String subjectName) { this.subjectName = subjectName; }
    
    public Integer getCredits() { return credits; }
    public void setCredits(Integer credits) { this.credits = credits; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public List<Score> getScores() { return scores; }
    public void setScores(List<Score> scores) { this.scores = scores; }
}