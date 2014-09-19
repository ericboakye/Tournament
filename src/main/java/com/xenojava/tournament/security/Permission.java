package com.xenojava.tournament.security;

import org.bukkit.Bukkit;
import org.bukkit.permissions.PermissionDefault;

/**
 * Created by Xeno on 8/3/2014.
 */
public enum Permission {
    ADMIN("tournament.admin");

    org.bukkit.permissions.Permission permission;

    Permission(String permission) {
        this.permission = new org.bukkit.permissions.Permission(permission);
        this.permission.setDefault(PermissionDefault.OP);
        Bukkit.getServer().getPluginManager().addPermission(this.permission);
    }
    public org.bukkit.permissions.Permission getRealPermission() {
        return permission;
    }
}

