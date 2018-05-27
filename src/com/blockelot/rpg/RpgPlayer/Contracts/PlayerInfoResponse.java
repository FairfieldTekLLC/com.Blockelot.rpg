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
    private int HpMax;
    private int Exp;
    private int Lvl;
    private int ExpToLevel;

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

    public int getHpMax() {
        return HpMax;

    }

    public void setHpMax(int h) {
        HpMax = h;
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
