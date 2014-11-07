package com.circuit.CircuitMod.Gui;

import MiscUtils.Network.PacketHandler;
import com.circuit.CircuitMod.Main.CircuitMod;
import com.circuit.CircuitMod.Packets.OneDigitCounterValueChanged;
import com.circuit.CircuitMod.TileEntity.EventSenders.TileEntityOneDigitCounter;
import net.minecraft.client.gui.GuiButton;

public class GuiOneDigitCounter  extends GuiNumberSelectorBaseGui
{
    TileEntityOneDigitCounter tile;

    public GuiOneDigitCounter(TileEntityOneDigitCounter tile) {
        super(tile.ResetAt);
        this.tile = tile;
    }

    @Override
    int length() {
        return 2;
    }

    @Override
    public void OnFinish(int TotalNum) {
        PacketHandler.sendToServer(new OneDigitCounterValueChanged(tile, TotalNum), CircuitMod.Utils.channels);

    }

    @Override
    protected void actionPerformed(GuiButton bt) {
        super.actionPerformed(bt);

        if(Number2 > 1)
            Number2 = 0;

        if(Number2 >= 1 && Number1 > 0)
            Number1 = 0;

        if(bt.id == 10 && Number2 < 1 && Number1 >= 9) {
            Number1 = 0;
            Number2 = 1;
        }


        if(bt.id == 20 && Number2 >= 1){
            Number2 = 0;
            Number1 = 9;
        }
    }
}
