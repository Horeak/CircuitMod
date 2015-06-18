package com.circuit.CircuitMod.Utils.CC;

import com.circuit.CircuitMod.Blocks.ModBlockComputerInterface;
import com.circuit.CircuitMod.TileEntity.TileEntityComputerInterface;
import dan200.computercraft.api.ComputerCraftAPI;
import dan200.computercraft.api.peripheral.IPeripheral;
import dan200.computercraft.api.peripheral.IPeripheralProvider;
import net.minecraft.block.Block;
import net.minecraft.world.World;

import java.util.HashMap;

public class PeripheralProvider implements IPeripheralProvider {


    public static HashMap<Class<? extends Block>, Class<? extends IPeripheral>> BlockPeripherals = new HashMap<Class<? extends Block>, Class<? extends IPeripheral>>();

    public static void addPeripherals(){

        BlockPeripherals.put(ModBlockComputerInterface.class, TileEntityComputerInterface.class);


        ComputerCraftAPI.registerPeripheralProvider(new PeripheralProvider());
    }


    @Override
    public IPeripheral getPeripheral(World world, int x, int y, int z, int side) {
        Block bl = world.getBlock(x,y,z);

        try {
            if (BlockPeripherals.get(bl.getClass()) != null) {
                return BlockPeripherals.get(bl.getClass()).newInstance();
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }
}
