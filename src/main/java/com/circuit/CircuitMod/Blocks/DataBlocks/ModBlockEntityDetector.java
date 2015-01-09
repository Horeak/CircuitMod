package com.circuit.CircuitMod.Blocks.DataBlocks;

import MiscUtils.Block.ModBlockCustomModel;
import com.circuit.CircuitMod.Main.ModBlocks;
import com.circuit.CircuitMod.TileEntity.DataBlocks.TileEntityEntityDetector;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class ModBlockEntityDetector extends ModBlockCustomModel{


    public ModBlockEntityDetector() {
        super(Material.iron);
        this.setBlockBounds(0.2F, 0.0F, 0.2F, 0.8F, 0.8F, 0.8F);
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEntityEntityDetector();
    }

    public boolean canBlockStay(World world, int x, int y, int z)
    {
            if(!world.isSideSolid(x, y-1, z, ForgeDirection.UP)){
                return false;
            }


        return true;
    }


    public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
        if(world.getBlock(x,y,z) == ModBlocks.EntityDetector) {
            if (!canBlockStay((World) world, x, y, z)) {
                world.getBlock(x, y, z).dropBlockAsItem((World) world, x, y, z, world.getBlockMetadata(x, y, z), 1);
                ((World) world).setBlock(x, y, z, Blocks.air, 0, 2);

            }
        }
    }

    public boolean canPlaceBlockAt(World world, int x, int y, int z)
    {
        return super.canPlaceBlockAt(world, x, y, z) && canBlockStay(world, x, y, z);
    }

}
