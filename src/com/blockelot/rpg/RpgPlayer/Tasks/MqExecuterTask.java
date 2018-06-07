/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blockelot.rpg.RpgPlayer.Tasks;

import com.blockelot.rpg.RpgPlayer.RabbitMQ.RabbitExecuter;
import com.blockelot.rpg.RpgPlayer.RabbitMQ.RabbitMessagePayload;

/**
 *
 * @author geev
 */
public class MqExecuterTask extends RabbitExecuter implements Runnable {

    @Override
    public RabbitMessagePayload Execute(RabbitMessagePayload payload) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
