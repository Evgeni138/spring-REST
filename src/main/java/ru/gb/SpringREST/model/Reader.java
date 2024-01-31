package ru.gb.SpringREST.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "readers")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Читатели")
public class Reader {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(name = "Идентификатор читателя")
    private Long id;

    @Column(name = "name")
    @Schema(name = "Имя читателя")
    private String name;

    public Reader(String name) {
        this.name = name;
    }

}
