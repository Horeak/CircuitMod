package com.circuit.CircuitMod.Blocks.EventRecivers;

import MiscUtils.Block.ModBlockContainer;
import com.circuit.CircuitMod.TileEntity.EventReceivers.TileEntitySignalShortender;
import com.circuit.CircuitMod.Utils.Ref;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class ModBlockSignalShortender extends ModBlockContainer {

    IIcon front, Side;

    public ModBlockSignalShortender() {
        super(Material.iron);
    }

    @SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
        front = par1IconRegister.registerIcon(Ref.ModId + ":" + "SignalShortenderFront");
        Side = par1IconRegister.registerIcon(Ref.ModId + ":" + "DigitEqualsBack");

    }


    @Override
    public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side) {

        if(world.getTileEntity(x,y,z) instanceof TileEntitySignalShortender) {
            TileEntitySignalShortender tile = (TileEntitySignalShortender)world.getTileEntity(x,y,z);


            if(side == tile.dir.ordinal())
                return front;

        }

        return Side;

    }

    @Override
    @SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
    public IIcon getIcon(int side, int meta) {

        if (side == 4)
            return front;

        return Side;

    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEntitySignalShortender();
    }

    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack)
    {

        int g = BlockPistonBase.determineOrientation(world, x, y, z, par5EntityLivingBase);

        if(world.getTileEntity(x,y,z) instanceof TileEntitySignalShortender){
            TileEntitySignalShortender tile = (TileEntitySignalShortender)world.getTileEntity(x,y,z);
            tile.dir = ForgeDirection.getOrientation(g);
        }
    }
}
