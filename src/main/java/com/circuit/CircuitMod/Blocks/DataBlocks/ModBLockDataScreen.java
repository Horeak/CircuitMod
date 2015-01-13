package com.circuit.CircuitMod.Blocks.DataBlocks;

import MiscUtils.Block.ModBlockContainer;
import com.circuit.CircuitMod.TileEntity.DataBlocks.TileEntityDataScreen;
import com.circuit.CircuitMod.Utils.Ref;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class ModBlockDataScreen extends ModBlockContainer {

    public ModBlockDataScreen() {
        super(Material.iron);
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEntityDataScreen();
    }

    IIcon front, Iconside;


    @SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {

        front = par1IconRegister.registerIcon(Ref.ModId + ":" + "datascreen/DataScreenStandard");
        Iconside = par1IconRegister.registerIcon(Ref.ModId + ":" + "DataTransmitterSides");
        blockIcon = Iconside;

    }

    @Override
    public boolean isOpaqueCube(){
        return false;
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side)
    {
        int meta = world.getBlockMetadata(x,y,z);
        return side == meta ? front : Iconside;

    }

    @Override
    public IIcon getIcon(int side, int metadata)
    {

        return side == 4 ? front : Iconside;
    }

    public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack)
    {
        int l = MathHelper.floor_double((double) (par5EntityLivingBase.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;


        if (l == 0)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 2, 2);
        }

        if (l == 1)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 5, 2);
        }

        if (l == 2)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 3, 2);
        }

        if (l == 3)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 4, 2);
        }

    }
}
