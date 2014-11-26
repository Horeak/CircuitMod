package com.circuit.CircuitMod.Packets;

import MiscUtils.Network.AbstractPacket;
import MiscUtils.Network.PacketHandler;
import com.circuit.CircuitMod.Main.CircuitMod;
import com.circuit.CircuitMod.TileEntity.DataBlocks.TileEntityDataConverter;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.relauncher.Side;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class DataConverterPacket extends AbstractPacket{

    int x, y, z;
    String value;

    public DataConverterPacket(){}
    public DataConverterPacket(TileEntity tile, String text){
        x = tile.xCoord;
        y = tile.yCoord;
        z = tile.zCoord;

        value = text;

    }

    @Override
    public void toBytes(ByteBuf buffer, Side side) {

        buffer.writeInt(x);
        buffer.writeInt(y);
        buffer.writeInt(z);


        ByteBufUtils.writeUTF8String(buffer, value);

    }

    @Override
    public void fromBytes(ByteBuf buffer, Side side) {

        x = buffer.readInt();
        y = buffer.readInt();
        z = buffer.readInt();


        value = ByteBufUtils.readUTF8String(buffer);

    }

    @Override
    public void onMessage(Side side, EntityPlayer player) {


        World world = player.getEntityWorld();

        if(world.getTileEntity(x,y,z) instanceof TileEntityDataConverter){
            TileEntityDataConverter tile = (TileEntityDataConverter)world.getTileEntity(x,y,z);

                tile.DataTagUse = value;

            if(side == Side.SERVER){
                PacketHandler.sendToAll(new DataConverterPacket(tile, value), CircuitMod.Utils.channels);
            }

            world.notifyBlocksOfNeighborChange(x,y,z, world.getBlock(x,y,z));

        }


    }
}
