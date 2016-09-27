package com.grim3212.mc.pack.world.gen;

import com.grim3212.mc.pack.world.config.WorldConfig;

import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class WorldEvents {

	@SubscribeEvent
	public void replaceDesertWell(DecorateBiomeEvent.Decorate event) {
		if (WorldConfig.replaceDesertWells) {
			if (event.getType() == EventType.DESERT_WELL) {
				event.setResult(Result.DENY);

				if (event.getRand().nextInt(1000) == 0) {
					int i = event.getRand().nextInt(16) + 8;
					int j = event.getRand().nextInt(16) + 8;
					BlockPos blockpos1 = event.getWorld().getHeight(event.getPos().add(i, 0, j)).up();
					(new WorldGenBetterDesertWells()).generate(event.getWorld(), event.getRand(), blockpos1);
				}
			}
		}
	}
}
