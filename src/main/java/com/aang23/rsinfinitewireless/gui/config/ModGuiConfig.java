package com.aang23.rsinfinitewireless.gui.config;

import com.aang23.rsinfinitewireless.RSInfiniteWireless;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.client.config.GuiConfig;

public class ModGuiConfig extends GuiConfig {
    public ModGuiConfig(GuiScreen guiScreen) {
        super(
            guiScreen,
            RSInfiniteWireless.INSTANCE.config.getConfigElements(),
            RSInfiniteWireless.ID,
            false,
            false,
            GuiConfig.getAbridgedConfigPath(RSInfiniteWireless.INSTANCE.config.getConfig().toString())
        );
    }
}
