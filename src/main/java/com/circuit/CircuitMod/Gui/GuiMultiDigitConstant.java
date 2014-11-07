package com.circuit.CircuitMod.Gui;

import MiscUtils.Network.PacketHandler;
import com.circuit.CircuitMod.Main.CircuitMod;
import com.circuit.CircuitMod.Packets.MultiDigitConstantValueChanged;
import com.circuit.CircuitMod.TileEntity.EventSenders.TileEntityMultiDigitConstant;

public class GuiMultiDigitConstant extends GuiNumberSelectorBaseGui
{
    TileEntityMultiDigitConstant tile;

    public GuiMultiDigitConstant(TileEntityMultiDigitConstant tile) {
        super(tile.Constant);
        this.tile = tile;
    }

    @Override
    int length() {
        return 4;
    }

    @Override
    public void OnFinish(int TotalNum) {
         PacketHandler.sendToServer(new MultiDigitConstantValueChanged(tile, TotalNum), CircuitMod.Utils.channels);

    }
}
