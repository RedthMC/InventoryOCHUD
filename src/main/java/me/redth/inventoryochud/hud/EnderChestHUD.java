package me.redth.inventoryochud.hud;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class EnderChestHUD extends InventoryHUD {
    public static transient IInventory enderChest;

    public EnderChestHUD() {
        super(540, 540);
    }

    @Override
    protected ItemStack getItem(int index) {
        if (enderChest == null) return null;
        return enderChest.getStackInSlot(index);
    }

    @Override
    protected boolean shouldShow() {
        return super.shouldShow() && enderChest != null;
    }
}
