package ru.gb.SpringREST.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import ru.gb.SpringREST.model.User;

import java.util.List;

//@NoRepositoryBean
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u where u.name = :myvalue")
    List<User> myquery(String myvalue);

    @Query(nativeQuery = true, value = "SELECT id FROM USERS WHERE age = 20")
    List<Integer> myquery2();

    List<User> findAllByName(String name);

    List<User> findUserByNameOrAge(String name, Integer age);

    Page<User> findAllByAgeGreaterThan(Pageable pageable, Integer x);

}
