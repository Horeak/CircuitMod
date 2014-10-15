package com.circuit.CircuitMod.TileEntity.CircuitUtils;

import com.circuit.CircuitMod.CircuitEvents.EventPacket;

public interface IEventRec {

    public void OnRecived(EventPacket packet);
    public boolean CanRecive(EventPacket packet);
}
