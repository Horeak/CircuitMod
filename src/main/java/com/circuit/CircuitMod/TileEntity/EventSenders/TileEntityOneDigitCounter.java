package com.circuit.CircuitMod.TileEntity.EventSenders;

import com.circuit.CircuitMod.Utils.ByteValues;
import com.circuit.CircuitMod.TileEntity.Utils.TileEntityCounterBase;

public class TileEntityOneDigitCounter extends TileEntityCounterBase{


    @Override
    public int DefReset() {
        return 10;
    }

    @Override
    public int ResetTo() {
        return 0;
    }

    @Override
    public byte OutputValue() {
        return ByteValues.OneDigitNumber.Value();
    }

    @Override
    public int OutputTimeout() {
        return -1;
    }

    @Override
    public byte InputValue() {
        return ByteValues.OneDigitNumber.Value();
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


}
