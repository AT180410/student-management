package com.studentmanagement.controller;

import com.studentmanagement.model.Subject;
import com.studentmanagement.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/subjects")
public class SubjectController {
    
    @Autowired
    private SubjectService subjectService;
    
    @GetMapping
    public String listSubjects(Model model) {
        model.addAttribute("subjects", subjectService.getAllSubjects());
        return "subjects/list";
    }
    
    @GetMapping("/add")
    public String addSubjectForm(Model model) {
        model.addAttribute("subject", new Subject());
        return "subjects/add";
    }
    
    @PostMapping("/add")
    public String addSubject(@ModelAttribute Subject subject, RedirectAttributes redirectAttributes) {
        try {
            subjectService.saveSubject(subject);
            redirectAttributes.addFlashAttribute("success", "Thêm môn học thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Có lỗi xảy ra: " + e.getMessage());
        }
        return "redirect:/subjects";
    }
    
    @GetMapping("/edit/{id}")
    public String editSubjectForm(@PathVariable Long id, Model model) {
        Subject subject = subjectService.getSubjectById(id).orElse(null);
        if (subject == null) {
            return "redirect:/subjects";
        }
        model.addAttribute("subject", subject);
        return "subjects/edit";
    }
    
    @PostMapping("/edit/{id}")
    public String editSubject(@PathVariable Long id, @ModelAttribute Subject subject, RedirectAttributes redirectAttributes) {
        try {
            subject.setId(id);
            subjectService.saveSubject(subject);
            redirectAttributes.addFlashAttribute("success", "Cập nhật môn học thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Có lỗi xảy ra: " + e.getMessage());
        }
        return "redirect:/subjects";
    }
    
    @GetMapping("/delete/{id}")
    public String deleteSubject(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            subjectService.deleteSubject(id);
            redirectAttributes.addFlashAttribute("success", "Xóa môn học thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Có lỗi xảy ra: " + e.getMessage());
        }
        return "redirect:/subjects";
    }
}