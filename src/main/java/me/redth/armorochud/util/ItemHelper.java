package me.redth.armorochud.util;

import me.redth.armorochud.config.ModConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public interface ItemHelper {
    ItemStack[] EXAMPLES = new ItemStack[] {
            new ItemStack(Items.diamond_sword),
            new ItemStack(Items.diamond_boots),
            new ItemStack(Items.diamond_leggings),
            new ItemStack(Items.diamond_chestplate),
            new ItemStack(Items.diamond_helmet)
    };
    Minecraft MC = Minecraft.getMinecraft();

    static ItemStack getItem(int slot, boolean example) {
        if (example) return EXAMPLES[slot];
        if (MC.thePlayer == null) return null;
        return MC.thePlayer.getEquipmentInSlot(slot);
    }

    static void drawItem(ItemStack item) {
        GlStateManager.enableRescaleNormal();
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        RenderHelper.enableGUIStandardItemLighting();

        RenderItem itemRenderer = MC.getRenderItem();
        itemRenderer.renderItemAndEffectIntoGUI(item, 0, 0);
        itemRenderer.renderItemOverlayIntoGUI(MC.fontRendererObj, item, 0, 0, null);

        RenderHelper.disableStandardItemLighting();
        GlStateManager.disableBlend();
        GlStateManager.disableRescaleNormal();
    }

    static String getDurabilityText(ItemStack item) {
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

    static void drawDurability(ItemStack item, int side) {
        String text = getDurabilityText(item);
        int color = getDurabilityColor(item);

        switch (side) {
            case 1:
                int textWidth = MC.fontRendererObj.getStringWidth(text);
                MC.fontRendererObj.drawStringWithShadow(text, -2 - textWidth, 4, color);
                break;
            case 2:
                MC.fontRendererObj.drawStringWithShadow(text, 18, 4, color);
                break;
        }
    }

    static int getDurabilityColor(ItemStack item) {
        if (ModConfig.useStaticTextColor) return ModConfig.staticTextColor.getRGB();

        int i = (int) Math.round(item.getItem().getDurabilityForDisplay(item) * 255.0D);
        return (i << 16 | (255 - i) << 8) | 0xFF000000;
    }
}
