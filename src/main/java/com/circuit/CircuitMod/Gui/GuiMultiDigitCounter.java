package com.circuit.CircuitMod.Gui;

import MiscUtils.Network.PacketHandler;
import com.circuit.CircuitMod.Main.CircuitMod;
import com.circuit.CircuitMod.Packets.MultiDigitCounterValueChanged;
import com.circuit.CircuitMod.TileEntity.EventSenders.TileEntityMultiDigitCounter;

public class GuiMultiDigitCounter extends GuiNumberSelectorBaseGui
{
    TileEntityMultiDigitCounter tile;

    public GuiMultiDigitCounter(TileEntityMultiDigitCounter tile) {
        super(tile.ResetAt);
        this.tile = tile;
    }

    @Override
    int length() {
        return 4;
    }

    @Override
    public void OnFinish(int TotalNum) {
        PacketHandler.sendToServer(new MultiDigitCounterValueChanged(tile, TotalNum), CircuitMod.Utils.channels);

    }
}

