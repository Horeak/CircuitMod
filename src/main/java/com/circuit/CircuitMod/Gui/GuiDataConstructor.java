package com.circuit.CircuitMod.Gui;

import MiscUtils.Network.PacketHandler;
import com.circuit.CircuitMod.Main.CircuitMod;
import com.circuit.CircuitMod.Packets.DataConstructPacket;
import com.circuit.CircuitMod.TileEntity.DataBlocks.TileEntityDataConstructor;
import com.circuit.CircuitMod.Utils.Ref;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

import java.io.IOException;

public class GuiDataConstructor extends GuiScreen
{
    private final ResourceLocation Texture = new ResourceLocation(Ref.ModId.toLowerCase() , "textures/gui/MultiDigitSetGui.png");

    GuiTextField Input;
    String text = null;

    public GuiDataConstructor(TileEntityDataConstructor tile){
        this.tile = tile;

        if(tile.SavedData != null)
            text = tile.SavedData;

    }




    TileEntityDataConstructor tile;

    public static final int xSizeOfTexture = 164;
    public static final int ySizeOfTexture = 83;


    @Override
    public void drawScreen(int x, int y, float f) {
        drawDefaultBackground();

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        Minecraft.getMinecraft().renderEngine.bindTexture(Texture);

        int posX = (this.width - xSizeOfTexture) / 2;
        int posY = (this.height - ySizeOfTexture) / 2;

        drawTexturedModalRect(posX, posY, 0, 0, xSizeOfTexture, ySizeOfTexture);

        if(Input != null)
            Input.drawTextBox();

        initGui();

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glDisable(GL11.GL_LIGHTING);

        super.drawScreen(x, y, f);
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    public void keyTyped(char key, int keycode) throws IOException{
        super.keyTyped(key, keycode);

            if (Input != null)
                Input.textboxKeyTyped(key, keycode);

        if(Input.isFocused())
        if(keycode == 28)
            SendPacket(false);




    }

    protected void mouseClicked(int x, int y, int g)  throws IOException{
        super.mouseClicked(x, y, g);


        if(Input != null)
            Input.mouseClicked(x, y, g);
    }


    @Override
    public void initGui() {
        super.initGui();
        buttonList.clear();
        int posX = (this.width - xSizeOfTexture) / 2;
        int posY = (this.height - ySizeOfTexture) / 2;

        int xSize = 140, ySize = 20;
        int xx = posX + 10, yy = posY + 10;

          if(Input == null){
              Input = new GuiTextField(0, fontRendererObj, xx, yy, xSize, ySize);

              Input.setMaxStringLength(Integer.MAX_VALUE);

              if(text != null)
                  Input.setText(text);


          }else{
              Input.xPosition = xx;
              Input.yPosition = yy;

              Input.width = xSize;
              Input.height = ySize;
          }


        buttonList.add(new GuiButton(1, posX + 20, posY + 59, 120, 20, StatCollector.translateToLocal("gui.dataconstructor.construct")));
        buttonList.add(new GuiButton(2, posX + 20, posY + 39, 120, 20, StatCollector.translateToLocal("gui.dataconstructor.save")));


    }

    @Override
    protected void actionPerformed(GuiButton bt) {
        int id = bt.id;

        if (id == 1)
            SendPacket(false);

        if (id == 2)
            SendPacket(true);

    }

    public void SendPacket(boolean sf){

        if(Input != null){
            String text = Input.getText();

            if(text != null && !text.isEmpty()){
                PacketHandler.sendToServer(new DataConstructPacket(tile, text, sf), CircuitMod.Utils.channels);

                if(!sf)
                Input.setText("");

            }

        }

    }





}