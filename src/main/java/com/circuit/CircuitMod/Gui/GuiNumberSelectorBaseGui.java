package com.circuit.CircuitMod.Gui;

import com.circuit.CircuitMod.Utils.Ref;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

public abstract class GuiNumberSelectorBaseGui  extends GuiScreen
{
    private final ResourceLocation Texture = new ResourceLocation(Ref.ModId.toLowerCase() , "textures/gui/MultiDigitSetGui.png");


    public static final int xSizeOfTexture = 164;
    public static final int ySizeOfTexture = 83;

    int Number1 = 0, Number2 = 0, Number3 = 0, Number4 = 0;
    abstract int length();

    public GuiNumberSelectorBaseGui(int Num){


        if(length() >= 4)
        Number4 = Num / 1000;

        if(length() >= 3)
        Number3 = (Num / 100) - (Number4 * 10);

        if(length() >= 2)
        Number2 = (Num / 10) - (Number4 * 100) - (Number3 * 10);

        Number1 = (Num) - (Number4 * 1000) - (Number3 * 100) - (Number2 * 10);





    }

    @Override
    public void drawScreen(int x, int y, float f) {
        drawDefaultBackground();

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        Minecraft.getMinecraft().renderEngine.bindTexture(Texture);

        int posX = (this.width - xSizeOfTexture) / 2;
        int posY = (this.height - ySizeOfTexture) / 2;

        drawTexturedModalRect(posX, posY, 0, 0, xSizeOfTexture, ySizeOfTexture);


        if(length() >= 4)
            drawTexturedModalRect(posX + 15, posY + 30, 166, 5, 16, 22);

        if(length() >= 3)
            drawTexturedModalRect(posX + 38, posY + 30, 166, 5, 16, 22);

        if(length() >= 2)
            drawTexturedModalRect(posX + 61, posY + 30, 166, 5, 16, 22);

        drawTexturedModalRect(posX + 84, posY + 30, 166, 5, 16, 22);




        if(length() >= 4)
        fontRendererObj.drawString(Number4 + "", posX + 20, posY + 38, 4210752, false);

        if(length() >= 3)
        fontRendererObj.drawString(Number3 + "", posX + 44, posY + 38, 4210752, false);

        if(length() >= 2)
        fontRendererObj.drawString(Number2 + "", posX + 67, posY + 38, 4210752, false);


        fontRendererObj.drawString(Number1 + "", posX + 90, posY + 38, 4210752, false);


        if(Number1 > 9 || Number1 < 0)
            Number1 = 0;

        if(Number2 > 9 || Number2 < 0)
            Number2 = 0;

        if(Number3 > 9 || Number3 < 0)
            Number3 = 0;

        if(Number4 > 9 || Number4 < 0)
            Number4 = 0;


        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glDisable(GL11.GL_LIGHTING);

        super.drawScreen(x, y, f);
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }




    @Override
    public void initGui() {
        super.initGui();
        buttonList.clear();
        int posX = (this.width - xSizeOfTexture) / 2;
        int posY = (this.height - ySizeOfTexture) / 2;

        for(int i = 0; i < 4; i++) {
            if((i+1) <= length())
            buttonList.add(new GuiButton(10 + i, posX + 12 + (23 * 3)  - (23 * i), posY + 8, 20, 20, "+"));
        }

        for(int i = 0; i < 4; i++) {
            if ((i + 1) <= length())
                buttonList.add(new GuiButton(20 + i, posX + 12 + (23 * 3) - (23 * i), posY + 56, 20, 20, "-"));
        }


        buttonList.add(new GuiButton(1, posX + 105, posY + 31, 50, 20, StatCollector.translateToLocal("gui.mulitidigitconstant.setvalue")));


    }



    public abstract void OnFinish(int TotalNum);

    @Override
    protected void actionPerformed(GuiButton bt) {
        int id = bt.id;

        if(id == 10)
            Number1 += 1;

        if(id == 20)
            Number1 -= 1;


        if(id == 11)
            Number2 += 1;

        if(id == 21)
            Number2 -= 1;


        if(id == 12)
            Number3 += 1;

        if(id == 22)
            Number3 -= 1;


        if(id == 13)
            Number4 += 1;

        if(id == 23)
            Number4 -= 1;




        if(id == 1){

            int total = 0;

            total = (Number4 > 0 ? (Number4 * 1000) : 0) + (Number3 > 0 ? (Number3 * 100) : 0) + (Number2 > 0 ? (Number2 * 10) : 0) + Number1;
            OnFinish(total);

        }




        if(Number1 > 9)
            Number1 = 0;

        if(Number2 > 9)
            Number2 = 0;

        if(Number3 > 9)
            Number3 = 0;

        if(Number4 > 9)
            Number4 = 0;



        if(Number1 < 0)
            Number1 = 9;

        if(Number2 < 0)
            Number2 = 9;

        if(Number3 < 0)
            Number3 = 9;

        if(Number4 < 0)
            Number4 = 9;

    }





}
