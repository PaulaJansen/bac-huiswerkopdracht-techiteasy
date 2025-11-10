package nl.novi.homework.techiteasy.models;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String rolename;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<RoleAuthority> authorities = new HashSet<>();

    public Set<GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> granted = new HashSet<>();
        for (RoleAuthority ra : authorities) {
            granted.add(new SimpleGrantedAuthority(ra.getAuthority().getUsername()));
        }
        return granted;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public long getId() { return id; }

    public void setId(long id) {
        this.id = id;
    }

    public void setAuthorities(Set<RoleAuthority> authorities) {
        this.authorities = authorities;
    }
}
