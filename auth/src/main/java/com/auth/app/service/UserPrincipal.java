package com.auth.app.service;

import com.google.common.collect.ImmutableList;
import com.auth.app.dao.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class UserPrincipal implements UserDetails {

    private UserEntity user;

    public UserPrincipal(UserEntity user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(user.getRoles().stream().anyMatch(roleEntity -> roleEntity.getName().trim().equals("ROLE_ADMIN"))) {
            return ImmutableList.of(new SimpleGrantedAuthority("ROLE_ADMIN"));
        } else if(user.getRoles().stream().anyMatch(roleEntity -> roleEntity.getName().trim().equals("ROLE_COURIER"))) {
            return ImmutableList.of(new SimpleGrantedAuthority("ROLE_COURIER"));
        }
        return ImmutableList.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
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
