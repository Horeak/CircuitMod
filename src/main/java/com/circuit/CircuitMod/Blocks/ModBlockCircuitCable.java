package com.circuit.CircuitMod.Blocks;

import MiscUtils.Block.ModBlockCustomModel;
import com.circuit.CircuitMod.Main.ModBlocks;
import com.circuit.CircuitMod.TileEntity.TileEntityCircuitCable;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.List;

public class ModBlockCircuitCable extends ModBlockCustomModel {

    public int damageDropped(int meta)
    {
        return meta;
    }

    public int getDamageValue(World world, int x, int y, int z)
    {
        return world.getBlockMetadata(x,y,z);
    }

    public ModBlockCircuitCable() {
        super(Material.ground);
        setTickRandomly(true);
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

    public boolean canBlockStay(World world, int x, int y, int z)
    {

        if(world.getTileEntity(x,y,z) instanceof TileEntityCircuitCable){
            TileEntityCircuitCable tile = (TileEntityCircuitCable)world.getTileEntity(x,y,z);

            ForgeDirection dir = tile.Direction;

            if(dir == ForgeDirection.UP || dir == ForgeDirection.DOWN)
                dir = dir.getOpposite();

            int xCord = x + dir.offsetX;
            int yCord = y + dir.offsetY;
            int zCord = z + dir.offsetZ;

            if(!world.isSideSolid(xCord, yCord, zCord, dir)){
                return false;
            }

        }

        return true;
    }


    public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
        if(world.getBlock(x,y,z) == ModBlocks.CircuitCable) {
            if (!canBlockStay((World) world, x, y, z)) {
                world.getBlock(x, y, z).dropBlockAsItem((World) world, x, y, z, world.getBlockMetadata(x, y, z), 1);
                ((World) world).setBlock(x, y, z, Blocks.air, 0, 2);

            }
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
