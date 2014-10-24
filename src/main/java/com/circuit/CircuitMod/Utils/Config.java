package com.circuit.CircuitMod.Utils;

import MiscUtils.Config.ConfigBase;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class Config extends ConfigBase{

    public Config(String Loc){
        super(new Configuration(new File(Loc + "/MiscUtils mods/" + Ref.ModName + ".cfg")));
        InitConfig();
    }


    @Override
    public void InitConfig() {
        LoadConfig();
    }

    @Override
    public void LoadConfig() {

        if(GetConfigFile().hasChanged())
            GetConfigFile().save();
    }

}
