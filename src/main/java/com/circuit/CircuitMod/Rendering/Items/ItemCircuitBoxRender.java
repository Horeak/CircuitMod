package com.circuit.CircuitMod.Rendering.Items;

import com.circuit.CircuitMod.Rendering.Models.CircuitBoxModel;
import com.circuit.CircuitMod.Rendering.Models.CircuitCableModel;
import com.circuit.CircuitMod.TileEntity.TileEntityCircuitCable;
import com.circuit.CircuitMod.Utils.Ref;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class ItemCircuitBoxRender  implements IItemRenderer
{

    public final CircuitBoxModel model;
    public static ResourceLocation rs = new ResourceLocation(Ref.ModId.toLowerCase() , "textures/models/CircuitBox.png");

    public final CircuitCableModel model1;
    public ResourceLocation rs1 = new ResourceLocation(Ref.ModId.toLowerCase(), "textures/models/CircuitCable.png");

    public ItemCircuitBoxRender(){
        this.model = new CircuitBoxModel();
        this.model1 = new CircuitCableModel();
    }

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        switch (type) {
            case ENTITY:
                return true;
            case EQUIPPED:
                return true;
            case EQUIPPED_FIRST_PERSON:
                return true;
            case INVENTORY:
                return true;
            default:
                return false;
        }
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item,ItemRendererHelper helper) {
        return true;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {

        GL11.glPushMatrix();
        if(type == ItemRenderType.EQUIPPED_FIRST_PERSON){
            GL11.glTranslatef((float) 0.5F, (float) 2F, (float) 0.5F);
        }else
            GL11.glTranslatef((float) 0.5F, (float) 1.5F, (float) 0.5F);

        GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);


        GL11.glPushMatrix();

        Minecraft.getMinecraft().renderEngine.bindTexture(rs);
        this.model.render((Entity)null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);

        GL11.glPopMatrix();



        GL11.glPushMatrix();

        Minecraft.getMinecraft().renderEngine.bindTexture(rs1);

        GL11.glPushMatrix();

            Color c = TileEntityCircuitCable.BaseColor;
            float r = (float)c.getRed() / (float)255, g = (float)c.getGreen() / (float)255, b = (float)c.getBlue() / (float)255;
            GL11.glColor4f(r, g, b, 1F);


        this.model1.render((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F, true, true, true, true);

        GL11.glColor4f(1F, 1F, 1F, 1F);
        GL11.glPopMatrix();
        GL11.glPopMatrix();
        GL11.glPopMatrix();

    }
}