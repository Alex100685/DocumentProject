package ua.kiev.prog.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public class CustomUserDetailsUser extends org.springframework.security.core.userdetails.User {
    private String salt;
    private String phone;
    private String username;
    private String password;

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public CustomUserDetailsUser(String username, String password, boolean enabled,
                                 boolean accountNonExpired, boolean credentialsNonExpired,
                                 boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities,
                                 String salt, String phone) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired,
                accountNonLocked, authorities);
        this.salt = salt;
        this.phone = phone;
        this.username = username;
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
