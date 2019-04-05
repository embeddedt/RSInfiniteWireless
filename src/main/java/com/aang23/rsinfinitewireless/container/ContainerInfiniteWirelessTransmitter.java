package com.aang23.rsinfinitewireless.container;

import com.raoulvdberge.refinedstorage.container.ContainerBase;
import com.aang23.rsinfinitewireless.tile.TileInfiniteWirelessTransmitter;
import net.minecraft.entity.player.EntityPlayer;

public class ContainerInfiniteWirelessTransmitter extends ContainerBase {
    public ContainerInfiniteWirelessTransmitter(TileInfiniteWirelessTransmitter infiniteWirelessTransmitter, EntityPlayer player) {
        super(infiniteWirelessTransmitter, player);

        addPlayerInventory(8, 50);
    }
}