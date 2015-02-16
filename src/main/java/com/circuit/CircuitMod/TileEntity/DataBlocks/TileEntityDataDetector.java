package com.circuit.CircuitMod.TileEntity.DataBlocks;

import com.circuit.CircuitMod.TileEntity.TileEntityEventSender;
import com.circuit.CircuitMod.Utils.ByteValues;
import com.circuit.CircuitMod.Utils.DataPacket;
import com.circuit.CircuitMod.Utils.EventPacket;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

import javax.vecmath.Vector3d;

public class TileEntityDataDetector extends TileEntityEventSender {

    public ForgeDirection dir = ForgeDirection.UNKNOWN;

    @Override
    public void OnRecived(EventPacket packet) {
        SendPacketTo(packet, dir);

        if(dir == null || dir == ForgeDirection.UNKNOWN || packet.LastSentFrom.getOpposite() == dir){
            System.err.println("Invalid direction! Send dir: " + dir + " Packet dir: " + packet.LastSentFrom);
            return;
        }


        if(packet != null) {
            SendPacketTo(new EventPacket(-1, ByteValues.OnSignal.Value()), dir);
        }else{
            System.err.println("Invalid packet!");
        }

    }

    @Override
    public boolean CanConnectToTile(TileEntity tile, ForgeDirection dir) {
        return true;
    }

    @Override
    public boolean CanRecive(EventPacket packet) {
        boolean hasBeen = EventPacket.ContainesVactor(packet, new Vector3d(xCoord, yCoord, zCoord));
        return packet instanceof DataPacket && !hasBeen && packet.LastSentFrom != dir;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {
        super.readFromNBT(nbtTagCompound);

        dir = ForgeDirection.getOrientation(nbtTagCompound.getInteger("Dir"));
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound) {
        super.writeToNBT(nbtTagCompound);

        nbtTagCompound.setInteger("Dir", dir.ordinal());
    }

}