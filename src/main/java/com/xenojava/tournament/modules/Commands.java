package com.xenojava.tournament.modules;

import com.xenojava.tournament.Module;
import com.xenojava.tournament.commands.CommandException;
import com.xenojava.tournament.commands.PluginDependent;
import com.xenojava.tournament.commands.TCommand;
import com.xenojava.tournament.security.AdminAccess;
import com.xenojava.tournament.security.Permission;
import com.xenojava.tournament.utils.Log;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.*;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.SimplePluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;

public class Commands extends Module implements CommandExecutor{


    /**
     * This class handles and executes commands from the plugin
     * commands can be easily registered and executed from the module.
     * Will be considered as a module.
     */

    // Manifest
    public Commands() {
        super("Commands", "v2.5");
    }

    public static ArrayList<TCommand> commands = new ArrayList<TCommand>();
    private JavaPlugin plugin;


    public void setup(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void end(JavaPlugin plugin) {
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        for (TCommand cmd : commands)
            if (cmd.getAliases().length > 0) {
                if (cmd.getName().equalsIgnoreCase(label)) {
                    this.processCommand(cmd, sender, args);
                    return true;
                }
                for (String aliase : cmd.getAliases())
                    if (aliase.equalsIgnoreCase(label)) {
                        this.processCommand(cmd, sender, args);
                        return true;
                    }
            } else if (cmd.getName().equalsIgnoreCase(label)) {
                this.processCommand(cmd, sender, args);
                return true;
            }
        return false;
    }

    public void reg(Class<? extends TCommand> clazz) throws Exception {
        Constructor ctor;
        TCommand cmd;
        PluginCommand pCmd;

        if (clazz.isAnnotationPresent(PluginDependent.class)) {
            ctor = clazz.getDeclaredConstructor(JavaPlugin.class);
            ctor.setAccessible(true);
            cmd = (TCommand) ctor.newInstance(plugin);

        } else cmd = clazz.newInstance();

        commands.add(cmd);
        pCmd = getCommand(cmd.getName(), plugin);
        pCmd.setExecutor(this);
        pCmd.setAliases(Arrays.asList(cmd.getAliases()));
        getCommandMap().register(plugin.getDescription().getName(), pCmd);
    }

    private boolean processCommand(TCommand cmd, CommandSender sender, String[] args) {
        if (cmd.getPermission() != null)
            if (!sender.hasPermission(cmd.getPermission())) {
                sender.sendMessage(ChatColor.RED + "You do not have this command's permission");
                return true;
            }

        if (cmd instanceof AdminAccess) {
            if (!sender.hasPermission(Permission.ADMIN.getRealPermission())) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', ((AdminAccess) cmd).error()));
                return true;
            }
        }

        try {
            cmd.execute(sender, args);
            return true;
        } catch (CommandException e) {
            sender.sendMessage(e.getMessage());
            e.printStackTrace();
        }
        return false;
    }


    private CommandMap getCommandMap() {
        CommandMap commandMap = null;
        try {
            if (Bukkit.getPluginManager() instanceof SimplePluginManager) {
                Field f = SimplePluginManager.class.getDeclaredField("commandMap");
                f.setAccessible(true);

                commandMap = (CommandMap) f.get(Bukkit.getPluginManager());
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return commandMap;
    }

    private PluginCommand getCommand(String name, Plugin plugin) {
        PluginCommand command = null;
        try {
            Constructor<PluginCommand> c = PluginCommand.class.getDeclaredConstructor(String.class, Plugin.class);
            c.setAccessible(true);

            command = c.newInstance(name, plugin);
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return command;
    }


}
