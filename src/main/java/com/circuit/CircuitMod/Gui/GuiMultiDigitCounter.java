package com.circuit.CircuitMod.Gui;

import MiscUtils.Network.PacketHandler;
import com.circuit.CircuitMod.Main.CircuitMod;
import com.circuit.CircuitMod.Packets.MultiDigitCounterValueChanged;
import com.circuit.CircuitMod.TileEntity.EventSenders.TileEntityMultiDigitCounter;
import com.circuit.CircuitMod.Utils.Ref;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

public class GuiMultiDigitCounter extends GuiScreen
{
	private final ResourceLocation Texture = new ResourceLocation(Ref.ModId.toLowerCase() , "textures/gui/MultiDigitSetGui.png");


    public static final int xSizeOfTexture = 164;
    public static final int ySizeOfTexture = 83;

    int Number1 = 0, Number2 = 0, Number3 = 0, Number4 = 0;

    TileEntityMultiDigitCounter tile;

	public GuiMultiDigitCounter(TileEntityMultiDigitCounter tile){
        this.tile = tile;
            Number4 = tile.ResetAt / 1000;
            Number3 = (tile.ResetAt / 100) - (Number4 * 10);
            Number2 = (tile.ResetAt / 10) - ((Number4 * 100) + (Number3 * 10));
            Number1 = (tile.ResetAt) - ((Number4 * 1000) + (Number3 * 100) + (Number2 * 10));


	}

	 @Override
	    public void drawScreen(int x, int y, float f) {
	        drawDefaultBackground();

	        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	        Minecraft.getMinecraft().renderEngine.bindTexture(Texture);

	        int posX = (this.width - xSizeOfTexture) / 2;
	        int posY = (this.height - ySizeOfTexture) / 2;

	        drawTexturedModalRect(posX, posY, 0, 0, xSizeOfTexture, ySizeOfTexture);

            fontRendererObj.drawString(Number4 + "", posX + 20, posY + 38, 4210752, false);
            fontRendererObj.drawString(Number3 + "", posX + 44, posY + 38, 4210752, false);
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

            for(int i = 0; i < 4; i++)
            buttonList.add(new GuiButton(13 - i, posX + 12 + (23 * i), posY + 8, 20, 20, "+"));

            for(int i = 0; i < 4; i++)
                buttonList.add(new GuiButton(23 - i, posX + 12 + (23 * i), posY + 56, 20, 20, "-"));

            buttonList.add(new GuiButton(1, posX + 105, posY + 31, 50, 20, StatCollector.translateToLocal("gui.mulitidigitconstant.setvalue")));
	        

	    }
	    


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

                int total = (Number4 * 1000) + (Number3 * 100) + (Number2 * 10) + Number1;
                PacketHandler.sendToServer(new MultiDigitCounterValueChanged(tile, total), CircuitMod.Utils.channels);

            }




            if(Number1 > 9 || Number1 < 0)
                Number1 = 0;

            if(Number2 > 9 || Number2 < 0)
                Number2 = 0;

            if(Number3 > 9 || Number3 < 0)
                Number3 = 0;

            if(Number4 > 9 || Number4 < 0)
                Number4 = 0;

	    }
	    
	    



}
