package com.circuit.CircuitMod.TileEntity;

import MiscUtils.TileEntity.ModTileEntity;
import com.circuit.CircuitMod.TileEntity.CircuitUtils.ICircuitConnector;
import com.circuit.CircuitMod.TileEntity.CircuitUtils.IEventRec;
import com.circuit.CircuitMod.Utils.EventPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

import javax.vecmath.Vector3d;

public abstract class TileEntityEventSender extends ModTileEntity implements IEventRec, ICircuitConnector{



    public void SendPacketTo(EventPacket packet,ForgeDirection dir){

            if(dir != packet.LastSentFrom && dir != ForgeDirection.UNKNOWN && packet != null && !packet.TimedOut) {
                if (worldObj.getTileEntity(xCoord + dir.offsetX, yCoord + dir.offsetY, zCoord + dir.offsetZ) instanceof IEventRec) {
                    TileEntity tile = worldObj.getTileEntity(xCoord + dir.offsetX, yCoord + dir.offsetY, zCoord + dir.offsetZ);

                    if (tile instanceof ICircuitConnector) {


                        if (((ICircuitConnector) tile).CanConnectToTile(this, dir.getOpposite()) && CanConnectToTile(tile, dir)) {


                            if (tile instanceof IEventRec) {
                                IEventRec tileE = (IEventRec) tile;
                                if (tileE.CanRecive(packet) && tileE != null) {
                                    Vector3d vec = new Vector3d(tile.xCoord, tile.yCoord, tile.zCoord);

                                    if(!EventPacket.ContainesVactor(packet, vec)) {
                                        packet.LastSentFrom = dir.getOpposite();
                                        packet.Resend();
                                        packet.Postitions.add(vec);


                                        tileE.OnRecived(packet);

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
        for(ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS){
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
    public abstract boolean CanConnectToTile(TileEntity tile, ForgeDirection dir);
}
