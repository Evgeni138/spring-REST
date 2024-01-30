package ru.gb.SpringREST.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "readers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reader2 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    public Reader2(String name) {
        this.name = name;
    }

}
