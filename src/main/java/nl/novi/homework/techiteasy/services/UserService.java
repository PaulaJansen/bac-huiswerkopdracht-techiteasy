package nl.novi.homework.techiteasy.services;

import nl.novi.homework.techiteasy.Dtos.UserDto;
import nl.novi.homework.techiteasy.models.User;
import nl.novi.homework.techiteasy.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.stream.Collectors;

public class UserService {

    private final UserRepository userRepos;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository repos, PasswordEncoder passwordEncoder) {
        this.userRepos = repos;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getAllUsers() {
        return userRepos.findAll();
    }

    public User getUser(String username) {
        return userRepos.findById(username)
                .orElseThrow(() -> new RuntimeException("User met username " + username + " niet gevonden"));
    }

    public User createUser(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.username);
        user.setPassword(passwordEncoder.encode(userDto.password));

        return userRepos.save(user);
    }

    public UserDto toDto(User user) {
        UserDto dto = new UserDto();
        dto.setUsername(user.getUsername());
        dto.setEnabled(user.isEnabled());

        if (user.getAuthorities() != null) {
            dto.setAuthorities(
                    user.getAuthorities().stream()
                            .map(a -> a.getAuthority())
                            .collect(Collectors.toSet())
            );
        }

        return dto;
    }
}
