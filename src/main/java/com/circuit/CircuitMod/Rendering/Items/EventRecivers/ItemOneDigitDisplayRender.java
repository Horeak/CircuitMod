package com.circuit.CircuitMod.Rendering.Items.EventRecivers;

import com.circuit.CircuitMod.Rendering.Models.DefaultCircuitBlockModel;
import com.circuit.CircuitMod.Rendering.Models.DigitDisplayModel;
import com.circuit.CircuitMod.Rendering.TileEntities.EventReceivers.TileEntityOneDigitDisplayRender;
import com.circuit.CircuitMod.Utils.Ref;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class ItemOneDigitDisplayRender  implements IItemRenderer {

    public final DigitDisplayModel model;
    public static ResourceLocation rs = new ResourceLocation(Ref.ModId.toLowerCase(), "textures/models/NumberDisplay.png");

    public final DefaultCircuitBlockModel model1;
    public static ResourceLocation rs1 = new ResourceLocation(Ref.ModId.toLowerCase(), "textures/models/DefaultCircuitBlockModel.png");

    public static Color Def = TileEntityOneDigitDisplayRender.Def;
    public static Color Border = TileEntityOneDigitDisplayRender.Border;

    public ItemOneDigitDisplayRender() {
        this.model = new DigitDisplayModel();
        this.model1 = new DefaultCircuitBlockModel();
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
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        return true;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {

        GL11.glPushMatrix();
        if (type == ItemRenderType.EQUIPPED_FIRST_PERSON) {
            GL11.glTranslatef((float) 0.5F, (float) 2F, (float) 0.5F);
        } else
            GL11.glTranslatef((float) 0.5F, (float) 1.4F, (float) 0.5F);

        GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
        GL11.glPushMatrix();

        if (type == ItemRenderType.EQUIPPED_FIRST_PERSON) {
            GL11.glRotatef((90F * 3), 0.0F, 1.0F, 0.0F);
        }else {
            GL11.glRotatef(180F, 0.0F, 1.0F, 0.0F);
        }

        Minecraft.getMinecraft().renderEngine.bindTexture(rs);
        this.model.render((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F, false, false, false, false, false, false, false);

        Minecraft.getMinecraft().renderEngine.bindTexture(rs1);
        this.model1.render((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F, Def, Border);



        GL11.glPopMatrix();
        GL11.glPopMatrix();

    }

}
