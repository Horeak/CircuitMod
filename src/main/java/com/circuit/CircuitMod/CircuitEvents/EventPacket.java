package com.circuit.CircuitMod.CircuitEvents;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;

import javax.vecmath.Vector3d;
import java.util.ArrayList;

public abstract class EventPacket {


    public ArrayList<Vector3d> Postitions = new ArrayList<Vector3d>();

    public static boolean ContainesVactor(EventPacket packet, Vector3d vec){
        for(Vector3d vecc : packet.Postitions){
            if(vecc.equals(vec)){
                return true;
            }
        }

        return false;
    }

    public ForgeDirection LastSentFrom = ForgeDirection.UNKNOWN;
    public abstract String ID();
    public int TimesSent = 0;

    public void SaveToNBT(NBTTagCompound nbt) {

        nbt.setInteger("DIR", LastSentFrom.ordinal());
        nbt.setInteger("TimesSent", TimesSent);


    }

    public void LoadFromNBT(NBTTagCompound nbt) {

        LastSentFrom = ForgeDirection.getOrientation(nbt.getInteger("DIR"));
        TimesSent = nbt.getInteger("TimesSent");

    }

    public abstract EventPacket GetInstance();


}
