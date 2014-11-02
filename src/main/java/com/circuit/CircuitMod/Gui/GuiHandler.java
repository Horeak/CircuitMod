package com.circuit.CircuitMod.Gui;

import com.circuit.CircuitMod.TileEntity.EventSenders.TileEntityMultiDigitConstant;
import com.circuit.CircuitMod.TileEntity.EventSenders.TileEntityMultiDigitCounter;
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


        return null;

    }
    

}