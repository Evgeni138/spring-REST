package ru.gb.SpringREST.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "roles")
public class Role {

    @Id
    private long id;

    @Column(name = "name")
    private String name;

}
