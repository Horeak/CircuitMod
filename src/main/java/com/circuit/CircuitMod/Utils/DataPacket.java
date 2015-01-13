package com.circuit.CircuitMod.Utils;

import net.minecraft.util.EnumChatFormatting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DataPacket extends EventPacket {
    public DataPacket(int TimeOut, byte Value) {
        super(TimeOut, Value);
    }

    public static final String DEFAULT_DATA_STORAGE = "Data";

    private HashMap<String, String> DataStorage = new HashMap<String, String>();

    public boolean Encrypted = false;
    private String EncryptedKey = null;

    private String DecryptionKey = null;



    public boolean CanAccessData(){
        return Encrypted && DecryptionKey != null && EncryptedKey != null && DecryptionKey.equals(EncryptedKey) || !Encrypted;
    }

    //Since strings are so secure for saving passwords... (not at all)
    public boolean DecryptionUseAccess(String Password){
        if(!Encrypted)
            return false;

        DecryptionKey = Password;
        return CanAccessData();
    }


    public boolean DecryptPacket(String Password){
        if(!Encrypted)
            return false;

        DecryptionKey = Password;

        if(CanAccessData()){
            Encrypted = false;
            EncryptedKey = null;

            DecryptionKey = null;

            return true;
        }

        DecryptionKey = null;
        return false;
    }


    public void Encrypt(String Password){
        EncryptedKey = Password;
        Encrypted = true;
    }






    public EventPacket GetInstance(){
            return new DataPacket(TimeOut, ByteValue);
    }





    public void SaveData(String DataTag, String Data){
        if(!CanAccessData())
            return;

            DataStorage.put(DataTag, Data);
    }


    public void RemoveData(String DataTag){
        if(!CanAccessData())
            return;

        for(Map.Entry<String, String> ent : DataStorage.entrySet()){
            if(ent.getKey().equals(DataTag)){
                DataStorage.remove(ent.getKey(), ent.getValue());
            }
        }
    }


    public HashMap<String, String> GetDataAcces(){
        if(!CanAccessData())
            return null;


        return DataStorage;
    }

    public void SetData(HashMap<String, String> data){
        if(CanAccessData()){
            DataStorage = data;
        }
    }


    public String ReadData(String DataTag){
        if(!CanAccessData())
            return EnumChatFormatting.DARK_RED + "NULL: Data encrypted" + EnumChatFormatting.RESET;

        return GetTotalDataWhitelist(DataTag);
    }

    public String GetTotalData(){
        return GetTotalDataExcluding("");
    }

    public String GetTotalDataWhitelist(String whitelisted){
        return GetTotalDataExcluding("!_ " + whitelisted);
    }

    public String GetTotalDataExcluding(String Exclude){
        if(!CanAccessData())
            return EnumChatFormatting.DARK_RED + "NULL: Data encrypted" + EnumChatFormatting.RESET;

        String data = "";


        ArrayList<String> TempT = new ArrayList<String>();

        for(Map.Entry<String, String> ent : DataStorage.entrySet()) {
            if(!TempT.contains(ent.getKey()))
                TempT.add(ent.getKey());
        }

        boolean multi = TempT.size() > 1;

        for(Map.Entry<String, String> ent : DataStorage.entrySet()) {

            if (Exclude.startsWith("!_")) {
                Exclude = Exclude.substring(2);

                if (Exclude.isEmpty() || ent.getKey().equals(Exclude)) {
                    if (multi) {
                        data = data + (data.isEmpty() ? "" : ", ") + (EnumChatFormatting.DARK_GRAY + "" + EnumChatFormatting.ITALIC + "[" + ent.getKey() + "]" + EnumChatFormatting.RESET) + ent.getValue() + " ";
                    } else {
                        data = data + (data.isEmpty() ? "" : ", ") + ent.getValue();
                    }
                }

            } else {

                if (Exclude.isEmpty() || !ent.getKey().equals(Exclude)) {

                    if (multi) {
                        data = data + (data.isEmpty() ? "" : ", ") + (EnumChatFormatting.DARK_GRAY + "" + EnumChatFormatting.ITALIC + "[" + ent.getKey() + "] " + EnumChatFormatting.RESET) + ent.getValue() + " ";
                    } else {
                        data = data + (data.isEmpty() ? "" : ", ") + ent.getValue();
                    }

                }
            }
        }

        return data;
    }



    public void RecreatingPacket(EventPacket packet){
        super.RecreatingPacket(packet);

        DataPacket pack = (DataPacket)packet;

        DataStorage = pack.DataStorage;


        Encrypted = pack.Encrypted;
        EncryptedKey = pack.EncryptedKey;

        if(Encrypted && DecryptionKey != null)
            DecryptionKey = null;




    }

}

