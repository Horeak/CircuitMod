package com.circuit.CircuitMod.Rendering.Models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class CircuitCableModel extends ModelBase
{
    //fields
    ModelRenderer Middle;
    ModelRenderer Right;
    ModelRenderer Left;
    ModelRenderer Front;
    ModelRenderer Back;

    public CircuitCableModel()
    {
        textureWidth = 64;
        textureHeight = 32;

        Middle = new ModelRenderer(this, 0, 0);
        Middle.addBox(0F, 0F, 0F, 4, 2, 4);
        Middle.setRotationPoint(-2F, 22F, -2F);
        Middle.setTextureSize(64, 32);
        Middle.mirror = true;
        setRotation(Middle, 0F, 0F, 0F);
        Right = new ModelRenderer(this, 0, 9);
        Right.addBox(0F, 0F, 0F, 6, 2, 4);
        Right.setRotationPoint(-8F, 22F, -2F);
        Right.setTextureSize(64, 32);
        Right.mirror = true;
        setRotation(Right, 0F, 0F, 0F);
        Left = new ModelRenderer(this, 0, 9);
        Left.addBox(0F, 0F, 0F, 6, 2, 4);
        Left.setRotationPoint(2F, 22F, -2F);
        Left.setTextureSize(64, 32);
        Left.mirror = true;
        setRotation(Left, 0F, 0F, 0F);
        Front = new ModelRenderer(this, 0, 16);
        Front.addBox(0F, 0F, 0F, 4, 2, 6);
        Front.setRotationPoint(-2F, 22F, -8F);
        Front.setTextureSize(64, 32);
        Front.mirror = true;
        setRotation(Front, 0F, 0F, 0F);
        Back = new ModelRenderer(this, 0, 16);
        Back.addBox(0F, 0F, 0F, 4, 2, 6);
        Back.setRotationPoint(-2F, 22F, 2F);
        Back.setTextureSize(64, 32);
        Back.mirror = true;
        setRotation(Back, 0F, 0F, 0F);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5, boolean Front, boolean Back, boolean Right, boolean Left)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        Middle.render(f5);

        if(Right)
        this.Right.render(f5);

        if(Left)
        this.Left.render(f5);

        if(Front)
        this.Front.render(f5);

        if(Back)
        this.Back.render(f5);
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
