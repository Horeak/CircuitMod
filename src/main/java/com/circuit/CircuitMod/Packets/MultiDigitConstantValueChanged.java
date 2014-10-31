package com.circuit.CircuitMod.Packets;

import MiscUtils.Network.AbstractPacket;
import MiscUtils.Network.PacketHandler;
import com.circuit.CircuitMod.Main.CircuitMod;
import com.circuit.CircuitMod.TileEntity.EventSenders.TileEntityMultiDigitConstant;
import cpw.mods.fml.relauncher.Side;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class MultiDigitConstantValueChanged extends AbstractPacket{

    int x, y, z;
    int value;

    public MultiDigitConstantValueChanged(){}
    public MultiDigitConstantValueChanged(TileEntityMultiDigitConstant tile, int Num){
        x = tile.xCoord;
        y = tile.yCoord;
        z = tile.zCoord;

        value = Num;
    }

    @Override
    public void toBytes(ByteBuf buffer, Side side) {

        buffer.writeInt(x);
        buffer.writeInt(y);
        buffer.writeInt(z);

        buffer.writeInt(value);

    }

    @Override
    public void fromBytes(ByteBuf buffer, Side side) {

        x = buffer.readInt();
        y = buffer.readInt();
        z = buffer.readInt();

        value = buffer.readInt();

    }

    @Override
    public void onMessage(Side side, EntityPlayer player) {


        World world = player.getEntityWorld();

        if(world.getTileEntity(x,y,z) instanceof TileEntityMultiDigitConstant){
            TileEntityMultiDigitConstant tile = (TileEntityMultiDigitConstant)world.getTileEntity(x,y,z);
            tile.Constant = value;


            if(side == Side.SERVER){
                PacketHandler.sendToAll(new MultiDigitConstantValueChanged(tile, value), CircuitMod.Utils.channels);
            }

        }


    }
}
