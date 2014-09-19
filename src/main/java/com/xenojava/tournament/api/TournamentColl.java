package com.xenojava.tournament.api;

import com.xenojava.tournament.Tournament;
import com.xenojava.tournament.enums.ServerState;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import static com.xenojava.tournament.enums.ServerState.LOBBY;

public class TournamentColl implements TournamentAPI {

    private Tournament tournament;
    private ServerState state = LOBBY;


    public TournamentColl(Tournament tournament) {
        this.tournament = tournament;
    }

    public void startTournament() {
        this.setState(ServerState.CONCURRENT);
    }

    public void endTournament() {
        this.setState(ServerState.LOBBY);
    }

    public void spectate(Player player) {

    }

    public boolean hasStarted() {
        return (getState().equals(ServerState.CONCURRENT));
    }

    public void setState(ServerState state) {
        this.state = state;
    }

    public ServerState getState() {
        return state;
    }

    public JavaPlugin getPlugin() {
        return tournament.getPlugin();
    }
}
