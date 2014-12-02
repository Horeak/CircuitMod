package com.circuit.CircuitMod.Rendering.Items;

import com.circuit.CircuitMod.Rendering.Models.CircuitCableModel;
import com.circuit.CircuitMod.TileEntity.TileEntityCircuitCable;
import com.circuit.CircuitMod.Utils.Ref;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class ItemCircuitCableRender implements IItemRenderer
{

    CircuitCableModel model = new CircuitCableModel();
    public ResourceLocation rs = new ResourceLocation(Ref.ModId.toLowerCase(), "textures/models/CircuitCable.png");

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


        Minecraft.getMinecraft().getTextureManager().bindTexture(rs);


        GL11.glPushMatrix();
        GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);

        int Meta = item.getItemDamage();

        if(Meta != 0) {
            Color c = new Color(ItemDye.dyeColors[15 - Meta]);
            float r = (float)c.getRed() / (float)255, g = (float)c.getGreen() / (float)255, b = (float)c.getBlue() / (float)255;
            GL11.glColor4f(r, g, b, 1F);
        }else{
            Color c = TileEntityCircuitCable.BaseColor;
            float r = (float)c.getRed() / (float)255, g = (float)c.getGreen() / (float)255, b = (float)c.getBlue() / (float)255;
            GL11.glColor4f(r, g, b, 1F);
        }

        model.render((Entity)null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F, true, true, true, true);


        GL11.glPopMatrix();
        GL11.glPopMatrix();

    }
}