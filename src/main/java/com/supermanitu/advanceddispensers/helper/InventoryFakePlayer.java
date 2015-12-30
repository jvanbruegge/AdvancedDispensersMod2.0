package com.supermanitu.advanceddispensers.helper;

import com.supermanitu.advanceddispensers.lib.TileEntityAdvancedDispensers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;

public class InventoryFakePlayer extends InventoryPlayer
{
	private TileEntityAdvancedDispensers tileEntity;
	
	public InventoryFakePlayer(EntityPlayer player, TileEntityAdvancedDispensers tileEntity) 
	{
		super(player);
		
		this.tileEntity = tileEntity;
		this.mainInventory = tileEntity.getInventory();
		this.armorInventory = null;
	}
	
	@Override
	public int getSizeInventory()
	{
		return tileEntity.getSizeInventory();
	}
	
	@Override
	public ItemStack getStackInSlot(int i)
	{
		return tileEntity.getStackInSlot(i);
	}
	
	@Override
	public int getInventoryStackLimit()
	{
		return tileEntity.getInventoryStackLimit();
	}
	
	@Override
	public ItemStack armorItemInSlot(int i)
    {
        return null;
    }
	
	@Override
	public boolean isUseableByPlayer(EntityPlayer player)
	{
		return tileEntity.isUseableByPlayer(player);
	}
}
