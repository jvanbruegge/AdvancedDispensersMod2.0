package com.supermanitu.advanceddispensers.lib;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;

public abstract class TileEntityAdvancedDispensers extends TileEntity implements IInventory
{
	protected ItemStack[] inventory;
	private String customName;
	
	private boolean wasActive = false;
	
	public TileEntityAdvancedDispensers(ItemStack[] inventory)
	{
		this.inventory = inventory;
	}

	public String getName()
    {
        return this.hasCustomName() ? this.customName : this.getUnlocalizedName();
    }

	@Override
	public boolean hasCustomName() 
	{
		return this.customName != null;
	}

	@Override
	public IChatComponent getDisplayName() 
	{
		return (IChatComponent)(this.hasCustomName() ? new ChatComponentText(this.getName()) : new ChatComponentTranslation(this.getName(), new Object[0]));
	}

	@Override
	public int getSizeInventory() 
	{
		return inventory.length;
	}

	@Override
	public ItemStack getStackInSlot(int index)
	{
		if(index < 0 || index >= this.getSizeInventory())
			return null;
		return inventory[index];
	}

	@Override
	public ItemStack decrStackSize(int index, int count) 
	{
		if (this.getStackInSlot(index) != null) 
		{
			ItemStack itemstack;

			if (this.getStackInSlot(index).stackSize <= count)
			{
				itemstack = this.getStackInSlot(index);
				this.setInventorySlotContents(index, null);
				this.markDirty();
				return itemstack;
			} 
			else 
			{
				itemstack = this.getStackInSlot(index).splitStack(count);

				if (this.getStackInSlot(index).stackSize <= 0) 
				{
					this.setInventorySlotContents(index, null);
				} 
				else 
				{
					//Just to show that changes happened
					this.setInventorySlotContents(index, this.getStackInSlot(index));
				}

				this.markDirty();
				return itemstack;
			}
		}
		else 
		{
			return null;
		}
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int index)
	{
		ItemStack stack = this.getStackInSlot(index);
	    this.setInventorySlotContents(index, null);
	    return stack;
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) 
	{
		if (index < 0 || index >= this.getSizeInventory())
			return;

		if (stack != null && stack.stackSize > this.getInventoryStackLimit()) 
			stack.stackSize = this.getInventoryStackLimit();

		if (stack != null && stack.stackSize == 0) 
			stack = null;

		this.inventory[index] = stack;
		this.markDirty();
	}

	@Override
	public int getInventoryStackLimit()
	{
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) 
	{
		 return !player.isSneaking() && this.worldObj.getTileEntity(this.getPos()) == this && player.getDistanceSq(this.pos.add(0.5, 0.5, 0.5)) <= 64;
	}

	@Override
	public void openInventory(EntityPlayer player) 
	{
		//Not used
	}

	@Override
	public void closeInventory(EntityPlayer player) 
	{
		//Not used
	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) 
	{
		return true;
	}

	@Override
	public int getField(int id) 
	{
		return 0;
	}

	@Override
	public void setField(int id, int value) 
	{
		
	}

	@Override
	public int getFieldCount() 
	{
		return 0;
	}

	@Override
	public void clear()
	{
		for (int i = 0; i < this.getSizeInventory(); i++)
		{
			this.setInventorySlotContents(i, null);
		}
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt) 
	{
	    super.writeToNBT(nbt);

	    NBTTagList list = new NBTTagList();
	    for (int i = 0; i < this.getSizeInventory(); ++i) 
	    {
	        if (this.getStackInSlot(i) != null)
	        {
	            NBTTagCompound stackTag = new NBTTagCompound();
	            stackTag.setByte("Slot", (byte) i);
	            this.getStackInSlot(i).writeToNBT(stackTag);
	            list.appendTag(stackTag);
	        }
	    }
	    nbt.setTag("Items", list);
	    
	    if (this.hasCustomName())
	    {
	        nbt.setString("CustomName", this.getName());
	    }
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) 
	{
	    super.readFromNBT(nbt);

	    NBTTagList list = nbt.getTagList("Items", 10);
	    for (int i = 0; i < list.tagCount(); ++i) 
	    {
	        NBTTagCompound stackTag = list.getCompoundTagAt(i);
	        int slot = stackTag.getByte("Slot") & 255;
	        this.setInventorySlotContents(slot, ItemStack.loadItemStackFromNBT(stackTag));
	    }
	    
	    if (nbt.hasKey("CustomName", 8)) 
	    {
	        this.setCustomName(nbt.getString("CustomName"));
	    }
	}
	
	public final void onReceiveRedstoneSignal(World world, BlockPos pos, IBlockState state, Random rand)
	{
		if(this instanceof IReceiveRedstone) ((IReceiveRedstone)this).onReceiveRedstoneSignal(world, pos, state, rand);
		
		if(this instanceof IReceiveRedstoneOnce && !wasActive) 
		{
			((IReceiveRedstoneOnce)this).onReceiveRedstoneSignalOnce(world, pos, state, rand);
			wasActive = true;
		}
	}

	public void setCustomName(String customName)
	{
		this.customName = customName;
	}

	public ItemStack[] getInventory()
	{
		return inventory;
	}
	
	public void deleteEmptyStacks()
	{
		for(int i = 0; i < inventory.length; i++)
		{
			if(inventory[i] != null && inventory[i].stackSize == 0) inventory[i] = null;
		}
		this.markDirty();
	}

	public final void onNotReceiveRedstoneSignal(World world, BlockPos pos, IBlockState state, Random rand)
	{
		wasActive = false;
	}
	
	protected abstract String getUnlocalizedName();
}
