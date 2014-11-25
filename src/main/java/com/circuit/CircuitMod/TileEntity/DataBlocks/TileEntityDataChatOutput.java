package com.circuit.CircuitMod.TileEntity.DataBlocks;

import MiscUtils.Handlers.ChatMessageHandler;
import com.circuit.CircuitMod.TileEntity.TileEntityEventSender;
import com.circuit.CircuitMod.Utils.ByteValues;
import com.circuit.CircuitMod.Utils.EventPacket;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.List;

public class TileEntityDataChatOutput extends TileEntityEventSender{


    int Reset = 0;
    static int ResetAt = 2;

    static int RANGE_DEFAULT = 10;
    int Range = RANGE_DEFAULT;

    public void updateEntity(){


        if(Range != -RANGE_DEFAULT) {
            if (Reset >= ResetAt) {
                Range = RANGE_DEFAULT;
                Reset = 0;
            }else{
                Reset += 1;
            }
        }

    }

    @Override
    public void OnRecived(EventPacket packet) {
        if(packet.ByteValue == ByteValues.MultiDigitNumber.Value() || packet.ByteValue == ByteValues.OneDigitNumber.Value()){

            if(Range != RANGE_DEFAULT){
                if(packet.NBT.getInteger("StoredNumber") != Range )
                    return;
            }

            Range = packet.NBT.getInteger("StoredNumber");
            Reset = 0;

        }else if(packet.ByteValue == ByteValues.DataSignal.Value()){

            String Pre = EnumChatFormatting.GRAY + "[" + worldObj.getBlock(xCoord, yCoord, zCoord).getLocalizedName() + "]: " + EnumChatFormatting.RESET;
            String Message = Pre + packet.NBT.getString("Data");

            double rn = (Range + 1);
            List list = worldObj.getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getBoundingBox(xCoord - rn, yCoord - rn, zCoord - rn, xCoord + rn, yCoord + rn, zCoord + rn));

            for(Object r : list){
                if(r instanceof EntityPlayer){
                    EntityPlayer player = (EntityPlayer)r;
                    if(player.getDistance(xCoord, yCoord, zCoord) <= Range) {

                        if(!worldObj.isRemote)
                        ChatMessageHandler.sendChatToPlayer(player, Message);
                    }

                }

            }

        }
    }

    @Override
    public boolean CanConnectToTile(TileEntity tile, ForgeDirection dir) {
        return true;
    }

    @Override
    public boolean CanRecive(EventPacket packet) {
        return packet.ByteValue == ByteValues.DataSignal.Value() || packet.ByteValue == ByteValues.OneDigitNumber.Value() || packet.ByteValue == ByteValues.MultiDigitNumber.Value();
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {
        super.readFromNBT(nbtTagCompound);

        Range = nbtTagCompound.getInteger("Range");
        Reset = nbtTagCompound.getInteger("Reset");


    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound) {
        super.writeToNBT(nbtTagCompound);

        nbtTagCompound.setInteger("Range", Range);
        nbtTagCompound.setInteger("Reset", Reset);

    }
}
