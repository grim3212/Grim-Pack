package com.grim3212.mc.pack.tools.items;

import java.util.List;

import com.grim3212.mc.pack.GrimPack;
import com.grim3212.mc.pack.core.config.CoreConfig;
import com.grim3212.mc.pack.core.item.ItemManualArmor;
import com.grim3212.mc.pack.core.item.ItemManualAxe;
import com.grim3212.mc.pack.core.item.ItemManualHoe;
import com.grim3212.mc.pack.core.item.ItemManualPage;
import com.grim3212.mc.pack.core.item.ItemManualPickaxe;
import com.grim3212.mc.pack.core.item.ItemManualSpade;
import com.grim3212.mc.pack.core.item.ItemManualSword;
import com.grim3212.mc.pack.core.part.IPartItems;
import com.grim3212.mc.pack.core.util.OreDictionaryHelper;
import com.grim3212.mc.pack.core.util.RecipeHelper;
import com.grim3212.mc.pack.core.util.Utils;
import com.grim3212.mc.pack.industry.item.IndustryItems;
import com.grim3212.mc.pack.tools.GrimTools;
import com.grim3212.mc.pack.tools.config.ToolsConfig;
import com.grim3212.mc.pack.tools.items.ItemBetterBucket.BucketType;
import com.grim3212.mc.pack.tools.util.BackpackRecipeHandler;
import com.grim3212.mc.pack.tools.util.ChiselRegistry;
import com.grim3212.mc.pack.tools.util.DispenseBehaviors;
import com.grim3212.mc.pack.tools.util.EnumSpearType;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.RecipeSorter;
import net.minecraftforge.oredict.RecipeSorter.Category;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class ToolsItems implements IPartItems {

	public static Item backpack;
	public static Item portable_workbench;
	public static Item tomahawk;
	public static Item grip;
	public static Item throwing_knife;
	public static Item loaded_knife;
	public static Item unloaded_knife;
	public static Item ammo_part;
	public static Item button_part;
	public static Item spring_part;
	public static Item rod_part;
	public static Item black_diamond;
	public static Item black_diamond_pickaxe;
	public static Item black_diamond_shovel;
	public static Item black_diamond_axe;
	public static Item black_diamond_hoe;
	public static Item black_diamond_sword;
	public static Item black_diamond_helmet;
	public static Item black_diamond_chestplate;
	public static Item black_diamond_leggings;
	public static Item black_diamond_boots;
	public static Item wooden_bucket;
	public static Item stone_bucket;
	public static Item golden_bucket;
	public static Item diamond_bucket;
	public static Item obsidian_bucket;
	public static Item wooden_milk_bucket;
	public static Item stone_milk_bucket;
	public static Item golden_milk_bucket;
	public static Item diamond_milk_bucket;
	public static Item obsidian_milk_bucket;
	public static Item iron_chisel;
	public static Item iron_ore_item;
	public static Item gold_ore_item;
	public static Item diamond_chisel;
	public static Item extinguisher;
	public static Item wood_hammer;
	public static Item stone_hammer;
	public static Item gold_hammer;
	public static Item iron_hammer;
	public static Item diamond_hammer;
	public static Item machete_gold;
	public static Item machete_iron;
	public static Item machete_diamond;
	public static Item machete_stone;
	public static Item machete_wood;
	public static Item machete_slime;
	public static Item building_wand;
	public static Item breaking_wand;
	public static Item mining_wand;
	public static Item reinforced_building_wand;
	public static Item reinforced_breaking_wand;
	public static Item reinforced_mining_wand;
	public static Item diamond_multi_tool;
	public static Item wooden_multi_tool;
	public static Item stone_multi_tool;
	public static Item iron_multi_tool;
	public static Item golden_multi_tool;
	public static Item obsidian_multi_tool;
	public static Item black_diamond_multi_tool;
	public static Item steel_multi_tool;
	public static Item pokeball;
	public static Item powerstaff;
	public static Item energy_canister;
	public static Item element_115;
	public static Item empty_energy_canister;
	public static Item ray_gun;
	public static Item advanced_empty_energy_canister;
	public static Item advanced_energy_canister;
	public static Item advanced_ray_gun;
	public static Item dark_iron_ingot;
	public static Item sling_shot;
	public static Item sling_pellet;
	public static Item spear;
	public static Item iron_spear;
	public static Item diamond_spear;
	public static Item explosive_spear;
	public static Item fire_spear;
	public static Item slime_spear;
	public static Item light_spear;
	public static Item lightning_spear;
	public static Item ultimate_fist;
	public static Item mask;
	public static Item boomerang;
	public static Item diamond_boomerang;

	public static ToolMaterial multitool = EnumHelper.addToolMaterial("multitool", 4, 5122, 15F, 5F, 20);
	public static ToolMaterial blackdiamond = EnumHelper.addToolMaterial("black_diamond", 4, 5122, 15F, 5F, 20);
	public static ToolMaterial obsidianToolMaterial = EnumHelper.addToolMaterial("obsidian", 3, 3333, 9.5F, 7f, 14);
	public static ArmorMaterial masks = EnumHelper.addArmorMaterial("mask", GrimPack.modID + ":masks", 5, new int[] { 1, 3, 2, 1 }, 15, SoundEvents.BLOCK_CLOTH_PLACE, 0.0F);
	public static ArmorMaterial blackarmor = EnumHelper.addArmorMaterial("blackarmor", GrimPack.modID + ":blackarmor", 35, new int[] { 3, 6, 8, 3 }, 20, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.5F);

	@Override
	public void initItems() {
		boomerang = (new ItemBoomerang()).setUnlocalizedName("boomerang").setCreativeTab(GrimTools.INSTANCE.getCreativeTab());
		diamond_boomerang = (new ItemDiamondBoomerang()).setUnlocalizedName("diamond_boomerang").setCreativeTab(GrimTools.INSTANCE.getCreativeTab());
		backpack = new ItemBackpack().setUnlocalizedName("backpack").setCreativeTab(GrimTools.INSTANCE.getCreativeTab());
		portable_workbench = new ItemPortableWorkbench().setUnlocalizedName("portable_workbench").setCreativeTab(GrimTools.INSTANCE.getCreativeTab());
		loaded_knife = (new ItemBallisticKnife(true, false)).setUnlocalizedName("loaded_knife").setCreativeTab(GrimTools.INSTANCE.getCreativeTab());
		unloaded_knife = (new ItemBallisticKnife(false, false)).setMaxStackSize(1).setUnlocalizedName("unloaded_knife").setCreativeTab(GrimTools.INSTANCE.getCreativeTab());
		ammo_part = (new ItemBallisticKnife(false, true)).setMaxStackSize(64).setUnlocalizedName("ammo_part").setCreativeTab(GrimTools.INSTANCE.getCreativeTab());
		button_part = (new ItemManualPage("tools:ballistic.part3")).setMaxStackSize(1).setUnlocalizedName("button_part").setCreativeTab(GrimTools.INSTANCE.getCreativeTab());
		spring_part = (new ItemManualPage("tools:ballistic.part2")).setMaxStackSize(1).setUnlocalizedName("spring_part").setCreativeTab(GrimTools.INSTANCE.getCreativeTab());
		rod_part = (new ItemManualPage("tools:ballistic.part4")).setMaxStackSize(64).setUnlocalizedName("rod_part").setCreativeTab(GrimTools.INSTANCE.getCreativeTab());
		black_diamond = (new ItemManualPage("tools:black.cookOre")).setUnlocalizedName("black_diamond").setCreativeTab(GrimTools.INSTANCE.getCreativeTab());
		black_diamond_boots = (new ItemManualArmor(blackarmor, 4, EntityEquipmentSlot.FEET, "tools:tools.armor")).setUnlocalizedName("black_diamond_boots").setCreativeTab(GrimTools.INSTANCE.getCreativeTab());
		black_diamond_leggings = (new ItemManualArmor(blackarmor, 4, EntityEquipmentSlot.LEGS, "tools:tools.armor")).setUnlocalizedName("black_diamond_leggings").setCreativeTab(GrimTools.INSTANCE.getCreativeTab());
		black_diamond_chestplate = (new ItemManualArmor(blackarmor, 4, EntityEquipmentSlot.CHEST, "tools:tools.armor")).setUnlocalizedName("black_diamond_chestplate").setCreativeTab(GrimTools.INSTANCE.getCreativeTab());
		black_diamond_helmet = (new ItemManualArmor(blackarmor, 4, EntityEquipmentSlot.HEAD, "tools:tools.armor")).setUnlocalizedName("black_diamond_helmet").setCreativeTab(GrimTools.INSTANCE.getCreativeTab());
		black_diamond_sword = (new ItemManualSword(blackdiamond, "tools:tools.tools")).setUnlocalizedName("black_diamond_sword").setCreativeTab(GrimTools.INSTANCE.getCreativeTab());
		black_diamond_hoe = (new ItemManualHoe(blackdiamond, "tools:tools.tools")).setUnlocalizedName("black_diamond_hoe").setCreativeTab(GrimTools.INSTANCE.getCreativeTab());
		black_diamond_axe = (new ItemManualAxe(blackdiamond, 8.0f, -2.8f, "tools:tools.tools")).setUnlocalizedName("black_diamond_axe").setCreativeTab(GrimTools.INSTANCE.getCreativeTab());
		black_diamond_shovel = (new ItemManualSpade(blackdiamond, "tools:tools.tools")).setUnlocalizedName("black_diamond_shovel").setCreativeTab(GrimTools.INSTANCE.getCreativeTab());
		black_diamond_pickaxe = (new ItemManualPickaxe(blackdiamond, "tools:tools.tools")).setUnlocalizedName("black_diamond_pickaxe").setCreativeTab(GrimTools.INSTANCE.getCreativeTab());
		wooden_bucket = new ItemBetterBucket(1, 0, 1000f, new ItemStack(Items.STICK, 3), BucketType.wood).setUnlocalizedName("wooden_bucket").setCreativeTab(GrimTools.INSTANCE.getCreativeTab());
		stone_bucket = new ItemBetterBucket(1, 0, new ItemStack(Blocks.COBBLESTONE, 3), BucketType.stone).setUnlocalizedName("stone_bucket").setCreativeTab(GrimTools.INSTANCE.getCreativeTab());
		golden_bucket = new ItemBetterBucket(4, 0, BucketType.gold).setUnlocalizedName("golden_bucket").setCreativeTab(GrimTools.INSTANCE.getCreativeTab());
		diamond_bucket = new ItemBetterBucket(16, 1, BucketType.diamond).setUnlocalizedName("diamond_bucket").setCreativeTab(GrimTools.INSTANCE.getCreativeTab());
		obsidian_bucket = new ItemBetterBucket(32, 2, true, BucketType.obsidian).setUnlocalizedName("obsidian_bucket").setCreativeTab(GrimTools.INSTANCE.getCreativeTab());
		wooden_milk_bucket = new ItemBetterMilkBucket((ItemBetterBucket) wooden_bucket).setUnlocalizedName("wooden_milk_bucket").setCreativeTab(GrimTools.INSTANCE.getCreativeTab());
		stone_milk_bucket = new ItemBetterMilkBucket((ItemBetterBucket) stone_bucket).setUnlocalizedName("stone_milk_bucket").setCreativeTab(GrimTools.INSTANCE.getCreativeTab());
		golden_milk_bucket = new ItemBetterMilkBucket((ItemBetterBucket) golden_bucket).setUnlocalizedName("golden_milk_bucket").setCreativeTab(GrimTools.INSTANCE.getCreativeTab());
		diamond_milk_bucket = new ItemBetterMilkBucket((ItemBetterBucket) diamond_bucket).setUnlocalizedName("diamond_milk_bucket").setCreativeTab(GrimTools.INSTANCE.getCreativeTab());
		obsidian_milk_bucket = new ItemBetterMilkBucket((ItemBetterBucket) obsidian_bucket).setUnlocalizedName("obsidian_milk_bucket").setCreativeTab(GrimTools.INSTANCE.getCreativeTab());
		throwing_knife = (new ItemKnife()).setUnlocalizedName("throwing_knife").setCreativeTab(GrimTools.INSTANCE.getCreativeTab());
		tomahawk = (new ItemTomahawk()).setUnlocalizedName("tomahawk").setCreativeTab(GrimTools.INSTANCE.getCreativeTab());
		grip = (new ItemManualPage("tools:ballistic.grip")).setUnlocalizedName("grip").setCreativeTab(GrimTools.INSTANCE.getCreativeTab());
		diamond_chisel = (new ItemChisel(4)).setUnlocalizedName("diamond_chisel");
		gold_ore_item = (new ItemManualPage("tools:chisels.ore")).setUnlocalizedName("gold_ore_item").setCreativeTab(GrimTools.INSTANCE.getCreativeTab());
		iron_ore_item = (new ItemManualPage("tools:chisels.ore")).setUnlocalizedName("iron_ore_item").setCreativeTab(GrimTools.INSTANCE.getCreativeTab());
		iron_chisel = (new ItemChisel(1)).setUnlocalizedName("iron_chisel");
		extinguisher = new ItemExtinguisher().setUnlocalizedName("extinguisher").setCreativeTab(GrimTools.INSTANCE.getCreativeTab());
		wood_hammer = (new ItemHammer(ToolMaterial.WOOD)).setUnlocalizedName("wood_hammer");
		stone_hammer = (new ItemHammer(ToolMaterial.STONE)).setUnlocalizedName("stone_hammer");
		gold_hammer = (new ItemHammer(ToolMaterial.GOLD)).setUnlocalizedName("gold_hammer");
		iron_hammer = (new ItemHammer(ToolMaterial.IRON)).setUnlocalizedName("iron_hammer");
		diamond_hammer = (new ItemHammer(ToolMaterial.DIAMOND)).setUnlocalizedName("diamond_hammer");
		machete_gold = (new ItemMachete(ToolMaterial.GOLD)).setUnlocalizedName("machete_gold").setMaxDamage(32);
		machete_iron = (new ItemMachete(ToolMaterial.IRON)).setUnlocalizedName("machete_iron").setMaxDamage(250);
		machete_diamond = (new ItemMachete(ToolMaterial.DIAMOND)).setUnlocalizedName("machete_diamond").setMaxDamage(1561);
		machete_stone = (new ItemMachete(ToolMaterial.STONE)).setUnlocalizedName("machete_stone").setMaxDamage(131);
		machete_wood = (new ItemMachete(ToolMaterial.WOOD)).setUnlocalizedName("machete_wood").setMaxDamage(59);
		machete_slime = (new ItemMachete(ToolMaterial.IRON)).setUnlocalizedName("machete_slime").setMaxDamage(250);
		building_wand = (new ItemBuildingWand(false)).setUnlocalizedName("building_wand");
		breaking_wand = (new ItemBreakingWand(false)).setUnlocalizedName("breaking_wand");
		mining_wand = (new ItemMiningWand(false)).setUnlocalizedName("mining_wand");
		reinforced_building_wand = (new ItemBuildingWand(true)).setUnlocalizedName("reinforced_building_wand");
		reinforced_breaking_wand = (new ItemBreakingWand(true)).setUnlocalizedName("reinforced_breaking_wand");
		reinforced_mining_wand = (new ItemMiningWand(true)).setUnlocalizedName("reinforced_mining_wand");
		diamond_multi_tool = (new ItemMultiTool(addMultiToolMaterial(ToolMaterial.DIAMOND))).setUnlocalizedName("diamond_multi_tool");
		wooden_multi_tool = (new ItemMultiTool(addMultiToolMaterial(ToolMaterial.WOOD))).setUnlocalizedName("wooden_multi_tool");
		stone_multi_tool = (new ItemMultiTool(addMultiToolMaterial(ToolMaterial.STONE))).setUnlocalizedName("stone_multi_tool");
		iron_multi_tool = (new ItemMultiTool(addMultiToolMaterial(ToolMaterial.IRON))).setUnlocalizedName("iron_multi_tool");
		golden_multi_tool = (new ItemMultiTool(addMultiToolMaterial(ToolMaterial.GOLD))).setUnlocalizedName("golden_multi_tool");
		obsidian_multi_tool = (new ItemMultiTool(addMultiToolMaterial(obsidianToolMaterial))).setUnlocalizedName("obsidian_multi_tool");
		black_diamond_multi_tool = (new ItemMultiTool(addMultiToolMaterial(blackdiamond))).setUnlocalizedName("black_diamond_multi_tool");
		pokeball = (new ItemPokeball()).setUnlocalizedName("pokeball");
		powerstaff = (new ItemPowerStaff()).setUnlocalizedName("powerstaff").setCreativeTab(GrimTools.INSTANCE.getCreativeTab());
		energy_canister = (new ItemManualPage("tools:raygun.canisters")).setUnlocalizedName("energy_canister").setCreativeTab(GrimTools.INSTANCE.getCreativeTab());
		element_115 = (new ItemManualPage("tools:raygun.element")).setUnlocalizedName("element_115").setCreativeTab(GrimTools.INSTANCE.getCreativeTab());
		empty_energy_canister = (new ItemManualPage("tools:raygun.canisters")).setUnlocalizedName("empty_energy_canister").setCreativeTab(GrimTools.INSTANCE.getCreativeTab());
		ray_gun = (new ItemRayg()).setUnlocalizedName("ray_gun").setCreativeTab(GrimTools.INSTANCE.getCreativeTab());
		advanced_empty_energy_canister = (new ItemManualPage("tools:raygun.advCanisters")).setUnlocalizedName("advanced_empty_energy_canister").setCreativeTab(GrimTools.INSTANCE.getCreativeTab());
		advanced_energy_canister = (new ItemManualPage("tools:raygun.advCanisters")).setUnlocalizedName("advanced_energy_canister").setCreativeTab(GrimTools.INSTANCE.getCreativeTab());
		advanced_ray_gun = (new ItemAdvRayg()).setUnlocalizedName("advanced_ray_gun").setCreativeTab(GrimTools.INSTANCE.getCreativeTab());
		dark_iron_ingot = (new ItemManualPage("tools:raygun.darkIron")).setUnlocalizedName("dark_iron_ingot").setCreativeTab(GrimTools.INSTANCE.getCreativeTab());
		sling_shot = (new ItemSlingshot()).setUnlocalizedName("sling_shot").setCreativeTab(GrimTools.INSTANCE.getCreativeTab());
		sling_pellet = (new ItemManualPage("tools:sling.pellets")).setUnlocalizedName("sling_pellet").setCreativeTab(GrimTools.INSTANCE.getCreativeTab());
		spear = (new ItemSpear(EnumSpearType.STONE)).setUnlocalizedName("spear").setCreativeTab(GrimTools.INSTANCE.getCreativeTab());
		iron_spear = (new ItemSpear(EnumSpearType.IRON)).setUnlocalizedName("iron_spear").setCreativeTab(GrimTools.INSTANCE.getCreativeTab());
		diamond_spear = (new ItemSpear(EnumSpearType.DIAMOND)).setUnlocalizedName("diamond_spear").setCreativeTab(GrimTools.INSTANCE.getCreativeTab());
		explosive_spear = (new ItemSpear(EnumSpearType.EXPLOSIVE)).setUnlocalizedName("explosive_spear").setCreativeTab(GrimTools.INSTANCE.getCreativeTab());
		fire_spear = (new ItemSpear(EnumSpearType.FIRE)).setUnlocalizedName("fire_spear").setCreativeTab(GrimTools.INSTANCE.getCreativeTab());
		slime_spear = (new ItemSpear(EnumSpearType.SLIME)).setUnlocalizedName("slime_spear").setCreativeTab(GrimTools.INSTANCE.getCreativeTab());
		light_spear = (new ItemSpear(EnumSpearType.LIGHT)).setUnlocalizedName("light_spear").setCreativeTab(GrimTools.INSTANCE.getCreativeTab());
		lightning_spear = (new ItemSpear(EnumSpearType.LIGHTNING)).setUnlocalizedName("lightning_spear").setCreativeTab(GrimTools.INSTANCE.getCreativeTab());
		ultimate_fist = (new ItemUltimateFist()).setUnlocalizedName("ultimate_fist").setCreativeTab(GrimTools.INSTANCE.getCreativeTab());
		mask = (new ItemMaskArmor(masks, 3)).setUnlocalizedName("mask");

		Utils.registerItem(boomerang, "boomerang");
		Utils.registerItem(diamond_boomerang, "diamond_boomerang");
		Utils.registerItem(mask, "mask");
		Utils.registerItem(ultimate_fist, "ultimate_fist");
		Utils.registerItem(spear, "spear");
		Utils.registerItem(iron_spear, "iron_spear");
		Utils.registerItem(diamond_spear, "diamond_spear");
		Utils.registerItem(explosive_spear, "explosive_spear");
		Utils.registerItem(fire_spear, "fire_spear");
		Utils.registerItem(slime_spear, "slime_spear");
		Utils.registerItem(light_spear, "light_spear");
		Utils.registerItem(lightning_spear, "lightning_spear");
		Utils.registerItem(sling_shot, "sling_shot");
		Utils.registerItem(sling_pellet, "sling_pellet");
		Utils.registerItem(element_115, "element_115");
		Utils.registerItem(energy_canister, "energy_canister");
		Utils.registerItem(empty_energy_canister, "empty_energy_canister");
		Utils.registerItem(ray_gun, "ray_gun");
		Utils.registerItem(advanced_empty_energy_canister, "advanced_empty_energy_canister");
		Utils.registerItem(advanced_energy_canister, "advanced_energy_canister");
		Utils.registerItem(advanced_ray_gun, "advanced_ray_gun");
		Utils.registerItem(dark_iron_ingot, "dark_iron_ingot");
		Utils.registerItem(powerstaff, "powerstaff");
		Utils.registerItem(pokeball, "pokeball");
		Utils.registerItem(diamond_multi_tool, "diamond_multi_tool");
		Utils.registerItem(wooden_multi_tool, "wooden_multi_tool");
		Utils.registerItem(stone_multi_tool, "stone_multi_tool");
		Utils.registerItem(iron_multi_tool, "iron_multi_tool");
		Utils.registerItem(golden_multi_tool, "golden_multi_tool");
		Utils.registerItem(obsidian_multi_tool, "obsidian_multi_tool");
		Utils.registerItem(black_diamond_multi_tool, "black_diamond_multi_tool");
		Utils.registerItem(building_wand, "building_wand");
		Utils.registerItem(breaking_wand, "breaking_wand");
		Utils.registerItem(mining_wand, "mining_wand");
		Utils.registerItem(reinforced_building_wand, "reinforced_building_wand");
		Utils.registerItem(reinforced_breaking_wand, "reinforced_breaking_wand");
		Utils.registerItem(reinforced_mining_wand, "reinforced_mining_wand");
		Utils.registerItem(machete_gold, "machete_gold");
		Utils.registerItem(machete_iron, "machete_iron");
		Utils.registerItem(machete_diamond, "machete_diamond");
		Utils.registerItem(machete_stone, "machete_stone");
		Utils.registerItem(machete_wood, "machete_wood");
		Utils.registerItem(machete_slime, "machete_slime");
		Utils.registerItem(wood_hammer, "wood_hammer");
		Utils.registerItem(stone_hammer, "stone_hammer");
		Utils.registerItem(gold_hammer, "gold_hammer");
		Utils.registerItem(iron_hammer, "iron_hammer");
		Utils.registerItem(diamond_hammer, "diamond_hammer");
		Utils.registerItem(extinguisher, "extinguisher");
		Utils.registerItem(iron_chisel, "iron_chisel");
		Utils.registerItem(diamond_chisel, "diamond_chisel");
		Utils.registerItem(gold_ore_item, "gold_ore_item");
		Utils.registerItem(iron_ore_item, "iron_ore_item");
		Utils.registerItem(throwing_knife, "throwing_knife");
		Utils.registerItem(grip, "grip");
		Utils.registerItem(tomahawk, "tomahawk");
		Utils.registerItem(wooden_bucket, "wooden_bucket");
		Utils.registerItem(stone_bucket, "stone_bucket");
		Utils.registerItem(golden_bucket, "golden_bucket");
		Utils.registerItem(diamond_bucket, "diamond_bucket");
		Utils.registerItem(obsidian_bucket, "obsidian_bucket");
		Utils.registerItem(wooden_milk_bucket, "wooden_milk_bucket");
		Utils.registerItem(stone_milk_bucket, "stone_milk_bucket");
		Utils.registerItem(golden_milk_bucket, "golden_milk_bucket");
		Utils.registerItem(diamond_milk_bucket, "diamond_milk_bucket");
		Utils.registerItem(obsidian_milk_bucket, "obsidian_milk_bucket");
		Utils.registerItem(loaded_knife, "loaded_knife");
		Utils.registerItem(unloaded_knife, "unloaded_knife");
		Utils.registerItem(ammo_part, "ammo_part");
		Utils.registerItem(button_part, "button_part");
		Utils.registerItem(spring_part, "spring_part");
		Utils.registerItem(rod_part, "rod_part");
		Utils.registerItem(backpack, "backpack");
		Utils.registerItem(portable_workbench, "portable_workbench");
		Utils.registerItem(black_diamond_helmet, "black_diamond_helmet");
		Utils.registerItem(black_diamond_chestplate, "black_diamond_chestplate");
		Utils.registerItem(black_diamond_leggings, "black_diamond_leggings");
		Utils.registerItem(black_diamond_boots, "black_diamond_boots");
		Utils.registerItem(black_diamond_sword, "black_diamond_sword");
		Utils.registerItem(black_diamond_hoe, "black_diamond_hoe");
		Utils.registerItem(black_diamond_axe, "black_diamond_axe");
		Utils.registerItem(black_diamond_shovel, "black_diamond_shovel");
		Utils.registerItem(black_diamond_pickaxe, "black_diamond_pickaxe");
		Utils.registerItem(black_diamond, "black_diamond");

		if (CoreConfig.useIndustry) {
			steel_multi_tool = (new ItemMultiTool(addMultiToolMaterial(IndustryItems.steel))).setUnlocalizedName("steel_multi_tool");
			Utils.registerItem(steel_multi_tool, "steel_multi_tool");
		}

		// Register all of the dispenser behaviors
		DispenseBehaviors.register();
	}

	public static List<IRecipe> blackTools;
	public static List<IRecipe> blackArmor;
	public static List<IRecipe> chisels;
	public static IRecipe extinguisherRecipe;
	public static IRecipe extinguisherRefillRecipe;
	public static List<IRecipe> hammers;
	public static List<IRecipe> machetes;
	public static List<IRecipe> regular;
	public static List<IRecipe> reinforced;
	public static List<IRecipe> tools;
	public static List<IRecipe> advCanisters;
	public static List<IRecipe> basicCanisters;
	public static List<IRecipe> pellets;
	public static List<IRecipe> basics;
	public static List<IRecipe> specials;
	public static IRecipe emptyRecipe;
	public static List<IRecipe> mobs;

	@Override
	public void addRecipes() {
		// Set repair items
		obsidianToolMaterial.setRepairItem(new ItemStack(Blocks.OBSIDIAN));
		blackdiamond.setRepairItem(new ItemStack(black_diamond));

		OreDictionary.registerOre("ingotDarkIron", dark_iron_ingot);
		OreDictionary.registerOre("bucketMilk", Items.MILK_BUCKET);
		OreDictionary.registerOre("bucketMilk", wooden_milk_bucket);
		OreDictionary.registerOre("bucketMilk", stone_milk_bucket);
		OreDictionary.registerOre("bucketMilk", golden_milk_bucket);
		OreDictionary.registerOre("bucketMilk", diamond_milk_bucket);
		OreDictionary.registerOre("bucketMilk", obsidian_milk_bucket);
		OreDictionary.registerOre("ingotMetal", Items.IRON_INGOT);
		OreDictionary.registerOre("ingotMetal", Items.GOLD_INGOT);
		OreDictionary.registerOre("rod", rod_part);
		OreDictionary.registerOre("rodMetal", rod_part);
		OreDictionary.registerOre("stickMetal", rod_part);

		// Register chiseleable blocks
		ChiselRegistry.registerBlock(Blocks.DIAMOND_ORE, Blocks.STONE, Items.DIAMOND, 1, 0);
		ChiselRegistry.registerBlock(Blocks.EMERALD_ORE, Blocks.STONE, Items.EMERALD, 1, 0);
		ChiselRegistry.registerBlock(Blocks.QUARTZ_ORE, Blocks.NETHERRACK, Items.QUARTZ, 1, 0);
		ChiselRegistry.registerBlock(Blocks.GOLD_ORE, Blocks.STONE, gold_ore_item, 1, 0);
		ChiselRegistry.registerBlock(Blocks.IRON_ORE, Blocks.STONE, iron_ore_item, 1, 0);
		ChiselRegistry.registerBlock(Blocks.REDSTONE_ORE, Blocks.STONE, Items.REDSTONE, 4, 0);
		ChiselRegistry.registerBlock(Blocks.LIT_REDSTONE_ORE, Blocks.STONE, Items.REDSTONE, 4, 0);
		ChiselRegistry.registerBlock(Blocks.COAL_ORE, Blocks.STONE, Items.COAL, 1, 0);
		ChiselRegistry.registerBlock(Blocks.LAPIS_ORE, Blocks.STONE, Items.DYE, 3, 4);

		// Replace milk buckets
		OreDictionaryHelper.replaceRecipes(new ItemStack(Items.MILK_BUCKET), "bucketMilk", null);

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(boomerang, 1), new Object[] { "XX ", "X  ", "XX ", 'X', "plankWood" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(diamond_boomerang, 1), new Object[] { "XX ", "XY ", "XX ", 'X', "gemDiamond", 'Y', boomerang }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(backpack, 1), new Object[] { "LLS", "LIS", "LLL", 'L', "leather", 'S', "string", 'I', "ingotIron" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(portable_workbench, 1), new Object[] { "III", "IWI", "III", 'W', "workbench", 'I', "ingotIron" }));

		CraftingManager.getInstance().getRecipeList().add(new BackpackRecipeHandler());
		RecipeSorter.register("Backpack_Recipes", BackpackRecipeHandler.class, Category.SHAPELESS, "after:grim3212core");

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(iron_chisel, 1), new Object[] { " B", "I ", 'B', "ingotIron", 'I', "rodMetal" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(iron_chisel, 1), new Object[] { " B", "I ", 'B', "ingotIron", 'I', "stickMetal" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(diamond_chisel, 1), new Object[] { " B", "I ", 'B', "gemDiamond", 'I', "rodMetal" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(diamond_chisel, 1), new Object[] { " B", "I ", 'B', "gemDiamond", 'I', "stickMetal" }));
		chisels = RecipeHelper.getLatestIRecipes(4);

		GameRegistry.addSmelting(iron_ore_item, new ItemStack(Items.IRON_INGOT), 0.4F);
		GameRegistry.addSmelting(gold_ore_item, new ItemStack(Items.GOLD_INGOT), 1F);

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(tomahawk, 1), new Object[] { "XX ", "XY ", " Y ", 'X', "ingotIron", 'Y', grip }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(throwing_knife, 4), new Object[] { "Y", "X", 'Y', "ingotIron", 'X', grip }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(grip, 2), new Object[] { "Y", "Y", 'Y', "leather" }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ammo_part, 1), new Object[] { "#  ", " # ", "  !", '#', "ingotIron", '!', rod_part }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(spring_part, 1), new Object[] { "#  ", " ! ", "  #", '#', "ingotIron", '!', "string" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(button_part, 1), new Object[] { " # ", "#!#", " # ", '#', "dustRedstone", '!', Blocks.STONE_BUTTON }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(unloaded_knife, 1), new Object[] { " #", "! ", " @", '#', button_part, '!', spring_part, '@', grip }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(rod_part, 2), new Object[] { "#", "#", '#', "ingotMetal" }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(black_diamond_pickaxe, 1), new Object[] { "###", " X ", " X ", 'X', "stickWood", '#', black_diamond }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(black_diamond_sword, 1), new Object[] { "#", "#", "X", 'X', "stickWood", '#', black_diamond }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(black_diamond_axe, 1), new Object[] { "## ", "#X ", " X ", 'X', "stickWood", '#', black_diamond }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(black_diamond_hoe, 1), new Object[] { "## ", " X ", " X ", 'X', "stickWood", '#', black_diamond }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(black_diamond_shovel, 1), new Object[] { "#", "X", "X", 'X', "stickWood", '#', black_diamond }));
		blackTools = RecipeHelper.getLatestIRecipes(5);

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(black_diamond_helmet, 1), new Object[] { "###", "# #", '#', black_diamond }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(black_diamond_chestplate, 1), new Object[] { "# #", "###", "###", '#', black_diamond }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(black_diamond_leggings, 1), new Object[] { "###", "# #", "# #", '#', black_diamond }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(black_diamond_boots, 1), new Object[] { "# #", "# #", '#', black_diamond }));
		blackArmor = RecipeHelper.getLatestIRecipes(4);

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(wooden_bucket, 1), new Object[] { "# #", " # ", '#', "slabWood" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(stone_bucket, 1), new Object[] { "# #", " # ", '#', "cobblestone" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(golden_bucket, 1), new Object[] { "# #", " # ", '#', "ingotGold" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(diamond_bucket, 1), new Object[] { "# #", " # ", '#', "gemDiamond" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(obsidian_bucket, 1), new Object[] { "# #", " # ", '#', "obsidian" }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(extinguisher, 1), new Object[] { "FX ", " X ", " T ", 'X', Items.WATER_BUCKET, 'F', "dustRedstone", 'T', "ingotIron" }));
		extinguisherRecipe = RecipeHelper.getLatestIRecipe();
		GameRegistry.addShapelessRecipe(new ItemStack(extinguisher, 1), new Object[] { Items.WATER_BUCKET, Items.WATER_BUCKET, new ItemStack(extinguisher, 1, 1) });
		extinguisherRefillRecipe = RecipeHelper.getLatestIRecipe();

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(wood_hammer, 1), new Object[] { "MMM", "MMM", " G ", 'M', "plankWood", 'G', "rodMetal" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(stone_hammer, 1), new Object[] { "MMM", "MMM", " G ", 'M', "cobblestone", 'G', "rodMetal" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(gold_hammer, 1), new Object[] { "MMM", "MMM", " G ", 'M', "ingotGold", 'G', "rodMetal" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(iron_hammer, 1), new Object[] { "MMM", "MMM", " G ", 'M', "ingotIron", 'G', "rodMetal" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(diamond_hammer, 1), new Object[] { "MMM", "MMM", " G ", 'M', "gemDiamond", 'G', "rodMetal" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(wood_hammer, 1), new Object[] { "MMM", "MMM", " G ", 'M', "plankWood", 'G', "stickMetal" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(stone_hammer, 1), new Object[] { "MMM", "MMM", " G ", 'M', "cobblestone", 'G', "stickMetal" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(gold_hammer, 1), new Object[] { "MMM", "MMM", " G ", 'M', "ingotGold", 'G', "stickMetal" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(iron_hammer, 1), new Object[] { "MMM", "MMM", " G ", 'M', "ingotIron", 'G', "stickMetal" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(diamond_hammer, 1), new Object[] { "MMM", "MMM", " G ", 'M', "gemDiamond", 'G', "stickMetal" }));
		hammers = RecipeHelper.getLatestIRecipes(10);

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(machete_wood, 1), new Object[] { " XX", "XX ", "#  ", 'X', "plankWood", '#', "rodMetal" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(machete_stone, 1), new Object[] { " XX", "XX ", "#  ", 'X', "cobblestone", '#', "rodMetal" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(machete_gold, 1), new Object[] { " XX", "XX ", "#  ", 'X', "ingotGold", '#', "rodMetal" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(machete_iron, 1), new Object[] { " XX", "XX ", "#  ", 'X', "ingotIron", '#', "rodMetal" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(machete_diamond, 1), new Object[] { " XX", "XX ", "#  ", 'X', "gemDiamond", '#', "rodMetal" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(machete_wood, 1), new Object[] { " XX", "XX ", "#  ", 'X', "plankWood", '#', "stickMetal" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(machete_stone, 1), new Object[] { " XX", "XX ", "#  ", 'X', "cobblestone", '#', "stickMetal" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(machete_gold, 1), new Object[] { " XX", "XX ", "#  ", 'X', "ingotGold", '#', "stickMetal" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(machete_iron, 1), new Object[] { " XX", "XX ", "#  ", 'X', "ingotIron", '#', "stickMetal" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(machete_diamond, 1), new Object[] { " XX", "XX ", "#  ", 'X', "gemDiamond", '#', "stickMetal" }));
		machetes = RecipeHelper.getLatestIRecipes(10);

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(machete_slime, 1), new Object[] { " XX", "XX ", "#  ", 'X', "slimeball", '#', "stickWood" }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(building_wand), new Object[] { "PXP", "PXP", "PXP", 'P', "plankWood", 'X', "ingotGold" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(breaking_wand), new Object[] { "PXP", "PXP", "PXP", 'P', "plankWood", 'X', "ingotIron" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(mining_wand), new Object[] { "PXP", "PXP", "PXP", 'P', "plankWood", 'X', "gemDiamond" }));
		regular = RecipeHelper.getLatestIRecipes(3);

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(reinforced_building_wand), new Object[] { "OXO", "OXO", "OXO", 'O', Blocks.OBSIDIAN, 'X', "blockGold" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(reinforced_breaking_wand), new Object[] { "OXO", "OXO", "OXO", 'O', Blocks.OBSIDIAN, 'X', "blockIron" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(reinforced_mining_wand), new Object[] { "OXO", "OXO", "OXO", 'O', Blocks.OBSIDIAN, 'X', "blockDiamond" }));
		reinforced = RecipeHelper.getLatestIRecipes(3);

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(wooden_multi_tool, 1), new Object[] { Items.WOODEN_SWORD, Items.WOODEN_PICKAXE, Items.WOODEN_SHOVEL, Items.WOODEN_HOE, Items.WOODEN_AXE, "logWood", "logWood", "logWood", "logWood" }));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(stone_multi_tool, 1), new Object[] { Items.STONE_SWORD, Items.STONE_PICKAXE, Items.STONE_SHOVEL, Items.STONE_HOE, Items.STONE_AXE, "cobblestone", "cobblestone", "cobblestone", "cobblestone" }));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(iron_multi_tool, 1), new Object[] { Items.IRON_SWORD, Items.IRON_PICKAXE, Items.IRON_SHOVEL, Items.IRON_HOE, Items.IRON_AXE, "ingotIron", "ingotIron", "ingotIron", "ingotIron" }));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(golden_multi_tool, 1), new Object[] { Items.GOLDEN_SWORD, Items.GOLDEN_PICKAXE, Items.GOLDEN_SHOVEL, Items.GOLDEN_HOE, Items.GOLDEN_AXE, "ingotGold", "ingotGold", "ingotGold", "ingotGold" }));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(diamond_multi_tool, 1), new Object[] { Items.DIAMOND_SWORD, Items.DIAMOND_PICKAXE, Items.DIAMOND_SHOVEL, Items.DIAMOND_HOE, Items.DIAMOND_AXE, "gemDiamond", "gemDiamond", "gemDiamond", "gemDiamond" }));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(obsidian_multi_tool, 1), new Object[] { diamond_multi_tool, golden_multi_tool, wooden_multi_tool, stone_multi_tool, iron_multi_tool, "obsidian", "obsidian", "obsidian", "obsidian" }));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(black_diamond_multi_tool, 1), new Object[] { black_diamond_sword, black_diamond_pickaxe, black_diamond_shovel, black_diamond_hoe, black_diamond_axe, black_diamond, black_diamond, black_diamond, black_diamond }));
		if (CoreConfig.useIndustry) {
			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(steel_multi_tool, 1), new Object[] { IndustryItems.steel_sword, IndustryItems.steel_pickaxe, IndustryItems.steel_shovel, IndustryItems.steel_hoe, IndustryItems.steel_axe, "ingotSteel", "ingotSteel", "ingotSteel", "ingotSteel" }));
			tools = RecipeHelper.getLatestIRecipes(8);
		} else {
			tools = RecipeHelper.getLatestIRecipes(7);
		}

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(pokeball), new Object[] { "RRR", "CBC", "III", 'R', "dustRedstone", 'C', Items.COAL, 'B', Blocks.STONE_BUTTON, 'I', "ingotIron" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(powerstaff, 1), new Object[] { "IDI", "IRI", " I ", 'D', "gemDiamond", 'R', "dustRedstone", 'I', "ingotIron" }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(empty_energy_canister, 2), new Object[] { "YXY", "Y Y", "YXY", 'X', "ingotIron", 'Y', "blockGlass" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(energy_canister, 1), new Object[] { "Y", "X", 'X', empty_energy_canister, 'Y', element_115 }));
		basicCanisters = RecipeHelper.getLatestIRecipes(2);
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ray_gun, 1), new Object[] { "XZ ", " XY", " XX", 'X', "ingotIron", 'Y', element_115, 'Z', "dustRedstone" }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(dark_iron_ingot, 1), new Object[] { "XYX", 'X', element_115, 'Y', "ingotIron" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(advanced_empty_energy_canister, 2), new Object[] { "YXY", "Y Y", "YXY", 'X', "ingotDarkIron", 'Y', "blockGlass" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(advanced_energy_canister, 1), new Object[] { "XY", "YY", 'X', advanced_empty_energy_canister, 'Y', element_115 }));
		advCanisters = RecipeHelper.getLatestIRecipes(2);
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(advanced_ray_gun, 1), new Object[] { "XZ ", " XY", " XX", 'X', "ingotDarkIron", 'Y', ray_gun, 'Z', "dustRedstone" }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(sling_shot, 1), new Object[] { "#@#", " # ", " # ", '#', "stickWood", '@', Items.STRING }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(sling_pellet, 2), new Object[] { "#", '#', "dirt" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(sling_pellet, 2), new Object[] { "#", '#', "cobblestone" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(sling_pellet, 4), new Object[] { "#", '#', "stone" }));
		pellets = RecipeHelper.getLatestIRecipes(3);

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(spear, 1), new Object[] { "#  ", " X ", "  X", '#', Items.FLINT, 'X', "stickWood" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(spear, 1), new Object[] { "XX#", '#', Items.FLINT, 'X', "stickWood" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(iron_spear, 1), new Object[] { "#", "X", '#', "ingotIron", 'X', spear }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(diamond_spear, 1), new Object[] { "#", "X", '#', "gemDiamond", 'X', spear }));
		basics = RecipeHelper.getLatestIRecipes(4);

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(explosive_spear, 1), new Object[] { " # ", "# #", " X ", '#', "gunpowder", 'X', spear }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(fire_spear, 1), new Object[] { "#", "X", '#', Items.COAL, 'X', spear }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(slime_spear, 1), new Object[] { "#", "X", '#', "slimeball", 'X', spear }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(light_spear, 1), new Object[] { "#", "X", '#', "torch", 'X', spear }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(lightning_spear, 1), new Object[] { "U", "#", "X", 'U', Items.WATER_BUCKET, '#', "dustRedstone", 'X', iron_spear }));
		specials = RecipeHelper.getLatestIRecipes(5);

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ultimate_fist, 1), new Object[] { "XXX", "X##", "XXX", '#', "leather", 'X', "blockDiamond" }));

		ItemStack emptyStack = new ItemStack(mask, 1, 0);
		GameRegistry.addRecipe(new ShapedOreRecipe(emptyStack, new Object[] { "YXY", 'Y', "string", 'X', "paper" }));
		emptyRecipe = RecipeHelper.getLatestIRecipe();

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(mask, 1, 1), new Object[] { emptyStack, "dyeBrown", "dyeYellow", "dyeOrange" }));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(mask, 1, 2), new Object[] { emptyStack, "dyeBlue", "dyeRed", "dyeCyan" }));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(mask, 1, 3), new Object[] { emptyStack, "dyeWhite", "dyeRed", "dyeOrange" }));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(mask, 1, 4), new Object[] { emptyStack, "dyeWhite", "dyeBrown", "dyeBlack" }));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(mask, 1, 5), new Object[] { emptyStack, "dyeGreen", "dyeBlack", "dyeBlack" }));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(mask, 1, 6), new Object[] { emptyStack, "dyeBlack", "dyeMagenta", "dyePurple" }));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(mask, 1, 7), new Object[] { emptyStack, "dyeWhite", "dyeGray", "dyeLightGray" }));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(mask, 1, 8), new Object[] { emptyStack, "dyeBlack", "dyeRed", "dyeYellow" }));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(mask, 1, 9), new Object[] { emptyStack, "dyePink", "dyeBlack" }));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(mask, 1, 10), new Object[] { emptyStack, "dyePink", "dyeWhite", "dyeBrown" }));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(mask, 1, 11), new Object[] { emptyStack, "dyeRed", "dyeBlack", "dyeWhite" }));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(mask, 1, 12), new Object[] { emptyStack, "dyeWhite", "dyeGray" }));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(mask, 1, 13), new Object[] { emptyStack, "dyeGreen", "dyeLime" }));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(mask, 1, 14), new Object[] { emptyStack, "dyeRed", "dyeBlack", "dyeGray" }));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(mask, 1, 15), new Object[] { emptyStack, "dyeBlue", "dyeCyan", "dyeLightBlue" }));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(mask, 1, 16), new Object[] { emptyStack, "dyeLime", "dyeBrown", "dyeOrange" }));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(mask, 1, 17), new Object[] { emptyStack, "dyeBlack", "dyeBrown", "dyeLightGray" }));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(mask, 1, 18), new Object[] { emptyStack, "dyeGreen", "dyeBlack" }));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(mask, 1, 19), new Object[] { emptyStack, "dyeLime", "dyePink", "dyeGray" }));
		mobs = RecipeHelper.getLatestIRecipes(19);
	}

	private static ToolMaterial addMultiToolMaterial(ToolMaterial material) {
		return EnumHelper.addToolMaterial(material.name() + "_multitool", material.getHarvestLevel(), (int) (material.getMaxUses() * ToolsConfig.multiToolDurabilityMultiplier), material.getEfficiencyOnProperMaterial(), material.getDamageVsEntity(), material.getEnchantability());
	}
}