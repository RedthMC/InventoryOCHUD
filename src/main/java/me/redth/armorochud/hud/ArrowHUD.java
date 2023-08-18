package me.redth.armorochud.hud;

import cc.polyfrost.oneconfig.config.annotations.Switch;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class ArrowHUD extends ItemHUD {
    private static final transient ItemStack barrier = new ItemStack(Blocks.barrier);
    private static final transient ItemStack creative = new ItemStack(Items.arrow);

    @Switch(name = "Display when holding bow", size = 2)
    public static boolean whenHoldingBow = true;

    public ArrowHUD() {
        super(0, 476, new ItemStack(Items.arrow, 10));
    }

    @Override
    protected ItemStack getItem() {
        if (mc.thePlayer.capabilities.isCreativeMode)
            return creative;

        int arrows = 0;
        for (ItemStack item : mc.thePlayer.inventory.mainInventory) {
            if (item != null && item.getItem() == Items.arrow) {
                arrows += item.stackSize;
            }
        }

        return arrows == 0 ? barrier : new ItemStack(Items.arrow, arrows);
    }

    private static boolean isHoldingBow() {
        if (!whenHoldingBow) return true;
        ItemStack item = mc.thePlayer.getHeldItem();
        return item != null && item.getItem() == Items.bow;
    }

    @Override
    protected boolean shouldShow() {
        return super.shouldShow() && isHoldingBow();
    }
}
