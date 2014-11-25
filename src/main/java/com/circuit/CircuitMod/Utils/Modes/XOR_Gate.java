package com.circuit.CircuitMod.Utils.Modes;

import com.circuit.CircuitMod.TileEntity.TileEntityCircuitBox;
import com.circuit.CircuitMod.Utils.EventPacket;

public class XOR_Gate extends CircuitBoxMode {
    @Override
    public String ModeName() {
        return "XOR";
    }

    @Override
    public String GetID() {
        return "XOR";
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

    @Override
    public boolean OutputtingSignal(TileEntityCircuitBox tile) {
        return tile.GetActiveInputs() == 1;
    }
}
