package me.redth.armorochud.hud;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class HandHUD extends EquipmentHUD {
    public HandHUD() {
        super(0, 492, new ItemStack(Items.diamond_sword), 0);
    }
}
