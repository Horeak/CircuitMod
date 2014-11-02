package com.circuit.CircuitMod.Items;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ItemBlockLamp extends ItemBlock {
    public ItemBlockLamp(Block p_i45328_1_) {
        super(p_i45328_1_);
    }

    public String getItemStackDisplayName(ItemStack stack)
    {

            return StatCollector.translateToLocal("item.fireworksCharge." + ItemDye.field_150923_a[15 - stack.getItemDamage()]) + " " + ("" + StatCollector.translateToLocal(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim();

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

}
