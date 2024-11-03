package ru.uunit.students.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.uunit.students.dto.StudentUpdateRequest;
import ru.uunit.students.model.Student;
import ru.uunit.students.repository.StudentRepository;

import java.util.List;

@Service
@Primary
public class StudentServiceDb implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceDb(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> getStudents() throws Exception {
        return studentRepository.findAll();
    }

    @Override
    public  Student getStudent(int id) throws Exception {
        return studentRepository.findById(id).orElse(new Student());
    }

    @Override
    public Student addStudent(Student student) throws Exception {
        return  studentRepository.save(student);
    }

    @Override
    public Student editStudent(int id, StudentUpdateRequest updateRequest) throws Exception {
        Student findStudent = getStudent(id);

        if (updateRequest.getName() != null) {
            findStudent.setName(updateRequest.getName());
        }
        if (updateRequest.getAge() != null) {
            findStudent.setAge(updateRequest.getAge());
        }
        if (updateRequest.getCourseNumber() != null) {
            findStudent.setCourseNumber(updateRequest.getCourseNumber());
        }
        if (updateRequest.getGroupName() != null) {
            findStudent.setGroupName(updateRequest.getGroupName());
        }

        return studentRepository.save(findStudent);
    }

    @Override
    public void deleteStudent(int id) throws Exception {
        studentRepository.deleteById(id);
    }
}
