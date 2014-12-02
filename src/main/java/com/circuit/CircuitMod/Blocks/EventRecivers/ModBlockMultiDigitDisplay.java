package com.circuit.CircuitMod.Blocks.EventRecivers;

import MiscUtils.Block.ModBlockCustomModel;
import com.circuit.CircuitMod.TileEntity.EventReceivers.TileEntityMultiDigitDisplay;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModBlockMultiDigitDisplay extends ModBlockCustomModel {



    public ModBlockMultiDigitDisplay() {
        super(Material.iron);
        setHardness(1F);
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEntityMultiDigitDisplay();
    }


    public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
    public IBlockState getStateFromMeta(int meta)
    {
        EnumFacing enumfacing = EnumFacing.getFront(meta);

        if (enumfacing.getAxis() == EnumFacing.Axis.Y)
        {
            enumfacing = EnumFacing.NORTH;
        }

        return this.getDefaultState().withProperty(FACING, enumfacing);
    }

    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
    {
        this.func_176445_e(worldIn, pos, state);
    }

    private void func_176445_e(World worldIn, BlockPos p_176445_2_, IBlockState p_176445_3_)
    {
        if (!worldIn.isRemote)
        {
            Block block = worldIn.getBlockState(p_176445_2_.offsetNorth()).getBlock();
            Block block1 = worldIn.getBlockState(p_176445_2_.offsetSouth()).getBlock();
            Block block2 = worldIn.getBlockState(p_176445_2_.offsetWest()).getBlock();
            Block block3 = worldIn.getBlockState(p_176445_2_.offsetEast()).getBlock();
            EnumFacing enumfacing = (EnumFacing)p_176445_3_.getValue(FACING);

            if (enumfacing == EnumFacing.NORTH && block.isFullBlock() && !block1.isFullBlock())
            {
                enumfacing = EnumFacing.SOUTH;
            }
            else if (enumfacing == EnumFacing.SOUTH && block1.isFullBlock() && !block.isFullBlock())
            {
                enumfacing = EnumFacing.NORTH;
            }
            else if (enumfacing == EnumFacing.WEST && block2.isFullBlock() && !block3.isFullBlock())
            {
                enumfacing = EnumFacing.EAST;
            }
            else if (enumfacing == EnumFacing.EAST && block3.isFullBlock() && !block2.isFullBlock())
            {
                enumfacing = EnumFacing.WEST;
            }

            worldIn.setBlockState(p_176445_2_, p_176445_3_.withProperty(FACING, enumfacing), 2);
        }
    }


    @SideOnly(Side.CLIENT)
    public IBlockState getStateForEntityRender(IBlockState state)
    {
        return this.getDefaultState().withProperty(FACING, EnumFacing.SOUTH);
    }

    public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        return this.getDefaultState().withProperty(FACING, placer.func_174811_aO().getOpposite());
    }
}