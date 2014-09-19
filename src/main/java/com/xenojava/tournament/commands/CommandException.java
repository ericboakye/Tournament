package com.xenojava.tournament.commands;

import org.bukkit.ChatColor;

public class CommandException extends Exception {

    private String message = "&cAn error has occured with HQGamingPlugin v1.0";

    public void setMessage(String message){
        this.message = message;
    }

    public String getMessage(){
        return ChatColor.translateAlternateColorCodes('&', message);
    }

}
