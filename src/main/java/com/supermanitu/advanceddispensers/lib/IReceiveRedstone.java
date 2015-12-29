package com.supermanitu.advanceddispensers.lib;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public interface IReceiveRedstone
{
	public void onReceiveRedstoneSignal(World world, BlockPos pos, IBlockState state, Random rand);
}
