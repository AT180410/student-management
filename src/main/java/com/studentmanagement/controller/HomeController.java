package com.studentmanagement.controller;

import com.studentmanagement.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/")
    public String home(Model model) {
        // Lấy danh sách sinh viên từ service
        model.addAttribute("students", studentService.getAllStudents());
        return "index"; // Trả về index.html
    }
}
