package com.mz363.arcadegames.auth;

import com.mz363.arcadegames.ArcadeGamesApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import static com.mz363.arcadegames.security.ApplicationUserRoles.*;

import java.util.*;

import javax.persistence.*;

@Entity
@Table(name = "user_table")
public class ApplicationUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;

    @Transient
    private Set<? extends GrantedAuthority> grantedAuthorities;

    @Column(name = "isAccountNonExpired")
    private boolean isAccountNonExpired;

    @Column(name = "isAccountNonLocked")
    private boolean isAccountNonLocked;

    @Column(name = "isCredentialsNonExpired")
    private boolean isCredentialsNonExpired;

    @Column(name = "isEnabled")
    private boolean isEnabled;

    public ApplicationUser(Integer id,
                           String username,
                           String password,
                           String role,
                           Set<? extends GrantedAuthority> grantedAuthorities,
                           boolean isAccountNonExpired,
                           boolean isAccountNonLocked,
                           boolean isCredentialsNonExpired,
                           boolean isEnabled) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.grantedAuthorities = grantedAuthorities;
        this.isAccountNonExpired = isAccountNonExpired;
        this.isAccountNonLocked = isAccountNonLocked;
        this.isCredentialsNonExpired = isCredentialsNonExpired;
        this.isEnabled = isEnabled;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        logger.info("Role: " + this.role);
//
//        if(this.role == "PLAYER") {
//            return PLAYER.getGrantedAuthorities();
//        }
//        else if(this.role == "ADMIN") {
//            return ADMIN.getGrantedAuthorities();
//        }
//        else {
//            return PLAYER.getGrantedAuthorities();
//        }
//    }

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
//
//        list.add(new SimpleGrantedAuthority("ROLE_" + role));
//
//        return list;
//    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    public ApplicationUser() {

    }
}