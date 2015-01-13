package com.circuit.CircuitMod.TileEntity.DataBlocks;

import com.circuit.CircuitMod.TileEntity.TileEntityEventSender;
import com.circuit.CircuitMod.Utils.ByteValues;
import com.circuit.CircuitMod.Utils.CircuitUtils.IDataRec;
import com.circuit.CircuitMod.Utils.DataPacket;
import com.circuit.CircuitMod.Utils.DataStorage.DataValueStorage;
import com.circuit.CircuitMod.Utils.EventPacket;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.HashMap;
import java.util.Map;

public class TileEntityDataConverter extends TileEntityEventSender implements IDataRec {

    public ForgeDirection dir = ForgeDirection.UNKNOWN;
    public String DataTagUse = DataPacket.DEFAULT_DATA_STORAGE, DataTagFrom = DataPacket.DEFAULT_DATA_STORAGE;

    @Override
    public boolean CanRecive(EventPacket packet) {
        return packet instanceof DataPacket && packet.ByteValue == ByteValues.DataSignal.Value() && packet.LastSentFrom != dir;
    }

    @Override
    public boolean CanReceivePacketWireless(EventPacket packet, int Channel) {
        return false;
    }

    @Override
    public void OnRecived(EventPacket packet) {
        DataPacket pack = (DataPacket) packet;

        if (pack != null && pack.GetDataAcces() != null){

            HashMap<String, DataValueStorage> Temp = new HashMap<String, DataValueStorage>();
                for(Map.Entry<String, DataValueStorage> ent : pack.GetDataAcces().entrySet()){
                    if(ent.getKey().equals(DataTagFrom)){
                        Temp.put(DataTagUse, ent.getValue());
                    }
                }


            pack.SetData(Temp);


}

        if(!pack.GetTotalData().isEmpty()) {
            SendPacketTo(pack, dir);

        }

    }

    @Override
    public boolean CanConnectToTile(TileEntity tile, ForgeDirection dir) {
        return true;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {
        super.readFromNBT(nbtTagCompound);

        dir = ForgeDirection.getOrientation(nbtTagCompound.getInteger("Dir"));
        DataTagUse = nbtTagCompound.getString("Tag");
        DataTagFrom = nbtTagCompound.getString("DataTagFrom");


    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound) {
        super.writeToNBT(nbtTagCompound);

        nbtTagCompound.setInteger("Dir", dir.ordinal());
        nbtTagCompound.setString("Tag", DataTagUse);
        nbtTagCompound.setString("DataTagFrom", DataTagFrom);

    }
}
