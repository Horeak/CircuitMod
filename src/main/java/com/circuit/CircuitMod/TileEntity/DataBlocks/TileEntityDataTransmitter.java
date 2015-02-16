package com.circuit.CircuitMod.TileEntity.DataBlocks;

import com.circuit.CircuitMod.TileEntity.TileEntityEventSender;
import com.circuit.CircuitMod.Utils.ByteValues;
import com.circuit.CircuitMod.Utils.CircuitUtils.IDataRec;
import com.circuit.CircuitMod.Utils.CircuitUtils.IEventRec;
import com.circuit.CircuitMod.Utils.EventPacket;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.ForgeDirection;

import javax.vecmath.Vector3d;
import java.util.List;

public class TileEntityDataTransmitter extends TileEntityEventSender implements IDataRec {

    public static final int SIGNAL_RANGE = 50;

    public int DataChannel = 0;

    public void SendPacket(EventPacket packet, int rad) {

        if (!getWorldObj().isRemote) {
            WorldServer world = (WorldServer) getWorldObj();

            int Radius = rad;
            List list = world.func_147486_a(xCoord - Radius, yCoord - Radius, zCoord - Radius, xCoord + Radius, yCoord + Radius, zCoord + Radius);


            for (Object r : list) {
                if (r instanceof TileEntity) {
                    TileEntity te = (TileEntity) r;

                    if (te instanceof IDataRec
                            && te instanceof IEventRec) {

                        IDataRec data = (IDataRec) te;
                        IEventRec rec = (IEventRec) te;


                        try {
                            if (data.CanReceivePacketWireless(packet, DataChannel)) {
                                if (rec.CanRecive(packet)) {
                                    Vector3d vec = new Vector3d(te.xCoord, te.yCoord, te.zCoord);

                                    if (!EventPacket.ContainesVactor(packet, vec)) {

                                        EventPacket sendPacket = packet.GetInstance();

                                        sendPacket.RecreatingPacket(packet);
                                        sendPacket.LastSentFrom = ForgeDirection.UNKNOWN;
                                        sendPacket.Postitions.add(vec);
                                        rec.OnRecived(sendPacket);

                                    }

                                }
                            }

                        }catch(Exception e){
                            e.printStackTrace();
                        }
                        }
                    }
                }

        }
    }


    public void SendPacket(EventPacket packet){

        SendPacket(packet, SIGNAL_RANGE);
    }

    @Override
    public void OnRecived(EventPacket packet) {
        SendPacket(packet);
    }




    @Override
    public boolean CanRecive(EventPacket packet) {
        return packet.ByteValue == ByteValues.DataSignal.Value();
    }

    @Override
    public boolean CanConnectToTile(TileEntity tile, ForgeDirection dir) {
        return true;
    }

    @Override
    public boolean CanReceivePacketWireless(EventPacket packet, int channel) {
        return false;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {
        super.readFromNBT(nbtTagCompound);

        DataChannel = nbtTagCompound.getInteger("Channel");

    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound) {
        super.writeToNBT(nbtTagCompound);

        nbtTagCompound.setInteger("Channel", DataChannel);

    }

}
