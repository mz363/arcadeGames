package com.mz363.arcadegames.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.mz363.arcadegames.security.ApplicationUserPermission.*;

public enum ApplicationUserRoles {
    PLAYER(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(GAME_READ, GAME_WRITE, PLAYER_READ, PLAYER_WRITE, USER_READ));
//    ADMINTRAINEE(Sets.newHashSet(GAME_READ, PLAYER_READ));

    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRoles(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}