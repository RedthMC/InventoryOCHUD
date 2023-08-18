package me.redth.inventoryochud;

import cc.polyfrost.oneconfig.events.EventManager;
import cc.polyfrost.oneconfig.events.event.ReceivePacketEvent;
import cc.polyfrost.oneconfig.events.event.ScreenOpenEvent;
import cc.polyfrost.oneconfig.libs.eventbus.Subscribe;
import me.redth.inventoryochud.config.ModConfig;
import me.redth.inventoryochud.hud.EnderChestHUD;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.network.play.server.S01PacketJoinGame;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = InventoryOCHUD.MODID, name = InventoryOCHUD.NAME, version = InventoryOCHUD.VERSION)
public class InventoryOCHUD {
    public static final String MODID = "@ID@";
    public static final String NAME = "@NAME@";
    public static final String VERSION = "@VER@";

    @Mod.Instance(MODID)
    public static InventoryOCHUD INSTANCE;

    @Mod.EventHandler
    public void onInit(FMLInitializationEvent event) {
        new ModConfig();
        EventManager.INSTANCE.register(this);
    }

    @Subscribe
    public void onOpenContainer(ScreenOpenEvent e) {
        if (!(e.screen instanceof GuiChest)) return;
        GuiChest chestGUI = (GuiChest) e.screen;
        ContainerChest chestContainer = (ContainerChest) chestGUI.inventorySlots;
        String title = chestContainer.getLowerChestInventory().getDisplayName().getUnformattedText();
        if (!"Ender Chest".equals(title)) return;
        EnderChestHUD.enderChest = chestContainer.getLowerChestInventory();

    }

    @Subscribe
    public void onWorldLoad(ReceivePacketEvent e) {
        if (!(e.packet instanceof S01PacketJoinGame)) return;
        EnderChestHUD.enderChest = null;
    }
}
