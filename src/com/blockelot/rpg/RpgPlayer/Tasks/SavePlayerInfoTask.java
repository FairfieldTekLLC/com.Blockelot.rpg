/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blockelot.rpg.RpgPlayer.Tasks;

import com.blockelot.rpg.Plugin;
import com.blockelot.rpg.RpgPlayer.Classes.PlayerInfo;
import com.blockelot.rpg.RpgPlayer.Contracts.PlayerInfoResponse;
import com.blockelot.rpg.RpgPlayer.Contracts.PlayerInfoSaveResponse;
import com.blockelot.rpg.RpgPlayer.Util.Http;
import com.google.gson.Gson;

/**
 *
 * @author geev
 */
public class SavePlayerInfoTask implements Runnable {

    PlayerInfo PlayerInfo = null;
//    private int CurrentHp;
    PlayerInfoResponse Req;

    public SavePlayerInfoTask(PlayerInfoResponse req,PlayerInfo pi) {
        Req=req;
        PlayerInfo = pi;
//        CurrentHp = currentHp;
    }

    @Override
    public void run() {
        try {
            Gson gson = new Gson();
//            PlayerInfoResponse req = new PlayerInfoResponse();
//            req.setExp(PlayerInfo.getPlayerStats().getExp());
//            req.setHpCur(CurrentHp);
//            req.setId(PlayerInfo.getId());
//            req.setLvl(PlayerInfo.getPlayerStats().getLevel());
//            req.setUuid(PlayerInfo.getUuid());
            String body = gson.toJson(Req);
            PlayerInfoSaveResponse response = gson.fromJson(Http.RequestHttp(Plugin.BaseUri + "PlayerSave", body), PlayerInfoSaveResponse.class);
            PlayerInfo.setLastSaveSuccess(response.getSuccess());
            PlayerInfo.getPlayerStats().setExpToLevel(response.getExpToLevel());
            Plugin.print("Player: " + PlayerInfo.getUuid() + " saved.");
        } catch (Exception e) {
            Plugin.print("Player: " + PlayerInfo.getUuid() + " failed to saved.");
        }
        PlayerInfo.setSavingAsync(false);

    }

}
