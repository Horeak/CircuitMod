package com.circuit.CircuitMod.Rendering.Items.DataBlocks;

import com.circuit.CircuitMod.Rendering.BlockBase;
import com.circuit.CircuitMod.Utils.Ref;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

public class DataScreenItemRender  implements IItemRenderer {

    BlockBase base = new BlockBase();
    ResourceLocation texture = new ResourceLocation(Ref.ModId.toLowerCase(), "textures/models/datascreen.png");


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
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        return true;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {

        GL11.glPushMatrix();
        if (type == ItemRenderType.EQUIPPED_FIRST_PERSON) {
            GL11.glTranslatef((float) 0.5F, (float) 1.6F, (float) 0.5F);
            GL11.glRotatef(((5) * 90F), 0.0F, 1.0F, 0.0F);

        } else {
            GL11.glTranslatef((float) 0.5F, (float) 1.4F, (float) 0.5F);
            GL11.glRotatef(((2) * 90F), 0.0F, 1.0F, 0.0F);
        }



        GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
        GL11.glPushMatrix();
        Minecraft.getMinecraft().renderEngine.bindTexture(texture);

        base.render((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);

        GL11.glPopMatrix();
        GL11.glPopMatrix();

    }

}