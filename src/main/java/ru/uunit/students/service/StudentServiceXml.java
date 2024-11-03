package ru.uunit.students.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.uunit.students.dto.StudentUpdateRequest;
import ru.uunit.students.model.Student;
import ru.uunit.students.model.StudentList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;

@Service
public class StudentServiceXml implements StudentService {

    private static final String FILE_PATH = "C:\\uunit\\web-tech\\students\\src\\main\\resources\\students.xml";

    @Override
    public List<Student> getStudents() throws Exception {
        return getStudentList().getStudents();
    }

    @Override
    public Student getStudent(int id) throws Exception {
        return getStudents().stream().filter(student -> student.getId() == id)
                .findFirst().orElse(new Student());
    }

    @Override
    public Student addStudent(Student student) throws Exception {
        StudentList studentList = getStudentList();
        studentList.getStudents().add(student);
        save(studentList);
        return student;
    }

    @Override
    public Student editStudent(int id, StudentUpdateRequest updateRequest) throws Exception {
        StudentList studentList = getStudentList();
        Student findStudent = studentList.getStudents().stream().filter(student -> student.getId() == id)
                .findFirst().orElse(null);
        if (findStudent == null) {
            return new Student();
        }

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
        return findStudent;
    }

    @Override
    public void deleteStudent(int id) throws Exception {
        StudentList studentList = getStudentList();
        Student findStudent = studentList.getStudents().stream().filter(student -> student.getId() == id)
                .findFirst().orElse(null);
        if (findStudent != null) {
            studentList.getStudents().remove(findStudent);
            save(studentList);
        }
    }

    private StudentList getStudentList() throws Exception {
        File file = new File(FILE_PATH);
        JAXBContext context = JAXBContext.newInstance(StudentList.class);
        Unmarshaller unMarshaller = context.createUnmarshaller();
        return (StudentList) unMarshaller.unmarshal(file);
    }

    private void save(StudentList studentList) throws Exception {
        File file = new File(FILE_PATH);
        JAXBContext context = JAXBContext.newInstance(StudentList.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(studentList, file);
    }
}
