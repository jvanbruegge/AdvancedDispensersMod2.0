package com.supermanitu.advanceddispensers.breaker;

import net.minecraft.util.IStringSerializable;

public enum BreakerTier implements IStringSerializable
{
	Iron("iron"),
	Diamond("diamond");
	
	private final String name;
	
	private BreakerTier(String name)
    {
      this.name = name;
    }

	@Override
	public String getName() 
	{
		return name;
	}
}
