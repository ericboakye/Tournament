package com.xenojava.tournament;

import com.xenojava.tournament.api.TournamentAPI;
import com.xenojava.tournament.events.TournamentListener;
import com.xenojava.tournament.modules.Commands;
import com.xenojava.tournament.modules.Database;
import com.xenojava.tournament.modules.Settings;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class Manager {

    private static JavaPlugin plugin;
    private TournamentAPI api;


    private final static ArrayList<Module> modules = new ArrayList<Module>();

    public Manager(JavaPlugin p, TournamentAPI api) throws Exception {
        plugin = p;
        this.api = api;

        Bukkit.getPluginManager().registerEvents(new TournamentListener(api), plugin);

        // Must be in order!
        reg(Database.class);
        reg(Settings.class);
        reg(Commands.class);
    }


    public void enableModules() throws Exception {
        for (Module m : modules) {
            m.enable(plugin);
        }
    }

    public void disableModules() throws Exception {
        //for (Module m : modules) {
        //m.teminate(plugin);
        // }
    }

    private void reg(Class<? extends Module> mclass) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        mclass.newInstance();
    }

    public static ArrayList<Module> getModules() {
        return modules;
    }

    public static Module getModule(String name) {
        for (Module m : modules)
            if (m.getName().equalsIgnoreCase(name)) return m;
        return null;
    }

    public static boolean useBarAPI() {
        Plugin BarAPI = plugin.getServer().getPluginManager().getPlugin("BarAPI");
        return (BarAPI != null);
    }

    public static void add(Module m) {
        modules.add(m);
    }

    public static void remove(Module m) {
        modules.remove(m);
    }
}
