package com.circuit.CircuitMod.Proxy;

import com.circuit.CircuitMod.Main.CircuitMod;
import com.circuit.CircuitMod.Main.ModBlocks;
import com.circuit.CircuitMod.Rendering.Items.DataBlocks.EntityDetectorItemRender;
import com.circuit.CircuitMod.Rendering.Items.EventRecivers.ItemLampRender;
import com.circuit.CircuitMod.Rendering.Items.EventRecivers.ItemMultiDigitDisplayRender;
import com.circuit.CircuitMod.Rendering.Items.EventRecivers.ItemOneDigitDisplayRender;
import com.circuit.CircuitMod.Rendering.Items.EventRecivers.ItemVariableRender;
import com.circuit.CircuitMod.Rendering.Items.EventSenders.*;
import com.circuit.CircuitMod.Rendering.Items.ItemCircuitBoxRender;
import com.circuit.CircuitMod.Rendering.Items.ItemCircuitCableRender;
import com.circuit.CircuitMod.Rendering.Items.ItemConnectionPointRender;
import com.circuit.CircuitMod.Rendering.TileEntities.DataBlocks.TileEntityDataDelayRender;
import com.circuit.CircuitMod.Rendering.TileEntities.DataBlocks.TileEntityDataScreenRender;
import com.circuit.CircuitMod.Rendering.TileEntities.DataBlocks.TileEntityEntityDetectorRender;
import com.circuit.CircuitMod.Rendering.TileEntities.EventReceivers.TIleEntityMultiDigitDisplayRender;
import com.circuit.CircuitMod.Rendering.TileEntities.EventReceivers.TileEntityLampRender;
import com.circuit.CircuitMod.Rendering.TileEntities.EventReceivers.TileEntityOneDigitDisplayRender;
import com.circuit.CircuitMod.Rendering.TileEntities.EventReceivers.TileEntityVariableRender;
import com.circuit.CircuitMod.Rendering.TileEntities.EventSenders.*;
import com.circuit.CircuitMod.Rendering.TileEntities.TileEntityCircuitBoxRender;
import com.circuit.CircuitMod.Rendering.TileEntities.TileEntityCircuitCableRender;
import com.circuit.CircuitMod.TileEntity.DataBlocks.TileEntityDataDelay;
import com.circuit.CircuitMod.TileEntity.DataBlocks.TileEntityDataScreen;
import com.circuit.CircuitMod.TileEntity.DataBlocks.TileEntityEntityDetector;
import com.circuit.CircuitMod.TileEntity.EventReceivers.TileEntityLamp;
import com.circuit.CircuitMod.TileEntity.EventReceivers.TileEntityMultiDigitDisplay;
import com.circuit.CircuitMod.TileEntity.EventReceivers.TileEntityOneDigitDisplay;
import com.circuit.CircuitMod.TileEntity.EventReceivers.TileEntityVariable;
import com.circuit.CircuitMod.TileEntity.EventSenders.*;
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
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityLamp.class, new TileEntityLampRender());

        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityVariable.class, new TileEntityVariableRender());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityRandomNumber.class, new TileEntityRandomNumberComponentRender());

        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityOneDigitDisplay.class, new TileEntityOneDigitDisplayRender());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityOneDigitConstant.class, new TileEntityOneDigitConstantRender());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityOneDigitCounter.class, new TileEntityOneDigitCounterRender());

        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMultiDigitDisplay.class, new TIleEntityMultiDigitDisplayRender());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMultiDigitConstant.class, new TileEntityMultiDigitConstantRender());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMultiDigitCounter.class, new TileEntityMultiDigitCounterRender());

        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDataScreen.class, new TileEntityDataScreenRender());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityEntityDetector.class, new TileEntityEntityDetectorRender());

        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDataDelay.class, new TileEntityDataDelayRender());



        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.CircuitCable), new ItemCircuitCableRender());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.CircuitBox), new ItemCircuitBoxRender());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.CableConnectionPoint), new ItemConnectionPointRender());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.Lamp), new ItemLampRender());

        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.Variable), new ItemVariableRender());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.RandomNumberComponent), new ItemRandomNumberComponentRender());

        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.OneDigitDisplay), new ItemOneDigitDisplayRender());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.OneDigitConstant), new ItemOneDigitConstantRender());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.OneDigitCounter), new ItemOneDigitCounterRender());

        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.MultiDigitDisplay), new ItemMultiDigitDisplayRender());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.MultiDigitConstant), new ItemMultiDigitConstantRender());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.MultiDigitCounter), new ItemMultiDigitCounterRender());

        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.EntityDetector), new EntityDetectorItemRender());

    }

    @Override
    public void PreInt() {

        CircuitMod.localizationUpdater.registerListener();
    }
}
