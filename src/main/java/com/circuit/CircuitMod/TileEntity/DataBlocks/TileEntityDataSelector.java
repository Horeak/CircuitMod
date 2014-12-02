package com.circuit.CircuitMod.TileEntity.DataBlocks;

import com.circuit.CircuitMod.TileEntity.TileEntityEventSender;
import com.circuit.CircuitMod.Utils.ByteValues;
import com.circuit.CircuitMod.Utils.CircuitUtils.IDataRec;
import com.circuit.CircuitMod.Utils.DataPacket;
import com.circuit.CircuitMod.Utils.EventPacket;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;

public class TileEntityDataSelector extends TileEntityEventSender implements IDataRec {

    public EnumFacing dir = null;
    public String DataTagUse = DataPacket.DEFAULT_DATA_STORAGE;

    //0 = Whitelist
    //1 = Blacklist
    public int Mode = 0;

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
        DataPacket pack = (DataPacket)packet;

       if(pack != null && pack.DataStorageFree != null)
        for(int i = 0; i < pack.DataStorageFree.size(); i++){
            String t = pack.DataStorageFree.get(i);

            boolean Keep = false;

                String[] dt_Text = t.split(DataPacket.SPLIT_DATA_TAG);
                String DataTag = dt_Text[0];

            if(Mode == 0){
                if(DataTag.equals(DataTagUse)) {
                    Keep = true;
                }

            }

            if(Mode == 1){
                if(!DataTag.equals(DataTagUse)) {
                    Keep = true;
                }
            }

            if(!Keep)
                pack.RemoveData(DataTag);


            }

        if(!pack.GetTotalData().isEmpty())
        SendPacketTo(pack, dir);


    }

    @Override
    public boolean CanConnectToTile(TileEntity tile, EnumFacing dir) {
        return true;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {
        super.readFromNBT(nbtTagCompound);

        dir = EnumFacing.getFront(nbtTagCompound.getInteger("Dir"));
        DataTagUse = nbtTagCompound.getString("Tag");


    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound) {
        super.writeToNBT(nbtTagCompound);

        nbtTagCompound.setInteger("Dir", dir.ordinal());
        nbtTagCompound.setString("Tag", DataTagUse);

    }
}
