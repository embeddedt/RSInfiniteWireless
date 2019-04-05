package com.aang23.rsinfinitewireless.apiimpl.network.node;

import com.raoulvdberge.refinedstorage.api.network.IWirelessTransmitter;
import com.raoulvdberge.refinedstorage.apiimpl.network.node.NetworkNode;
import com.aang23.rsinfinitewireless.RSInfiniteWireless;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class NetworkNodeInfiniteWirelessTransmitter extends NetworkNode implements IWirelessTransmitter {
    public static final String ID = "rsinfinitewireless:infinite_wireless_transmitter";

    public NetworkNodeInfiniteWirelessTransmitter(World world, BlockPos pos) {
        super(world, pos);
    }

    @Override
    public int getEnergyUsage() {
        return RSInfiniteWireless.INSTANCE.config.infiniteWirelessTransmitterUsage;
    }

    @Override
    public String getId() {
        return ID;
    }

    @Override
    public boolean canConduct(@Nullable EnumFacing direction) {
        return direction != null && EnumFacing.DOWN.equals(direction);
    }

    @Override
    public boolean hasConnectivityState() {
        return true;
    }

    @Override
    public void visit(Operator operator) {
        operator.apply(world, pos.offset(EnumFacing.DOWN), EnumFacing.UP);
    }

    @Override
    public int getRange() {
        return RSInfiniteWireless.INSTANCE.config.infiniteWirelessTransmitterRange;
    }

    @Override
    public BlockPos getOrigin() {
        return pos;
    }

    @Override
    public int getDimension() {
        return world.provider.getDimension();
    }
}
