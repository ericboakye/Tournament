package com.xenojava.tournament.bukkit;

import com.xenojava.tournament.Tournament;
import com.xenojava.tournament.api.IBase;
import com.xenojava.tournament.tasks.TickApplication;
import com.xenojava.tournament.utils.Log;
import org.bukkit.plugin.java.JavaPlugin;

public class TournamentPlugin extends JavaPlugin implements IBase {

    private Tournament controller;
    private TickApplication application;

    public void onEnable() {
        try {
            this.controller = new Tournament(this);
            this.application = new TickApplication(Tournament.getAPI());
        } catch (Exception e) {
            Log.severe("Enable exception. Please check source code to solve this problem.");
            e.printStackTrace();
        }
    }

    public void onDisable() {
        try {
            controller.onDisable();
            application.cancel();
        } catch (Exception e) {
            Log.severe("Disable exception. Please check source code to solve this problem.");
            e.printStackTrace();
        }
    }

    public JavaPlugin getPlugin() {
        return this;
    }

    public Tournament getTournament() {
        return this.controller;
    }

}
