package com.supermanitu.advanceddispensers.proxies;

import com.supermanitu.advanceddispensers.main.AdvancedDispensersBlocks;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy 
{
    public void preInit(FMLPreInitializationEvent e) 
    {
    	AdvancedDispensersBlocks.createBlocks();
    }

    public void init(FMLInitializationEvent e) 
    {
    	AdvancedDispensersBlocks.addRecipes();
    }

    public void postInit(FMLPostInitializationEvent e)
    {

    }
}