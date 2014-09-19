package com.xenojava.tournament.tasks;

import com.xenojava.tournament.Setting;
import com.xenojava.tournament.api.TournamentAPI;
import com.xenojava.tournament.enums.ServerState;
import com.xenojava.tournament.utils.Log;
import org.bukkit.Bukkit;

public class TickApplication implements Runnable {

    private final TournamentAPI api;
    private final int task;

    public TickApplication(TournamentAPI api) {
        this.api = api;
        this.task = Bukkit.getScheduler().scheduleSyncRepeatingTask(api.getPlugin(), this, 20L, 20L);
        Log.debug("Starting tick application: " + this.task);
    }

    public void cancel() {
        Bukkit.getScheduler().cancelTask(this.task);
        Log.debug("Canceling tick application: " + this.task);
    }

    public void run() {
        if (api.getState().equals(ServerState.LOBBY) && Setting.AUTO_START.getBoolean())

            if (Bukkit.getOnlinePlayers().length >= Setting.REQUIRED_AMOUNT_START.getInteger()) {
                api.startTournament();
            }
    }
}
