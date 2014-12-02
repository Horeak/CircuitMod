package com.circuit.CircuitMod.Items;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ItemBlockLamp extends ItemBlock {
    public ItemBlockLamp(Block p_i45328_1_) {
        super(p_i45328_1_);
    }

    public String getItemStackDisplayName(ItemStack stack)
    {

            return EnumDyeColor.func_176764_b(stack.getMetadata()).func_176762_d() + " " + ("" + StatCollector.translateToLocal(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim();

    }

    public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, IBlockState newState)
    {

        if (!world.setBlockState(pos, newState))
        {
            return false;
        }

        if (world.getBlockState(pos).getBlock() == block)
        {
            block.onBlockPlacedBy(world, pos, newState, player, stack);
        }


        world.setBlockState(pos, block.getStateFromMeta(stack.getItemDamage()));
        return true;
    }

}
