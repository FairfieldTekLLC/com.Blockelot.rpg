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
public class PlayerInfoResponse {

    private int ClassId;
    private String ClassName;
    private int Id;
    private String Uuid;
    private int HpCur;
    
    private int Exp;
    private int Lvl;
    private int ExpToLevel;
    private int Str;
    private int Sta;
    private int Dex;
    private int Wis;
    private int Cha;
    private int Ac;
    
    private String InvCont;
    private String InvArm;
    private String InvEnd;
    
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

    public int getExpToLevel() {
        return ExpToLevel;
    }

    public void setExpToLevel(int i) {
        ExpToLevel = i;
    }

    public int getClassId() {
        return ClassId;
    }

    public void setClassId(int i) {
        ClassId = i;
    }

    public String getClassName() {
        return ClassName;
    }

    public void setClassName(String name) {
        ClassName = name;
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
