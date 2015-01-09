package com.circuit.CircuitMod.Rendering.Models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

/**
 * EntityDetector - tm1990
 * Created using Tabula 4.1.0
 */
public class EntityDetector extends ModelBase {
    public ModelRenderer BasePlate;
    public ModelRenderer MiddleUpPiece;
    public ModelRenderer RadarStart;
    public ModelRenderer Radar2;
    public ModelRenderer Radar2_1;
    public ModelRenderer Radar4;

    public EntityDetector() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.Radar4 = new ModelRenderer(this, 0, 16);
        this.Radar4.setRotationPoint(0.0F, 13.4F, -1.2F);
        this.Radar4.addBox(-1.0F, 0.0F, -1.0F, 2, 1, 2, 0.0F);
        this.setRotateAngle(Radar4, 0.859051057831609F, 0.0F, 0.0F);
        this.Radar2_1 = new ModelRenderer(this, 22, 14);
        this.Radar2_1.setRotationPoint(0.0F, 15.0F, -1.0F);
        this.Radar2_1.addBox(-2.0F, 0.0F, 0.0F, 4, 1, 1, 0.0F);
        this.Radar2 = new ModelRenderer(this, 0, 19);
        this.Radar2.setRotationPoint(0.0F, 14.0F, -0.4000000059604645F);
        this.Radar2.addBox(-5.0F, 0.0F, -2.0F, 10, 1, 4, 0.0F);
        this.setRotateAngle(Radar2, 0.8590510487556456F, 0.0F, 0.0F);
        this.BasePlate = new ModelRenderer(this, 0, 0);
        this.BasePlate.setRotationPoint(-5.0F, 23.0F, -5.0F);
        this.BasePlate.addBox(0.0F, 0.0F, 0.0F, 10, 1, 10, 0.0F);
        this.MiddleUpPiece = new ModelRenderer(this, 0, 24);
        this.MiddleUpPiece.setRotationPoint(-1.0F, 17.0F, -1.0F);
        this.MiddleUpPiece.addBox(0.0F, 0.0F, 0.0F, 2, 6, 2, 0.0F);
        this.RadarStart = new ModelRenderer(this, 33, 11);
        this.RadarStart.setRotationPoint(0.0F, 16.0F, 0.0F);
        this.RadarStart.addBox(-3.0F, 0.0F, -2.0F, 6, 1, 4, 0.0F);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5, boolean rotate) {
        this.MiddleUpPiece.render(f5);
        this.BasePlate.render(f5);

        GL11.glPushMatrix();
        if(rotate) {
            float g = new Long(System.currentTimeMillis()).shortValue() / 60;
            float t = 1.8F;

            GL11.glRotatef(t * g, 0F, 1F, 0F);
        }

        this.RadarStart.render(f5);
        this.Radar2.render(f5);
        this.Radar4.render(f5);
        this.Radar2_1.render(f5);
        GL11.glPopMatrix();
    }

    public void setRadarRotation(float y){
        setRotateAngle(RadarStart, RadarStart.rotateAngleX, y, RadarStart.rotateAngleZ);
        setRotateAngle(Radar2, Radar2.rotateAngleX, y, Radar2.rotateAngleZ);
        setRotateAngle(Radar4, Radar4.rotateAngleX, y, Radar4.rotateAngleZ);
        setRotateAngle(Radar2_1, Radar2_1.rotateAngleX, y, Radar2_1.rotateAngleZ);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
