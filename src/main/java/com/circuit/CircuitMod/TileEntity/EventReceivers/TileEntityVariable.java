package com.circuit.CircuitMod.TileEntity.EventReceivers;

import MiscUtils.TileEntity.IBlockInfo;
import com.circuit.CircuitMod.TileEntity.TileEntityEventSender;
import com.circuit.CircuitMod.Utils.ByteValues;
import com.circuit.CircuitMod.Utils.EventPacket;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.StatCollector;

import java.util.ArrayList;

public class TileEntityVariable extends TileEntityEventSender implements IBlockInfo {

    public int StoredNumber = 0;
    EnumFacing dirFrom = null;

    public void updateEntity(){

        EventPacket packet = new EventPacket(-1, StoredNumber > 9 ? ByteValues.MultiDigitNumber.Value() : ByteValues.OneDigitNumber.Value());
        packet.NBT.setInteger("StoredNumber", StoredNumber);


        for(EnumFacing dir : EnumFacing.values()){
            if(dir != dirFrom)
                SendPacketTo(packet, dir);
        }


    }

    @Override
    public void Info(ArrayList<String> Strings) {

        Strings.add(EnumChatFormatting.WHITE + StatCollector.translateToLocal("tile.variable.name") + EnumChatFormatting.RESET);
        Strings.add(StatCollector.translateToLocal("blockinfo.variable.value").replace("$Number", (EnumChatFormatting.GRAY + "" + StoredNumber + EnumChatFormatting.RESET)));

    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {
        super.readFromNBT(nbtTagCompound);

        StoredNumber = nbtTagCompound.getInteger("Number");
        dirFrom = EnumFacing.getFront(nbtTagCompound.getInteger("DirFrom"));


    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound) {
        super.writeToNBT(nbtTagCompound);

        nbtTagCompound.setInteger("Number", StoredNumber);
        nbtTagCompound.setInteger("DirFrom", dirFrom.ordinal());

    }


    @Override
    public void OnRecived(EventPacket packet) {
        if(packet.ByteValue == ByteValues.MultiDigitNumber.Value() || packet.ByteValue == ByteValues.OneDigitNumber.Value())
            StoredNumber = packet.NBT.getInteger("StoredNumber");
            dirFrom = packet.LastSentFrom;



    }

    @Override
    public boolean CanRecive(EventPacket packet) {
        return packet.ByteValue == ByteValues.MultiDigitNumber.Value() || packet.ByteValue == ByteValues.OneDigitNumber.Value();
    }

    @Override
    public boolean CanConnectToTile(TileEntity tile, EnumFacing dir) {
        return true;
    }
}