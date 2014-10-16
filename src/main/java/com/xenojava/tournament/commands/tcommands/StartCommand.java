package com.xenojava.tournament.commands.tcommands;

import com.xenojava.tournament.api.TournamentAPI;
import com.xenojava.tournament.commands.CommandException;
import com.xenojava.tournament.commands.InterfaceDependant;
import com.xenojava.tournament.commands.TCommand;
import com.xenojava.tournament.commands.AdminAccess;
import org.bukkit.command.CommandSender;

@InterfaceDependant
public class StartCommand extends TCommand implements AdminAccess {

    private TournamentAPI api;

    public StartCommand(TournamentAPI api) {
        super("start");
        this.api = api;
    }

    public void execute(CommandSender sender, String[] args) throws CommandException {

    }

    public String[] getAliases() {
        return new String[]{"begin"};
    }

    public String error() {
        return "You do not have permission to start the tournament!";
    }
}
