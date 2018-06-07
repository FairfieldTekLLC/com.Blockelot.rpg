/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blockelot.rpg.RpgPlayer.Classes;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
/**
 *
 * @author geev
 */
public class PlayerStats {

    private static int StartingHP = 20;

    PlayerInfo PlayerInfo;
    public Stats Current = new Stats();
    public Stats Base = new Stats();
    private int Level;
    private int ClassId;
    private String ClassName;
    private int Exp;
    private int ExpToLevel;

    public int getExpToLevel() {
        return ExpToLevel;
    }

    public void setExpToLevel(int i) {
        ExpToLevel = i;
    }

    public int getHpCurrent() {
        return (int) PlayerInfo.getPlayer().getHealth();
    }

    public void setHpCurrent(int hp) {
        PlayerInfo.getPlayer().setHealth(hp);
    }

    public void addExp(int add) {
        Exp += add;
        if (Exp > ExpToLevel) {
            Exp = Exp - ExpToLevel;
            setLevel(getLevel() + 1);
            PlayerInfo.getPlayer().playSound(PlayerInfo.getPlayer().getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 0.5F, 1.0F);
            PlayerInfo.getPlayer().sendMessage("       ➠   " + ChatColor.BOLD + ChatColor.YELLOW + "Welcome to Level " + getLevel());
            PlayerInfo.SaveToDisk();
        }
    }

    public PlayerStats(PlayerInfo pi) {
        PlayerInfo = pi;
    }

    public int getExp() {
        return Exp;
    }

    public void setExp(int i) {
        Exp = i;
    }

    public int getLevel() {
        return Level;
    }

    public void Update() {
        Current.setAc(Base.getAc());
        Current.setCha(Base.getAc());
        Current.setDex(Base.getDex());
        Current.setMaxHp(Base.getMaxHp());
        Current.setSta(Base.getSta());
        Current.setStr(Base.getStr());
        Current.setWis(Base.getWis());
        
        PlayerInfo.getPlayer().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(Current.getMaxHp());
        
        //If the new HP is less that the current health, adjust it.
        if (PlayerInfo.getPlayer().getHealth()>Current.getMaxHp()){
            PlayerInfo.getPlayer().setHealth(Current.getMaxHp());
        }
    }

    public void setLevel(int i) {
        Level = i;
        switch (ClassId) {
            case 1:
                double stamina = Base.getSta();
                double hpToAddToMax = (stamina * (Level -1)) / 10.0;
                int newHpMax = StartingHP + (int) Math.ceil(hpToAddToMax);
                Base.setMaxHp(newHpMax);
                Update();
                break;
            case 2:
                break;
            case 3:
                break;
            default:
                break;
        }
    }

    public int getClassId() {
        return ClassId;
    }

    public void setClassId(int i) {
        ClassId = i;
    }

    public void setClassName(String name) {
        ClassName = name;
    }

    public String getClassName() {
        return ClassName;
    }

    public void LoadBase(int str, int sta, int dex, int wis, int cha, int ac) {
        Base.setStr(str);
        Base.setSta(sta);
        Base.setDex(dex);
        Base.setWis(wis);
        Base.setCha(cha);
        Base.setAc(ac);
    }
}
