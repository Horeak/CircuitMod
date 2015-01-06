package com.circuit.CircuitMod.Blocks;

import MiscUtils.Block.ModBlockCustomModel;
import MiscUtils.Handlers.ChatMessageHandler;
import com.circuit.CircuitMod.Main.CircuitMod;
import com.circuit.CircuitMod.Main.ModBlocks;
import com.circuit.CircuitMod.TileEntity.TileEntityCircuitBox;
import com.circuit.CircuitMod.Utils.CircuitBoxModeUtils;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;


public class ModBlockCircuitBox extends ModBlockCustomModel {
    public ModBlockCircuitBox() {
        super(Material.ground);

        setHardness(0.1F);
        float f = 0.25F;

        this.setBlockBounds(f, 0.0F, f, 1F - f, 0.87F, 1F - f);
    }

    public boolean canPlaceBlockAt(World world, int x, int y, int z)
    {

        return canBlockStay(world,x,y,z);
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

                if(!CircuitMod.ShowHoverText){
                    ChatMessageHandler.sendChatToPlayer(player, world.getBlock(x,y,z).getLocalizedName() + ": " + CircuitBoxModeUtils.Modes.get(tile.ModeNum).ModeName());
                }

                tile.SetMode(CircuitBoxModeUtils.Modes.get(tile.ModeNum));
            }


        }



        return false;
    }



    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack)
    {

        if(!canBlockStay(world,x,y,z)) {
            onNeighborBlockChange(world,x,y,z,world.getBlock(x,y,z));
            return;
        }

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

    public boolean canBlockStay(World world, int x, int y, int z)
    {
            if(!world.isSideSolid(x, y-1, z, ForgeDirection.UP)){
                return false;
        }

        return true;
    }

    public void onNeighborChange(IBlockAccess world, int x, int y, int z, int tileX, int tileY, int tileZ)
    {
        onNeighborBlockChange((World)world, x, y, z, world.getBlock(x,y,z));

    }

    public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
        if(!canBlockStay((World)world, x,y,z)){
            world.getBlock(x,y,z).dropBlockAsItem((World)world, x,y,z, world.getBlockMetadata(x,y,z), 1);
            ((World) world).setBlock(x,y,z, Blocks.air, 0, 2);

        }
    }

}

