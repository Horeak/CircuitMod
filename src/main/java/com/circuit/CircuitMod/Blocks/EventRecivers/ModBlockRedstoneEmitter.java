package com.circuit.CircuitMod.Blocks.EventRecivers;

import MiscUtils.Block.ModBlockContainer;
import com.circuit.CircuitMod.TileEntity.EventReceivers.TileEntityRedstoneEmitter;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class ModBlockRedstoneEmitter extends ModBlockContainer {
    public ModBlockRedstoneEmitter() {
        super(Material.iron);
        setHardness(1F);
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEntityRedstoneEmitter();
    }

    public int isProvidingWeakPower(IBlockAccess block, BlockPos pos, IBlockState state, EnumFacing side)
    {
        if(block.getTileEntity(pos) instanceof TileEntityRedstoneEmitter){
            return (((TileEntityRedstoneEmitter) block.getTileEntity(pos)).Do < ((TileEntityRedstoneEmitter) block.getTileEntity(pos)).Finish ? 15 : 0);
        }


        return 0;
    }

    public boolean canProvidePower()
    {
        return true;
    }

}
