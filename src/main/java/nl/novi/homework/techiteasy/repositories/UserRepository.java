package nl.novi.homework.techiteasy.repositories;

import nl.novi.homework.techiteasy.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
