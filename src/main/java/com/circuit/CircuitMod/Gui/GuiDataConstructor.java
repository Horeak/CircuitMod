package com.circuit.CircuitMod.Gui;

import MiscUtils.Network.PacketHandler;
import com.circuit.CircuitMod.Main.CircuitMod;
import com.circuit.CircuitMod.Packets.DataConstructPacket;
import com.circuit.CircuitMod.Packets.DataConstructorSavePacket;
import com.circuit.CircuitMod.TileEntity.DataBlocks.TileEntityDataConstructor;
import com.circuit.CircuitMod.Utils.ByteValues;
import com.circuit.CircuitMod.Utils.Ref;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

public class GuiDataConstructor extends GuiScreen
{
    private final ResourceLocation Texture = new ResourceLocation(Ref.ModId.toLowerCase() , "textures/gui/DataConstructor.png");

    GuiTextField Input;
    String text = null;

	int valuesNum = 0;
	ByteValues val;
	boolean renderInput = val != ByteValues.OnSignal;

    public GuiDataConstructor(TileEntityDataConstructor tile){
        this.tile = tile;

        if(tile.SavedData != null)
            text = tile.SavedData;

	    valuesNum = tile.SavedMode;
    }

    TileEntityDataConstructor tile;

    public static final int xSizeOfTexture = 164;
    public static final int ySizeOfTexture = 107;


    @Override
    public void drawScreen(int x, int y, float f) {
        drawDefaultBackground();

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        Minecraft.getMinecraft().renderEngine.bindTexture(Texture);

        int posX = (this.width - xSizeOfTexture) / 2;
        int posY = (this.height - ySizeOfTexture) / 2;

        drawTexturedModalRect(posX, posY, 0, 0, xSizeOfTexture, ySizeOfTexture);

        if(Input != null && renderInput)
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

    public void keyTyped(char key, int keycode){
        super.keyTyped(key, keycode);

	    if(val == ByteValues.OneDigitNumber || val == ByteValues.MultiDigitNumber) {
		    if (Character.isDigit(key) || keycode == Keyboard.KEY_BACK || keycode == Keyboard.KEY_LEFT || keycode == Keyboard.KEY_RIGHT) {

			    if (Input != null && Input.isFocused()) {

				    if(val == ByteValues.OneDigitNumber){
					    if(Character.isDigit(key)) {
						    Input.setText(key + "");
					    }else{
						    Input.textboxKeyTyped(key, keycode);
					    }
				    }else{

					    if(Character.isDigit(key) && Input.getText().length() < 4 || !Character.isDigit(key)) {
						    Input.textboxKeyTyped(key, keycode);
					    }
				    }
			    }
		    }
	    }else{
		    if(Input != null && Input.isFocused()){
			    Input.textboxKeyTyped(key, keycode);
		    }
	    }
    }

    protected void mouseClicked(int x, int y, int g) {
        super.mouseClicked(x, y, g);


        if(Input != null)
            Input.mouseClicked(x, y, g);
    }


    @Override
    public void initGui() {

	    val = ByteValues.getValue(valuesNum);
	    renderInput = val != ByteValues.OnSignal;

        super.initGui();
        buttonList.clear();
        int posX = (this.width - xSizeOfTexture) / 2;
        int posY = (this.height - ySizeOfTexture) / 2;

        int xSize = 144, ySize = 20;
        int xx = posX + 10, yy = posY + 80;

          if(Input == null){
              Input = new GuiTextField(fontRendererObj, xx, yy, xSize, ySize);

              if(text != null)
                  Input.setText(text);
          }else{
              Input.xPosition = xx;
              Input.yPosition = yy;

              Input.width = xSize;
              Input.height = ySize;


	          if(val == ByteValues.MultiDigitNumber){
		          Input.setMaxStringLength(4);

	          }else if(val == ByteValues.OneDigitNumber){
		          Input.setMaxStringLength(1);

	          }else{
		          Input.setMaxStringLength(Integer.MAX_VALUE);
	          }

          }


	      buttonList.add(new GuiButton(1, posX + 20, posY + 10, 120, 20, "Mode: " + val.Name));
	      buttonList.add(new GuiButton(2, posX + 20, posY + 35, 120, 20, StatCollector.translateToLocal("gui.dataconstructor.save")));

	    if(val == ByteValues.OnSignal){
		    buttonList.add(new GuiButton(3, posX + 20, posY + 80, 120, 20, "Send signal"));
	    }else{
		    buttonList.add(new GuiButton(3, posX + 20, posY + 55, 120, 20, "Send data"));
	    }

    }

    @Override
    protected void actionPerformed(GuiButton bt) {
        int id = bt.id;

        if (id == 1){
	        valuesNum += 1;

	        if(valuesNum >= ByteValues.values().length){
		        valuesNum = 0;
	        }

	        Input.setText("");
        }

	    if(id == 2){
		   StorePacket();
	    }

	    if(id == 3){
		    SendPacket();
	    }


    }

    public void SendPacket(){
        if(Input != null && Input.getText() != null){
            String text = Input.getText();

            if(text != null && !text.isEmpty()){
                PacketHandler.sendToServer(new DataConstructPacket(tile, text, valuesNum), CircuitMod.Utils.channels);

            }else if(val == ByteValues.OnSignal){
	            PacketHandler.sendToServer(new DataConstructPacket(tile, "empty", valuesNum), CircuitMod.Utils.channels);
            }

        }
    }


	public void StorePacket(){
		if(Input != null && Input.getText() != null){
			String text = Input.getText();

			if(text != null && !text.isEmpty()){
				PacketHandler.sendToServer(new DataConstructorSavePacket(tile, text, valuesNum), CircuitMod.Utils.channels);

			}else if(val == ByteValues.OnSignal){
				PacketHandler.sendToServer(new DataConstructorSavePacket(tile, "empty", valuesNum), CircuitMod.Utils.channels);
			}

		}
	}

}