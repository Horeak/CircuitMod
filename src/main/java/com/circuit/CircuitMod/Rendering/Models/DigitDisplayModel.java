package com.circuit.CircuitMod.Rendering.Models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class DigitDisplayModel extends ModelBase
{
    //fields
    ModelRenderer Num1;
    ModelRenderer Num2;
    ModelRenderer Num3;
    ModelRenderer Num4;
    ModelRenderer Num5;
    ModelRenderer Num6;
    ModelRenderer Num7;

    public DigitDisplayModel()
    {
        textureWidth = 16;
        textureHeight = 16;


        Num1 = new ModelRenderer(this, 0, 0);
        Num1.addBox(0F, 0F, 0F, 6, 1, 1);
        Num1.setRotationPoint(-3F, 21F, -8F);
        Num1.setTextureSize(16, 16);
        Num1.mirror = true;
        setRotation(Num1, 0F, 0F, 0F);
        Num2 = new ModelRenderer(this, 0, 0);
        Num2.addBox(0F, 0F, 0F, 1, 5, 1);
        Num2.setRotationPoint(-4F, 16F, -8F);
        Num2.setTextureSize(16, 16);
        Num2.mirror = true;
        setRotation(Num2, 0F, 0F, 0F);
        Num3 = new ModelRenderer(this, 0, 0);
        Num3.addBox(0F, 0F, 0F, 1, 5, 1);
        Num3.setRotationPoint(3F, 16F, -8F);
        Num3.setTextureSize(16, 16);
        Num3.mirror = true;
        setRotation(Num3, 0F, 0F, 0F);
        Num4 = new ModelRenderer(this, 0, 0);
        Num4.addBox(0F, 0F, 0F, 6, 1, 1);
        Num4.setRotationPoint(-3F, 15F, -8F);
        Num4.setTextureSize(16, 16);
        Num4.mirror = true;
        setRotation(Num4, 0F, 0F, 0F);
        Num5 = new ModelRenderer(this, 0, 0);
        Num5.addBox(0F, 0F, 0F, 1, 4, 1);
        Num5.setRotationPoint(-4F, 11F, -8F);
        Num5.setTextureSize(16, 16);
        Num5.mirror = true;
        setRotation(Num5, 0F, 0F, 0F);
        Num6 = new ModelRenderer(this, 0, 0);
        Num6.addBox(0F, 0F, 0F, 1, 4, 1);
        Num6.setRotationPoint(3F, 11F, -8F);
        Num6.setTextureSize(16, 16);
        Num6.mirror = true;
        setRotation(Num6, 0F, 0F, 0F);
        Num7 = new ModelRenderer(this, 0, 0);
        Num7.addBox(0F, 0F, 0F, 6, 1, 1);
        Num7.setRotationPoint(-3F, 10F, -8F);
        Num7.setTextureSize(16, 16);
        Num7.mirror = true;
        setRotation(Num7, 0F, 0F, 0F);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5, boolean ONNum1, boolean ONNum2, boolean ONNum3, boolean ONNum4, boolean ONNum5, boolean ONNum6, boolean ONNum7)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        Color Off = new Color(8, 46, 4);
        Color On = new Color(98, 255,0);


        GL11.glPushMatrix();

        GL11.glDisable(GL11.GL_LIGHTING);

        if(ONNum1){
            GL11.glColor4f(On.getRed() / (float)255, On.getGreen() / (float)255, On.getBlue() / (float)255, 1);
        }else{
            GL11.glColor4f(Off.getRed() / (float)255, Off.getGreen() / (float)255, Off.getBlue() / (float)255, 1);
        }
        Num1.render(f5);


        if(ONNum2){
            GL11.glColor4f(On.getRed() / (float)255, On.getGreen() / (float)255, On.getBlue() / (float)255, 1);
        }else{
            GL11.glColor4f(Off.getRed() / (float)255, Off.getGreen() / (float)255, Off.getBlue() / (float)255, 1);
        }
        Num2.render(f5);


        if(ONNum3){
            GL11.glColor4f(On.getRed() / (float)255, On.getGreen() / (float)255, On.getBlue() / (float)255, 1);
        }else{
            GL11.glColor4f(Off.getRed() / (float)255, Off.getGreen() / (float)255, Off.getBlue() / (float)255, 1);
        }
        Num3.render(f5);


        if(ONNum4){
            GL11.glColor4f(On.getRed() / (float)255, On.getGreen() / (float)255, On.getBlue() / (float)255, 1);
        }else{
            GL11.glColor4f(Off.getRed() / (float)255, Off.getGreen() / (float)255, Off.getBlue() / (float)255, 1);
        }
        Num4.render(f5);


        if(ONNum5){
            GL11.glColor4f(On.getRed() / (float)255, On.getGreen() / (float)255, On.getBlue() / (float)255, 1);
        }else{
            GL11.glColor4f(Off.getRed() / (float)255, Off.getGreen() / (float)255, Off.getBlue() / (float)255, 1);
        }
        Num5.render(f5);


        if(ONNum6){
            GL11.glColor4f(On.getRed() / (float)255, On.getGreen() / (float)255, On.getBlue() / (float)255, 1);
        }else{
            GL11.glColor4f(Off.getRed() / (float)255, Off.getGreen() / (float)255, Off.getBlue() / (float)255, 1);
        }
        Num6.render(f5);


        if(ONNum7){
            GL11.glColor4f(On.getRed() / (float)255, On.getGreen() / (float)255, On.getBlue() / (float)255, 1);
        }else{
            GL11.glColor4f(Off.getRed() / (float)255, Off.getGreen() / (float)255, Off.getBlue() / (float)255, 1);
        }
        Num7.render(f5);

        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
    }

    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
    {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    }

}
