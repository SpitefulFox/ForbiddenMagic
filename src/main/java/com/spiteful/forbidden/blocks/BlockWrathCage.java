package com.spiteful.forbidden.blocks;

import java.util.Random;

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

import com.spiteful.forbidden.Config;
import com.spiteful.forbidden.Forbidden;
import com.spiteful.forbidden.items.ForbiddenItems;
import com.spiteful.forbidden.tiles.TileEntityWrathCage;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockWrathCage extends BlockContainer
{
	protected BlockWrathCage()
	{
		super(Material.iron);
		this.setCreativeTab(Forbidden.tab);
	}

	/**
	 * Returns a new instance of a block's tile entity class. Called on placing the block.
	 */
	@Override
	public TileEntity createNewTileEntity(World par1World, int meta)
	{
		return new TileEntityWrathCage();
	}

	/**
	 * Returns the ID of the items to drop on destruction.
	 */
	@Override
	public Item getItem(World par1World, int par2, int par3, int par4)
	{
		return Item.getItemFromBlock(ForbiddenBlocks.wrathCage);
	}

	/**
	 * Returns the quantity of items to drop on block destruction.
	 */
	@Override
	public int quantityDropped(Random par1Random)
	{
		return 1;
	}

	/**
	 * Called upon block activation (right click on the block.)
	 */
	@Override
	public boolean onBlockActivated(World par1World, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
	{
		ItemStack held = player.getCurrentEquippedItem();
		if(held != null && held.getItem() == ForbiddenItems.mobCrystal){
			NBTTagCompound nbttagcompound = held.getTagCompound();
			if(nbttagcompound == null)
				return false;
			String string = nbttagcompound.getString("mob");
			if(string != null)
			{
				if(!par1World.isRemote){
					TileEntityWrathCage spawner = (TileEntityWrathCage)par1World.getTileEntity(x, y, z);
					String mob = null;
					ItemStack crystal = null;
					if(spawner.getSpawnerLogic().isMobSet()){
						mob = spawner.getSpawnerLogic().getEntityNameToSpawn();
						crystal = new ItemStack(ForbiddenItems.mobCrystal, 1);
						NBTTagString mobTag = new NBTTagString(mob);
						crystal.setTagInfo("mob", mobTag);
					}
					spawner.getSpawnerLogic().setMobID(string);
					spawner.getSpawnerLogic().mobIsSet(true);
					spawner.checkAspect();
					par1World.markBlockForUpdate(x, y, z);
					player.setCurrentItemOrArmor(0, crystal);
				}
				player.swingItem();
				return true;
			}
		}
		else if(held != null && held.getItem() == ForbiddenItems.fork && Config.wrathCost > 0){
			TileEntityWrathCage spawner = (TileEntityWrathCage)par1World.getTileEntity(x, y, z);
			if(++spawner.mode > 2)
				spawner.mode = 0;
			player.swingItem();
		}
		return false;
	}
		
	/**
	 * Called on server worlds only when the block has been replaced by a different block ID, or the same block with a
	 * different metadata value, but before the new metadata value is set. Args: World, x, y, z, old block ID, old
	 * metadata
	 */
	@Override
	public void onBlockPreDestroy(World par1World, int x, int y, int z, int par6)
	{
		TileEntityWrathCage spawner = (TileEntityWrathCage)par1World.getTileEntity(x, y, z);

		if (spawner != null && spawner.getSpawnerLogic().isMobSet())
		{
			float f = par1World.rand.nextFloat() * 0.8F + 0.1F;
			float f1 = par1World.rand.nextFloat() * 0.8F + 0.1F;
			float f2 = par1World.rand.nextFloat() * 0.8F + 0.1F;

			ItemStack crystal = new ItemStack(ForbiddenItems.mobCrystal, 1);
			crystal.setTagCompound(new NBTTagCompound());
			String mob = spawner.getSpawnerLogic().getEntityNameToSpawn();
			crystal.stackTagCompound.setString("mob", mob);
			
			EntityItem entityitem = new EntityItem(par1World, (double)((float)x + f), (double)((float)y + f1), (double)((float)z + f2), crystal);

			entityitem.motionX = (double)((float)par1World.rand.nextGaussian() * 0.05F);
			entityitem.motionY = (double)((float)par1World.rand.nextGaussian() * 0.05F + 0.2F);
			entityitem.motionZ = (double)((float)par1World.rand.nextGaussian() * 0.05F);
			par1World.spawnEntityInWorld(entityitem);

		}

		super.onBlockPreDestroy(par1World, x, y, z, par6);
	}

	/**
	 * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
	 * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
	 */
	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	/**
	 * only called by clickMiddleMouseButton , and passed to inventory.setCurrentItem (along with isCreative)
	 */
	@SideOnly(Side.CLIENT)
	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z)
    {
		return new ItemStack(getItem(null, x, y, z));
    }
}
