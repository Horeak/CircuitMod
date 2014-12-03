package com.circuit.CircuitMod.Blocks;

import MiscUtils.Block.ModBlockCustomModel;
import com.circuit.CircuitMod.Main.ModBlocks;
import com.circuit.CircuitMod.TileEntity.TileEntityCircuitCable;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ModBlockCircuitCable extends ModBlockCustomModel {

    public ModBlockCircuitCable() {
        super(Material.ground);
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

    public boolean canBlockStay(World world, BlockPos pos)
    {

        if(world.getTileEntity(pos) instanceof TileEntityCircuitCable){
            TileEntityCircuitCable tile = (TileEntityCircuitCable)world.getTileEntity(pos);

            EnumFacing dir = tile.Direction;

            if(dir == EnumFacing.UP || dir == EnumFacing.DOWN)
                dir = dir.getOpposite();

            int xCord = pos.getX() + dir.getFrontOffsetX();
            int yCord = pos.getY() + dir.getFrontOffsetY();
            int zCord = pos.getZ() + dir.getFrontOffsetZ();

            if(!world.isSideSolid(new BlockPos(xCord, yCord, zCord), dir)){
                return false;
            }

        }

        return true;
    }


    public void onNeighborBlockChange(World world, BlockPos pos, IBlockState state, Block block) {
        if(world.getBlockState(pos).getBlock() == ModBlocks.CircuitCable) {
            if (!canBlockStay((World) world, pos)) {
                world.getBlockState(pos).getBlock().dropBlockAsItem((World) world, pos, world.getBlockState(pos), 1);
                ((World) world).setBlockState(pos, Blocks.air.getDefaultState());

            }
        }
    }



    public void setBlockBoundsBasedOnState(IBlockAccess block, BlockPos pos) {



        if(block.getTileEntity(pos) instanceof TileEntityCircuitCable){
            TileEntityCircuitCable tile = (TileEntityCircuitCable)block.getTileEntity(pos);

            if(tile.Direction == EnumFacing.DOWN) {
                setBlockBounds(0, 0.8F, 0, 1F, 1F, 1F);

            }else if(tile.Direction == EnumFacing.NORTH){
                setBlockBounds(0F, 0F, 0F, 1F, 1F, 0.2F);

            }else if(tile.Direction == EnumFacing.SOUTH){
                setBlockBounds(0F, 0F, 0.8F, 1F, 1F, 1F);

            }else if(tile.Direction == EnumFacing.WEST){
                setBlockBounds(0F, 0F, 0F, 0.2F, 1F, 1F);

            }else if(tile.Direction == EnumFacing.EAST){
                setBlockBounds(0.8F, 0F, 0F, 1F, 1F, 1F);

            }else{
                setBlockBounds(0F, 0F, 0F, 1F, 0.2F, 1F);
            }

        }

    }



}
