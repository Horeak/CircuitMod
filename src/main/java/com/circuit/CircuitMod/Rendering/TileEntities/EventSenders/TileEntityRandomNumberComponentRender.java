package com.circuit.CircuitMod.Rendering.TileEntities.EventSenders;

import MiscUtils.Render.TileEntityBlockInfoRender;
import com.circuit.CircuitMod.Rendering.Models.DefaultCircuitBlockModel;
import com.circuit.CircuitMod.TileEntity.EventSenders.TileEntityRandomNumber;
import com.circuit.CircuitMod.Utils.Ref;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class TileEntityRandomNumberComponentRender extends TileEntityBlockInfoRender {

    public final DefaultCircuitBlockModel model;
    public static ResourceLocation rs = new ResourceLocation(Ref.ModId.toLowerCase(), "textures/models/DefaultCircuitBlockModel.png");


    public static Color Def = new Color(106, 104, 78);
    public static Color Bord = new Color(84, 84, 84);

    public TileEntityRandomNumberComponentRender() {
        this.model = new DefaultCircuitBlockModel();
    }


    @Override
    public void renderTileEntityAt(TileEntity te, double x, double y, double z, float scale, int f) {
        super.renderTileEntityAt(te,x,y,z,scale, f);


        if (te instanceof TileEntityRandomNumber) {
            TileEntityRandomNumber tile = (TileEntityRandomNumber) te;

            GL11.glPushMatrix();
            GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);

            bindTexture(rs);

            GL11.glPushMatrix();
            GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);


            this.model.render((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F, Def, Bord);

            GL11.glPopMatrix();
            GL11.glPopMatrix();

            GL11.glPushMatrix();


            GL11.glPopMatrix();


        }
    }
}

