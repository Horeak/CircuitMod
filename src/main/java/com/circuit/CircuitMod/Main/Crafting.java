package com.circuit.CircuitMod.Main;

import MiscUtils.Utils.CraftingUtils;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;

public class Crafting {
    static CraftingUtils Utils = new CraftingUtils(CircuitMod.config);

    public static void RegisterCrafting(){


        Utils.AddRecipe(new ItemStack(ModBlocks.CircuitCable, 16), new Object[]{"III", "RRR", "III", 'R', Items.redstone, 'I', Items.iron_ingot});

        for(int i = 1; i < ItemDye.field_150922_c.length; i++)
            Utils.AddRecipe(new ItemStack(ModBlocks.CircuitCable, 8, i), new Object[]{"CCC", "CIC", "CCC", 'C', ModBlocks.CircuitCable, 'I', new ItemStack(Items.dye, 1, 15 - i)});

        for(int i = 0; i < ItemDye.field_150922_c.length; i++)
            Utils.AddRecipe(new ItemStack(ModBlocks.Lamp, 16, i), new Object[]{"IGI", "GLG", "IGI", 'I', Items.iron_ingot, 'L', Blocks.redstone_lamp, 'G', new ItemStack(Blocks.stained_glass, 1, i)});


        Utils.AddRecipe(new ItemStack(ModBlocks.CircuitBox), new Object[]{" I ", "IRI", "CCC", 'I', Items.iron_ingot, 'R', Items.redstone, 'C', ModBlocks.CircuitCable});
        Utils.AddRecipe(new ItemStack(ModBlocks.CableConnectionPoint, 8), new Object[]{"ICI", "CCC", "ICI", 'I', Items.iron_ingot, 'C', ModBlocks.CircuitCable});
        Utils.AddRecipe(new ItemStack(ModBlocks.RedstoneReciver), new Object[]{"IRI", "RLR", "IRI", 'I', Items.iron_ingot, 'R', Items.redstone, 'L', Blocks.lever});
        Utils.AddRecipe(new ItemStack(ModBlocks.RedstoneEmitter), new Object[]{"IRI", "RLR", "IRI", 'I', Items.iron_ingot, 'R', Items.redstone, 'L', Blocks.redstone_block});

        Utils.AddRecipe(new ItemStack(ModBlocks.Variable), new Object[]{"RCR", "CBC", "RCR", 'R', Items.redstone, 'C', ModBlocks.CircuitCable, 'B', Blocks.redstone_block});
        Utils.AddRecipe(new ItemStack(ModBlocks.RandomNumberComponent), new Object[]{"III", "RVR", "ICI", 'I', Items.iron_ingot, 'R', Items.redstone, 'V', ModBlocks.Variable, 'C', ModBlocks.CircuitCable});

        Utils.AddRecipe(new ItemStack(ModBlocks.SignalShortender), new Object[]{"III", "CRI", "III", 'I', Items.iron_ingot, 'R', Items.redstone, 'C', ModBlocks.CircuitCable});

        Utils.AddRecipe(new ItemStack(ModBlocks.DigitEquals), new Object[]{"III", "CRC", "III", 'I', Items.iron_ingot, 'R', Items.redstone, 'C', ModBlocks.CircuitCable});
        Utils.AddRecipe(new ItemStack(ModBlocks.GreaterGate), new Object[]{"III", "RCC", "III", 'I', Items.iron_ingot, 'R', Items.redstone, 'C', ModBlocks.CircuitCable});
        Utils.AddRecipe(new ItemStack(ModBlocks.LessGate), new Object[]{"III", "CCR", "III", 'I', Items.iron_ingot, 'R', Items.redstone, 'C', ModBlocks.CircuitCable});
        Utils.AddRecipe(new ItemStack(ModBlocks.SignalGate), new Object[]{"ICI", "RCR", "ICI", 'I', Items.iron_ingot, 'R', Items.redstone, 'C', ModBlocks.CircuitCable});

        Utils.AddRecipe(new ItemStack(ModBlocks.AdditionGate), new Object[]{"ICI", "CBC", "ICI", 'I', Items.iron_ingot, 'C', ModBlocks.CircuitCable, 'B', ModBlocks.CircuitBox});
        Utils.AddShapelessRecipe(new ItemStack(ModBlocks.SubtractionGate), ModBlocks.AdditionGate);
        Utils.AddShapelessRecipe(new ItemStack(ModBlocks.MultiplicationGate), ModBlocks.SubtractionGate);
        Utils.AddShapelessRecipe(new ItemStack(ModBlocks.DividationGate), ModBlocks.MultiplicationGate);
        Utils.AddShapelessRecipe(new ItemStack(ModBlocks.AdditionGate), ModBlocks.DividationGate);

        Utils.AddRecipe(new ItemStack(ModBlocks.OneDigitDisplay), new Object[]{"ILI", "RVR", "ICI", 'I', Items.iron_ingot, 'L', ModBlocks.Lamp, 'R', Items.redstone, 'C', ModBlocks.CircuitCable, 'V', ModBlocks.Variable});
        Utils.AddRecipe(new ItemStack(ModBlocks.OneDigitConstant), new Object[]{"ILI", "RVR", "ICI", 'I', Items.iron_ingot, 'L', ModBlocks.CableConnectionPoint, 'R', Items.redstone, 'C', ModBlocks.CircuitCable, 'V', ModBlocks.Variable});
        Utils.AddRecipe(new ItemStack(ModBlocks.OneDigitCounter), new Object[]{"ILI", "RVR", "ICI", 'I', Items.iron_ingot, 'L', ModBlocks.CableConnectionPoint, 'R', ModBlocks.CircuitBox, 'C', ModBlocks.CircuitCable, 'V', ModBlocks.Variable});

        Utils.AddRecipe(new ItemStack(ModBlocks.MultiDigitDisplay), new Object[]{"ILI", "RVR", "ICI", 'I', Items.iron_ingot, 'L', ModBlocks.Lamp, 'R', Items.redstone, 'C', ModBlocks.CircuitCable, 'V', ModBlocks.OneDigitDisplay});
        Utils.AddRecipe(new ItemStack(ModBlocks.MultiDigitConstant), new Object[]{"ILI", "RVR", "ICI", 'I', Items.iron_ingot, 'L', ModBlocks.CableConnectionPoint, 'R', Items.redstone, 'C', ModBlocks.CircuitCable, 'V', ModBlocks.OneDigitConstant});
        Utils.AddRecipe(new ItemStack(ModBlocks.MultiDigitCounter), new Object[]{"ILI", "RVR", "ICI", 'I', Items.iron_ingot, 'L', ModBlocks.CableConnectionPoint, 'R', ModBlocks.CircuitBox, 'C', ModBlocks.CircuitCable, 'V', ModBlocks.OneDigitCounter});

        Utils.AddRecipe(new ItemStack(ModBlocks.DataTransmitter), new Object[]{"IDI", "BBB", "ICI", 'I', Items.iron_ingot, 'D', Blocks.dispenser, 'B', ModBlocks.CircuitBox, 'C', ModBlocks.CircuitCable});
        Utils.AddRecipe(new ItemStack(ModBlocks.DataReceiver), new Object[]{"IDI", "BBB", "ICI", 'I', Items.iron_ingot, 'D', ModBlocks.CableConnectionPoint, 'B', ModBlocks.CircuitBox, 'C', ModBlocks.CircuitCable});
        Utils.AddRecipe(new ItemStack(ModBlocks.DataConstructor), new Object[]{"RIR", "BCB", "IBI", 'I', Items.iron_ingot, 'R', Blocks.redstone_block, 'B', ModBlocks.CircuitBox, 'C', ModBlocks.CircuitCable});
        Utils.AddRecipe(new ItemStack(ModBlocks.DataChatOutput), new Object[]{"CBC", "BNB", "CBC", 'B', ModBlocks.CircuitBox, 'C', ModBlocks.CircuitCable, 'N', Blocks.noteblock});

        Utils.AddRecipe(new ItemStack(ModBlocks.DataSelector), new Object[]{"III", "BPB", "III", 'B', ModBlocks.CircuitBox, 'P', ModBlocks.CableConnectionPoint, 'I', Items.iron_ingot});
        Utils.AddRecipe(new ItemStack(ModBlocks.DataConverter), new Object[]{"III", "BBB", "III", 'B', ModBlocks.CircuitBox, 'I', Items.iron_ingot});

        Utils.AddRecipe(new ItemStack(ModBlocks.DataEncryptor), new Object[]{"III", "EBE", "III", 'I', Items.iron_ingot, 'E', ModBlocks.DataConverter, 'B', ModBlocks.CircuitBox});
        Utils.AddRecipe(new ItemStack(ModBlocks.DataDecryptor), new Object[]{"III", "EBE", "III", 'I', Items.iron_ingot, 'E', ModBlocks.DataSelector, 'B', ModBlocks.CircuitBox});

        Utils.AddRecipe(new ItemStack(ModBlocks.DataScreen), new Object[]{"III", "ILI", "ICI", 'I', Items.iron_ingot, 'L', ModBlocks.Lamp, 'C', ModBlocks.CircuitCable});

        Utils.AddRecipe(new ItemStack(ModBlocks.EntityDetector), new Object[]{"ITI", "BCB", "IOI", 'I', Items.iron_ingot, 'T', ModBlocks.DataTransmitter, 'B', ModBlocks.CircuitBox, 'C', ModBlocks.DataConstructor, 'O', ModBlocks.DataChatOutput});




    }

}
