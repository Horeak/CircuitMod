package com.circuit.CircuitMod.Main;

import MiscUtils.Register.BlockRegister;
import com.circuit.CircuitMod.Blocks.EventRecivers.ModBlockLamp;
import com.circuit.CircuitMod.Blocks.EventRecivers.ModBlockMultiDigitDisplay;
import com.circuit.CircuitMod.Blocks.EventRecivers.ModBlockOneDigitDisplay;
import com.circuit.CircuitMod.Blocks.EventRecivers.ModBlockRedstoneEmitter;
import com.circuit.CircuitMod.Blocks.EventRecivers.ModBlockSignalShortender;
import com.circuit.CircuitMod.Blocks.EventRecivers.ModBlockVariable;
import com.circuit.CircuitMod.Blocks.EventSenders.ModBlockAddittionGate;
import com.circuit.CircuitMod.Blocks.EventSenders.ModBlockDigitEquals;
import com.circuit.CircuitMod.Blocks.EventSenders.ModBlockDividationGate;
import com.circuit.CircuitMod.Blocks.EventSenders.ModBlockGreaterGate;
import com.circuit.CircuitMod.Blocks.EventSenders.ModBlockLessGate;
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
import com.circuit.CircuitMod.Items.ItemBlockCircuitBox;
import com.circuit.CircuitMod.Items.ItemBlockCircuitCable;
import com.circuit.CircuitMod.Items.ItemBlockLamp;
import com.circuit.CircuitMod.TileEntity.EventReceivers.TileEntityLamp;
import com.circuit.CircuitMod.TileEntity.EventReceivers.TileEntityMultiDigitDisplay;
import com.circuit.CircuitMod.TileEntity.EventReceivers.TileEntityOneDigitDisplay;
import com.circuit.CircuitMod.TileEntity.EventReceivers.TileEntityRedstoneEmitter;
import com.circuit.CircuitMod.TileEntity.EventReceivers.TileEntitySignalShortender;
import com.circuit.CircuitMod.TileEntity.EventReceivers.TileEntityVariable;
import com.circuit.CircuitMod.TileEntity.EventSenders.TileEntityAdditionGate;
import com.circuit.CircuitMod.TileEntity.EventSenders.TileEntityDigitEquals;
import com.circuit.CircuitMod.TileEntity.EventSenders.TileEntityDividationGate;
import com.circuit.CircuitMod.TileEntity.EventSenders.TileEntityGreaterGate;
import com.circuit.CircuitMod.TileEntity.EventSenders.TileEntityLessGate;
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


    public static Block CircuitBox, CircuitCable, CableConnectionPoint, Lamp;
    public static Block Variable, RandomNumberComponent;
    public static Block SignalShortender;
    public static Block DigitEquals, GreaterGate, LessGate;
    public static Block SignalGate, AdditionGate, SubtractionGate, MultiplicationGate, DividationGate;
    public static Block RedstoneReciver, RedstoneEmitter;
    public static Block OneDigitDisplay, OneDigitConstant, OneDigitCounter;
    public static Block MultiDigitDisplay, MultiDigitConstant, MultiDigitCounter;


    //TODO Add sensors and text based event types (for example player sensors that outputs the player name and maybe range from the sensor? will also need some kind of constant and display)

    public static void RegisterBlocks(){
        BlockRegister Utils = new BlockRegister(CircuitMod.config, Ref.ModId);


        //Main blocks
        CircuitBox = new ModBlockCircuitBox().setCreativeTab(CircuitMod.CreativeTab);
        Utils.Register(CircuitBox, ItemBlockCircuitBox.class, "CircuitBox", TileEntityCircuitBox.class);

        CircuitCable = new ModBlockCircuitCable().setCreativeTab(CircuitMod.CreativeTab);
        Utils.Register(CircuitCable, ItemBlockCircuitCable.class, "CircuitCable",  TileEntityCircuitCable.class);

        CableConnectionPoint = new ModBlockCableConnectionPoint().setCreativeTab(CircuitMod.CreativeTab).setBlockTextureName(Ref.ModId.toLowerCase() + ":CableConnectionPoint");
        Utils.Register(CableConnectionPoint, ItemBlockCableConnectionPoint.class, "CableConnectionPoint", TileEntityCableConnectionPoint.class);

        Lamp = new ModBlockLamp().setCreativeTab(CircuitMod.CreativeTab).setHardness(1F);
        Utils.Register(Lamp, ItemBlockLamp.class, "Lamp", TileEntityLamp.class);



        //Redstone handlers
        RedstoneReciver = new ModBlockRedstoneReciver().setCreativeTab(CircuitMod.CreativeTab).setBlockTextureName(Ref.ModId.toLowerCase() + ":RedstoneEventReciver").setHardness(1F);
        Utils.Register(RedstoneReciver, "RedstoneReciver", TileEntityRedstoneReciver.class);

        RedstoneEmitter = new ModBlockRedstoneEmitter().setCreativeTab(CircuitMod.CreativeTab).setBlockTextureName(Ref.ModId.toLowerCase() + ":RedstoneEmitter").setHardness(1F);
        Utils.Register(RedstoneEmitter, "RedstoneEmitter", TileEntityRedstoneEmitter.class);


        //Random things
        Variable = new ModBlockVariable().setCreativeTab(CircuitMod.CreativeTab).setHardness(1F);
        Utils.Register(Variable, "Variable", TileEntityVariable.class);

        RandomNumberComponent = new ModBlockRandomNumberComponent().setCreativeTab(CircuitMod.CreativeTab).setHardness(1F);
        Utils.Register(RandomNumberComponent, "RandomNumberComponent", TileEntityRandomNumber.class);

        SignalShortender = new ModBlockSignalShortender().setCreativeTab(CircuitMod.CreativeTab).setHardness(1F);
        Utils.Register(SignalShortender, "SignalShortender", TileEntitySignalShortender.class);


        //Gates
        DigitEquals = new ModBlockDigitEquals().setCreativeTab(CircuitMod.CreativeTab).setHardness(1F);
        Utils.Register(DigitEquals, "EqualsGate", TileEntityDigitEquals.class);

        GreaterGate = new ModBlockGreaterGate().setCreativeTab(CircuitMod.CreativeTab).setHardness(1F);
        Utils.Register(GreaterGate, "Greatergate", TileEntityGreaterGate.class);

        LessGate = new ModBlockLessGate().setCreativeTab(CircuitMod.CreativeTab).setHardness(1F);
        Utils.Register(LessGate, "LessGate", TileEntityLessGate.class);


        SignalGate = new ModBlockSignalGate().setCreativeTab(CircuitMod.CreativeTab).setHardness(1F);
        Utils.Register(SignalGate, "SignalGate", TileEntitySignalGate.class);

        AdditionGate = new ModBlockAddittionGate().setCreativeTab(CircuitMod.CreativeTab).setHardness(1F);
        Utils.Register(AdditionGate, "AdditionGate", TileEntityAdditionGate.class);

        SubtractionGate = new ModBlockSubtractionGate().setCreativeTab(CircuitMod.CreativeTab).setHardness(1F);
        Utils.Register(SubtractionGate, "SubtractionGate", TileEntitySubtractionGate.class);

        MultiplicationGate = new ModBlockMultiplicationGate().setCreativeTab(CircuitMod.CreativeTab).setHardness(1F);
        Utils.Register(MultiplicationGate, "MultiplicationGate", TileEntityMultiplicationGate.class);

        DividationGate = new ModBlockDividationGate().setCreativeTab(CircuitMod.CreativeTab).setHardness(1F);
        Utils.Register(DividationGate, "DividationGate", TileEntityDividationGate.class);



        //One digit utils
        OneDigitDisplay = new ModBlockOneDigitDisplay().setCreativeTab(CircuitMod.CreativeTab).setHardness(1F);
        Utils.Register(OneDigitDisplay,  "OneDigitDisplay", TileEntityOneDigitDisplay.class);

        OneDigitConstant = new ModBlockOneDigitConstant().setCreativeTab(CircuitMod.CreativeTab).setHardness(1F);
        Utils.Register(OneDigitConstant, "OneDigitConstant", TileEntityOneDigitConstant.class);

        OneDigitCounter = new ModBlockOneDigitCounter().setCreativeTab(CircuitMod.CreativeTab).setHardness(1F);
        Utils.Register(OneDigitCounter, "OneDigitCounter", TileEntityOneDigitCounter.class);




        //Multi digit utils
        MultiDigitDisplay = new ModBlockMultiDigitDisplay().setCreativeTab(CircuitMod.CreativeTab).setHardness(1F);
        Utils.Register(MultiDigitDisplay, "MultiDigitDisplay", TileEntityMultiDigitDisplay.class);

        MultiDigitConstant = new ModBlockMultiDigitConstant().setCreativeTab(CircuitMod.CreativeTab).setHardness(1F);
        Utils.Register(MultiDigitConstant, "MultiDigitConstant", TileEntityMultiDigitConstant.class);

        MultiDigitCounter = new ModBlockMultiDigitCounter().setCreativeTab(CircuitMod.CreativeTab).setHardness(1F);
        Utils.Register(MultiDigitCounter, "MultiDigitCounter", TileEntityMultiDigitCounter.class);





    }
}
