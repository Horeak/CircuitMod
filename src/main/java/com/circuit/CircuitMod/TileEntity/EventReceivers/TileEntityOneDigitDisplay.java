package com.circuit.CircuitMod.TileEntity.EventReceivers;

import MiscUtils.TileEntity.ModTileEntity;
import com.circuit.CircuitMod.TileEntity.CircuitUtils.ByteValues;
import com.circuit.CircuitMod.TileEntity.CircuitUtils.ICircuitConnector;
import com.circuit.CircuitMod.TileEntity.CircuitUtils.IEventRec;
import com.circuit.CircuitMod.Utils.EventPacket;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityOneDigitDisplay extends ModTileEntity implements IEventRec, ICircuitConnector {


    public int DisplayNumber = -1;
    public int Rotation;
    int Reset = 0;
    static int ResetAt = 5;

    public void updateEntity(){
        if(DisplayNumber != -1) {
            if (Reset >= ResetAt) {
                DisplayNumber = -1;
                Reset = 0;
            }else{
                Reset += 1;
            }
        }

    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {

        super.readFromNBT(nbtTagCompound);

        DisplayNumber = nbtTagCompound.getInteger("Number");
        Rotation = nbtTagCompound.getInteger("Rot");
        Reset = nbtTagCompound.getInteger("Reset");


    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound) {
        super.writeToNBT(nbtTagCompound);

        nbtTagCompound.setInteger("Number", DisplayNumber);
        nbtTagCompound.setInteger("Rot", Rotation);
        nbtTagCompound.setInteger("Reset", Reset);

    }

    @Override
    public boolean CanConnectToTile(TileEntity tile, ForgeDirection dir) {
        return true;
    }

    @Override
    public void OnRecived(EventPacket packet) {
        if(packet.ByteValue == ByteValues.OneDigitNumber.Value()){
            DisplayNumber = packet.NBT.getInteger("StoredNumber");
            Reset = 0;

        }

    }

    @Override
    public boolean CanRecive(EventPacket packet) {
        return packet.ByteValue == ByteValues.OneDigitNumber.Value();
    }
}
