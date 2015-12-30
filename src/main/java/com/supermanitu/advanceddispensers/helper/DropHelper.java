package com.supermanitu.advanceddispensers.helper;

import java.util.Random;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class DropHelper 
{
	private static final Random random = new Random();
	
	public static void spawnItemStack(World world, double x, double y, double z, ItemStack itemStack)
    {	
        float f = random.nextFloat() * 0.8F + 0.1F;
        float f1 = random.nextFloat() * 0.8F + 0.1F;
        float f2 = random.nextFloat() * 0.8F + 0.1F;

        while (itemStack.stackSize > 0)
        {
            int i = random.nextInt(21) + 10;

            if (i > itemStack.stackSize)
            {
                i = itemStack.stackSize;
            }

            itemStack.stackSize -= i;
            EntityItem entityitem = new EntityItem(world, x + (double)f, y + (double)f1, z + (double)f2, new ItemStack(itemStack.getItem(), i, itemStack.getMetadata()));

            if (itemStack.hasTagCompound())
            {
                entityitem.getEntityItem().setTagCompound((NBTTagCompound)itemStack.getTagCompound().copy());
            }

            float f3 = 0.05F;
            entityitem.motionX = random.nextGaussian() * (double)f3;
            entityitem.motionY = random.nextGaussian() * (double)f3 + 0.20000000298023224D;
            entityitem.motionZ = random.nextGaussian() * (double)f3;
            world.spawnEntityInWorld(entityitem);
        }
    }
	
	public static void spawnItemStack(World world, BlockPos pos, ItemStack itemStack)
	{
		spawnItemStack(world, pos.getX(), pos.getY(), pos.getZ(), itemStack);
	}
}
