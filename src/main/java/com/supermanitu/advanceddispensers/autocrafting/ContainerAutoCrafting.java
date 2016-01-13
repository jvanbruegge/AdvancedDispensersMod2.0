package com.supermanitu.advanceddispensers.autocrafting;

import com.supermanitu.advanceddispensers.lib.ContainerAdvancedDispensers;
import com.supermanitu.advanceddispensers.lib.TileEntityAdvancedDispensers;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.world.World;

public class ContainerAutoCrafting extends ContainerAdvancedDispensers
{
	private TileEntityAutoCrafting tileEntity;
	private InventoryAutoCrafting crafting;
	private InventoryAutoCraftingResult result;
	
	public ContainerAutoCrafting(IInventory player, TileEntityAdvancedDispensers tileEntity, World world)
	{
		super(player, tileEntity, world);
		
		this.tileEntity = (TileEntityAutoCrafting) tileEntity;
		this.crafting = new InventoryAutoCrafting(this.tileEntity, this, 3, 3);
		this.result = new InventoryAutoCraftingResult(this.tileEntity);
		
		int counter = 0;
		
		for (int i = 0; i < 3; i++)
		{
			for (int j = 0; j < 3; j++)
			{	
				this.addSlotToContainer(new Slot(crafting, i*3 + j, 30 + j * 18, 17 + i * 18));
				counter++;
			}
		}

		this.addSlotToContainer(new SlotAutoCrafting(crafting, result, counter, 124, 35));
		counter++;

		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 5; j++)
			{
				this.addSlotToContainer(new Slot(tileEntity, counter, 8 + j*18, 89 + i*18));
				counter++;
			}
		}

		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				this.addSlotToContainer(new Slot(tileEntity, counter, 116 + j*18, 89 + i*18));
				counter++;
			}
		}

		for (int i = 0; i < 3; ++i)
		{
			for (int j = 0; j < 9; ++j)
			{
				this.addSlotToContainer(new Slot(player, j + i * 9 + 9, 8 + j * 18, 152 + i * 18));
			}
		}

		for (int i = 0; i < 9; ++i)
		{
			this.addSlotToContainer(new Slot(player, i, 8 + i * 18, 210));
		}

		this.onCraftMatrixChanged(this.crafting);
	}
	
	@Override
	public void onCraftMatrixChanged(IInventory par1IInventory)
	{
		super.onCraftMatrixChanged(par1IInventory);
		
		this.result.setInventorySlotContents(0, CraftingManager.getInstance().findMatchingRecipe(this.crafting, this.world));
	}
	
	@Override
	public boolean canMergeSlot(ItemStack itemStack, Slot slot)
	{
		return slot.inventory != this.result && super.canMergeSlot(itemStack, slot);
	}

	@Override
	protected boolean customTransferStackBehavior(ItemStack current, int fromSlot)
	{
		if (fromSlot < 34)
		{
			if (!this.mergeItemStack(current, 10, 70, true))
			{
				return false;
			}
		}
		else if (!this.mergeItemStack(current, 10, 25, false))
		{
			return false;
		}
		return true;
	}
}
