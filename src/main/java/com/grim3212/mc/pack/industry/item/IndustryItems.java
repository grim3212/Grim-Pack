package com.grim3212.mc.pack.industry.item;

import com.grim3212.mc.pack.GrimPack;
import com.grim3212.mc.pack.core.client.gui.PackGuiHandler;
import com.grim3212.mc.pack.core.common.CommonItems;
import com.grim3212.mc.pack.core.config.CoreConfig;
import com.grim3212.mc.pack.core.item.ItemManualArmor;
import com.grim3212.mc.pack.core.item.ItemManualAxe;
import com.grim3212.mc.pack.core.item.ItemManualHoe;
import com.grim3212.mc.pack.core.item.ItemManualPage;
import com.grim3212.mc.pack.core.item.ItemManualPickaxe;
import com.grim3212.mc.pack.core.item.ItemManualSpade;
import com.grim3212.mc.pack.core.item.ItemManualSword;
import com.grim3212.mc.pack.core.part.GrimCreativeTabs;
import com.grim3212.mc.pack.core.util.Utils;
import com.grim3212.mc.pack.industry.block.BlockSiding.EnumSidingColor;
import com.grim3212.mc.pack.industry.config.IndustryConfig;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.IForgeRegistry;

public class IndustryItems {

	public static final ToolMaterial steel = Utils.addToolMaterial(IndustryConfig.steelToolMaterial);
	public static final ArmorMaterial antiRadiation = Utils.addArmorMaterial(GrimPack.modID + ":radiation", SoundEvents.BLOCK_CLOTH_PLACE, IndustryConfig.antiRadiationArmorMaterial);
	public static final ArmorMaterial gravboots = Utils.addArmorMaterial(GrimPack.modID + ":gravity", SoundEvents.ITEM_ARMOR_EQUIP_IRON, IndustryConfig.gravityArmorMaterial);

