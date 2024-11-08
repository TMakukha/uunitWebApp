package ru.uunit.students.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.uunit.students.dto.StudentUpdateRequest;
import ru.uunit.students.model.Student;
import ru.uunit.students.service.StudentService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) { this.studentService = studentService; }

    @GetMapping
    public @ResponseBody
    List<Student> getStudents() throws Exception { return studentService.getStudents(); }

    @GetMapping("/{id}")
    public @ResponseBody
    Student getStudent(@PathVariable int id) throws Exception { return studentService.getStudent(id); }

    @PostMapping
    public @ResponseBody
    Student addStudent(@RequestBody Student student) throws Exception { return studentService.addStudent(student); }

    @PutMapping("/{id}")
    public @ResponseBody
    Student editStudent(@PathVariable int id, @RequestBody StudentUpdateRequest updateRequest) throws Exception { return studentService.editStudent(id, updateRequest); }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteStudent(@PathVariable int id) throws Exception { studentService.deleteStudent(id); }
}
