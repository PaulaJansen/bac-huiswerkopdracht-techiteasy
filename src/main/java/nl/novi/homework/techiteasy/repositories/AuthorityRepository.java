package nl.novi.homework.techiteasy.repositories;

import nl.novi.homework.techiteasy.models.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    Optional<Authority> findById(long id);
    boolean existsById(long id);
}
