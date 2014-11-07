package com.circuit.CircuitMod.Rendering.TileEntities;

import MiscUtils.Render.TileEntityBlockInfoRender;
import com.circuit.CircuitMod.Rendering.Models.CircuitBoxModel;
import com.circuit.CircuitMod.Rendering.Models.CircuitCableModel;
import com.circuit.CircuitMod.Utils.CircuitUtils.ICircuitConnector;
import com.circuit.CircuitMod.TileEntity.TileEntityCircuitBox;
import com.circuit.CircuitMod.TileEntity.TileEntityCircuitCable;
import com.circuit.CircuitMod.Utils.Ref;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemDye;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class TileEntityCircuitBoxRender extends TileEntityBlockInfoRender {

    public final CircuitBoxModel model;
    public static ResourceLocation rs = new ResourceLocation(Ref.ModId.toLowerCase() , "textures/models/CircuitBox.png");

    public final CircuitCableModel model1;
    public ResourceLocation rs1 = new ResourceLocation(Ref.ModId.toLowerCase(), "textures/models/CircuitCable.png");

    public TileEntityCircuitBoxRender() {
        this.model = new CircuitBoxModel();
        this.model1 = new CircuitCableModel();
    }


    @Override
    public void renderTileEntityAt(TileEntity te, double x, double y, double z, float scale) {
        super.renderTileEntityAt(te,x,y,z,scale);

        if (te instanceof TileEntityCircuitBox) {
            TileEntityCircuitBox tile = (TileEntityCircuitBox) te;


            int Meta = te.getWorldObj().getBlockMetadata(te.xCoord, te.yCoord, te.zCoord);

            GL11.glPushMatrix();
            GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);

            bindTexture(rs);


            GL11.glPushMatrix();
            GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);


            int face = tile.Rotation == 2 ? 0 : tile.Rotation == 3 ? 2 : tile.Rotation == 4 ? 3 : tile.Rotation == 5 ? 5 : 0;

            GL11.glRotatef(((face - 1) * 90F), 0.0F, 1.0F, 0.0F);

            this.model.render((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);

            GL11.glPopMatrix();
            GL11.glPopMatrix();


            GL11.glPushMatrix();
            GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);

            bindTexture(rs1);

            GL11.glPushMatrix();
            GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);


            if (Meta != 0) {
                Color c = new Color(ItemDye.field_150922_c[15 - Meta]);
                float r = (float) c.getRed() / (float) 255, g = (float) c.getGreen() / (float) 255, b = (float) c.getBlue() / (float) 255;
                GL11.glColor4f(r, g, b, 1F);
            } else {
                Color c = TileEntityCircuitCable.BaseColor;
                float r = (float) c.getRed() / (float) 255, g = (float) c.getGreen() / (float) 255, b = (float) c.getBlue() / (float) 255;
                GL11.glColor4f(r, g, b, 1F);
            }

            boolean Front = false, Back = false, Right = false, Left = false;

            World world = te.getWorldObj();
            int meta = world.getBlockMetadata(te.xCoord, te.yCoord, te.zCoord);

            if (te instanceof ICircuitConnector) {
                ICircuitConnector ic = (ICircuitConnector) te;

                if (world.getTileEntity(te.xCoord, te.yCoord, te.zCoord - 1) instanceof ICircuitConnector && ((ICircuitConnector) world.getTileEntity(te.xCoord, te.yCoord, te.zCoord - 1)).CanConnectToTile(te, null) && ic.CanConnectToTile(world.getTileEntity(te.xCoord, te.yCoord, te.zCoord - 1), null))
                    Front = true;

                if (world.getTileEntity(te.xCoord, te.yCoord, te.zCoord + 1) instanceof ICircuitConnector && ((ICircuitConnector) world.getTileEntity(te.xCoord, te.yCoord, te.zCoord + 1)).CanConnectToTile(te, null) && ic.CanConnectToTile(world.getTileEntity(te.xCoord, te.yCoord, te.zCoord + 1), null))
                    Back = true;


                if (world.getTileEntity(te.xCoord + 1, te.yCoord, te.zCoord) instanceof ICircuitConnector && ((ICircuitConnector) world.getTileEntity(te.xCoord + 1, te.yCoord, te.zCoord)).CanConnectToTile(te, null) && ic.CanConnectToTile(world.getTileEntity(te.xCoord + 1, te.yCoord, te.zCoord), null))
                    Right = true;

                if (world.getTileEntity(te.xCoord - 1, te.yCoord, te.zCoord) instanceof ICircuitConnector && ((ICircuitConnector) world.getTileEntity(te.xCoord - 1, te.yCoord, te.zCoord)).CanConnectToTile(te, null) && ic.CanConnectToTile(world.getTileEntity(te.xCoord - 1, te.yCoord, te.zCoord), null))
                    Left = true;


            }


            this.model1.render((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F, Front, Back, Right, Left);

            GL11.glColor4f(1F, 1F, 1F, 1F);

            GL11.glPopMatrix();
            GL11.glPopMatrix();


            }


    }


}