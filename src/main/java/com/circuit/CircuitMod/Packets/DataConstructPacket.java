package com.circuit.CircuitMod.Packets;

import MiscUtils.Network.AbstractPacket;
import MiscUtils.Network.PacketHandler;
import com.circuit.CircuitMod.Main.CircuitMod;
import com.circuit.CircuitMod.TileEntity.DataBlocks.TileEntityDataConstructor;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.relauncher.Side;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class DataConstructPacket extends AbstractPacket{

    int x, y, z;
    String value;
    boolean Save;

    public DataConstructPacket(){}
    public DataConstructPacket(TileEntity tile, String text, boolean Save){
        x = tile.xCoord;
        y = tile.yCoord;
        z = tile.zCoord;

        value = text;

        this.Save = Save;
    }

    @Override
    public void toBytes(ByteBuf buffer, Side side) {

        buffer.writeInt(x);
        buffer.writeInt(y);
        buffer.writeInt(z);

        buffer.writeBoolean(Save);

        ByteBufUtils.writeUTF8String(buffer, value);

    }

    @Override
    public void fromBytes(ByteBuf buffer, Side side) {

        x = buffer.readInt();
        y = buffer.readInt();
        z = buffer.readInt();

        Save = buffer.readBoolean();

        value = ByteBufUtils.readUTF8String(buffer);

    }

    @Override
    public void onMessage(Side side, EntityPlayer player) {


        World world = player.getEntityWorld();

        if(world.getTileEntity(x,y,z) instanceof TileEntityDataConstructor){
            TileEntityDataConstructor tile = (TileEntityDataConstructor)world.getTileEntity(x,y,z);
            if(!Save) {
                tile.SendPacketFromGUI(value);
                tile.SavedData = null;
            } else {
                tile.SavedData = value;
            }

            if(side == Side.SERVER){
                PacketHandler.sendToAll(new DataConstructPacket(tile, value, Save), CircuitMod.Utils.channels);
            }

            world.notifyBlocksOfNeighborChange(x,y,z, world.getBlock(x,y,z));

        }


    }
}
