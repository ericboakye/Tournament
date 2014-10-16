package com.xenojava.tournament.api;

import com.xenojava.tournament.Tournament;
import com.xenojava.tournament.enums.ServerState;
import com.xenojava.tournament.tasks.TimeApplication;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import static com.xenojava.tournament.enums.ServerState.LOBBY;

public class TournamentAbstract implements TournamentAPI {

    private Tournament tournament;

    public TournamentAbstract(Tournament tournament) {
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

    public TimeApplication getTimeApplication() {
        return tournament.getTimeApplication();
    }

    public void setState(ServerState state) {
        tournament.state = state;
    }

    public ServerState getState() {
        return tournament.state;
    }

    public JavaPlugin getPlugin() {
        return tournament.getPlugin();
    }
}
