package me.redth.armorochud.config;

import cc.polyfrost.oneconfig.config.Config;
import cc.polyfrost.oneconfig.config.annotations.Color;
import cc.polyfrost.oneconfig.config.annotations.Dropdown;
import cc.polyfrost.oneconfig.config.annotations.HUD;
import cc.polyfrost.oneconfig.config.annotations.Switch;
import cc.polyfrost.oneconfig.config.core.OneColor;
import cc.polyfrost.oneconfig.config.data.Mod;
import cc.polyfrost.oneconfig.config.data.ModType;
import me.redth.armorochud.ArmorOCHUD;
import me.redth.armorochud.hud.BootsHUD;
import me.redth.armorochud.hud.ChestplateHUD;
import me.redth.armorochud.hud.HelmetHUD;
import me.redth.armorochud.hud.LeggingsHUD;
import me.redth.armorochud.hud.MainhandHUD;

@SuppressWarnings("unused")
public class ModConfig extends Config {

    @Dropdown(name = "Durability Format", options = {"Percentage", "Remaining", "Remaining/Max"}, size = 2)
    public static int durabilityFormat = 2;

    @Switch(name = "Use Static Text Color")
    public static boolean useStaticTextColor = false;

    @Color(name = "Static Text Color")
    public static OneColor staticTextColor = new OneColor(0xFFFFFFFF);

    @HUD(name = "Mainhand", category = "Mainhand")
    public MainhandHUD mainhandHUD = new MainhandHUD();

    @HUD(name = "Helmet", category = "Helmet")
    public HelmetHUD helmetHUD = new HelmetHUD();

    @HUD(name = "Chestplate", category = "Chestplate")
    public ChestplateHUD chestplateHUD = new ChestplateHUD();

    @HUD(name = "Leggings", category = "Leggings")
    public LeggingsHUD leggingsHUD = new LeggingsHUD();

    @HUD(name = "Boots", category = "Boots")
    public BootsHUD bootsHUD = new BootsHUD();

    public ModConfig() {
        super(new Mod(ArmorOCHUD.NAME, ModType.HUD), ArmorOCHUD.MODID + ".json");
        initialize();
        addDependency("staticTextColor", "useStaticTextColor");
    }

}

