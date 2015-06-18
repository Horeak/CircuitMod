package com.circuit.CircuitMod.Rendering.TileEntities.DataBlocks;

import MiscUtils.Render.TileEntityBlockInfoRender;
import com.circuit.CircuitMod.Rendering.Models.EntityDetector;
import com.circuit.CircuitMod.Utils.Ref;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class TileEntityEntityDetectorRender  extends TileEntityBlockInfoRender {

    public final EntityDetector model = new EntityDetector();
    public static ResourceLocation texture = new ResourceLocation(Ref.ModId.toLowerCase(), "textures/models/EntityDetector.png");


    @Override
    public void renderTileEntityAt(TileEntity te, double x, double y, double z, float scale) {
        super.renderTileEntityAt(te,x,y,z,scale);


            GL11.glPushMatrix();
            GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);

            bindTexture(texture);

            GL11.glPushMatrix();
            GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);

            this.model.render((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F, true);

            GL11.glPopMatrix();
            GL11.glPopMatrix();

            GL11.glPushMatrix();


            GL11.glPopMatrix();

    }
}

