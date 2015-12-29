package com.supermanitu.advanceddispensers.proxies;

import com.supermanitu.advanceddispensers.main.AdvancedDispensersBlocks;
import com.supermanitu.advanceddispensers.main.AdvancedDispensersGuiHandler;
import com.supermanitu.advanceddispensers.main.AdvancedDispensersMod;
import com.supermanitu.advanceddispensers.main.AdvancedDispensersTileEntities;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class CommonProxy 
{
    public void preInit(FMLPreInitializationEvent e) 
    {
    	AdvancedDispensersBlocks.createBlocks();
    	AdvancedDispensersTileEntities.registerTileEntities();
    }

    public void init(FMLInitializationEvent e) 
    {
    	AdvancedDispensersBlocks.addRecipes();
    	NetworkRegistry.INSTANCE.registerGuiHandler(AdvancedDispensersMod.instance, new AdvancedDispensersGuiHandler());
    }

    public void postInit(FMLPostInitializationEvent e)
    {

    }
}