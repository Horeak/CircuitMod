package com.circuit.CircuitMod.Blocks.DataBlocks;

import MiscUtils.Block.ModBlockContainer;
import com.circuit.CircuitMod.TileEntity.DataBlocks.TileEntityDataChatOutput;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ModBlockDataChatOutput extends ModBlockContainer {


    public ModBlockDataChatOutput() {
        super(Material.iron);
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEntityDataChatOutput();
    }
}
