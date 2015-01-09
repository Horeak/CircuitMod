package com.circuit.CircuitMod.TileEntity.DataBlocks;

import MiscUtils.TileEntity.ModTileEntity;
import com.circuit.CircuitMod.Utils.ByteValues;
import com.circuit.CircuitMod.Utils.CircuitUtils.ICircuitConnector;
import com.circuit.CircuitMod.Utils.CircuitUtils.IEventRec;
import com.circuit.CircuitMod.Utils.DataPacket;
import com.circuit.CircuitMod.Utils.EventPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityDataScreen extends ModTileEntity implements IEventRec, ICircuitConnector {


    boolean CanReceive = true;
    public DataPacket CurrentPacket = null;

    int UpdateTick = 0;
    static int ToUpdate = 20;

    public void updateEntity(){
        CanReceive = CurrentPacket == null;

        if(!CanReceive){
            if(UpdateTick >= ToUpdate){
                UpdateTick = 0;
                CanReceive = true;
                CurrentPacket = null;
            }else{
                UpdateTick += 1;
            }

        }else if(UpdateTick > 0){
            UpdateTick = 0;
        }


    }

    @Override
    public void OnRecived(EventPacket packet) {
        CurrentPacket = (DataPacket)packet;
        UpdateTick = 0;

    }



    @Override
    public boolean CanRecive(EventPacket packet) {
        if(!(packet instanceof DataPacket))
                return false;

        boolean t = CanReceive || packet.equals(CurrentPacket) || ((DataPacket) packet).GetTotalData().equalsIgnoreCase(CurrentPacket.GetTotalData());

        return packet.ByteValue == ByteValues.DataSignal.Value() && packet instanceof DataPacket && t;
    }

    @Override
    public boolean CanConnectToTile(TileEntity tile, ForgeDirection dir) {
        return true;
    }
}
