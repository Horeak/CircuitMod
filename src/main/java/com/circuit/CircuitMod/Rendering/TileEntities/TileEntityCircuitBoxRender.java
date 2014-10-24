package com.circuit.CircuitMod.Rendering.TileEntities;

import MiscUtils.Utils.RayTracing;
import com.circuit.CircuitMod.Rendering.Models.CircuitBoxModel;
import com.circuit.CircuitMod.Rendering.Models.CircuitCableModel;
import com.circuit.CircuitMod.TileEntity.CircuitUtils.ICircuitConnector;
import com.circuit.CircuitMod.TileEntity.TileEntityCircuitBox;
import com.circuit.CircuitMod.TileEntity.TileEntityCircuitCable;
import com.circuit.CircuitMod.Utils.Ref;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemDye;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.ArrayList;

public class TileEntityCircuitBoxRender extends TileEntitySpecialRenderer {

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


            //Render Info Tag
            EntityPlayer player = Minecraft.getMinecraft().thePlayer;

            RayTracing.instance().fire();
            MovingObjectPosition mop = RayTracing.instance().getTarget();

            RenderManager manager = RenderManager.instance;


            if(mop != null && mop.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK)
            if (mop.blockX == tile.xCoord && mop.blockY == tile.yCoord && mop.blockZ == tile.zCoord) {
                GL11.glPushMatrix();



                GL11.glAlphaFunc(GL11.GL_GREATER, 0.1F);
                float f = 1.6F;
                float f1 = 0.016666668F * f;

                ArrayList<String> Strings = new ArrayList<String>();
                Strings.add(EnumChatFormatting.WHITE + StatCollector.translateToLocal("tile.circuitbox.name"));
                if (tile.CurrentMode != null) {
                    Strings.add("Mode: " + EnumChatFormatting.GRAY + tile.CurrentMode.ModeName() + EnumChatFormatting.RESET);
                    Strings.add("Min Inputs: " + EnumChatFormatting.GRAY + tile.CurrentMode.MinInputs() + EnumChatFormatting.RESET);
                    Strings.add("Max Inputs: " + EnumChatFormatting.GRAY + tile.CurrentMode.MaxInputs() + EnumChatFormatting.RESET);
                    Strings.add("Active Inputs: " + EnumChatFormatting.GRAY + tile.GetActiveInputs() + EnumChatFormatting.RESET);
                    Strings.add("Outputting: " + (tile.CurrentMode.OutputtingSignal(tile) ? EnumChatFormatting.GREEN : EnumChatFormatting.RED) + tile.CurrentMode.OutputtingSignal(tile) + EnumChatFormatting.RESET);
                }

                FontRenderer fontrenderer = manager.getFontRenderer();

                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

                GL11.glPushMatrix();
                GL11.glTranslatef((float) x + 0.5F, (float) y + 0.8F + (Strings.size() * 0.27F), (float) z + 0.5F);
                GL11.glNormal3f(0.0F, 1.0F, 0.0F);
                GL11.glRotatef(-manager.playerViewY, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(manager.playerViewX, 1.0F, 0.0F, 0.0F);
                GL11.glScalef(-f1, -f1, f1);
                GL11.glDisable(GL11.GL_LIGHTING);
                GL11.glTranslatef(0.0F, 0.25F / f1, 0.0F);
                GL11.glDepthMask(false);
                GL11.glEnable(GL11.GL_BLEND);
                OpenGlHelper.glBlendFunc(770, 771, 1, 0);
                Tessellator tessellator = Tessellator.instance;
                GL11.glDisable(GL11.GL_TEXTURE_2D);
                tessellator.startDrawingQuads();

                int j = 0;

                for (int h = 0; h < Strings.size(); h++) {
                    int i = fontrenderer.getStringWidth(Strings.get(h)) / 2;

                    if (i > j)
                        j = i;

                }

                double Height = 8.0D * Strings.size();

                tessellator.setColorRGBA_F(0.0F, 0.0F, 0.0F, 0.25F);
                tessellator.addVertex((double) (-j - 1), -1.0D, 0.0D);
                tessellator.addVertex((double) (-j - 1), Height, 0.0D);
                tessellator.addVertex((double) (j + 1), Height, 0.0D);
                tessellator.addVertex((double) (j + 1), -1.0D, 0.0D);
                tessellator.draw();


                GL11.glEnable(GL11.GL_TEXTURE_2D);
                GL11.glDepthMask(true);

                for (int h = 0; h < Strings.size(); h++) {
                    int i = fontrenderer.getStringWidth(Strings.get(h)) / 2;
                    fontrenderer.drawString(Strings.get(h), -fontrenderer.getStringWidth(Strings.get(h)) / 2, 0 + (h * 8), new Color(0x919191).getRGB());

                }

                GL11.glEnable(GL11.GL_LIGHTING);
                GL11.glDisable(GL11.GL_BLEND);
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                GL11.glPopMatrix();
                GL11.glPopMatrix();
            }




        }
    }


}