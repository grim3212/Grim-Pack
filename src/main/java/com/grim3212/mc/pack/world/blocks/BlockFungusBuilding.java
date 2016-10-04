package com.grim3212.mc.pack.world.blocks;

import java.util.Random;

import com.grim3212.mc.pack.core.manual.IManualEntry.IManualBlock;
import com.grim3212.mc.pack.core.manual.pages.Page;
import com.grim3212.mc.pack.world.client.ManualWorld;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockStoneBrick;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockFungusBuilding extends BlockFungusBase implements IManualBlock {

	protected BlockFungusBuilding() {
		super(true);
	}

	public static final Block[] buildId = { Blocks.FLOWING_WATER, // 0
			Blocks.DIRT, // 1
			Blocks.STONE, // 2
			Blocks.SAND, // 3
			Blocks.NETHERRACK, // 4
			Blocks.COBBLESTONE, // 5
			Blocks.STONEBRICK, // 6
			Blocks.SANDSTONE, // 7
			Blocks.FLOWING_LAVA, // 8
			// layers
			Blocks.DIRT, // 9
			Blocks.STONE, // 10
			/* maze */Blocks.STONEBRICK, // 11
			Blocks.NETHERRACK, // 12
			Blocks.COBBLESTONE, // 13
			Blocks.STONEBRICK, // 14
			Blocks.SANDSTONE // 15
	};

	@Override
	public boolean canReplace(IBlockState side, IBlockState state) {
		Block block = side.getBlock();
		int meta = (Integer) state.getValue(TYPE);
		return ((block == Blocks.FLOWING_WATER || block == Blocks.WATER) && !(meta == 0)) || ((block == Blocks.FLOWING_LAVA || block == Blocks.LAVA) && !(meta == 8)) || block == Blocks.AIR || block instanceof BlockBush || block == Blocks.FIRE || block == Blocks.SNOW_LAYER || block == Blocks.REEDS || block == Blocks.VINE || ((block == WorldBlocks.fungus_growing || block == WorldBlocks.fungus_building || block == WorldBlocks.fungus_killing) && (side != state || block != this) && !(meta == 11));
	}

	public int getBrick(Random random) {
		int number = random.nextInt(30);
		if (number <= 8) {
			return 0;
		}
		if (number <= 16) {
			return 1;
		}
		if (number <= 24) {
			return 2;
		}
		return 3;
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		int meta = (Integer) state.getValue(TYPE);

		// builder 'except maze
		if (meta != 11) {
			if (!spreadToSide(worldIn, pos, state, false, meta <= 8)) {
				Block i2b = buildId[meta];
				int m2b = 0;
				if (i2b == Blocks.STONEBRICK) {
					m2b = getBrick(rand);
					IBlockState stonebrick = Blocks.STONEBRICK.getDefaultState().withProperty(BlockStoneBrick.VARIANT, BlockStoneBrick.EnumType.byMetadata(m2b));
					worldIn.setBlockState(pos, stonebrick, 2);
					return;
				}

				worldIn.setBlockState(pos, i2b.getDefaultState(), 2);
			}
			return;
		}

		// maze builder
		if (meta == 11) {
			if (worldIn.getBlockState(pos.up()).getBlock() == Blocks.AIR) {
				// if(random.nextInt(7)==0){return;}
				boolean spread = spreadToSideMaze(worldIn, pos, state);
				if (!spread) {
					int m2b = getBrick(worldIn.rand);
					IBlockState stonebrick = Blocks.STONEBRICK.getDefaultState().withProperty(BlockStoneBrick.VARIANT, BlockStoneBrick.EnumType.byMetadata(m2b));
					worldIn.setBlockState(pos, stonebrick, 2);
					if (canReplace(worldIn.getBlockState(pos.down()), state)) {
						worldIn.setBlockState(pos.down(), state);
						return;
					}
				} else {
					if (rand.nextInt(32) == 0) {
						worldIn.setBlockToAir(pos);
						for (int jj = pos.getY(); jj > 1; jj--) {
							BlockPos newPos = new BlockPos(pos.getX(), jj, pos.getZ());
							if (worldIn.getBlockState(newPos).getBlock() == this || worldIn.getBlockState(newPos).getBlock() == Blocks.STONEBRICK) {
								worldIn.setBlockToAir(newPos);
							} else {
								break;
							}
						}
						return;
					}
					if (canReplace(worldIn.getBlockState(pos.down()), state)) {
						worldIn.setBlockState(pos.down(), state);
						return;
					}
				}
			} else {
				if (canReplace(worldIn.getBlockState(pos.down()), state)) {
					worldIn.setBlockState(pos.down(), state);
					return;
				} else {
					int m2b = getBrick(worldIn.rand);
					IBlockState stonebrick = Blocks.STONEBRICK.getDefaultState().withProperty(BlockStoneBrick.VARIANT, BlockStoneBrick.EnumType.byMetadata(m2b));
					worldIn.setBlockState(pos, stonebrick);
				}
			}
			return;
		}
	}

	// MAZE START
	public boolean spreadToSideMaze(World world, BlockPos pos, IBlockState state) {
		IBlockState[] sideStates = { world.getBlockState(pos.west()), world.getBlockState(pos.east()), world.getBlockState(pos.north()), world.getBlockState(pos.south()) };
		int[] side = { 0, 1, 2, 3 };

		for (int q = 0; q < side.length; q++) {
			int randomPosition = world.rand.nextInt(side.length);
			int temp = side[q];
			side[q] = side[randomPosition];
			side[randomPosition] = temp;
		}

		// sides
		for (int r = 0; r <= 3; r++) {
			if (side[r] == 0 && good4maze(world, pos.west(), state, sideStates[0])) {
				world.setBlockState(pos.west(), state);
				return true;
			}
			if (side[r] == 1 && good4maze(world, pos.east(), state, sideStates[1])) {
				world.setBlockState(pos.east(), state);
				return true;
			}
			if (side[r] == 2 && good4maze(world, pos.north(), state, sideStates[2])) {
				world.setBlockState(pos.north(), state);
				return true;
			}
			if (side[r] == 3 && good4maze(world, pos.south(), state, sideStates[3])) {
				world.setBlockState(pos.south(), state);
				return true;
			}
		}
		return false;
	}

	public boolean good4maze(World world, BlockPos pos, IBlockState state, IBlockState side) {
		if (!canReplace(side, state)) {
			return false;
		}

		int sides = 0;
		int corners = 0;
		IBlockState[] sideStates = { world.getBlockState(pos.west()), world.getBlockState(pos.east()), world.getBlockState(pos.north()), world.getBlockState(pos.south()), world.getBlockState(pos.west().north()), world.getBlockState(pos.east().south()), world.getBlockState(pos.east().north()), world.getBlockState(pos.east().south()) };

		int ls = -1;
		for (int q = 0; q <= 3; q++) {
			if (!canReplace(sideStates[q], state)) {
				sides++;
				ls = q;
			}
		}

		int c1 = -1;
		int c2 = -1;
		for (int q = 4; q <= 7; q++) {
			if (!canReplace(sideStates[q], state)) {
				corners++;

				if (corners == 1) {
					c1 = q;
				} else if (corners == 2) {
					c2 = q;
				} else {
					return false;
				}
			}
		}

		if (sides > 1)
			return world.rand.nextInt(200) == 0;

		if (corners > 2) {
			return false;
		}
		if (corners == 0) {
			return true;
		}
		boolean good = false;
		if (ls == 0 && ((c1 == 4 && c2 == -1) || (c1 == 5 && c2 == -1) || (c1 == 4 && c2 == 5))) {
			good = true;
		}
		if (ls == 1 && ((c1 == 6 && c2 == -1) || (c1 == 7 && c2 == -1) || (c1 == 6 && c2 == 7))) {
			good = true;
		}
		if (ls == 2 && ((c1 == 4 && c2 == -1) || (c1 == 6 && c2 == -1) || (c1 == 4 && c2 == 6))) {
			good = true;
		}
		if (ls == 3 && ((c1 == 5 && c2 == -1) || (c1 == 7 && c2 == -1) || (c1 == 5 && c2 == 7))) {
			good = true;
		}
		return good || world.rand.nextInt(200) == 0;
	}

	public static final int[] color = { 0x5A9CFF, // 0 water
			0x82510c, // 1 dirt
			0x999999, // 2 stone
			0xffb500, // 3 sand
			0x851d0c, // 4 nether
			0x777777, // 5 cobble
			0x888888, // 6 stonebri
			0xFFDB87, // 7 sandst
			0xFF7500, // 8 lava
			// layer
			0x82510c, // 9 dirt
			0x999999, // 10 sto
			0x66849a, // 11 MAZE
			0x851d0c, // 12 neth
			0x777777, // 13 cobl
			0x888888, // 14 stonebri
			0xFFDB87 // 15 sandst
	};

	@Override
	public Page getPage(IBlockState state) {
		if(state.getValue(TYPE) == 11){
			return ManualWorld.mazeFungus_page;
		}
		
		return ManualWorld.buildFungus_page;
	}
}
