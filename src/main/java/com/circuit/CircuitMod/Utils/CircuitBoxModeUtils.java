package com.circuit.CircuitMod.Utils;

import com.circuit.CircuitMod.Utils.Modes.AND_Gate;
import com.circuit.CircuitMod.Utils.Modes.CircuitBoxMode;
import com.circuit.CircuitMod.Utils.Modes.OR_Gate;

import java.util.ArrayList;

public class CircuitBoxModeUtils {

    public static ArrayList<CircuitBoxMode> Modes = new ArrayList<CircuitBoxMode>();

    public static void RegisterMode(CircuitBoxMode mode){

        if(!Modes.contains(mode))
            Modes.add(mode);
    }

    public static void RegisterModes(){

        RegisterMode(new OR_Gate());
        RegisterMode(new AND_Gate());
    }

    public static CircuitBoxMode GetMode(String Id){
       for(CircuitBoxMode mode : Modes){
           if(mode.ModeName().equalsIgnoreCase(Id))
               return mode;
       }

        return null;
    }

}
