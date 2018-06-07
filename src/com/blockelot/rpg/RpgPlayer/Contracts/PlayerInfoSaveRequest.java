/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blockelot.rpg.RpgPlayer.Contracts;

/**
 *
 * @author geev
 */
public class PlayerInfoSaveRequest {


    private int Id;
    private String Uuid;
    private int HpCur;
    private int Exp;
    private int Lvl;
    private String InvCont;
    private String InvArm;
    private String InvEnd;
    private String LastWorld;
    
    public String getLastWorld(){
        return LastWorld;
    }
    public void setLastWorld(String worldname)
    {
        LastWorld = worldname;
    }  
    
    public String getInvEnd(){
        return InvEnd;
    }
    public void setInvEnd(String s){
        InvEnd = s;
    }
    
    public String getInvCont(){
        return InvCont;
    }
    public void setInvCont(String json){
        InvCont=json;
    }
    
    public String getInvArm(){
        return InvArm;
    }
    public void setInvArm(String json){
        InvArm= json;
    }
   
    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String GetUuid() {
        return Uuid;
    }

    public void setUuid(String uuid) {
        Uuid = uuid;
    }

    public int getHpCur() {
        return HpCur;
    }

    public void setHpCur(int hp) {
        HpCur = hp;
    }


    public int getExp() {
        return Exp;
    }

    public void setExp(int exp) {
        Exp = exp;
    }

    public int getLvl() {
        return Lvl;
    }

    public void setLvl(int l) {
        Lvl = l;
    }
}
