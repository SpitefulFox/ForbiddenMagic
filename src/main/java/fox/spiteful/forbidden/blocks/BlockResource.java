package fox.spiteful.forbidden.blocks;

import fox.spiteful.forbidden.Forbidden;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.IBlockAccess;

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
