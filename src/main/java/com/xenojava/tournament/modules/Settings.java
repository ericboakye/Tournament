package com.xenojava.tournament.modules;

import com.xenojava.tournament.Manager;
import com.xenojava.tournament.Module;
import com.xenojava.tournament.Setting;
import com.xenojava.tournament.utils.Json;
import com.xenojava.tournament.utils.Log;
import org.bukkit.plugin.java.JavaPlugin;

public class Settings extends Module {

    /**
     * The settings class will handle all configured settings
     * in the settings.json configuration.
     */

    private static Json settings;

    // Manifest
    public Settings() {
        super("Settings", "v1.1");
        settings = new Json(((Database)Manager.getModule("Database")).getSettingsJSON());
    }

    public void setup(JavaPlugin plugin) {
        runSettings();
    }


    public void end(JavaPlugin plugin) {
    }

    public static void saveSettings() {
        settings.save();
    }

    public static Json get() {
        return settings;
    }

    private void runSettings() {
        Log.info("Running tournament settings ...");
        for (Setting setting : Setting.values())
            if (settings.get(setting.getPath()) != null)
              setting.setValue(settings.get(setting.getPath()));
            else {
                settings.set(setting.getPath(), setting.getDefault());
                setting.setValue(setting.getDefault());
            }
        saveSettings();
        Log.debug("Tournament settings have been loaded! Ready to rock and roll.");
    }
}
