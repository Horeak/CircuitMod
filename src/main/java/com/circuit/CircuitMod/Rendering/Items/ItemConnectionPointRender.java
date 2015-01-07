package com.circuit.CircuitMod.Rendering.Items;

import MiscUtils.Render.RendererHelper;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

import java.awt.*;

public class ItemConnectionPointRender implements IItemRenderer
{


    public ItemConnectionPointRender() {}

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        switch (type) {
            case ENTITY:
            case EQUIPPED:
            case EQUIPPED_FIRST_PERSON:
            case INVENTORY:
                return true;
            default:
                return false;
        }
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        switch (type) {
            case ENTITY: {
                return (helper == ItemRendererHelper.ENTITY_BOBBING ||
                        helper == ItemRendererHelper.ENTITY_ROTATION ||
                        helper == ItemRendererHelper.BLOCK_3D);
            }
            case EQUIPPED: {
                return (helper == ItemRendererHelper.BLOCK_3D ||
                        helper == ItemRendererHelper.EQUIPPED_BLOCK);
            }
            case EQUIPPED_FIRST_PERSON: {
                return (helper == ItemRendererHelper.EQUIPPED_BLOCK);
            }
            case INVENTORY: {
                return (helper == ItemRendererHelper.INVENTORY_BLOCK);
            }
            default: {
                return false;
            }
        }
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
        Block block = Block.getBlockFromItem(item.getItem());

        Color color = new Color(block.getRenderColor(item.getItemDamage()));

        RendererHelper.RenderInventoryBlockWithColor(type, item, color);
    }
}