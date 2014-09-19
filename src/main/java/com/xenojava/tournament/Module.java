package com.xenojava.tournament;

import com.xenojava.tournament.utils.Log;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class Module {

    private String name = "Default";
    private String verison = "1.0";

    /**
     * Create a plugin module.
     * Made for plugin side objects.
     *
     * @param name Manifest name.
     * @param verison Manifest verison.
     */

    public Module(String name, String verison) {
        this.name = name;
        this.verison = verison;
        register();
    }

    private void register() {
        Manager.add(this);
    }

    private void unregister() {
        Manager.remove(this);
    }

    public String getName() {
        return name;
    }

    public String getVerison() {
        return verison;
    }


    /**
     * All abstract methods in the class must remain quarantined.
     */

    public abstract void setup(JavaPlugin plugin);

    public abstract void end(JavaPlugin plugin);


    /**
     * Enables module and executes the abstract methods.
     *
     * @param plugin Requires plugin instance.
     */
    public void enable(JavaPlugin plugin) {
        Log.info(String.format("%s %s module has been enabled! Setup will be auto-configured based on the settings. ", getName(), getVerison()));
        setup(plugin);
    }

    /**
     * Terminates module and all running processes in the class.
     * Also executes the abstract methods.
     *
     * @param plugin Requires plugin instance.
     */
    public void teminate(JavaPlugin plugin) {
        end(plugin);
        unregister();
    }


}
