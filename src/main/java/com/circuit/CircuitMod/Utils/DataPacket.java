package com.circuit.CircuitMod.Utils;

import java.util.ArrayList;
import java.util.HashMap;

public class DataPacket extends EventPacket {
    public DataPacket(int TimeOut, byte Value) {
        super(TimeOut, Value);
    }

    public static final String DEFAULT_DATA_STORAGE = "Data";
    public static final String SPLIT_DATA_TAG = "_|_";

    private HashMap<String, String> DataStorage = new HashMap<String, String>();
    public ArrayList<String> DataStorageFree = new ArrayList<String>();

    public EventPacket GetInstance(){
        return new DataPacket(TimeOut, ByteValue);
    }

    public void SaveData(String DataTag, String Data){
            DataStorage.put(DataTag, Data);
            DataStorageFree.add(DataTag + SPLIT_DATA_TAG + Data);
    }

    public void RemoveData(String DataTag){
            String data = ReadData(DataTag);

            DataStorage.remove(DataTag, data);
            DataStorageFree.remove(DataTag + SPLIT_DATA_TAG + data);

    }

    public String ReadData(String DataTag){
        String Data = DataStorage.get(DataTag);

        return Data;
    }

    public String GetTotalData(){
        return GetTotalDataExcluding("");
    }

    public String GetTotalDataExcluding(String Exclude){
        String data = "";

        for(String t : DataStorageFree){
            String[] dt_Text = t.split(SPLIT_DATA_TAG);
            if(dt_Text.length >= 2) {
                String DataTag = dt_Text[0];

                if(Exclude.isEmpty() || !DataTag.equalsIgnoreCase(Exclude)) {

                    String text = dt_Text[2];

                    data = data + (data.isEmpty() ? "" : ", ") + DataTag + ": " + text;
                }
            }
        }

        return data;
    }

    public String GetTotalDataWithDataTag(String dataTag){
        String data = "";

        for(String t : DataStorageFree){
            String[] dt_Text = t.split(SPLIT_DATA_TAG);
            if(dt_Text.length >= 2) {
                String DataTag = dt_Text[0];

                if(!DataTag.isEmpty() || DataTag.equalsIgnoreCase(dataTag)) {

                    String text = dt_Text[2];

                    data = data + (data.isEmpty() ? "" : ", ") + DataTag + ": " + text;
                }
            }
        }

        return data;
    }


    public String[] GetTotalDataSplit(){
        String[] data = new String[DataStorageFree.size()];

        int g = 0;
        for(String t : DataStorageFree){
            String[] dt_Text = t.split(SPLIT_DATA_TAG);
            if(dt_Text.length >= 2) {
                String text = dt_Text[2];

                data[g] = text;

                g += 1;
            }
        }

        return data;

    }


    public void RecreatingPacket(EventPacket packet){
        DataPacket pack = (DataPacket)packet;

        DataStorage = pack.DataStorage;
        DataStorageFree = pack.DataStorageFree;

    }

}
