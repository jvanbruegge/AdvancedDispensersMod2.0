package com.supermanitu.advanceddispensers.breaker;

import com.supermanitu.advanceddispensers.lib.ContainerAdvancedDispensers;
import com.supermanitu.advanceddispensers.lib.TileEntityAdvancedDispensers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerBreaker extends ContainerAdvancedDispensers
{
	public ContainerBreaker(IInventory player, TileEntityAdvancedDispensers tileEntity) 
	{
		super(player, tileEntity);
		
		// Tile Entity, Slot 0-8, Slot IDs 0-8
	    for (int y = 0; y < 3; ++y)
	    {
	        for (int x = 0; x < 3; ++x)
	        {
	        	this.addSlotToContainer(new Slot(tileEntity, x + y * 3, 62 + x * 18, 17 + y * 18));
	        }
	    }

	    // Player Inventory, Slot 9-35, Slot IDs 9-35
	    for (int y = 0; y < 3; ++y) 
	    {
	        for (int x = 0; x < 9; ++x)
	        {
	            this.addSlotToContainer(new Slot(player, x + y * 9 + 9, 8 + x * 18, 84 + y * 18));
	        }
	    }

	    // Player Inventory, Slot 0-8, Slot IDs 36-44
	    for (int x = 0; x < 9; ++x) 
	    {
	        this.addSlotToContainer(new Slot(player, x, 8 + x * 18, 142));
	    }
	}

	@Override
	protected boolean customTransferStackBehavior(ItemStack current, int fromSlot) 
	{
		if (fromSlot < 9) 
		{
            // From TE Inventory to Player Inventory
            if (!this.mergeItemStack(current, 9, 45, true))
                return false;
        }
		else
        {
            // From Player Inventory to TE Inventory
            if (!this.mergeItemStack(current, 0, 9, false))
                return false;
        }
		
		return true;
	}
}
