package com.aang23.rsinfinitewireless.proxy;

import com.raoulvdberge.refinedstorage.RS;
import com.raoulvdberge.refinedstorage.network.MessageNetworkItemOpen;
import com.aang23.rsinfinitewireless.RSInfiniteWireless;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import com.raoulvdberge.refinedstorage.render.model.baked.BakedModelFullbright;
import com.aang23.rsinfinitewireless.RSInfiniteWirelessBlocks;
import net.minecraft.item.Item;

public class ProxyClient extends ProxyCommon {
    @SubscribeEvent
    public void registerModels(ModelBakeEvent e) {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(RSInfiniteWirelessBlocks.INFINITE_WIRELESS_TRANSMITTER),
                0, new ModelResourceLocation(RSInfiniteWireless.ID + ":infinite_wireless_transmitter", "inventory"));
    }

    @Override
    public void init(FMLInitializationEvent e) {
        super.init(e);
    }

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent e) {
        InventoryPlayer inv = Minecraft.getMinecraft().player.inventory;
    }

    @SubscribeEvent
    public void onModelBake(ModelBakeEvent e) {
        for (ModelResourceLocation resource : e.getModelRegistry().getKeys()) {
            if (resource.getNamespace().equals(RSInfiniteWireless.ID)
                    && resource.getPath().equals("infinite_wireless_transmitter")) {
                e.getModelRegistry().putObject(resource,
                        new BakedModelFullbright(e.getModelRegistry().getObject(resource),
                                RSInfiniteWireless.ID + ":blocks/infinite_wireless_transmitter_connected"));
            }
        }
    }

    private void findAndOpen(IInventory inv, Item search) {
        for (int i = 0; i < inv.getSizeInventory(); ++i) {
            ItemStack slot = inv.getStackInSlot(i);

            if (slot.getItem() == search) {
                RS.INSTANCE.network.sendToServer(new MessageNetworkItemOpen(i));

                return;
            }
        }
    }
}
