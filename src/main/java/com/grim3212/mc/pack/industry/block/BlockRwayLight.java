package com.grim3212.mc.pack.industry.block;

import java.util.Random;

import com.grim3212.mc.pack.core.block.BlockManual;
import com.grim3212.mc.pack.core.manual.pages.Page;
import com.grim3212.mc.pack.industry.client.ManualIndustry;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockRwayLight extends BlockManual {

	public BlockRwayLight() {
		super(Material.ROCK, SoundType.STONE);
	}

	@Override
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn) {
		boolean powered = worldIn.isBlockPowered(pos) || worldIn.isBlockPowered(pos.up());
		if (powered) {
			worldIn.setBlockState(pos, IndustryBlocks.rway_light_on.getDefaultState());
		} else if (!powered) {
			worldIn.setBlockState(pos, IndustryBlocks.rway_light_off.getDefaultState());
		}
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return Item.getItemFromBlock(IndustryBlocks.rway_light_off);
	}

	@Override
	public Page getPage(IBlockState state) {
		return ManualIndustry.rways_page;
	}
}
