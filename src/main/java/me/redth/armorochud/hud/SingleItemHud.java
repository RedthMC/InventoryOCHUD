package me.redth.armorochud.hud;

import cc.polyfrost.oneconfig.config.annotations.Dropdown;
import cc.polyfrost.oneconfig.hud.Hud;
import cc.polyfrost.oneconfig.libs.universal.UMatrixStack;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;

import me.redth.armorochud.util.ItemHelper;

public class SingleItemHud extends Hud {
    private final transient int slot;

    @Dropdown(name = "Show Durability", options = {"None", "On Left", "On Right"}, size = 2)
    public int showDurability = 2;

    public SingleItemHud(int slot, int x, int y) {
        super(true, x, y);
        this.slot = slot;
    }

    @Override
    protected void draw(UMatrixStack matrices, float x, float y, float scale, boolean example) {
        ItemStack item = ItemHelper.getItem(slot, example);
        if (item == null) return;

        GlStateManager.pushMatrix();
        GlStateManager.translate(x, y, 100F);
        GlStateManager.scale(scale, scale, 1.0);

        ItemHelper.drawItem(item);

        if (item.isItemStackDamageable()) {
            ItemHelper.drawDurability(item, showDurability);
        }

        GlStateManager.popMatrix();
    }

    @Override
    protected float getWidth(float scale, boolean example) {
        return 16 * scale;
    }

    @Override
    protected float getHeight(float scale, boolean example) {
        return 16 * scale;
    }
}
