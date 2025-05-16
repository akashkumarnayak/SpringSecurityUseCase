package org.springsecurity.usecases.basicuserpasswordauthenticationusecase1.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springsecurity.usecases.basicuserpasswordauthenticationusecase1.models.User;
import org.springsecurity.usecases.basicuserpasswordauthenticationusecase1.models.UserState;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DBUserDetails implements UserDetails {

    private User user;

    DBUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<GrantedAuthority>(List.of(new SimpleGrantedAuthority("ROLE_"+user.getUserType().name())));
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return user.getUserState().equals(UserState.ACTIVE);
    }

    @Override
    public boolean isAccountNonLocked() {

        return user.getUserState().equals(UserState.ACTIVE);
    }

    @Override
    public boolean isCredentialsNonExpired() {

        return user.getUserState().equals(UserState.ACTIVE);
    }

    @Override
    public boolean isEnabled() {
        return user.getUserState().equals(UserState.ACTIVE);
    }
}
