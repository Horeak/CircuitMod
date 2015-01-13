package com.circuit.CircuitMod.Blocks.DataBlocks;

import MiscUtils.Block.ModBlockContainer;
import com.circuit.CircuitMod.Main.CircuitMod;
import com.circuit.CircuitMod.TileEntity.DataBlocks.TileEntityDataDecryptor;
import com.circuit.CircuitMod.Utils.Ref;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class ModBlockDataDecryptor extends ModBlockContainer {

    IIcon front, Side;

    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {

        FMLNetworkHandler.openGui(par5EntityPlayer, CircuitMod.instance, 0, par1World, par2, par3, par4);
        return true;

    }

    public ModBlockDataDecryptor() {
        super(Material.iron);
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEntityDataDecryptor();
    }

    @SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
        front = par1IconRegister.registerIcon(Ref.ModId + ":" + "DataDecryptorFront");
        Side = par1IconRegister.registerIcon(Ref.ModId + ":" + "DataTransmitterSides");

    }


    @Override
    public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side) {

        if(world.getTileEntity(x,y,z) instanceof TileEntityDataDecryptor) {
            TileEntityDataDecryptor tile = (TileEntityDataDecryptor)world.getTileEntity(x,y,z);


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


    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack)
    {

        int g = BlockPistonBase.determineOrientation(world, x, y, z, par5EntityLivingBase);

        if(world.getTileEntity(x,y,z) instanceof TileEntityDataDecryptor){
            TileEntityDataDecryptor tile = (TileEntityDataDecryptor)world.getTileEntity(x,y,z);
            tile.dir = ForgeDirection.getOrientation(g);
        }
    }
}
