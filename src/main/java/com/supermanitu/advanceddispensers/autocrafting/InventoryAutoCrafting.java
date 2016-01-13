package com.supermanitu.advanceddispensers.autocrafting;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;

public class InventoryAutoCrafting extends InventoryCrafting
{
	private TileEntityAutoCrafting tileEntity;
	private ContainerAutoCrafting container;
	
	public InventoryAutoCrafting(TileEntityAutoCrafting tileEntity, ContainerAutoCrafting container, int width, int height) 
	{
		super(container, width, height);
		
		this.tileEntity = tileEntity;
		this.container = container;
	}
	
	@Override
    public int getSizeInventory()
    {
        return 9;
    }

    @Override
    public ItemStack getStackInSlot(int index)
    {
        return index >= this.getSizeInventory() ? null : tileEntity.getStackInSlot(index);
    }

    @Override
    public ItemStack getStackInRowAndColumn(int row, int column)
    {
        if (row >= 0 && row < 3)
        {
            int k = row + column * 3;
            return this.getStackInSlot(k);
        }
        else
        {
            return null;
        }
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int index)
    {
        if (tileEntity.getStackInSlot(index) != null)
        {
            ItemStack itemstack = tileEntity.getStackInSlot(index);
            tileEntity.setInventorySlotContents(index, null);
            return itemstack;
        }
        else
        {
            return null;
        }
    }

    @Override
    public ItemStack decrStackSize(int index, int amount)
    {
    	if (tileEntity.getStackInSlot(index) != null)
        {
            ItemStack itemstack;

            if (tileEntity.getStackInSlot(index).stackSize <= amount)
            {
                itemstack = tileEntity.getStackInSlot(index);
                tileEntity.setInventorySlotContents(index, null);
                this.container.onCraftMatrixChanged(this);
                return itemstack;
            }
            else
            {
                itemstack = tileEntity.getStackInSlot(index).splitStack(amount);

                if (tileEntity.getStackInSlot(index).stackSize == 0)
                {
                	tileEntity.setInventorySlotContents(index, null);
                }

                this.container.onCraftMatrixChanged(this);
                return itemstack;
            }
        }
        else
        {
            return null;
        }
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack itemStack)
    {
    	tileEntity.setInventorySlotContents(index, itemStack);

        this.container.onCraftMatrixChanged(this);
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
        return tileEntity.isItemValidForSlot(index, itemStack);
    }
}
