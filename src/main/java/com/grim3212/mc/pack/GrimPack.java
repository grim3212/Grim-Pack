package com.grim3212.mc.pack;

import com.grim3212.mc.pack.core.GrimCore;
import com.grim3212.mc.pack.core.client.gui.PackGuiHandler;
import com.grim3212.mc.pack.core.config.CoreConfig;
import com.grim3212.mc.pack.core.part.PartRegistry;
import com.grim3212.mc.pack.cuisine.GrimCuisine;
import com.grim3212.mc.pack.decor.GrimDecor;
import com.grim3212.mc.pack.industry.GrimIndustry;
import com.grim3212.mc.pack.tools.GrimTools;
import com.grim3212.mc.pack.util.GrimUtil;
import com.grim3212.mc.pack.world.GrimWorld;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

@Mod(modid = GrimPack.modID, name = GrimPack.modName, version = "@MOD_VERSION@", dependencies = "required-after:Forge@[12.18.1.2039,)", guiFactory = "com.grim3212.mc.pack.core.config.ConfigGuiFactory", updateJSON = "http://grim3212.com/files/update.json")
public class GrimPack {

	@Instance(GrimPack.modID)
	public static GrimPack INSTANCE;

	public static final String modID = "grimpack";
	public static final String modName = "Grim Pack";

	static {
		PartRegistry.registerPart(GrimCore.INSTANCE);
		if (CoreConfig.useCuisine)
			PartRegistry.registerPart(GrimCuisine.INSTANCE);
		if (CoreConfig.useDecor)
			PartRegistry.registerPart(GrimDecor.INSTANCE);
		if (CoreConfig.useIndustry)
			PartRegistry.registerPart(GrimIndustry.INSTANCE);
		if (CoreConfig.useTools)
			PartRegistry.registerPart(GrimTools.INSTANCE);
		if (CoreConfig.useUtil)
			PartRegistry.registerPart(GrimUtil.INSTANCE);
		if (CoreConfig.useWorld)
			PartRegistry.registerPart(GrimWorld.INSTANCE);
	}

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		// Register GUI handler for the Instruction Manual
		NetworkRegistry.INSTANCE.registerGuiHandler(GrimPack.INSTANCE, new PackGuiHandler());

		PartRegistry.preInit(event);
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		PartRegistry.init(event);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		PartRegistry.postInit(event);
	}
}
