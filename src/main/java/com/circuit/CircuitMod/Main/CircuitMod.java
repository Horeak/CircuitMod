package com.circuit.CircuitMod.Main;

import MiscUtils.Network.ChannelUtils;
import com.circuit.CircuitMod.Proxy.ServerProxy;
import com.circuit.CircuitMod.Utils.CircuitBoxModeUtils;
import com.circuit.CircuitMod.Utils.Config;
import com.circuit.CircuitMod.Utils.Ref;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

@Mod(modid = Ref.ModId, name = Ref.ModName, version = Ref.Version, dependencies = "required-after:MiscUtils")
public class CircuitMod {

    @Mod.Instance(Ref.ModId)
    public static CircuitMod instance = new CircuitMod();

    @SidedProxy(clientSide = "com.circuit.CircuitMod.Proxy.ClientProxy", serverSide = "com.circuit.CircuitMod.Proxy.ServerProxy")
    public static ServerProxy proxy;

    public static ChannelUtils Utils;
    public static Config config;

    public static CreativeTabs CreativeTab = new CreativeTabs("tabCircuit") {
        @Override
        @SideOnly(Side.CLIENT)
        public Item getTabIconItem() {
            return ItemBlock.getItemFromBlock(config.GetCheckedBlock(ModBlocks.CircuitBox));
        }

    };

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {

        Utils = new ChannelUtils(Ref.ModChannel, Ref.ModId);
        RegisterPackets();


        config = new Config(event.getModConfigurationDirectory() + "");



        ModBlocks.RegisterBlocks();
        ModItems.RegisterItems();

        proxy.RegisterRenderThings();

        CircuitBoxModeUtils.RegisterModes();

    }

    @Mod.EventHandler
    public void Init(FMLInitializationEvent event){


    }



    @Mod.EventHandler
    public void PostInit(FMLPostInitializationEvent event)
    {


    }


    public static void RegisterPackets(){


    }

    }
