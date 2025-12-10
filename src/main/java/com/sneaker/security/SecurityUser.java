package com.sneaker.security;

import com.sneaker.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Data
@AllArgsConstructor
public class SecurityUser implements UserDetails {
    
    private Account account;
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + account.getRole().name()));
    }
    
    @Override
    public String getPassword() {
        return account.getPassword();
    }
    
    @Override
    public String getUsername() {
        return account.getEmail();
    }
    
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    
    @Override
    public boolean isAccountNonLocked() {
        return account.getStatus() == Account.AccountStatus.ACTIVE;
    }
    
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    
    @Override
    public boolean isEnabled() {
        return account.getStatus() == Account.AccountStatus.ACTIVE;
    }
    
    public Integer getId() {
        return account.getId();
    }
}

