package com.xenojava.tournament.commands;

import org.bukkit.ChatColor;

public class CommandException extends Exception {

    private String message = "&cAn error has occured!";

    public void setMessage(String message){
        this.message = message;
    }

    public String getMessage(){
        return ChatColor.translateAlternateColorCodes('&', message);
    }

}
