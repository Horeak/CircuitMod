package com.circuit.CircuitMod.Packets;

import MiscUtils.Network.AbstractPacket;
import MiscUtils.Network.PacketHandler;
import com.circuit.CircuitMod.Main.CircuitMod;
import com.circuit.CircuitMod.TileEntity.EventSenders.TileEntityOneDigitCounter;
import cpw.mods.fml.relauncher.Side;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class OneDigitCounterValueChanged extends AbstractPacket{

    int x, y, z;
    int value;

    public OneDigitCounterValueChanged(){}
    public OneDigitCounterValueChanged(TileEntityOneDigitCounter tile, int Num){
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

        if(world.getTileEntity(x,y,z) instanceof TileEntityOneDigitCounter){
            TileEntityOneDigitCounter tile = (TileEntityOneDigitCounter)world.getTileEntity(x,y,z);
            tile.ResetAt = value;


            if(side == Side.SERVER){
                PacketHandler.sendToAll(new OneDigitCounterValueChanged(tile, value), CircuitMod.Utils.channels);
            }

            world.notifyBlocksOfNeighborChange(x,y,z, world.getBlock(x,y,z));

        }


    }
}
