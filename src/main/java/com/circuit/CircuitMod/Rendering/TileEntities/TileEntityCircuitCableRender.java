package com.circuit.CircuitMod.Rendering.TileEntities;

import com.circuit.CircuitMod.Rendering.Models.CircuitCableModel;
import com.circuit.CircuitMod.TileEntity.TileEntityCircuitCable;
import com.circuit.CircuitMod.Utils.CircuitUtils.ICircuitConnector;
import com.circuit.CircuitMod.Utils.Ref;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemDye;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class TileEntityCircuitCableRender extends TileEntitySpecialRenderer {


    public final CircuitCableModel model;
    public ResourceLocation rs = new ResourceLocation(Ref.ModId.toLowerCase(), "textures/models/CircuitCable.png");

    public TileEntityCircuitCableRender() {
        this.model = new CircuitCableModel();
    }


    @Override
    public void renderTileEntityAt(TileEntity te, double x, double y, double z, float scale, int f) {

        RenderWithDirection((TileEntityCircuitCable)te, x, y, z);
    }

    public void RenderWithDirection(TileEntityCircuitCable tile, double x, double y, double z){

        GL11.glPushMatrix();

        bindTexture(rs);

        GL11.glPushMatrix();

        GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);

        GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);

        IBlockState meta = tile.getWorld().getBlockState(new BlockPos(tile.getPos().getX(), tile.getPos().getY(), tile.getPos().getZ()));

        int Meta = tile.Color;
        
        if(Meta != 0) {
            Color c = new Color(ItemDye.dyeColors[15 - Meta]);
            float r = (float)c.getRed() / (float)255, g = (float)c.getGreen() / (float)255, b = (float)c.getBlue() / (float)255;
            GL11.glColor4f(r, g, b, 1F);
        }else{
            Color c = TileEntityCircuitCable.BaseColor;
            float r = (float)c.getRed() / (float)255, g = (float)c.getGreen() / (float)255, b = (float)c.getBlue() / (float)255;
            GL11.glColor4f(r, g, b, 1F);
        }
        World world = tile.getWorld();

        EnumFacing direction = tile.Direction;



      if(direction == EnumFacing.DOWN){
            GL11.glTranslatef(0, 2F, 0);
            GL11.glRotatef(180, 1.0F, 0.0F, 0.0F);

        }else if(direction == EnumFacing.WEST){
          GL11.glTranslatef(-1, 1, 0);
            GL11.glRotatef(-90, 0.0F, 0.0F, 1.0F);

        }else if(direction == EnumFacing.EAST){
          GL11.glTranslatef(1, 1, 0);
          GL11.glRotatef(90, 0.0F, 0.0F, 1.0F);

      }else if(direction == EnumFacing.NORTH){
        GL11.glTranslatef(0, 1, 1);
        GL11.glRotatef(180 + 90, 1.0F, 0.0F, 0.0F);

    }else if(direction == EnumFacing.SOUTH){
          GL11.glTranslatef(0, 1, -1);
          GL11.glRotatef(180 - 90, 1.0F, 0.0F, 0.0F);

      }


        //Front = north
        //Back = south

        //Right = east
        //Left = west

        //Front, Back, Right, Left




        if(direction == EnumFacing.UP) {
            this.model.render((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F,
                    Check(world, tile.getPos().getX() + EnumFacing.NORTH.getFrontOffsetX(), tile.getPos().getY() + EnumFacing.NORTH.getFrontOffsetY(), tile.getPos().getZ() + EnumFacing.NORTH.getFrontOffsetZ(), tile),
                    Check(world, tile.getPos().getX() + EnumFacing.SOUTH.getFrontOffsetX(), tile.getPos().getY() + EnumFacing.SOUTH.getFrontOffsetY(), tile.getPos().getZ() + EnumFacing.SOUTH.getFrontOffsetZ(), tile),
                    Check(world, tile.getPos().getX() + EnumFacing.EAST.getFrontOffsetX(), tile.getPos().getY() + EnumFacing.EAST.getFrontOffsetY(), tile.getPos().getZ() + EnumFacing.EAST.getFrontOffsetZ(), tile),
                    Check(world, tile.getPos().getX() + EnumFacing.WEST.getFrontOffsetX(), tile.getPos().getY() + EnumFacing.WEST.getFrontOffsetY(), tile.getPos().getZ() + EnumFacing.WEST.getFrontOffsetZ(), tile));

        }else if(direction == EnumFacing.DOWN){
            this.model.render((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F,
                    Check(world, tile.getPos().getX() + EnumFacing.SOUTH.getFrontOffsetX(), tile.getPos().getY() + EnumFacing.SOUTH.getFrontOffsetY(), tile.getPos().getZ() + EnumFacing.SOUTH.getFrontOffsetZ(), tile),
                    Check(world, tile.getPos().getX() + EnumFacing.NORTH.getFrontOffsetX(), tile.getPos().getY() + EnumFacing.NORTH.getFrontOffsetY(), tile.getPos().getZ() + EnumFacing.NORTH.getFrontOffsetZ(), tile),
                    Check(world, tile.getPos().getX() + EnumFacing.EAST.getFrontOffsetX(), tile.getPos().getY() + EnumFacing.EAST.getFrontOffsetY(), tile.getPos().getZ() + EnumFacing.EAST.getFrontOffsetZ(), tile),
                    Check(world, tile.getPos().getX() + EnumFacing.WEST.getFrontOffsetX(), tile.getPos().getY() + EnumFacing.WEST.getFrontOffsetY(), tile.getPos().getZ() + EnumFacing.WEST.getFrontOffsetZ(), tile));

        }else if(direction == EnumFacing.WEST){
            this.model.render((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F,
                    Check(world, tile.getPos().getX() + EnumFacing.NORTH.getFrontOffsetX(), tile.getPos().getY() + EnumFacing.NORTH.getFrontOffsetY(), tile.getPos().getZ() + EnumFacing.NORTH.getFrontOffsetZ(), tile),
                    Check(world, tile.getPos().getX() + EnumFacing.SOUTH.getFrontOffsetX(), tile.getPos().getY() + EnumFacing.SOUTH.getFrontOffsetY(), tile.getPos().getZ() + EnumFacing.SOUTH.getFrontOffsetZ(), tile),
                    Check(world, tile.getPos().getX() + EnumFacing.DOWN.getFrontOffsetX(), tile.getPos().getY() + EnumFacing.DOWN.getFrontOffsetY(), tile.getPos().getZ() + EnumFacing.DOWN.getFrontOffsetZ(), tile),
                    Check(world, tile.getPos().getX() + EnumFacing.UP.getFrontOffsetX(), tile.getPos().getY() + EnumFacing.UP.getFrontOffsetY(), tile.getPos().getZ() + EnumFacing.UP.getFrontOffsetZ(), tile));

        }else if(direction == EnumFacing.EAST){
            this.model.render((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F,
                    Check(world, tile.getPos().getX() + EnumFacing.NORTH.getFrontOffsetX(), tile.getPos().getY() + EnumFacing.NORTH.getFrontOffsetY(), tile.getPos().getZ() + EnumFacing.NORTH.getFrontOffsetZ(), tile),
                    Check(world, tile.getPos().getX() + EnumFacing.SOUTH.getFrontOffsetX(), tile.getPos().getY() + EnumFacing.SOUTH.getFrontOffsetY(), tile.getPos().getZ() + EnumFacing.SOUTH.getFrontOffsetZ(), tile),
                    Check(world, tile.getPos().getX() + EnumFacing.UP.getFrontOffsetX(), tile.getPos().getY() + EnumFacing.UP.getFrontOffsetY(), tile.getPos().getZ() + EnumFacing.UP.getFrontOffsetZ(), tile),
                    Check(world, tile.getPos().getX() + EnumFacing.DOWN.getFrontOffsetX(), tile.getPos().getY() + EnumFacing.DOWN.getFrontOffsetY(), tile.getPos().getZ() + EnumFacing.DOWN.getFrontOffsetZ(), tile));

        }else if(direction == EnumFacing.NORTH){
            this.model.render((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F,
                    Check(world, tile.getPos().getX() + EnumFacing.UP.getFrontOffsetX(), tile.getPos().getY() + EnumFacing.UP.getFrontOffsetY(), tile.getPos().getZ() + EnumFacing.UP.getFrontOffsetZ(), tile),
                    Check(world, tile.getPos().getX() + EnumFacing.DOWN.getFrontOffsetX(), tile.getPos().getY() + EnumFacing.DOWN.getFrontOffsetY(), tile.getPos().getZ() + EnumFacing.DOWN.getFrontOffsetZ(), tile),
                    Check(world, tile.getPos().getX() + EnumFacing.EAST.getFrontOffsetX(), tile.getPos().getY() + EnumFacing.EAST.getFrontOffsetY(), tile.getPos().getZ() + EnumFacing.EAST.getFrontOffsetZ(), tile),
                    Check(world, tile.getPos().getX() + EnumFacing.WEST.getFrontOffsetX(), tile.getPos().getY() + EnumFacing.WEST.getFrontOffsetY(), tile.getPos().getZ() + EnumFacing.WEST.getFrontOffsetZ(), tile));

        }else if(direction == EnumFacing.SOUTH){
            this.model.render((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F,
                    Check(world, tile.getPos().getX() + EnumFacing.DOWN.getFrontOffsetX(), tile.getPos().getY() + EnumFacing.DOWN.getFrontOffsetY(), tile.getPos().getZ() + EnumFacing.DOWN.getFrontOffsetZ(), tile),
                    Check(world, tile.getPos().getX() + EnumFacing.UP.getFrontOffsetX(), tile.getPos().getY() + EnumFacing.UP.getFrontOffsetY(), tile.getPos().getZ() + EnumFacing.UP.getFrontOffsetZ(), tile),
                    Check(world, tile.getPos().getX() + EnumFacing.EAST.getFrontOffsetX(), tile.getPos().getY() + EnumFacing.EAST.getFrontOffsetY(), tile.getPos().getZ() + EnumFacing.EAST.getFrontOffsetZ(), tile),
                    Check(world, tile.getPos().getX() + EnumFacing.WEST.getFrontOffsetX(), tile.getPos().getY() + EnumFacing.WEST.getFrontOffsetY(), tile.getPos().getZ() + EnumFacing.WEST.getFrontOffsetZ(), tile));

        }

        GL11.glColor4f(1F, 1F, 1F, 1F);

        GL11.glPopMatrix();
        GL11.glPopMatrix();

    }

    public boolean Check(World world, int x, int y, int z, TileEntityCircuitCable tile){
        if(tile instanceof ICircuitConnector) {
            ICircuitConnector ic = (ICircuitConnector) tile;
            BlockPos pos = new BlockPos(x,y,z);

            EnumFacing di = null;

            for(EnumFacing dir : EnumFacing.values()){
                if(tile.getPos().getX() + dir.getFrontOffsetX() == x &&  tile.getPos().getY() + dir.getFrontOffsetY() == y && tile.getPos().getZ() + dir.getFrontOffsetZ() == z){
                    di = dir;
                    break;
                }
            }

            if(world.getTileEntity(pos) instanceof TileEntityCircuitCable){
                TileEntityCircuitCable ci = (TileEntityCircuitCable)world.getTileEntity(pos);
                return world.getTileEntity(pos) instanceof ICircuitConnector && ((ICircuitConnector) world.getTileEntity(pos)).CanConnectToTile(tile, di.getOpposite()) && ic.CanConnectToTile(world.getTileEntity(pos), di) && ci.Direction == tile.Direction;
            }

            return world.getTileEntity(pos) instanceof ICircuitConnector && ((ICircuitConnector) world.getTileEntity(pos)).CanConnectToTile(tile, di.getOpposite()) && ic.CanConnectToTile(world.getTileEntity(pos), di);

        }

   return false;
    }

}


