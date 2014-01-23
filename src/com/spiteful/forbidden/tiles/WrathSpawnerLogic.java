package com.spiteful.forbidden.tiles;

import com.spiteful.forbidden.*;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityLivingData;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.WeightedRandom;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;

import thaumcraft.common.Thaumcraft;

public class WrathSpawnerLogic
{
    /** The mob spawner we deal with */
    final TileEntityWrathCage mobSpawnerEntity;
	
	public boolean mobSet = false;
	
	/** The delay to spawn. */
    public int spawnDelay = 20;
    private String mobID = "Pig";
	Aspect aspect = Aspect.GREED;
	private boolean slothful = false;
	private int fuel = 0;

    public double field_98287_c;
    public double field_98284_d;
    private int minSpawnDelay = 200;
    private int maxSpawnDelay = 300;

    /** A counter for spawn tries. */
    private int spawnCount = 3;
    private Entity renderEntity;
    private int maxNearbyEntities = 6;

    /** The distance from which a player activates the spawner. */
    private int activatingRangeFromPlayer = 16;

    /** The range coefficient for spawning entities around. */
    private int spawnRange = 4;

    WrathSpawnerLogic(TileEntityWrathCage par1TileEntityMobSpawner)
    {
        this.mobSpawnerEntity = par1TileEntityMobSpawner;
    }
	
	/**
     * Gets the entity name that should be spawned.
     */
    public String getEntityNameToSpawn()
    {
        return this.mobID;
    }
	
	public Aspect getAspect()
	{
		return aspect;
	}

    public void setMobID(String par1Str)
    {
        this.mobID = par1Str;
		if(Config.spawnerMobs.containsKey(mobID))
			aspect = Config.spawnerMobs.get(mobID);
		else
			aspect = Aspect.GREED;
    }
	
	public void mobIsSet(boolean inp){
		mobSet = inp;
	}
	
	public boolean isMobSet()
	{
		return mobSet;
	}
	
    public void updateSpawnerBlock(int par1)
    {
        this.mobSpawnerEntity.worldObj.addBlockEvent(this.mobSpawnerEntity.xCoord, this.mobSpawnerEntity.yCoord, this.mobSpawnerEntity.zCoord, Block.mobSpawner.blockID, par1, 0);
    }

    public World getSpawnerWorld()
    {
        return this.mobSpawnerEntity.worldObj;
    }

    public int getSpawnerX()
    {
        return this.mobSpawnerEntity.xCoord;
    }

    public int getSpawnerY()
    {
        return this.mobSpawnerEntity.yCoord;
    }

    public int getSpawnerZ()
    {
        return this.mobSpawnerEntity.zCoord;
    }
	
