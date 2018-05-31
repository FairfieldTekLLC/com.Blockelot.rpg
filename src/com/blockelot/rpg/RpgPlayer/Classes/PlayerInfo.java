/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blockelot.rpg.RpgPlayer.Classes;

import com.blockelot.rpg.Plugin;
import com.blockelot.rpg.RpgPlayer.Contracts.*;
import com.blockelot.rpg.RpgPlayer.Tasks.*;
import com.blockelot.rpg.RpgPlayer.Util.Graphics;
import com.blockelot.rpg.RpgPlayer.Util.Http;
import com.google.gson.Gson;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import com.blockelot.rpg.RpgPlayer.Util.Inventory;
import org.bukkit.inventory.ItemStack;
import de.tr7zw.itemnbtapi.*;
import java.util.*;

/**
 *
 * @author geev
 */
public class PlayerInfo {
    
    public Boolean LoadedSuccess = false;
    
    private final Player Player;
    private Boolean LastSaveSuccess = false;
    private int Id;
    private String Uuid;
    private boolean SavingAsync = false;
    
    private PlayerStats PlayerStats;
    
    public PlayerInfo(Player player) throws Exception {
        
        PlayerStats = new PlayerStats(this);
        Player = player;
        Uuid = player.getUniqueId().toString();
        Plugin.print("SetHeathScaled:false");
        Player.setHealthScaled(false);
        Plugin.print("Loading Info.");
        PlayerInfoResponse response;
        try {
            
            Plugin.print("Creatng GSON");
            Gson gson = new Gson();
            
            Plugin.print("Creating PlayerInfoRequest");
            PlayerInfoRequest req = new PlayerInfoRequest();
            
            Plugin.print("Setting Uuid to: " + this.Uuid);
            req.setUuid(this.Uuid);
            
            Plugin.print("Setting Body");
            String body = gson.toJson(req);
            
            Plugin.print("Requesting from server");
            response = gson.fromJson(Http.RequestHttp(Plugin.BaseUri + "PlayerLoad", body), PlayerInfoResponse.class);
            
            if (response == null) {
                Plugin.print("Failed...");
                LoadedSuccess = false;
                return;
            }
        } catch (Exception e) {
            Plugin.print("Failed...");
            LoadedSuccess = false;
            return;
        }
        Plugin.print("Success...");
        
        setId(response.getId());
        PlayerStats.LoadBase(response.getStr(), response.getSta(), response.getDex(), response.getWis(), response.getCha(), response.getAc());
        
        PlayerStats.setExpToLevel(response.getExpToLevel());
        PlayerStats.setClassId(response.getClassId());
        PlayerStats.setClassName(response.getClassName());
        PlayerStats.setLevel(response.getLvl());
        PlayerStats.setExp(response.getExp());
        PlayerStats.setHpCurrent(response.getHpCur());
        Player.getInventory().clear();
        
        Plugin.print(response.getInvArm());
        
        ItemStack[] tst = com.blockelot.rpg.RpgPlayer.Util.Inventory.itemStackArrayFromBase64(response.getInvArm());
        Plugin.print("# of ITems " + tst.length);
        player.getInventory().setArmorContents(tst);
        
        try{
        Plugin.print(response.getInvCont());
        tst = com.blockelot.rpg.RpgPlayer.Util.Inventory.itemStackArrayFromBase64(response.getInvCont());
        Plugin.print("# of ITems " + tst.length);
        player.getInventory().setContents(tst);
        
        //player.getInventory().getContents();
        }
        catch (Exception e)
        {
            Plugin.print("Exception: "+ e.getMessage());
        }
        //player.getInventory().setArmorContents(com.blockelot.rpg.RpgPlayer.Util.Inventory.itemStackArrayFromBase64(response.getInvArm()));
        //player.getInventory().setContents(com.blockelot.rpg.RpgPlayer.Util.Inventory.itemStackArrayFromBase64(response.getInvCont()));
        
        PlayerStats.Update();
        LoadedSuccess = true;
        
    }
    
    public void SaveInventory() {
        
    }
    
    public PlayerStats getPlayerStats() {
        return PlayerStats;
    }
    
    public Player getPlayer() {
        return Player;
    }
    
    public void setLastSaveSuccess(Boolean b) {
        Plugin.print("Last Save: " + b);
        LastSaveSuccess = b;
    }
    
