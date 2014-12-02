package com.circuit.CircuitMod.Blocks.EventRecivers;

import MiscUtils.Block.ModBlockContainer;
import com.circuit.CircuitMod.TileEntity.EventReceivers.TileEntitySignalShortender;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ModBlockSignalShortender extends ModBlockContainer {

    public ModBlockSignalShortender() {
        super(Material.iron);
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEntitySignalShortender();
    }

    //TODO Fix
//    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack)
//    {
//
//        int g = BlockPistonBase.determineOrientation(world, x, y, z, par5EntityLivingBase);
//
//        if(world.getTileEntity(x,y,z) instanceof TileEntitySignalShortender){
//            TileEntitySignalShortender tile = (TileEntitySignalShortender)world.getTileEntity(x,y,z);
//            tile.dir = EnumFacing.getOrientation(g);
//        }
//    }
}
