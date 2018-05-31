/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blockelot.rpg.RpgPlayer.Classes;

/**
 *
 * @author geev
 */
public class Stats {
      
    private int Str;
    private int Sta;
    private int Dex;
    private int Wis;
    private int Cha;
    private int Ac;
    private int MaxHp;
    
    
    public int getMaxHp(){
        return MaxHp;
    }
    public void setMaxHp(int i){
        MaxHp=i;
    }
    
    public int getStr(){
        return Str;
    }
    public void setStr(int i){
        Str=i;
    }
    
    public int getSta(){
        return Sta;
    }
    public void setSta(int i){
        Sta=i;
    }
    
    public int getDex(){
        return Dex;
    }
    
    public void setDex(int i){
        Dex=i;
    }
    public int getWis(){
        return Wis;
    }
    public void setWis(int i){
        Wis=i;
    }
    public int getCha(){
        return Cha;
    }
    public void setCha(int i){
        Cha = i;
    }
    public int getAc(){
        return Ac;
    }
    public void setAc(int i){
        Ac = i;
    }
    
    
}
