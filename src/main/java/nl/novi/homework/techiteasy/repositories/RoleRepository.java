package nl.novi.homework.techiteasy.repositories;

import nl.novi.homework.techiteasy.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, String> {
    Optional<Role> findByName(String rolename);
    boolean existsByName(String rolename);

    void deleteById(long id);

    Optional<Object> findById(long id);
}
