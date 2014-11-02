package com.circuit.CircuitMod.Blocks.EventRecivers;

import MiscUtils.Block.ModBlockCustomModel;
import com.circuit.CircuitMod.TileEntity.EventReceivers.TileEntityLamp;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import java.util.List;

public class ModBlockLamp extends ModBlockCustomModel {


    public ModBlockLamp() {
        super(Material.iron);
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEntityLamp();
    }

    public int damageDropped(int meta)
    {
        return meta;
    }

    public int getDamageValue(World world, int x, int y, int z)
    {
        return world.getBlockMetadata(x,y,z);
    }


    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item p_149666_1_, CreativeTabs p_149666_2_, List p_149666_3_)
    {

        for (int i = 0; i < 16; ++i)
        {
            p_149666_3_.add(new ItemStack(p_149666_1_, 1, i));
        }
    }
}
