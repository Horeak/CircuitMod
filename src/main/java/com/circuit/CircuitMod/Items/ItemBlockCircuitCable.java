package com.circuit.CircuitMod.Items;

import com.circuit.CircuitMod.Main.ModBlocks;
import com.circuit.CircuitMod.TileEntity.TileEntityCircuitCable;
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

public class ItemBlockCircuitCable extends ItemBlock {
    public ItemBlockCircuitCable(Block p_i45328_1_) {
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
        return onItemUse(stack, player, world, pos, side, hitX, hitY, hitZ);
    }

    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
    {

      EnumFacing dir = side.getOpposite();

        if(dir == EnumFacing.UP || dir == EnumFacing.DOWN)
            dir = dir.getOpposite();

        int xCord = pos.getX() - dir.getFrontOffsetX(), yCord = pos.getY() - dir.getFrontOffsetY(), zCord = pos.getZ() - dir.getFrontOffsetZ();

        if(dir == EnumFacing.DOWN){
            xCord = pos.getX();
            yCord = pos.getY() - 1;
            zCord = pos.getZ();
        }else if(dir == EnumFacing.UP){
            xCord = pos.getX();
            yCord = pos.getY() + 1;
            zCord = pos.getZ();
        }

        BlockPos newPos = new BlockPos(xCord, yCord, zCord);

        if(world.isSideSolid(pos, dir) && ModBlocks.CircuitCable.canPlaceBlockAt(world, newPos)){
           world.setTileEntity(newPos, new TileEntityCircuitCable());

        if(world.getTileEntity(newPos) instanceof TileEntityCircuitCable) {
            ((TileEntityCircuitCable) world.getTileEntity(newPos)).Direction = dir;
            ((TileEntityCircuitCable) world.getTileEntity(newPos)).Color = stack.getItemDamage();
        }

            if(!player.capabilities.isCreativeMode)
            player.inventory.getCurrentItem().stackSize -= 1;

            return true;

        }

       return false;
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List list, boolean p_77624_4_) {

        list.add(StatCollector.translateToLocal("item.desc.differentcolorsnoconnect"));
    }
}
