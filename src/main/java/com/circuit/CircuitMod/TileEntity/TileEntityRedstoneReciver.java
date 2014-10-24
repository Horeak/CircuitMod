package com.circuit.CircuitMod.TileEntity;

import com.circuit.CircuitMod.Utils.EventPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityRedstoneReciver extends TileEntityEventSender {

    public void updateEntity(){

        if(worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord)){

            SendPacketToAround(new EventPacket());
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
    public boolean CanConnectToTile(TileEntity tile, ForgeDirection dir) {
        return true;
    }
}
