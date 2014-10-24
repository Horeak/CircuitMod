package com.circuit.CircuitMod.Utils;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;

import javax.vecmath.Vector3d;
import java.util.ArrayList;

public class EventPacket {



    //Finished adding function to allow nbt data saved in each packet and byte specific handling.
    //TODO Add other byte specific event senders to test system. (Event sender with number? Event sender with random output number? Toggleable event sender?)
    //TODO Add byte and nbt specific event receivers like for example digital number display

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
    int TimeOutValue = 0;
    public int TimeOut;

    public byte ByteValue;

    public boolean TimedOut;

    public NBTTagCompound nbt;

    public EventPacket(int TimeOut, byte Value){
        this.TimeOut = TimeOut;
        this.ByteValue = Value;
        TimedOut = false;
        nbt = new NBTTagCompound();

    }

    public void SaveToNBT(NBTTagCompound nbt) {

        nbt.setInteger("DIR", LastSentFrom.ordinal());
        nbt.setInteger("TimeOutValue", TimeOutValue);
        nbt.setInteger("TimeOut", TimeOut);
        nbt.setByte("ByteValue", ByteValue);
        nbt.setBoolean("TimedOut", TimedOut);

        nbt.setTag("SavedData", nbt);


    }

    public void LoadFromNBT(NBTTagCompound nbt) {

        LastSentFrom = ForgeDirection.getOrientation(nbt.getInteger("DIR"));
        TimeOutValue = nbt.getInteger("TimeOutValue");
        TimeOut = nbt.getInteger("TimeOut");
        ByteValue = nbt.getByte("ByteValue");
        TimedOut = nbt.getBoolean("TimedOut");

        this.nbt = (NBTTagCompound)nbt.getTag("SavedData");


    }


    public void Resend(){
        if(TimeOut > -1 && TimeOutValue != -1){
            if(TimeOutValue >= TimeOut){
                TimeOut();
            }else{
                TimeOutValue += 1;
            }


        }

    }

    public void TimeOut(){
        TimeOutValue = -1;
        TimedOut = true;
    }

}
