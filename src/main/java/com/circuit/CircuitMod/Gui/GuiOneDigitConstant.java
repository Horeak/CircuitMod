package com.circuit.CircuitMod.Gui;

import MiscUtils.Network.PacketHandler;
import com.circuit.CircuitMod.Main.CircuitMod;
import com.circuit.CircuitMod.Packets.OneDigitConstantValueChanged;
import com.circuit.CircuitMod.TileEntity.EventSenders.TileEntityOneDigitConstant;

public class GuiOneDigitConstant extends GuiNumberSelectorBaseGui
{
    TileEntityOneDigitConstant tile;

    public GuiOneDigitConstant(TileEntityOneDigitConstant tile) {
        super(tile.Constant);
        this.tile = tile;
    }

    @Override
    int length() {
        return 1;
    }

    @Override
    public void OnFinish(int TotalNum) {
        PacketHandler.sendToServer(new OneDigitConstantValueChanged(tile, TotalNum), CircuitMod.Utils.channels);

    }
}
