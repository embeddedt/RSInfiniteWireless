package com.aang23.rsinfinitewireless;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.config.IConfigElement;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public final class RSInfiniteWirelessConfig {
    private Configuration config;

    // region Infinite Wireless Transmitter
    public int infiniteWirelessTransmitterUsage;
    public int infiniteWirelessTransmitterRange;
    // endregion

    // region Categories
    private static final String INFINITE_WIRELESS_TRANSMITTER = "infiniteWirelessTransmitter";
    // endregion

    public RSInfiniteWirelessConfig(File configFile) {
        config = new Configuration(configFile);

        MinecraftForge.EVENT_BUS.register(this);

        loadConfig();
    }

    public Configuration getConfig() {
        return config;
    }

    @SubscribeEvent
    public void onConfigChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.getModID().equalsIgnoreCase(RSInfiniteWireless.ID)) {
            loadConfig();
        }
    }

    private void loadConfig() {
        // region Infinite Wireless Transmitter
        infiniteWirelessTransmitterUsage = config.getInt("infiniteWirelessTransmitterUsage",
                INFINITE_WIRELESS_TRANSMITTER, 1000, 0, Integer.MAX_VALUE,
                "The energy used by the Infinite Wireless Transmitter");
        infiniteWirelessTransmitterRange = config.getInt("infiniteWirelessTransmitterRange",
                INFINITE_WIRELESS_TRANSMITTER, Integer.MAX_VALUE, 0, Integer.MAX_VALUE,
                "The range of the Infinite Wireless Transmitter");
        // endRegion

        if (config.hasChanged()) {
            config.save();
        }
    }

    @SuppressWarnings("unchecked")
    public List<IConfigElement> getConfigElements() {
        List<IConfigElement> list = new ArrayList<>();

        list.add(new ConfigElement(config.getCategory(INFINITE_WIRELESS_TRANSMITTER)));
        
        return list;
    }
}
