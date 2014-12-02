package com.circuit.CircuitMod.Utils;

import net.minecraft.util.EnumChatFormatting;

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
    public ArrayList<String> DataTagsFree = new ArrayList<String>();

    public EventPacket GetInstance(){
        return new DataPacket(TimeOut, ByteValue);
    }

    public void SaveData(String DataTag, String Data){
            DataStorage.put(DataTag, Data);
            DataStorageFree.add(DataTag + SPLIT_DATA_TAG + Data);
            DataTagsFree.add(DataTag);
    }

    public void RemoveData(String DataTag){
            String data = ReadData(DataTag);

            DataStorage.remove(DataTag, data);
            DataStorageFree.remove(DataTag + SPLIT_DATA_TAG + data);

        for(int i = 0; i < DataTagsFree.size(); i++){
            String t = DataTagsFree.get(i);

            if(t.equals(DataTag)){
                DataTagsFree.remove(i);

            }

        }


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

                    if(DataTagsFree.size() > 1){
                        text = (EnumChatFormatting.DARK_GRAY + "" + EnumChatFormatting.ITALIC +  "[" + DataTag + "]" + EnumChatFormatting.RESET) + text;
                    }

                    data = data + (data.isEmpty() ? "" : ", ") + text;
                }
            }
        }

        return data;
    }



    public void RecreatingPacket(EventPacket packet){
        super.RecreatingPacket(packet);

        DataPacket pack = (DataPacket)packet;

        DataStorage = pack.DataStorage;
        DataStorageFree = pack.DataStorageFree;
        DataTagsFree = pack.DataTagsFree;

    }

}
