/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blockelot.rpg.RpgPlayer.Tasks;

import com.blockelot.rpg.Plugin;
import com.blockelot.rpg.RpgPlayer.Classes.PlayerInfo;
import com.blockelot.rpg.RpgPlayer.Contracts.PlayerInfoSaveRequest;
import com.blockelot.rpg.RpgPlayer.Contracts.PlayerInfoSaveResponse;
import com.google.gson.Gson;

/**
 *
 * @author geev
 */
public class SavePlayerInfoTask implements Runnable {

    PlayerInfo PlayerInfo = null;
    PlayerInfoSaveRequest Req;

    public SavePlayerInfoTask(PlayerInfoSaveRequest req, PlayerInfo pi) {
        Req = req;
        PlayerInfo = pi;
    }

    @Override
    public void run() {
        try {
System.out.print("Saving Player.");
            PlayerInfoSaveResponse response = Plugin.MqClient.Call("Minecraft", "DataService", Req, 10, PlayerInfoSaveResponse.class);
            PlayerInfo.setLastSaveSuccess(response.getSuccess());
            PlayerInfo.getPlayerStats().setExpToLevel(response.getExpToLevel());
            Plugin.print("Player: " + PlayerInfo.getUuid() + " saved.");
        } catch (Exception e) {
            Plugin.print("Player: " + PlayerInfo.getUuid() + " failed to saved.");
        }
        PlayerInfo.setSavingAsync(false);
    }

}
