package com.circuit.CircuitMod.Packets;

import MiscUtils.Network.AbstractPacket;
import MiscUtils.Network.PacketHandler;
import com.circuit.CircuitMod.Main.CircuitMod;
import com.circuit.CircuitMod.TileEntity.DataBlocks.TileEntityDataConverter;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.relauncher.Side;

public class DataConverterPacket extends AbstractPacket{

    int x, y, z;
    String value, val;

    public DataConverterPacket(){}
    public DataConverterPacket(TileEntity tile, String text, String val){
        x = tile.getPos().getX();
        y = tile.getPos().getY();
        z = tile.getPos().getZ();

        value = text;
        this.val = val;

    }

    @Override
    public void toBytes(ByteBuf buffer, Side side) {

        buffer.writeInt(x);
        buffer.writeInt(y);
        buffer.writeInt(z);


        ByteBufUtils.writeUTF8String(buffer, value);
        ByteBufUtils.writeUTF8String(buffer, val);

    }

    @Override
    public void fromBytes(ByteBuf buffer, Side side) {

        x = buffer.readInt();
        y = buffer.readInt();
        z = buffer.readInt();


        value = ByteBufUtils.readUTF8String(buffer);
        val = ByteBufUtils.readUTF8String(buffer);

    }

    @Override
    public void onMessage(Side side, EntityPlayer player) {


        World world = player.getEntityWorld();
        BlockPos pos = new BlockPos(x,y,z);

        if(world.getTileEntity(pos) instanceof TileEntityDataConverter){
            TileEntityDataConverter tile = (TileEntityDataConverter)world.getTileEntity(pos);

            tile.DataTagUse = value;
            tile.DataTagFrom = val;

            if(side == Side.SERVER){
                PacketHandler.sendToAll(new DataConverterPacket(tile, value, val), CircuitMod.Utils.channels);
            }

            world.notifyNeighborsOfStateChange(pos, world.getBlockState(pos).getBlock());

        }


    }
}
