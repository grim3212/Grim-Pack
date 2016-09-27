package com.grim3212.mc.pack.decor.block;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import com.grim3212.mc.pack.core.manual.IManualEntry.IManualBlock;
import com.grim3212.mc.pack.core.manual.pages.Page;
import com.grim3212.mc.pack.core.property.UnlistedPropertyBlockState;
import com.grim3212.mc.pack.core.util.NBTHelper;
import com.grim3212.mc.pack.decor.GrimDecor;
import com.grim3212.mc.pack.decor.client.ManualDecor;
import com.grim3212.mc.pack.decor.item.ItemBrush;
import com.grim3212.mc.pack.decor.tile.TileEntityColorizer;
import com.grim3212.mc.pack.decor.util.BlockHelper;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.particle.ParticleDigging;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.property.ExtendedBlockState;
import net.minecraftforge.common.property.IExtendedBlockState;
import net.minecraftforge.common.property.IUnlistedProperty;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockColorizer extends BlockContainer implements IManualBlock {

	public static final UnlistedPropertyBlockState BLOCK_STATE = UnlistedPropertyBlockState.create("blockstate");

	public BlockColorizer() {
		super(Material.ROCK);
		this.setHardness(1.5F);
		this.setResistance(12F);
		this.setSoundType(SoundType.STONE);
		this.setCreativeTab(GrimDecor.INSTANCE.getCreativeTab());
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new ExtendedBlockState(this, new IProperty[] {}, new IUnlistedProperty[] { BLOCK_STATE });
	}

	@Override
	public IBlockState getExtendedState(IBlockState state, IBlockAccess world, BlockPos pos) {
		TileEntity te = world.getTileEntity(pos);
		if (te instanceof TileEntityColorizer && state instanceof IExtendedBlockState) {
			TileEntityColorizer tef = (TileEntityColorizer) te;
			return ((IExtendedBlockState) state).withProperty(BLOCK_STATE, tef.getBlockState());
		}
		return state;
	}

	@Override
	public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		TileEntity te = world.getTileEntity(pos);

		List<ItemStack> ret = new ArrayList<ItemStack>();
		if (te instanceof TileEntityColorizer) {
			ItemStack item = new ItemStack(this);
			NBTHelper.setString(item, "registryName", Block.REGISTRY.getNameForObject(Blocks.AIR).toString());
			NBTHelper.setInteger(item, "meta", 0);
			ret.add(item);
		} else {
			ret.add(new ItemStack(this));
		}
		return ret;
	}

	@Override
	public void harvestBlock(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, TileEntity te, ItemStack stack) {
		if (te instanceof TileEntityColorizer) {
			player.addStat(StatList.getBlockStats(this));
			player.addExhaustion(0.025F);

			harvesters.set(player);
			ItemStack itemstack = new ItemStack(this);
			NBTHelper.setString(itemstack, "registryName", Block.REGISTRY.getNameForObject(Blocks.AIR).toString());
			NBTHelper.setInteger(itemstack, "meta", 0);
			spawnAsEntity(worldIn, pos, itemstack);
			harvesters.set(null);
		} else {
			super.harvestBlock(worldIn, player, pos, state, (TileEntity) null, stack);
		}
	}

	@Override
	public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player) {
		if (!player.capabilities.isCreativeMode) {
			if (!(((IExtendedBlockState) this.getExtendedState(state, worldIn, pos)).getValue(BLOCK_STATE) == Blocks.AIR.getDefaultState())) {
				IBlockState blockState = ((IExtendedBlockState) this.getExtendedState(state, worldIn, pos)).getValue(BLOCK_STATE);
				spawnAsEntity(worldIn, pos, new ItemStack(blockState.getBlock(), 1, blockState.getBlock().getMetaFromState(blockState)));
			}
		}
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
		ItemStack itemstack = new ItemStack(this);
		NBTHelper.setString(itemstack, "registryName", Block.REGISTRY.getNameForObject(Blocks.AIR).toString());
		NBTHelper.setInteger(itemstack, "meta", 0);
		return itemstack;
	}

	@Override
	public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list) {
		ItemStack itemstack = new ItemStack(this);
		NBTHelper.setString(itemstack, "registryName", Block.REGISTRY.getNameForObject(Blocks.AIR).toString());
		NBTHelper.setInteger(itemstack, "meta", 0);
		list.add(itemstack);
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}

	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}

	@Override
	public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
		return true;
	}

	@Override
	@SuppressWarnings("deprecation")
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		TileEntity tileentity = worldIn.getTileEntity(pos);

		if (heldItem != null && heldItem.getItem() != null && tileentity instanceof TileEntityColorizer) {
			TileEntityColorizer te = (TileEntityColorizer) tileentity;
			Block block = Block.getBlockFromItem(heldItem.getItem());

			if (block != null && !(block instanceof BlockColorizer)) {
				if (BlockHelper.getUsableBlocks().contains(block)) {
					// Can only set blockstate if it contains nothing or if
					// in creative mode
					if (te.getBlockState() == Blocks.AIR.getDefaultState() || playerIn.capabilities.isCreativeMode) {
						if (!playerIn.capabilities.isCreativeMode)
							--heldItem.stackSize;

						ItemBrush.setTileEntityData(worldIn, pos, state, block.getStateFromMeta(heldItem.getMetadata()));

						worldIn.playSound(playerIn, pos, block.getSoundType().getPlaceSound(), SoundCategory.BLOCKS, (block.getSoundType().getVolume() + 1.0F) / 2.0F, block.getSoundType().getPitch() * 0.8F);

						return true;
					} else if (te.getBlockState() != Blocks.AIR.getDefaultState()) {
						return false;
					}
				} else {
					return false;
				}
			} else {
				return false;
			}
		}

		return true;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityColorizer();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean addHitEffects(IBlockState state, World worldObj, RayTraceResult target, ParticleManager manager) {
		TileEntity te = worldObj.getTileEntity(target.getBlockPos());

		if (te instanceof TileEntityColorizer) {
			TileEntityColorizer tileentity = (TileEntityColorizer) te;
			BlockPos pos = target.getBlockPos();

			if (tileentity.getBlockState().getRenderType() != EnumBlockRenderType.INVISIBLE) {
				int i = pos.getX();
				int j = pos.getY();
				int k = pos.getZ();
				float f = 0.1F;
				AxisAlignedBB axisalignedbb = tileentity.getBlockState().getBoundingBox(worldObj, pos);
				double d0 = (double) i + RANDOM.nextDouble() * (axisalignedbb.maxX - axisalignedbb.minX - (double) (f * 2.0F)) + (double) f + axisalignedbb.minX;
				double d1 = (double) j + RANDOM.nextDouble() * (axisalignedbb.maxY - axisalignedbb.minY - (double) (f * 2.0F)) + (double) f + axisalignedbb.minY;
				double d2 = (double) k + RANDOM.nextDouble() * (axisalignedbb.maxZ - axisalignedbb.minZ - (double) (f * 2.0F)) + (double) f + axisalignedbb.minZ;

				EnumFacing side = target.sideHit;

				if (side == EnumFacing.DOWN) {
					d1 = (double) j + axisalignedbb.minY - (double) f;
				}

				if (side == EnumFacing.UP) {
					d1 = (double) j + axisalignedbb.maxY + (double) f;
				}

				if (side == EnumFacing.NORTH) {
					d2 = (double) k + axisalignedbb.minZ - (double) f;
				}

				if (side == EnumFacing.SOUTH) {
					d2 = (double) k + axisalignedbb.maxZ + (double) f;
				}

				if (side == EnumFacing.WEST) {
					d0 = (double) i + axisalignedbb.minX - (double) f;
				}

				if (side == EnumFacing.EAST) {
					d0 = (double) i + axisalignedbb.maxX + (double) f;
				}

				try {
					Constructor<ParticleDigging> constructor = ParticleDigging.class.getDeclaredConstructor(World.class, double.class, double.class, double.class, double.class, double.class, double.class, IBlockState.class);
					constructor.setAccessible(true);
					ParticleDigging digging = constructor.newInstance(worldObj, d0, d1, d2, 0.0D, 0.0D, 0.0D, tileentity.getBlockState());
					digging.setBlockPos(target.getBlockPos()).multiplyVelocity(0.2f).multipleParticleScaleBy(0.6f);
					manager.addEffect(digging);
					return true;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		return super.addHitEffects(state, worldObj, target, manager);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean addDestroyEffects(World world, BlockPos pos, ParticleManager manager) {
		TileEntity te = world.getTileEntity(pos);
		if (te != null && te instanceof TileEntityColorizer) {
			TileEntityColorizer tileentity = (TileEntityColorizer) te;
			if (tileentity.getBlockState() == Blocks.AIR.getDefaultState()) {
				return super.addDestroyEffects(world, pos, manager);
			} else {
				manager.clearEffects(world);
				manager.addBlockDestroyEffects(pos, tileentity.getBlockState());
			}
		}

		return true;
	}

	@Override
	public boolean addLandingEffects(IBlockState state, WorldServer worldObj, BlockPos blockPosition, IBlockState iblockstate, EntityLivingBase entity, int numberOfParticles) {
		TileEntity tileentity = (TileEntity) worldObj.getTileEntity(blockPosition);
		if (tileentity instanceof TileEntityColorizer) {
			TileEntityColorizer te = (TileEntityColorizer) tileentity;
			if (te.getBlockState() == Blocks.AIR.getDefaultState()) {
				return super.addLandingEffects(state, worldObj, blockPosition, iblockstate, entity, numberOfParticles);
			} else {
				worldObj.spawnParticle(EnumParticleTypes.BLOCK_DUST, entity.posX, entity.posY, entity.posZ, numberOfParticles, 0.0D, 0.0D, 0.0D, 0.15000000596046448D, new int[] { Block.getStateId(te.getBlockState()) });
			}
		}
		return true;
	}

	@Override
	public Page getPage(IBlockState state) {
		return ManualDecor.colorizer_page;
	}
}
