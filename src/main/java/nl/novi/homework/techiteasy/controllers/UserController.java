package nl.novi.homework.techiteasy.controllers;

import nl.novi.homework.techiteasy.Dtos.UserDto;
import nl.novi.homework.techiteasy.models.Role;
import nl.novi.homework.techiteasy.models.User;
import nl.novi.homework.techiteasy.repositories.RoleRepository;
import nl.novi.homework.techiteasy.repositories.UserRepository;
import nl.novi.homework.techiteasy.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
public class UserController {

    // No UserService used in demo code!

    private final UserRepository userRepos;
    private final RoleRepository roleRepos;
    private final PasswordEncoder encoder;
    private final UserService userService;

    public UserController(UserRepository userRepos, RoleRepository roleRepos, PasswordEncoder encoder, UserService userService) {
        this.userRepos = userRepos;
        this.roleRepos = roleRepos;
        this.encoder = encoder;
        this.userService = userService;
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<UserDto> getUser(@PathVariable String username) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUsername = authentication.getName();

        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        if (!isAdmin && !loggedInUsername.equals(username)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(null);
        }

        User user = userService.getUser(username);
        UserDto dto = userService.toDto(user);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/users")
    public String createUser(@RequestBody UserDto userDto) {
        User newUser = new User();
        newUser.setUsername(userDto.username);
        newUser.setPassword(encoder.encode(userDto.password));

        Set<Role> userRoles = newUser.getRoles();
        for (String rolename : userDto.roles) {
            Optional<Role> or = roleRepos.findById("ROLE_" + rolename);
            if (or.isPresent()) {
                userRoles.add(or.get());
            }
        }

        userRepos.save(newUser);

        return "Done";
    }
}
