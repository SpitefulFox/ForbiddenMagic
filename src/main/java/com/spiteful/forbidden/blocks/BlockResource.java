package com.spiteful.forbidden.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.IBlockAccess;

import com.spiteful.forbidden.Forbidden;

public class BlockResource extends Block {
	public BlockResource() {
		super(Material.iron);
		this.setCreativeTab(Forbidden.tab);
	}

	@Override
	public boolean isBeaconBase(IBlockAccess worldObj, int x, int y, int z, int beaconX, int beaconY, int beaconZ) {
		return true;
	}
}
