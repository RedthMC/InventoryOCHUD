package me.redth.armorochud.hud;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class ChestplateHUD extends SingleItemHud {
    private static final ItemStack exampleItem = new ItemStack(Items.diamond_chestplate);

    public ChestplateHUD() {
        super(0, 524);
    }


    @Override
    protected ItemStack getItem(boolean example) {
        return example ? exampleItem : mc.thePlayer.getEquipmentInSlot(3);
    }
}
