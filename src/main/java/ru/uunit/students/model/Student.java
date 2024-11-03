package ru.uunit.students.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import java.util.Objects;

@Entity
@Table(name = "students")
@XmlAccessorType(XmlAccessType.FIELD)
public class Student {

    @Id
    @XmlElement(name = "id")
    private int id;
    @Column(name = "name")
    @XmlElement(name = "name")
    private String name;
    @Column(name = "age")
    @XmlElement(name = "age")
    private int age;
    @Column(name = "courseNumber")
    @XmlElement(name = "courseNumber")
    private int courseNumber;
    @Column(name = "groupName")
    @XmlElement(name = "groupName")
    private String groupName;

    public Student() {
    }

    public Student(int id, String name, int age, int courseNumber, String groupName) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.courseNumber = courseNumber;
        this.groupName = groupName;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public int getCourseNumber() {return  courseNumber; }
    public void setCourseNumber(int courseNumber) { this.courseNumber = courseNumber; }
    public String getGroupName() { return  groupName; }
    public void setGroupName(String groupName) { this.groupName = groupName; }


    @Override
    public boolean equals(Object o) {
        if (this == o) return  true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id;
    }

    @Override
    public int hashCode() { return Objects.hash(id); }
}
