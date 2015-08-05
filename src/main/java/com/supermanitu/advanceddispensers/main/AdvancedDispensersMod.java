package com.supermanitu.advanceddispensers.main;

import com.supermanitu.advanceddispensers.proxies.CommonProxy;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = AdvancedDispensersMod.MODID, name = AdvancedDispensersMod.MODNAME, version = AdvancedDispensersMod.VERSION)
public class AdvancedDispensersMod 
{
	public static final String MODID = "advanceddispensers", MODNAME = "Advanced Dispensers Mod", VERSION = "2.0.0";
	
	@Instance
	public static AdvancedDispensersMod instance;
	
	@SidedProxy(clientSide="com.supermanitu.advanceddispensers.proxies.ClientProxy", serverSide="com.supermanitu.advanceddispensers.proxies.ServerProxy")
	public static CommonProxy proxy;
	
	
	public static AdvancedDispensersTab advancedDispensersTab = new AdvancedDispensersTab();
	
	
	@EventHandler
    public void preInit(FMLPreInitializationEvent e) 
	{
		proxy.preInit(e);
    }

    @EventHandler
    public void init(FMLInitializationEvent e)
    {
    	proxy.init(e);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent e)
    {
    	proxy.postInit(e);
    }
}
