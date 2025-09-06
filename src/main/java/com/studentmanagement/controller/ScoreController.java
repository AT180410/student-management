package com.studentmanagement.controller;

import com.studentmanagement.model.Score;
import com.studentmanagement.model.Student;
import com.studentmanagement.model.Subject;
import com.studentmanagement.service.ScoreService;
import com.studentmanagement.service.StudentService;
import com.studentmanagement.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/scores")
public class ScoreController {

    @Autowired
    private ScoreService scoreService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private SubjectService subjectService;

    @GetMapping
    public String listScores(Model model) {
        model.addAttribute("scores", scoreService.getAllScores());
        return "scores/list";
    }

    @GetMapping("/add")
    public String addScoreForm(Model model) {
        model.addAttribute("score", new Score());
        model.addAttribute("students", studentService.getAllStudents());
        model.addAttribute("subjects", subjectService.getAllSubjects());
        return "scores/add";
    }
    
    @PostMapping("/add")
    public String addScore(@RequestParam("studentId") Long studentId,
                           @RequestParam("subjectId") Long subjectId,
                           @RequestParam("score") Double scoreValue,
                           @RequestParam(value = "semester", required = false) String semester,
                           @RequestParam(value = "year", required = false) Integer year,
                           RedirectAttributes redirectAttributes) {
        try {
            Student student = studentService.getStudentById(studentId)
                    .orElseThrow(() -> new IllegalArgumentException("Sinh viên không tồn tại"));
            Subject subject = subjectService.getSubjectById(subjectId)
                    .orElseThrow(() -> new IllegalArgumentException("Môn học không tồn tại"));

            Score score = new Score();
            score.setStudent(student);
            score.setSubject(subject);
            score.setScore(scoreValue);
            score.setSemester(semester);
            score.setYear(year);

            scoreService.saveScore(score);
            redirectAttributes.addFlashAttribute("success", "Thêm điểm thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Có lỗi xảy ra: " + e.getMessage());
        }
        return "redirect:/scores";
    }

    
    @GetMapping("/edit/{id}")
    public String editScoreForm(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Score score = scoreService.getScoreById(id).orElse(null);
        if (score == null) {
            redirectAttributes.addFlashAttribute("error", "Điểm không tồn tại!");
            return "redirect:/scores";
        }
        model.addAttribute("score", score);
        model.addAttribute("students", studentService.getAllStudents());
        model.addAttribute("subjects", subjectService.getAllSubjects());
        return "scores/edit";
    }

    @PostMapping("/edit/{id}")
    public String editScore(@PathVariable Long id,
                            @RequestParam("studentId") Long studentId,
                            @RequestParam("subjectId") Long subjectId,
                            @RequestParam("score") Double scoreValue,
                            @RequestParam(value = "semester", required = false) String semester,
                            @RequestParam(value = "year", required = false) Integer year,
                            RedirectAttributes redirectAttributes) {
        try {
            Score score = scoreService.getScoreById(id).orElseThrow(() ->
                    new IllegalArgumentException("Điểm không tồn tại"));
            Student student = studentService.getStudentById(studentId).orElseThrow(() ->
                    new IllegalArgumentException("Sinh viên không tồn tại"));
            Subject subject = subjectService.getSubjectById(subjectId).orElseThrow(() ->
                    new IllegalArgumentException("Môn học không tồn tại"));

            score.setStudent(student);
            score.setSubject(subject);
            score.setScore(scoreValue);
            score.setSemester(semester);
            score.setYear(year);

            scoreService.saveScore(score);
            redirectAttributes.addFlashAttribute("success", "Cập nhật điểm thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Có lỗi xảy ra: " + e.getMessage());
        }
        return "redirect:/scores";
    }

    @GetMapping("/delete/{id}")
    public String deleteScore(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            scoreService.deleteScore(id);
            redirectAttributes.addFlashAttribute("success", "Xóa điểm thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Có lỗi xảy ra: " + e.getMessage());
        }
        return "redirect:/scores";
    }
}
