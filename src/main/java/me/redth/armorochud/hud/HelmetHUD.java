package me.redth.armorochud.hud;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class HelmetHUD extends EquipmentHUD {
    public HelmetHUD() {
        super(0, 508, new ItemStack(Items.diamond_helmet), 4);
    }
}
