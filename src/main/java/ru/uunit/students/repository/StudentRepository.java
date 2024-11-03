package ru.uunit.students.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.uunit.students.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
}
