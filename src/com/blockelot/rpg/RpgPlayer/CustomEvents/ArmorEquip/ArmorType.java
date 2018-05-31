/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blockelot.rpg.RpgPlayer.CustomEvents.ArmorEquip;

import org.bukkit.inventory.ItemStack;

/**
 * @Author Borlea
 * @Github https://github.com/borlea/
 * @Website http://codingforcookies.com/
 * @Since Jul 30, 2015 6:46:16 PM
 */
public enum ArmorType {
    HELMET(5), CHESTPLATE(6), LEGGINGS(7), BOOTS(8);
    /**
     * Attempts to match the ArmorType for the specified ItemStack.
     *
     * @param itemStack The ItemStack to parse the type of.
     * @return The parsed ArmorType. (null if none were found.)
     */
    public final static ArmorType matchType(final ItemStack itemStack) {
        if (itemStack == null) {
            return null;
        }
        switch (itemStack.getType()) {
            case DIAMOND_HELMET:
            case GOLD_HELMET:
            case IRON_HELMET:
            case CHAINMAIL_HELMET:
            case LEATHER_HELMET:
            case PUMPKIN:
            case SKULL_ITEM:
                return HELMET;
            case DIAMOND_CHESTPLATE:
            case GOLD_CHESTPLATE:
            case IRON_CHESTPLATE:
            case CHAINMAIL_CHESTPLATE:
            case LEATHER_CHESTPLATE:
                return CHESTPLATE;
            case DIAMOND_LEGGINGS:
            case GOLD_LEGGINGS:
            case IRON_LEGGINGS:
            case CHAINMAIL_LEGGINGS:
            case LEATHER_LEGGINGS:
                return LEGGINGS;
            case DIAMOND_BOOTS:
            case GOLD_BOOTS:
            case IRON_BOOTS:
            case CHAINMAIL_BOOTS:
            case LEATHER_BOOTS:
                return BOOTS;
            default:
                return null;
        }
    }

    private final int slot;

    ArmorType(int slot) {
        this.slot = slot;
    }


    public int getSlot() {
        return slot;
    }
}
