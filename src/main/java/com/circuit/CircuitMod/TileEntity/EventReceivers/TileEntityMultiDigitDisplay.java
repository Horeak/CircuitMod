package com.circuit.CircuitMod.TileEntity.EventReceivers;

import MiscUtils.TileEntity.ModTileEntity;
import com.circuit.CircuitMod.Utils.ByteValues;
import com.circuit.CircuitMod.Utils.CircuitUtils.ICircuitConnector;
import com.circuit.CircuitMod.Utils.CircuitUtils.IEventRec;
import com.circuit.CircuitMod.Utils.DataStorage.DataIntegerValue;
import com.circuit.CircuitMod.Utils.EventPacket;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityMultiDigitDisplay extends ModTileEntity implements IEventRec, ICircuitConnector {

    public int DisplayNumber = -1;
    public int Rotation;
    int Reset = 0;
    static int ResetAt = 2;
    static int MaxNum = 9999;

    public void updateEntity(){

        if(DisplayNumber > MaxNum)
           DisplayNumber = MaxNum;

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
    public void OnRecived(EventPacket packet) {
        if(packet.ByteValue == ByteValues.MultiDigitNumber.Value() || packet.ByteValue == ByteValues.OneDigitNumber.Value()){

            if(DisplayNumber != -1){
                if(packet.Data instanceof DataIntegerValue && ((DataIntegerValue)packet.Data).GetStoredObject() != DisplayNumber)
                    return;
            }


            DisplayNumber = packet.Data instanceof DataIntegerValue ? ((DataIntegerValue)packet.Data).GetStoredObject() : 0;
            Reset = 0;

        }

    }

    @Override
    public boolean CanRecive(EventPacket packet) {
        return packet.ByteValue == ByteValues.MultiDigitNumber.Value() || packet.ByteValue == ByteValues.OneDigitNumber.Value();
    }

    @Override
    public boolean CanConnectToTile(TileEntity tile, ForgeDirection dir) {
        return true;
    }
}
