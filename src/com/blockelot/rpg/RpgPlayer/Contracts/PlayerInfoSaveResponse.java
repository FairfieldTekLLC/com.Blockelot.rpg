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
public class PlayerInfoSaveResponse {

    private Boolean Success;
    private int ExpToLevel;

    public int getExpToLevel() {
        return ExpToLevel;
    }

    public void setExpToLevel(int i) {
        ExpToLevel = i;
    }

    public Boolean getSuccess() {
        return Success;
    }

    public void setSuccess(Boolean s) {
        Success = s;
    }
}
