package com.grim3212.mc.pack.core.part;

import java.util.ArrayList;
import java.util.List;

import com.grim3212.mc.pack.GrimPack;
import com.grim3212.mc.pack.core.config.GrimConfig;
import com.grim3212.mc.pack.core.manual.IManualPart;
import com.grim3212.mc.pack.core.manual.ManualPart;
import com.grim3212.mc.pack.core.part.IPartEntities.IPartTileEntities;

import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class GrimPart {

	public static final String COMMON_PROXY = "com.grim3212.mc.pack.core.proxy.CommonProxy";

	private String partId;
	private String partName;
	private GrimPartCreativeTab creativeTab;
	private GrimConfig config;
	private ManualPart manualPart;
	private List<IPartItems> items;
	private List<IPartEntities> entities;
	private List<IPartTileEntities> tileentities;
	private boolean useCreativeTab;

	public GrimPart(String partId, String partName, GrimConfig config) {
		this(partId, partName, config, true);
	}

	public GrimPart(String partId, String partName, GrimConfig config, boolean useCreativeTab) {
		this.partId = partId;
		this.partName = partName;
		this.useCreativeTab = useCreativeTab;
		this.config = config;
		this.items = new ArrayList<IPartItems>();
		this.entities = new ArrayList<IPartEntities>();
		this.tileentities = new ArrayList<IPartTileEntities>();
	}

	@SubscribeEvent
	public void onConfigChanged(OnConfigChangedEvent event) {
		if (event.getModID().equals(GrimPack.modID)) {
			this.getGrimConfig().syncConfig();
		}
	}

	public boolean shouldUseCreativeTab() {
		return useCreativeTab;
	}

	public void setCreativeTab(GrimPartCreativeTab creativeTab) {
		this.creativeTab = creativeTab;
	}

	public GrimConfig getGrimConfig() {
		return this.config;
	}

	@SideOnly(Side.CLIENT)
	public abstract IManualPart getManual();

	public Configuration getConfig() {
		return this.getGrimConfig().config;
	}

	public ManualPart setModSection(ManualPart manualPart) {
		return this.manualPart = manualPart;
	}

	public ManualPart getManualPart() {
		return manualPart;
	}

	public void addEntity(IPartEntities entity) {
		this.entities.add(entity);
	}

	public void addItem(IPartItems item) {
		this.items.add(item);
	}

	public void addTileEntity(IPartTileEntities te) {
		this.tileentities.add(te);
	}

	/**
	 * Registers the config change event And iterates through items and entities
	 * 
	 * @param FMLPreInitializationEvent
	 *            event
	 */
	public void preInit(FMLPreInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(this);

		// Initialize all items and blocks first
		for (int i = 0; i < this.items.size(); i++) {
			this.items.get(i).initItems();
		}

		for (int i = 0; i < this.entities.size(); i++) {
			this.entities.get(i).initEntities();
		}
	}

	/**
	 * Initializes tile entities
	 * 
	 * @param FMLInitializationEvent
	 *            event
	 */
	public void init(FMLInitializationEvent event) {
		// Then create recipes after all blocks are loaded
		for (int i = 0; i < this.items.size(); i++) {
			this.items.get(i).addRecipes();
		}

		for (int i = 0; i < this.tileentities.size(); i++) {
			this.tileentities.get(i).initTileEntities();
		}

		if (event.getSide() == Side.CLIENT)
			getManual().initPages();
	}

	/**
	 * @param FMLPostInitializationEvent
	 *            event
	 */
	public void postInit(FMLPostInitializationEvent event) {
	}

	protected ItemStack getCreativeTabIcon() {
		return ItemStack.EMPTY;
	}

	public String getPartId() {
		return partId;
	}

	public String getPartName() {
		return partName;
	}

	public GrimPartCreativeTab getCreativeTab() {
		return creativeTab;
	}
}
