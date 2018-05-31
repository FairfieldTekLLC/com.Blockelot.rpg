/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blockelot.rpg.RpgPlayer.Listeners;

import com.blockelot.rpg.Plugin;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;
import org.bukkit.inventory.meta.ItemMeta;
import java.util.*;

/**
 *
 * @author geev
 */
public class EntityListener implements Listener {

    @EventHandler
    public void onKill(EntityDeathEvent e) {
        if (e.getEntity().getKiller() instanceof Player) {
            Player p = (Player) e.getEntity().getKiller();
            if (p.getGameMode() == GameMode.SURVIVAL || p.getGameMode() == GameMode.ADVENTURE) {
                Plugin.PlayerInfoList.get(p).onMobKill(e.getEntity());
            }
            
            ItemStack item = new ItemStack(Material.CHAINMAIL_BOOTS, 1);
            
            ItemMeta testMeta = item.getItemMeta();
            
            List<String> testLore = new ArrayList<String>();
            testMeta.setDisplayName("Test Me");
            testLore.add("Line 1");
            testLore.add("Line 2");
            testLore.add("Line 3");
            testMeta .setLore(testLore);
            item.setItemMeta(testMeta);
            
            
            e.getDrops().add(item);
        }

    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e) {
    }

}
