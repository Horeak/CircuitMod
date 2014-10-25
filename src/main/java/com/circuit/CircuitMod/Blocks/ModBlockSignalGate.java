package com.circuit.CircuitMod.Blocks;

import MiscUtils.Block.ModBlockContainer;
import com.circuit.CircuitMod.TileEntity.TileEntitySignalGate;
import com.circuit.CircuitMod.Utils.Ref;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class ModBlockSignalGate extends ModBlockContainer {


    IIcon Front, Back, Green, Red;


    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
        this.Front = par1IconRegister.registerIcon(Ref.ModId + ":" + "PacketGateFront");
        this.Back = par1IconRegister.registerIcon(Ref.ModId + ":" + "PacketGateBlank");
        this.Green = par1IconRegister.registerIcon(Ref.ModId + ":" + "PacketGateGreen");
        this.Red = par1IconRegister.registerIcon(Ref.ModId + ":" + "PacketGateRed");

    }


    @Override
    public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side) {
        ForgeDirection dir = ForgeDirection.getOrientation(world.getBlockMetadata(x,y,z));
        ForgeDirection green = ForgeDirection.UNKNOWN;
        ForgeDirection red;

        if (side == dir.ordinal())
            return Front;



        if(dir == ForgeDirection.NORTH)
            green = ForgeDirection.EAST;

        if(dir == ForgeDirection.WEST)
            green = ForgeDirection.NORTH;

        if(dir == ForgeDirection.EAST)
            green = ForgeDirection.SOUTH;

        if(dir == ForgeDirection.SOUTH)
            green = ForgeDirection.WEST;

        if(dir == ForgeDirection.DOWN || dir == ForgeDirection.UP)
            green = ForgeDirection.WEST;

        red = green.getOpposite();



        if(side == green.ordinal())
            return Green;

        if(side == red.ordinal())
            return Red;




   return Back;

    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {

        if (side == 4)
            return Front;

        if(side == 3)
            return Green;

        if(side == 2)
            return Red;

         return Back;

    }

    @SideOnly(Side.CLIENT)
    protected  IIcon getSideIcon(int var1)
    {
        return Back;
    }

    @SideOnly(Side.CLIENT)
    protected IIcon getTopIcon(int p_150161_1_)
    {
        return Front;
    }

    public ModBlockSignalGate() {
        super(Material.iron);
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEntitySignalGate();
    }


    private void func_149930_e(World world, int x, int y, int z)
    {

        Block block = world.getBlock(x, y, z - 1);
        Block block1 = world.getBlock(x, y, z + 1);
        Block block2 = world.getBlock(x - 1, y, z);
        Block block3 = world.getBlock(x + 1, y, z);
        byte b0 = 3;

        if (block.func_149730_j() && !block1.func_149730_j())
        {
            b0 = 3;
        }

        if (block1.func_149730_j() && !block.func_149730_j())
        {
            b0 = 2;
        }

        if (block2.func_149730_j() && !block3.func_149730_j())
        {
            b0 = 5;
        }

        if (block3.func_149730_j() && !block2.func_149730_j())
        {
            b0 = 4;
        }



        world.setBlockMetadataWithNotify(x,y,z,b0,2);

    }

    public void onBlockAdded(World par1World, int par2, int par3, int par4)
    {
        super.onBlockAdded(par1World, par2, par3, par4);
        this.func_149930_e(par1World, par2, par3, par4);
    }
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack)
    {
        int l = MathHelper.floor_double((double) (par5EntityLivingBase.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

        int g = 0;

        if (l == 0)
        {
            g =  2;
        }

        if (l == 1)
        {
            g = 5;
        }

        if (l == 2)
        {
            g = 3;
        }

        if (l == 3)
        {
            g = 4;
        }


        world.setBlockMetadataWithNotify(x,y,z,g,2);
    }
}
