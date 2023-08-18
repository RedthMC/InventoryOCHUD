package me.redth.armorochud.hud;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class ChestplateHUD extends EquipmentHUD {
    public ChestplateHUD() {
        super(0, 524, new ItemStack(Items.diamond_chestplate), 3);
    }
}
