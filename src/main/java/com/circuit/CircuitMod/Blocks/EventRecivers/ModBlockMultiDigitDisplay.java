package com.circuit.CircuitMod.Blocks.EventRecivers;

import MiscUtils.Block.ModBlockCustomModel;
import com.circuit.CircuitMod.TileEntity.EventReceivers.TileEntityMultiDigitDisplay;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ModBlockMultiDigitDisplay extends ModBlockCustomModel {



    public ModBlockMultiDigitDisplay() {
        super(Material.iron);
        setHardness(1F);
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEntityMultiDigitDisplay();
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


        if(world.getTileEntity(x,y,z) instanceof TileEntityMultiDigitDisplay){
            TileEntityMultiDigitDisplay tile = (TileEntityMultiDigitDisplay)world.getTileEntity(x,y,z);
            tile.Rotation = g;
        }
    }
}