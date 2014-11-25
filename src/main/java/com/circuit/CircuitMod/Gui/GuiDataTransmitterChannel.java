package com.circuit.CircuitMod.Gui;

import MiscUtils.Network.PacketHandler;
import com.circuit.CircuitMod.Main.CircuitMod;
import com.circuit.CircuitMod.Packets.DataChannelChanged;
import com.circuit.CircuitMod.TileEntity.DataBlocks.TileEntityDataTransmitter;
import com.circuit.CircuitMod.Utils.Ref;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

public class GuiDataTransmitterChannel extends GuiScreen
{
    private final ResourceLocation Texture = new ResourceLocation(Ref.ModId.toLowerCase() , "textures/gui/MultiDigitSetGui.png");

    GuiTextField NumberInput;
    String Text = null;

    public GuiDataTransmitterChannel(TileEntityDataTransmitter tile){
        this.tile = tile;

        Text = Integer.toString(tile.DataChannel);

    }




    TileEntityDataTransmitter tile;

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

        drawString(fontRendererObj, StatCollector.translateToLocal("gui.datachannel.text"), posX + 15, posY + 38, 0xffffff);


        if(NumberInput != null)
            NumberInput.drawTextBox();

        initGui();

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glDisable(GL11.GL_LIGHTING);

        super.drawScreen(x, y, f);
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    public void keyTyped(char key, int keycode){
        super.keyTyped(key, keycode);

        if(Character.isDigit(key) || keycode == Keyboard.KEY_BACK || keycode == Keyboard.KEY_LEFT || keycode == Keyboard.KEY_RIGHT) {
            if (NumberInput != null) {
                NumberInput.textboxKeyTyped(key, keycode);
            }
        }else if(keycode == 28)
            SetChannel();




    }

    protected void mouseClicked(int x, int y, int g) {
        super.mouseClicked(x, y, g);


        if(NumberInput != null)
            NumberInput.mouseClicked(x, y, g);
    }


    @Override
    public void initGui() {
        super.initGui();
        buttonList.clear();
        int posX = (this.width - xSizeOfTexture) / 2;
        int posY = (this.height - ySizeOfTexture) / 2;

        int xSize = 140, ySize = 20;
        int xx = posX + 10, yy = posY + 10;

          if(NumberInput == null){
              NumberInput = new GuiTextField(fontRendererObj, xx, yy, xSize, ySize);

              if(Text != null)
                  NumberInput.setText(Text);


          }else{
              NumberInput.xPosition = xx;
              NumberInput.yPosition = yy;

              NumberInput.width = xSize;
              NumberInput.height = ySize;
          }


        buttonList.add(new GuiButton(1, posX + 90, posY + 59, 70, 20, StatCollector.translateToLocal("gui.datatransmitter.setchannel")));


    }

    @Override
    protected void actionPerformed(GuiButton bt) {
        int id = bt.id;

        if (id == 1)
            SetChannel();

    }

    public void SetChannel(){

        if(NumberInput != null){
            String t = NumberInput.getText();

            if(t != null && !t.isEmpty()){
                int num = Integer.parseInt(t);
                PacketHandler.sendToServer(new DataChannelChanged(tile, num), CircuitMod.Utils.channels);

                mc.displayGuiScreen(null);
                mc.setIngameFocus();


            }

        }

    }





}