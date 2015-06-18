package com.circuit.CircuitMod.Packets;

import MiscUtils.Network.AbstractPacket;
import MiscUtils.Network.PacketHandler;
import com.circuit.CircuitMod.Main.CircuitMod;
import com.circuit.CircuitMod.TileEntity.DataBlocks.TileEntityDataConstructor;
import com.circuit.CircuitMod.Utils.ByteValues;
import com.circuit.CircuitMod.Utils.DataStorage.DataIntegerValue;
import com.circuit.CircuitMod.Utils.DataStorage.DataStringValue;
import com.circuit.CircuitMod.Utils.EventPacket;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.relauncher.Side;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class DataConstructPacket extends AbstractPacket{

    int x, y, z, mode;
    String value;

    public DataConstructPacket(){}
    public DataConstructPacket(TileEntity tile, String text, int Mode){
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

	        ByteValues val = ByteValues.getValue(mode);

            if(val == ByteValues.DataSignal) {
	            tile.SendPacketFromGUI(new DataStringValue(value));

            }else if(val == ByteValues.OneDigitNumber){
	            EventPacket packet = new EventPacket(-1, val.Value());
	            packet.Data = new DataIntegerValue(Integer.parseInt(value));

	            tile.SendPacketFromGUI(packet);

            }else if(val == ByteValues.MultiDigitNumber){
	            EventPacket packet = new EventPacket(-1, val.Value());
	            packet.Data = new DataIntegerValue(Integer.parseInt(value));

	            tile.SendPacketFromGUI(packet);

            }else if(val == ByteValues.OnSignal){
	            tile.SendPacketFromGUI(new EventPacket(-1, val.Value()));

            }

            if(side == Side.SERVER){
                PacketHandler.sendToAll(new DataConstructPacket(tile, value, mode), CircuitMod.Utils.channels);
            }

            world.notifyBlocksOfNeighborChange(x, y, z, world.getBlock(x, y, z));
        }


    }
}
