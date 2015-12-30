package com.supermanitu.advanceddispensers.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.supermanitu.advanceddispensers.breaker.BlockBreaker;
import com.supermanitu.advanceddispensers.helper.EntityFakePlayer;
import com.supermanitu.advanceddispensers.lib.IReceiveRedstoneOnce;
import com.supermanitu.advanceddispensers.lib.TileEntityAdvancedDispensers;
import com.supermanitu.advanceddispensers.main.AdvancedDispensersBlocks;
import com.supermanitu.advanceddispensers.main.AdvancedDispensersGuiHandler;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumFacing.Axis;
import net.minecraft.world.World;

public class TileEntityUser extends TileEntityAdvancedDispensers implements IReceiveRedstoneOnce
{
	private EntityFakePlayer fakePlayer;

	public TileEntityUser() 
	{
		super(new ItemStack[9]);
	}

	@Override
	protected String getUnlocalizedName()
	{
		return "tile.blockuser.name";
	}

	@Override
	public void onReceiveRedstoneSignalOnce(World world, BlockPos pos, IBlockState state, Random rand)
	{
		if(fakePlayer == null)
		{
			fakePlayer = new EntityFakePlayer(worldObj, pos, worldObj.getBlockState(pos));
		}

		EnumFacing facing = (EnumFacing) state.getValue(BlockBreaker.PROPERTYFACING);

		for(int j = 0; j < inventory.length; j++)
		{
			if(inventory[j] != null)
			{
				for(int i = 0; i <= fakePlayer.getRange(); i++)
				{
					BlockPos frontPos;
					BlockPos behindPos;
					Block blockBehind;

					if(i < 4)
					{
						frontPos = new BlockPos(pos.getX() + facing.getFrontOffsetX() * i, pos.getY() + facing.getFrontOffsetY() * i, pos.getZ() + facing.getFrontOffsetZ() * i);
						behindPos = new BlockPos(pos.getX() + facing.getFrontOffsetX() * (i + 1), pos.getY() + facing.getFrontOffsetY() * (i + 1), pos.getZ() + facing.getFrontOffsetZ() * (i +  1));
						blockBehind = world.getBlockState(behindPos).getBlock();
					}
					else if(facing != EnumFacing.UP && facing != EnumFacing.DOWN)
					{
						frontPos = new BlockPos(pos.getX() + facing.getFrontOffsetX(), pos.getY() + facing.getFrontOffsetY(), pos.getZ() + facing.getFrontOffsetZ());
						behindPos = new BlockPos(pos.getX() + facing.getFrontOffsetX(), pos.getY() + facing.getFrontOffsetY() - 1, pos.getZ() + facing.getFrontOffsetZ());
						blockBehind = world.getBlockState(behindPos).getBlock();
					}
					else
					{
						break;
					}
					EnumFacing reverseFacing;

					if(i < 4)
					{
						if(facing == EnumFacing.UP || facing == EnumFacing.DOWN) reverseFacing = facing.rotateAround(Axis.X).rotateAround(Axis.X);
						else reverseFacing = facing.rotateY().rotateY();
					}
					else
					{
						reverseFacing = EnumFacing.UP;
					}
					
					AxisAlignedBB rect = getRect(frontPos, facing);
					List<EntityLivingBase> near = world.getEntitiesWithinAABBExcludingEntity(fakePlayer, rect);
					near = sortByDistance(near);
					
					for(EntityLivingBase entity : near)
					{
						if(entity == null) continue;
						Item item = inventory[j].getItem();
						if(item.itemInteractionForEntity(inventory[j], fakePlayer, entity))
						{
							this.deleteEmptyStacks();
							return;
						}
						else if(item instanceof ItemSword && item.hitEntity(inventory[j], entity, fakePlayer))
						{
							entity.attackEntityFrom(DamageSource.generic, ((ItemSword)item).getDamageVsEntity() + 5);
							this.deleteEmptyStacks();
							return;
						}
					}

					if(inventory[j].getItem().onItemUseFirst(inventory[j], fakePlayer, world, behindPos, reverseFacing, 0.5f, 0.5f, 0.5f))
					{
						this.deleteEmptyStacks();
						return;
					}
					else if(inventory[j].getItem().onItemUse(inventory[j], fakePlayer, world, behindPos, reverseFacing, 0.5f, 0.5f, 0.5f))
					{
						this.deleteEmptyStacks();
						return;
					}
					else
					{
						ItemStack stack = inventory[j].getItem().onItemRightClick(inventory[j], worldObj, fakePlayer);
						if(!stack.equals(inventory[j]))
						{
							System.out.println(stack);
							this.deleteEmptyStacks();
							return;
						}
					}
				}
			}
		}
	}
	
	private List<EntityLivingBase> sortByDistance(List<EntityLivingBase> near) 
	{
		ArrayList<EntityLivingBase> l = new ArrayList<EntityLivingBase>();
		
		for(int i = 0; i < near.size(); i++)
		{
			double min = Double.MAX_VALUE;
			EntityLivingBase nearest = null;
			
			for(int j = 0; j < near.size(); j++)
			{
				if(near.get(j) instanceof EntityLivingBase && near.get(j) != null)
				{
					double distance = near.get(j).getDistanceSqToEntity(fakePlayer);
					if(distance < min)
					{
						min = distance;
						nearest = near.get(j);
					}
				}
			}
			
			l.add(nearest);
			near.remove(nearest);
		}
		return l;
	}

	private AxisAlignedBB getRect(BlockPos frontPos, EnumFacing userFacing)
	{
		return AxisAlignedBB.fromBounds(frontPos.getX() - 1, frontPos.getY() - 1, frontPos.getZ() - 1,
				frontPos.getX() + userFacing.getFrontOffsetX() * fakePlayer.getRange() + 1, frontPos.getY() + userFacing.getFrontOffsetY() * fakePlayer.getRange() + 1, frontPos.getZ() + userFacing.getFrontOffsetZ() * fakePlayer.getRange() + 1);
	}
}