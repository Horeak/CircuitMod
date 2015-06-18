package com.circuit.CircuitMod.Rendering.TileEntities;

import MiscUtils.Render.RenderHelper;
import MiscUtils.Render.TileEntityBlockInfoRender;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;

public class TileEntityBaseTextRender extends TileEntityBlockInfoRender {



    @Override
    public void renderTileEntityAt(TileEntity te, double x, double y, double z, float scale) {
        GL11.glPushMatrix();

        RenderHelper.lightningFix();
        super.renderTileEntityAt(te,x,y,z,scale);

        GL11.glPopMatrix();

    }


}