package com.spiteful.forbidden.blocks;

import com.spiteful.forbidden.Forbidden;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;

import net.minecraftforge.common.ForgeDirection;

public class BlockBlackFlower extends BlockFlower
{
	protected BlockBlackFlower(int par1)
	{
		super(par1);
		float f = 0.2F;
		this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 2.0F, 0.5F + f);
		this.setTickRandomly(true);
		this.setCreativeTab(Forbidden.tab);
	}

	/**
	 * Ticks the block if it's been scheduled
	 */
	public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
	{
		if (!par1World.isRemote && par5Random.nextInt(30) == 1)
			spreadFlowers(par1World, par2, par3, par4, par5Random);
	}
	
	public void spreadFlowers(World par1World, int par2, int par3, int par4, Random par5Random){
		int l = 10;
		int i1;
		int j1;
		int k1;

		for (i1 = par2 - 2; i1 <= par2 + 2; ++i1)
		{
			for (j1 = par4 - 2; j1 <= par4 + 2; ++j1)
			{
				for (k1 = par3 - 1; k1 <= par3 + 1; ++k1)
				{
					if (par1World.getBlockId(i1, k1, j1) == this.blockID)
					{
						--l;

						if (l <= 0)
						{
							return;
						}
					}
				}
			}
		}

		i1 = par2 + par5Random.nextInt(3) - 1;
		j1 = par3 + par5Random.nextInt(2) - par5Random.nextInt(2);
		k1 = par4 + par5Random.nextInt(3) - 1;

		for (int l1 = 0; l1 < 4; ++l1)
		{
			if (par1World.isAirBlock(i1, j1, k1) && this.canBlockStay(par1World, i1, j1, k1))
			{
				par2 = i1;
				par3 = j1;
				par4 = k1;
			}

			i1 = par2 + par5Random.nextInt(3) - 1;
			j1 = par3 + par5Random.nextInt(2) - par5Random.nextInt(2);
			k1 = par4 + par5Random.nextInt(3) - 1;
		}

		if (par1World.isAirBlock(i1, j1, k1) && this.canBlockStay(par1World, i1, j1, k1))
		{
			par1World.setBlock(i1, j1, k1, this.blockID, 0, 2);
		}
	}
	
	/**
	 * Checks to see if its valid to put this block at the specified coordinates. Args: world, x, y, z
	 */
	public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4)
	{
		return super.canPlaceBlockAt(par1World, par2, par3, par4) && this.canBlockStay(par1World, par2, par3, par4);
	}

	/**
	 * Gets passed in the blockID of the block below and supposed to return true if its allowed to grow on the type of
	 * blockID passed in. Args: blockID
	 */
	protected boolean canThisPlantGrowOnThisBlockID(int par1)
	{
		return Block.opaqueCubeLookup[par1];
	}

	/**
	 * Can this block stay at this position.  Similar to canPlaceBlockAt except gets checked often with plants.
	 */
	public boolean canBlockStay(World par1World, int par2, int par3, int par4)
	{
		int l = par1World.getBlockId(par2, par3 - 1, par4);
		return (l == Block.dirt.blockID || l == Block.grass.blockID);
	}
}
