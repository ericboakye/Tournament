package com.xenojava.tournament.modules;

import com.xenojava.tournament.Module;
import com.xenojava.tournament.Setting;
import com.xenojava.tournament.utils.Json;
import com.xenojava.tournament.utils.Log;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class Database extends Module {


    /**
     * This is our database class. This will handle all data being saved
     * or used for later purposes. It will also be considered as a module.
     */

    // Manifest
    public Database() {
        super("Database", "v1.2.1");
    }


    private File work_folder = new File("tournament-beta");
    private File settingsJSON = new File(work_folder, "settings.json");

    public void setup(JavaPlugin plugin) {

        if (!this.getWorkFolder().exists()) if (this.getWorkFolder().mkdir())
            Log.info("Tournament data directory has been created ...");

        if (!settingsJSON.exists()) try {
            if (settingsJSON.createNewFile()) {
                Log.info("Tournament Settings json has been created and now being loaded ...");
            }
        } catch (IOException e) {
            Log.severe(ChatColor.RED + "Uh ohh... Could not create settings.json!");
        }
    }


    public File getSettingsJSON() {
        return settingsJSON;
    }

    public File getWorkFolder() {
        return work_folder;
    }

    public void end(JavaPlugin plugin) {
    }

}
