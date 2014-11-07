package com.circuit.CircuitMod.Utils.CircuitUtils;

import com.circuit.CircuitMod.Utils.EventPacket;

public interface IEventRec {

    public void OnRecived(EventPacket packet);
    public boolean CanRecive(EventPacket packet);
}
