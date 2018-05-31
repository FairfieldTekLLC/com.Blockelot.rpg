/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blockelot.rpg.RpgPlayer.CustomEvents.ArmorEquip;

import java.util.*;

/**
 *
 * @author geev
 */
public class ArmorBlockedMaterials {

    static private ArrayList<String> BlockedMaterials = null;

    static public String[] GetList() {
        if (BlockedMaterials == null) {
            BlockedMaterials = new ArrayList<>();
            Load();
        }
        return BlockedMaterials.toArray(new String[0]);
    }

    static private void Load() {
        BlockedMaterials.add("FURNACE");
        BlockedMaterials.add("CHEST");
        BlockedMaterials.add("TRAPPED_CHEST");
        BlockedMaterials.add("BEACON");
        BlockedMaterials.add("DISPENSER");
        BlockedMaterials.add("DROPPER");
        BlockedMaterials.add("HOPPER");
        BlockedMaterials.add("WORKBENCH");
        BlockedMaterials.add("ENCHANTMENT_TABLE");
        BlockedMaterials.add("ENDER_CHEST");
        BlockedMaterials.add("ANVIL");
        BlockedMaterials.add("BED_BLOCK");
        BlockedMaterials.add("FENCE_GATE");
        BlockedMaterials.add("SPRUCE_FENCE_GATE");
        BlockedMaterials.add("BIRCH_FENCE_GATE");
        BlockedMaterials.add("ACACIA_FENCE_GATE");
        BlockedMaterials.add("JUNGLE_FENCE_GATE");
        BlockedMaterials.add("DARK_OAK_FENCE_GATE");
        BlockedMaterials.add("IRON_DOOR_BLOCK");
        BlockedMaterials.add("WOODEN_DOOR");
        BlockedMaterials.add("SPRUCE_DOOR");
        BlockedMaterials.add("BIRCH_DOOR");
        BlockedMaterials.add("JUNGLE_DOOR");
        BlockedMaterials.add("ACACIA_DOOR");
        BlockedMaterials.add("DARK_OAK_DOOR");
        BlockedMaterials.add("WOOD_BUTTON");
        BlockedMaterials.add("STONE_BUTTON");
        BlockedMaterials.add("TRAP_DOOR");
        BlockedMaterials.add("IRON_TRAPDOOR");
        BlockedMaterials.add("DIODE_BLOCK_OFF");
        BlockedMaterials.add("DIODE_BLOCK_ON");
        BlockedMaterials.add("REDSTONE_COMPARATOR_OFF");
        BlockedMaterials.add("REDSTONE_COMPARATOR_ON");
        BlockedMaterials.add("FENCE");
        BlockedMaterials.add("SPRUCE_FENCE");
        BlockedMaterials.add("BIRCH_FENCE");
        BlockedMaterials.add("JUNGLE_FENCE");
        BlockedMaterials.add("DARK_OAK_FENCE");
        BlockedMaterials.add("ACACIA_FENCE");
        BlockedMaterials.add("NETHER_FENCE");
        BlockedMaterials.add("BREWING_STAND");
        BlockedMaterials.add("CAULDRON");
        BlockedMaterials.add("SIGN_POST");
        BlockedMaterials.add("WALL_SIGN");
        BlockedMaterials.add("SIGN");
        BlockedMaterials.add("LEVER");
        BlockedMaterials.add("BLACK_SHULKER_BOX");
        BlockedMaterials.add("BLUE_SHULKER_BOX");
        BlockedMaterials.add("BROWN_SHULKER_BOX");
        BlockedMaterials.add("CYAN_SHULKER_BOX");
        BlockedMaterials.add("GRAY_SHULKER_BOX");
        BlockedMaterials.add("GREEN_SHULKER_BOX");
        BlockedMaterials.add("LIGHT_BLUE_SHULKER_BOX");
        BlockedMaterials.add("LIME_SHULKER_BOX");
        BlockedMaterials.add("MAGENTA_SHULKER_BOX");
        BlockedMaterials.add("ORANGE_SHULKER_BOX");
        BlockedMaterials.add("PINK_SHULKER_BOX");
        BlockedMaterials.add("PURPLE_SHULKER_BOX");
        BlockedMaterials.add("RED_SHULKER_BOX");
        BlockedMaterials.add("SILVER_SHULKER_BOX");
        BlockedMaterials.add("WHITE_SHULKER_BOX");
        BlockedMaterials.add("YELLOW_SHULKER_BOX");
    }
}
