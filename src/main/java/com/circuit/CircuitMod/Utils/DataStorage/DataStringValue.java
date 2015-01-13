package com.circuit.CircuitMod.Utils.DataStorage;

public class DataStringValue extends DataValueStorage {

    String data = null;

    public DataStringValue(String ob) {
        super(ob);
        data = ob;
    }

    @Override
    public String GetStoredObject() {
        return data;
    }

    @Override
    public String GetObjectString() {
        return data;
    }


}
