package com.circuit.CircuitMod.TileEntity;

import com.circuit.CircuitMod.Blocks.ModBlockCableConnectionPoint;
import com.circuit.CircuitMod.Utils.EventPacket;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;


public class TileEntityCableConnectionPoint extends TileEntityEventSender {

    @Override
    public void OnRecived(EventPacket packet) {
        SendPacketToAround(packet);
    }


    @Override
    public boolean CanConnectToTile(TileEntity tile, EnumFacing dir) {

        IBlockState metaZZ = worldObj.getBlockState(new BlockPos(getPos().getX(), getPos().getY(), getPos().getZ()));
        IBlockState metaXX = tile.getWorld().getBlockState(new BlockPos(tile.getPos().getX(), tile.getPos().getY(), tile.getPos().getZ()));

        int metaZ = Integer.parseInt(metaZZ.getProperties().get(ModBlockCableConnectionPoint.COLOR).toString());
        int metaX = Integer.parseInt(metaXX.getProperties().get(ModBlockCableConnectionPoint.COLOR).toString());


        boolean g = metaX == 0 && metaZ != 0 || metaZ == 0 && metaX != 0 ? true : metaZ == metaX;
        boolean j = tile instanceof TileEntityCircuitCable || tile instanceof TileEntityCircuitBox || tile instanceof TileEntityCableConnectionPoint;

        return j && g || !j;
    }
}
