package nl.novi.homework.techiteasy.repositories;

import nl.novi.homework.techiteasy.models.AuthorityKey;
import nl.novi.homework.techiteasy.models.RoleAuthority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleAuthorityRepository extends JpaRepository<RoleAuthority, AuthorityKey> {
}
