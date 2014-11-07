package com.circuit.CircuitMod.Items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import java.util.List;

public class ItemBlockCableConnectionPoint extends ItemBlock {
    public ItemBlockCableConnectionPoint(Block p_i45328_1_) {
        super(p_i45328_1_);
    }

    public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata)
    {

        if (!world.setBlock(x, y, z, field_150939_a, metadata, 3))
        {
            return false;
        }

        if (world.getBlock(x, y, z) == field_150939_a)
        {
            field_150939_a.onBlockPlacedBy(world, x, y, z, player, stack);
            field_150939_a.onPostBlockPlaced(world, x, y, z, metadata);
        }


        world.setBlockMetadataWithNotify(x,y,z, stack.getItemDamage(), 2);
        return true;
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List list, boolean p_77624_4_) {

        list.add(StatCollector.translateToLocal("item.desc.changeblockcolorfromcable.half1"));
        list.add(StatCollector.translateToLocal("item.desc.changeblockcolorfromcable.half2"));
    }
}
