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
public class PlayerWorldRequest {
    private String PlayerId;
    public String getPlayerId(){
        return PlayerId;
    }
    public void setPlayerId(String id){
        PlayerId = id;
    }
}
