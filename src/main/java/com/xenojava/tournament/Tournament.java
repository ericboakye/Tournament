package com.xenojava.tournament;


import com.xenojava.tournament.api.IBase;
import com.xenojava.tournament.api.TournamentAPI;
import com.xenojava.tournament.api.TournamentColl;
import org.bukkit.plugin.java.JavaPlugin;

public class Tournament {


    /**
     * ** Tournament Project **
     * <p/>
     * A project developed for and only Triangle. This project
     * was all made possible by the plugin developer and requester.
     *
     * @author Xeno
     */

    private static JavaPlugin plugin;
    private static Tournament instance;
    private static TournamentColl coll;

    private Manager manager;

    public Tournament(IBase pinterface) throws Exception {
        instance = this;

        plugin = pinterface.getPlugin();
        coll = new TournamentColl(this);

        manager = new Manager(plugin, coll);
        manager.enableModules();
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
        return coll;
    }

}
