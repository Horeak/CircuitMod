package com.circuit.CircuitMod.Blocks.EventSenders;

import MiscUtils.Block.ModBlockCustomModel;
import com.circuit.CircuitMod.Main.CircuitMod;
import com.circuit.CircuitMod.TileEntity.EventSenders.TileEntityOneDigitConstant;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.internal.FMLNetworkHandler;

public class ModBlockOneDigitConstant extends ModBlockCustomModel {


    public ModBlockOneDigitConstant() {
        super(Material.iron);
        setHardness(1F);
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEntityOneDigitConstant();
    }

    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float hitX, float hitY, float hitZ)
    {

        FMLNetworkHandler.openGui(player, CircuitMod.instance, 0, world, pos.getX(), pos.getY(), pos.getZ());
        return true;

    }
}
