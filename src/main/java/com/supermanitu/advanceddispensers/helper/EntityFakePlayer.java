package com.supermanitu.advanceddispensers.helper;

import java.util.UUID;

import com.mojang.authlib.GameProfile;
import com.supermanitu.advanceddispensers.lib.BlockAdvancedDispensers;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class EntityFakePlayer extends EntityPlayer
{
	private final int range = 4;
	
	public EntityFakePlayer(World world, BlockPos pos, IBlockState state) 
	{
		super(world, new GameProfile(UUID.randomUUID(), "fakePlayer"));
		
		initPosition(pos, state);
	}

	@Override
	public boolean isSpectator() 
	{
		return false;
	}
	
	public int getRange()
	{
		return range;
	}
	
	private void initPosition(BlockPos pos, IBlockState state)
	{
		double var3 = (double)pos.getX() + 0.5D;
        double var5 = (double)pos.getY() - 1.1D;
        double var7 = (double)pos.getZ() + 0.5D;
        float var1;
        float var2;
        
        BlockAdvancedDispensers block = (BlockAdvancedDispensers) state.getBlock();

        switch (block.getMetaFromState(state) - 8)
        {
            case 0:
                var1 = 90.0F;
                var2 = 0.0F;
                var5 -= 0.51D;
                break;

            case 1:
                var1 = -90.0F; 
                var2 = 0.0F;
                var5 += 0.51D;
                break;

            case 2:
                var1 = 0.0F;
                var2 = 180.0F;
                var7 -= 0.51D;
                break;

            case 3:
                var1 = 0.0F;
                var2 = 0.0F;
                var7 += 0.51D;
                break;

            case 4:
                var1 = 0.0F;
                var2 = 90.0F;
                var3 -= 0.51D;
                break;

            default:
                var1 = 0.0F;
                var2 = 270.0F;
                var3 += 0.51D;
        }
        
        this.setLocationAndAngles(var3, var5, var7, var2, var1);
	}
}
