package com.grim3212.mc.pack.core.worldgen;

import com.grim3212.mc.pack.core.config.CoreConfig;
import com.grim3212.mc.pack.core.init.CoreInit;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.registries.ForgeRegistries;

public class CoreWorldGen {

	public static void initWorldGen() {
		if (CoreConfig.generateAluminum.get())
			for (Biome biome : ForgeRegistries.BIOMES) {
				CountRangeConfig placementConfig = new CountRangeConfig(12, 0, 0, 64);
				biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, new DimensionFeature<>(Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, CoreInit.aluminum_ore.getDefaultState(), 8)).func_227228_a_(Placement.COUNT_RANGE.func_227446_a_(placementConfig)), DimensionType.OVERWORLD));
			}
	}

}