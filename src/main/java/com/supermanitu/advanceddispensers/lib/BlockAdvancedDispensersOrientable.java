package com.supermanitu.advanceddispensers.lib;

import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public abstract class BlockAdvancedDispensersOrientable extends BlockAdvancedDispensers
{
	public static final PropertyEnum PROPERTYFACING = PropertyEnum.create("facing", EnumFacing.class);
	
	public BlockAdvancedDispensersOrientable(int tickRate, Material material) 
	{
		super(tickRate, material);
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		EnumFacing facing = EnumFacing.getFront(meta & 0x7);
		return this.getDefaultState().withProperty(PROPERTYFACING, facing);
	}
	
	@Override
	public int getMetaFromState(IBlockState state)
	{
		EnumFacing facing = (EnumFacing)state.getValue(PROPERTYFACING);
		return facing.getIndex();
	}
	
	@Override
	protected BlockState createBlockState()
	{
		return new BlockState(this, new IProperty[] {PROPERTYFACING});
	}
	
	@Override
	public IBlockState onBlockPlaced(World world, BlockPos pos, EnumFacing blockFaceClickedOn, float hitX, float hitY, float hitZ, int meta, EntityLivingBase player)
	{
		EnumFacing facing = BlockPistonBase.getFacingFromEntity(world, pos, player);
		
		return this.getDefaultState().withProperty(PROPERTYFACING, facing);
	}
}
