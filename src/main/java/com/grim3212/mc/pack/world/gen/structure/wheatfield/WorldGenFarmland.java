package com.grim3212.mc.pack.world.gen.structure.wheatfield;

import java.util.Random;

import com.grim3212.mc.pack.world.gen.structure.StructureGenerator;
import com.grim3212.mc.pack.world.gen.structure.StructureStorage;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;

public class WorldGenFarmland extends StructureGenerator {

	private final int numWheat;
	private final int numRows;
	private final int attempts;
	private final StructureStorage storage;

	public WorldGenFarmland(String structName, int numWheat, int numRows, int attempts, StructureStorage storage) {
		super(structName);
		this.numWheat = numWheat;
		this.numRows = numRows;
		this.attempts = attempts;
		this.storage = storage;
	}

	private void recBlockGenerateX(World world, Random random, BlockPos pos, int count) {
		// set block (valid location assumed)
		if (world.getBlockState(pos.up()).getBlock() != Blocks.LOG) {
			world.setBlockState(pos, Blocks.FARMLAND.getDefaultState());
			world.setBlockState(pos.up(), Blocks.WHEAT.getDefaultState().withProperty(BlockCrops.AGE, random.nextInt(8)), 2);
		}

		// generate z in next valid z block with count = numWheat
		if (world.getBlockState(pos.south()).getBlock() == Blocks.GRASS)
			recBlockGenerateZ(world, random, pos.south(), numWheat - 1);
		else if (world.getBlockState(pos.down().south()).getBlock() == Blocks.GRASS)
			recBlockGenerateZ(world, random, pos.down().south(), numWheat - 1);
		else if (world.getBlockState(pos.up().south()).getBlock() == Blocks.GRASS)
			recBlockGenerateZ(world, random, pos.up().south(), numWheat - 1);

		// if count > 1, generate x in next x block with count - 1
		if (count < 2)
			return;

		if (world.getBlockState(pos.east()).getBlock() == Blocks.GRASS)
			recBlockGenerateX(world, random, pos.east(), count - 1);
		else if (world.getBlockState(pos.east().down()).getBlock() == Blocks.GRASS)
			recBlockGenerateX(world, random, pos.east().down(), count - 1);
		else if (world.getBlockState(pos.east().up()).getBlock() == Blocks.GRASS)
			recBlockGenerateX(world, random, pos.east().up(), count - 1);
	}

	private void recBlockGenerateZ(World world, Random random, BlockPos pos, int count) {
		// set block (valid location assumed)
		if (world.getBlockState(pos.up()).getBlock() != Blocks.LOG) {
			world.setBlockState(pos, Blocks.FARMLAND.getDefaultState());
			world.setBlockState(pos.up(), Blocks.WHEAT.getDefaultState().withProperty(BlockCrops.AGE, random.nextInt(8)), 2);
		}

		// generate z in next valid z block with count = numWheat
		if (count < 2)
			return;
		if (world.getBlockState(pos.south()).getBlock() == Blocks.GRASS)
			recBlockGenerateZ(world, random, pos.south(), count - 1);
		else if (world.getBlockState(pos.down().south()).getBlock() == Blocks.GRASS)
			recBlockGenerateZ(world, random, pos.down().south(), count - 1);
		else if (world.getBlockState(pos.up().south()).getBlock() == Blocks.GRASS)
			recBlockGenerateZ(world, random, pos.up().south(), count - 1);
	}

	public static boolean removeAboveBlocks(World world, BlockPos pos) {
		while (world.getBlockState(pos).getBlock() != Blocks.AIR && pos.getY() < 128) {
			world.setBlockToAir(pos);
			pos = pos.up();
		}
		return true;
	}

	public static boolean fillBelowBlocks(World world, BlockPos pos, Block l) {
		while ((world.getBlockState(pos).getBlock() == Blocks.AIR || world.getBlockState(pos).getBlock() == Blocks.SNOW) && pos.getY() > 0) {
			world.setBlockState(pos, l.getDefaultState());
			pos = pos.down();
		}
		return true;
	}

	@Override
	public boolean generate(World worldIn, Random random, BlockPos pos) {
		for (int l = 0; l < this.attempts; l++) {
			int i1 = (pos.getX() + random.nextInt(8)) - random.nextInt(8);
			int j1 = (pos.getY() + random.nextInt(4)) - random.nextInt(4);
			int k1 = (pos.getZ() + random.nextInt(8)) - random.nextInt(8);
			BlockPos newPos = new BlockPos(i1, j1, k1);

			if (worldIn.getBlockState(newPos).getBlock() == Blocks.GRASS) {
				recBlockGenerateX(worldIn, random, newPos, numRows);

				storage.addBBSave(structName, new StructureBoundingBox(newPos.getX(), newPos.getY() - numWheat, newPos.getZ(), newPos.getX() + numRows, newPos.getY() + numRows, newPos.getZ() + numWheat));
				return true;
			}
		}
		return false;
	}
}