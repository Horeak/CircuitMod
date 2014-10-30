package com.circuit.CircuitMod.Proxy;

import com.circuit.CircuitMod.Main.ModBlocks;
import com.circuit.CircuitMod.Rendering.Items.EventRecivers.ItemMultiDigitDisplayRender;
import com.circuit.CircuitMod.Rendering.Items.EventRecivers.ItemOneDigitDisplayRender;
import com.circuit.CircuitMod.Rendering.Items.EventSenders.ItemMultiDigitConstantRender;
import com.circuit.CircuitMod.Rendering.Items.EventSenders.ItemOneDigitConstantRender;
import com.circuit.CircuitMod.Rendering.Items.EventSenders.ItemOneDigitCounterRender;
import com.circuit.CircuitMod.Rendering.Items.ItemCircuitBoxRender;
import com.circuit.CircuitMod.Rendering.Items.ItemCircuitCableRender;
import com.circuit.CircuitMod.Rendering.Items.ItemConnectionPointRender;
import com.circuit.CircuitMod.Rendering.TileEntities.EventReceivers.TIleEntityMultiDigitDisplayRender;
import com.circuit.CircuitMod.Rendering.TileEntities.EventReceivers.TileEntityOneDigitDisplayRender;
import com.circuit.CircuitMod.Rendering.TileEntities.EventSenders.TileEntityMultiDigitConstantRender;
import com.circuit.CircuitMod.Rendering.TileEntities.EventSenders.TileEntityOneDigitConstantRender;
import com.circuit.CircuitMod.Rendering.TileEntities.EventSenders.TileEntityOneDigitCounterRender;
import com.circuit.CircuitMod.Rendering.TileEntities.TileEntityCircuitBoxRender;
import com.circuit.CircuitMod.Rendering.TileEntities.TileEntityCircuitCableRender;
import com.circuit.CircuitMod.TileEntity.EventReceivers.TileEntityMultiDigitDisplay;
import com.circuit.CircuitMod.TileEntity.EventReceivers.TileEntityOneDigitDisplay;
import com.circuit.CircuitMod.TileEntity.EventSenders.TileEntityMultiDigitConstant;
import com.circuit.CircuitMod.TileEntity.EventSenders.TileEntityOneDigitConstant;
import com.circuit.CircuitMod.TileEntity.EventSenders.TileEntityOneDigitCounter;
import com.circuit.CircuitMod.TileEntity.TileEntityCircuitBox;
import com.circuit.CircuitMod.TileEntity.TileEntityCircuitCable;
import cpw.mods.fml.client.registry.ClientRegistry;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends ServerProxy  implements ProxyInterface {

    @Override
    public void RegisterRenderThings() {

        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCircuitBox.class, new TileEntityCircuitBoxRender());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCircuitCable.class, new TileEntityCircuitCableRender());

        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityOneDigitDisplay.class, new TileEntityOneDigitDisplayRender());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityOneDigitConstant.class, new TileEntityOneDigitConstantRender());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityOneDigitCounter.class, new TileEntityOneDigitCounterRender());

        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMultiDigitDisplay.class, new TIleEntityMultiDigitDisplayRender());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMultiDigitConstant.class, new TileEntityMultiDigitConstantRender());



        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.CircuitCable), new ItemCircuitCableRender());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.CircuitBox), new ItemCircuitBoxRender());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.CableConnectionPoint), new ItemConnectionPointRender());

        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.OneDigitDisplay), new ItemOneDigitDisplayRender());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.OneDigitConstant), new ItemOneDigitConstantRender());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.OneDigitCounter), new ItemOneDigitCounterRender());

        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.MultiDigitDisplay), new ItemMultiDigitDisplayRender());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.MultiDigitConstant), new ItemMultiDigitConstantRender());

    }
}
