package me.redth.armorochud.hud;

import cc.polyfrost.oneconfig.hud.Hud;
import cc.polyfrost.oneconfig.libs.universal.UMatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;

public abstract class ItemHUD extends Hud {
    protected static final transient Minecraft mc = Minecraft.getMinecraft();
    protected final transient ItemStack exampleItem;

    public ItemHUD(int x, int y, ItemStack exampleItem) {
        super(true, x, y);
        this.exampleItem = exampleItem;
    }

    @Override
    protected void draw(UMatrixStack matrices, float x, float y, float scale, boolean example) {
        ItemStack item = example ? exampleItem : getItem();
        if (item == null) return;

        GlStateManager.pushMatrix();
        GlStateManager.translate(x, y, 100F);
        GlStateManager.scale(scale, scale, 1.0);

        drawItem(item);

        GlStateManager.popMatrix();
    }

    protected abstract ItemStack getItem();

    @Override
    protected boolean shouldShow() {
        return super.shouldShow() && mc.thePlayer != null;
    }

    @Override
    protected float getWidth(float scale, boolean example) {
        return 16 * scale;
    }

    @Override
    protected float getHeight(float scale, boolean example) {
        return 16 * scale;
    }

    protected void drawItem(ItemStack item) {
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
