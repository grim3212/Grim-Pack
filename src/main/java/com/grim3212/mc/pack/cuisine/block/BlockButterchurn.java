package com.grim3212.mc.pack.cuisine.block;

import com.grim3212.mc.pack.core.block.BlockManual;
import com.grim3212.mc.pack.core.manual.pages.Page;
import com.grim3212.mc.pack.core.util.Utils;
import com.grim3212.mc.pack.cuisine.client.ManualCuisine;
import com.grim3212.mc.pack.cuisine.item.CuisineItems;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.oredict.OreDictionary;

public class BlockButterchurn extends BlockManual {

	public static final PropertyInteger ACTIVE = PropertyInteger.create("active", 0, 1);

	protected BlockButterchurn() {
		super(Material.WOOD, SoundType.WOOD);
		this.setDefaultState(this.blockState.getBaseState().withProperty(ACTIVE, 0));
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (heldItem != null) {
			if (heldItem.getItem() == Items.MILK_BUCKET) {
				if (state.getValue(ACTIVE) == 0) {
					worldIn.setBlockState(pos, state.cycleProperty(ACTIVE), 4);

					--heldItem.stackSize;
					if (heldItem.stackSize <= 0) {
						playerIn.inventory.addItemStackToInventory(new ItemStack(Items.BUCKET));
					}
				}
			} else if (!OreDictionary.getOres("bucketMilk").isEmpty()) {
				for (int count = 0; count < OreDictionary.getOres("bucketMilk").size(); count++) {
					if (heldItem.getItem() == OreDictionary.getOres("bucketMilk").get(count).getItem()) {
						if (state.getValue(ACTIVE) == 0) {

							if (Utils.hasFluidHandler(heldItem)) {
								IFluidHandler fluidBucket = Utils.getFluidHandler(heldItem);
								worldIn.setBlockState(pos, state.cycleProperty(ACTIVE), 4);
								fluidBucket.drain(Fluid.BUCKET_VOLUME, true);
							}
						}
					}
				}
			}
		}

		return true;
	}

	@Override
	public void onBlockClicked(World worldIn, BlockPos pos, EntityPlayer playerIn) {
		if (!worldIn.isRemote) {
			if (worldIn.getBlockState(pos).getValue(ACTIVE) == 1) {
				worldIn.setBlockState(pos, this.getDefaultState());
				float f = 0.7F;
				double d = (double) (worldIn.rand.nextFloat() * f) + (double) (1.0F - f) * 0.5D;
				double d1 = (double) (worldIn.rand.nextFloat() * f) + (double) (1.0F - f) * 0.20000000000000001D + 0.59999999999999998D;
				double d2 = (double) (worldIn.rand.nextFloat() * f) + (double) (1.0F - f) * 0.5D;

				int amount = worldIn.rand.nextInt(4);
				if (amount == 0) {
					amount = 1;
				}

				EntityItem entityitem = new EntityItem(worldIn, (double) pos.getX() + d, (double) pos.getY() + d1, (double) pos.getZ() + d2, new ItemStack(CuisineItems.butter, amount));
				entityitem.setPickupDelay(10);
				worldIn.spawnEntityInWorld(entityitem);
			}
		}
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(ACTIVE, meta);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(ACTIVE);
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { ACTIVE });
	}

	@Override
	public Page getPage(IBlockState state) {
		return ManualCuisine.butterChurn_page;
	}
}