	public void updateSpawner()
    {
		if(!mobSet)
			return;

		double d0;

		if (this.getSpawnerWorld().isRemote && (fuel > 0 || Config.wrathCost <= 0))
		{
			double d1 = (double)((float)this.getSpawnerX() + this.getSpawnerWorld().rand.nextFloat());
			double d2 = (double)((float)this.getSpawnerY() + this.getSpawnerWorld().rand.nextFloat());
			d0 = (double)((float)this.getSpawnerZ() + this.getSpawnerWorld().rand.nextFloat());
			//this.getSpawnerWorld().spawnParticle("smoke", d1, d2, d0, 0.0D, 0.0D, 0.0D);
			//this.getSpawnerWorld().spawnParticle("flame", d1, d2, d0, 0.0D, 0.0D, 0.0D);
			Thaumcraft.proxy.sparkle((float)d1, (float)d2, (float)d0, 0x870404);

			if (this.spawnDelay > 0)
			{
				--this.spawnDelay;
			}

			this.field_98284_d = this.field_98287_c;
			this.field_98287_c = (this.field_98287_c + (double)(1000.0F / ((float)this.spawnDelay + 200.0F))) % 360.0D;
		}
		else if(Config.wrathCage)
		{
			if(Config.wrathCost > 0 && fuel <= 0){
				if(mobSpawnerEntity.special >= Config.wrathCost){
					mobSpawnerEntity.special -= Config.wrathCost;
					slothful = false;
					fuel = Config.wrathEff;
					mobSpawnerEntity.worldObj.markBlockForUpdate(getSpawnerX(), getSpawnerY(), getSpawnerZ());
				}
				else if(mobSpawnerEntity.wrath >= Config.wrathCost){
					mobSpawnerEntity.wrath -= Config.wrathCost;
					slothful = false;
					fuel = Config.wrathEff;
					mobSpawnerEntity.worldObj.markBlockForUpdate(getSpawnerX(), getSpawnerY(), getSpawnerZ());
				}
				else if(mobSpawnerEntity.sloth >= Config.wrathCost){
					mobSpawnerEntity.sloth -= Config.wrathCost;
					slothful = true;
					fuel = Config.wrathEff;
					mobSpawnerEntity.worldObj.markBlockForUpdate(getSpawnerX(), getSpawnerY(), getSpawnerZ());
				}
			}
			
			if (this.spawnDelay == -1)
			{
				this.updateDelay();
			}

			if(fuel <= 0 && Config.wrathCost > 0)
				return;

			if (this.spawnDelay > 0)
			{
				--this.spawnDelay;
				return;
			}
			
			for (int i = 0; i < this.spawnCount && (fuel > 0 || Config.wrathCost <= 0); ++i)
			{
				Entity entity = EntityList.createEntityByName(this.getEntityNameToSpawn(), this.getSpawnerWorld());

				if (entity == null)
				{
					return;
				}

				int j = this.getSpawnerWorld().getEntitiesWithinAABB(entity.getClass(), AxisAlignedBB.getAABBPool().getAABB((double)this.getSpawnerX(), (double)this.getSpawnerY(), (double)this.getSpawnerZ(), (double)(this.getSpawnerX() + 1), (double)(this.getSpawnerY() + 1), (double)(this.getSpawnerZ() + 1)).expand((double)(this.spawnRange * 2), 4.0D, (double)(this.spawnRange * 2))).size();

				if (j >= this.maxNearbyEntities)
				{
					this.updateDelay();
					return;
				}

				d0 = (double)this.getSpawnerX() + (this.getSpawnerWorld().rand.nextDouble() - this.getSpawnerWorld().rand.nextDouble()) * (double)this.spawnRange;
				double d3 = (double)(this.getSpawnerY() + this.getSpawnerWorld().rand.nextInt(3) - 1);
				double d4 = (double)this.getSpawnerZ() + (this.getSpawnerWorld().rand.nextDouble() - this.getSpawnerWorld().rand.nextDouble()) * (double)this.spawnRange;
				EntityLiving entityliving = entity instanceof EntityLiving ? (EntityLiving)entity : null;
				entity.setLocationAndAngles(d0, d3, d4, this.getSpawnerWorld().rand.nextFloat() * 360.0F, 0.0F);

				if (
				//entityliving == null || 
				entityCanSpawn(entityliving))
				//entityliving.getCanSpawnHere())
				{
					this.spawnMob(entity);
					this.getSpawnerWorld().playAuxSFX(2004, this.getSpawnerX(), this.getSpawnerY(), this.getSpawnerZ(), 0);

					if (entityliving != null)
					{
						entityliving.spawnExplosionParticle();
						fuel--;
					}

					this.updateDelay();
				}
				else if(entityliving != null)
					entityliving.isDead = true;
			}

		}
    }
	
	/**
     * Checks if the entity's current position is a valid location to spawn this entity.
     */
    public boolean entityCanSpawn(EntityLiving entity)
    {
        return entity.worldObj.checkNoEntityCollision(entity.boundingBox) && entity.worldObj.getCollidingBoundingBoxes(entity, entity.boundingBox).isEmpty() && !entity.worldObj.isAnyLiquid(entity.boundingBox);
    }
	
