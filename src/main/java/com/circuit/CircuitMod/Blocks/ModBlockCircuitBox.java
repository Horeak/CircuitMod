package com.circuit.CircuitMod.Blocks;

import MiscUtils.Block.ModBlockCustomModel;
import com.circuit.CircuitMod.Main.ModBlocks;
import com.circuit.CircuitMod.TileEntity.TileEntityCircuitBox;
import com.circuit.CircuitMod.Utils.CircuitBoxModeUtils;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;


public class ModBlockCircuitBox extends ModBlockCustomModel {
    public ModBlockCircuitBox() {
        super(Material.ground);

        setHardness(0.1F);
        float f = 0.25F;

        this.setBlockBounds(f, 0.0F, f, 1F - f, 0.87F, 1F - f);
    }

    public boolean canPlaceBlockAt(World world, BlockPos pos)
    {

        return canBlockStay(world, pos);
    }


    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEntityCircuitBox();
    }

    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        if(worldIn.getTileEntity(pos) instanceof TileEntityCircuitBox){
            ((TileEntityCircuitBox)worldIn.getTileEntity(pos)).Rotation = placer.func_174811_aO().getOpposite().getIndex();
        }

    }


    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float hitX, float hitY, float hitZ) {

            if (player.inventory.getCurrentItem() != null && player.inventory.getCurrentItem().getItem() == Item.getItemFromBlock(ModBlocks.CircuitCable)) {
                if (world.getTileEntity(pos) instanceof TileEntityCircuitBox) {

                    TileEntity te = world.getTileEntity(pos);
                    if(te instanceof TileEntityCircuitBox){
                        TileEntityCircuitBox tile = (TileEntityCircuitBox)te;

                        tile.Color = player.inventory.getCurrentItem().getItemDamage();
                        world.notifyNeighborsOfStateChange(pos, world.getBlockState(pos).getBlock());



                    }
                    return true;

                }

            } else if (player.isSneaking()) {

                if (world.getTileEntity(pos) instanceof TileEntityCircuitBox) {
                    TileEntityCircuitBox tile = (TileEntityCircuitBox) world.getTileEntity(pos);

                    if (tile.ModeNum < (CircuitBoxModeUtils.Modes.size() - 1)) {
                        tile.ModeNum += 1;

                    } else if (tile.ModeNum >= (CircuitBoxModeUtils.Modes.size() - 1)) {
                        tile.ModeNum = 0;

                    }

                    tile.SetMode(CircuitBoxModeUtils.Modes.get(tile.ModeNum));
                }


            }


            return false;

    }



    public boolean canBlockStay(World world, BlockPos pos)
    {
            if(!world.isSideSolid(new BlockPos(pos.getX(), pos.getY()-1, pos.getZ()), EnumFacing.UP)){
                return false;
        }

        return true;
    }

    public void onNeighborChange(IBlockAccess world, BlockPos pos, BlockPos pos2)
    {
        onNeighborBlockChange((World)world, pos, world.getBlockState(pos), world.getBlockState(pos2).getBlock());

    }

    public void onNeighborBlockChange(World world, BlockPos pos, IBlockState state, Block neighborBlock){
        if(!canBlockStay((World)world, pos)){
            world.getBlockState(pos).getBlock().dropBlockAsItem((World)world, pos, world.getBlockState(pos), 1);
            ((World) world).setBlockState(pos, Blocks.air.getDefaultState());

        }
    }


}

