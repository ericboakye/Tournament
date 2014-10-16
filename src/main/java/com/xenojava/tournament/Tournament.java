package com.xenojava.tournament;


import com.xenojava.tournament.api.IBase;
import com.xenojava.tournament.api.TournamentAPI;
import com.xenojava.tournament.api.TournamentAbstract;
import com.xenojava.tournament.enums.ServerState;
import com.xenojava.tournament.tasks.TimeApplication;
import org.bukkit.plugin.java.JavaPlugin;

import static com.xenojava.tournament.enums.ServerState.LOBBY;

public class Tournament {


    /**
     * ** Tournament Project **
     * A project developed for and only Triangle. This project
     * was all made possible by the plugin developer and requester.
     *
     * @author Xeno
     */

    private static JavaPlugin plugin;
    private static Tournament instance;
    private static TournamentAbstract call;

    public ServerState state = LOBBY;
    private TimeApplication time;
    private Manager manager;

    public Tournament(IBase pinterface) throws Exception {
        instance = this;

        plugin = pinterface.getPlugin();
        call = new TournamentAbstract(this);

        time = new TimeApplication(call);
        manager = new Manager(plugin, call);
        manager.enableModules();
    }

    public TimeApplication getTimeApplication() {
        return time;
    }

    public void onDisable() throws Exception {
        manager.disableModules();
    }

    public JavaPlugin getPlugin() {
        return plugin;
    }

    public static Tournament getInstance() {
        return instance;
    }

    public static TournamentAPI getAPI() {
        return call;
    }

}
