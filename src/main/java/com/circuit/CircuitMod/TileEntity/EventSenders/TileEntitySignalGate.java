package com.circuit.CircuitMod.TileEntity.EventSenders;

import com.circuit.CircuitMod.Utils.ByteValues;
import com.circuit.CircuitMod.TileEntity.Utils.TileEntityTwoSidedEventChecker;
import com.circuit.CircuitMod.Utils.EventPacket;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntitySignalGate extends TileEntityTwoSidedEventChecker {


    boolean ActiveInputSignal = false;
    int ResetSignal = 0;


    @Override
    public byte InputPacketA() {
        return -1;
    }

    @Override
    public byte InputPacketB() {
        return -1;
    }

    @Override
    public EventPacket OutPutPacket() {
        return ActiveInputSignal ? (packetA != null ? packetA : null) : (packetB != null ? packetB : null);
    }

    public void updateEntity(){
        super.updateEntity();

        if(ActiveInputSignal){
            if(ResetSignal >= ResetAt){
                ResetSignal = 0;
                ActiveInputSignal = false;

            }else{
                ResetSignal += 1;
            }
        }
    }

    @Override
    public void OnRecived(EventPacket packet) {
        super.OnRecived(packet);

        ForgeDirection di = packet.LastSentFrom;
        if(di != GetOutputSide() && di != GetDirectionA() && di != GetDirectionB()){
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


    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound) {
        super.writeToNBT(nbtTagCompound);

        nbtTagCompound.setBoolean("Active", ActiveInputSignal);
        nbtTagCompound.setInteger("Reset", ResetSignal);


    }

    @Override
    public boolean CanConnectToTile(TileEntity tile, ForgeDirection dir) {
        return true;
    }

}
