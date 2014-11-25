package com.circuit.CircuitMod.TileEntity.DataBlocks;

import com.circuit.CircuitMod.TileEntity.TileEntityEventSender;
import com.circuit.CircuitMod.Utils.ByteValues;
import com.circuit.CircuitMod.Utils.EventPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityDataConstructor extends TileEntityEventSender {

    public void SendPacketFromGUI(String Data){
        EventPacket packet = CreatePacket();
        packet.NBT.setString("Data", Data);
        SendPacketToAround(packet);
    }

    @Override
    public void OnRecived(EventPacket packet) {

    }

    public EventPacket CreatePacket(){
        EventPacket packet = new EventPacket(-1 ,ByteValues.DataSignal.Value());
        return packet;
    }

    @Override
    public boolean CanConnectToTile(TileEntity tile, ForgeDirection dir) {
        return true;
    }

    @Override
    public boolean CanRecive(EventPacket packet) {
        return false;
    }
}
