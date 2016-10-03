package com.grim3212.mc.pack.world.gen;

import java.util.Random;

import com.grim3212.mc.pack.core.part.GrimWorldGen;
import com.grim3212.mc.pack.world.blocks.WorldBlocks;
import com.grim3212.mc.pack.world.config.WorldConfig;
import com.grim3212.mc.pack.world.util.FloatingIslandsBlacklist;

import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;

public class GrimWorldGenerator extends GrimWorldGen {

	@Override
	protected void generateSurface(World world, Random random, int i, int j) {
<<<<<<< HEAD
		for (int var5 = 0; var5 < 11; ++var5) {
			int x = i + random.nextInt(16);
			int y = random.nextInt(110);
			int z = j + random.nextInt(16);
			(new WorldGenMinable(WorldBlocks.randomite.getDefaultState(), 6)).generate(world, random, new BlockPos(x, y, z));
		}

		for (int reed = 0; reed < 5; ++reed) {
			int Xcoord1 = i + random.nextInt(16) + 8;
			int Ycoord1 = random.nextInt(128);
			int Zcoord1 = j + random.nextInt(16) + 8;
			(new WorldGenGunReeds()).generate(world, random, new BlockPos(Xcoord1, Ycoord1, Zcoord1));
=======
		if (WorldConfig.generateRandomite) {
			for (int var5 = 0; var5 < 11; ++var5) {
				int x = i + random.nextInt(16);
				int y = random.nextInt(110);
				int z = j + random.nextInt(16);
				(new WorldGenMinable(WorldBlocks.randomite.getDefaultState(), 6)).generate(world, random, new BlockPos(x, y, z));
			}
		}

		if (WorldConfig.generateGunpowderReeds) {
			for (int reed = 0; reed < 5; ++reed) {
				int Xcoord1 = i + random.nextInt(16) + 8;
				int Ycoord1 = random.nextInt(128);
				int Zcoord1 = j + random.nextInt(16) + 8;
				(new WorldGenGunReeds()).generate(world, random, new BlockPos(Xcoord1, Ycoord1, Zcoord1));
			}
>>>>>>> 22fd8b1d8d5d5162d98e857979c97722f5731c37
		}

		FloatingIslandsBlacklist.generateFloatingIslands(world, random, i, j);

		if (WorldConfig.generateFlatBedRockSurface)
			surfaceFlatBedrock(world, i, j);

		generateExtras(world, random, i, j);
	}

	private void surfaceFlatBedrock(World world, int blockX, int blockZ) {
		// Used to determine if Flatbedrock should be generated
		boolean foundStone = false;

		for (int k = 1; k <= 5; k++) {
			for (int i1 = blockX; i1 < blockX + 16; i1++) {
				for (int k1 = blockZ; k1 < blockZ + 16; k1++) {
					BlockPos pos = new BlockPos(i1, k, k1);
					if (world.getBlockState(pos).getBlock() == Blocks.BEDROCK) {
						if (!foundStone)
							foundStone = true;

						world.setBlockState(pos, Blocks.STONE.getDefaultState(), 2);
					}
				}
			}
		}

		if (foundStone) {
			for (int l = blockX; l < blockX + 16; l++) {
				for (int j1 = blockZ; j1 < blockZ + 16; j1++) {
					BlockPos pos = new BlockPos(l, 0, j1);
					if (world.getBlockState(pos).getBlock() != Blocks.BEDROCK) {
						world.setBlockState(pos, Blocks.BEDROCK.getDefaultState(), 2);
					}
				}
			}
		}
	}

	private void generateExtras(World world, Random random, int blockX, int blockZ) {
		Biome biome = world.getBiomeProvider().getBiome(new BlockPos(blockX + 16, 60, blockZ + 16));

		if (biome != null) {
			if (random.nextInt(WorldConfig.frequencyWheatField) == 0 && BiomeDictionary.isBiomeOfType(biome, Type.PLAINS)) {
				for (int i = 0; i < 5; i++) {
					int j = random.nextInt(40) + 60;
					new WorldGenFarmland(random.nextInt(8) + 4, random.nextInt(6) + 2).generate(world, random, new BlockPos(blockX + random.nextInt(16), j, blockZ + random.nextInt(16)));
				}
			}

			if (random.nextInt(WorldConfig.frequencySaplings) == 0 && !BiomeDictionary.isBiomeOfType(biome, Type.SANDY)) {
				for (int i = 0; i < 5; i++) {
					int j = random.nextInt(30) + 60;
					new WorldGenSaplingsAndStumps(random.nextInt(16) + 1, true).generate(world, random, new BlockPos(blockX + random.nextInt(16), j, blockZ + random.nextInt(16)));
				}
			}

			if (random.nextInt(WorldConfig.frequencyTreeStumps) == 0 && !BiomeDictionary.isBiomeOfType(biome, Type.SANDY)) {
				for (int i = 0; i < 5; i++) {
					int j = random.nextInt(30) + 60;
					new WorldGenSaplingsAndStumps(random.nextInt(12) + 3, false).generate(world, random, new BlockPos(blockX + random.nextInt(16), j, blockZ + random.nextInt(16)));
				}
			}

			if (random.nextInt(WorldConfig.frequencySandstonePillars) == 0 && biome == Biomes.DESERT) {
				for (int i = 0; i < 5; i++) {
					int j = random.nextInt(25) + 60;
					new WorldGenSandExpanded(random.nextInt(18) + 4, 0).generate(world, random, new BlockPos(blockX + random.nextInt(16), j, blockZ + random.nextInt(16)));
				}
			}

			if (random.nextInt(WorldConfig.frequencyCactusFields) == 0 && biome == Biomes.DESERT) {
				for (int i = 0; i < 5; i++) {
					int j = random.nextInt(55) + 60;
					new WorldGenSandExpanded(random.nextInt(24) + 3, 1).generate(world, random, new BlockPos(blockX + random.nextInt(16), j, blockZ + random.nextInt(16)));
				}
			}

			if (random.nextInt(WorldConfig.frequencySandPits) == 0 && biome == Biomes.DESERT) {
				for (int i = 0; i < 5; i++) {
					int j = random.nextInt(25) + 60;
					new WorldGenSandExpanded(random.nextInt(19) + 4, 2).generate(world, random, new BlockPos(blockX + random.nextInt(16), j, blockZ + random.nextInt(16)));
				}
			}

			if (random.nextInt(WorldConfig.frequencyMelons) == 0 && (biome == Biomes.RIVER || biome == Biomes.OCEAN || biome == Biomes.PLAINS)) {
				for (int i = 0; i < 5; i++) {
					int j = random.nextInt(10) + 60;
					new WorldGenMelons(random.nextInt(8) + 4).generate(world, random, new BlockPos(blockX + random.nextInt(16), j, blockZ + random.nextInt(16)));
				}
			}
		}
	}

	@Override
	protected void generateNether(World world, Random random, int i, int j) {
		if (WorldConfig.generateFlatBedRockNether)
			netherFlatBedrock(world, i, j);

		for (int var5 = 0; var5 < 39; ++var5) {
			int var6 = i + random.nextInt(16);
			int var7 = random.nextInt(128);
			int var8 = j + random.nextInt(16);
			(new WorldGenCorruption(10, WorldBlocks.corruption_block)).generate(world, random, new BlockPos(var6, var7, var8));
		}
	}

	private void netherFlatBedrock(World world, int blockX, int blockZ) {
		for (int k = 1; k <= 5; k++) {
			for (int i1 = blockX; i1 < blockX + 16; i1++) {
				for (int k1 = blockZ; k1 < blockZ + 16; k1++) {
					BlockPos pos = new BlockPos(i1, k, k1);
					if (world.getBlockState(pos).getBlock() == Blocks.BEDROCK) {
						world.setBlockState(pos, Blocks.STONE.getDefaultState(), 2);
					}
				}
			}
		}

		for (int l = blockX; l < blockX + 16; l++) {
			for (int j1 = blockZ; j1 < blockZ + 16; j1++) {
				BlockPos pos = new BlockPos(l, 0, j1);
				if (world.getBlockState(pos).getBlock() != Blocks.BEDROCK) {
					world.setBlockState(pos, Blocks.BEDROCK.getDefaultState(), 2);
				}
			}
		}
	}

	@Override
	protected void generateEnd(World world, Random random, int i, int j) {
	}

	@Override
	protected void generateCustom(DimensionType dimension, World world, Random random, int i, int j) {
	}
}
