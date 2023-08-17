package me.redth.armorochud.hud;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class HelmetHUD extends SingleItemHud {
    private static final ItemStack exampleItem = new ItemStack(Items.diamond_helmet);

    public HelmetHUD() {
        super(0, 508);
    }

    @Override
    protected ItemStack getItem(boolean example) {
        return example ? exampleItem : mc.thePlayer.getEquipmentInSlot(4);
    }
}
