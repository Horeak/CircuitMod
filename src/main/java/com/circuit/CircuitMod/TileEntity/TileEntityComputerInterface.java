package com.circuit.CircuitMod.TileEntity;

import com.circuit.CircuitMod.Utils.ByteValues;
import com.circuit.CircuitMod.Utils.DataPacket;
import com.circuit.CircuitMod.Utils.DataStorage.DataStringValue;
import com.circuit.CircuitMod.Utils.EventPacket;
import cpw.mods.fml.common.FMLCommonHandler;
import dan200.computercraft.api.lua.ILuaContext;
import dan200.computercraft.api.lua.ILuaTask;
import dan200.computercraft.api.lua.LuaException;
import dan200.computercraft.api.peripheral.IComputerAccess;
import dan200.computercraft.api.peripheral.IPeripheral;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityComputerInterface extends TileEntityEventSender implements IPeripheral {

	//TODO World is not synced with the ComputerThread
    volatile int x,y,z;
	volatile World world;

    public void validate()
    {
        super.validate();

	    x = xCoord;
	    y = yCoord;
	    z = zCoord;
	    world = worldObj;

        System.out.println(Thread.currentThread() + " | " + FMLCommonHandler.instance().getEffectiveSide() + " | " + x + " | " + y  + " | " + z + " | " + world);
    }


	public World getWorld(){
		return world;
	}

    @Override
    public void OnRecived(EventPacket packet) {
    }

    @Override
    public boolean CanConnectToTile(TileEntity tile, ForgeDirection dir) {
        return true;
    }

    @Override
    public boolean CanRecive(EventPacket packet) {
        return false;
    }

    @Override
    public String getType() {
        return "computerInterface";
    }

    @Override
    public String[] getMethodNames() {
        return new String[]{"createPacket"};
    }



    @Override
    public Object[] callMethod(IComputerAccess computer, ILuaContext context, int method, Object[] arguments) throws LuaException, InterruptedException {

	    System.out.println("Arguements: ");
	    for(Object t : arguments)
        System.out.println(" - " + t);

        if(method == 0){
                if (arguments.length < 1 || arguments.length > 3) {
                    throw new LuaException("command usage: createPacket(<DataType>, [DataContents], [timeOut])");
                } else {

                    Object dataTypeObject = arguments[0];

                    if (dataTypeObject instanceof String) {
                        String dataType = (String) dataTypeObject;
                        ByteValues byt = null;
                        int timeOut = -1;
                        Object contents = null;


                        if (arguments.length >= 2)
                            contents = arguments[1];

                        if (arguments.length == 3)
                            timeOut = Integer.parseInt((String) arguments[2]);


                        for (ByteValues bu : ByteValues.values()) {
                            if (bu.Id.equalsIgnoreCase(dataType)) {
                                byt = bu;
                            }
                        }


                         EventPacket packet = null;

                        if (byt == ByteValues.DataSignal) {
                            packet = new DataPacket(timeOut);
                            ((DataPacket) packet).SaveData("data", new DataStringValue("test"));
                        }


                        final EventPacket packetUse = packet;

                        if (packetUse != null) {
                           SendPacketToAround(packet);


                            context.executeMainThreadTask(new ILuaTask() {
                                @Override
                                public Object[] execute() throws LuaException {
                                    return new Object[0];
                                }
                            });
                        }

                    } else {
                        throw new LuaException("DataType is required to be a string");
                    }


                }

        }


        return null;
    }

    @Override
    public void attach(IComputerAccess computer) {

    }

    @Override
    public void detach(IComputerAccess computer) {
    }

    @Override
    public boolean equals(IPeripheral other) {
        if ((other != null) && ((other instanceof TileEntityComputerInterface)))
        {
            TileEntityComputerInterface interFace = (TileEntityComputerInterface)other;
            if (interFace == this) {
                return true;
            }
        }
        return false;
    }
}
