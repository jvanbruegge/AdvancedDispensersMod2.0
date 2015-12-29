package com.supermanitu.advanceddispensers.lib;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public interface IReceiveRedstoneOnce 
{
	public void onReceiveRedstoneSignalOnce(World world, BlockPos pos, IBlockState state, Random rand);
}
