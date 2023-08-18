package me.redth.armorochud.hud;

import cc.polyfrost.oneconfig.config.annotations.Dropdown;
import me.redth.armorochud.config.ModConfig;
import net.minecraft.item.ItemStack;

public class EquipmentHUD extends ItemHUD {
    protected final transient int slot;

    @Dropdown(name = "Show Durability", options = {"None", "On Left", "On Right"}, size = 2)
    public int showDurability = 2;

    public EquipmentHUD(int x, int y, ItemStack exampleItem, int slot) {
        super(x, y, exampleItem);
        this.slot = slot;
    }

    @Override
    protected ItemStack getItem() {
        return mc.thePlayer.getEquipmentInSlot(slot);
    }

    @Override
    protected void drawItem(ItemStack item) {
        super.drawItem(item);
        if (item.isItemStackDamageable()) {
            drawDurability(item, showDurability);
        }
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
