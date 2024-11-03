package ru.uunit.students.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StudentUpdateRequest {

    private String name;
    private Integer age;
    private Integer courseNumber;
    private String groupName;

    @JsonProperty
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("age")
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @JsonProperty("courseNumber")
    public Integer getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(Integer courseNumber) {
        this.courseNumber = courseNumber;
    }

    @JsonProperty("groupName")
    public String getGroupName() {
        return  groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
