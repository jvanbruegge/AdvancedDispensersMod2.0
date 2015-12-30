package com.supermanitu.advanceddispensers.main;

import com.supermanitu.advanceddispensers.breaker.BlockBreaker;
import com.supermanitu.advanceddispensers.lib.BlockAdvancedDispensers;
import com.supermanitu.advanceddispensers.lib.IHasSubtypes;
import com.supermanitu.advanceddispensers.user.BlockUser;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public final class AdvancedDispensersBlocks 
{
	public static final BlockAdvancedDispensers[] blocks = { new BlockBreaker(), new BlockUser() };
	
	public static void createBlocks()
	{
		for(BlockAdvancedDispensers block : blocks)
		{
			if(block instanceof IHasSubtypes)
			{
				GameRegistry.registerBlock(block, ((IHasSubtypes)block).getItemBlock(), block.getName());
			}
			else
			{
				GameRegistry.registerBlock(block, block.getName());
			}
		}
	}
	
	public static void addRecipes()
	{
		for(BlockAdvancedDispensers block : blocks)
		{
			if(block instanceof IHasSubtypes)
			{
				((IHasSubtypes)block).addRecipes();
			}
			else
			{
				GameRegistry.addRecipe(new ItemStack(block, 1), block.getRecipe());
			}
		}
	}
	
	public static void preInitClientBlocks()
	{
		for(BlockAdvancedDispensers block : blocks)
		{
			if(block instanceof IHasSubtypes)
			{
				Item item = GameRegistry.findItem("advanceddispensers", block.getName());
				((IHasSubtypes)block).addVariantName(item);
			}
		}
	}
	
	public static void createClientBlocks()
	{
		for(BlockAdvancedDispensers block : blocks)
		{
			Item itemBlock = GameRegistry.findItem("advanceddispensers", block.getName());
			if(block instanceof IHasSubtypes)
			{
				for(int i = 0; i < ((IHasSubtypes)block).countSubtypes(); i++)
				{
					ModelResourceLocation itemModelResourceLocation = new ModelResourceLocation(((IHasSubtypes)block).getVariantName(i), "inventory");
				    Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(itemBlock, i, itemModelResourceLocation);
				}
			}
			else
			{
				ModelResourceLocation itemModelResourceLocation = new ModelResourceLocation("advanceddispensers:" + block.getName(), "normal");
			    final int DEFAULT_ITEM_SUBTYPE = 0;
			    Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(itemBlock, DEFAULT_ITEM_SUBTYPE, itemModelResourceLocation);
			}
		}
	}
	
	public static Block getTabIcon()
	{
		return blocks[0];
	}
}
