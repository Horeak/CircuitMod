package com.circuit.CircuitMod.Blocks.EventSenders;

import MiscUtils.Block.ModBlockCustomModel;
import com.circuit.CircuitMod.TileEntity.EventSenders.TileEntityOneDigitCounter;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ModBlockOneDigitCounter extends ModBlockCustomModel {


    public ModBlockOneDigitCounter() {
        super(Material.iron);
        setHardness(1F);
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEntityOneDigitCounter();
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
    {


        if(player.isSneaking()){

            if(world.getTileEntity(x,y,z) instanceof TileEntityOneDigitCounter){
                TileEntityOneDigitCounter tile = (TileEntityOneDigitCounter)world.getTileEntity(x,y,z);

                if(tile.ResetAt >= TileEntityOneDigitCounter.DefReset){
                    tile.ResetAt = 1;
                }else{
                    tile.ResetAt += 1;
                }
            }


        }



        return false;
    }
}
