package com.circuit.CircuitMod.Utils;

import MiscUtils.Config.ConfigBase;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class Config extends ConfigBase{

    public Config(String Loc){
        super(new Configuration(new File(Loc + "/tm1990's mods/" + Ref.ModName + ".cfg")));
        InitConfig();
    }


    @Override
    public void InitConfig() {

        RegisterCategory("Client Settings", "Client side only settings. Settings that does not affect gameplay");
        RegisterCategory("Server Settings", "Server side settings which can affect gameplay");

        LoadConfig();
    }

    @Override
    public void LoadConfig() {

        if(GetConfigFile().hasChanged())
            GetConfigFile().save();
    }

    public boolean CanSpawnParticles(){
        return GetConfigFile().getBoolean("Can Spawn Particles", "Client Settings", true, "Should particles be enabled?");
    }
}
