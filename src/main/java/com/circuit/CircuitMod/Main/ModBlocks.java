package com.circuit.CircuitMod.Main;

import MiscUtils.Register.BlockRegister;
import com.circuit.CircuitMod.Blocks.EventRecivers.ModBlockMultiDigitDisplay;
import com.circuit.CircuitMod.Blocks.EventRecivers.ModBlockOneDigitDisplay;
import com.circuit.CircuitMod.Blocks.EventRecivers.ModBlockRedstoneEmitter;
import com.circuit.CircuitMod.Blocks.EventRecivers.ModBlockVariable;
import com.circuit.CircuitMod.Blocks.EventSenders.ModBlockAddittionGate;
import com.circuit.CircuitMod.Blocks.EventSenders.ModBlockDigitEquals;
import com.circuit.CircuitMod.Blocks.EventSenders.ModBlockDividationGate;
import com.circuit.CircuitMod.Blocks.EventSenders.ModBlockMultiDigitConstant;
import com.circuit.CircuitMod.Blocks.EventSenders.ModBlockMultiDigitCounter;
import com.circuit.CircuitMod.Blocks.EventSenders.ModBlockMultiplicationGate;
import com.circuit.CircuitMod.Blocks.EventSenders.ModBlockOneDigitConstant;
import com.circuit.CircuitMod.Blocks.EventSenders.ModBlockOneDigitCounter;
import com.circuit.CircuitMod.Blocks.EventSenders.ModBlockRandomNumberComponent;
import com.circuit.CircuitMod.Blocks.EventSenders.ModBlockRedstoneReciver;
import com.circuit.CircuitMod.Blocks.EventSenders.ModBlockSubtractionGate;
import com.circuit.CircuitMod.Blocks.ModBlockCableConnectionPoint;
import com.circuit.CircuitMod.Blocks.ModBlockCircuitBox;
import com.circuit.CircuitMod.Blocks.ModBlockCircuitCable;
import com.circuit.CircuitMod.Blocks.ModBlockSignalGate;
import com.circuit.CircuitMod.Items.ItemBlockCableConnectionPoint;
import com.circuit.CircuitMod.Items.ItemBlockCircuitCable;
import com.circuit.CircuitMod.TileEntity.EventReceivers.TileEntityMultiDigitDisplay;
import com.circuit.CircuitMod.TileEntity.EventReceivers.TileEntityOneDigitDisplay;
import com.circuit.CircuitMod.TileEntity.EventReceivers.TileEntityRedstoneEmitter;
import com.circuit.CircuitMod.TileEntity.EventReceivers.TileEntityVariable;
import com.circuit.CircuitMod.TileEntity.EventSenders.TileEntityAdditionGate;
import com.circuit.CircuitMod.TileEntity.EventSenders.TileEntityDigitEquals;
import com.circuit.CircuitMod.TileEntity.EventSenders.TileEntityDividationGate;
import com.circuit.CircuitMod.TileEntity.EventSenders.TileEntityMultiDigitConstant;
import com.circuit.CircuitMod.TileEntity.EventSenders.TileEntityMultiDigitCounter;
import com.circuit.CircuitMod.TileEntity.EventSenders.TileEntityMultiplicationGate;
import com.circuit.CircuitMod.TileEntity.EventSenders.TileEntityOneDigitConstant;
import com.circuit.CircuitMod.TileEntity.EventSenders.TileEntityOneDigitCounter;
import com.circuit.CircuitMod.TileEntity.EventSenders.TileEntityRandomNumber;
import com.circuit.CircuitMod.TileEntity.EventSenders.TileEntityRedstoneReciver;
import com.circuit.CircuitMod.TileEntity.EventSenders.TileEntitySignalGate;
import com.circuit.CircuitMod.TileEntity.EventSenders.TileEntitySubtractionGate;
import com.circuit.CircuitMod.TileEntity.TileEntityCableConnectionPoint;
import com.circuit.CircuitMod.TileEntity.TileEntityCircuitBox;
import com.circuit.CircuitMod.TileEntity.TileEntityCircuitCable;
import com.circuit.CircuitMod.Utils.Ref;
import net.minecraft.block.Block;

public class ModBlocks {


    public static Block CircuitBox, CircuitCable, CableConnectionPoint;
    public static Block Variable, RandomNumberComponent;
    public static Block SignalGate, DigitEquals, AdditionGate, SubtractionGate, MultiplicationGate, DividationGate;
    public static Block RedstoneReciver, RedstoneEmitter;
    public static Block OneDigitDisplay, OneDigitConstant, OneDigitCounter;
    public static Block MultiDigitDisplay, MultiDigitConstant, MultiDigitCounter;


