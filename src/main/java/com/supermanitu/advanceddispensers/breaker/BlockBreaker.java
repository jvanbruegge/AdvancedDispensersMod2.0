package com.supermanitu.advanceddispensers.breaker;

import java.util.List;

import com.supermanitu.advanceddispensers.lib.BlockAdvancedDispensers;
import com.supermanitu.advanceddispensers.lib.IHasSubtypes;
import com.supermanitu.advanceddispensers.main.AdvancedDispensersGuiHandler;
import com.supermanitu.advanceddispensers.main.AdvancedDispensersMod;

import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockBreaker extends BlockAdvancedDispensers implements IHasSubtypes
{
	public static final PropertyEnum PROPERTYTIER = PropertyEnum.create("tier", BreakerTier.class);
	public static final PropertyEnum PROPERTYFACING = PropertyEnum.create("facing", EnumFacing.class);
	
	public BlockBreaker() 
	{
		super(Material.rock);
		this.setUnlocalizedName(getName());
		this.setCreativeTab(AdvancedDispensersMod.advancedDispensersTab);
		this.setHarvestLevel("pickaxe", 1);
	}
	
	@Override
	public int damageDropped(IBlockState state)
	{
		BreakerTier tier = (BreakerTier)state.getValue(PROPERTYTIER);
		if(tier == BreakerTier.Iron) return 0;
		else return 1;
	}
	
	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, List list)
	{
		list.add(new ItemStack(item, 1, 0));
		list.add(new ItemStack(item, 1, 1));
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		EnumFacing facing = EnumFacing.getFront(meta & 0x7);
		BreakerTier tier = (meta & 0x8) == 0 ? BreakerTier.Iron : BreakerTier.Diamond;
		return this.getDefaultState().withProperty(PROPERTYTIER, tier).withProperty(PROPERTYFACING, facing);
	}
	
	@Override
	public int getMetaFromState(IBlockState state)
	{
		EnumFacing facing = (EnumFacing)state.getValue(PROPERTYFACING);
		BreakerTier tier = (BreakerTier)state.getValue(PROPERTYTIER);
		
		int facingbits = facing.getIndex();
		int tierbit = tier == BreakerTier.Iron ? 0x0 : 0x8;
		
		return tierbit | facingbits;
	}
	
	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos)
	{
		return state;
	}
	
	@Override
	protected BlockState createBlockState()
	{
		return new BlockState(this, new IProperty[] {PROPERTYFACING, PROPERTYTIER});
	}
	
	@Override
	public IBlockState onBlockPlaced(World world, BlockPos pos, EnumFacing blockFaceClickedOn, float hitX, float hitY, float hitZ, int meta, EntityLivingBase player)
	{
		BreakerTier tier = meta  == 0 ? BreakerTier.Iron : BreakerTier.Diamond;
		EnumFacing facing = BlockPistonBase.getFacingFromEntity(world, pos, player);
		
		return this.getDefaultState().withProperty(PROPERTYFACING, facing).withProperty(PROPERTYTIER, tier);
	}
	
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		if (!world.isRemote) 
		{
	        player.openGui(AdvancedDispensersMod.instance, AdvancedDispensersGuiHandler.BREAKER_GUI, world, pos.getX(), pos.getY(), pos.getZ());
	    }
	    return true;
	}

	@Override
	public Object[] getRecipe()
	{
		return null;
	}
	
	@Override
	public void addRecipes()
	{
		GameRegistry.addRecipe(new ItemStack(this, 1, 0), new Object[]{"XZX","ZOZ","BYB", 'Z', Items.redstone, 'Y', Blocks.piston, 'X', Items.iron_ingot, 'O', Items.iron_pickaxe, 'B', Blocks.stone});
		GameRegistry.addRecipe(new ItemStack(this, 1, 1), new Object[]{"XZX","ZOZ","BYB", 'Z', Items.redstone, 'Y', Blocks.piston, 'X', Items.iron_ingot, 'O', Items.diamond_pickaxe, 'B', Blocks.stone});
	}

	@Override
	public String getName()
	{
		return "blockbreaker";
	}

	@Override
	public int countSubtypes() 
	{
		return 2;
	}

	@Override
	public String getVariantName(int i)
	{
		return i == 0 ? "advanceddispensers:blockbreaker_iron" : "advanceddispensers:blockbreaker_diamond";
	}
	
	@Override
	public void addVariantName(Item item) 
	{
		ModelBakery.addVariantName(item, "advanceddispensers:blockbreaker_iron", "advanceddispensers:blockbreaker_diamond");
	}
	
	@Override
	public Class<? extends ItemBlock> getItemBlock()
	{
		return ItemBlockBreaker.class;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return new TileEntityBreaker();
	}
}
