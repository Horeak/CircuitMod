package com.circuit.CircuitMod.Main;

import MiscUtils.Register.BlockRegister;
import com.circuit.CircuitMod.Blocks.EventRecivers.ModBlockOneDigitDisplay;
import com.circuit.CircuitMod.Blocks.EventRecivers.ModBlockRedstoneEmitter;
import com.circuit.CircuitMod.Blocks.EventSenders.ModBlockDigitEquals;
import com.circuit.CircuitMod.Blocks.EventSenders.ModBlockOneDigitConstant;
import com.circuit.CircuitMod.Blocks.EventSenders.ModBlockOneDigitCounter;
import com.circuit.CircuitMod.Blocks.EventSenders.ModBlockRedstoneReciver;
import com.circuit.CircuitMod.Blocks.ModBlockCableConnectionPoint;
import com.circuit.CircuitMod.Blocks.ModBlockCircuitBox;
import com.circuit.CircuitMod.Blocks.ModBlockCircuitCable;
import com.circuit.CircuitMod.Blocks.ModBlockSignalGate;
import com.circuit.CircuitMod.Items.ItemBlockCableConnectionPoint;
import com.circuit.CircuitMod.Items.ItemBlockCircuitCable;
import com.circuit.CircuitMod.TileEntity.EventReceivers.TileEntityOneDigitDisplay;
import com.circuit.CircuitMod.TileEntity.EventReceivers.TileEntityRedstoneEmitter;
import com.circuit.CircuitMod.TileEntity.EventSenders.TileEntityDigitEquals;
import com.circuit.CircuitMod.TileEntity.EventSenders.TileEntityOneDigitConstant;
import com.circuit.CircuitMod.TileEntity.EventSenders.TileEntityOneDigitCounter;
import com.circuit.CircuitMod.TileEntity.EventSenders.TileEntityRedstoneReciver;
import com.circuit.CircuitMod.TileEntity.TileEntityCableConnectionPoint;
import com.circuit.CircuitMod.TileEntity.TileEntityCircuitBox;
import com.circuit.CircuitMod.TileEntity.TileEntityCircuitCable;
import com.circuit.CircuitMod.TileEntity.EventSenders.TileEntitySignalGate;
import com.circuit.CircuitMod.Utils.Ref;
import net.minecraft.block.Block;

public class ModBlocks {


    public static Block CircuitBox, CircuitCable, CableConnectionPoint;
    public static Block SignalGate, DigitEquals;
    public static Block RedstoneReciver, RedstoneEmitter;
    public static Block OneDigitDisplay, OneDigitConstant, OneDigitCounter;


    public static void RegisterBlocks(){
        BlockRegister Utils = new BlockRegister(CircuitMod.config, Ref.ModId);


        CircuitBox = new ModBlockCircuitBox().setCreativeTab(CircuitMod.CreativeTab);
        Utils.Register(CircuitBox, "CircuitBox", TileEntityCircuitBox.class);

        CircuitCable = new ModBlockCircuitCable().setCreativeTab(CircuitMod.CreativeTab);
        Utils.Register(CircuitCable, ItemBlockCircuitCable.class, "CircuitCable",  TileEntityCircuitCable.class);

        CableConnectionPoint = new ModBlockCableConnectionPoint().setCreativeTab(CircuitMod.CreativeTab).setBlockTextureName(Ref.ModId.toLowerCase() + ":CableConnectionPoint");
        Utils.Register(CableConnectionPoint, ItemBlockCableConnectionPoint.class, "CableConnectionPoint", TileEntityCableConnectionPoint.class);



        SignalGate = new ModBlockSignalGate().setCreativeTab(CircuitMod.CreativeTab);
        Utils.Register(SignalGate, "SignalGate", TileEntitySignalGate.class);

        DigitEquals = new ModBlockDigitEquals().setCreativeTab(CircuitMod.CreativeTab);
        Utils.Register(DigitEquals, "EqualsGate", TileEntityDigitEquals.class);


        OneDigitConstant = new ModBlockOneDigitConstant().setCreativeTab(CircuitMod.CreativeTab);
        Utils.Register(OneDigitConstant, "OneDigitConstant", TileEntityOneDigitConstant.class);

        OneDigitDisplay = new ModBlockOneDigitDisplay().setCreativeTab(CircuitMod.CreativeTab);
        Utils.Register(OneDigitDisplay,  "OneDigitDisplay", TileEntityOneDigitDisplay.class);

        OneDigitCounter = new ModBlockOneDigitCounter().setCreativeTab(CircuitMod.CreativeTab);
        Utils.Register(OneDigitCounter, "OneDigitCounter", TileEntityOneDigitCounter.class);

        RedstoneReciver = new ModBlockRedstoneReciver().setCreativeTab(CircuitMod.CreativeTab).setBlockTextureName(Ref.ModId.toLowerCase() + ":RedstoneEventReciver");
        Utils.Register(RedstoneReciver, "RedstoneReciver", TileEntityRedstoneReciver.class);

        RedstoneEmitter = new ModBlockRedstoneEmitter().setCreativeTab(CircuitMod.CreativeTab).setBlockTextureName(Ref.ModId.toLowerCase() + ":RedstoneEmitter");
        Utils.Register(RedstoneEmitter, "RedstoneEmitter", TileEntityRedstoneEmitter.class);




    }
}
