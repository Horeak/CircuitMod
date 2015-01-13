package com.circuit.CircuitMod.Utils.DataStorage;

public class DataIntegerValue extends DataValueStorage {

    int data = 0;

    public DataIntegerValue(int ob) {
        super(ob);
        data = ob;

    }

    @Override
    public Integer GetStoredObject() {
        return data;
    }

    @Override
    public String GetObjectString() {
        return Integer.toString(data);
    }
}
