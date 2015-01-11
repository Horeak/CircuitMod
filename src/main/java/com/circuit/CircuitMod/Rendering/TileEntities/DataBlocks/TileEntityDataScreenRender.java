package com.circuit.CircuitMod.Rendering.TileEntities.DataBlocks;

import com.circuit.CircuitMod.TileEntity.DataBlocks.TileEntityDataScreen;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;

import java.util.List;

public class TileEntityDataScreenRender  extends TileEntitySpecialRenderer
{

    public void renderTileEntityAt(TileEntityDataScreen tile, double xx, double yy, double zz, float p_147500_8_)
    {


        GL11.glPushMatrix();
        GL11.glTranslatef((float) xx + 0.5F, (float) yy + 1.5F, (float) zz + 0.5F);

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glEnable(GL11.GL_LIGHTING);

        int dirr = tile.getBlockMetadata();

        int face = dirr == 2 ? 0 : dirr == 3 ? 2 : dirr == 3 ? 3 : dirr == 5 ? 3 : 5;
        GL11.glRotatef(((face) * 90F), 0.0F, 1.0F, 0.0F);



        GL11.glPushMatrix();
        FontRenderer fontrenderer = this.func_147498_b();
        GL11.glDepthMask(false);

        float scale = 0.01F;

        GL11.glScalef(scale, scale, scale);

        GL11.glRotatef(180F, 1F, 0F, 0F);
        GL11.glRotatef(180F, 0F, 1F, 0F);

        GL11.glTranslatef(0, 100, -51F);



        if(tile.CurrentPacket != null) {
            String text = tile.CurrentPacket.GetTotalData();

            List<String> list = fontrenderer.listFormattedStringToWidth(text, 70);
            int size = list.size();

                    if (size > 7) {
                        size = 7;

                        String temp = list.get(6);

                        temp.substring(0, temp.length() - 3);
                        temp = temp + "...";

                        list.set(6, temp);
                    }


            //TODO Fix text sometimes rendering dark?
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                    for (int i = 0; i < size; ++i) {
                        String s = list.get(i);
                        fontrenderer.drawString(s, -fontrenderer.getStringWidth(s) / 2, i * 10 - size * 5, java.awt.Color.WHITE.getRGB());
                    }
                }


        GL11.glDepthMask(true);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glPopMatrix();


        GL11.glPopMatrix();
    }

    public void renderTileEntityAt(TileEntity p_147500_1_, double p_147500_2_, double p_147500_4_, double p_147500_6_, float p_147500_8_)
    {
        this.renderTileEntityAt((TileEntityDataScreen)p_147500_1_, p_147500_2_, p_147500_4_, p_147500_6_, p_147500_8_);
    }
}