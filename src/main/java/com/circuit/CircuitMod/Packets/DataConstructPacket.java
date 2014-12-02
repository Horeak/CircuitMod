package com.circuit.CircuitMod.Packets;

import MiscUtils.Network.AbstractPacket;
import MiscUtils.Network.PacketHandler;
import com.circuit.CircuitMod.Main.CircuitMod;
import com.circuit.CircuitMod.TileEntity.DataBlocks.TileEntityDataConstructor;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.relauncher.Side;

public class DataConstructPacket extends AbstractPacket{

    int x, y, z;
    String value;
    boolean Save;

    public DataConstructPacket(){}
    public DataConstructPacket(TileEntity tile, String text, boolean Save){
        x = tile.getPos().getX();
        y = tile.getPos().getY();
        z = tile.getPos().getZ();

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
        BlockPos pos = new BlockPos(x,y,z);

        if(world.getTileEntity(pos) instanceof TileEntityDataConstructor){
            TileEntityDataConstructor tile = (TileEntityDataConstructor)world.getTileEntity(pos);
            if(!Save) {
                tile.SendPacketFromGUI(value);
                tile.SavedData = null;
            } else {
                tile.SavedData = value;
            }

            if(side == Side.SERVER){
                PacketHandler.sendToAll(new DataConstructPacket(tile, value, Save), CircuitMod.Utils.channels);
            }

            world.notifyNeighborsOfStateChange(pos, world.getBlockState(pos).getBlock());

        }


    }
}
