package com.circuit.CircuitMod.Blocks.EventRecivers;

import MiscUtils.Block.ModBlockCustomModel;
import com.circuit.CircuitMod.TileEntity.EventReceivers.TileEntityOneDigitDisplay;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ModBlockOneDigitDisplay extends ModBlockCustomModel{



    public ModBlockOneDigitDisplay() {
        super(Material.iron);
        setHardness(1F);
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEntityOneDigitDisplay();
    }

    private void func_149930_e(World world, int x, int y, int z)
    {

        Block block = world.getBlock(x, y, z - 1);
        Block block1 = world.getBlock(x, y, z + 1);
        Block block2 = world.getBlock(x - 1, y, z);
        Block block3 = world.getBlock(x + 1, y, z);
        byte b0 = 3;

        if (block.func_149730_j() && !block1.func_149730_j())
        {
            b0 = 3;
        }

        if (block1.func_149730_j() && !block.func_149730_j())
        {
            b0 = 2;
        }

        if (block2.func_149730_j() && !block3.func_149730_j())
        {
            b0 = 5;
        }

        if (block3.func_149730_j() && !block2.func_149730_j())
        {
            b0 = 4;
        }



        if(world.getTileEntity(x,y,z) instanceof TileEntityOneDigitDisplay){
            TileEntityOneDigitDisplay tile = (TileEntityOneDigitDisplay)world.getTileEntity(x,y,z);
            tile.Rotation = b0;
        }

    }

    public void onBlockAdded(World par1World, int par2, int par3, int par4)
    {
        super.onBlockAdded(par1World, par2, par3, par4);
        this.func_149930_e(par1World, par2, par3, par4);
    }
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack)
    {
        int l = MathHelper.floor_double((double) (par5EntityLivingBase.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

        int g = 0;

        if (l == 0)
        {
            g =  2;
        }

        if (l == 1)
        {
            g = 5;
        }

        if (l == 2)
        {
            g = 3;
        }

        if (l == 3)
        {
            g = 4;
        }


        if(world.getTileEntity(x,y,z) instanceof TileEntityOneDigitDisplay){
            TileEntityOneDigitDisplay tile = (TileEntityOneDigitDisplay)world.getTileEntity(x,y,z);
            tile.Rotation = g;
        }
    }
}
