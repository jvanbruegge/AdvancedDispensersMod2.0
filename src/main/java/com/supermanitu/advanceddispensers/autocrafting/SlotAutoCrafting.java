package com.supermanitu.advanceddispensers.autocrafting;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class SlotAutoCrafting extends Slot
{
	private IInventory craftMatrix;
	private int amountCrafted;
	
	public SlotAutoCrafting(IInventory craftMatrix, IInventory craftResult, int index, int xPosition, int yPosition) 
	{
		super(craftResult, index, xPosition, yPosition);
		
		this.craftMatrix = craftMatrix;
	}
	
	@Override
	public boolean isItemValid(ItemStack itemStack)
	{
		return false;
	}
	
	@Override
	public ItemStack decrStackSize(int index)
	{
		if (this.getHasStack())
		{
			this.amountCrafted += Math.min(index, this.getStack().stackSize);
		}

		return super.decrStackSize(index);
	}
	
	@Override
	protected void onCrafting(ItemStack itemStack, int amount)
	{
		this.amountCrafted += amount;
		this.onCrafting(itemStack);
	}
	
	@Override
	protected void onCrafting(ItemStack itemStack)
	{
		this.amountCrafted = 0;
	}
	
	public void onPickupFromSlot(EntityPlayer player, ItemStack itemStack)
	{
		net.minecraftforge.fml.common.FMLCommonHandler.instance().firePlayerCraftingEvent(player, itemStack, craftMatrix);
        this.onCrafting(itemStack);
        ItemStack[] aitemstack = CraftingManager.getInstance().func_180303_b((InventoryCrafting) this.craftMatrix, player.worldObj);

        for (int i = 0; i < aitemstack.length; ++i)
        {
            ItemStack itemstack1 = this.craftMatrix.getStackInSlot(i);
            ItemStack itemstack2 = aitemstack[i];

            if (itemstack1 != null)
            {
                this.craftMatrix.decrStackSize(i, 1);
            }

            if (itemstack2 != null)
            {
                if (this.craftMatrix.getStackInSlot(i) == null)
                {
                    this.craftMatrix.setInventorySlotContents(i, itemstack2);
                }
                else if (!player.inventory.addItemStackToInventory(itemstack2))
                {
                    player.dropPlayerItemWithRandomChoice(itemstack2, false);
                }
            }
        }
	}
}
