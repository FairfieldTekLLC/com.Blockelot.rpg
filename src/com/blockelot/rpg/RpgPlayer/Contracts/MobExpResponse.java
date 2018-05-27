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
public class MobExpResponse {

    private MobExp[] Mobs;

    public MobExp[] getMobs() {
        return Mobs;
    }

    public void setMobs(MobExp[] mobs) {
        Mobs = mobs;
    }

}
