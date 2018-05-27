/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blockelot.rpg;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.scheduler.BukkitRunnable;

/**
 *
 * @author geev
 */
public class ConsolePrintTask extends BukkitRunnable {

    private static ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();

    @Override
    public void run() {
        try {
            while (!Plugin.ConsoleQueue.isEmpty()) {
                String msg = (String) Plugin.ConsoleQueue.take();
                console.sendMessage(ChatColor.YELLOW + msg);
            }
            if (!Plugin.Running) {
                this.cancel();
            }
        } catch (Exception e) {

        }
    }

}
