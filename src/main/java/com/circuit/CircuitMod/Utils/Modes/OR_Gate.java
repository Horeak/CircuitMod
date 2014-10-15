package com.circuit.CircuitMod.Utils.Modes;

import com.circuit.CircuitMod.CircuitEvents.EventPacket;
import com.circuit.CircuitMod.TileEntity.TileEntityCircuitBox;

public class OR_Gate extends CircuitBoxMode {
    @Override
    public String ModeName() {
        return "OR";
    }


    @Override
    public void OnUpdate(TileEntityCircuitBox tile, EventPacket packet) {
        for(int i = 0; i < tile.Sides.length; i++){
            if(tile.Sides[i]){
                tile.SendPacketTo(packet, tile.GetOutputSide());
            }
        }
    }
}
