package fox.spiteful.forbidden.blocks;

import java.util.Random;

import fox.spiteful.forbidden.Forbidden;
import fox.spiteful.forbidden.tiles.TileEntityWrathCage;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import fox.spiteful.forbidden.Config;
import fox.spiteful.forbidden.items.ForbiddenItems;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockWrathCage extends BlockContainer {
	protected BlockWrathCage() {
		super(Material.iron);
		this.setCreativeTab(Forbidden.tab);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityWrathCage();
	}

	@Override
	public Item getItem(World world, int x, int y, int z) {
		return Item.getItemFromBlock(ForbiddenBlocks.wrathCage);
	}

	@Override
	public int quantityDropped(Random rand) {
		return 1;
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		ItemStack held = player.getCurrentEquippedItem();
		if (held != null && held.getItem() == ForbiddenItems.mobCrystal) {
			NBTTagCompound nbttagcompound = held.getTagCompound();
			if (nbttagcompound == null)
				return false;
			String string = nbttagcompound.getString("mob");
			if (string != null) {
				if (!world.isRemote) {
					TileEntityWrathCage spawner = (TileEntityWrathCage) world.getTileEntity(x, y, z);
					String mob = null;
					ItemStack crystal = null;
					if (spawner.getSpawnerLogic().isMobSet()) {
						mob = spawner.getSpawnerLogic().getEntityNameToSpawn();
						crystal = new ItemStack(ForbiddenItems.mobCrystal, 1);
						NBTTagString mobTag = new NBTTagString(mob);
						crystal.setTagInfo("mob", mobTag);
					}
					spawner.getSpawnerLogic().setMobID(string);
					spawner.getSpawnerLogic().mobIsSet(true);
					spawner.checkAspect();
					world.markBlockForUpdate(x, y, z);
					player.setCurrentItemOrArmor(0, crystal);
				}
				player.swingItem();
				return true;
			}
		} else if (held != null && held.getItem() == ForbiddenItems.fork && Config.wrathCost > 0) {
			TileEntityWrathCage spawner = (TileEntityWrathCage) world.getTileEntity(x, y, z);
			if (++spawner.mode > 2)
				spawner.mode = 0;
			player.swingItem();
		}
		return false;
	}

	@Override
	public void onBlockPreDestroy(World world, int x, int y, int z, int side) {
		TileEntityWrathCage spawner = (TileEntityWrathCage) world.getTileEntity(x, y, z);

		if (spawner != null && spawner.getSpawnerLogic().isMobSet()) {
			float f = world.rand.nextFloat() * 0.8F + 0.1F;
			float f1 = world.rand.nextFloat() * 0.8F + 0.1F;
			float f2 = world.rand.nextFloat() * 0.8F + 0.1F;

			ItemStack crystal = new ItemStack(ForbiddenItems.mobCrystal);
			crystal.setTagCompound(new NBTTagCompound());
			String mob = spawner.getSpawnerLogic().getEntityNameToSpawn();
			crystal.stackTagCompound.setString("mob", mob);

			EntityItem entityitem = new EntityItem(world, (double) ((float) x + f), (double) ((float) y + f1), (double) ((float) z + f2), crystal);

			entityitem.motionX = (double) ((float) world.rand.nextGaussian() * 0.05F);
			entityitem.motionY = (double) ((float) world.rand.nextGaussian() * 0.05F + 0.2F);
			entityitem.motionZ = (double) ((float) world.rand.nextGaussian() * 0.05F);
			world.spawnEntityInWorld(entityitem);

		}

		super.onBlockPreDestroy(world, x, y, z, side);
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
		return new ItemStack(getItem(null, x, y, z));
	}
}
