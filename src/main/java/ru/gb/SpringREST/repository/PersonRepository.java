package ru.gb.SpringREST.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.SpringREST.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
