package ru.gb.SpringREST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 1. Создать spring-boot приложение с помощью https://start.spring.io/
 * 2. Создать Класс Student c полями: идентификатор, имя, имя группы
 * 3. Создать контроллер, обрабатывающий входящие запросы:
 * 3.1 GET /student/{id} - получить студента по ID
 * 3.2 GET /student - получить всех студентов
 * 3.3 GET /student/search?name='studentName' - получить список студентов, чье имя содержит подстроку studentName
 * 3.4 GET /group/{groupName}/student - получить всех студентов группы
 * 3.5 POST /student - создать студента (принимает JSON) (отладиться можно с помощью Postman)
 * 3.6 DELETE /student/{id} - удалить студента
 * 4. При старте приложения, в программе должно быть создано 5-10 студентов.
 */

@RestController
public class StudentsController {

    private StudentsRepository repository;

    @Autowired
    public StudentsController(StudentsRepository repository) {
        this.repository = repository;
    };

    @GetMapping(path = "/students/")
    public List<Student> getAllStudents() {
        return repository.getAll();
    }

    @GetMapping(path = "/students/{id}")
    public Student getStudentById(@PathVariable int id) {
        return repository.getById(id);
    }

    @GetMapping("/students")
    public List<Student> getStudentByName(@RequestParam String name) {
        return repository.getByName(name);
    }

    @GetMapping("/group")
    public List<Student> getStudentByGroup(@RequestParam String groupName) {
        return repository.getByGroup(groupName);
    }

    @DeleteMapping("/students/{id}")
    public void deleteStudent(@PathVariable int id) {
        repository.deleteById(id);
    }

    @PostMapping("/students/")
    public Student addNewStudent(@RequestBody Student student) {
        this.repository.addStudent(student.getName(), student.getGroupName());
        return student;
    }




}
