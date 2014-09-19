package com.xenojava.tournament.api;

import com.xenojava.tournament.tasks.ServerState;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public interface TournamentAPI {

    /**
     * Begins official tournament
     * 100% Synchronizes with tick application
     */
    public void startTournament();

    /**
     * Ends/Cancels official tournament
     * Puts server in back to lobby mode
     * 100% Synchronizes with tick application
     */
    public void endTournament();

    /**
     * Sets a user in spectate mode.
     *
     * @param player Choosen player.
     */

    @Deprecated
    public void spectate(Player player);

    /**
     * Sets the state of server also has an event
     * object called ServerChangeStateEvent
     *
     * @param state Server State.
     */
    public void setState(ServerState state);

    /**
     * @return Returns server state.
     */
    public ServerState getState();

    /**
     * @return Returns plugin instance passed
     * on from IBase.
     */
    public JavaPlugin getPlugin();
}
