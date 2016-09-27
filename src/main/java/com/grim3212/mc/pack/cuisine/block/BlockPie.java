package com.grim3212.mc.pack.cuisine.block;

import com.grim3212.mc.pack.core.manual.IManualEntry.IManualBlock;
import com.grim3212.mc.pack.core.manual.pages.Page;
import com.grim3212.mc.pack.cuisine.client.ManualCuisine;

import net.minecraft.block.BlockCake;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockPie extends BlockCake implements IManualBlock {

	public BlockPie() {
		super();
		setSoundType(SoundType.CLOTH);
	}

	@Override
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
		return new ItemStack(this);
	}

	@Override
	public Page getPage(IBlockState state) {
		return ManualCuisine.bakedPie_page;
	}
}
