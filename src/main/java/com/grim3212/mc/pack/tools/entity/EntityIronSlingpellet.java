package com.grim3212.mc.pack.tools.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityIronSlingpellet extends EntityThrowable {

	public EntityIronSlingpellet(World world) {
		super(world);
	}

	public EntityIronSlingpellet(World world, EntityLivingBase entity) {
		super(world, entity);
	}

	public EntityIronSlingpellet(World world, double par2, double par4, double par6) {
		super(world, par2, par4, par6);
	}

	@Override
	protected void onImpact(RayTraceResult result) {
		if (result.entityHit != null) {

			result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), 8);
		}

		for (int i = 0; i < 8; ++i) {
			this.worldObj.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
		}

		if (!this.worldObj.isRemote) {
			this.setDead();
		}
	}
}
