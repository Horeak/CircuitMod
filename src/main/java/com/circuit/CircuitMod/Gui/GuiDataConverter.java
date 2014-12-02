package com.circuit.CircuitMod.Gui;

import MiscUtils.Network.PacketHandler;
import com.circuit.CircuitMod.Main.CircuitMod;
import com.circuit.CircuitMod.Packets.DataConverterPacket;
import com.circuit.CircuitMod.TileEntity.DataBlocks.TileEntityDataConverter;
import com.circuit.CircuitMod.Utils.DataPacket;
import com.circuit.CircuitMod.Utils.Ref;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

import java.io.IOException;

public class GuiDataConverter extends GuiScreen
{
    private final ResourceLocation Texture = new ResourceLocation(Ref.ModId.toLowerCase() , "textures/gui/MultiDigitSetGui.png");

    GuiTextField Input;
    GuiTextField Input2;
    String text = null, text2 = null;

    public GuiDataConverter(TileEntityDataConverter tile){
        this.tile = tile;

        if(tile.DataTagUse != null)
            text = tile.DataTagUse;

        if(tile.DataTagFrom != null)
            text2 = tile.DataTagFrom;

    }




    TileEntityDataConverter tile;

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
        drawTexturedModalRect(posX, posY + 79, 0, 45, xSizeOfTexture, 40);

        drawString(fontRendererObj, StatCollector.translateToLocal("gui.dataconverter.from"), posX + 12, posY + 5, 0xffffff);
        drawString(fontRendererObj, StatCollector.translateToLocal("gui.dataconverter.to"), posX + 12, posY + 42, 0xffffff);

        if(Input != null)
            Input.drawTextBox();

        if(Input2 != null)
            Input2.drawTextBox();

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

        if (Input2 != null)
            Input2.textboxKeyTyped(key, keycode);


        if(Input.isFocused())
        if(keycode == 28)
            SendPacket();




    }

    protected void mouseClicked(int x, int y, int g)  throws IOException {
        super.mouseClicked(x, y, g);

        if(Input != null)
            Input.mouseClicked(x, y, g);

        if(Input2 != null)
            Input2.mouseClicked(x, y, g);
    }


    @Override
    public void initGui() {
        super.initGui();
        buttonList.clear();
        int posX = (this.width - xSizeOfTexture) / 2;
        int posY = (this.height - ySizeOfTexture) / 2;

        int xSize = 140, ySize = 20;
        int xx = posX + 10, yy = posY + 18;

          if(Input2 == null){
              Input2 = new GuiTextField(0, fontRendererObj, xx, yy, xSize, ySize);

              Input2.setMaxStringLength(Integer.MAX_VALUE);

              if(text2 != null)
                  Input2.setText(text2);


          }else{
              Input2.xPosition = xx;
              Input2.yPosition = yy;
          }


        yy += 15 + ySize;

        if(Input == null){
            Input = new GuiTextField(0, fontRendererObj, xx, yy, xSize, ySize);
            Input.setMaxStringLength(Integer.MAX_VALUE);

            if(text != null)
                Input.setText(text);
        }else{
            Input.xPosition = xx;
            Input.yPosition = yy;
        }

        buttonList.add(new GuiButton(2, posX + 15, posY + 89, 60, 20, StatCollector.translateToLocal("gui.dataselector.save")));
        buttonList.add(new GuiButton(3, posX + 89, posY + 89, 60, 20, StatCollector.translateToLocal("gui.dataselector.default")));


    }

    @Override
    protected void actionPerformed(GuiButton bt) {
        int id = bt.id;


        if(id == 2){
            SendPacket();
        }

        if(id == 3){
            Input.setText(DataPacket.DEFAULT_DATA_STORAGE);
            Input2.setText(DataPacket.DEFAULT_DATA_STORAGE);
        }

    }

    public void SendPacket(){

        if(Input != null){
            String text = Input.getText();
            String text2 = Input2.getText();

            if(text != null && !text.isEmpty() && text2 != null && !text2.isEmpty()){
                PacketHandler.sendToServer(new DataConverterPacket(tile, text, text2), CircuitMod.Utils.channels);


            }

        }

    }





}