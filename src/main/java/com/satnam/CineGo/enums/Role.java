package com.satnam.CineGo.enums;

import java.util.Set;

public enum Role {

    USER(Set.of(Permissions.READ)),
    ADMIN(Set.of(Permissions.READ, Permissions.WRITE, Permissions.DELETE));

    private final Set<Permissions> permissions;

    Role(Set<Permissions>permissions){
        this.permissions=permissions;
    }

    public Set<Permissions> getPermissions(){
        return permissions;
    }
}
