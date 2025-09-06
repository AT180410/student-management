package com.studentmanagement.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String studentCode;
    
    @Column(nullable = false)
    private String fullName;
    
    @Column(nullable = false)
    private String email;
    
    private String phone;
    private String address;
    
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Score> scores;
    
    // Constructors
    public Student() {}
    
    public Student(String studentCode, String fullName, String email, String phone, String address) {
        this.studentCode = studentCode;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getStudentCode() { return studentCode; }
    public void setStudentCode(String studentCode) { this.studentCode = studentCode; }
    
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    
    public List<Score> getScores() { return scores; }
    public void setScores(List<Score> scores) { this.scores = scores; }
}