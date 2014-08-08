package com.spiteful.forbidden.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockArcaneCake extends Block {
	@SideOnly(Side.CLIENT)
	private IIcon cakeTopIcon;
	@SideOnly(Side.CLIENT)
	private IIcon cakeBottomIcon;
	@SideOnly(Side.CLIENT)
	private IIcon cakeInsideIcon;

	protected BlockArcaneCake() {
		super(Material.cake);
		this.setTickRandomly(true);
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {
		int l = world.getBlockMetadata(x, y, z);
		float f = 0.0625F;
		float f1 = (float) (1 + l) / 12.0F;
		this.setBlockBounds(f1, 0.0F, f, 1.0F - f, 0.5F, 1.0F - f);
	}

	@Override
	public void setBlockBoundsForItemRender() {
		float f = 0.0625F;
		float f1 = 0.5F;
		this.setBlockBounds(f, 0.0F, f, 1.0F - f, f1, 1.0F - f);
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
		int l = world.getBlockMetadata(x, y, z);
		float f = 0.0625F;
		float f1 = (float) (1 + l) / 12.0F;
		float f2 = 0.5F;
		return AxisAlignedBB.getAABBPool().getAABB((double) ((float) x + f1), (double) y, (double) ((float) z + f), (double) ((float) (x + 1) - f), (double) ((float) y + f2 - f), (double) ((float) (z + 1) - f));
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z) {
		int l = world.getBlockMetadata(x, y, z);
		float f = 0.0625F;
		float f1 = (float) (1 + l) / 12.0F;
		float f2 = 0.5F;
		return AxisAlignedBB.getAABBPool().getAABB((double) ((float) x + f1), (double) y, (double) ((float) z + f), (double) ((float) (x + 1) - f), (double) ((float) y + f2), (double) ((float) (z + 1) - f));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int par1, int x) {
		return par1 == 1 ? this.cakeTopIcon : (par1 == 0 ? this.cakeBottomIcon : (x > 0 && par1 == 4 ? this.cakeInsideIcon : this.blockIcon));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister ir) {
		this.blockIcon = ir.registerIcon("forbidden:cake_side");
		this.cakeInsideIcon = ir.registerIcon("forbidden:cake_inner");
		this.cakeTopIcon = ir.registerIcon("forbidden:cake_top");
		this.cakeBottomIcon = ir.registerIcon("forbidden:cake_bottom");
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		this.eatCakeSlice(world, x, y, z, player);
		return true;
	}

	@Override
	public void onBlockClicked(World world, int x, int y, int z, EntityPlayer player) {
		this.eatCakeSlice(world, x, y, z, player);
	}

	private void eatCakeSlice(World world, int x, int y, int z, EntityPlayer player) {
		if (player.canEat(false)) {
			player.getFoodStats().addStats(2, 1.0F);
			int l = world.getBlockMetadata(x, y, z) + 1;

			if (l >= 12) {
				world.setBlockToAir(x, y, z);
			} else {
				world.setBlockMetadataWithNotify(x, y, z, l, 2);
			}

		}
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random par5Random) {
		super.updateTick(world, x, y, z, par5Random);
		int l = world.getBlockMetadata(x, y, z);

		if (l > 0) {
			// if (par5Random.nextInt((int)(25.0F / f) + 1) == 0)
			// {
			--l;
			world.setBlockMetadataWithNotify(x, y, z, l, 2);
			// }
		}
	}

	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z) {
		return !super.canPlaceBlockAt(world, x, y, z) ? false : this.canBlockStay(world, x, y, z);
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
		if (!this.canBlockStay(world, x, y, z)) {
			world.setBlockToAir(x, y, z);
		}
	}

	@Override
	public boolean canBlockStay(World world, int x, int y, int z) {
		return world.getBlock(x, y - 1, z).getMaterial().isSolid();
	}

	@Override
	public int quantityDropped(Random par1Random) {
		return 0;
	}

	@Override
	public Item getItem(World world, int x, int y, int z) {
		return null;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
		return new ItemStack(this);
	}
}
