package com.circuit.CircuitMod.TileEntity.EventSenders;

import com.circuit.CircuitMod.TileEntity.CircuitUtils.ByteValues;
import com.circuit.CircuitMod.TileEntity.Utils.TileEntityTwoSidedEventChecker;
import com.circuit.CircuitMod.Utils.EventPacket;

public class TileEntityAdditionGate extends TileEntityTwoSidedEventChecker {
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

                    int total = packetA.NBT.getInteger("StoredNumber") + packetB.NBT.getInteger("StoredNumber");

                    if(total > 9999)
                        total = 9999;

                    EventPacket packet = new EventPacket(-1, total > 9 ? ByteValues.MultiDigitNumber.Value() : ByteValues.OneDigitNumber.Value());
                    packet.NBT.setInteger("StoredNumber", total);

                        return packet;


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
