/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blockelot.rpg.RpgPlayer.Commands;

import com.blockelot.rpg.Plugin;
import com.blockelot.rpg.RpgPlayer.Util.Graphics;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import me.Bleuzen.RPGHealthPlus.Main;
import org.bukkit.Bukkit;

/**
 *
 * @author geev
 */
public class Commands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender cs, Command cmnd, String string, String[] args) {
        if (string.equalsIgnoreCase("rpg")) {
            
            //Shows the users current Exp and Exp needed to level.
            if (args.length == 1 && args[0].equalsIgnoreCase("exp")) {
                if (cs instanceof Player) {
                    Plugin.PlayerInfoList.get((Player) cs).SendExpBar();
                    return true;
                }
            }
            //Shows the players stats.
            if (args.length == 1 && args[0].equalsIgnoreCase("stats")) {
                  if (cs instanceof Player) {
                      Plugin.PlayerInfoList.get((Player)cs).SendStats();
                      return true;
                  }
                  
            }            

            if (args[0].equalsIgnoreCase("reload")) {
                //Main.getInstance().waitSave();
                //Main.getInstance().saveplayers();
                //Main.getInstance().cfgReload();
            }

        }
        return true;
    }

}
