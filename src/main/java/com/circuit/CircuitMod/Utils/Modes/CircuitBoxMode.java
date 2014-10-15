package com.circuit.CircuitMod.Utils.Modes;

import com.circuit.CircuitMod.CircuitEvents.EventPacket;
import com.circuit.CircuitMod.TileEntity.TileEntityCircuitBox;

public abstract class CircuitBoxMode {

    public abstract String ModeName();


    public abstract void OnUpdate(TileEntityCircuitBox tile, EventPacket packet);
}
