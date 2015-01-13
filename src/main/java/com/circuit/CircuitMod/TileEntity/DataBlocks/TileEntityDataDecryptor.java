package com.circuit.CircuitMod.TileEntity.DataBlocks;

import com.circuit.CircuitMod.TileEntity.TileEntityEventSender;
import com.circuit.CircuitMod.Utils.DataPacket;
import com.circuit.CircuitMod.Utils.EventPacket;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityDataDecryptor extends TileEntityEventSender {

    public String EncryptPassword = null;
    public ForgeDirection dir = ForgeDirection.UNKNOWN;


    @Override
    public void OnRecived(EventPacket packet) {

        if(dir != null) {
            DataPacket pack = (DataPacket) packet;

            if(EncryptPassword != null && !EncryptPassword.isEmpty()){
                if(pack.Encrypted){
                   pack.DecryptPacket(EncryptPassword);

                }
            }


            SendPacketTo(pack, dir);
        }
    }

    @Override
    public boolean CanConnectToTile(TileEntity tile, ForgeDirection dir) {
        return true;
    }

    @Override
    public boolean CanRecive(EventPacket packet) {
        return packet instanceof DataPacket;
    }


    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {
        super.readFromNBT(nbtTagCompound);

        dir = ForgeDirection.getOrientation(nbtTagCompound.getInteger("Dir"));
        EncryptPassword = nbtTagCompound.getString("EncryptPassword");

        if(EncryptPassword.equals("~~EMPTY~~"))
            EncryptPassword = null;

    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound) {
        super.writeToNBT(nbtTagCompound);

        nbtTagCompound.setInteger("Dir", dir.ordinal());

        if(EncryptPassword == null || EncryptPassword.isEmpty()){
            nbtTagCompound.setString("EncryptPassword", "~~EMPTY~~");
        }else {
            nbtTagCompound.setString("EncryptPassword", EncryptPassword);
        }

    }
}
