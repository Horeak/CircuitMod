package com.circuit.CircuitMod.TileEntity;

import com.circuit.CircuitMod.CircuitEvents.EventPacket;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

import java.awt.*;

public class TileEntityCircuitCable extends TileEntityEventSender {

    public static Color BaseColor = new Color(0x002810);

    @Override
    public void OnRecived(EventPacket packet) {

        ForgeDirection dk = packet.LastSentFrom;

        for(ForgeDirection dkk : ForgeDirection.VALID_DIRECTIONS){
            if(dkk != dk && dkk != Direction && dkk != Direction.getOpposite()){
                SendPacketTo(packet, dkk);

            }

        }

    }

    @Override
    public boolean CanConnectToTile(TileEntity tile) {
        if(tile == null)
            return false;

        int metaZ = worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
        int metaX = tile.getWorldObj().getBlockMetadata(tile.xCoord, tile.yCoord, tile.zCoord);
        boolean t = tile instanceof TileEntityCircuitCable || tile instanceof TileEntityCircuitBox;
        boolean g = metaZ == 0 && metaX == 0 || metaZ == metaX;

        return t && g || !t;
    }
    public ForgeDirection Direction = ForgeDirection.UP;

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {

        super.readFromNBT(nbtTagCompound);

        Direction = ForgeDirection.getOrientation(nbtTagCompound.getInteger("DIR"));


    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound) {
        super.writeToNBT(nbtTagCompound);

        nbtTagCompound.setInteger("DIR", Direction.ordinal());

    }

}
