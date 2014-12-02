package com.circuit.CircuitMod.TileEntity;

import com.circuit.CircuitMod.Blocks.ModBlockCableConnectionPoint;
import com.circuit.CircuitMod.Utils.EventPacket;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

import java.awt.*;

public class TileEntityCircuitCable extends TileEntityEventSender {

    public static Color BaseColor = new Color(0x002810);

    @Override
    public void OnRecived(EventPacket packet) {

        EnumFacing dk = packet.LastSentFrom;
        for(EnumFacing dkk : EnumFacing.values()){
            if(dkk != dk && dkk != Direction && dkk != Direction.getOpposite()){
                SendPacketTo(packet, dkk);

            }

        }

    }

    @Override
    public boolean CanConnectToTile(TileEntity tile, EnumFacing dir) {
        if(tile == null)
            return false;

        IBlockState metaZZ = worldObj.getBlockState(new BlockPos(getPos().getX(), getPos().getY(), getPos().getZ()));
        IBlockState metaXX = tile.getWorld().getBlockState(new BlockPos(tile.getPos().getX(), tile.getPos().getY(), tile.getPos().getZ()));

        int metaZ = Integer.parseInt(metaZZ.getProperties().get(ModBlockCableConnectionPoint.COLOR).toString());
        int metaX = Integer.parseInt(metaXX.getProperties().get(ModBlockCableConnectionPoint.COLOR).toString());

        boolean t = tile instanceof TileEntityCircuitCable || tile instanceof TileEntityCircuitBox;
        boolean g = metaX == 0 && metaZ == 0 || metaZ == metaX;

        boolean j = dir != null ? dir != Direction && dir != Direction.getOpposite() : false;

        return t && g && j || !t && j;
    }
    public EnumFacing Direction = EnumFacing.UP;

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {

        super.readFromNBT(nbtTagCompound);

        Direction = EnumFacing.getFront(nbtTagCompound.getInteger("DIR"));


    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound) {
        super.writeToNBT(nbtTagCompound);

        nbtTagCompound.setInteger("DIR", Direction.ordinal());

    }

}
