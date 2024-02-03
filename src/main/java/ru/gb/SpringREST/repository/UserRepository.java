package ru.gb.SpringREST.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.SpringREST.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByLogin(String login);
}
