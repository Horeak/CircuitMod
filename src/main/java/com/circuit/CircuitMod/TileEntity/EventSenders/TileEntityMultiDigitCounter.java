package com.circuit.CircuitMod.TileEntity.EventSenders;

import com.circuit.CircuitMod.Utils.ByteValues;
import com.circuit.CircuitMod.TileEntity.Utils.TileEntityCounterBase;
import com.circuit.CircuitMod.Utils.DataStorage.DataIntegerValue;
import com.circuit.CircuitMod.Utils.EventPacket;

public class TileEntityMultiDigitCounter extends TileEntityCounterBase {


    public TileEntityMultiDigitCounter(){
        ResetAt = 10;
    }

    @Override
    public int DefReset() {
        return 9999;
    }

    @Override
    public int ResetTo() {
        return 0;
    }

    @Override
    public byte OutputValue() {
        return CurrentCount > 9 ? ByteValues.MultiDigitNumber.Value() : ByteValues.OneDigitNumber.Value();
    }

    @Override
    public int OutputTimeout() {
        return -1;
    }

    @Override
    public byte InputValue() {
        return ByteValues.MultiDigitNumber.Value();
    }


    @Override
    public boolean IncrementOrDecrement() {
        return true;
    }

    @Override
    public int ResetCountTimer() {
        return 4;
    }

    @Override
    public int ResetChangeToBase() {
        return 1;
    }

    @Override
    public boolean OutputsSignalOnReset() {
        return true;
    }

    @Override
    public boolean CanRecive(EventPacket packet) {
        return packet.ByteValue == ByteValues.OneDigitNumber.Value() || packet.ByteValue == ByteValues.MultiDigitNumber.Value() || packet.ByteValue == ByteValues.OnSignal.Value();
    }

    @Override
    public void OnRecived(EventPacket packet) {

        if(packet.ByteValue == ByteValues.OneDigitNumber.Value() || packet.ByteValue == ByteValues.MultiDigitNumber.Value()){
            Change = packet.Data instanceof DataIntegerValue ? ((DataIntegerValue)packet.Data).GetStoredObject() : 0;
            ResetCount = 0;
        }



        if(packet.ByteValue == ByteValues.OnSignal.Value()){
            ResetCountInput = 0;

            if(!IsPowered) {
                ChangeNumber();
                packet.TimeOut();
            }
        }


    }

}
