package com.grim3212.mc.pack.world.gen.structure.snowball;

import java.util.Random;

import com.grim3212.mc.pack.world.config.WorldConfig;
import com.grim3212.mc.pack.world.gen.structure.Structure;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;

public class StructureSnowball extends Structure {

	public static StructureSnowball INSTANCE = new StructureSnowball();

	@Override
	protected String getStructureName() {
		return "SnowBalls";
	}

	@Override
	protected boolean generateStructureInChunk(long seed, World world, int chunkX, int chunkZ) {
		Random random = new Random(seed);

		int x = chunkX * 16 + 8 + random.nextInt(16);
		int z = chunkZ * 16 + 8 + random.nextInt(16);
		BlockPos pos = world.getTopSolidOrLiquidBlock(new BlockPos(x, 0, z));

		int radius = 3 * (3 + random.nextInt(5));

		return checkStructures(world, pos) && new StructureSnowBallGenerator(getStructureName(), radius, getStructureStorage(world)).generate(world, random, pos);
	}

	@Override
	protected boolean canGenerateInChunk(World world, Random rand, int chunkX, int chunkZ) {
		if (rand.nextInt(WorldConfig.ruinSnowBallChance) == 0) {
			BlockPos pos = new BlockPos(chunkX * 16, 0, chunkZ * 16);
			Biome biome = world.getBiomeProvider().getBiome(pos);

			return BiomeDictionary.hasType(biome, Type.SNOWY);
		}

		return false;
	}

	@Override
	protected boolean canGenerate() {
		return WorldConfig.subpartRuins && WorldConfig.ruinSnowBallChance > 0;
	}

	@Override
	protected int[] getChunkOffsets() {
		return BASIC_2_OFFSETS;
	}
}
