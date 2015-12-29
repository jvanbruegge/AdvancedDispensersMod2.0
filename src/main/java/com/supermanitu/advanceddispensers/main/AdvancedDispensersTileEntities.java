package com.supermanitu.advanceddispensers.main;

import com.supermanitu.advanceddispensers.breaker.TileEntityBreaker;

import net.minecraftforge.fml.common.registry.GameRegistry;

public final class AdvancedDispensersTileEntities 
{
	public static void registerTileEntities()
	{
		GameRegistry.registerTileEntity(TileEntityBreaker.class, "tileEntityBreaker");
	}
}
