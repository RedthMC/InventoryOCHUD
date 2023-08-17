package me.redth.armorochud.hud;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class LeggingsHUD extends SingleItemHud {
    private static final ItemStack exampleItem = new ItemStack(Items.diamond_leggings);

    public LeggingsHUD() {
        super(0, 540);
    }

    @Override
    protected ItemStack getItem(boolean example) {
        return example ? exampleItem : mc.thePlayer.getEquipmentInSlot(2);
    }
}
