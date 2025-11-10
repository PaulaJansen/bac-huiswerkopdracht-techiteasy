package nl.novi.homework.techiteasy.Dtos;

import java.util.Set;

public class UserDto {

    public String username;
    public String password;
    public boolean enabled;
    public String[] roles;
    public Set<String> authorities;

    public void setUsername(String username) {
    }

    public void setEnabled(boolean enabled) {
    }

    public void setAuthorities(Set<String> collect) {
    }
}
