package com.supermanitu.advanceddispensers.lib;

import java.lang.reflect.InvocationTargetException;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.world.World;

public abstract class GuiAdvancedDispensers extends GuiContainer
{
	private InventoryPlayer player;
	private TileEntityAdvancedDispensers tileEntity;
	
	public GuiAdvancedDispensers(InventoryPlayer player, TileEntityAdvancedDispensers tileEntity, Class<? extends ContainerAdvancedDispensers> container, World world) throws Exception
	{
		super(container.getDeclaredConstructor(IInventory.class, TileEntityAdvancedDispensers.class, World.class).newInstance(player, tileEntity, world));
		
		this.tileEntity = tileEntity;
		this.player = player;
	}
	
	public GuiAdvancedDispensers(InventoryPlayer player, TileEntityAdvancedDispensers tileEntity, Class<? extends ContainerAdvancedDispensers> container, World world, int width, int height) throws Exception
	{
		this(player, tileEntity, container, world);
		
		this.xSize = width;
		this.ySize = height;
	}
	
	protected InventoryPlayer getPlayer()
	{
		return player;
	}
	
	protected TileEntityAdvancedDispensers getTileEntity()
	{
		return tileEntity;
	}
	
	protected IInventory getBlockInventory()
	{
		return (IInventory) tileEntity;
	}
}
