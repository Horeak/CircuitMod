package com.circuit.CircuitMod.Utils;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;

import javax.vecmath.Vector3d;
import java.util.ArrayList;

public class EventPacket {

    public ArrayList<Vector3d> Postitions = new ArrayList<Vector3d>();

    public static boolean ContainesVactor(EventPacket packet, Vector3d vec){
        for(Vector3d vecc : packet.Postitions){
            if(vecc.equals(vec)){
                return true;
            }
        }

        return false;
    }

    public EnumFacing LastSentFrom = null;
    int TimeOutValue = 0;
    public int TimeOut;

    public byte ByteValue;

    public boolean TimedOut;

    public NBTTagCompound NBT = new NBTTagCompound();

    public EventPacket GetInstance(){
        return new EventPacket(TimeOut, ByteValue);
    }


    public EventPacket(int TimeOut, byte Value){
        this.TimeOut = TimeOut;
        this.ByteValue = Value;
        TimedOut = false;


    }

    public void RecreatingPacket(EventPacket packet){
        NBTTagCompound nbt = new NBTTagCompound();
        packet.SaveToNBT(nbt);

        NBT = packet.NBT;
        LoadFromNBT(nbt);

        Resend();
        Postitions = packet.Postitions;

    }

    public void SaveToNBT(NBTTagCompound nbt) {

        nbt.setInteger("DIR", LastSentFrom.ordinal());
        nbt.setInteger("TimeOutValue", TimeOutValue);
        nbt.setInteger("TimeOut", TimeOut);
        nbt.setByte("ByteValue", ByteValue);
        nbt.setBoolean("TimedOut", TimedOut);



    }

    public void LoadFromNBT(NBTTagCompound nbt) {

        LastSentFrom = EnumFacing.getFront(nbt.getInteger("DIR"));
        TimeOutValue = nbt.getInteger("TimeOutValue");
        TimeOut = nbt.getInteger("TimeOut");
        ByteValue = nbt.getByte("ByteValue");
        TimedOut = nbt.getBoolean("TimedOut");



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
