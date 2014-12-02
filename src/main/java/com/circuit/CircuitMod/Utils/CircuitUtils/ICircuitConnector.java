package com.circuit.CircuitMod.Utils.CircuitUtils;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;

public interface ICircuitConnector {

    public boolean CanConnectToTile(TileEntity tile, EnumFacing dir);
}
