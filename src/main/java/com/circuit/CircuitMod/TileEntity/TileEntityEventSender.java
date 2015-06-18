package com.circuit.CircuitMod.TileEntity;

import MiscUtils.TileEntity.ModTileEntity;
import com.circuit.CircuitMod.Utils.CircuitUtils.ICircuitConnector;
import com.circuit.CircuitMod.Utils.CircuitUtils.IEventRec;
import com.circuit.CircuitMod.Utils.EventPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import javax.vecmath.Vector3d;

public abstract class TileEntityEventSender extends ModTileEntity implements IEventRec, ICircuitConnector{


    public World getWorld(){
        return worldObj;
    }

    public void SendPacketTo(EventPacket packet,ForgeDirection dir){

            if (dir == packet.LastSentFrom || dir == ForgeDirection.UNKNOWN || packet == null || packet.TimedOut || packet.ByteValue < 0)
                return;

            try {
	            if(getWorld() != null) {
		            if (getWorld().getTileEntity(xCoord + dir.offsetX, yCoord + dir.offsetY, zCoord + dir.offsetZ) instanceof IEventRec) {
			            TileEntity tile = getWorld().getTileEntity(xCoord + dir.offsetX, yCoord + dir.offsetY, zCoord + dir.offsetZ);

			            if (tile instanceof ICircuitConnector) {

				            if (((ICircuitConnector) tile).CanConnectToTile(this, dir.getOpposite()) && CanConnectToTile(tile, dir)) {


					            if (tile instanceof IEventRec) {
						            IEventRec tileE = (IEventRec) tile;
						            if (tileE.CanRecive(packet) && tileE != null) {
							            Vector3d vec = new Vector3d(tile.xCoord, tile.yCoord, tile.zCoord);

							            if (!EventPacket.ContainesVector(packet, vec)) {

								            EventPacket sendPacket = packet.GetInstance();
								            sendPacket.RecreatingPacket(packet);
								            sendPacket.LastSentFrom = dir.getOpposite();
								            sendPacket.Postitions.add(vec);


								            tileE.OnRecived(sendPacket);


							            }


						            }

					            }


				            }

			            }
		            }

	            }else{
		            System.out.println("Empty world.");
	            }
            } catch (Exception e) {
                e.printStackTrace();
            }



    }

    public void SendPacketToAround(EventPacket packet){
        for(ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS){
            if(dir != packet.LastSentFrom && packet != null)
           SendPacketTo(packet, dir);
        }
    }





    @Override
    public abstract void OnRecived(EventPacket packet);

    @Override
    public boolean CanRecive(EventPacket packet) {
        return true;
    }

    @Override
    public abstract boolean CanConnectToTile(TileEntity tile, ForgeDirection dir);
}
