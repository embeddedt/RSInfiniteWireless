package com.aang23.rsinfinitewireless;

import com.raoulvdberge.refinedstorage.api.IRSAPI;
import com.raoulvdberge.refinedstorage.api.RSAPIInject;
import com.aang23.rsinfinitewireless.proxy.ProxyCommon;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;

@Mod(modid = RSInfiniteWireless.ID, version = RSInfiniteWireless.VERSION, dependencies = RSInfiniteWireless.DEPENDENCIES, acceptedMinecraftVersions = "[1.12,1.13)", guiFactory = RSInfiniteWireless.GUI_FACTORY)
public final class RSInfiniteWireless {
    @RSAPIInject
    public static IRSAPI RSAPI;

    public static final String ID = "rsinfinitewireless";
    public static final String VERSION = "@version@";
    public static final String DEPENDENCIES = "required-after:refinedstorage@[1.6.9,);";
    public static final String GUI_FACTORY = "com.aang23.rsinfinitewireless.gui.config.ModGuiFactory";

    @Mod.Instance
    public static RSInfiniteWireless INSTANCE;

    public RSInfiniteWirelessConfig config;
    public final SimpleNetworkWrapper network = NetworkRegistry.INSTANCE.newSimpleChannel(ID);

    @SidedProxy(clientSide = "com.aang23.rsinfinitewireless.proxy.ProxyClient", serverSide = "com.aang23.rsinfinitewireless.proxy.ProxyCommon")
    public static ProxyCommon PROXY;

    @EventHandler
    public void preInit(FMLPreInitializationEvent e) {
        config = new RSInfiniteWirelessConfig(e.getSuggestedConfigurationFile());

        PROXY.preInit(e);
    }

    @EventHandler
    public void init(FMLInitializationEvent e) {
        PROXY.init(e);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        PROXY.postInit(e);
    }
}
