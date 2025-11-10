package nl.novi.homework.techiteasy.controllers;

import nl.novi.homework.techiteasy.Dtos.RoleDto;
import nl.novi.homework.techiteasy.models.Role;
import nl.novi.homework.techiteasy.repositories.RoleRepository;
import nl.novi.homework.techiteasy.services.RoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RoleController {

    private final RoleService roleService;
    private final RoleRepository repos;

    public RoleController(RoleService roleService, RoleRepository repos) {
        this.roleService = roleService;
        this.repos = repos;
    }
    @GetMapping("/roles")
    public List<RoleDto> getRoles() {
        List<RoleDto> roleDtos = new ArrayList<>();
        for (Role r : repos.findAll()) {
            RoleDto rdto = new RoleDto();
            rdto.rolename = r.getRolename();
            roleDtos.add(rdto);
        }
        return roleDtos;
    }
    
    @PostMapping
    public ResponseEntity<Role> createRole(@RequestBody RoleDto roleDto) {
        Role role = roleService.createRole(roleDto.rolename);
        return ResponseEntity.ok(role);
    }

    @PostMapping("/{roleId}/addAuthority/{authorityId}")
    public ResponseEntity<Role> addAuthority(@PathVariable Long roleId, @PathVariable Long authorityId) {
        Role role = roleService.addAuthorityToRole(roleId, authorityId);
        return ResponseEntity.ok(role);
    }
}

