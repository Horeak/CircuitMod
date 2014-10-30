package com.circuit.CircuitMod.TileEntity.Utils;

import MiscUtils.TileEntity.IBlockInfo;
import com.circuit.CircuitMod.TileEntity.CircuitUtils.ByteValues;
import com.circuit.CircuitMod.TileEntity.TileEntityEventSender;
import com.circuit.CircuitMod.Utils.EventPacket;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.ArrayList;

public abstract class TileEntityCounterBase extends TileEntityEventSender  implements IBlockInfo {


    public abstract int DefReset();
    public abstract int ResetTo();

    public abstract byte OutputValue();
    public abstract int OutputTimeout();

    public abstract byte InputValue();

    //True = increment, False = decrement
    public abstract boolean IncrementOrDecrement();

    public abstract int ResetCountTimer();

    public abstract int ResetChangeToBase();

    public abstract boolean OutputsSignalOnReset();

    public int CurrentCount = 0;
    public int ResetAt = DefReset();
    public int ResetCount = 0, ResetCountInput = 0;


    public int Change = 1;

    public boolean IsPowered = false;

    public void updateEntity(){

        EventPacket packet = new EventPacket(OutputTimeout(), OutputValue());
        packet.NBT.setInteger("StoredNumber", CurrentCount);

        SendPacketToAround(packet);


        if(ResetAt > DefReset())
            ResetAt = DefReset();



        if(Change > ResetChangeToBase())
            if(ResetCount >= ResetCountTimer()){
                Change = ResetChangeToBase();
                ResetCount = 0;

            }else{
                ResetCount += 1;
            }



        if(IsPowered)
            if(ResetCountInput >= ResetCountTimer()){
                IsPowered = false;
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

    public void ChangeNumber(){

        if(IncrementOrDecrement()) {
            CurrentCount += Change;
        }else {
            CurrentCount -= Change;
        }

        if(IncrementOrDecrement() && CurrentCount >= ResetAt || !IncrementOrDecrement() && CurrentCount <= ResetAt){
            CurrentCount = ResetTo();

            if(OutputsSignalOnReset())
            SendPacketToAround(new EventPacket(-1, ByteValues.OnSignal.Value()));
        }


        IsPowered = true;
    }

    @Override
    public void OnRecived(EventPacket packet) {

        if(packet.ByteValue == InputValue()){
            Change = packet.NBT.getInteger("StoredNumber");
            ResetCount = 0;
        }



        if(packet.ByteValue == ByteValues.OnSignal.Value()){
            ResetCountInput = 0;

            if(!IsPowered) {
                ChangeNumber();
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
        return packet.ByteValue == ByteValues.OnSignal.Value() || packet.ByteValue == InputValue();
    }

    @Override
    public void Info(ArrayList<String> Strings) {

        Strings.add(EnumChatFormatting.WHITE + worldObj.getBlock(xCoord, yCoord, zCoord).getLocalizedName() + EnumChatFormatting.RESET);
        Strings.add(StatCollector.translateToLocal("blockinfo.counter.currentcount").replace("$Number", EnumChatFormatting.GRAY + "" + CurrentCount + EnumChatFormatting.RESET));
        Strings.add(IncrementOrDecrement() ? (StatCollector.translateToLocal("blockinfo.counter.increment").replace("$Number", (EnumChatFormatting.GRAY + "" + Change + EnumChatFormatting.RESET))) : (StatCollector.translateToLocal("blockinfo.counter.decrement").replace("$Number", (EnumChatFormatting.GRAY + "" + Change + EnumChatFormatting.RESET))));
        Strings.add(StatCollector.translateToLocal("blockinfo.counter.resetsat").replace("$Number", (EnumChatFormatting.GRAY + "" + ResetAt + EnumChatFormatting.RESET)));

    }
}
