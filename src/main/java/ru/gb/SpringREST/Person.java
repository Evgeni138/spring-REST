package ru.gb.SpringREST;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Person {
    private String firstName;
    private String lastName;
    private Integer age;
    private String company;


}
