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
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ItemBlockCableConnectionPoint extends ItemBlock {
    public ItemBlockCableConnectionPoint(Block p_i45328_1_) {
        super(p_i45328_1_);
    }

    public String getItemStackDisplayName(ItemStack stack)
    {

        int Meta = stack.getItemDamage();

        if(Meta > 0){

            return StatCollector.translateToLocal("item.fireworksCharge." + EnumDyeColor.func_176766_a(Meta).func_176762_d()) + " " + ("" + StatCollector.translateToLocal(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim();
        }

        return ("" + StatCollector.translateToLocal(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim();
    }

    public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, IBlockState newState)
    {

        if (!world.setBlockState(pos, block.getDefaultState()))
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

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List list, boolean p_77624_4_) {

        list.add(StatCollector.translateToLocal("item.desc.changeblockcolorfromcable.half1"));
        list.add(StatCollector.translateToLocal("item.desc.changeblockcolorfromcable.half2"));
    }
}
