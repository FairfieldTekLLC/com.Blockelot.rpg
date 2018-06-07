///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.blockelot.rpg.RpgPlayer.Util;
//
//import com.blockelot.rpg.Plugin;
//import com.google.gson.Gson;
//import com.blockelot.rpg.RpgPlayer.Contracts.MobExp;
//import com.blockelot.rpg.RpgPlayer.Contracts.MobExpRequest;
//import com.blockelot.rpg.RpgPlayer.Contracts.MobExpResponse;
//import com.blockelot.rpg.RpgPlayer.RabbitMQ.MqRpcClient;
//import com.blockelot.rpg.RpgPlayer.RabbitMQ.RabbitMessagePayload;
//import com.rabbitmq.client.BuiltinExchangeType;
//import java.util.Arrays;
//
///**
// *
// * @author geev
// */
//public class DataLoader {
//
//    static Gson gson = new Gson();
//
//    static public MobExp[] LoadMobs() {
//
//        try {
//            MqRpcClient c = new MqRpcClient("192.168.211.63", "Minecraft", BuiltinExchangeType.DIRECT);
//            RabbitMessagePayload payload = c.call("Minecraft", "TestMessage",  new RabbitMessagePayload(new MobExpRequest()), 10);
//            MobExpResponse response = gson.fromJson(payload.getData(), MobExpResponse.class);
//            return response.getMobs();
//        } catch (Exception e) {
//            System.out.print(e.getMessage());
//            System.out.print(Arrays.toString(e.getStackTrace()));
//            return null;
//        }
//
//    }
//}
