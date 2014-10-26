package com.circuit.CircuitMod.TileEntity;

import com.circuit.CircuitMod.Utils.EventPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityCableConnectionPoint extends TileEntityEventSender {

    @Override
    public void OnRecived(EventPacket packet) {
        SendPacketToAround(packet);
    }


    @Override
    public boolean CanConnectToTile(TileEntity tile, ForgeDirection dir) {
        int metaZ = worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
        int metaX = tile.getWorldObj().getBlockMetadata(tile.xCoord, tile.yCoord, tile.zCoord);

        boolean g = metaX == 0 && metaZ != 0 || metaZ == 0 && metaX != 0 ? true : metaZ == metaX;
        boolean j = tile instanceof TileEntityCircuitCable || tile instanceof TileEntityCircuitBox || tile instanceof TileEntityCableConnectionPoint;

        return j && g || !j;
    }
}