	public static final Item gravity_boots = (new ItemManualArmor("gravity_boots", gravboots, 4, EntityEquipmentSlot.FEET, "industry:refining.armor")).setCreativeTab(GrimCreativeTabs.GRIM_INDUSTRY);
	public static final Item uranium_ingot = (new ItemManualPage("uranium_ingot", "industry:refining.uranium_smelt")).setCreativeTab(GrimCreativeTabs.GRIM_INDUSTRY);
	public static final Item refined_uranium = (new ItemManualPage("refined_uranium", "industry:refining.refined_uranium")).setCreativeTab(GrimCreativeTabs.GRIM_INDUSTRY);
	public static final Item plutonium_ingot = (new ItemManualPage("plutonium_ingot", "industry:refining.plutonium")).setCreativeTab(GrimCreativeTabs.GRIM_INDUSTRY);
	public static final Item refined_plutonium = (new ItemManualPage("refined_plutonium", "industry:refining.refined_plutonium")).setCreativeTab(GrimCreativeTabs.GRIM_INDUSTRY);
	public static final Item reactor_core = (new ItemManualPage("reactor_core", "industry:reactor.reactor")).setCreativeTab(GrimCreativeTabs.GRIM_INDUSTRY);
	public static final Item reactor_core_case = (new ItemManualPage("reactor_core_case", "industry:reactor.reactor_case")).setCreativeTab(GrimCreativeTabs.GRIM_INDUSTRY);
	public static final Item iron_parts = (new ItemManualPage("iron_parts", "industry:reactor.iron_parts")).setCreativeTab(GrimCreativeTabs.GRIM_INDUSTRY);
	public static final Item low_gravity_controller = new ItemLowGravityController();
	public static final Item gravity_controller = new ItemGravityController();
	public static final Item mob_repulsor = new ItemMobRepulsor();
	public static final Item anti_radiation_helmet = (new ItemManualArmor("anti_radiation_helmet", antiRadiation, 3, EntityEquipmentSlot.HEAD, "industry:refining.armor")).setCreativeTab(GrimCreativeTabs.GRIM_INDUSTRY);
	public static final Item anti_radiation_chest = (new ItemManualArmor("anti_radiation_chest", antiRadiation, 3, EntityEquipmentSlot.CHEST, "industry:refining.armor")).setCreativeTab(GrimCreativeTabs.GRIM_INDUSTRY);
	public static final Item anti_radiation_legs = (new ItemManualArmor("anti_radiation_legs", antiRadiation, 3, EntityEquipmentSlot.LEGS, "industry:refining.armor")).setCreativeTab(GrimCreativeTabs.GRIM_INDUSTRY);
	public static final Item anti_radiation_boots = (new ItemManualArmor("anti_radiation_boots", antiRadiation, 3, EntityEquipmentSlot.FEET, "industry:refining.armor")).setCreativeTab(GrimCreativeTabs.GRIM_INDUSTRY);
	public static final Item gate_grating = (new ItemManualPage("gate_grating", "industry:gates.gate")).setCreativeTab(GrimCreativeTabs.GRIM_INDUSTRY);
	public static final Item gate_trumpet = new ItemActivator("gate_trumpet");
	public static final Item garage_panel = (new ItemManualPage("garage_panel", "industry:gates.garage")).setCreativeTab(GrimCreativeTabs.GRIM_INDUSTRY);
	public static final Item garage_remote = new ItemActivator("garage_remote");
	public static final Item paint_roller = (new ItemManualPage("paint_roller", "industry:rways.paint")).setCreativeTab(GrimCreativeTabs.GRIM_INDUSTRY).setMaxStackSize(1).setFull3D();
	public static final Item tarball = (new ItemManualPage("tarball", "industry:tarball.paint")).setCreativeTab(GrimCreativeTabs.GRIM_INDUSTRY);
	public static final Item asphalt = new ItemAsphalt();
	public static final Item paint_roller_white = new ItemPaintRollerColor(EnumSidingColor.white);
	public static final Item paint_roller_red = new ItemPaintRollerColor(EnumSidingColor.red);
	public static final Item paint_roller_green = new ItemPaintRollerColor(EnumSidingColor.green);
	public static final Item paint_roller_blue = new ItemPaintRollerColor(EnumSidingColor.blue);
	public static final Item coal_iron_ingot = (new ItemManualPage("coal_iron_ingot", "industry:metalworks.coaliron")).setCreativeTab(GrimCreativeTabs.GRIM_INDUSTRY);
	public static final Item crude_oil = (new ItemManualPage("crude_oil", "industry:machines.refinery_recipes")).setCreativeTab(GrimCreativeTabs.GRIM_INDUSTRY);
	public static final Item fuel = (new ItemManualPage("fuel", "industry:machines.refinery_recipes")).setCreativeTab(GrimCreativeTabs.GRIM_INDUSTRY);
	public static final Item oily_chunk = (new ItemManualPage("oily_chunk", "industry:metalworks.fuel")).setCreativeTab(GrimCreativeTabs.GRIM_INDUSTRY);
	public static final Item steel_axe = (new ItemManualAxe("steel_axe", steel, 8.0f, -3.1f, "industry:metalworks.steeltools")).setCreativeTab(GrimCreativeTabs.GRIM_INDUSTRY);
	public static final Item steel_hoe = (new ItemManualHoe("steel_hoe", steel, "industry:metalworks.steeltools")).setCreativeTab(GrimCreativeTabs.GRIM_INDUSTRY);
	public static final Item steel_pickaxe = (new ItemManualPickaxe("steel_pickaxe", steel, "industry:metalworks.steeltools")).setCreativeTab(GrimCreativeTabs.GRIM_INDUSTRY);
	public static final Item steel_shovel = (new ItemManualSpade("steel_shovel", steel, "industry:metalworks.steeltools")).setCreativeTab(GrimCreativeTabs.GRIM_INDUSTRY);
	public static final Item steel_sword = (new ItemManualSword("steel_sword", steel, "industry:metalworks.steeltools")).setCreativeTab(GrimCreativeTabs.GRIM_INDUSTRY);
	public static final Item super_crude_oil = (new ItemManualPage("super_crude_oil", "industry:machines.refinery_recipes")).setCreativeTab(GrimCreativeTabs.GRIM_INDUSTRY);
	public static final Item drill_head_item = (new ItemManualPage("drill_head_item", "industry:machines.drill")).setCreativeTab(GrimCreativeTabs.GRIM_INDUSTRY);
	public static final Item extruder = new ItemExtruder();
	public static final Item portable_diamond_workbench = new ItemUpgradedPortableWorkbench(PackGuiHandler.PORTABLE_DIAMOND_MAIN_GUI_ID, PackGuiHandler.PORTABLE_DIAMOND_OFF_GUI_ID);
	public static final Item portable_iron_workbench = new ItemUpgradedPortableWorkbench(PackGuiHandler.PORTABLE_IRON_MAIN_GUI_ID, PackGuiHandler.PORTABLE_IRON_OFF_GUI_ID);
	public static final Item position_finder = new ItemPositionFinder();
	public static final Item locksmith_lock = new ItemCombination("locksmith_lock");
	public static final Item locksmith_key = new ItemCombination("locksmith_key");

