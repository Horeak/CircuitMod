package com.circuit.CircuitMod.TileEntity.EventReceivers;

import MiscUtils.TileEntity.ModTileEntity;
import com.circuit.CircuitMod.TileEntity.CircuitUtils.ByteValues;
import com.circuit.CircuitMod.TileEntity.CircuitUtils.ICircuitConnector;
import com.circuit.CircuitMod.TileEntity.CircuitUtils.IEventRec;
import com.circuit.CircuitMod.Utils.EventPacket;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityLamp extends ModTileEntity implements IEventRec, ICircuitConnector {


    public boolean Powered = false;
    int Reset = 0;
    static int ResetAt = 5;

    public void updateEntity(){

        if(worldObj.isBlockIndirectlyGettingPowered(xCoord,yCoord, zCoord)){
            Powered = true;
            Reset = 0;

        }

        if(Powered) {
            if (Reset >= ResetAt) {
                Powered = false;
                Reset = 0;
            }else{
                Reset += 1;
            }
        }

    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {

        super.readFromNBT(nbtTagCompound);

        Powered = nbtTagCompound.getBoolean("Powered");
        Reset = nbtTagCompound.getInteger("Reset");


    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound) {
        super.writeToNBT(nbtTagCompound);

        nbtTagCompound.setBoolean("Powered", Powered);
        nbtTagCompound.setInteger("Reset", Reset);

    }

    @Override
    public boolean CanConnectToTile(TileEntity tile, ForgeDirection dir) {
        return true;
    }

    @Override
    public void OnRecived(EventPacket packet) {
        if(packet.ByteValue == ByteValues.OnSignal.Value()){
            Powered = true;
            Reset = 0;

        }

    }

    @Override
    public boolean CanRecive(EventPacket packet) {
        return packet.ByteValue == ByteValues.OnSignal.Value();

    }
    }