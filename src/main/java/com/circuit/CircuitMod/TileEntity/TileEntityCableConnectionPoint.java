package com.circuit.CircuitMod.TileEntity;

import com.circuit.CircuitMod.Utils.EventPacket;
import com.circuit.CircuitMod.TileEntity.CircuitUtils.IEventRec;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityCableConnectionPoint extends TileEntityEventSender {

    @Override
    public void OnRecived(EventPacket packet) {
        SendPacketToAround(packet);
    }


    @Override
    public boolean CanConnectToTile(TileEntity tile, ForgeDirection dir) {
        return tile instanceof TileEntityCircuitCable || tile instanceof TileEntityCableConnectionPoint || tile instanceof TileEntityEventSender || tile instanceof IEventRec;
    }
}
