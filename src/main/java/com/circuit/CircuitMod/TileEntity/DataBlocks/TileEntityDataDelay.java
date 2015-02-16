package com.circuit.CircuitMod.TileEntity.DataBlocks;

import MiscUtils.TileEntity.IBlockInfo;
import com.circuit.CircuitMod.Main.CircuitMod;
import com.circuit.CircuitMod.TileEntity.TileEntityEventSender;
import com.circuit.CircuitMod.Utils.ByteValues;
import com.circuit.CircuitMod.Utils.DataPacket;
import com.circuit.CircuitMod.Utils.DataStorage.DataIntegerValue;
import com.circuit.CircuitMod.Utils.EventPacket;
import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TileEntityDataDelay  extends TileEntityEventSender implements IBlockInfo {

    public ForgeDirection dir = ForgeDirection.UNKNOWN;
    int Reset = 0;
    static int ResetAt = 2;


    int Delay = 1;
    HashMap<Integer, EventPacket> packets = new HashMap<Integer, EventPacket>();

    public void updateEntity(){

        for(Map.Entry<Integer, EventPacket> ent : packets.entrySet()){
            if(ent.getKey() >= Delay){
                SendPacketTo(ent.getValue(), dir);
                packets.remove(ent.getKey(), ent.getValue());

            }else{
                int t = ent.getKey() + 1;
                EventPacket packetTemp = ent.getValue();

                packets.remove(ent.getKey(), ent.getValue());
                packets.put(t, packetTemp);
            }
        }

            if(Delay != 1) {
                if (Reset >= ResetAt) {
                    Delay = 1;
                    Reset = 0;
                }else{
                    Reset += 1;
                }
            }


    }


    @Override
    public void OnRecived(EventPacket packet) {

        if(packet.ByteValue != ByteValues.OneDigitNumber.Value() && packet.ByteValue != ByteValues.MultiDigitNumber.Value()) {
            if(Delay == 0){
                SendPacketTo(packet, dir);
            }else {
                packets.put(0, packet);
            }
        }else{
            if(Delay != 1){
                    if(packet.Data instanceof DataIntegerValue && ((DataIntegerValue)packet.Data).GetStoredObject() != Delay)
                        return;
                }
                Delay = packet.Data instanceof DataIntegerValue ? ((DataIntegerValue)packet.Data).GetStoredObject() : 0;
                Reset = 0;


        }

    }

    @Override
    public boolean CanConnectToTile(TileEntity tile, ForgeDirection dir) {
        return true;
    }

    @Override
    public boolean CanRecive(EventPacket packet) {
        return packet instanceof DataPacket || packet.ByteValue == ByteValues.OneDigitNumber.Value() || packet.ByteValue == ByteValues.MultiDigitNumber.Value();
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {
        super.readFromNBT(nbtTagCompound);

        dir = ForgeDirection.getOrientation(nbtTagCompound.getInteger("Dir"));
        Delay = nbtTagCompound.getInteger("Del");
        Reset = nbtTagCompound.getInteger("Reset");
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound) {
        super.writeToNBT(nbtTagCompound);

        nbtTagCompound.setInteger("Dir", dir.ordinal());
        nbtTagCompound.setInteger("Del", Delay);
        nbtTagCompound.setInteger("Reset", Reset);
    }

    @Override
    public void Info(ArrayList<String> Strings) {
        if(!CircuitMod.ShowHoverText)
            return;

        Strings.add(EnumChatFormatting.WHITE + worldObj.getBlock(xCoord, yCoord, zCoord).getLocalizedName() + EnumChatFormatting.RESET);

        MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
        int dim = worldObj.provider.dimensionId;
        double worldTps = getTPS(dim);

            Strings.add(StatCollector.translateToLocal("blockinfo.datadelay.tickDelay").replace("$delay", (EnumChatFormatting.GRAY + "" + Delay + EnumChatFormatting.RESET)));
            Strings.add(StatCollector.translateToLocal("blockinfo.datadelay.secondDelay").replace("$delay", (EnumChatFormatting.GRAY + "" + (Double.parseDouble(Integer.toString(Delay)) / worldTps) + EnumChatFormatting.RESET)));

    }

    /**
     * Copied from: https://github.com/ForgeEssentials/ForgeEssentialsMain/blob/fb6557131180b3ae69c917e0b4a82038c55c3237/src/main/java/com/forgeessentials/util/FunctionHelper.java#L351
     * Will be remade at a later date!
     */
    private static double getTPS(int dimID)
    {
        try
        {
            MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
            long sum = 0L;
            long[] ticks = server.worldTickTimes.get(dimID);
            for (int i = 0; i < ticks.length; ++i)
            {
                sum += ticks[i];
            }
            double tps = (double) sum / (double) ticks.length * 1.0E-6D;
            if (tps < 50)
            {
                return 20;
            }
            else
            {
                return 1000 / tps;
            }
        }
        catch (Exception e)
        {
            return -1;
        }
    }
}