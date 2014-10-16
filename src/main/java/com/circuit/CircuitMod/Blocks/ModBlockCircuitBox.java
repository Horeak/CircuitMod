package com.circuit.CircuitMod.Blocks;

import MiscUtils.Block.ModBlockCustomModel;
import com.circuit.CircuitMod.Main.ModBlocks;
import com.circuit.CircuitMod.TileEntity.TileEntityCircuitBox;
import com.circuit.CircuitMod.Utils.CircuitBoxModeUtils;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ModBlockCircuitBox extends ModBlockCustomModel {
    public ModBlockCircuitBox() {
        super(Material.iron);

        float f = 0.25F;

        this.setBlockBounds(f, 0.0F, f, 1F - f, 0.87F, 1F - f);
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEntityCircuitBox();
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
    {
        if(player.inventory.getCurrentItem() != null && player.inventory.getCurrentItem().getItem() == Item.getItemFromBlock(ModBlocks.CircuitCable)) {
            if (world.getTileEntity(x, y, z) instanceof TileEntityCircuitBox) {
                    world.setBlockMetadataWithNotify(x, y, z, player.inventory.getCurrentItem().getItemDamage(), 2);


                    return true;

            }

        }else if(player.isSneaking()){

            if(world.getTileEntity(x,y,z) instanceof TileEntityCircuitBox){
                TileEntityCircuitBox tile = (TileEntityCircuitBox)world.getTileEntity(x,y,z);

                if(tile.ModeNum < (CircuitBoxModeUtils.Modes.size()-1)){
                    tile.ModeNum += 1;

                }else if(tile.ModeNum >= (CircuitBoxModeUtils.Modes.size()-1)){
                    tile.ModeNum = 0;

                }

                tile.SetMode(CircuitBoxModeUtils.Modes.get(tile.ModeNum));
            }


        }



        return false;
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



            if(world.getTileEntity(x,y,z) instanceof TileEntityCircuitBox){
                TileEntityCircuitBox tile = (TileEntityCircuitBox)world.getTileEntity(x,y,z);
                tile.Rotation = b0;
            }

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


        if(world.getTileEntity(x,y,z) instanceof TileEntityCircuitBox){
            TileEntityCircuitBox tile = (TileEntityCircuitBox)world.getTileEntity(x,y,z);
            tile.Rotation = g;
        }
    }

}

