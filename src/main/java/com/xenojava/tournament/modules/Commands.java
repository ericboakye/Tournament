package com.xenojava.tournament.modules;

import com.xenojava.tournament.Module;
import com.xenojava.tournament.Tournament;
import com.xenojava.tournament.api.TournamentAPI;
import com.xenojava.tournament.commands.InterfaceDependant;
import com.xenojava.tournament.commands.CommandException;
import com.xenojava.tournament.commands.TCommand;
import com.xenojava.tournament.commands.AdminAccess;
import com.xenojava.tournament.utils.Permission;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.*;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.SimplePluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;

public class Commands extends Module implements CommandExecutor {


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
    private PluginCommand tcommand;


    public void setup(JavaPlugin plugin) {
        this.plugin = plugin;

        // TCommand registration
        tcommand = getCommand("t", plugin);
        tcommand.setExecutor(this);
        tcommand.setAliases(Arrays.asList("tournament"));
        getCommandMap().register(plugin.getDescription().getName(), tcommand);
    }

    public void end(JavaPlugin plugin) {
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!command.equals(tcommand)) {
            return true;
        }

        if (args.length == 0) {
            sender.sendMessage("Too few arguments!");
            return true;
        }

        ArrayList<String> a = new ArrayList<String>(Arrays.asList(args));
        a.remove(0);

        String[] arguments = a.toArray(new String[a.size()]);

        for (TCommand cmd : commands)
            if (cmd.getAliases().length > 0) {
                if (cmd.getName().equalsIgnoreCase(args[0])) {
                    this.processCommand(cmd, sender, arguments);
                    return true;
                }
                for (String aliase : cmd.getAliases())
                    if (aliase.equalsIgnoreCase(args[0])) {
                        this.processCommand(cmd, sender, arguments);
                        return true;
                    }
            } else if (cmd.getName().equalsIgnoreCase(args[0])) {
                this.processCommand(cmd, sender, arguments);
                return true;
            }

        sender.sendMessage("Unknown command!");

        return false;
    }

    public void reg(Class<? extends TCommand> clazz) throws Exception {
        Constructor ctor;
        TCommand cmd;

        if (clazz.isAnnotationPresent(InterfaceDependant.class)) {
            ctor = clazz.getDeclaredConstructor(TournamentAPI.class);
            ctor.setAccessible(true);
            cmd = (TCommand) ctor.newInstance(Tournament.getAPI());

        } else cmd = clazz.newInstance();

        commands.add(cmd);
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
