package com.circuit.CircuitMod.Utils.Modes;

import com.circuit.CircuitMod.Utils.EventPacket;
import com.circuit.CircuitMod.TileEntity.TileEntityCircuitBox;

public class AND_Gate_2 extends CircuitBoxMode {
    @Override
    public String ModeName() {
        return "AND";
    }

    @Override
    public String GetID() {
        return "AND_2";
    }

    @Override
    public int MinInputs() {
        return 3;
    }

    @Override
    public int MaxInputs() {
        return 3;
    }


    @Override
    public void OnUpdate(TileEntityCircuitBox tile, EventPacket packet) {
        tile.SendPacketTo(packet, tile.GetOutputSide());
    }

    @Override
    public boolean OutputtingSignal(TileEntityCircuitBox tile) {
        return tile.GetActiveInputs() == 3;
    }
}
