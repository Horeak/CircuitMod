package com.circuit.CircuitMod.Gui;

import MiscUtils.Network.PacketHandler;
import com.circuit.CircuitMod.Main.CircuitMod;
import com.circuit.CircuitMod.Packets.RandomNumberComponentValueChanged;
import com.circuit.CircuitMod.TileEntity.EventSenders.TileEntityRandomNumber;

public class GuiRandomNumberComponent extends GuiNumberSelectorBaseGui
{
    TileEntityRandomNumber tile;

    public GuiRandomNumberComponent(TileEntityRandomNumber tile) {
        super(tile.MaxValue);
        this.tile = tile;
    }

    @Override
    int length() {
        return 4;
    }

    @Override
    public void OnFinish(int TotalNum) {
        PacketHandler.sendToServer(new RandomNumberComponentValueChanged(tile, TotalNum), CircuitMod.Utils.channels);

    }
}
