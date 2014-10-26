package com.circuit.CircuitMod.TileEntity.EventSenders;

import com.circuit.CircuitMod.TileEntity.CircuitUtils.ByteValues;
import com.circuit.CircuitMod.TileEntity.TileEntityEventSender;
import com.circuit.CircuitMod.Utils.EventPacket;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityOneDigitCounter extends TileEntityEventSender {

    public static int DefReset = 10;

    public int CurrentCount = 0;
    public int ResetAt = DefReset;
    public int ResetCount = 0, ResetCountInput = 0;
    int ResetCountAt = 2;
    public int Increment = 1;
    boolean t = false;

    public void updateEntity(){

        EventPacket packet = new EventPacket(-1, ByteValues.OneDigitNumber.Value());
        packet.NBT.setInteger("StoredNumber", CurrentCount);

        SendPacketToAround(packet);


        if(ResetAt > DefReset)
            ResetAt = DefReset;

        if(Increment > 1)
        if(ResetCount >= ResetCountAt){
            Increment = 1;
            ResetCount = 0;
        }else{
            ResetCount += 1;
        }

        if(t)
            if(ResetCountInput >= ResetCountAt){
                t = false;
                ResetCountInput = 0;
            }else{
                ResetCountInput += 1;
            }


    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {
        super.readFromNBT(nbtTagCompound);

        CurrentCount = nbtTagCompound.getInteger("Count");
        ResetAt = nbtTagCompound.getInteger("Reset");
        ResetCount = nbtTagCompound.getInteger("ResetC");
        ResetCountInput = nbtTagCompound.getInteger("ResetCountInput");


    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound) {
        super.writeToNBT(nbtTagCompound);

        nbtTagCompound.setInteger("Count", CurrentCount);
        nbtTagCompound.setInteger("Reset", ResetAt);
        nbtTagCompound.setInteger("ResetC", ResetCount);
        nbtTagCompound.setInteger("ResetCountInput", ResetCountInput);

    }

    public void IncreaseNumber(){
        CurrentCount += Increment;

        if(CurrentCount >= ResetAt){
            CurrentCount = 0;
        }

        t = true;
    }

    @Override
    public void OnRecived(EventPacket packet) {

        if(packet.ByteValue == ByteValues.OneDigitNumber.Value()){
            Increment = packet.NBT.getInteger("StoredNumber");
            ResetCount = 0;
        }

        if(packet.ByteValue == ByteValues.OnSignal.Value()){
            ResetCountInput = 0;

            if(!t) {
                IncreaseNumber();
                packet.TimeOut();
            }
        }


    }

    @Override
    public boolean CanConnectToTile(TileEntity tile, ForgeDirection dir) {
        return true;
    }

    @Override
    public boolean CanRecive(EventPacket packet) {
        return packet.ByteValue == ByteValues.OnSignal.Value() || packet.ByteValue == ByteValues.OneDigitNumber.Value();
    }
}
