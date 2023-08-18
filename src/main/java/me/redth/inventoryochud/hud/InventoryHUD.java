package me.redth.inventoryochud.hud;

import cc.polyfrost.oneconfig.hud.BasicHud;
import cc.polyfrost.oneconfig.libs.universal.UMatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;

public abstract class InventoryHUD extends BasicHud {
    protected static final transient Minecraft mc = Minecraft.getMinecraft();

    public InventoryHUD(int x, int y) {
        super(true, x, y);
    }

    @Override
    protected void draw(UMatrixStack matrices, float x, float y, float scale, boolean example) {
        GlStateManager.pushMatrix();
        GlStateManager.translate(x, y, 100F);
        GlStateManager.scale(scale, scale, 1.0);

        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 9; column++) {
                int index = row * 9 + column;
                drawItem(getItem(index));
                GlStateManager.translate(18, 0, 0);
            }
            GlStateManager.translate(-162, 18, 0);
        }


        GlStateManager.popMatrix();
    }

    protected abstract ItemStack getItem(int index);

    @Override
    protected boolean shouldShow() {
        return super.shouldShow() && mc.thePlayer != null;
    }

    @Override
    protected float getWidth(float scale, boolean example) {
        return 160 * scale;
    }

    @Override
    protected float getHeight(float scale, boolean example) {
        return 50 * scale;
    }

    protected static void drawItem(ItemStack item) {
        if (item == null) return;

        GlStateManager.enableRescaleNormal();
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        RenderHelper.enableGUIStandardItemLighting();

        RenderItem itemRenderer = mc.getRenderItem();
        itemRenderer.renderItemAndEffectIntoGUI(item, 0, 0);
        itemRenderer.renderItemOverlayIntoGUI(mc.fontRendererObj, item, 0, 0, null);

        RenderHelper.disableStandardItemLighting();
        GlStateManager.disableBlend();
        GlStateManager.disableRescaleNormal();
    }
}
