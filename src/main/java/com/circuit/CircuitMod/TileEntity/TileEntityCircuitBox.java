package com.circuit.CircuitMod.TileEntity;

import com.circuit.CircuitMod.CircuitEvents.ActivatedPacket;
import com.circuit.CircuitMod.CircuitEvents.EventPacket;
import com.circuit.CircuitMod.Utils.CircuitBoxModeUtils;
import com.circuit.CircuitMod.Utils.Modes.CircuitBoxMode;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

import javax.vecmath.Vector3d;
import java.util.ArrayList;

public class TileEntityCircuitBox extends TileEntityEventSender{

    public String Mode = "EMPTY";
    public int ModeNum = 0;
    public boolean[] Sides;
    public ForgeDirection[] SidesDir = new ForgeDirection[]{ForgeDirection.NORTH, ForgeDirection.SOUTH, ForgeDirection.EAST, ForgeDirection.WEST};
    public int[] ResetCount;
    public CircuitBoxMode CurrentMode;
    public ForgeDirection[] Inputs = new ForgeDirection[3];

    public int Rotation = 0;
    int ResetAt = 20;

    public void SetMode(CircuitBoxMode mode){
        this.Mode = mode.GetID();
        CurrentMode = mode;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {

        super.readFromNBT(nbtTagCompound);

        Rotation = nbtTagCompound.getInteger("ROT");
        ModeNum = nbtTagCompound.getInteger("ModeNum");

        Mode = nbtTagCompound.getString("ModeID");


        if(Mode != "EMPTY") {
            for (int i = 0; i < 4; i++)
                Sides[i] = nbtTagCompound.getBoolean("Sides_" + i);

            for (int i = 0; i < 4; i++)
                ResetCount[i] = nbtTagCompound.getInteger("ResetCountSide_" + i);


            CurrentMode = CircuitBoxModeUtils.GetMode(Mode);
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound) {
        super.writeToNBT(nbtTagCompound);

        nbtTagCompound.setInteger("ROT", Rotation);

        nbtTagCompound.setInteger("ModeNum", ModeNum);

        if(Mode != "EMPTY") {
            nbtTagCompound.setString("ModeID", Mode);

            for (int i = 0; i < 4; i++)
                nbtTagCompound.setBoolean("Sides_" + i, Sides[i]);

            for (int i = 0; i < 4; i++)
                nbtTagCompound.setInteger("ResetCountSide_" + i, ResetCount[i]);



        }

    }

    public TileEntityCircuitBox(){
        Sides = new boolean[4];
        ResetCount = new int[4];
    }

    public void SetSideState(ForgeDirection dir, boolean t){

            if(dir == ForgeDirection.NORTH) {
                Sides[0] = t;
            }else if(dir == ForgeDirection.SOUTH) {
                Sides[1] = t;
            }else if(dir == ForgeDirection.EAST) {
                Sides[2] = t;
            }else  if(dir == ForgeDirection.WEST) {
                Sides[3] = t;
            }


    }

    public int GetActiveInputs(){
        int g = 0;

        for(int i = 0; i < Inputs.length; i++){
            if(GetInputState(i))
                g += 1;
        }

        return g;
    }

    public boolean GetInputState(int input){
        if(Inputs.length >= input && Inputs[input] != null)
        return GetSideState(Inputs[input]);

        return false;
    }

    public boolean GetSideState(ForgeDirection dir){

        if(dir == ForgeDirection.NORTH) {
            return Sides[0];
        }else if(dir == ForgeDirection.SOUTH) {
            return Sides[1];
        }else if(dir == ForgeDirection.EAST) {
            return Sides[2];
        }else  if(dir == ForgeDirection.WEST) {
            return Sides[3];
        }

        return false;

    }

    public ForgeDirection GetOutputSide(){
        return ForgeDirection.getOrientation(Rotation);
    }

    public void updateEntity(){

        if (CurrentMode != null) {
            if(GetActiveInputs() >= CurrentMode.MinInputs() && GetActiveInputs() <= CurrentMode.MaxInputs()) {

                ActivatedPacket packet = new ActivatedPacket();
                packet.LastSentFrom = GetOutputSide().getOpposite();
                packet.Postitions.add(new Vector3d(xCoord, yCoord, zCoord));

                CurrentMode.OnUpdate(this, packet);
            }
        }

        if(CurrentMode == null){
            ModeNum = 0;
            SetMode(CircuitBoxModeUtils.Modes.get(ModeNum));

        }

        ArrayList<ForgeDirection> dd = new ArrayList<ForgeDirection>();

        for(int i = 0; i < SidesDir.length; i++){
            ForgeDirection side = SidesDir[i];

            if(side != GetOutputSide()){
                dd.add(side);
            }
        }

        for(int i = 0; i < dd.size(); i++){
            if(i < Inputs.length)
            Inputs[i] = dd.get(i);
        }

        for(int i = 0; i < ResetCount.length; i++){
            if(ResetCount[i] >= ResetAt && Sides[i]){
                ResetCount[i] = 0;
                Sides[i] = false;
            }else{

                if(Sides[i])
                ResetCount[i] += 1;
            }
        }



    }

    @Override
    public boolean CanConnectToTile(TileEntity tile) {
        int metaZ = worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
        int metaX = tile.getWorldObj().getBlockMetadata(tile.xCoord, tile.yCoord, tile.zCoord);
        boolean t = tile instanceof TileEntityCircuitCable ? ((TileEntityCircuitCable)tile).Direction == ForgeDirection.UP : true;
        boolean g = metaZ == 0 && metaX == 0 || metaZ == metaX;
        boolean j = tile instanceof TileEntityCircuitCable || tile instanceof TileEntityCircuitBox;

        if(j){

            return t && g;
        }

        return j && g && t || !j;
    }


    @Override
    public void OnRecived(EventPacket packet) {
        if(packet.LastSentFrom != GetOutputSide()) {
            SetSideState(packet.LastSentFrom, true);

        }
    }

    @Override
    public boolean CanRecive(EventPacket packet) {
        return true;
    }
}
