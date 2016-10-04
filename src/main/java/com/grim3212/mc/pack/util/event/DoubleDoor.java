package com.grim3212.mc.pack.util.event;

import net.minecraft.block.BlockDoor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class DoubleDoor {

	public static void activateDoubleDoor(World world, BlockPos pos, IBlockState state, EntityPlayer entityPlayer, EnumHand enumHand, ItemStack itemStack, EnumFacing facing, Vec3d vec3d) {
		BlockPos blockpos1 = state.getValue(BlockDoor.HALF) == BlockDoor.EnumDoorHalf.LOWER ? pos : pos.down();
		IBlockState iblockstate1 = pos.equals(blockpos1) ? state : world.getBlockState(blockpos1);

		if (iblockstate1.getBlock() instanceof BlockDoor)
			state = iblockstate1.cycleProperty(BlockDoor.OPEN);

		int coordX = 0;
		int coordZ = 0;
		int coordOffset = isHingeLeft(BlockDoor.combineMetadata(world, pos)) ? -1 : 1;

		switch (state.getValue(BlockDoor.FACING)) {
		case SOUTH:
			coordX = pos.getX() - coordOffset;
			coordZ = pos.getZ();
			break;
		case WEST:
			coordX = pos.getX();
			coordZ = pos.getZ() - coordOffset;
			break;
		case NORTH:
			coordX = pos.getX() + coordOffset;
			coordZ = pos.getZ();
			break;
		case EAST:
			coordX = pos.getX();
			coordZ = pos.getZ() + coordOffset;
			break;
		default:
			break;
		}

		BlockPos neighborPos = new BlockPos(coordX, pos.getY(), coordZ);
		IBlockState neighborState = world.getBlockState(neighborPos);

		if (!(neighborState.getBlock() instanceof BlockDoor)) {
			return;
		}

		if (state.getValue(BlockDoor.OPEN) == BlockDoor.isOpen(world, neighborPos)) {
			return;
		}

		neighborState.getBlock().onBlockActivated(world, neighborPos, neighborState, entityPlayer, enumHand, itemStack, facing, (float) vec3d.xCoord, (float) vec3d.yCoord, (float) vec3d.zCoord);
	}

	protected static boolean isHingeLeft(int combinedMeta) {
		return (combinedMeta & 16) != 0;
	}
}
