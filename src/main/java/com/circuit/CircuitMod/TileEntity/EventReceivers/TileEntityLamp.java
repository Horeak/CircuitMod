package com.circuit.CircuitMod.TileEntity.EventReceivers;

import MiscUtils.TileEntity.ModTileEntity;
import com.circuit.CircuitMod.Utils.ByteValues;
import com.circuit.CircuitMod.Utils.CircuitUtils.ICircuitConnector;
import com.circuit.CircuitMod.Utils.CircuitUtils.IEventRec;
import com.circuit.CircuitMod.Utils.EventPacket;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

public class TileEntityLamp extends ModTileEntity implements IEventRec, ICircuitConnector {


    public boolean Powered = false;
    int Reset = 0;
    static int ResetAt = 2;

    public void updateEntity(){

        if(Powered && !worldObj.isBlockPowered(new BlockPos(getPos().getX(), getPos().getY(), getPos().getZ()))) {
            if (Reset >= ResetAt) {
                SetState(false);
                Reset = 0;
            }else{
                Reset += 1;
            }
        }

        if(worldObj.isBlockPowered(new BlockPos(getPos().getX(), getPos().getY(), getPos().getZ()))){
            Reset = 0;
            SetState(true);


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
    public boolean CanConnectToTile(TileEntity tile, EnumFacing dir) {
        return true;
    }

    @Override
    public void OnRecived(EventPacket packet) {
        if(packet.ByteValue == ByteValues.OnSignal.Value()){
            if(!Powered) {
                SetState(true);
            }
            Reset = 0;

        }

    }

    @Override
    public boolean CanRecive(EventPacket packet) {
        return packet.ByteValue == ByteValues.OnSignal.Value();

    }


    public void SetState(boolean t){
        Powered = t;

        worldObj.markBlockForUpdate(new BlockPos(getPos().getX(), getPos().getY(), getPos().getZ()));
        //worldObj.notifyBlocksOfNeighborChange(xCoord, yCoord, zCoord, worldObj.getBlock(xCoord, yCoord, zCoord));


    }
    }