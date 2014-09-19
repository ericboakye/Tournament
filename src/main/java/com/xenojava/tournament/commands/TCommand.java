package com.xenojava.tournament.commands;


import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;

/**
 * @author Xeno
 */
public abstract class TCommand {

    private String name;
    private Permission permission;

    public TCommand(String name) {
        this.name = name;
    }

    public TCommand(String name, String permission) {
        this.name = name;
        this.permission = new Permission(permission);
        this.permission.setDefault(PermissionDefault.OP);
    }

    public TCommand(String name, Permission permission) {
        this.name = name;
        this.permission = permission;
    }

    public String getName() {
        return name;
    }

    public Permission getPermission() {
        return permission;
    }

    public abstract void execute(CommandSender sender, String[] args) throws CommandException;

    public abstract String[] getAliases();
}
