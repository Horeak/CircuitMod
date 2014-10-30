package com.circuit.CircuitMod.Blocks.EventSenders;

import com.circuit.CircuitMod.Blocks.Utils.ModBlockTwoSidedEventChecker;
import com.circuit.CircuitMod.TileEntity.EventSenders.TileEntityDigitEquals;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ModBlockDigitEquals extends ModBlockTwoSidedEventChecker {
    public ModBlockDigitEquals() {
        super(Material.iron);
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEntityDigitEquals();
    }

    @Override
    public String FrontIcon() {
        return "DigitEqualsFront";
    }

    @Override
    public String BackIcon() {
        return "DigitEqualsBack";
    }

    @Override
    public String SideAIcon() {
        return "DigitEqualsSide";
    }

    @Override
    public String SideBIcon() {
        return "DigitEqualsSide";
    }
}