	@SubscribeEvent
	public void initItems(RegistryEvent.Register<Item> evt) {
		IForgeRegistry<Item> r = evt.getRegistry();

		if (IndustryConfig.subpartStorage) {
			r.register(locksmith_lock);
			r.register(locksmith_key);
		}

		if (IndustryConfig.subpartMachines) {
			r.register(crude_oil);
			r.register(super_crude_oil);
			r.register(fuel);
			r.register(oily_chunk);
			r.register(drill_head_item);
		}

		if (IndustryConfig.subpartGates) {
			r.register(gate_grating);
			r.register(gate_trumpet);
			r.register(garage_panel);
			r.register(garage_remote);
		}

		if (IndustryConfig.subpartRWays) {
			r.register(tarball);
			r.register(asphalt);
		}

		if (IndustryConfig.subpartDecoration) {
			r.register(paint_roller);
			r.register(paint_roller_white);
			r.register(paint_roller_blue);
			r.register(paint_roller_green);
			r.register(paint_roller_red);
		}

		if (IndustryConfig.subpartExtruder)
			r.register(extruder);

		if (IndustryConfig.subpartWorkbenchUpgrades) {
			r.register(portable_diamond_workbench);
			r.register(portable_iron_workbench);
		}

		if (IndustryConfig.subpartSensors)
			r.register(position_finder);

		if (IndustryConfig.subpartSteel) {
			r.register(steel_axe);
			r.register(steel_hoe);
			r.register(steel_pickaxe);
			r.register(steel_shovel);
			r.register(steel_sword);
			r.register(coal_iron_ingot);

			if (CoreConfig.subpartSteel)
				steel.setRepairItem(new ItemStack(CommonItems.steel_ingot));
		}

		if (IndustryConfig.subpartNuclear) {
			r.register(anti_radiation_boots);
			r.register(anti_radiation_chest);
			r.register(anti_radiation_helmet);
			r.register(anti_radiation_legs);
			r.register(iron_parts);
			r.register(reactor_core_case);
			r.register(reactor_core);
			r.register(refined_plutonium);
			r.register(plutonium_ingot);
			r.register(refined_uranium);
			r.register(uranium_ingot);

			antiRadiation.setRepairItem(new ItemStack(Blocks.WOOL));
		}

		if (IndustryConfig.subpartGravity) {
			r.register(gravity_boots);
			r.register(gravity_controller);
			r.register(low_gravity_controller);
			r.register(mob_repulsor);

			gravboots.setRepairItem(new ItemStack(Items.IRON_INGOT));
		}

		initOreDict();
	}

	private void initOreDict() {
		if (IndustryConfig.subpartNuclear) {
			OreDictionary.registerOre("ingotUranium", uranium_ingot);
			OreDictionary.registerOre("ingotRefinedUranium", refined_uranium);
			OreDictionary.registerOre("ingotPlutonium", plutonium_ingot);
			OreDictionary.registerOre("ingotRefinedPlutonium", refined_plutonium);
		}

		if (IndustryConfig.subpartMachines) {
			OreDictionary.registerOre("canOil", crude_oil);
			OreDictionary.registerOre("canCrudeOil", super_crude_oil);
			OreDictionary.registerOre("canFuel", fuel);
		}

		if (IndustryConfig.subpartRWays)
			OreDictionary.registerOre("tar", tarball);
	}

	@SubscribeEvent
	public void registerRecipes(RegistryEvent.Register<IRecipe> evt) {
		if (IndustryConfig.subpartRWays)
			GameRegistry.addSmelting(tarball, new ItemStack(asphalt, 1), 0.35F);

		if (IndustryConfig.subpartSteel)
			if (CoreConfig.subpartSteel)
				GameRegistry.addSmelting(coal_iron_ingot, new ItemStack(CommonItems.steel_ingot, 1), 0.5F);
	}
}