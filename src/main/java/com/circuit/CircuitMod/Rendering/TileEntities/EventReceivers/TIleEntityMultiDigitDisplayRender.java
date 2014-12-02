package com.circuit.CircuitMod.Rendering.TileEntities.EventReceivers;

import MiscUtils.MiscUtilsMain;
import com.circuit.CircuitMod.Rendering.Models.DefaultCircuitBlockModel;
import com.circuit.CircuitMod.Rendering.Models.DigitDisplayModel;
import com.circuit.CircuitMod.TileEntity.EventReceivers.TileEntityMultiDigitDisplay;
import com.circuit.CircuitMod.Utils.Ref;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class TIleEntityMultiDigitDisplayRender  extends TileEntitySpecialRenderer {

    public final DigitDisplayModel model;
    public static ResourceLocation rs = new ResourceLocation(Ref.ModId.toLowerCase(), "textures/models/NumberDisplay.png");

    public final DefaultCircuitBlockModel model1;
    public static ResourceLocation rs1 = new ResourceLocation(Ref.ModId.toLowerCase(), "textures/models/DefaultCircuitBlockModel.png");

    public static Color Def = new Color(0,0,0);
    public static Color Border = new Color(96, 8, 14);

    public TIleEntityMultiDigitDisplayRender() {
        this.model = new DigitDisplayModel();
        this.model1 = new DefaultCircuitBlockModel();
    }


    @Override
    public void renderTileEntityAt(TileEntity te, double x, double y, double z, float sc, int f) {

        if (te instanceof TileEntityMultiDigitDisplay) {
            TileEntityMultiDigitDisplay tile = (TileEntityMultiDigitDisplay) te;

            GL11.glPushMatrix();
            GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);


            GL11.glPushMatrix();
            GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);


            int face = tile.Rotation == 2 ? 0 : tile.Rotation == 3 ? 2 : tile.Rotation == 4 ? 3 : tile.Rotation == 5 ? 5 : 0;

            GL11.glRotatef(((face) * 90F), 0.0F, 1.0F, 0.0F);

            bindTexture(rs1);
            this.model1.render((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F, Def, Border);


            int Number = tile.DisplayNumber;

            if(Number >= 9999)
                Number = 9999;


            if(!(Integer.toString(Number)).isEmpty()) {
                String nums = new StringBuilder(Integer.toString(Number)).reverse().toString();
                String[] Numbers = nums.split("");
                int Tens = nums.length();

                bindTexture(rs);


                float scale = 0.36F;
                float offset = 0.6F;


                GL11.glPushMatrix();

                GL11.glTranslatef(0.322F, 0.61F, -0.22F);

                GL11.glScalef(scale, scale, 0.5F);

                for (int i = 0; i < (MiscUtilsMain.IsLoadedInDev ? 4 : 5); i++) {

                    if(i != 0)
                        GL11.glTranslatef(-offset, 0F, 0F);


                    if(i == 4){
                        if (Numbers.length > 4 && Numbers[4].length() > 0) {
                            Integer tempg = Integer.decode(Numbers[4]);
                            boolean[] Num = GetStateForNumber(tempg);

                            this.model.render((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F, Num[0], Num[1], Num[2], Num[3], Num[4], Num[5], Num[6]);

                        }else{
                            this.model.render((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F, false, false, false, false, false, false, false);
                        }



                        GL11.glPopMatrix();
                        GL11.glPopMatrix();
                        GL11.glPopMatrix();
                        return;
                    }

                    if (Number == -1) {
                        this.model.render((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F, false, false, false, false, false, false, false);
                        continue;
                    }

                    boolean RenderFalse = true;

                            if (Tens >= i && Numbers.length > i) {

                                while(Numbers[i].length() <= 0 && i < 4)
                                    i += 1;


                                if (Numbers[i].length() > 0) {
                                    Integer tempg = Integer.decode(Numbers[i]);
                                    boolean[] Num = GetStateForNumber(tempg);

                                    this.model.render((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F, Num[0], Num[1], Num[2], Num[3], Num[4], Num[5], Num[6]);
                                    RenderFalse = false;

                                }

                            }


                    if (RenderFalse) {
                        this.model.render((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F, false, false, false, false, false, false, false);
                    }


                }
            }
            GL11.glPopMatrix();
            GL11.glPopMatrix();
            GL11.glPopMatrix();




        }

    }

    public static boolean[] GetStateForNumber(Integer Number){
        boolean[] Num = new boolean[7];


        if(Number == 1){
            Num[2] = true;
            Num[5] = true;

        }else if(Number == 2){
            Num[0] = true;
            Num[1] = true;
            Num[3] = true;
            Num[5] = true;
            Num[6] = true;

        }else if(Number == 3){
            Num[0] = true;
            Num[2] = true;
            Num[3] = true;
            Num[5] = true;
            Num[6] = true;

        }else if(Number == 4){
            Num[2] = true;
            Num[3] = true;
            Num[4] = true;
            Num[5] = true;

        }else if(Number == 5){
            Num[0] = true;
            Num[2] = true;
            Num[3] = true;
            Num[4] = true;
            Num[6] = true;

        }else if(Number == 6){
            Num[0] = true;
            Num[1] = true;
            Num[2] = true;
            Num[3] = true;
            Num[4] = true;
            Num[6] = true;

        }else if(Number == 7){
            Num[6] = true;
            Num[5] = true;
            Num[2] = true;

        }else if(Number == 8){
            Num[0] = true;
            Num[1] = true;
            Num[2] = true;
            Num[3] = true;
            Num[4] = true;
            Num[5] = true;
            Num[6] = true;

        }else if(Number == 9){
            Num[2] = true;
            Num[3] = true;
            Num[4] = true;
            Num[5] = true;
            Num[6] = true;

        }else if(Number == 0){
            Num[0] = true;
            Num[1] = true;
            Num[2] = true;
            Num[4] = true;
            Num[5] = true;
            Num[6] = true;

        }


        return Num;
    }


}