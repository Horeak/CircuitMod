package com.circuit.CircuitMod.Blocks.EventRecivers;

import MiscUtils.Block.ModBlockCustomModel;
import com.circuit.CircuitMod.TileEntity.EventReceivers.TileEntityVariable;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ModBlockVariable extends ModBlockCustomModel {
    public ModBlockVariable() {
        super(Material.iron);
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEntityVariable();
    }

}

