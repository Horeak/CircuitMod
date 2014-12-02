package com.circuit.CircuitMod.TileEntity;

import MiscUtils.TileEntity.ModTileEntity;
import com.circuit.CircuitMod.Utils.CircuitUtils.ICircuitConnector;
import com.circuit.CircuitMod.Utils.CircuitUtils.IEventRec;
import com.circuit.CircuitMod.Utils.EventPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

import javax.vecmath.Vector3d;

public abstract class TileEntityEventSender extends ModTileEntity implements IEventRec, ICircuitConnector{



    public void SendPacketTo(EventPacket packet,EnumFacing dir){

            if(dir != packet.LastSentFrom && dir !=null && packet != null && !packet.TimedOut) {
                if (worldObj.getTileEntity(new BlockPos(getPos().getX() + dir.getFrontOffsetX(), getPos().getY() + dir.getFrontOffsetY(), getPos().getZ() + dir.getFrontOffsetZ())) instanceof IEventRec) {
                    TileEntity tile = worldObj.getTileEntity(new BlockPos(getPos().getX() + dir.getFrontOffsetX(), getPos().getY() + dir.getFrontOffsetY(), getPos().getZ() + dir.getFrontOffsetZ()));

                    if (tile instanceof ICircuitConnector) {


                        if (((ICircuitConnector) tile).CanConnectToTile(this, dir.getOpposite()) && CanConnectToTile(tile, dir)) {


                            if (tile instanceof IEventRec) {
                                IEventRec tileE = (IEventRec) tile;
                                if (tileE.CanRecive(packet) && tileE != null) {
                                    Vector3d vec = new Vector3d(tile.getPos().getX(), tile.getPos().getY(), tile.getPos().getZ());

                                    if(!EventPacket.ContainesVactor(packet, vec)) {

                                        EventPacket sendPacket = packet.GetInstance();

                                        sendPacket.RecreatingPacket(packet);
                                        sendPacket.LastSentFrom = dir.getOpposite();
                                        sendPacket.Postitions.add(vec);


                                        tileE.OnRecived(sendPacket);



                                    }


                                }

                            }


                        }

                    }
                }

            }else{
                return;
            }

    }

    public void SendPacketToAround(EventPacket packet){
        for(EnumFacing dir : EnumFacing.values()){
            if(dir != packet.LastSentFrom && packet != null)
           SendPacketTo(packet, dir);
        }
    }





    @Override
    public abstract void OnRecived(EventPacket packet);

    @Override
    public boolean CanRecive(EventPacket packet) {
        return true;
    }

    @Override
    public abstract boolean CanConnectToTile(TileEntity tile, EnumFacing dir);
}
