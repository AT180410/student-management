package com.studentmanagement.controller;

import com.studentmanagement.model.Student;
import com.studentmanagement.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/students")
public class StudentController {
    
    @Autowired
    private StudentService studentService;
    
    @GetMapping
    public String listStudents(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        return "students/list";
    }
    
    @GetMapping("/add")
    public String addStudentForm(Model model) {
        model.addAttribute("student", new Student());
        return "students/add";
    }
    
    @PostMapping("/add")
    public String addStudent(@ModelAttribute Student student, RedirectAttributes redirectAttributes) {
        try {
            studentService.saveStudent(student);
            redirectAttributes.addFlashAttribute("success", "Thêm sinh viên thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Có lỗi xảy ra: " + e.getMessage());
        }
        return "redirect:/students";
    }
    
    @GetMapping("/edit/{id}")
    public String editStudentForm(@PathVariable Long id, Model model) {
        Student student = studentService.getStudentById(id).orElse(null);
        if (student == null) {
            return "redirect:/students";
        }
        model.addAttribute("student", student);
        return "students/edit";
    }
    
    @PostMapping("/edit/{id}")
    public String editStudent(@PathVariable Long id, @ModelAttribute Student student, RedirectAttributes redirectAttributes) {
        try {
            student.setId(id);
            studentService.saveStudent(student);
            redirectAttributes.addFlashAttribute("success", "Cập nhật sinh viên thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Có lỗi xảy ra: " + e.getMessage());
        }
        return "redirect:/students";
    }
    
    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            studentService.deleteStudent(id);
            redirectAttributes.addFlashAttribute("success", "Xóa sinh viên thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Có lỗi xảy ra: " + e.getMessage());
        }
        return "redirect:/students";
    }
}