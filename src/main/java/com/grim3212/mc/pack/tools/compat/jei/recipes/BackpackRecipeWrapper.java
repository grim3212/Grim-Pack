package com.grim3212.mc.pack.tools.compat.jei.recipes;

import java.util.Arrays;
import java.util.List;

import com.google.common.collect.Lists;
import com.grim3212.mc.pack.tools.items.ItemBackpack;
import com.grim3212.mc.pack.tools.items.ToolsItems;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.BlankRecipeWrapper;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class BackpackRecipeWrapper extends BlankRecipeWrapper {

	private final List<List<ItemStack>> inputs;
	private final ItemStack output;

	public BackpackRecipeWrapper(int meta) {
		inputs = Lists.newArrayList();

		if (meta == -1) {
			List<ItemStack> backpacks = Lists.newArrayList();
			for (int color = 0; color < 16; color++) {
				ItemStack backpackStack = new ItemStack(ToolsItems.backpack);
				ItemBackpack.setColor(backpackStack, color);
				backpacks.add(backpackStack);
			}
			inputs.add(backpacks);
			inputs.add(Arrays.asList(new ItemStack(Items.WATER_BUCKET)));

			output = new ItemStack(ToolsItems.backpack);
		} else {
			String[] dyes = { "Black", "Red", "Green", "Brown", "Blue", "Purple", "Cyan", "LightGray", "Gray", "Pink", "Lime", "Yellow", "LightBlue", "Magenta", "Orange", "White" };
			inputs.add(OreDictionary.getOres("dye" + dyes[meta]));
			inputs.add(Arrays.asList(new ItemStack(ToolsItems.backpack)));

			ItemStack backpackStack = new ItemStack(ToolsItems.backpack);
			ItemBackpack.setColor(backpackStack, meta);
			output = backpackStack;
		}
	}

	@Override
	public void getIngredients(IIngredients ingredients) {
		ingredients.setInputLists(ItemStack.class, inputs);
		ingredients.setOutput(ItemStack.class, output);
	}
}