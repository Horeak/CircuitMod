package com.circuit.CircuitMod.Utils.CircuitUtils;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public interface ICircuitConnector {

    public boolean CanConnectToTile(TileEntity tile, ForgeDirection dir);
}
