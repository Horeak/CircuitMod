package com.circuit.CircuitMod.Utils.DataStorage;

public class DataFloatValue extends DataValueStorage {

    float data = 0;

    public DataFloatValue(float ob) {
        super(ob);

        data = ob;
    }

    @Override
    public Float GetStoredObject() {
        return data;
    }

    @Override
    public String GetObjectString() {
        return Float.toString(data);
    }
}
