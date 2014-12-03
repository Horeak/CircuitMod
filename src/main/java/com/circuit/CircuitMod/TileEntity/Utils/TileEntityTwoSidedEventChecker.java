package com.circuit.CircuitMod.TileEntity.Utils;

import com.circuit.CircuitMod.Main.ModBlocks;
import com.circuit.CircuitMod.TileEntity.TileEntityEventSender;
import com.circuit.CircuitMod.Utils.EventPacket;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

public abstract class TileEntityTwoSidedEventChecker extends TileEntityEventSender {



    public abstract byte InputPacketA();
    public abstract byte InputPacketB();

    public abstract EventPacket OutPutPacket();


    public EventPacket packetA, packetB;
    public int ResetPacketA = 0, ResetPacketB = 0;
    public int ResetAt = 4;

    public EnumFacing dir = EnumFacing.UP;


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
            ResetPacketA = 0;
            return;

        }else if(packet.LastSentFrom == GetDirectionB()){
            if(packet.ByteValue == InputPacketB() || InputPacketB() == -1)
            packetB = packet;
            ResetPacketB = 0;
            return;
        }




    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {

        super.readFromNBT(nbtTagCompound);

        ResetPacketA = nbtTagCompound.getInteger("ResetPacketA");
        ResetPacketB = nbtTagCompound.getInteger("ResetPacketB");

        dir = EnumFacing.getFront(nbtTagCompound.getInteger("Dir"));


    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound) {
        super.writeToNBT(nbtTagCompound);

        nbtTagCompound.setInteger("ResetPacketA", ResetPacketA);
        nbtTagCompound.setInteger("ResetPacketB", ResetPacketB);

        nbtTagCompound.setInteger("Dir", dir.getIndex());


    }

    @Override
    public boolean CanConnectToTile(TileEntity tile, EnumFacing dir) {
        return true;
    }


    public EnumFacing GetOutputSide(){
        return dir;
    }

    public EnumFacing GetDirectionA(){

        EnumFacing dir = EnumFacing.getFront(ModBlocks.AdditionGate.getMetaFromState(worldObj.getBlockState(new BlockPos(getPos().getX(), getPos().getY(), getPos().getZ()))));
        EnumFacing A = null;

        if(dir == EnumFacing.NORTH)
            A = EnumFacing.EAST;

        if(dir == EnumFacing.WEST)
            A = EnumFacing.NORTH;

        if(dir == EnumFacing.EAST)
            A = EnumFacing.SOUTH;

        if(dir == EnumFacing.SOUTH)
            A = EnumFacing.WEST;

        if(dir == EnumFacing.DOWN || dir == EnumFacing.UP)
            A = EnumFacing.WEST;

        return A;
    }

    public EnumFacing GetDirectionB(){
        return GetDirectionA();
    }
}