    public void SendStats() {
        Player.sendMessage("       ➠   " + ChatColor.BOLD + ChatColor.YELLOW + " Character Stats");
        Player.sendMessage("       ➠   " + ChatColor.BOLD + ChatColor.YELLOW + "=================");
        Player.sendMessage("       ➠   " + ChatColor.BOLD + ChatColor.YELLOW + "STR: " + PlayerStats.Base.getStr() + " (" + PlayerStats.Current.getStr() + ")");
        Player.sendMessage("       ➠   " + ChatColor.BOLD + ChatColor.YELLOW + "STA: " + PlayerStats.Base.getSta() + " (" + PlayerStats.Current.getSta() + ")");
        Player.sendMessage("       ➠   " + ChatColor.BOLD + ChatColor.YELLOW + "DEX: " + PlayerStats.Base.getDex() + " (" + PlayerStats.Current.getDex() + ")");
        Player.sendMessage("       ➠   " + ChatColor.BOLD + ChatColor.YELLOW + "WIS: " + PlayerStats.Base.getWis() + " (" + PlayerStats.Current.getWis() + ")");
        Player.sendMessage("       ➠   " + ChatColor.BOLD + ChatColor.YELLOW + "CHA: " + PlayerStats.Base.getCha() + " (" + PlayerStats.Current.getCha() + ")");
        Player.sendMessage("       ➠   " + ChatColor.BOLD + ChatColor.YELLOW + "AC: " + PlayerStats.Base.getAc() + " (" + PlayerStats.Current.getAc() + ")");
        Player.sendMessage("       ➠   " + ChatColor.BOLD + ChatColor.YELLOW + "HP: " + (int) Player.getHealth() + " (" + PlayerStats.Current.getMaxHp() + ")");
        
    }
    
    public void SendExpBar() {
        double per = (double) PlayerStats.getExp() / (double) PlayerStats.getExpToLevel();
        
        Plugin.print("Percent: " + per);
        
        StringBuilder xpBar = new StringBuilder();
        xpBar.append(ChatColor.RED).append("").append(ChatColor.BOLD);
        for (int i = 0; i < per * 40; i++) {
            xpBar.append("▍");
        }
        
        xpBar.append(ChatColor.GRAY).append("").append(ChatColor.BOLD);
        for (int i = (int) Math.ceil(per * 40); i < 40; i++) {
            xpBar.append("▍");
        }
        Player.sendMessage("       ➠   " + ChatColor.BOLD + ChatColor.YELLOW + " Experience Bar");
        Player.sendMessage("       ➠   " + ChatColor.BOLD + ChatColor.YELLOW + "===============");
        Player.sendMessage(xpBar.toString());
        
    }
    
    public void SendExpMessage(Location loc, double gained) {
        Graphics.showHologram(loc, ChatColor.RED + "+" + gained + " Experience.");
    }
    
    public Boolean getLastSaveSuccess() {
        return LastSaveSuccess;
    }
    
    public String getUuid() {
        return Uuid;
    }
    
    public final void SaveToDisk() {
        
        if (!getSavingAsync()) {
            Plugin.print("Saving Player...");
            setSavingAsync(true);
            PlayerInfoResponse req = new PlayerInfoResponse();
            req.setExp(getPlayerStats().getExp());
            req.setHpCur((int) Player.getHealth());
            req.setId(getId());
            req.setLvl(getPlayerStats().getLevel());
            req.setUuid(getUuid());
            
            String[] tmp = com.blockelot.rpg.RpgPlayer.Util.Inventory.playerInventoryToBase64(getPlayer().getInventory());
            
            req.setInvCont(tmp[0]);
            req.setInvArm(tmp[1]);
            
            SavePlayerInfoTask task = new SavePlayerInfoTask(req, this);
            Bukkit.getScheduler().runTaskAsynchronously(Plugin.getInstance(), task);
        }
    }
    
    public int getId() {
        return Id;
    }
    
    public final void setId(int id) {
        Plugin.print("Setting ID: " + id);
        Id = id;
    }
    
    public boolean getSavingAsync() {
        return SavingAsync;
    }
    
    public void setSavingAsync(Boolean b) {
        Plugin.print("Setting Saving Async(" + b + ")");
        SavingAsync = b;
    }
    
    public void onMobKill(LivingEntity entity) {
        int expGained = Plugin.MobList.get(entity.getType().toString());
        if (expGained == 0) {
            return;
        }
        PlayerStats.addExp(expGained);
        if (Plugin.useHolographicDisplays) {
            Graphics.showHologram(entity.getLocation()
                    .add(0, entity.getEyeHeight(), 0),
                    "" + ChatColor.RED
                    + "+ " + expGained
                    + " XP " + ChatColor.GRAY
                    + "[" + PlayerStats.getExp()
                    + "/" + PlayerStats.getExpToLevel() + "]");
        }
        
    }
}
