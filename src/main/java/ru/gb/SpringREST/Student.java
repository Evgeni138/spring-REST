package ru.gb.SpringREST;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Data;

@Data
public class Student {
    private static int studentId = 1;
    private int id;
    private String name;
    private String groupName;

    @JsonCreator
    public Student(int id, String name, String groupName) {
        this.id = id;
        this.name = name;
        this.groupName = groupName;
    }

    public Student(String name, String groupName) {
        this.id = studentId++;
        this.name = name;
        this.groupName = groupName;
    }
}
