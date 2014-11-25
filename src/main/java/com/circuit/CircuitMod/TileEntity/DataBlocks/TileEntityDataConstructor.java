package com.circuit.CircuitMod.TileEntity.DataBlocks;

import com.circuit.CircuitMod.TileEntity.TileEntityEventSender;
import com.circuit.CircuitMod.Utils.ByteValues;
import com.circuit.CircuitMod.Utils.EventPacket;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityDataConstructor extends TileEntityEventSender {

    public String SavedData = null;
    int Reset = 0;
    static int ResetAt = 1;
    boolean Resetting;

    public void updateEntity(){
        if(Resetting){
            if(Reset < ResetAt){
                Reset += 1;
            }else{
                Reset = 0;
                Resetting = false;
            }
        }


    }

    public void SendPacketFromGUI(String Data){
        EventPacket packet = CreatePacket();
        packet.NBT.setString("Data", Data);
        SendPacketToAround(packet);
    }

    @Override
    public void OnRecived(EventPacket packet) {
        if(!Resetting){

        EventPacket SendPacket = CreatePacket();
        SendPacket.NBT.setString("Data", SavedData);
        SendPacketToAround(SendPacket);

            Resetting = true;
        }else{
            Reset = 0;
        }


    }


    public EventPacket CreatePacket(){
        EventPacket packet = new EventPacket(-1 ,ByteValues.DataSignal.Value());
        return packet;
    }

    @Override
    public boolean CanConnectToTile(TileEntity tile, ForgeDirection dir) {
        return true;
    }

    @Override
    public boolean CanRecive(EventPacket packet) {
        return packet.ByteValue == ByteValues.OnSignal.Value() && SavedData != null;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {
        super.readFromNBT(nbtTagCompound);

        SavedData = nbtTagCompound.getString("SavedData");
        Resetting = nbtTagCompound.getBoolean("Resetting");
        Reset = nbtTagCompound.getInteger("Reset");


    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound) {
        super.writeToNBT(nbtTagCompound);

        nbtTagCompound.setString("SavedData", SavedData);
        nbtTagCompound.setInteger("Reset", Reset);
        nbtTagCompound.setBoolean("Resetting", Resetting);

    }

}
