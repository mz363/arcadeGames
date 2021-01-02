package com.mz363.arcadegames.security;

public enum ApplicationUserPermission {
    PLAYER_READ("player:read"),
    PLAYER_WRITE("player:write"),
    GAME_READ("game:read"),
    GAME_WRITE("game:write"),
    USER_READ("user:read");

    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}