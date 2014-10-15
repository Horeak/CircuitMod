package com.circuit.CircuitMod.TileEntity;

import com.circuit.CircuitMod.CircuitEvents.EventPacket;
import com.circuit.CircuitMod.CircuitEvents.RedstonePacket;
import net.minecraft.tileentity.TileEntity;

public class TileEntityRedstoneReciver extends TileEntityEventSender {

    public void updateEntity(){

        if(worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord)){

            SendPacketToAround(new RedstonePacket());
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
    public boolean CanConnectToTile(TileEntity tile) {
        return true;
    }
}
