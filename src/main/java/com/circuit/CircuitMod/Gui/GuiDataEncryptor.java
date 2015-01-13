package com.circuit.CircuitMod.Gui;

import MiscUtils.Network.PacketHandler;
import com.circuit.CircuitMod.Main.CircuitMod;
import com.circuit.CircuitMod.Packets.DataEncryptionPacket;
import com.circuit.CircuitMod.TileEntity.DataBlocks.TileEntityDataEncryptor;
import com.circuit.CircuitMod.Utils.Ref;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

public class GuiDataEncryptor extends GuiScreen
{
    private final ResourceLocation Texture = new ResourceLocation(Ref.ModId.toLowerCase() , "textures/gui/MultiDigitSetGui.png");

    GuiTextField Input, Input2;
    String TextSave = null;

    public GuiDataEncryptor(TileEntityDataEncryptor tile){
        this.tile = tile;


    }




    TileEntityDataEncryptor tile;

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

        if(Input2 != null)
            Input2.drawTextBox();

        TextSave = "";

        if(Input != null && Input.getText() != null){
            for(int i = 0; i < Input.getText().length(); i++)
                TextSave = TextSave + "*";
        }

        Input2.setText(TextSave);

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

            if (Input != null)
                Input.textboxKeyTyped(key, keycode);



    }

    protected void mouseClicked(int x, int y, int g) {
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
              Input = new GuiTextField(fontRendererObj, xx, yy, xSize, ySize);
              Input.setMaxStringLength(20);

              if(tile.EncryptPassword != null && !tile.EncryptPassword.isEmpty()){
                  Input.setText(tile.EncryptPassword);
              }


          }else{
              Input.xPosition = xx;
              Input.yPosition = yy;

              Input.width = xSize;
              Input.height = ySize;
          }


        if(Input2 == null){
            Input2 = new GuiTextField(fontRendererObj, xx, yy, xSize, ySize);
            Input2.setMaxStringLength(20);


        }else{
            Input2.xPosition = xx;
            Input2.yPosition = yy;

            Input2.width = xSize;
            Input2.height = ySize;
        }


        buttonList.add(new GuiButton(1, posX + 20, posY + 55, 120, 20, StatCollector.translateToLocal("gui.encryption.save")));


    }

    @Override
    protected void actionPerformed(GuiButton bt) {
        int id = bt.id;

        if (id == 1){
            PacketHandler.sendToServer(new DataEncryptionPacket(tile, TextSave), CircuitMod.Utils.channels);

    }

    }





}