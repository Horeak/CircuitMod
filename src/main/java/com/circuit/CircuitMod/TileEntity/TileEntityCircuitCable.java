package com.circuit.CircuitMod.TileEntity;

import com.circuit.CircuitMod.Utils.EventPacket;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;

import java.awt.*;

public class TileEntityCircuitCable extends TileEntityEventSender {

    public static Color BaseColor = new Color(0x002810);

    public int Color = 0;

    @Override
    public void OnRecived(EventPacket packet) {

        EnumFacing dk = packet.LastSentFrom;
        for(EnumFacing dkk : EnumFacing.values()){
            if(dkk != dk && dkk != Direction && dkk != Direction.getOpposite()){
                SendPacketTo(packet, dkk);

            }

        }

    }

    @Override
    public boolean CanConnectToTile(TileEntity tile, EnumFacing dir) {
        if(tile == null)
            return false;

        int metaZ = this.Color;
        int metaX = 0;

        if(tile instanceof TileEntityCableConnectionPoint)
            metaX = ((TileEntityCableConnectionPoint) tile).Color;

        else if(tile instanceof TileEntityCircuitCable)
            metaX = ((TileEntityCircuitCable) tile).Color;

        else if(tile instanceof TileEntityCircuitBox)
            metaX = ((TileEntityCircuitBox) tile).Color;

        boolean t = tile instanceof TileEntityCircuitCable || tile instanceof TileEntityCircuitBox;
        boolean g = metaX == 0 && metaZ == 0 || metaZ == metaX;

        boolean j = dir != null ? dir != Direction && dir != Direction.getOpposite() : false;

        return t && g && j || !t && j;
    }
    public EnumFacing Direction = EnumFacing.UP;

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {

        super.readFromNBT(nbtTagCompound);

        Color = nbtTagCompound.getInteger("Color");
        Direction = EnumFacing.getFront(nbtTagCompound.getInteger("DIR"));


    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound) {
        super.writeToNBT(nbtTagCompound);


        nbtTagCompound.setInteger("Color", Color);
        nbtTagCompound.setInteger("DIR", Direction.getIndex());

    }

}
