package com.circuit.CircuitMod.Blocks.EventSenders;

import MiscUtils.Block.ModBlockContainer;
import com.circuit.CircuitMod.TileEntity.EventSenders.TileEntityRedstoneReciver;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ModBlockRedstoneReciver extends ModBlockContainer {
    public ModBlockRedstoneReciver() {
        super(Material.iron);
        setHardness(1F);
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEntityRedstoneReciver();
    }
}
