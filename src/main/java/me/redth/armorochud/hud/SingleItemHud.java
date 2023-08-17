package me.redth.armorochud.hud;

import cc.polyfrost.oneconfig.config.annotations.Dropdown;
import cc.polyfrost.oneconfig.hud.Hud;
import cc.polyfrost.oneconfig.libs.universal.UMatrixStack;
import me.redth.armorochud.config.ModConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;

public abstract class SingleItemHud extends Hud {
    protected static final Minecraft mc = Minecraft.getMinecraft();

    @Dropdown(name = "Show Durability", options = {"None", "On Left", "On Right"}, size = 2)
    public int showDurability = 2;

    public SingleItemHud(int x, int y) {
        super(true, x, y);
    }

    @Override
    protected void draw(UMatrixStack matrices, float x, float y, float scale, boolean example) {
        ItemStack item = getItem(example);
        if (item == null) return;

        GlStateManager.pushMatrix();
        GlStateManager.translate(x, y, 100F);
        GlStateManager.scale(scale, scale, 1.0);

        drawItem(item);

        if (item.isItemStackDamageable()) {
            drawDurability(item, showDurability);
        }

        GlStateManager.popMatrix();
    }

    protected abstract ItemStack getItem(boolean example);

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

    protected static void drawItem(ItemStack item) {
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

    protected static void drawDurability(ItemStack item, int side) {
        String text = getDurabilityText(item);
        int color = getDurabilityColor(item);

        switch (side) {
            case 1:
                int textWidth = mc.fontRendererObj.getStringWidth(text);
                mc.fontRendererObj.drawStringWithShadow(text, -2 - textWidth, 4, color);
                break;
            case 2:
                mc.fontRendererObj.drawStringWithShadow(text, 18, 4, color);
                break;
        }
    }

    protected static String getDurabilityText(ItemStack item) {
        switch (ModConfig.durabilityFormat) {
            case 0:
                return Math.round(((1.0D - item.getItem().getDurabilityForDisplay(item)) * 100.0D)) + "%";
            case 1:
                return String.valueOf(item.getMaxDamage() - item.getItemDamage());
            case 2:
            default:
                return (item.getMaxDamage() - item.getItemDamage()) + "/" + item.getMaxDamage();
        }
    }

    protected static int getDurabilityColor(ItemStack item) {
        if (ModConfig.useStaticTextColor) return ModConfig.staticTextColor.getRGB();

        int i = (int) Math.round(item.getItem().getDurabilityForDisplay(item) * 255.0D);
        return (i << 16 | (255 - i) << 8) | 0xFF000000;
    }
}
