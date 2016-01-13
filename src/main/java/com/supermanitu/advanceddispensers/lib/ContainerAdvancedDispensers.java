package com.supermanitu.advanceddispensers.lib;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public abstract class ContainerAdvancedDispensers extends Container
{
	protected TileEntityAdvancedDispensers tileEntity;
	protected World world;
	
	public ContainerAdvancedDispensers(IInventory player, TileEntityAdvancedDispensers tileEntity, World world)
	{
		this.tileEntity = tileEntity;
		this.world = world;
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer player)
	{
		return tileEntity.isUseableByPlayer(player);
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int fromSlot) 
	{
	    ItemStack previous = null;
	    Slot slot = (Slot) this.inventorySlots.get(fromSlot);

	    if (slot != null && slot.getHasStack()) {
	        ItemStack current = slot.getStack();
	        previous = current.copy();

	        if(!customTransferStackBehavior(current, fromSlot)) return null;

	        if (current.stackSize == 0)
	            slot.putStack((ItemStack) null);
	        else
	            slot.onSlotChanged();

	        if (current.stackSize == previous.stackSize)
	            return null;
	        slot.onPickupFromSlot(playerIn, current);
	    }
	    return previous;
	}
	
	protected abstract boolean customTransferStackBehavior(ItemStack current, int fromSlot);
}
