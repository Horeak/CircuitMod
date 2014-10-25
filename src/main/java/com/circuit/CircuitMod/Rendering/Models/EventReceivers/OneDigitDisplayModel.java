package com.circuit.CircuitMod.Rendering.Models.EventReceivers;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class OneDigitDisplayModel extends ModelBase
{
    //fields
    ModelRenderer Shape1;
    ModelRenderer Shape2;
    ModelRenderer Shape3;
    ModelRenderer Shape4;
    ModelRenderer Shape5;
    ModelRenderer Shape6;
    ModelRenderer Shape7;
    ModelRenderer Shape8;
    ModelRenderer Shape9;
    ModelRenderer Shape10;
    ModelRenderer Shape11;
    ModelRenderer Shape12;
    ModelRenderer Shape13;
    ModelRenderer Num1;
    ModelRenderer Num2;
    ModelRenderer Num3;
    ModelRenderer Num4;
    ModelRenderer Num5;
    ModelRenderer Num6;
    ModelRenderer Num7;

    public OneDigitDisplayModel()
    {
        textureWidth = 128;
        textureHeight = 32;

        Shape1 = new ModelRenderer(this, 0, 0);
        Shape1.addBox(0F, 0F, 0F, 14, 1, 1);
        Shape1.setRotationPoint(-7F, 23F, -8F);
        Shape1.setTextureSize(128, 32);
        Shape1.mirror = true;
        setRotation(Shape1, 0F, 0F, 0F);
        Shape2 = new ModelRenderer(this, 0, 0);
        Shape2.addBox(0F, 0F, 0F, 14, 1, 1);
        Shape2.setRotationPoint(-7F, 23F, 7F);
        Shape2.setTextureSize(128, 32);
        Shape2.mirror = true;
        setRotation(Shape2, 0F, 0F, 0F);
        Shape3 = new ModelRenderer(this, 0, 0);
        Shape3.addBox(0F, 0F, 0F, 14, 1, 1);
        Shape3.setRotationPoint(-7F, 8F, 7F);
        Shape3.setTextureSize(128, 32);
        Shape3.mirror = true;
        setRotation(Shape3, 0F, 0F, 0F);
        Shape4 = new ModelRenderer(this, 0, 0);
        Shape4.addBox(0F, 0F, 0F, 14, 1, 1);
        Shape4.setRotationPoint(-7F, 8F, -8F);
        Shape4.setTextureSize(128, 32);
        Shape4.mirror = true;
        setRotation(Shape4, 0F, 0F, 0F);
        Shape5 = new ModelRenderer(this, 0, 17);
        Shape5.addBox(0F, 0F, 0F, 1, 1, 14);
        Shape5.setRotationPoint(-8F, 23F, -7F);
        Shape5.setTextureSize(128, 32);
        Shape5.mirror = true;
        setRotation(Shape5, 0F, 0F, 0F);
        Shape6 = new ModelRenderer(this, 0, 17);
        Shape6.addBox(0F, 0F, 0F, 1, 1, 14);
        Shape6.setRotationPoint(-8F, 8F, -7F);
        Shape6.setTextureSize(128, 32);
        Shape6.mirror = true;
        setRotation(Shape6, 0F, 0F, 0F);
        Shape7 = new ModelRenderer(this, 0, 17);
        Shape7.addBox(0F, 0F, 0F, 1, 1, 14);
        Shape7.setRotationPoint(7F, 8F, -7F);
        Shape7.setTextureSize(128, 32);
        Shape7.mirror = true;
        setRotation(Shape7, 0F, 0F, 0F);
        Shape8 = new ModelRenderer(this, 0, 17);
        Shape8.addBox(0F, 0F, 0F, 1, 1, 14);
        Shape8.setRotationPoint(7F, 23F, -7F);
        Shape8.setTextureSize(128, 32);
        Shape8.mirror = true;
        setRotation(Shape8, 0F, 0F, 0F);
        Shape9 = new ModelRenderer(this, 33, 15);
        Shape9.addBox(0F, 0F, 0F, 1, 16, 1);
        Shape9.setRotationPoint(-8F, 8F, -8F);
        Shape9.setTextureSize(128, 32);
        Shape9.mirror = true;
        setRotation(Shape9, 0F, 0F, 0F);
        Shape10 = new ModelRenderer(this, 33, 15);
        Shape10.addBox(0F, 0F, 0F, 1, 16, 1);
        Shape10.setRotationPoint(7F, 8F, -8F);
        Shape10.setTextureSize(128, 32);
        Shape10.mirror = true;
        setRotation(Shape10, 0F, 0F, 0F);
        Shape11 = new ModelRenderer(this, 33, 15);
        Shape11.addBox(0F, 0F, 0F, 1, 16, 1);
        Shape11.setRotationPoint(7F, 8F, 7F);
        Shape11.setTextureSize(128, 32);
        Shape11.mirror = true;
        setRotation(Shape11, 0F, 0F, 0F);
        Shape12 = new ModelRenderer(this, 33, 15);
        Shape12.addBox(0F, 0F, 0F, 1, 16, 1);
        Shape12.setRotationPoint(-8F, 8F, 7F);
        Shape12.setTextureSize(128, 32);
        Shape12.mirror = true;
        setRotation(Shape12, 0F, 0F, 0F);
        Shape13 = new ModelRenderer(this, 70, 0);
        Shape13.addBox(0F, 0F, 0F, 14, 14, 14);
        Shape13.setRotationPoint(-7F, 9F, -7F);
        Shape13.setTextureSize(128, 32);
        Shape13.mirror = true;
        setRotation(Shape13, 0F, 0F, 0F);
        Num1 = new ModelRenderer(this, 33, 0);
        Num1.addBox(0F, 0F, 0F, 6, 1, 1);
        Num1.setRotationPoint(-3F, 21F, -8F);
        Num1.setTextureSize(128, 32);
        Num1.mirror = true;
        setRotation(Num1, 0F, 0F, 0F);
        Num2 = new ModelRenderer(this, 60, 6);
        Num2.addBox(0F, 0F, 0F, 1, 5, 1);
        Num2.setRotationPoint(-4F, 16F, -8F);
        Num2.setTextureSize(128, 32);
        Num2.mirror = true;
        setRotation(Num2, 0F, 0F, 0F);
        Num3 = new ModelRenderer(this, 60, 6);
        Num3.addBox(0F, 0F, 0F, 1, 5, 1);
        Num3.setRotationPoint(3F, 16F, -8F);
        Num3.setTextureSize(128, 32);
        Num3.mirror = true;
        setRotation(Num3, 0F, 0F, 0F);
        Num4 = new ModelRenderer(this, 33, 0);
        Num4.addBox(0F, 0F, 0F, 6, 1, 1);
        Num4.setRotationPoint(-3F, 15F, -8F);
        Num4.setTextureSize(128, 32);
        Num4.mirror = true;
        setRotation(Num4, 0F, 0F, 0F);
        Num5 = new ModelRenderer(this, 60, 0);
        Num5.addBox(0F, 0F, 0F, 1, 4, 1);
        Num5.setRotationPoint(-4F, 11F, -8F);
        Num5.setTextureSize(128, 32);
        Num5.mirror = true;
        setRotation(Num5, 0F, 0F, 0F);
        Num6 = new ModelRenderer(this, 60, 0);
        Num6.addBox(0F, 0F, 0F, 1, 4, 1);
        Num6.setRotationPoint(3F, 11F, -8F);
        Num6.setTextureSize(128, 32);
        Num6.mirror = true;
        setRotation(Num6, 0F, 0F, 0F);
        Num7 = new ModelRenderer(this, 33, 0);
        Num7.addBox(0F, 0F, 0F, 6, 1, 1);
        Num7.setRotationPoint(-3F, 10F, -8F);
        Num7.setTextureSize(128, 32);
        Num7.mirror = true;
        setRotation(Num7, 0F, 0F, 0F);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5, boolean ONNum1, boolean ONNum2, boolean ONNum3, boolean ONNum4, boolean ONNum5, boolean ONNum6, boolean ONNum7)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        Shape1.render(f5);
        Shape2.render(f5);
        Shape3.render(f5);
        Shape4.render(f5);
        Shape5.render(f5);
        Shape6.render(f5);
        Shape7.render(f5);
        Shape8.render(f5);
        Shape9.render(f5);
        Shape10.render(f5);
        Shape11.render(f5);
        Shape12.render(f5);
        Shape13.render(f5);

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
