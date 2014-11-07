package com.circuit.CircuitMod.Rendering.TileEntities;

import com.circuit.CircuitMod.Rendering.Models.CircuitCableModel;
import com.circuit.CircuitMod.Utils.CircuitUtils.ICircuitConnector;
import com.circuit.CircuitMod.TileEntity.TileEntityCircuitCable;
import com.circuit.CircuitMod.Utils.Ref;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemDye;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class TileEntityCircuitCableRender extends TileEntitySpecialRenderer {


    public final CircuitCableModel model;
    public ResourceLocation rs = new ResourceLocation(Ref.ModId.toLowerCase(), "textures/models/CircuitCable.png");

    public TileEntityCircuitCableRender() {
        this.model = new CircuitCableModel();
    }


    @Override
    public void renderTileEntityAt(TileEntity te, double x, double y, double z, float scale) {

        RenderWithDirection((TileEntityCircuitCable)te, x, y, z);
    }

    public void RenderWithDirection(TileEntityCircuitCable tile, double x, double y, double z){

        GL11.glPushMatrix();

        bindTexture(rs);

        GL11.glPushMatrix();

        GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);

        GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);

        int Meta = tile.getWorldObj().getBlockMetadata(tile.xCoord, tile.yCoord, tile.zCoord);

        if(Meta != 0) {
            Color c = new Color(ItemDye.field_150922_c[15 - Meta]);
            float r = (float)c.getRed() / (float)255, g = (float)c.getGreen() / (float)255, b = (float)c.getBlue() / (float)255;
            GL11.glColor4f(r, g, b, 1F);
        }else{
            Color c = TileEntityCircuitCable.BaseColor;
            float r = (float)c.getRed() / (float)255, g = (float)c.getGreen() / (float)255, b = (float)c.getBlue() / (float)255;
            GL11.glColor4f(r, g, b, 1F);
        }
        World world = tile.getWorldObj();

        ForgeDirection direction = tile.Direction;



      if(direction == ForgeDirection.DOWN){
            GL11.glTranslatef(0, 2F, 0);
            GL11.glRotatef(180, 1.0F, 0.0F, 0.0F);

        }else if(direction == ForgeDirection.WEST){
          GL11.glTranslatef(-1, 1, 0);
            GL11.glRotatef(-90, 0.0F, 0.0F, 1.0F);

        }else if(direction == ForgeDirection.EAST){
          GL11.glTranslatef(1, 1, 0);
          GL11.glRotatef(90, 0.0F, 0.0F, 1.0F);

      }else if(direction == ForgeDirection.NORTH){
        GL11.glTranslatef(0, 1, 1);
        GL11.glRotatef(180 + 90, 1.0F, 0.0F, 0.0F);

    }else if(direction == ForgeDirection.SOUTH){
          GL11.glTranslatef(0, 1, -1);
          GL11.glRotatef(180 - 90, 1.0F, 0.0F, 0.0F);

      }


        //Front = north
        //Back = south

        //Right = east
        //Left = west

        //Front, Back, Right, Left




        if(direction == ForgeDirection.UP) {
            this.model.render((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F,
                    Check(world, tile.xCoord + ForgeDirection.NORTH.offsetX, tile.yCoord + ForgeDirection.NORTH.offsetY, tile.zCoord + ForgeDirection.NORTH.offsetZ, tile),
                    Check(world, tile.xCoord + ForgeDirection.SOUTH.offsetX, tile.yCoord + ForgeDirection.SOUTH.offsetY, tile.zCoord + ForgeDirection.SOUTH.offsetZ, tile),
                    Check(world, tile.xCoord + ForgeDirection.EAST.offsetX, tile.yCoord + ForgeDirection.EAST.offsetY, tile.zCoord + ForgeDirection.EAST.offsetZ, tile),
                    Check(world, tile.xCoord + ForgeDirection.WEST.offsetX, tile.yCoord + ForgeDirection.WEST.offsetY, tile.zCoord + ForgeDirection.WEST.offsetZ, tile));

        }else if(direction == ForgeDirection.DOWN){
            this.model.render((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F,
                    Check(world, tile.xCoord + ForgeDirection.SOUTH.offsetX, tile.yCoord + ForgeDirection.SOUTH.offsetY, tile.zCoord + ForgeDirection.SOUTH.offsetZ, tile),
                    Check(world, tile.xCoord + ForgeDirection.NORTH.offsetX, tile.yCoord + ForgeDirection.NORTH.offsetY, tile.zCoord + ForgeDirection.NORTH.offsetZ, tile),
                    Check(world, tile.xCoord + ForgeDirection.EAST.offsetX, tile.yCoord + ForgeDirection.EAST.offsetY, tile.zCoord + ForgeDirection.EAST.offsetZ, tile),
                    Check(world, tile.xCoord + ForgeDirection.WEST.offsetX, tile.yCoord + ForgeDirection.WEST.offsetY, tile.zCoord + ForgeDirection.WEST.offsetZ, tile));

        }else if(direction == ForgeDirection.WEST){
            this.model.render((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F,
                    Check(world, tile.xCoord + ForgeDirection.NORTH.offsetX, tile.yCoord + ForgeDirection.NORTH.offsetY, tile.zCoord + ForgeDirection.NORTH.offsetZ, tile),
                    Check(world, tile.xCoord + ForgeDirection.SOUTH.offsetX, tile.yCoord + ForgeDirection.SOUTH.offsetY, tile.zCoord + ForgeDirection.SOUTH.offsetZ, tile),
                    Check(world, tile.xCoord + ForgeDirection.DOWN.offsetX, tile.yCoord + ForgeDirection.DOWN.offsetY, tile.zCoord + ForgeDirection.DOWN.offsetZ, tile),
                    Check(world, tile.xCoord + ForgeDirection.UP.offsetX, tile.yCoord + ForgeDirection.UP.offsetY, tile.zCoord + ForgeDirection.UP.offsetZ, tile));

        }else if(direction == ForgeDirection.EAST){
            this.model.render((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F,
                    Check(world, tile.xCoord + ForgeDirection.NORTH.offsetX, tile.yCoord + ForgeDirection.NORTH.offsetY, tile.zCoord + ForgeDirection.NORTH.offsetZ, tile),
                    Check(world, tile.xCoord + ForgeDirection.SOUTH.offsetX, tile.yCoord + ForgeDirection.SOUTH.offsetY, tile.zCoord + ForgeDirection.SOUTH.offsetZ, tile),
                    Check(world, tile.xCoord + ForgeDirection.UP.offsetX, tile.yCoord + ForgeDirection.UP.offsetY, tile.zCoord + ForgeDirection.UP.offsetZ, tile),
                    Check(world, tile.xCoord + ForgeDirection.DOWN.offsetX, tile.yCoord + ForgeDirection.DOWN.offsetY, tile.zCoord + ForgeDirection.DOWN.offsetZ, tile));

        }else if(direction == ForgeDirection.NORTH){
            this.model.render((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F,
                    Check(world, tile.xCoord + ForgeDirection.UP.offsetX, tile.yCoord + ForgeDirection.UP.offsetY, tile.zCoord + ForgeDirection.UP.offsetZ, tile),
                    Check(world, tile.xCoord + ForgeDirection.DOWN.offsetX, tile.yCoord + ForgeDirection.DOWN.offsetY, tile.zCoord + ForgeDirection.DOWN.offsetZ, tile),
                    Check(world, tile.xCoord + ForgeDirection.EAST.offsetX, tile.yCoord + ForgeDirection.EAST.offsetY, tile.zCoord + ForgeDirection.EAST.offsetZ, tile),
                    Check(world, tile.xCoord + ForgeDirection.WEST.offsetX, tile.yCoord + ForgeDirection.WEST.offsetY, tile.zCoord + ForgeDirection.WEST.offsetZ, tile));

        }else if(direction == ForgeDirection.SOUTH){
            this.model.render((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F,
                    Check(world, tile.xCoord + ForgeDirection.DOWN.offsetX, tile.yCoord + ForgeDirection.DOWN.offsetY, tile.zCoord + ForgeDirection.DOWN.offsetZ, tile),
                    Check(world, tile.xCoord + ForgeDirection.UP.offsetX, tile.yCoord + ForgeDirection.UP.offsetY, tile.zCoord + ForgeDirection.UP.offsetZ, tile),
                    Check(world, tile.xCoord + ForgeDirection.EAST.offsetX, tile.yCoord + ForgeDirection.EAST.offsetY, tile.zCoord + ForgeDirection.EAST.offsetZ, tile),
                    Check(world, tile.xCoord + ForgeDirection.WEST.offsetX, tile.yCoord + ForgeDirection.WEST.offsetY, tile.zCoord + ForgeDirection.WEST.offsetZ, tile));

        }

        GL11.glColor4f(1F, 1F, 1F, 1F);

        GL11.glPopMatrix();
        GL11.glPopMatrix();

    }

    public boolean Check(World world, int x, int y, int z, TileEntityCircuitCable tile){
        if(tile instanceof ICircuitConnector) {
            ICircuitConnector ic = (ICircuitConnector) tile;

            ForgeDirection di = ForgeDirection.UNKNOWN;

            for(ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS){
                if(tile.xCoord + dir.offsetX == x &&  tile.yCoord + dir.offsetY == y && tile.zCoord + dir.offsetZ == z){
                    di = dir;
                    break;
                }
            }

            if(world.getTileEntity(x,y,z) instanceof TileEntityCircuitCable){
                TileEntityCircuitCable ci = (TileEntityCircuitCable)world.getTileEntity(x,y,z);
                return world.getTileEntity(x, y, z) instanceof ICircuitConnector && ((ICircuitConnector) world.getTileEntity(x, y, z)).CanConnectToTile(tile, di.getOpposite()) && ic.CanConnectToTile(world.getTileEntity(x, y, z), di) && ci.Direction == tile.Direction;
            }

            return world.getTileEntity(x, y, z) instanceof ICircuitConnector && ((ICircuitConnector) world.getTileEntity(x, y, z)).CanConnectToTile(tile, di.getOpposite()) && ic.CanConnectToTile(world.getTileEntity(x, y, z), di);

        }

   return false;
    }

}


