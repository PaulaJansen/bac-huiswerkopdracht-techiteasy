package nl.novi.homework.techiteasy.services;

import jakarta.transaction.Transactional;
import nl.novi.homework.techiteasy.models.Authority;
import nl.novi.homework.techiteasy.models.Role;
import nl.novi.homework.techiteasy.models.RoleAuthority;
import nl.novi.homework.techiteasy.repositories.AuthorityRepository;
import nl.novi.homework.techiteasy.repositories.RoleAuthorityRepository;
import nl.novi.homework.techiteasy.repositories.RoleRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class RoleService {

    private final RoleRepository roleRepository;
    private final AuthorityRepository authorityRepository;
    private final RoleAuthorityRepository roleAuthorityRepository;

    public RoleService(RoleRepository roleRepository,
                       AuthorityRepository authorityRepository,
                       RoleAuthorityRepository roleAuthorityRepository) {
        this.roleRepository = roleRepository;
        this.authorityRepository = authorityRepository;
        this.roleAuthorityRepository = roleAuthorityRepository;
    }

    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    public Role findByName(String rolename) {
        return roleRepository.findByName(rolename)
                .orElseThrow(() -> new RuntimeException("Role not found: " + rolename));
    }

    public Role createRole(String rolename) {
        if (roleRepository.existsByName(rolename)) {
            throw new RuntimeException("Role already exists: " + rolename);
        }
        Role role = new Role();
        role.setRolename(rolename);
        return roleRepository.save(role);
    }

    public void deleteRole(long id) {
        roleRepository.deleteById(id);
    }

    public Role addAuthorityToRole(long id, Long authorityId) {
        Role role = (Role) roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found"));
        Authority authority = authorityRepository.findById(authorityId)
                .orElseThrow(() -> new RuntimeException("Authority not found"));

        RoleAuthority roleAuthority = new RoleAuthority(role, authority);
        roleAuthorityRepository.save(roleAuthority);

        role.getAuthorities().add((GrantedAuthority) roleAuthority);
        return roleRepository.save(role);
    }
}
