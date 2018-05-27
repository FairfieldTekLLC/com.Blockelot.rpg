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
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

/**
 *
 * @author geev
 */
public class PlayerInfo {

    public Boolean LoadedSuccess = false;

    private final Player Player;
    private Boolean LastSaveSuccess = false;
    private int Id;
    private int HitPoint;
    private int HitPointMax;
    private int Experience;

    private int Level;
    private String Uuid;
    private boolean SavingAsync = false;
    private int ClassId;
    private String ClassName;
    private int ExpToLevel;
    public PlayerInfo(Player player) throws Exception {

        Player = player;
        Uuid = player.getUniqueId().toString();
        Plugin.print("SetHeathScaled:false");
        Player.setHealthScaled(false);
        Plugin.print("Loading Info.");
        PlayerInfoResponse response = null;
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
        setHitPointMax(response.getHpMax());
        setHitPoint(response.getHpCur());
        setExp(response.getExp());
        setLevel(response.getLvl());
        setId(response.getId());
        setClassId(response.getClassId());
        setClassName(response.getClassName());
        setExpToLevel(response.getExpToLevel());
        LoadedSuccess = true;
        
    }

    public int getExpToLevel() {
        return ExpToLevel;
    }

    public final void setExpToLevel(int i) {
        ExpToLevel = i;
    }

    public int getClassId() {
        return ClassId;
    }

    public final void setClassId(int i) {
        ClassId = i;
    }

    public String getClassName() {
        return ClassName;
    }

    public final void setClassName(String name) {
        ClassName = name;
    }


    public void setLastSaveSuccess(Boolean b) {
        Plugin.print("Last Save: " + b);
        LastSaveSuccess = b;
    }

    public void SendExpBar() {
        double per = (double) getExp() / (double) getExpToLevel();

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
            setHitPoint((int) Player.getHealth());
            SavePlayerInfoTask task = new SavePlayerInfoTask(this);
            Bukkit.getScheduler().runTaskAsynchronously(Plugin.getInstance(), task);
        }
    }

    public int getLevel() {
        return Level;
    }

    public int getId() {
        return Id;
    }

    public final void setId(int id) {
        Plugin.print("Setting ID: " + id);
        Id = id;
    }

    public final void setLevel(int level) {
        Plugin.print("Setting Level: " + level);
        Level = level;
    }

    public void addHitPointMax(int add) {
        HitPointMax += add;
        Player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(HitPointMax);
    }

    public final void setHitPointMax(int hp) {
        Plugin.print("Setting Max HP: " + hp);
        HitPointMax = hp;
        Player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(HitPointMax);
    }

    public int getHitPointMax() {
        return HitPointMax;
    }

    public int getHitPoint() {
        return HitPoint;
    }

    public boolean getSavingAsync() {
        return SavingAsync;
    }

    public void setSavingAsync(Boolean b) {
        Plugin.print("Setting Saving Async(" + b + ")");
        SavingAsync = b;
    }

    public final void setHitPoint(int hp) {
        Plugin.print("Setting Current HP: " + hp);
        HitPoint = hp;
        Player.setHealth(HitPoint);
    }

    public int getExp() {
        return Experience;
    }

    public final void setExp(int xp) {
        Plugin.print("Setting Exp: " + xp);
        Experience = xp;
    }

    public void addExp(int add) {
        Experience += add;
        if (Experience > ExpToLevel) {
            Experience = Experience - ExpToLevel;
            setLevel(getLevel() + 1);
            Player.playSound(Player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 0.5F, 1.0F);
            Player.sendMessage("       ➠   " + ChatColor.BOLD + ChatColor.YELLOW + "Welcome to Level " + getLevel());
            this.SaveToDisk();
        }
    }

    public void onMobKill(LivingEntity entity) {
        int expGained = Plugin.MobList.get(entity.getType().toString());
        if (expGained == 0) {
            return;
        }
        addExp(expGained);
        if (Plugin.useHolographicDisplays) {
            Graphics.showHologram(entity.getLocation().add(0, entity.getEyeHeight(), 0),""+ ChatColor.RED + "+ " + expGained + " XP " + ChatColor.GRAY + "[" + getExp() + "/" + getExpToLevel() + "]");
        }

    }
}
