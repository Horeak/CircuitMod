package com.circuit.CircuitMod.Blocks;

import MiscUtils.Block.ModBlockContainer;
import com.circuit.CircuitMod.Main.ModBlocks;
import com.circuit.CircuitMod.TileEntity.TileEntityCableConnectionPoint;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.awt.*;

public class ModBlockCableConnectionPoint extends ModBlockContainer{


    public ModBlockCableConnectionPoint() {
        super(Material.iron);
        setHardness(1F);
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEntityCableConnectionPoint();
    }

    @SideOnly(Side.CLIENT)
    public int getRenderColor(IBlockState state)
    {
        if(state != null){
            //TODO FIX?
            return new Color(ItemDye.dyeColors[15 - Integer.parseInt(state.getProperties().get(ModBlockCableConnectionPoint.COLOR).toString())]).getRGB();
        }

        return 16777215;
    }

    @SideOnly(Side.CLIENT)
    public int colorMultiplier(IBlockAccess worldIn, BlockPos pos, int renderPass)
    {
        return getRenderColor(worldIn.getBlockState(pos));
    }

    public static final PropertyInteger COLOR = PropertyInteger.create("color", 0, 16);

    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (playerIn.inventory.getCurrentItem() != null && playerIn.inventory.getCurrentItem().getItem() == Item.getItemFromBlock(ModBlocks.CircuitCable)) {
            worldIn.setBlockState(pos, getDefaultState().withProperty(COLOR, playerIn.inventory.getCurrentItem().getItemDamage()), 2);


                return true;



        }

        return false;
    }

    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(COLOR, meta);
    }

  }
