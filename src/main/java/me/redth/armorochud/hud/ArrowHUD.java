package me.redth.armorochud.hud;

import cc.polyfrost.oneconfig.config.annotations.Switch;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class ArrowHUD extends SingleItemHud {
    private static final ItemStack exampleItem = new ItemStack(Items.arrow, 10);
    private static final ItemStack barrier = new ItemStack(Blocks.barrier);

    @Switch(name = "Display when holding bow", size = 2)
    public static boolean whenHoldingBow = true;

    public ArrowHUD() {
        super(0, 476);
    }

    @Override
    protected ItemStack getItem(boolean example) {
        return example ? exampleItem : getItemShown();
    }

    private static ItemStack getItemShown() {
        int arrows = 0;
        for (ItemStack item : mc.thePlayer.inventory.mainInventory) {
            if (item != null && item.getItem() == Items.arrow) {
                arrows += item.stackSize;
            }
        }

        if (mc.thePlayer.capabilities.isCreativeMode) {
            return new ItemStack(Items.arrow);
        } else {
            return arrows == 0 ? barrier : new ItemStack(Items.arrow, arrows);
        }
    }

    private static boolean isHoldingBow() {
        return !whenHoldingBow || (mc.thePlayer.getHeldItem().getItem() == Items.bow);
    }

    @Override
    protected boolean shouldShow() {
        return super.shouldShow() && isHoldingBow();
    }
}
