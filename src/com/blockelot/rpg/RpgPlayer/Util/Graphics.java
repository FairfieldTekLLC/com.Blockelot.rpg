/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blockelot.rpg.RpgPlayer.Util;

import com.blockelot.rpg.Plugin;
import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;

import me.Bleuzen.RPGHealthPlus.Main;
import net.minecraft.server.v1_12_R1.EnumParticle;
import net.minecraft.server.v1_12_R1.PacketPlayOutWorldParticles;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

/**
 *
 * @author geev
 */
public class Graphics {

    public static void showParticles(Player p) {
        for (Player pa : Bukkit.getOnlinePlayers()) {
            ((CraftPlayer) pa).getHandle().playerConnection.sendPacket(new PacketPlayOutWorldParticles(
                    EnumParticle.HEART, true, (float) p.getLocation().getX(), (float) p.getLocation().getY() + 1.0F,
                    (float) p.getLocation().getZ(), 1.0F, 1.0F, 1.0F, 1.0F, 10, null));
        }
    }

    public static void showHologram(Location loc, String txt) {
        final Hologram holo = HologramsAPI.createHologram(Plugin.getInstance(), loc);
        holo.appendTextLine(txt);
        Bukkit.getScheduler().scheduleSyncDelayedTask(Plugin.getInstance(), new Runnable() {
            @Override
            public void run() {
                holo.delete();
            }
        }, 40);
    }
}
