package com.circuit.CircuitMod.TileEntity;

import com.circuit.CircuitMod.TileEntity.CircuitUtils.ByteValues;
import com.circuit.CircuitMod.Utils.EventPacket;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntitySignalGate extends TileEntityEventSender {



    public EventPacket packetA, packetB;
    boolean ActiveInputSignal = false;
    int ResetSignal = 0, ResetPacketA = 0, ResetPacketB = 0;
    int ResetAt = 2;


    public void updateEntity(){


        if(ActiveInputSignal){
            if(ResetSignal >= ResetAt){
                ResetSignal = 0;
                ActiveInputSignal = false;

            }else{
                ResetSignal += 1;
            }
        }


        if(packetA != null){
            if(ResetPacketA >= ResetAt){
                ResetPacketA = 0;
                packetA = null;

            }else{
                ResetPacketA += 1;
            }
        }

        if(packetB != null){
            if(ResetPacketB >= ResetAt){
                ResetPacketB = 0;
                packetB = null;

            }else{
                ResetPacketB += 1;
            }
        }



        if(ActiveInputSignal){
            if(packetA != null){
                SendPacketTo(packetA, GetOutputSide());
            }

        }else{
            if(packetB != null){
                SendPacketTo(packetB, GetOutputSide());
            }
        }

    }

    @Override
    public void OnRecived(EventPacket packet) {

        if(packet.LastSentFrom == GetDirectionGreen()){
            packetA = packet;
            return;

        }else if(packet.LastSentFrom == GetDirectionRed()){
            packetB = packet;
            return;
        }

        ForgeDirection di = packet.LastSentFrom;
        if(di != GetOutputSide() && di != GetDirectionGreen() && di != GetDirectionRed()){
            if(packet.ByteValue == ByteValues.OnSignal.Value()){
                ActiveInputSignal = true;
                ResetSignal = 0;

            }
        }


    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {

        super.readFromNBT(nbtTagCompound);

        ActiveInputSignal = nbtTagCompound.getBoolean("Active");
        ResetSignal = nbtTagCompound.getInteger("Reset");
        ResetPacketA = nbtTagCompound.getInteger("ResetPacketA");
        ResetPacketB = nbtTagCompound.getInteger("ResetPacketB");


    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound) {
        super.writeToNBT(nbtTagCompound);

        nbtTagCompound.setBoolean("Active", ActiveInputSignal);
        nbtTagCompound.setInteger("Reset", ResetSignal);
        nbtTagCompound.setInteger("ResetPacketA", ResetPacketA);
        nbtTagCompound.setInteger("ResetPacketB", ResetPacketB);


    }

    @Override
    public boolean CanConnectToTile(TileEntity tile, ForgeDirection dir) {
        return true;
    }


    public ForgeDirection GetOutputSide(){
        return ForgeDirection.getOrientation(worldObj.getBlockMetadata(xCoord,yCoord, zCoord));
    }

    public ForgeDirection GetDirectionGreen(){

        ForgeDirection dir = ForgeDirection.getOrientation(worldObj.getBlockMetadata(xCoord, yCoord, zCoord));
        ForgeDirection green = ForgeDirection.UNKNOWN;

        if(dir == ForgeDirection.NORTH)
            green = ForgeDirection.EAST;

        if(dir == ForgeDirection.WEST)
            green = ForgeDirection.NORTH;

        if(dir == ForgeDirection.EAST)
            green = ForgeDirection.SOUTH;

        if(dir == ForgeDirection.SOUTH)
            green = ForgeDirection.WEST;

        if(dir == ForgeDirection.DOWN || dir == ForgeDirection.UP)
            green = ForgeDirection.WEST;

        return green;
    }

    public ForgeDirection GetDirectionRed(){
        return GetDirectionGreen().getOpposite();
    }
}
