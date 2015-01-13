package com.circuit.CircuitMod.TileEntity.DataBlocks;

import com.circuit.CircuitMod.TileEntity.TileEntityEventSender;
import com.circuit.CircuitMod.Utils.ByteValues;
import com.circuit.CircuitMod.Utils.DataPacket;
import com.circuit.CircuitMod.Utils.DataStorage.DataStringValue;
import com.circuit.CircuitMod.Utils.DataStorage.DataValueStorage;
import com.circuit.CircuitMod.Utils.EventPacket;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityDataConstructor extends TileEntityEventSender {

    public String SavedData;
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

    public void SendPacketFromGUI(DataValueStorage Data){
        DataPacket packet = CreatePacket();
        packet.SaveData(DataPacket.DEFAULT_DATA_STORAGE, Data);
        SendPacketToAround(packet);
    }

    @Override
    public void OnRecived(EventPacket packet) {
        if(!Resetting){

        DataPacket SendPacket = CreatePacket();
        SendPacket.SaveData(DataPacket.DEFAULT_DATA_STORAGE, new DataStringValue(SavedData));
        SendPacketToAround(SendPacket);

            Resetting = true;
        }else{
            Reset = 0;
        }


    }


    public DataPacket CreatePacket(){
        DataPacket packet = new DataPacket(-1 ,ByteValues.DataSignal.Value());
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

        boolean t = nbtTagCompound.getBoolean("SavedDataNotNull");

        if(t)
        SavedData = nbtTagCompound.getString("SavedData");


        Resetting = nbtTagCompound.getBoolean("Resetting");
        Reset = nbtTagCompound.getInteger("Reset");


    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound) {
        super.writeToNBT(nbtTagCompound);

        nbtTagCompound.setBoolean("SavedDataNotNull", SavedData != null);

        if(SavedData != null)
        nbtTagCompound.setString("SavedData", SavedData);


        nbtTagCompound.setInteger("Reset", Reset);
        nbtTagCompound.setBoolean("Resetting", Resetting);

    }

}
