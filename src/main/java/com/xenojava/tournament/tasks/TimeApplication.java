package com.xenojava.tournament.tasks;

import com.xenojava.tournament.Setting;
import com.xenojava.tournament.api.TournamentAPI;
import com.xenojava.tournament.utils.Log;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;

public class TimeApplication implements Runnable {

    private final TournamentAPI api;
    private BukkitTask task;

    private long millis;
    private long endTime;
    private long startTime;

    public TimeApplication(TournamentAPI api) {
        this.api = api;
        Log.debug("Starting time application: " + this.task);
    }

    public void startTimer(int seconds) {
        Log.debug("Starting time application timer ...");

        this.task = Bukkit.getScheduler().runTaskTimer(api.getPlugin(), this, 20L, 20L);
        this.millis = seconds * 1000;
        this.startTime = System.currentTimeMillis();
        this.endTime = (this.startTime + this.millis);
    }

    public boolean hasStarted() {
        return (task != null);
    }

    public void cancel() {
        task.cancel();
        Log.debug("Canceling time application: " + this.task);
    }

    public void run() {

    }
}
