package com.circuit.CircuitMod.CircuitEvents;

public class ActivatedPacket extends EventPacket {
    @Override
    public String ID() {
        return "ActivatedPacket";
    }

    @Override
    public EventPacket GetInstance() {
        return new ActivatedPacket();
    }
}
