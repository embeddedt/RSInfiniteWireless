package com.aang23.rsinfinitewireless.proxy;

import com.aang23.rsinfinitewireless.RSInfiniteWireless;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import com.raoulvdberge.refinedstorage.apiimpl.network.node.NetworkNode;
import com.aang23.rsinfinitewireless.RSInfiniteWirelessBlocks;
import com.aang23.rsinfinitewireless.apiimpl.network.node.NetworkNodeInfiniteWirelessTransmitter;
import com.aang23.rsinfinitewireless.gui.GuiHandler;
import com.aang23.rsinfinitewireless.tile.TileInfiniteWirelessTransmitter;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ProxyCommon {
    public void preInit(FMLPreInitializationEvent e) {
        MinecraftForge.EVENT_BUS.register(this);
    }

    public void init(FMLInitializationEvent e) {
        RSInfiniteWireless.RSAPI.getNetworkNodeRegistry().add(NetworkNodeInfiniteWirelessTransmitter.ID, (tag, world, pos) -> {
            NetworkNode node = new NetworkNodeInfiniteWirelessTransmitter(world, pos);

            node.read(tag);

            return node;
        });

        GameRegistry.registerTileEntity(TileInfiniteWirelessTransmitter.class,
                new ResourceLocation(RSInfiniteWireless.ID, "infinite_wireless_transmitter"));

        NetworkRegistry.INSTANCE.registerGuiHandler(RSInfiniteWireless.INSTANCE, new GuiHandler());
    }

    public void postInit(FMLPostInitializationEvent e) {
        // NO OP
    }

    @SubscribeEvent
    public void registerBlocks(RegistryEvent.Register<Block> e) {
        e.getRegistry().register(RSInfiniteWirelessBlocks.INFINITE_WIRELESS_TRANSMITTER);
    }

    @SubscribeEvent
    public void registerItems(RegistryEvent.Register<Item> e) {
        e.getRegistry().register(RSInfiniteWirelessBlocks.INFINITE_WIRELESS_TRANSMITTER.createItem());
    }

    @SubscribeEvent
    public void fixItemMappings(RegistryEvent.MissingMappings<Item> e) {
    }
}
