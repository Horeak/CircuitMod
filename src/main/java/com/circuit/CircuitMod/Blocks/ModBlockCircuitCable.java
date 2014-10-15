package com.circuit.CircuitMod.Blocks;

import MiscUtils.Block.ModBlockCustomModel;
import com.circuit.CircuitMod.TileEntity.TileEntityCircuitCable;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.List;

public class ModBlockCircuitCable extends ModBlockCustomModel {


    public ModBlockCircuitCable() {
        super(Material.iron);
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEntityCircuitCable();
    }

    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item p_149666_1_, CreativeTabs p_149666_2_, List p_149666_3_)
    {

        for (int i = 0; i < 16; ++i)
        {
            p_149666_3_.add(new ItemStack(p_149666_1_, 1, i));
        }
    }

    public void setBlockBoundsBasedOnState(IBlockAccess block, int x, int y, int z) {



        if(block.getTileEntity(x,y,z) instanceof TileEntityCircuitCable){
            TileEntityCircuitCable tile = (TileEntityCircuitCable)block.getTileEntity(x,y,z);

            if(tile.Direction == ForgeDirection.DOWN) {
                setBlockBounds(0, 0.8F, 0, 1F, 1F, 1F);

            }else if(tile.Direction == ForgeDirection.NORTH){
                setBlockBounds(0F, 0F, 0F, 1F, 1F, 0.2F);

            }else if(tile.Direction == ForgeDirection.SOUTH){
                setBlockBounds(0F, 0F, 0.8F, 1F, 1F, 1F);

            }else if(tile.Direction == ForgeDirection.WEST){
                setBlockBounds(0F, 0F, 0F, 0.2F, 1F, 1F);

            }else if(tile.Direction == ForgeDirection.EAST){
                setBlockBounds(0.8F, 0F, 0F, 1F, 1F, 1F);

            }else{
                setBlockBounds(0F, 0F, 0F, 1F, 0.2F, 1F);
            }

        }

    }



}
