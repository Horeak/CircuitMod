package com.circuit.CircuitMod.Rendering.TileEntities.EventReceivers;

import MiscUtils.Render.TileEntityBlockInfoRender;
import com.circuit.CircuitMod.Blocks.ModBlockCableConnectionPoint;
import com.circuit.CircuitMod.Rendering.Models.LampModel;
import com.circuit.CircuitMod.TileEntity.EventReceivers.TileEntityLamp;
import com.circuit.CircuitMod.Utils.Ref;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class TileEntityLampRender extends TileEntityBlockInfoRender {

    public final LampModel model;
    public static ResourceLocation rs = new ResourceLocation(Ref.ModId.toLowerCase(), "textures/models/DefaultCircuitBlockModel.png");

    public TileEntityLampRender() {
        this.model = new LampModel();
    }


    @Override
    public void renderTileEntityAt(TileEntity te, double x, double y, double z, float scale, int f) {
        super.renderTileEntityAt(te,x,y,z,scale, f);


        if (te instanceof TileEntityLamp) {
            TileEntityLamp tile = (TileEntityLamp) te;

            GL11.glPushMatrix();
            GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);

            bindTexture(rs);

            GL11.glPushMatrix();
            GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);


            IBlockState meta = tile.getWorld().getBlockState(new BlockPos(tile.getPos().getX(), tile.getPos().getY(), tile.getPos().getZ()));
            int Meta = Integer.parseInt(meta.getProperties().get(ModBlockCableConnectionPoint.COLOR).toString());

            this.model.render((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F, Meta, tile.Powered);

            GL11.glPopMatrix();
            GL11.glPopMatrix();

            GL11.glPushMatrix();


            GL11.glPopMatrix();


        }
    }
}

