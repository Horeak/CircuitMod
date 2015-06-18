package com.circuit.CircuitMod.Utils;

public enum ByteValues {

    OnSignal((byte)0, "redstone", "Redstone"),
    OneDigitNumber((byte)1, "singelNumber", "One Digit Number"),
    MultiDigitNumber((byte)2, "multiNumber", "Multi Digit Number"),
    DataSignal((byte)3, "data", "Data");





    byte Value;
    public String Id, Name;
    ByteValues(byte v, String id, String name){
        Value = v;
        this.Id = id;
        this.Name = name;
    }

    public byte Value(){
        return Value;
    }


	public static ByteValues getValue(int i ){
		for(ByteValues val : ByteValues.values()){
			if(val.Value == i){
				return val;
			}
		}

		return null;
	}
}
