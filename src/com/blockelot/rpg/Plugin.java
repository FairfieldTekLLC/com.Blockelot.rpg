/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blockelot.rpg;

import com.blockelot.rpg.RpgPlayer.Classes.PlayerInfo;
import com.blockelot.rpg.RpgPlayer.Commands.Commands;
import com.blockelot.rpg.RpgPlayer.Contracts.*;
import com.blockelot.rpg.RpgPlayer.Listeners.CreatureListener;
import com.blockelot.rpg.RpgPlayer.Listeners.EntityListener;
import com.blockelot.rpg.RpgPlayer.Listeners.PlayerListener;
import com.blockelot.rpg.RpgPlayer.Util.DataLoader;
import java.util.HashMap;
import java.util.concurrent.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import com.blockelot.rpg.RpgPlayer.CustomEvents.ArmorEquip.ArmorListener;
import com.blockelot.rpg.RpgPlayer.RabbitMQ.MqRpcClient;
import com.blockelot.rpg.RpgPlayer.RabbitMQ.RabbitMessagePayload;
import com.rabbitmq.client.BuiltinExchangeType;
import com.blockelot.rpg.RpgPlayer.Contracts.*;
import java.util.UUID;

/**
 *
 * @author geev
 */
public class Plugin extends JavaPlugin implements Listener {

    public static BlockingQueue ConsoleQueue = new ArrayBlockingQueue(1024);

    public static String BaseUri = "http://mc.fairfieldtek.com/api/Player/v1/";
    //public static String BaseUri = "http://localhost:31312/api/Player/v1/";

    private static Plugin plugin;

    public static Boolean Running = false;

    public static HashMap<Player, PlayerInfo> PlayerInfoList = new HashMap<>();

    public static boolean useHolographicDisplays;

    private static final String BuildVersion = "v1_12_R1";

    static final String RunningVersion = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];

    public static HashMap<String, Integer> MobList = new HashMap<>();

    public static void print(String text) {
        try {
            ConsoleQueue.put(text);
        } catch (Exception e) {
        }
    }

    public static void print(String text, ChatColor color) {
        try {
            ConsoleQueue.put(color + text);
        } catch (Exception e) {
        }
    }

    public static Plugin getInstance() {
        return plugin;
    }

    @Override
    public void onDisable() {
        Running = false;
    }

    @Override
    public void onEnable() {

        try {
            MqRpcClient c = new MqRpcClient("192.168.211.63", "Minecraft", BuiltinExchangeType.DIRECT);
            PlayerInfoRequest pir = new PlayerInfoRequest();
            pir.setUuid(UUID.randomUUID().toString());
            RabbitMessagePayload msg = new RabbitMessagePayload(pir);
            msg.setType("PlayerInfoRequest");
            RabbitMessagePayload response = c.call("Minecraft", "TestMessage", msg, 10);
            System.out.print("Response: " + response.getData());
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }

        print(" ____  _            _        _       _     _____  _____   _____ ", ChatColor.BLUE);
        print("|  _ \\| |          | |      | |     | |   |  __ \\|  __ \\ / ____|", ChatColor.BLUE);
        print("| |_) | | ___   ___| | _____| | ___ | |_  | |__) | |__) | |  __ ", ChatColor.BLUE);
        print("|  _ <| |/ _ \\ / __| |/ / _ \\ |/ _ \\| __| |  _  /|  ___/| | |_ |", ChatColor.BLUE);
        print("| |_) | | (_) | (__|   <  __/ | (_) | |_  | | \\ \\| |    | |__| |", ChatColor.BLUE);
        print("|____/|_|\\___/ \\___|_|\\_\\___|_|\\___/ \\__| |_|  \\_\\_|     \\_____|", ChatColor.BLUE);

        print(ChatColor.YELLOW + "Built for version (" + ChatColor.RED + BuildVersion + ChatColor.YELLOW + ") Running version is (" + ChatColor.RED + RunningVersion + ChatColor.YELLOW + ")", ChatColor.YELLOW);

        if (BuildVersion.equals(RunningVersion)) {

            MobExp[] m = DataLoader.LoadMobs();

            for (MobExp m1 : m) {
                MobList.put(m1.getName(), m1.getExp());
            }

            Running = true;

            new ConsolePrintTask().runTaskTimer((org.bukkit.plugin.Plugin) this, 1, 15);

            plugin = this;

            getCommand("rpg").setExecutor(new Commands());
            // getCommand("hp").setExecutor(new Statusbar());

            getServer().getPluginManager().registerEvents(this, this);

            this.getServer().getPluginManager().registerEvents((Listener) new PlayerListener(), (org.bukkit.plugin.Plugin) this);
            this.getServer().getPluginManager().registerEvents((Listener) new EntityListener(), (org.bukkit.plugin.Plugin) this);
            this.getServer().getPluginManager().registerEvents((Listener) new CreatureListener(), (org.bukkit.plugin.Plugin) this);
            this.getServer().getPluginManager().registerEvents((Listener) new ArmorListener(), (org.bukkit.plugin.Plugin) this);

            useHolographicDisplays = Bukkit.getPluginManager().isPluginEnabled("HolographicDisplays");

            if (useHolographicDisplays) {
                print("HolographicDisplays Loaded!", ChatColor.GREEN);
            } else {
                print("HolographicDisplays not loaded!", ChatColor.RED);
            }

            //  cfgReload();
            saveConfig();

        } else {
            print("Blockelot.RPG NOT LOADED!!!");
        }
    }
}
