package com.studentmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StudentManagementApplication {
    public static void main(String[] args) {
        SpringApplication.run(StudentManagementApplication.class, args);
        System.out.println("=================================================");
        System.out.println("🎓 Student Management System đã khởi động!");
        System.out.println("📱 Truy cập: http://localhost:8080");
        System.out.println("=================================================");
    }
}