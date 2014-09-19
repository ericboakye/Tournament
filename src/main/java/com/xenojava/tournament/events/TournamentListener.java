package com.xenojava.tournament.events;

import com.xenojava.tournament.Tournament;
import com.xenojava.tournament.api.TournamentAPI;
import org.bukkit.event.Listener;

public class TournamentListener implements Listener {

    private TournamentAPI api;

    public TournamentListener(TournamentAPI api) {
        this.api = api;
    }
}
