package com.circuit.CircuitMod.Blocks.EventSenders;

import MiscUtils.Block.ModBlockCustomModel;
import com.circuit.CircuitMod.Main.CircuitMod;
import com.circuit.CircuitMod.TileEntity.EventSenders.TileEntityMultiDigitConstant;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ModBlockMultiDigitConstant extends ModBlockCustomModel {
    public ModBlockMultiDigitConstant() {
        super(Material.iron);
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEntityMultiDigitConstant();
    }


    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {

            FMLNetworkHandler.openGui(par5EntityPlayer, CircuitMod.instance, 0, par1World, par2, par3, par4);
            return true;

        }

}
