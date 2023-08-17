package me.redth.armorochud.hud;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class HandHUD extends ItemHUD {
    private static final ItemStack exampleItem = new ItemStack(Items.diamond_sword);

    public HandHUD() {
        super(0, 492);
    }

    @Override
    protected ItemStack getItem(boolean example) {
        return example ? exampleItem : mc.thePlayer.getEquipmentInSlot(0);
    }
}
