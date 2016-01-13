package com.supermanitu.advanceddispensers.autocrafting;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.item.ItemStack;

public class InventoryAutoCraftingResult extends InventoryCraftResult
{
	private TileEntityAutoCrafting tileEntity;
	
	public InventoryAutoCraftingResult(TileEntityAutoCrafting tileEntity)
    {
    	this.tileEntity = tileEntity;
    }
	
	@Override
	public int getSizeInventory()
    {
        return 1;
    }

	@Override
    public ItemStack getStackInSlot(int index)
    {
        return tileEntity.getStackInSlot(9);
    }

	@Override
    public ItemStack decrStackSize(int index, int count)
    {
		if (tileEntity.getStackInSlot(9) != null)
        {
            ItemStack itemstack = tileEntity.getStackInSlot(9);
            tileEntity.setInventorySlotContents(9, null);
            return itemstack;
        }
        else
        {
            return null;
        }
    }

	@Override
    public ItemStack getStackInSlotOnClosing(int par1)
    {
		if (tileEntity.getStackInSlot(9) != null)
        {
            ItemStack itemstack = tileEntity.getStackInSlot(9);
            tileEntity.setInventorySlotContents(9, null);
            return itemstack;
        }
        else
        {
            return null;
        }
    }

	@Override
    public void setInventorySlotContents(int index, ItemStack itemStack)
    {
    	tileEntity.setInventorySlotContents(9, itemStack);
    }

	@Override
    public int getInventoryStackLimit()
    {
        return 64;
    }

	@Override
    public void markDirty()
	{
		tileEntity.markDirty();
	}

    @Override
    public boolean isUseableByPlayer(EntityPlayer player)
    {
        return tileEntity.isUseableByPlayer(player);
    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack itemStack)
    {
        return tileEntity.isItemValidForSlot(9, itemStack);
    }
}
