package com.grim3212.mc.industry.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ContainerWorkbench;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class ContainerIronWorkbench extends ContainerWorkbench {

	private World worldObj;

	public ContainerIronWorkbench(InventoryPlayer invPlayer, World world, BlockPos pos) {
		super(invPlayer, world, pos);
		this.worldObj = world;

	}

	@Override
	public void onCraftMatrixChanged(IInventory iinventory) {
		this.craftResult.setInventorySlotContents(0, CraftingManager.getInstance().findMatchingRecipe(this.craftMatrix, this.worldObj));
		if (craftResult.getStackInSlot(0) != null) {
			if (craftResult.getStackInSlot(0).stackSize >= 64) {
				craftResult.getStackInSlot(0).stackSize = 127;
			} else {
				craftResult.getStackInSlot(0).stackSize *= 2;
			}
		}
	}

	@Override
	public void onContainerClosed(EntityPlayer par1EntityPlayer) {
		super.onContainerClosed(par1EntityPlayer);
	}

	@Override
	public boolean canInteractWith(EntityPlayer par1EntityPlayer) {
		return true;
	}
}