	private void updateDelay()
    {
        if (this.maxSpawnDelay <= this.minSpawnDelay)
        {
            this.spawnDelay = this.minSpawnDelay;
        }
        else
        {
            int i = this.maxSpawnDelay - this.minSpawnDelay;
            this.spawnDelay = this.minSpawnDelay + this.getSpawnerWorld().rand.nextInt(i);
        }
		
		if(slothful)
			spawnDelay += 200;

        this.updateSpawnerBlock(1);
    }
	
	public void readFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        mobID = par1NBTTagCompound.getString("EntityId");
		if(Config.spawnerMobs.containsKey(mobID))
			aspect = Config.spawnerMobs.get(mobID);
		else
			aspect = Aspect.GREED;
		mobSet = par1NBTTagCompound.getBoolean("MobSet");
		slothful = par1NBTTagCompound.getBoolean("Slothful");
		this.fuel = par1NBTTagCompound.getShort("Fuel");
        this.spawnDelay = par1NBTTagCompound.getShort("Delay");

        //if (par1NBTTagCompound.hasKey("MinSpawnDelay"))
        //{
        //    this.minSpawnDelay = par1NBTTagCompound.getShort("MinSpawnDelay");
        //    this.maxSpawnDelay = par1NBTTagCompound.getShort("MaxSpawnDelay");
        //    this.spawnCount = par1NBTTagCompound.getShort("SpawnCount");
        //}

        if (par1NBTTagCompound.hasKey("MaxNearbyEntities"))
        {
            this.maxNearbyEntities = par1NBTTagCompound.getShort("MaxNearbyEntities");
        }

        if (par1NBTTagCompound.hasKey("SpawnRange"))
        {
            this.spawnRange = par1NBTTagCompound.getShort("SpawnRange");
        }

        if (this.getSpawnerWorld() != null && this.getSpawnerWorld().isRemote)
        {
            this.renderEntity = null;
        }
    }

    public void writeToNBT(NBTTagCompound par1NBTTagCompound)
    {
        par1NBTTagCompound.setString("EntityId", this.getEntityNameToSpawn());
		par1NBTTagCompound.setBoolean("MobSet", mobSet);
		par1NBTTagCompound.setBoolean("Slothful", slothful);
		par1NBTTagCompound.setShort("Fuel", (short)this.fuel);
        par1NBTTagCompound.setShort("Delay", (short)this.spawnDelay);
        //par1NBTTagCompound.setShort("MinSpawnDelay", (short)this.minSpawnDelay);
        //par1NBTTagCompound.setShort("MaxSpawnDelay", (short)this.maxSpawnDelay);
        //par1NBTTagCompound.setShort("SpawnCount", (short)this.spawnCount);
        par1NBTTagCompound.setShort("MaxNearbyEntities", (short)this.maxNearbyEntities);
        par1NBTTagCompound.setShort("SpawnRange", (short)this.spawnRange);

    }
	
	public Entity spawnMob(Entity par1Entity)
    {
        if (par1Entity instanceof EntityLivingBase && par1Entity.worldObj != null)
        {
            ((EntityLiving)par1Entity).onSpawnWithEgg((EntityLivingData)null);
            this.getSpawnerWorld().spawnEntityInWorld(par1Entity);
        }

        return par1Entity;
    }
	
	/**
     * Sets the delay to minDelay if parameter given is 1, else return false.
     */
    public boolean setDelayToMin(int par1)
    {
        if (par1 == 1 && this.getSpawnerWorld().isRemote)
        {
            this.spawnDelay = this.minSpawnDelay;
            return true;
        }
        else
        {
            return false;
        }
    }

    @SideOnly(Side.CLIENT)
    public Entity getEntityForRender()
    {
        if (this.renderEntity == null)
        {
            Entity entity = EntityList.createEntityByName(this.getEntityNameToSpawn(), (World)null);
            entity = this.spawnMob(entity);
            this.renderEntity = entity;
        }

        return this.renderEntity;
    }

}
