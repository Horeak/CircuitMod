package com.circuit.CircuitMod.Items;

import com.circuit.CircuitMod.Main.ModBlocks;
import com.circuit.CircuitMod.TileEntity.TileEntityCircuitCable;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.List;

public class ItemBlockCircuitCable extends ItemBlock {
    public ItemBlockCircuitCable(Block p_i45328_1_) {
        super(p_i45328_1_);
    }

    public String getItemStackDisplayName(ItemStack stack)
    {

        int Meta = stack.getItemDamage();

        if(Meta > 0){

            if(Meta > 15)
                Meta = 15;

            return StatCollector.translateToLocal("item.fireworksCharge." + ItemDye.field_150923_a[15 - Meta]) + " " + ("" + StatCollector.translateToLocal(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim();
        }

        return ("" + StatCollector.translateToLocal(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim();
    }

    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float p_77648_8_, float p_77648_9_, float p_77648_10_)
    {

      ForgeDirection dir = ForgeDirection.getOrientation(side).getOpposite();
        if(dir == ForgeDirection.UP || dir == ForgeDirection.DOWN)
            dir = dir.getOpposite();

        int xCord = x - dir.offsetX, yCord = y - dir.offsetY, zCord = z - dir.offsetZ;

        if(dir == ForgeDirection.DOWN){
            xCord = x;
            yCord = y - 1;
            zCord = z;
        }else if(dir == ForgeDirection.UP){
            xCord = x;
            yCord = y + 1;
            zCord = z;
        }

        if(world.isSideSolid(x, y, z, dir) && ModBlocks.CircuitCable.canPlaceBlockAt(world, xCord, yCord, zCord)){
            world.setBlock(xCord, yCord, zCord, ModBlocks.CircuitCable, stack.getItemDamage(), 2);
            world.playSoundEffect((double)((float)xCord + 0.5F), (double)((float)yCord + 0.5F), (double)((float)zCord + 0.5F), this.field_150939_a.stepSound.func_150496_b(), (this.field_150939_a.stepSound.getVolume() + 1.0F) / 2.0F, this.field_150939_a.stepSound.getPitch() * 0.8F);
           world.setTileEntity(xCord,yCord,zCord, new TileEntityCircuitCable());

        if(world.getTileEntity(xCord,yCord,zCord) instanceof TileEntityCircuitCable)
          ((TileEntityCircuitCable)world.getTileEntity(xCord,yCord,zCord)).Direction = dir;

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
