package com.circuit.CircuitMod.Main;

import MiscUtils.GuideBase.Registry.GuideModRegistry;
import MiscUtils.Network.ChannelUtils;
import MiscUtils.Utils.LocalizationUpdater;
import com.circuit.CircuitMod.Gui.GuiHandler;
import com.circuit.CircuitMod.Main.GuideIntegration.CircuitModGuideInstance;
import com.circuit.CircuitMod.Packets.DataChannelChanged;
import com.circuit.CircuitMod.Packets.DataConstructPacket;
import com.circuit.CircuitMod.Packets.DataConverterPacket;
import com.circuit.CircuitMod.Packets.DataDecryptionPacket;
import com.circuit.CircuitMod.Packets.DataEncryptionPacket;
import com.circuit.CircuitMod.Packets.DataSelectorPacket;
import com.circuit.CircuitMod.Packets.MultiDigitConstantValueChanged;
import com.circuit.CircuitMod.Packets.MultiDigitCounterValueChanged;
import com.circuit.CircuitMod.Packets.OneDigitConstantValueChanged;
import com.circuit.CircuitMod.Packets.OneDigitCounterValueChanged;
import com.circuit.CircuitMod.Packets.RandomNumberComponentValueChanged;
import com.circuit.CircuitMod.Proxy.ServerProxy;
import com.circuit.CircuitMod.Utils.CircuitBoxModeUtils;
import com.circuit.CircuitMod.Utils.Config;
import com.circuit.CircuitMod.Utils.Ref;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
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

    public static boolean ShowHoverText = true;

    public static CreativeTabs CreativeTab = new CreativeTabs("tabCircuit") {
        @Override
        @SideOnly(Side.CLIENT)
        public Item getTabIconItem() {
            return ItemBlock.getItemFromBlock(config.GetCheckedBlock(ModBlocks.CircuitBox));
        }

    };

    public static LocalizationUpdater localizationUpdater;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {

        Utils = new ChannelUtils(Ref.ModChannel, Ref.ModId);
        RegisterPackets();

        config = new Config(event.getModConfigurationDirectory() + "");

        localizationUpdater = new LocalizationUpdater("tm1990", "CircuitMod", "master", "src/main/resources/assets/circuit/lang/");
        localizationUpdater.initializeThread(config.GetConfigFile());

        proxy.PreInt();


        ModBlocks.RegisterBlocks();
        ModItems.RegisterItems();

        Crafting.RegisterCrafting();

        proxy.RegisterRenderThings();


        CircuitBoxModeUtils.RegisterModes();

        NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());

        GuideModRegistry.RegisterModToGuide(new CircuitModGuideInstance());

    }

    @Mod.EventHandler
    public void Init(FMLInitializationEvent event){


    }



    @Mod.EventHandler
    public void PostInit(FMLPostInitializationEvent event)
    {



    }


    public static void RegisterPackets(){

        Utils.handler.RegisterPacket(MultiDigitConstantValueChanged.class);
        Utils.handler.RegisterPacket(MultiDigitCounterValueChanged.class);
        Utils.handler.RegisterPacket(RandomNumberComponentValueChanged.class);
        Utils.handler.RegisterPacket(OneDigitConstantValueChanged.class);
        Utils.handler.RegisterPacket(OneDigitCounterValueChanged.class);
        Utils.handler.RegisterPacket(DataChannelChanged.class);
        Utils.handler.RegisterPacket(DataConstructPacket.class);
        Utils.handler.RegisterPacket(DataSelectorPacket.class);
        Utils.handler.RegisterPacket(DataConverterPacket.class);
        Utils.handler.RegisterPacket(DataEncryptionPacket.class);
        Utils.handler.RegisterPacket(DataDecryptionPacket.class);

    }


    }
