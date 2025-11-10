package nl.novi.homework.techiteasy.models;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "role_authorities")
public class RoleAuthority implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private AuthorityKey id;

    @ManyToOne
    @MapsId("roleId")
    @JoinColumn(name = "role_id")
    private Role role;

    @ManyToOne
    @MapsId("authorityId")
    @JoinColumn(name = "authority_id")
    private Authority authority;

    public RoleAuthority() {}

    public RoleAuthority(Role role, Authority authority) {
        this.role = role;
        this.authority = authority;
        this.id = new AuthorityKey(role.getId(), authority.getId());
    }

    // getters & setters
    public Authority getAuthority() { return authority; }
    public void setAuthority(Authority authority) { this.authority = authority; }
    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }
}