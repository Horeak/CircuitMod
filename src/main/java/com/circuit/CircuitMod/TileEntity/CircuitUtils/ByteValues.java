package com.circuit.CircuitMod.TileEntity.CircuitUtils;

public enum ByteValues {

    OnSignal((byte)0);





    byte Value;
    ByteValues(byte v){
        Value = v;
    }

    public byte Value(){
        return Value;
    }

}
