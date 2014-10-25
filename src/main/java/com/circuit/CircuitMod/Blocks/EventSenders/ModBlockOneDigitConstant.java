package com.circuit.CircuitMod.Blocks.EventSenders;

import MiscUtils.Block.ModBlockCustomModel;
import com.circuit.CircuitMod.TileEntity.EventSenders.TileEntityOneDigitConstant;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ModBlockOneDigitConstant extends ModBlockCustomModel {


    public ModBlockOneDigitConstant() {
        super(Material.iron);
        setHardness(1F);
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEntityOneDigitConstant();
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
    {


          if(player.isSneaking()){

            if(world.getTileEntity(x,y,z) instanceof TileEntityOneDigitConstant){
                TileEntityOneDigitConstant tile = (TileEntityOneDigitConstant)world.getTileEntity(x,y,z);

                if(tile.Constant >= 9){
                    tile.Constant = 0;
                }else{
                    tile.Constant += 1;
                }
            }


        }



        return false;
    }
}
