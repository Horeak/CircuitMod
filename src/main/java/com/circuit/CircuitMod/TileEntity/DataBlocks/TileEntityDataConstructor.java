package com.circuit.CircuitMod.TileEntity.DataBlocks;

import MiscUtils.Network.PacketHandler;
import com.circuit.CircuitMod.Main.CircuitMod;
import com.circuit.CircuitMod.Packets.DataConstructPacket;
import com.circuit.CircuitMod.TileEntity.TileEntityEventSender;
import com.circuit.CircuitMod.Utils.ByteValues;
import com.circuit.CircuitMod.Utils.DataPacket;
import com.circuit.CircuitMod.Utils.DataStorage.DataValueStorage;
import com.circuit.CircuitMod.Utils.EventPacket;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityDataConstructor extends TileEntityEventSender {

    public String SavedData;
	public int SavedMode;

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

        boolean powered = worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord);

	    if(!worldObj.isRemote)
        if(powered){
            if(Resetting == false){
	                 SendPacket();
                    Resetting = true;
                }else{
                    Reset = 0;
                }
        }


    }

    public void SendPacketFromGUI(EventPacket packet){
        SendPacketToAround(packet);
    }


    public void SendPacketFromGUI(DataValueStorage Data){
        DataPacket packet = CreatePacket();
        packet.SaveData(DataPacket.DEFAULT_DATA_STORAGE, Data);
        SendPacketToAround(packet);
    }

    @Override
    public void OnRecived(EventPacket packet) {
    }


    public DataPacket CreatePacket(){
        DataPacket packet = new DataPacket(-1);
        return packet;
    }

    @Override
    public boolean CanConnectToTile(TileEntity tile, ForgeDirection dir) {
        return true;
    }

    @Override
    public boolean CanRecive(EventPacket packet) {
        return false;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {
        super.readFromNBT(nbtTagCompound);

        if(nbtTagCompound.getBoolean("SavedDataNotNull"))
        SavedData = nbtTagCompound.getString("SavedData");
		SavedMode = nbtTagCompound.getInteger("SavedMode");

        Resetting = nbtTagCompound.getBoolean("Resetting");
        Reset = nbtTagCompound.getInteger("Reset");


    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound) {
        super.writeToNBT(nbtTagCompound);

        nbtTagCompound.setBoolean("SavedDataNotNull", SavedData != null);

        if(SavedData != null)
        nbtTagCompound.setString("SavedData", SavedData);
	    nbtTagCompound.setInteger("SavedMode", SavedMode);


        nbtTagCompound.setInteger("Reset", Reset);
        nbtTagCompound.setBoolean("Resetting", Resetting);

    }

	public void SendPacket(){
		ByteValues val = ByteValues.getValue(SavedMode);

		if(SavedData != null){
			PacketHandler.sendToServer(new DataConstructPacket(this, SavedData, SavedMode), CircuitMod.Utils.channels);

		}else if(val == ByteValues.OnSignal){
			PacketHandler.sendToServer(new DataConstructPacket(this, "empty", SavedMode), CircuitMod.Utils.channels);
		}

	}

}
