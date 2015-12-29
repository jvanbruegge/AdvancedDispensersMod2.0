package com.supermanitu.advanceddispensers.main;

import com.supermanitu.advanceddispensers.breaker.ContainerBreaker;
import com.supermanitu.advanceddispensers.breaker.GuiBreaker;
import com.supermanitu.advanceddispensers.breaker.TileEntityBreaker;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class AdvancedDispensersGuiHandler implements IGuiHandler
{
	public static final int BREAKER_GUI = 0;
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) 
	{
		if(ID == BREAKER_GUI)
			return new ContainerBreaker(player.inventory, (TileEntityBreaker) world.getTileEntity(new BlockPos(x, y, z)));
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		if(ID == BREAKER_GUI)
		{
			try 
			{
				return new GuiBreaker(player.inventory,  (TileEntityBreaker) world.getTileEntity(new BlockPos(x, y, z)));
			} 
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return null;
	}

}
