package com.xenojava.tournament;

import com.xenojava.tournament.enums.UserMode;

import java.util.UUID;

public class User {

    private UUID uuid;
    private int points;

    private UserMode mode;

    public User(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUUID() {
        return uuid;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setMode(UserMode mode) {
        this.mode = mode;
    }

    public boolean isObserving() {
        return (mode.equals(UserMode.OBSERVER));
    }

    public int getPoints() {
        return points;
    }

}
