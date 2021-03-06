package com.grim3212.mc.pack.util.crafting;

import java.util.function.BooleanSupplier;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.grim3212.mc.pack.core.config.CoreConfig;
import com.grim3212.mc.pack.util.config.UtilConfig;

import net.minecraft.util.JsonUtils;
import net.minecraftforge.common.crafting.IConditionFactory;
import net.minecraftforge.common.crafting.JsonContext;

public class UtilConditionFactory implements IConditionFactory {

	@Override
	public BooleanSupplier parse(JsonContext context, JsonObject json) {
		String value = JsonUtils.getString(json, "subpart", "");

		if (CoreConfig.useUtil) {
			switch (value) {
			case "autoitemreplacer":
				return () -> UtilConfig.subpartAutoItemReplacer;
			case "autotorch":
				return () -> UtilConfig.subpartAutoTorch;
			case "debug":
				return () -> UtilConfig.subpartDebug;
			case "doubledoors":
				return () -> UtilConfig.subpartDoubleDoors;
			case "frozen":
				return () -> UtilConfig.subpartFrozen;
			case "fusrodah":
				return () -> UtilConfig.subpartFusRoDah;
			case "graves":
				return () -> UtilConfig.subpartGraves;
			case "infinitelava":
				return () -> UtilConfig.subpartInfiniteLava;
			case "time":
				return () -> UtilConfig.subpartTime;
			default:
				throw new JsonParseException("SubPart '" + value + "' is either misspelled or doesn't exist!");
			}
		} // Util is not even loaded
		return () -> false;
	}
}