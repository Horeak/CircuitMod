package com.circuit.CircuitMod.TileEntity;

import com.circuit.CircuitMod.Utils.EventPacket;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;


public class TileEntityCableConnectionPoint extends TileEntityEventSender {

    @Override
    public void OnRecived(EventPacket packet) {
        SendPacketToAround(packet);
    }


    public int Color = 0;

    @Override
    public boolean CanConnectToTile(TileEntity tile, EnumFacing dir) {
        int metaZ = Color;
        int metaX = 0;

        if(tile instanceof TileEntityCableConnectionPoint)
            metaX = ((TileEntityCableConnectionPoint) tile).Color;

        else if(tile instanceof TileEntityCircuitCable)
            metaX = ((TileEntityCircuitCable) tile).Color;

        else if(tile instanceof TileEntityCircuitBox)
            metaX = ((TileEntityCircuitBox) tile).Color;


        boolean g = metaX == 0 && metaZ != 0 || metaZ == 0 && metaX != 0 ? true : metaZ == metaX;
        boolean j = tile instanceof TileEntityCircuitCable || tile instanceof TileEntityCircuitBox || tile instanceof TileEntityCableConnectionPoint;

        return j && g || !j;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {
        super.readFromNBT(nbtTagCompound);

        Color = nbtTagCompound.getInteger("Color");


    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound) {
        super.writeToNBT(nbtTagCompound);

        nbtTagCompound.setInteger("Color", Color);

    }
}
