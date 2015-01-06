package com.circuit.CircuitMod.Main.GuideIntegration;

import MiscUtils.GuideBase.Utils.GuideInstance;
import MiscUtils.GuideBase.Utils.GuideTab;
import MiscUtils.GuideBase.Utils.ModGuideText;
import com.circuit.CircuitMod.Main.ModBlocks;
import com.circuit.CircuitMod.Utils.Ref;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class CircuitModGuideInstance extends GuideInstance {

    @Override
    public ResourceLocation BlockDescriptions() {
        return new ResourceLocation(Ref.ModId.toLowerCase(), "guide/circuit_blockinfo.txt");
    }

    @Override
    public ResourceLocation ItemDescriptions() {
        return new ResourceLocation(Ref.ModId.toLowerCase(), "guide/circuit_iteminfo.txt");
    }

    @Override
    public String ModPageName() {
        return Ref.ModName + " Mod Guide";
    }

    @Override
    public ItemStack ModPageDisplay() {
        return new ItemStack(ModBlocks.MultiDigitDisplay);
    }

    @Override
    public String ModDescription() {
        return "The " + Ref.ModName + " mod is a mod aiming to add more advanced signal and data transfer system in minecraft and allowing players to more easily make more complex contraptions in the game.";
    }

    ModGuideText MainTab;
    GuideTab BlocksTab;

    @Override
    public void RegisterInfo() {
        MainTab = new ModGuideText(this, Items.paper, "guide.circuit.tab.main");
        BlocksTab = new GuideTab(this, ModBlocks.CircuitBox, "guide.circuit.tab.blocks");

        for(Object r : Block.blockRegistry) {
            Block bl = (Block) r;
            if (bl != null) {

                GameRegistry.UniqueIdentifier id = GameRegistry.findUniqueIdentifierFor(bl);
                if (id != null & id.modId.equalsIgnoreCase(Ref.ModId))
                    BlocksTab.Register(bl);

            }

        }



        RegisterTab(MainTab);
        RegisterTab(BlocksTab);
    }
}
