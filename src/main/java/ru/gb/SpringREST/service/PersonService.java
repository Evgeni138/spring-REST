package ru.gb.SpringREST.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.SpringREST.model.Person;
import ru.gb.SpringREST.repository.PersonRepository;

@Service
@RequiredArgsConstructor
public class PersonService {

  private final PersonRepository repository;

  @Transactional
  public void updatePerson(Long id, String newName, Integer newAge) {
    // transaction[balance-- () balance++]

    Person person = repository.findById(id)
      .orElseThrow();

    person.setName(newName);
    repository.save(person);

    fail();

    person.setAge(newAge);
    repository.save(person);
  }

  private void fail() {
    throw new RuntimeException("fail");
  }

}
