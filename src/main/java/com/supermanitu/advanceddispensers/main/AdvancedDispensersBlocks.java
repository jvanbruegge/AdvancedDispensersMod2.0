package com.supermanitu.advanceddispensers.main;

import com.supermanitu.advanceddispensers.breaker.BlockBreaker;
import com.supermanitu.advanceddispensers.lib.BlockAdvancedDispensers;
import com.supermanitu.advanceddispensers.lib.IHasSubtypes;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public final class AdvancedDispensersBlocks 
{
	public static final BlockAdvancedDispensers[] blocks = { new BlockBreaker() };
	
	public static void createBlocks()
	{
		for(BlockAdvancedDispensers block : blocks)
		{
			if(block.countSubtypes() > 1)
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
			if(block.countSubtypes() > 1)
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
			if(block.countSubtypes() > 1)
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
			if(block.countSubtypes() > 1)
			{
				for(int i = 0; i < block.countSubtypes(); i++)
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
