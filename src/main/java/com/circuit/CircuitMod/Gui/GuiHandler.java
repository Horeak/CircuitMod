package com.circuit.CircuitMod.Gui;

import com.circuit.CircuitMod.TileEntity.DataBlocks.TileEntityDataConstructor;
import com.circuit.CircuitMod.TileEntity.DataBlocks.TileEntityDataConverter;
import com.circuit.CircuitMod.TileEntity.DataBlocks.TileEntityDataReceiver;
import com.circuit.CircuitMod.TileEntity.DataBlocks.TileEntityDataSelector;
import com.circuit.CircuitMod.TileEntity.DataBlocks.TileEntityDataTransmitter;
import com.circuit.CircuitMod.TileEntity.EventSenders.TileEntityMultiDigitConstant;
import com.circuit.CircuitMod.TileEntity.EventSenders.TileEntityMultiDigitCounter;
import com.circuit.CircuitMod.TileEntity.EventSenders.TileEntityOneDigitConstant;
import com.circuit.CircuitMod.TileEntity.EventSenders.TileEntityOneDigitCounter;
import com.circuit.CircuitMod.TileEntity.EventSenders.TileEntityRandomNumber;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler{


    @Override

    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tile = world.getTileEntity(x, y, z);
       



        
        return null;
    }
        


    @Override

    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tile = world.getTileEntity(x, y, z);


        if(tile instanceof TileEntityMultiDigitConstant)
            return new GuiMultiDigitConstant((TileEntityMultiDigitConstant)tile);


        if(tile instanceof TileEntityMultiDigitCounter)
            return new GuiMultiDigitCounter((TileEntityMultiDigitCounter)tile);

        if(tile instanceof TileEntityRandomNumber)
            return new GuiRandomNumberComponent((TileEntityRandomNumber)tile);

        if(tile instanceof TileEntityOneDigitConstant)
            return new GuiOneDigitConstant((TileEntityOneDigitConstant)tile);

        if(tile instanceof TileEntityOneDigitCounter)
            return new GuiOneDigitCounter((TileEntityOneDigitCounter)tile);

        if(tile instanceof TileEntityDataTransmitter)
            return new GuiDataTransmitterChannel((TileEntityDataTransmitter)tile);

        if(tile instanceof TileEntityDataReceiver)
            return new GuiDataReceiverChannel((TileEntityDataReceiver)tile);

        if(tile instanceof TileEntityDataConstructor)
            return new GuiDataConstructor((TileEntityDataConstructor)tile);

        if(tile instanceof TileEntityDataSelector)
            return new GuiDataSelector((TileEntityDataSelector)tile);

        if(tile instanceof TileEntityDataConverter)
            return new GuiDataConverter((TileEntityDataConverter)tile);


        return null;

    }
    

}
