package me.redth.inventoryochud.config;

import cc.polyfrost.oneconfig.config.Config;
import cc.polyfrost.oneconfig.config.annotations.HUD;
import cc.polyfrost.oneconfig.config.data.Mod;
import cc.polyfrost.oneconfig.config.data.ModType;
import me.redth.inventoryochud.InventoryOCHUD;
import me.redth.inventoryochud.hud.EnderChestHUD;
import me.redth.inventoryochud.hud.PlayerInventoryHUD;

@SuppressWarnings("unused")
public class ModConfig extends Config {
    @HUD(name = "Inventory", category = "Inventory")
    public static PlayerInventoryHUD playerInventoryHUD = new PlayerInventoryHUD();

    @HUD(name = "Ender Chest", category = "Ender Chest")
    public static EnderChestHUD enderChestHUD = new EnderChestHUD();

    public ModConfig() {
        super(new Mod(InventoryOCHUD.NAME, ModType.HUD), InventoryOCHUD.MODID + ".json");
        initialize();
    }

}

