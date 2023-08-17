package me.redth.armorochud;

import me.redth.armorochud.config.ModConfig;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = ArmorOCHUD.MODID, name = ArmorOCHUD.NAME, version = ArmorOCHUD.VERSION)
public class ArmorOCHUD {
    public static final String MODID = "@ID@";
    public static final String NAME = "@NAME@";
    public static final String VERSION = "@VER@";

    @Mod.Instance(MODID)
    public static ArmorOCHUD INSTANCE;

    @Mod.EventHandler
    public void onInit(FMLInitializationEvent event) {
        new ModConfig();
    }
}
