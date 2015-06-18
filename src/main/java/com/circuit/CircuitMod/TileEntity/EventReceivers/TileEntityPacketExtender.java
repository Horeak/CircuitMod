package com.circuit.CircuitMod.TileEntity.EventReceivers;

import MiscUtils.TileEntity.IBlockInfo;
import com.circuit.CircuitMod.Main.CircuitMod;
import com.circuit.CircuitMod.TileEntity.TileEntityEventSender;
import com.circuit.CircuitMod.Utils.ByteValues;
import com.circuit.CircuitMod.Utils.DataStorage.DataIntegerValue;
import com.circuit.CircuitMod.Utils.EventPacket;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class TileEntityPacketExtender extends TileEntityEventSender implements IBlockInfo {

    public ForgeDirection dir = ForgeDirection.UNKNOWN;

    int extentions = 1;
    HashMap<EventPacket, Integer> packets = new HashMap<EventPacket, Integer>();

	int Reset = 0;
	static int ResetAt = 2;

    public void updateEntity(){
	    try{

		    for(Entry<EventPacket, Integer> ent : packets.entrySet()){
			    EventPacket packet = ent.getKey();
			    int temp = ent.getValue();

			    SendPacketTo(packet, dir);

			    packets.remove(packet, temp);
			    if((temp + 1) < extentions) {
				    packets.put(packet, temp + 1);
			    }
		    }

	    }catch (Exception e){
		e.printStackTrace();
	    }


	    if(extentions != 1) {
		    if (Reset >= ResetAt) {
			    extentions = 1;
			    Reset = 0;
		    }else{
			    Reset += 1;
		    }
	    }

    }


    @Override
    public void OnRecived(EventPacket packet) {

        if(packet.ByteValue != ByteValues.OneDigitNumber.Value() && packet.ByteValue != ByteValues.MultiDigitNumber.Value()) {
	        packets.put(packet, 0);

        }else{
            if(extentions != 1){
                    if(packet.Data instanceof DataIntegerValue && ((DataIntegerValue)packet.Data).GetStoredObject() != extentions)
                        return;
                }

                extentions = packet.Data instanceof DataIntegerValue ? ((DataIntegerValue)packet.Data).GetStoredObject() : 0;
        }

    }

    @Override
    public boolean CanConnectToTile(TileEntity tile, ForgeDirection dir) {
        return true;
    }

    @Override
    public boolean CanRecive(EventPacket packet) {
        return true;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {
        super.readFromNBT(nbtTagCompound);

        dir = ForgeDirection.getOrientation(nbtTagCompound.getInteger("Dir"));
        extentions = nbtTagCompound.getInteger("extentions");
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound) {
        super.writeToNBT(nbtTagCompound);

        nbtTagCompound.setInteger("Dir", dir.ordinal());
        nbtTagCompound.setInteger("extentions", extentions);
    }

    @Override
    public void Info(ArrayList<String> Strings) {
        if(!CircuitMod.ShowHoverText)
            return;

        Strings.add(EnumChatFormatting.WHITE + worldObj.getBlock(xCoord, yCoord, zCoord).getLocalizedName() + EnumChatFormatting.RESET);

        int dim = worldObj.provider.dimensionId;
        double worldTps = TileEntityDataDelay.getTPS(dim);

	    Strings.add(StatCollector.translateToLocal("blockinfo.packetextender.extended").replace("$delay", (EnumChatFormatting.GRAY + "" + extentions + EnumChatFormatting.RESET)));
	    Strings.add(StatCollector.translateToLocal("blockinfo.packetextender.extendedSecond").replace("$delay", (EnumChatFormatting.GRAY + "" + (Double.parseDouble(Integer.toString(extentions)) / worldTps) + EnumChatFormatting.RESET)));

    }

}