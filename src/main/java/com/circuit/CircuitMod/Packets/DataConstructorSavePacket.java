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

public class DataConstructorSavePacket extends AbstractPacket{

    int x, y, z, mode;
    String value;

    public DataConstructorSavePacket(){}
    public DataConstructorSavePacket(TileEntity tile, String text, int Mode){
        x = tile.xCoord;
        y = tile.yCoord;
        z = tile.zCoord;

        value = text;
        this.mode = Mode;

    }

    @Override
    public void toBytes(ByteBuf buffer, Side side) {

        buffer.writeInt(x);
        buffer.writeInt(y);
        buffer.writeInt(z);

        buffer.writeInt(mode);

        ByteBufUtils.writeUTF8String(buffer, value);

    }

    @Override
    public void fromBytes(ByteBuf buffer, Side side) {

        x = buffer.readInt();
        y = buffer.readInt();
        z = buffer.readInt();

        mode = buffer.readInt();

        value = ByteBufUtils.readUTF8String(buffer);
    }

    @Override
    public void onMessage(Side side, EntityPlayer player) {
        World world = player.getEntityWorld();
	    
        if(world.getTileEntity(x,y,z) instanceof TileEntityDataConstructor){
            TileEntityDataConstructor tile = (TileEntityDataConstructor)world.getTileEntity(x,y,z);

            tile.SavedData = value;
	        tile.SavedMode = mode;

            if(side == Side.SERVER){
                PacketHandler.sendToAll(new DataConstructorSavePacket(tile, value, mode), CircuitMod.Utils.channels);
            }

        }


    }
}
