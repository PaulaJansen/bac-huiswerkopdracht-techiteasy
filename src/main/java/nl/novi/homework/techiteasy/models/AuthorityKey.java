package nl.novi.homework.techiteasy.models;

import jakarta.persistence.Embeddable;

import java.io.Serial;
import java.io.Serializable;

@Embeddable
public class AuthorityKey implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long roleId;
    private Long authorityId;

    public AuthorityKey() {}

    public AuthorityKey(Long roleId, Long authorityId) {
        this.roleId = roleId;
        this.authorityId = authorityId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AuthorityKey that)) return false;
        return roleId.equals(that.roleId) && authorityId.equals(that.authorityId);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(roleId, authorityId);
    }

    public Long getRoleId() { return roleId; }

    public void setRoleId(Long roleId) { this.roleId = roleId; }

    public Long getAuthorityId() { return authorityId; }

    public void setAuthorityId(Long authorityId) { this.authorityId = authorityId; }

}
