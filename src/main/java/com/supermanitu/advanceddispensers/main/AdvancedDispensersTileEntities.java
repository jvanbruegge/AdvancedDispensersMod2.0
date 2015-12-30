package com.supermanitu.advanceddispensers.main;

import com.supermanitu.advanceddispensers.autocrafting.TileEntityAutoCrafting;
import com.supermanitu.advanceddispensers.breaker.TileEntityBreaker;
import com.supermanitu.advanceddispensers.user.TileEntityUser;

import net.minecraftforge.fml.common.registry.GameRegistry;

public final class AdvancedDispensersTileEntities 
{
	public static void registerTileEntities()
	{
		GameRegistry.registerTileEntity(TileEntityBreaker.class, "tileEntityBreaker");
		GameRegistry.registerTileEntity(TileEntityUser.class, "tileEntityUser");
		GameRegistry.registerTileEntity(TileEntityAutoCrafting.class, "tileEntityAutoCrafting");
	}
}
