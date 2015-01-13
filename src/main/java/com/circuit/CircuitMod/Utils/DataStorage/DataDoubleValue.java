package com.circuit.CircuitMod.Utils.DataStorage;

public class DataDoubleValue extends DataValueStorage {

    double data = 0;

    public DataDoubleValue(double ob) {
        super(ob);

        data = ob;
    }

    @Override
    public Double GetStoredObject() {
        return data;
    }

    @Override
    public String GetObjectString() {
        return Double.toString(data);
    }
}
