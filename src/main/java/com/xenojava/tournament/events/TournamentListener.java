package com.xenojava.tournament.events;

import com.xenojava.tournament.api.TournamentAPI;
import com.xenojava.tournament.utils.Permission;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.PluginDescriptionFile;

public class TournamentListener implements Listener {

    private TournamentAPI api;

    public TournamentListener(TournamentAPI api) {
        this.api = api;
    }


    @EventHandler(priority = EventPriority.LOW)
    public void handleVerison(final PlayerJoinEvent event) {
        if (!event.getPlayer().hasPermission(Permission.ADMIN.getRealPermission())) {
            return;
        }

        PluginDescriptionFile pdf = api.getPlugin().getDescription();
        final String name = pdf.getName();
        final String version = pdf.getVersion();

        Bukkit.getScheduler().runTask(api.getPlugin(), new Runnable() {
            public void run() {
                event.getPlayer().sendMessage(ChatColor.RED + String.format("This server is running on %s verison %s", name, version));
            }
        });

    }
}