    public static void RegisterBlocks(){
        BlockRegister Utils = new BlockRegister(CircuitMod.config, Ref.ModId);


        //Main blocks
        CircuitBox = new ModBlockCircuitBox().setCreativeTab(CircuitMod.CreativeTab);
        Utils.Register(CircuitBox, "CircuitBox", TileEntityCircuitBox.class);

        CircuitCable = new ModBlockCircuitCable().setCreativeTab(CircuitMod.CreativeTab);
        Utils.Register(CircuitCable, ItemBlockCircuitCable.class, "CircuitCable",  TileEntityCircuitCable.class);

        CableConnectionPoint = new ModBlockCableConnectionPoint().setCreativeTab(CircuitMod.CreativeTab).setBlockTextureName(Ref.ModId.toLowerCase() + ":CableConnectionPoint");
        Utils.Register(CableConnectionPoint, ItemBlockCableConnectionPoint.class, "CableConnectionPoint", TileEntityCableConnectionPoint.class);



        //Redstone handlers
        RedstoneReciver = new ModBlockRedstoneReciver().setCreativeTab(CircuitMod.CreativeTab).setBlockTextureName(Ref.ModId.toLowerCase() + ":RedstoneEventReciver");
        Utils.Register(RedstoneReciver, "RedstoneReciver", TileEntityRedstoneReciver.class);

        RedstoneEmitter = new ModBlockRedstoneEmitter().setCreativeTab(CircuitMod.CreativeTab).setBlockTextureName(Ref.ModId.toLowerCase() + ":RedstoneEmitter");
        Utils.Register(RedstoneEmitter, "RedstoneEmitter", TileEntityRedstoneEmitter.class);


        //Random things
        Variable = new ModBlockVariable().setCreativeTab(CircuitMod.CreativeTab);
        Utils.Register(Variable, "Variable", TileEntityVariable.class);

        RandomNumberComponent = new ModBlockRandomNumberComponent().setCreativeTab(CircuitMod.CreativeTab);
        Utils.Register(RandomNumberComponent, "RandomNumberComponent", TileEntityRandomNumber.class);


        //Gates
        SignalGate = new ModBlockSignalGate().setCreativeTab(CircuitMod.CreativeTab);
        Utils.Register(SignalGate, "SignalGate", TileEntitySignalGate.class);

        DigitEquals = new ModBlockDigitEquals().setCreativeTab(CircuitMod.CreativeTab);
        Utils.Register(DigitEquals, "EqualsGate", TileEntityDigitEquals.class);

        AdditionGate = new ModBlockAddittionGate().setCreativeTab(CircuitMod.CreativeTab);
        Utils.Register(AdditionGate, "AdditionGate", TileEntityAdditionGate.class);

        SubtractionGate = new ModBlockSubtractionGate().setCreativeTab(CircuitMod.CreativeTab);
        Utils.Register(SubtractionGate, "SubtractionGate", TileEntitySubtractionGate.class);

        MultiplicationGate = new ModBlockMultiplicationGate().setCreativeTab(CircuitMod.CreativeTab);
        Utils.Register(MultiplicationGate, "MultiplicationGate", TileEntityMultiplicationGate.class);

        DividationGate = new ModBlockDividationGate().setCreativeTab(CircuitMod.CreativeTab);
        Utils.Register(DividationGate, "DividationGate", TileEntityDividationGate.class);



        //One digit utils
        OneDigitConstant = new ModBlockOneDigitConstant().setCreativeTab(CircuitMod.CreativeTab);
        Utils.Register(OneDigitConstant, "OneDigitConstant", TileEntityOneDigitConstant.class);

        OneDigitDisplay = new ModBlockOneDigitDisplay().setCreativeTab(CircuitMod.CreativeTab);
        Utils.Register(OneDigitDisplay,  "OneDigitDisplay", TileEntityOneDigitDisplay.class);

        OneDigitCounter = new ModBlockOneDigitCounter().setCreativeTab(CircuitMod.CreativeTab);
        Utils.Register(OneDigitCounter, "OneDigitCounter", TileEntityOneDigitCounter.class);




        //Multi digit utils
        MultiDigitDisplay = new ModBlockMultiDigitDisplay().setCreativeTab(CircuitMod.CreativeTab);
        Utils.Register(MultiDigitDisplay, "MultiDigitDisplay", TileEntityMultiDigitDisplay.class);

        MultiDigitConstant = new ModBlockMultiDigitConstant().setCreativeTab(CircuitMod.CreativeTab);
        Utils.Register(MultiDigitConstant, "MultiDigitConstant", TileEntityMultiDigitConstant.class);

        MultiDigitCounter = new ModBlockMultiDigitCounter().setCreativeTab(CircuitMod.CreativeTab);
        Utils.Register(MultiDigitCounter, "MultiDigitCounter", TileEntityMultiDigitCounter.class);





    }
}
