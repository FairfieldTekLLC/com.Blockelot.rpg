/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blockelot.rpg.RpgPlayer.Util;

import com.blockelot.rpg.Plugin;
import com.google.gson.Gson;
import com.blockelot.rpg.RpgPlayer.Contracts.MobExp;
import com.blockelot.rpg.RpgPlayer.Contracts.MobExpResponse;

/**
 *
 * @author geev
 */
public class DataLoader {

    static public MobExp[] LoadMobs() {
        Gson gson = new Gson();
        MobExpResponse response = gson.fromJson(Http.RequestHttp(Plugin.BaseUri + "MobExp", ""), MobExpResponse.class);
        return response.getMobs();

    }
}
