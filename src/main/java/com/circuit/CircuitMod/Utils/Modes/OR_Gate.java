package com.circuit.CircuitMod.Utils.Modes;

import com.circuit.CircuitMod.CircuitEvents.EventPacket;
import com.circuit.CircuitMod.TileEntity.TileEntityCircuitBox;

public class OR_Gate extends CircuitBoxMode {
    @Override
    public String ModeName() {
        return "OR";
    }

    @Override
    public String GetID() {
        return "OR";
    }

    @Override
    public int MinInputs() {
        return 1;
    }

    @Override
    public int MaxInputs() {
        return 3;
    }


    @Override
    public void OnUpdate(TileEntityCircuitBox tile, EventPacket packet) {
                tile.SendPacketTo(packet, tile.GetOutputSide());
    }
}
