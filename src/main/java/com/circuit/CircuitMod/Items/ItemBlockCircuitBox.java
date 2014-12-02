package com.circuit.CircuitMod.Items;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ItemBlockCircuitBox extends ItemBlock {
    public ItemBlockCircuitBox(Block p_i45328_1_) {
        super(p_i45328_1_);
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List list, boolean p_77624_4_) {

        list.add(StatCollector.translateToLocal("item.desc.changeblockcolorfromcable.half1"));
        list.add(StatCollector.translateToLocal("item.desc.changeblockcolorfromcable.half2"));
    }
}
