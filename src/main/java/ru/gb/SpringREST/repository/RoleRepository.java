package ru.gb.SpringREST.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.SpringREST.model.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findById(long id);
}
