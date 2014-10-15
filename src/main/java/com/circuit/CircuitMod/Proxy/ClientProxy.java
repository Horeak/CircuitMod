package com.circuit.CircuitMod.Proxy;

import com.circuit.CircuitMod.Main.ModBlocks;
import com.circuit.CircuitMod.Rendering.Items.ItemCircuitBoxRender;
import com.circuit.CircuitMod.Rendering.Items.ItemCircuitCableRender;
import com.circuit.CircuitMod.Rendering.TileEntities.TileEntityCircuitBoxRender;
import com.circuit.CircuitMod.Rendering.TileEntities.TileEntityCircuitCableRender;
import com.circuit.CircuitMod.TileEntity.TileEntityCircuitBox;
import com.circuit.CircuitMod.TileEntity.TileEntityCircuitCable;
import cpw.mods.fml.client.registry.ClientRegistry;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends ServerProxy  implements ProxyInterface {

    @Override
    public void RegisterRenderThings() {

        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCircuitBox.class, new TileEntityCircuitBoxRender());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCircuitCable.class, new TileEntityCircuitCableRender());

        MinecraftForgeClient.registerItemRenderer(new ItemStack(ModBlocks.CircuitCable).getItem(), new ItemCircuitCableRender());
        MinecraftForgeClient.registerItemRenderer(new ItemStack(ModBlocks.CircuitBox).getItem(), new ItemCircuitBoxRender());

    }
}
