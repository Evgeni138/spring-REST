package ru.gb.SpringREST;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@Primary
public class StudentsRepository {
    private final List<Student> students;

    public StudentsRepository() {
        this.students = new ArrayList<>();
        students.add(new Student("Eugene", "Group_1"));
        students.add(new Student("Vika", "Group_2"));
        students.add(new Student("Olya", "Group_2"));
        students.add(new Student("Janna", "Group_1"));
        students.add(new Student("Ira", "Group_1"));
    }

    public void addStudent(String name, String groupName) {
        students.add(new Student(name, groupName));
    }
    public List<Student> getAll() {
        return List.copyOf(students);
    }

    public Student getById(int id) {
        return students.stream()
                .filter(it -> Objects.equals(it.getId(), id))
                .findFirst()
                .orElse(null);
    }

    public List<Student> getByName(String name) {
        return students.stream()
                .filter(student -> student.getName().contains(name))
                .collect(Collectors.toList());
    }

    public  List<Student> getByGroup(String groupName) {
        return students.stream()
                .filter(student -> student.getGroupName().contains(groupName))
                .collect(Collectors.toList());
    }

    public void deleteById(int id) {
        this.students.removeIf(student -> Objects.equals(student.getId(), id));
    }
}
