package com.circuit.CircuitMod.Utils.Modes;

import com.circuit.CircuitMod.CircuitEvents.EventPacket;
import com.circuit.CircuitMod.TileEntity.TileEntityCircuitBox;

public class AND_Gate extends CircuitBoxMode {
    @Override
    public String ModeName() {
        return "AND";
    }


    @Override
    public void OnUpdate(TileEntityCircuitBox tile, EventPacket packet) {

       int g = 0;

        for(int i = 0; i < tile.Sides.length; i++){
            if(tile.Sides[i]){
                g += 1;
            }
        }


        if(g >= 2){
            tile.SendPacketTo(packet, tile.GetOutputSide());

        }
    }
}
