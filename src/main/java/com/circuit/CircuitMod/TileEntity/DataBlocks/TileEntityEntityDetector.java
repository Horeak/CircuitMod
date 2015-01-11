package com.circuit.CircuitMod.TileEntity.DataBlocks;

import com.circuit.CircuitMod.TileEntity.TileEntityEventSender;
import com.circuit.CircuitMod.Utils.ByteValues;
import com.circuit.CircuitMod.Utils.DataPacket;
import com.circuit.CircuitMod.Utils.EventPacket;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.ArrayList;
import java.util.List;

public class TileEntityEntityDetector extends TileEntityEventSender {

    public static int MaxRange = 10;

    //Send When Difference is found
    List<EntityLivingBase> CurrentList = new ArrayList<EntityLivingBase>();
    List<EntityLivingBase> LastCheckedList = new ArrayList<EntityLivingBase>();

    int UpdateTick = 0;
    static int TimeToUpdate = 20;


    public void updateEntity(){

        if(UpdateTick >= TimeToUpdate) {
            UpdateTick = 0;
            LastCheckedList = CurrentList;

            CurrentList = worldObj.getEntitiesWithinAABB(EntityLivingBase.class, AxisAlignedBB.getBoundingBox(xCoord - MaxRange, yCoord - MaxRange, zCoord - MaxRange, xCoord + MaxRange, yCoord + MaxRange, zCoord + MaxRange));

            for (Entity e : CurrentList) {
                boolean Found = false;

                for (Entity ee : LastCheckedList) {
                    if (e.isEntityEqual(ee)) {
                        Found = true;
                        break;
                    }
                }

                if (!Found) {
                    DataPacket packet = new DataPacket(-1, ByteValues.DataSignal.Value());

                    String name = null;

                    String d = (e.getDistance(xCoord, yCoord, zCoord) + "");
                    if(d.length() >= 4)
                        d = d.substring(0, 4);

                    //Find another way around this?
                    while(name == null || name.equalsIgnoreCase("unkown")){
                        name =  e.getCommandSenderName();
                    }

                    packet.SaveData("EntityName", name);
                    packet.SaveData("EntityId", Integer.toString(e.getEntityId()));
                    packet.SaveData("EntityDistance", d);

                    SendPacketToAround(packet);

                }

            }

        }else{
            UpdateTick += 1;

        }

    }


    @Override
    public void OnRecived(EventPacket packet) {}

    @Override
    public boolean CanConnectToTile(TileEntity tile, ForgeDirection dir) {
        return true;
    }

    @Override
    public boolean CanRecive(EventPacket packet) {
        return false;
    }
}
