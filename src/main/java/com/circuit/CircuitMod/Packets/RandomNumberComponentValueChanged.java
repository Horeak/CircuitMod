package com.circuit.CircuitMod.Packets;

import MiscUtils.Network.AbstractPacket;
import MiscUtils.Network.PacketHandler;
import com.circuit.CircuitMod.Main.CircuitMod;
import com.circuit.CircuitMod.TileEntity.EventSenders.TileEntityRandomNumber;
import cpw.mods.fml.relauncher.Side;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class RandomNumberComponentValueChanged extends AbstractPacket{

    int x, y, z;
    int value;

    public RandomNumberComponentValueChanged(){}
    public RandomNumberComponentValueChanged(TileEntityRandomNumber tile, int Num){
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

        if(world.getTileEntity(x,y,z) instanceof TileEntityRandomNumber){
            TileEntityRandomNumber tile = (TileEntityRandomNumber)world.getTileEntity(x,y,z);
            tile.MaxValue = value;

            if(tile.MaxValue < 1)
                tile.MaxValue = 1;


            if(side == Side.SERVER){
                PacketHandler.sendToAll(new RandomNumberComponentValueChanged(tile, value), CircuitMod.Utils.channels);
            }

        }


    }
}
