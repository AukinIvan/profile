package com.testtask.profile.config;

import com.testtask.profile.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotNull;
import java.util.Collection;

public class CustomUserDetails implements UserDetails {
    private String login;
    private String password;

    public static CustomUserDetails fromUserToCustomUserDetails(@NotNull User user) {
        CustomUserDetails newCustomUserDetails = new CustomUserDetails();
        newCustomUserDetails.login = user.getName();
        newCustomUserDetails.password = user.getPassword();
        return newCustomUserDetails;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
