package fox.spiteful.forbidden.blocks;

import java.util.List;
import java.util.Random;

import fox.spiteful.forbidden.Forbidden;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockBlackFlower extends BlockFlower {
	protected BlockBlackFlower() {
		super(1);
		float f = 0.2F;
		this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 2.0F, 0.5F + f);
		this.setCreativeTab(Forbidden.tab);
	}

	public void spreadFlowers(World world, int x, int y, int z, Random rand) {
		int l = 10;
		int i1;
		int j1;
		int k1;

		for (i1 = x - 2; i1 <= x + 2; ++i1) {
			for (j1 = z - 2; j1 <= z + 2; ++j1) {
				for (k1 = y - 1; k1 <= y + 1; ++k1) {
					if (world.getBlock(i1, k1, j1) == this) {
						--l;

						if (l <= 0) {
							return;
						}
					}
				}
			}
		}

		i1 = x + rand.nextInt(3) - 1;
		j1 = y + rand.nextInt(2) - rand.nextInt(2);
		k1 = z + rand.nextInt(3) - 1;

		for (int l1 = 0; l1 < 4; ++l1) {
			if (world.isAirBlock(i1, j1, k1) && this.canBlockStay(world, i1, j1, k1)) {
				x = i1;
				y = j1;
				z = k1;
			}

			i1 = x + rand.nextInt(3) - 1;
			j1 = y + rand.nextInt(2) - rand.nextInt(2);
			k1 = z + rand.nextInt(3) - 1;
		}

		if (world.isAirBlock(i1, j1, k1) && this.canBlockStay(world, i1, j1, k1)) {
			world.setBlock(i1, j1, k1, this, 0, 2);
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int p_149691_1_, int p_149691_2_) {
		return this.blockIcon;
	}

	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z) {
		return super.canPlaceBlockAt(world, x, y, z) && this.canBlockStay(world, x, y, z);
	}

	@Override
	public boolean canBlockStay(World world, int x, int y, int z) {
		Block block = world.getBlock(x, y - 1, z);
		return block.canSustainPlant(world, x, y - 1, z, ForgeDirection.UP, this);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
		list.add(new ItemStack(item, 1, 0));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister ir) {
		this.blockIcon = ir.registerIcon(this.getTextureName());
	}
}
