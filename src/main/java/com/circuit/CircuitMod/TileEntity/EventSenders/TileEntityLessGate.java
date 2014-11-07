package com.circuit.CircuitMod.TileEntity.EventSenders;

import com.circuit.CircuitMod.Utils.ByteValues;
import com.circuit.CircuitMod.TileEntity.Utils.TileEntityTwoSidedEventChecker;
import com.circuit.CircuitMod.Utils.EventPacket;

public class TileEntityLessGate  extends TileEntityTwoSidedEventChecker {
    @Override
    public byte InputPacketA() {
        return -1;
    }

    @Override
    public byte InputPacketB() {
        return -1;
    }

    @Override
    public EventPacket OutPutPacket() {

        if(packetA != null && packetB != null){
            if(packetA.ByteValue == ByteValues.OneDigitNumber.Value() || packetA.ByteValue == ByteValues.MultiDigitNumber.Value()){
                if(packetB.ByteValue == ByteValues.OneDigitNumber.Value() || packetB.ByteValue == ByteValues.MultiDigitNumber.Value()){

                    if(packetA.NBT.getInteger("StoredNumber") < packetB.NBT.getInteger("StoredNumber")){
                        return new EventPacket(-1, ByteValues.OnSignal.Value());
                    }

                }
            }
        }


        return null;
    }

    @Override
    public boolean CanRecive(EventPacket packet) {
        return packet.ByteValue == ByteValues.OneDigitNumber.Value() || packet.ByteValue == ByteValues.MultiDigitNumber.Value();
    }
}
