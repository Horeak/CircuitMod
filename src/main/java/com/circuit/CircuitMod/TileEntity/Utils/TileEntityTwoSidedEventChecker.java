package com.circuit.CircuitMod.TileEntity.Utils;

import com.circuit.CircuitMod.TileEntity.TileEntityEventSender;
import com.circuit.CircuitMod.Utils.EventPacket;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public abstract class TileEntityTwoSidedEventChecker extends TileEntityEventSender {



    public abstract byte InputPacketA();
    public abstract byte InputPacketB();

    public abstract EventPacket OutPutPacket();


    public EventPacket packetA, packetB;
    public int ResetPacketA = 0, ResetPacketB = 0;
    public int ResetAt = 4;

    public ForgeDirection dir = ForgeDirection.UNKNOWN;


    public void updateEntity(){

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

        if(OutPutPacket() != null){
            SendPacketTo(OutPutPacket(), GetOutputSide());
        }
    }

    @Override
    public void OnRecived(EventPacket packet) {

        if(packet.LastSentFrom == GetDirectionA()){
            if(packet.ByteValue == InputPacketA() || InputPacketA() == -1)
            packetA = packet;
            return;

        }else if(packet.LastSentFrom == GetDirectionB()){
            if(packet.ByteValue == InputPacketB() || InputPacketB() == -1)
            packetB = packet;
            return;
        }




    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {

        super.readFromNBT(nbtTagCompound);

        ResetPacketA = nbtTagCompound.getInteger("ResetPacketA");
        ResetPacketB = nbtTagCompound.getInteger("ResetPacketB");

        dir = ForgeDirection.getOrientation(nbtTagCompound.getInteger("Dir"));


    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound) {
        super.writeToNBT(nbtTagCompound);

        nbtTagCompound.setInteger("ResetPacketA", ResetPacketA);
        nbtTagCompound.setInteger("ResetPacketB", ResetPacketB);

        nbtTagCompound.setInteger("Dir", dir.ordinal());


    }

    @Override
    public boolean CanConnectToTile(TileEntity tile, ForgeDirection dir) {
        return true;
    }


    public ForgeDirection GetOutputSide(){
        return dir;
    }

    public ForgeDirection GetDirectionA(){

        ForgeDirection dir = ForgeDirection.getOrientation(worldObj.getBlockMetadata(xCoord, yCoord, zCoord));
        ForgeDirection A = ForgeDirection.UNKNOWN;

        if(dir == ForgeDirection.NORTH)
            A = ForgeDirection.EAST;

        if(dir == ForgeDirection.WEST)
            A = ForgeDirection.NORTH;

        if(dir == ForgeDirection.EAST)
            A = ForgeDirection.SOUTH;

        if(dir == ForgeDirection.SOUTH)
            A = ForgeDirection.WEST;

        if(dir == ForgeDirection.DOWN || dir == ForgeDirection.UP)
            A = ForgeDirection.WEST;

        return A;
    }

    public ForgeDirection GetDirectionB(){
        return GetDirectionA().getOpposite();
    }
}
