package com.circuit.CircuitMod.TileEntity.EventSenders;

import com.circuit.CircuitMod.TileEntity.TileEntityEventSender;
import com.circuit.CircuitMod.Utils.ByteValues;
import com.circuit.CircuitMod.Utils.EventPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;


public class TileEntityRedstoneReciver extends TileEntityEventSender {

    public void updateEntity(){
        if(worldObj.isBlockPowered(getPos())){
            SendPacketToAround(new EventPacket(-1, ByteValues.OnSignal.Value()));
        }
    }

    @Override
    public void OnRecived(EventPacket packet) {

    }

    @Override
    public boolean CanRecive(EventPacket packet) {
        return false;
    }

    @Override
    public boolean CanConnectToTile(TileEntity tile, EnumFacing dir) {
        return true;
    }
}
