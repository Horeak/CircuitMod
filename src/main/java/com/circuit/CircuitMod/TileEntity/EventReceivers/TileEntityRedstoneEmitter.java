package com.circuit.CircuitMod.TileEntity.EventReceivers;

import MiscUtils.TileEntity.ModTileEntity;
import com.circuit.CircuitMod.Utils.ByteValues;
import com.circuit.CircuitMod.Utils.CircuitUtils.ICircuitConnector;
import com.circuit.CircuitMod.Utils.CircuitUtils.IEventRec;
import com.circuit.CircuitMod.Utils.EventPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;


public class TileEntityRedstoneEmitter extends ModTileEntity implements IEventRec, ICircuitConnector {

    public static int Finish = 2;
    public int Do = Finish;

    public void updateEntity(){

        if(Do < Finish){
            Do += 1;
        }else{
            //TODO Finish
//            if(worldObj.getBlockMetadata(xCoord, yCoord, zCoord) == 1) {
//                worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 0, 3);
//                worldObj.notifyBlocksOfNeighborChange(xCoord, yCoord, zCoord, worldObj.getBlock(xCoord, yCoord, zCoord));
//            }
        }
    }

    @Override
    public boolean CanConnectToTile(TileEntity tile, EnumFacing dir) {
        return true;
    }

    @Override
    public void OnRecived(EventPacket packet) {
        if(packet.ByteValue == ByteValues.OnSignal.Value()) {
            Do = 0;

//            if(worldObj.getBlockMetadata(xCoord, yCoord, zCoord) == 0) {
//                worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 1, 3);
//                worldObj.notifyBlocksOfNeighborChange(xCoord, yCoord, zCoord, worldObj.getBlock(xCoord, yCoord, zCoord));
//            }
        }
    }

    @Override
    public boolean CanRecive(EventPacket packet) {
        return true;
    }
}
