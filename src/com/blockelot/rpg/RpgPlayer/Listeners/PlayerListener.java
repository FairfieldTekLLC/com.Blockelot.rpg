/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blockelot.rpg.RpgPlayer.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import com.blockelot.rpg.Plugin;
import com.blockelot.rpg.RpgPlayer.Classes.PlayerInfo;
import org.bukkit.ChatColor;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;

/**
 *
 * @author geev
 */
public class PlayerListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void World(PlayerChangedWorldEvent e) {
    }

    @EventHandler
    public void Death(PlayerDeathEvent e) {
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        org.bukkit.entity.Player player = event.getPlayer();
        if (!Plugin.PlayerInfoList.containsKey(player)) {
            Plugin.print("Adding Player " + player.getUniqueId().toString() + " - joined.  ", ChatColor.YELLOW);

            PlayerInfo p = null;
            try {
                p = new PlayerInfo(player);
            } catch (Exception err) {
                player.kickPlayer("Authorization Error.");
            }
            if (!p.LoadedSuccess) {
                player.kickPlayer("Authorization Error.");
            }
            Plugin.PlayerInfoList.put(player, p);

        }
    }

    @EventHandler
    public void onPlayerQuitEvent(PlayerQuitEvent event) {
        org.bukkit.entity.Player player = event.getPlayer();
        if (Plugin.PlayerInfoList.containsKey(player)) {
            Plugin.PlayerInfoList.get(player).SaveToDisk();
            Plugin.print("Removing Player " + player.getUniqueId().toString() + " - left.  ", ChatColor.YELLOW);
            Plugin.PlayerInfoList.remove(player);
        }
    }
}
