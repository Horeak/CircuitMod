package com.circuit.CircuitMod.Utils;

public enum ByteValues {

    OnSignal((byte)0),
    OneDigitNumber((byte)1),
    MultiDigitNumber((byte)2),
    DataSignal((byte)3);





    byte Value;
    ByteValues(byte v){
        Value = v;
    }

    public byte Value(){
        return Value;
    }

}
