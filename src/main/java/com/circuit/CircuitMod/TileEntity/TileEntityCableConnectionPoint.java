package com.circuit.CircuitMod.TileEntity;

import com.circuit.CircuitMod.CircuitEvents.EventPacket;
import com.circuit.CircuitMod.TileEntity.CircuitUtils.IEventRec;
import net.minecraft.tileentity.TileEntity;

public class TileEntityCableConnectionPoint extends TileEntityEventSender {

    @Override
    public void OnRecived(EventPacket packet) {
        SendPacketToAround(packet);
    }


    @Override
    public boolean CanConnectToTile(TileEntity tile) {
        return tile instanceof TileEntityCircuitCable || tile instanceof TileEntityCableConnectionPoint || tile instanceof TileEntityEventSender || tile instanceof IEventRec;
    }
}
