package ru.uunit.students.service;

import ru.uunit.students.dto.StudentUpdateRequest;
import ru.uunit.students.model.Student;

import java.util.List;

public interface StudentService {

    List<Student> getStudents() throws Exception;

    Student getStudent(int id) throws Exception;

    Student addStudent(Student student) throws Exception;

    Student editStudent(int id, StudentUpdateRequest updateRequest) throws Exception;

    void deleteStudent(int id) throws Exception;
}
