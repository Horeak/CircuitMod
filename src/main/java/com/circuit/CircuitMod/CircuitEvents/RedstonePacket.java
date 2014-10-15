package com.circuit.CircuitMod.CircuitEvents;

public class RedstonePacket extends EventPacket {


    @Override
    public String ID() {
        return "RedstonePacket";
    }



    @Override
    public EventPacket GetInstance() {
        return new RedstonePacket();
    }
}
