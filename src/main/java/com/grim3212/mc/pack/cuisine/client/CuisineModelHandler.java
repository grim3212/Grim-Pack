package com.grim3212.mc.pack.cuisine.client;

import com.grim3212.mc.pack.core.client.RenderHelper;
import com.grim3212.mc.pack.cuisine.block.CuisineBlocks;
import com.grim3212.mc.pack.cuisine.config.CuisineConfig;
import com.grim3212.mc.pack.cuisine.item.CuisineItems;
import com.grim3212.mc.pack.cuisine.item.ItemSodaBottle;

import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CuisineModelHandler {

	@SubscribeEvent
	public void registerModels(ModelRegistryEvent evt) {
		if (CuisineConfig.subpartDairy || CuisineConfig.subpartPie) {
			RenderHelper.renderItem(CuisineItems.knife);
		}

		if (CuisineConfig.subpartHealth) {
			RenderHelper.renderItem(CuisineItems.powered_sweets);
			RenderHelper.renderItem(CuisineItems.powered_sugar);
			RenderHelper.renderItem(CuisineItems.sweets);
			RenderHelper.renderItem(CuisineItems.bandage);
			RenderHelper.renderItem(CuisineItems.healthpack_super);
			RenderHelper.renderItem(CuisineItems.healthpack);
		}

		if (CuisineConfig.subpartDragonFruit) {
			RenderHelper.renderItem(CuisineItems.dragon_fruit);
		}

		if (CuisineConfig.subpartDairy) {
			RenderHelper.renderItem(CuisineItems.mixer);
			RenderHelper.renderItem(CuisineItems.butter);
			RenderHelper.renderItem(CuisineItems.cheese);
			RenderHelper.renderItem(CuisineItems.bread_slice);
			RenderHelper.renderItem(CuisineItems.cheese_burger);
			RenderHelper.renderItem(CuisineItems.hot_cheese);
			RenderHelper.renderItem(CuisineItems.eggs_unmixed);
			RenderHelper.renderItem(CuisineItems.eggs_mixed);
			RenderHelper.renderItem(CuisineItems.eggs_cooked);
			RenderHelper.renderBlock(CuisineBlocks.cheese_block);
			RenderHelper.renderBlock(CuisineBlocks.butter_churn);
			RenderHelper.renderBlock(CuisineBlocks.cheese_maker);
		}

		if (CuisineConfig.subpartChocolate) {
			RenderHelper.renderItem(CuisineItems.cocoa_fruit);
			RenderHelper.renderItem(CuisineItems.cocoa_dust);
			RenderHelper.renderItem(CuisineItems.chocolate_bowl);
			RenderHelper.renderItem(CuisineItems.chocolate_bowl_hot);
			RenderHelper.renderItem(CuisineItems.chocolate_bar);
			RenderHelper.renderItem(CuisineItems.chocolate_bar_wrapped);
			RenderHelper.renderItem(CuisineItems.chocolate_ball);
			RenderHelper.renderItem(CuisineItems.wrapper);
			RenderHelper.renderBlockNormal(CuisineBlocks.cocoa_block);
			RenderHelper.renderVariantForge(CuisineBlocks.cocoa_tree_sapling, "stage=0", "stage=1");
			RenderHelper.renderBlock(CuisineBlocks.chocolate_bar_mould);
			RenderHelper.renderBlock(CuisineBlocks.chocolate_cake);
			RenderHelper.renderBlock(CuisineBlocks.chocolate_block);
		}

		if (CuisineConfig.subpartPie) {
			RenderHelper.renderItem(CuisineItems.dough);
			RenderHelper.renderItem(CuisineItems.pan);
			RenderHelper.renderItem(CuisineItems.raw_empty_pie);
			RenderHelper.renderItem(CuisineItems.raw_apple_pie);
			RenderHelper.renderItem(CuisineItems.raw_chocolate_pie);
			RenderHelper.renderItem(CuisineItems.raw_pork_pie);
			RenderHelper.renderItem(CuisineItems.raw_pumpkin_pie);
			RenderHelper.renderItem(CuisineItems.raw_melon_pie);
			RenderHelper.renderItem(CuisineItems.pumpkin_slice);
			RenderHelper.renderBlock(CuisineBlocks.apple_pie);
			RenderHelper.renderBlock(CuisineBlocks.melon_pie);
			RenderHelper.renderBlock(CuisineBlocks.pumpkin_pie);
			RenderHelper.renderBlock(CuisineBlocks.chocolate_pie);
			RenderHelper.renderBlock(CuisineBlocks.pork_pie);
		}

		if (CuisineConfig.subpartSoda)
			RenderHelper.renderVariantForge(CuisineItems.soda, ItemSodaBottle.sodaTypes);
	}
}
