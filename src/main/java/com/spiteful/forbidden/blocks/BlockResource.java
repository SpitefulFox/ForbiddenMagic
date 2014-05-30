package com.spiteful.forbidden.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.IBlockAccess;

import com.spiteful.forbidden.Forbidden;

public class BlockResource extends Block
{
	public BlockResource()
	{
		super(Material.iron);
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
	@Override
	public boolean isBeaconBase(IBlockAccess worldObj, int x, int y, int z, int beaconX, int beaconY, int beaconZ)
	{
		return true;
	}
}
