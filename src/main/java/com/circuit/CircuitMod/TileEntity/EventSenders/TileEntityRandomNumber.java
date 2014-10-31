package com.circuit.CircuitMod.TileEntity.EventSenders;

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
import java.util.Random;

public class TileEntityRandomNumber extends TileEntityEventSender implements IBlockInfo {

    public int MaxValue = 9999;

    public boolean IsPowered = false;
    public int ResetCountInput = 0;
    public int Reset = 4;

    public void updateEntity(){

        if(IsPowered)
            if(ResetCountInput >= Reset){
                IsPowered = false;
                ResetCountInput = 0;
            }else{
                ResetCountInput += 1;
            }


    }

    @Override
    public void Info(ArrayList<String> Strings) {

        Strings.add(EnumChatFormatting.WHITE + StatCollector.translateToLocal("tile.randomnumbercomponent.name") + EnumChatFormatting.RESET);
        Strings.add(StatCollector.translateToLocal("blockinfo.randomnumbercomponent.posnum").replace("$Number", (EnumChatFormatting.GRAY + "1 ->" + MaxValue + EnumChatFormatting.RESET)));

    }

    @Override
    public boolean CanRecive(EventPacket packet) {
        return packet.ByteValue == ByteValues.OnSignal.Value();
    }

    @Override
    public void OnRecived(EventPacket packet) {
        ResetCountInput = 0;
        if(!IsPowered) {
            IsPowered = true;
            packet.TimeOut();


            if(MaxValue < 1)
                MaxValue = 1;

        Random rand = new Random();
        int num = rand.nextInt(MaxValue);

        System.out.println(num);

            if(num < 1)
            num = 1;


        EventPacket pack = new EventPacket(-1, num > 9 ? ByteValues.MultiDigitNumber.Value() : ByteValues.OneDigitNumber.Value());
        pack.NBT.setInteger("StoredNumber", num);

        SendPacketToAround(pack);


            }


    }

    @Override
    public boolean CanConnectToTile(TileEntity tile, ForgeDirection dir) {
        return true;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {
        super.readFromNBT(nbtTagCompound);

        MaxValue = nbtTagCompound.getInteger("MaxValue");
        ResetCountInput = nbtTagCompound.getInteger("ResetCountInput");
        IsPowered = nbtTagCompound.getBoolean("Powered");


    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound) {
        super.writeToNBT(nbtTagCompound);

        nbtTagCompound.setInteger("MaxValue", MaxValue);
        nbtTagCompound.setInteger("ResetCountInput", ResetCountInput);
        nbtTagCompound.setBoolean("Powered", IsPowered);

    }
}
