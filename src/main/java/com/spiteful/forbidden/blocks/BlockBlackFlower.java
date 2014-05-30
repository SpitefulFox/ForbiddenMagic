package com.spiteful.forbidden.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import com.spiteful.forbidden.Forbidden;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockBlackFlower extends BlockFlower
{
	protected BlockBlackFlower()
	{
		super(1);
		float f = 0.2F;
		this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 2.0F, 0.5F + f);
		this.setTickRandomly(true);
		this.setCreativeTab(Forbidden.tab);
	}

	/**
	 * Ticks the block if it's been scheduled
	 */
	@Override
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
					if (par1World.getBlock(i1, k1, j1) == this)
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
			par1World.setBlock(i1, j1, k1, this, 0, 2);
		}
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int p_149691_1_, int p_149691_2_)
	{
		return this.blockIcon;
	}
	
	/**
	 * Checks to see if its valid to put this block at the specified coordinates. Args: world, x, y, z
	 */
	@Override
	public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4)
	{
		return super.canPlaceBlockAt(par1World, par2, par3, par4) && this.canBlockStay(par1World, par2, par3, par4);
	}

	/**
	 * Can this block stay at this position.  Similar to canPlaceBlockAt except gets checked often with plants.
	 */
	@Override
	public boolean canBlockStay(World par1World, int par2, int par3, int par4)
	{
		Block block = par1World.getBlock(par2, par3 - 1, par4);
		return (block == Blocks.dirt || block == Blocks.grass);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
    public void getSubBlocks(Item p_149666_1_, CreativeTabs p_149666_2_, List p_149666_3_)
    {
		p_149666_3_.add(new ItemStack(p_149666_1_, 1, 0));
    }
	
	@SideOnly(Side.CLIENT)
	@Override
    public void registerBlockIcons(IIconRegister p_149651_1_)
    {
        this.blockIcon = p_149651_1_.registerIcon(this.getTextureName());
    }
}
