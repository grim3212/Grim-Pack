package com.grim3212.mc.pack.decor.item;

import com.grim3212.mc.pack.core.item.ItemManual;
import com.grim3212.mc.pack.core.manual.pages.Page;
import com.grim3212.mc.pack.core.part.GrimCreativeTabs;
import com.grim3212.mc.pack.core.util.NBTHelper;
import com.grim3212.mc.pack.decor.block.DecorBlocks;
import com.grim3212.mc.pack.decor.client.ManualDecor;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSnow;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

@SuppressWarnings("deprecation")
public class ItemLampPost extends ItemManual {

	public ItemLampPost() {
		super("lamp_item");
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
		setCreativeTab(GrimCreativeTabs.GRIM_DECOR);
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		IBlockState iblockstate = worldIn.getBlockState(pos);
		Block block = iblockstate.getBlock();

		if (block == Blocks.SNOW_LAYER && ((Integer) iblockstate.getValue(BlockSnow.LAYERS)).intValue() < 1) {
			facing = EnumFacing.UP;
		} else if (!block.isReplaceable(worldIn, pos)) {
			pos = pos.offset(facing);
		}

		ItemStack stack = playerIn.getHeldItem(hand);
		if (stack.getCount() == 0) {
			return EnumActionResult.FAIL;
		} else if (!playerIn.canPlayerEdit(pos, facing, stack)) {
			return EnumActionResult.FAIL;
		} else if (pos.getY() == 255 && DecorBlocks.lamp_post_bottom.getDefaultState().getMaterial().isSolid()) {
			return EnumActionResult.FAIL;
		} else if (worldIn.mayPlace(DecorBlocks.lamp_post_bottom, pos, false, facing, (Entity) null) && worldIn.mayPlace(DecorBlocks.lamp_post_middle, pos.up(), false, facing, (Entity) null) && worldIn.mayPlace(DecorBlocks.lamp_post_top, pos.up(2), false, facing, (Entity) null)) {

			worldIn.setBlockState(pos, DecorBlocks.lamp_post_bottom.getDefaultState());
			worldIn.setBlockState(pos.up(), DecorBlocks.lamp_post_middle.getDefaultState());
			worldIn.setBlockState(pos.up(2), DecorBlocks.lamp_post_top.getDefaultState());

			stack.shrink(1);

			worldIn.playSound(playerIn, pos, DecorBlocks.lamp_post_bottom.getSoundType().getPlaceSound(), SoundCategory.BLOCKS, (DecorBlocks.lamp_post_bottom.getSoundType().getVolume() + 1.0F) / 2.0F, DecorBlocks.lamp_post_bottom.getSoundType().getPitch() * 0.8F);

			return EnumActionResult.SUCCESS;
		} else {
			return EnumActionResult.FAIL;
		}
	}

	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> subItems) {
		if (isInCreativeTab(tab)) {
			ItemStack itemstack = new ItemStack(this);
			NBTHelper.setString(itemstack, "registryName", Block.REGISTRY.getNameForObject(Blocks.AIR).toString());
			NBTHelper.setInteger(itemstack, "meta", 0);
			subItems.add(itemstack);
		}
	}

	@Override
	public Page getPage(ItemStack stack) {
		return ManualDecor.lamps_page;
	}
}
