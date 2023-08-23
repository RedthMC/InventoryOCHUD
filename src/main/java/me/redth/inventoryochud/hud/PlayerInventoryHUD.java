package me.redth.inventoryochud.hud;

import net.minecraft.item.ItemStack;

public class PlayerInventoryHUD extends InventoryHUD {

    public PlayerInventoryHUD() {
        super(540, 640);
    }

    @Override
    protected ItemStack getItem(int index) {
        if (mc.thePlayer == null) return null;
        return mc.thePlayer.inventory.mainInventory[index + 9];
    }
}
