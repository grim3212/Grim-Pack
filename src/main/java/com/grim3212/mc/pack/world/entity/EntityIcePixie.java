package com.grim3212.mc.pack.world.entity;

import com.grim3212.mc.pack.core.manual.pages.Page;
import com.grim3212.mc.pack.world.client.ManualWorld;
import com.grim3212.mc.pack.world.util.LootTables;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackRanged;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityIcePixie extends EntityDayMob implements IRangedAttackMob {

	public EntityIcePixie(World world) {
		super(world);
		this.setSize(0.35F, 0.5F);

		this.tasks.taskEntries.clear();
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIAvoidBlock(this, 1.4D, Blocks.TORCH, 1));
		this.tasks.addTask(4, new EntityAIAttackRanged(this, 1.0D, 20, 40, 10.0F));
		this.tasks.addTask(4, new EntityAIWander(this, 1.0D));
		this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(6, new EntityAILookIdle(this));

		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget<EntityPlayer>(this, EntityPlayer.class, true));
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
	}

	@Override
	public float getEyeHeight() {
		return 0.4425F;
	}

	@Override
	protected ResourceLocation getLootTable() {
		return LootTables.ENTITIES_ICE_PIXIE;
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return SoundEvents.BLOCK_SNOW_STEP;
	}

	@Override
	protected SoundEvent getHurtSound() {
		return SoundEvents.BLOCK_WOOD_BUTTON_CLICK_ON;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.BLOCK_WOOD_BUTTON_CLICK_OFF;
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.UNDEFINED;
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		int i = 0;
		for (int j = (int) (-3D + posX); (double) j <= 3D + posX; j++) {
			for (int k = (int) (-3D + posY); (double) k <= 3D + posY; k++) {
				for (int l = (int) (-3D + posZ); (double) l <= 3D + posZ; l++) {
					Block i1 = worldObj.getBlockState(new BlockPos(j, k, l)).getBlock();
					if (i1 == Blocks.TORCH) {
						i++;
					}
				}
			}
		}

		if (i >= 1) {
			attackEntityFrom(DamageSource.generic, i / 2);
		}

		BlockPos pos = new BlockPos((int) posX, (int) posY - 1, (int) posZ);
		if (worldObj.getBlockState(pos).getBlock() == Blocks.ICE || worldObj.getBlockState(pos).getBlock() == Blocks.SNOW) {
			this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.40D);
		} else {
			this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
		}
	}

	@Override
	public boolean attackEntityFrom(DamageSource damagesource, float i) {
		Entity entity = damagesource.getEntity();
		if (entity instanceof EntityPlayer) {
			EntityPlayer entityplayer = (EntityPlayer) entity;
			ItemStack itemstack = entityplayer.getHeldItemMainhand();
			if (itemstack != null && (itemstack.getItem() == Items.FLINT_AND_STEEL || itemstack.getItem() == Item.getItemFromBlock(Blocks.TORCH))) {
				return super.attackEntityFrom(damagesource, i);
			}
		}
		if (damagesource == DamageSource.generic) {
			return super.attackEntityFrom(damagesource, i);
		} else {
			return false;
		}
	}

	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase entity, float f) {
		EntityIceCube entityicecube = new EntityIceCube(this.worldObj, this, entity, 1.2F, (float) (14 - this.worldObj.getDifficulty().getDifficultyId() * 4));
		worldObj.playSound((EntityPlayer) null, this.getPosition(), SoundEvents.ENTITY_SLIME_ATTACK, SoundCategory.HOSTILE, 1.0F, 1.0F / (rand.nextFloat() * 0.4F + 0.8F));
		worldObj.spawnEntityInWorld(entityicecube);
	}

	@Override
	public Page getPage(Entity entity) {
		return ManualWorld.icePixie_page;
	}
}