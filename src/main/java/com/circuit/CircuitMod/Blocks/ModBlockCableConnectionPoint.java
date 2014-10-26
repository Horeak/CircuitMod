package com.circuit.CircuitMod.Blocks;

import MiscUtils.Block.ModBlockContainer;
import com.circuit.CircuitMod.Main.ModBlocks;
import com.circuit.CircuitMod.TileEntity.TileEntityCableConnectionPoint;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.awt.*;

public class ModBlockCableConnectionPoint extends ModBlockContainer{


    public int damageDropped(int meta)
    {
        return meta;
    }

    public int getDamageValue(World world, int x, int y, int z)
    {
        return world.getBlockMetadata(x,y,z);
    }

    public ModBlockCableConnectionPoint() {
        super(Material.iron);
        setHardness(1F);
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEntityCableConnectionPoint();
    }

    @SideOnly(Side.CLIENT)
    public int getRenderColor(int meta)
    {
        if(meta > 0){
            return new Color(ItemDye.field_150922_c[15 - meta]).getRGB();
        }

        return 16777215;
    }

    @SideOnly(Side.CLIENT)
    public int colorMultiplier(IBlockAccess world, int x, int y, int z)
    {
        int meta = world.getBlockMetadata(x,y,z);


        return getRenderColor(meta);
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
        if (player.inventory.getCurrentItem() != null && player.inventory.getCurrentItem().getItem() == Item.getItemFromBlock(ModBlocks.CircuitCable)) {
                world.setBlockMetadataWithNotify(x, y, z, player.inventory.getCurrentItem().getItemDamage(), 2);


                return true;



        }

        return false;
    }


  }
