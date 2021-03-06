package com.grim3212.mc.pack.tools.client;

import com.grim3212.mc.pack.GrimPack;
import com.grim3212.mc.pack.core.client.entity.RenderProjectile.RenderProjectileFactory;
import com.grim3212.mc.pack.core.client.entity.RenderThrowable.RenderThrowableFactory;
import com.grim3212.mc.pack.core.proxy.ClientProxy;
import com.grim3212.mc.pack.tools.blocks.BlockCrystal;
import com.grim3212.mc.pack.tools.blocks.ToolsBlocks;
import com.grim3212.mc.pack.tools.client.entity.RenderBlockPushPullFactory;
import com.grim3212.mc.pack.tools.client.entity.RenderBoomerang.RenderBoomerangFactory;
import com.grim3212.mc.pack.tools.client.entity.RenderDetonator.RenderDetonatorFactory;
import com.grim3212.mc.pack.tools.client.entity.RenderGrenade.RenderGrenadeFactory;
import com.grim3212.mc.pack.tools.client.entity.RenderRayGun.RenderRayGunFactory;
import com.grim3212.mc.pack.tools.client.entity.RenderSlingPellet.RenderSlingPelletFactory;
import com.grim3212.mc.pack.tools.config.ToolsConfig;
import com.grim3212.mc.pack.tools.entity.EntityAdvRayw;
import com.grim3212.mc.pack.tools.entity.EntityBallisticKnife;
import com.grim3212.mc.pack.tools.entity.EntityBlockPushPull;
import com.grim3212.mc.pack.tools.entity.EntityBoomerang;
import com.grim3212.mc.pack.tools.entity.EntityDetonator;
import com.grim3212.mc.pack.tools.entity.EntityDiamondBoomerang;
import com.grim3212.mc.pack.tools.entity.EntityGrenade;
import com.grim3212.mc.pack.tools.entity.EntityKnife;
import com.grim3212.mc.pack.tools.entity.EntityPokeball;
import com.grim3212.mc.pack.tools.entity.EntityRayw;
import com.grim3212.mc.pack.tools.entity.EntitySlimeSpear;
import com.grim3212.mc.pack.tools.entity.EntitySlingPellet;
import com.grim3212.mc.pack.tools.entity.EntitySpear;
import com.grim3212.mc.pack.tools.entity.EntityTomahawk;
import com.grim3212.mc.pack.tools.event.ChickenSuitJumpEvent;
import com.grim3212.mc.pack.tools.items.ItemBackpack;
import com.grim3212.mc.pack.tools.items.ItemPelletBag;
import com.grim3212.mc.pack.tools.items.ToolsItems;
import com.grim3212.mc.pack.tools.util.EnumCrystalType;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class ToolsClientProxy extends ClientProxy {

	@Override
	public void preInit() {
		MinecraftForge.EVENT_BUS.register(new ToolsModelHandler());

		if (ToolsConfig.subpartChickenSuit)
			MinecraftForge.EVENT_BUS.register(new ChickenSuitJumpEvent());
		
		if(ToolsConfig.subpartMagic)
			MinecraftForge.EVENT_BUS.register(new ToolsClientEvents());

		// ENTITYS
		if (ToolsConfig.subpartPowerstaff)
			RenderingRegistry.registerEntityRenderingHandler(EntityBlockPushPull.class, new RenderBlockPushPullFactory());

		if (ToolsConfig.subpartPokeball)
			RenderingRegistry.registerEntityRenderingHandler(EntityPokeball.class, new RenderThrowableFactory(new ItemStack(ToolsItems.pokeball)));

		if (ToolsConfig.subpartSlingshots)
			RenderingRegistry.registerEntityRenderingHandler(EntitySlingPellet.class, new RenderSlingPelletFactory());

		if (ToolsConfig.subpartRayGuns) {
			RenderingRegistry.registerEntityRenderingHandler(EntityRayw.class, new RenderRayGunFactory(new ResourceLocation(GrimPack.modID, "textures/entities/sonicw.png")));
			RenderingRegistry.registerEntityRenderingHandler(EntityAdvRayw.class, new RenderRayGunFactory(new ResourceLocation(GrimPack.modID, "textures/entities/sonic_adv.png")));
		}
		if (ToolsConfig.subpartSpears) {
			RenderingRegistry.registerEntityRenderingHandler(EntitySpear.class, new RenderProjectileFactory(new ResourceLocation(GrimPack.modID, "textures/entities/spears.png")));
			RenderingRegistry.registerEntityRenderingHandler(EntitySlimeSpear.class, new RenderProjectileFactory(new ResourceLocation(GrimPack.modID, "textures/entities/spears.png")));
		}

		if (ToolsConfig.subpartKnives) {
			RenderingRegistry.registerEntityRenderingHandler(EntityBallisticKnife.class, new RenderProjectileFactory(new ResourceLocation(GrimPack.modID, "textures/entities/ballistic_knife.png")));
			RenderingRegistry.registerEntityRenderingHandler(EntityTomahawk.class, new RenderProjectileFactory(new ResourceLocation(GrimPack.modID, "textures/entities/tomahawk.png"), true));
			RenderingRegistry.registerEntityRenderingHandler(EntityKnife.class, new RenderProjectileFactory(new ResourceLocation(GrimPack.modID, "textures/entities/throwing_knife.png"), true));
		}

		if (ToolsConfig.subpartBoomerangs) {
			RenderingRegistry.registerEntityRenderingHandler(EntityBoomerang.class, new RenderBoomerangFactory(new ResourceLocation(GrimPack.modID, "textures/items/boomerang.png"), "grimpack:items/boomerang"));
			RenderingRegistry.registerEntityRenderingHandler(EntityDiamondBoomerang.class, new RenderBoomerangFactory(new ResourceLocation(GrimPack.modID, "textures/items/diamond_boomerang.png"), "grimpack:items/diamond_boomerang"));
		}

		if (ToolsConfig.subpartGrenadeLauncher)
			RenderingRegistry.registerEntityRenderingHandler(EntityGrenade.class, new RenderGrenadeFactory());

		if (ToolsConfig.subpartDetonators) {
			RenderingRegistry.registerEntityRenderingHandler(EntityDetonator.class, new RenderDetonatorFactory());
		}

		// Key bindings
		if (ToolsConfig.subpartWands || ToolsConfig.subpartPowerstaff || ToolsConfig.subpartSlingshots || ToolsConfig.subpartStaffs)
			MinecraftForge.EVENT_BUS.register(new KeyBindHelper());
	}

	@Override
	public void initColors() {
		if (ToolsConfig.subpartBackpacks) {
			Minecraft.getMinecraft().getItemColors().registerItemColorHandler(new IItemColor() {
				@Override
				public int colorMultiplier(ItemStack stack, int tintIndex) {
					if (tintIndex == 1)
						return Integer.parseInt(ItemBackpack.colorNumbers[15], 16);
					else {
						int packColor = ItemBackpack.getColor(stack);

						if (packColor < 0) {
							return Integer.parseInt("C65C35", 16);
						} else {
							return Integer.parseInt(ItemBackpack.colorNumbers[packColor], 16);
						}
					}
				}
			}, ToolsItems.backpack);
		}

		if (ToolsConfig.subpartSlingshots) {
			Minecraft.getMinecraft().getItemColors().registerItemColorHandler(new IItemColor() {
				@Override
				public int colorMultiplier(ItemStack stack, int tintIndex) {
					if (tintIndex == 1)
						return Integer.parseInt(ItemPelletBag.colorNumbers[15], 16);
					else {
						int packColor = ItemPelletBag.getColor(stack);

						if (packColor < 0) {
							return Integer.parseInt("C65C35", 16);
						} else {
							return Integer.parseInt(ItemPelletBag.colorNumbers[packColor], 16);
						}
					}
				}
			}, ToolsItems.pellet_bag);
		}

		if (ToolsConfig.subpartMagic) {
			Minecraft.getMinecraft().getItemColors().registerItemColorHandler(new IItemColor() {
				@Override
				public int colorMultiplier(ItemStack stack, int tintIndex) {
					return EnumCrystalType.values[stack.getMetadata()].getCrystalColor();
				}
			}, ToolsItems.shard, ToolsItems.gem);

			Minecraft.getMinecraft().getItemColors().registerItemColorHandler(new IItemColor() {
				@Override
				public int colorMultiplier(ItemStack stack, int tintIndex) {
					return EnumCrystalType.values[stack.getMetadata()].getCrystalColor();
				}
			}, ToolsBlocks.magic_crystal);

			Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler(new IBlockColor() {
				@Override
				public int colorMultiplier(IBlockState state, IBlockAccess worldIn, BlockPos pos, int tintIndex) {
					return state.getValue(BlockCrystal.CRYSTAL_TYPE).getCrystalColor();
				}
			}, ToolsBlocks.magic_crystal);
		}
	}
}
