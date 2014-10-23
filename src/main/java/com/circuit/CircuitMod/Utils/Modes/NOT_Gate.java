package com.circuit.CircuitMod.Utils.Modes;

import com.circuit.CircuitMod.CircuitEvents.EventPacket;
import com.circuit.CircuitMod.TileEntity.TileEntityCircuitBox;

public class NOT_Gate extends CircuitBoxMode {
    @Override
    public String ModeName() {
        return "NOT";
    }

    @Override
    public String GetID() {
        return "NOT";
    }

    @Override
    public int MinInputs() {
        return 0;
    }

    @Override
    public int MaxInputs() {
        return 0;
    }

    @Override
    public void OnUpdate(TileEntityCircuitBox tile, EventPacket packet) {
        tile.SendPacketTo(packet, tile.GetOutputSide());
    }

    @Override
    public boolean OutputtingSignal(TileEntityCircuitBox tile) {
        return tile.GetActiveInputs() == 0;
    }
}
