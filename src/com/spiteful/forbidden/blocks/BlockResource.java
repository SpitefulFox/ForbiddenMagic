package com.spiteful.forbidden.blocks;

import com.spiteful.forbidden.items.ForbiddenItems;
import com.spiteful.forbidden.*;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockResource extends Block
{
	public BlockResource(int par1)
	{
		super(par1, Material.iron);
		this.setCreativeTab(Forbidden.tab);
	}

	/**
	 * Determines if this block can be used as the base of a beacon.
	 *
	 * @param world The current world
	 * @param x X Position
	 * @param y Y Position
	 * @param z Z position
	 * @param beaconX Beacons X Position
	 * @param beaconY Beacons Y Position
	 * @param beaconZ Beacons Z Position
	 * @return True, to support the beacon, and make it active with this block.
	 */
	public boolean isBeaconBase(World worldObj, int x, int y, int z, int beaconX, int beaconY, int beaconZ)
	{
		return true;
	}
}
