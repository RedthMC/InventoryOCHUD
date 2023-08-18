package me.redth.armorochud.hud;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class LeggingsHUD extends EquipmentHUD {
    public LeggingsHUD() {
        super(0, 540, new ItemStack(Items.diamond_leggings), 2);
    }
}
