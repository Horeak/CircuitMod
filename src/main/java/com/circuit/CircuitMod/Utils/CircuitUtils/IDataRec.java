package com.circuit.CircuitMod.Utils.CircuitUtils;

import com.circuit.CircuitMod.Utils.EventPacket;

public interface IDataRec {

    boolean CanReceivePacketWireless(EventPacket packet, int Channel);
}
