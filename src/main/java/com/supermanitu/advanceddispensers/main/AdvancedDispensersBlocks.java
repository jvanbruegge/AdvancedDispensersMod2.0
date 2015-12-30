package com.supermanitu.advanceddispensers.main;

import com.supermanitu.advanceddispensers.autocrafting.BlockAutoCrafting;
import com.supermanitu.advanceddispensers.breaker.BlockBreaker;
import com.supermanitu.advanceddispensers.lib.BlockAdvancedDispensers;
import com.supermanitu.advanceddispensers.lib.ConfigAdvancedDispensers;
import com.supermanitu.advanceddispensers.lib.IHasSubtypes;
import com.supermanitu.advanceddispensers.user.BlockUser;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.registry.GameRegistry;

public final class AdvancedDispensersBlocks 
{	
	private ConfigAdvancedDispensers[] configs;
	private BlockAdvancedDispensers[] blocks;
	private Configuration config;
	
	private static AdvancedDispensersBlocks instance;
	
	public AdvancedDispensersBlocks(Configuration config)
	{
		this.config = config;
		instance = this;
		
		configs = new ConfigAdvancedDispensers[] {
				new ConfigAdvancedDispensers(config, "User"),
				new ConfigAdvancedDispensers(config, "Breaker"),
				new ConfigAdvancedDispensers(config, "AutomatedCraftingTable")
		};
		
		blocks = new BlockAdvancedDispensers[] { 
				new BlockUser(configs[0].getTick()),
				new BlockBreaker(configs[1].getTick()),
				new BlockAutoCrafting(configs[2].getTick())
		};
		
		this.config.save();
	}
	
	public ConfigAdvancedDispensers getConfig(int guiID)
	{
		return configs[guiID];
	}
	
	public void createBlocks()
	{
		for(int i = 0; i < blocks.length; i++)
		{
			if(configs[i].isEnabled())
			{
				if(blocks[i] instanceof IHasSubtypes)
				{
					GameRegistry.registerBlock(blocks[i], ((IHasSubtypes)blocks[i]).getItemBlock(), blocks[i].getName());
				}
				else
				{
					GameRegistry.registerBlock(blocks[i], blocks[i].getName());
				}
			}
		}
	}
	
	public void addRecipes()
	{
		for(int i = 0; i < blocks.length; i++)
		{
			if(configs[i].isEnabled())
			{
				if(blocks[i] instanceof IHasSubtypes)
				{
					((IHasSubtypes)blocks[i]).addRecipes();
				}
				else
				{
					GameRegistry.addRecipe(new ItemStack(blocks[i], 1), blocks[i].getRecipe());
				}
			}
		}
	}
	
	public void preInitClientBlocks()
	{
		for(int i = 0; i < blocks.length; i++)
		{
			if(configs[i].isEnabled())
			{
				if(blocks[i] instanceof IHasSubtypes)
				{
					Item item = GameRegistry.findItem("advanceddispensers", blocks[i].getName());
					((IHasSubtypes)blocks[i]).addVariantName(item);
				}
			}
		}
	}
	
	public void createClientBlocks()
	{
		for(int i = 0; i < blocks.length; i++)
		{
			if(configs[i].isEnabled())
			{
				Item itemBlock = GameRegistry.findItem("advanceddispensers", blocks[i].getName());
				if(blocks[i] instanceof IHasSubtypes)
				{
					for(int j = 0; j < ((IHasSubtypes)blocks[i]).countSubtypes(); j++)
					{
						ModelResourceLocation itemModelResourceLocation = new ModelResourceLocation(((IHasSubtypes)blocks[i]).getVariantName(j), "inventory");
					    Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(itemBlock, j, itemModelResourceLocation);
					}
				}
				else
				{
					ModelResourceLocation itemModelResourceLocation = new ModelResourceLocation("advanceddispensers:" + blocks[i].getName(), "inventory");
				    final int DEFAULT_ITEM_SUBTYPE = 0;
				    Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(itemBlock, DEFAULT_ITEM_SUBTYPE, itemModelResourceLocation);
				}
			}
		}
	}
	
	public Block getTabIcon()
	{
		for(int i = 0; i < blocks.length; i++)
		{
			if(configs[i].isEnabled()) return blocks[i];
		}
		return Blocks.dispenser;
	}
	
	public static AdvancedDispensersBlocks instance()
	{
		return instance;
	}
}
