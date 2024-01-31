package ru.gb.SpringREST.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "books")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Книги")
public class Book {

    @Id
    @Schema(name = "Книга")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @Schema(name = "Название", maxLength = 200)
    private String name;

    public Book(String name) {
        this.name = name;
    }

}
