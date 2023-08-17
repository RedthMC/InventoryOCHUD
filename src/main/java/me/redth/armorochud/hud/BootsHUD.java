package me.redth.armorochud.hud;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class BootsHUD extends SingleItemHud {
    private static final ItemStack exampleItem = new ItemStack(Items.diamond_boots);

    public BootsHUD() {
        super(0, 556);
    }


    @Override
    protected ItemStack getItem(boolean example) {
        return example ? exampleItem : mc.thePlayer.getEquipmentInSlot(1);
    }
}
