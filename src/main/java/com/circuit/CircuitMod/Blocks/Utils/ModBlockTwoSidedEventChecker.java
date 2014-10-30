package com.circuit.CircuitMod.Blocks.Utils;

import MiscUtils.Block.ModBlockContainer;
import com.circuit.CircuitMod.TileEntity.Utils.TileEntityTwoSidedEventChecker;
import com.circuit.CircuitMod.Utils.Ref;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public abstract class ModBlockTwoSidedEventChecker extends ModBlockContainer {
    protected ModBlockTwoSidedEventChecker(Material p_i45394_1_) {
        super(p_i45394_1_);
    }


    public abstract String FrontIcon();
    public abstract String BackIcon();
    public abstract String SideAIcon();
    public abstract String SideBIcon();

    IIcon Front, Back, SideA, SideB;


    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
        this.Front = par1IconRegister.registerIcon(Ref.ModId + ":" + FrontIcon());
        this.Back = par1IconRegister.registerIcon(Ref.ModId + ":" + BackIcon());
        this.SideA = par1IconRegister.registerIcon(Ref.ModId + ":" + SideAIcon());
        this.SideB = par1IconRegister.registerIcon(Ref.ModId + ":" + SideBIcon());

    }


    @Override
    public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side) {

        if(world.getTileEntity(x,y,z) instanceof TileEntityTwoSidedEventChecker) {
            TileEntityTwoSidedEventChecker tile = (TileEntityTwoSidedEventChecker)world.getTileEntity(x,y,z);

            if (side == tile.GetOutputSide().ordinal())
                return Front;


            if (side == tile.GetDirectionA().ordinal())
                return SideA;

            if (side == tile.GetDirectionB().ordinal())
                return SideB;


        }

        return Back;

    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {

        if (side == 4)
            return Front;

        if(side == 3)
            return SideA;

        if(side == 2)
            return SideB;

        return Back;

    }






    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase player, ItemStack par6ItemStack)
        {


            {
                int j = MathHelper.floor_double((double) (player.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

                int g = 0;

                if (j == 0) {
                    g = 2;
                }

                if (j == 1) {
                    g = 5;
                }

                if (j == 2) {
                    g = 3;
                }

                if (j == 3) {
                    g = 4;
                }

                world.setBlockMetadataWithNotify(x, y, z, g, 2);
            }




            int l = BlockPistonBase.determineOrientation(world,x,y,z,player);
            if (world.getTileEntity(x, y, z) instanceof TileEntityTwoSidedEventChecker) {
                TileEntityTwoSidedEventChecker tile = (TileEntityTwoSidedEventChecker)world.getTileEntity(x,y,z);

            ForgeDirection dir = ForgeDirection.getOrientation(l);

//            if (dir != ForgeDirection.UP && dir != ForgeDirection.DOWN)
//                dir = dir.getOpposite();


               if(tile.dir == ForgeDirection.UNKNOWN) {
                   tile.dir = dir;

      }
    }


    }

}
