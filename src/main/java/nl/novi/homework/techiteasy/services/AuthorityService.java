package nl.novi.homework.techiteasy.services;

import jakarta.transaction.Transactional;
import nl.novi.homework.techiteasy.models.Authority;
import nl.novi.homework.techiteasy.repositories.AuthorityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class AuthorityService {

    private final AuthorityRepository authorityRepository;

    public AuthorityService(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    public List<Authority> findAll() {
        return authorityRepository.findAll();
    }

    public Authority findById(long id) {
        return authorityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Authority not found: " + id));
    }

    public Authority createAuthority(long id) {
        if (authorityRepository.existsById(id)) {
            throw new RuntimeException("Authority already exists: " + id);
        }
        Authority authority = new Authority();
        authority.setId(id);
        return authorityRepository.save(authority);
    }

    public void deleteAuthority(Long id) {
        authorityRepository.deleteById(id);
    }
}
