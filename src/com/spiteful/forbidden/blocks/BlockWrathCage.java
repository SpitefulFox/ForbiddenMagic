package com.spiteful.forbidden.blocks;

import com.spiteful.forbidden.tiles.TileEntityWrathCage;
import com.spiteful.forbidden.items.ForbiddenItems;
import com.spiteful.forbidden.Forbidden;
import com.spiteful.forbidden.Config;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.world.World;

public class BlockWrathCage extends BlockContainer
{
	protected BlockWrathCage(int par1)
	{
		super(par1, Material.iron);
		this.setCreativeTab(Forbidden.tab);
	}

	/**
	 * Returns a new instance of a block's tile entity class. Called on placing the block.
	 */
	public TileEntity createNewTileEntity(World par1World)
	{
		return new TileEntityWrathCage();
	}

	/**
	 * Returns the ID of the items to drop on destruction.
	 */
	public int idDropped(int par1, Random par2Random, int par3)
	{
		return blockID;
	}

	/**
	 * Returns the quantity of items to drop on block destruction.
	 */
	public int quantityDropped(Random par1Random)
	{
		return 1;
	}

	/**
	 * Called upon block activation (right click on the block.)
	 */
	public boolean onBlockActivated(World par1World, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
	{
		ItemStack held = player.getCurrentEquippedItem();
		if(held != null && held.itemID == ForbiddenItems.mobCrystal.itemID){
			NBTTagCompound nbttagcompound = held.getTagCompound();
			if(nbttagcompound == null)
				return false;
			NBTTagString nbttagstring = (NBTTagString)nbttagcompound.getTag("mob");

			if (nbttagstring != null)
			{
				if(!par1World.isRemote){
					TileEntityWrathCage spawner = (TileEntityWrathCage)par1World.getBlockTileEntity(x, y, z);
					String mob = null;
					ItemStack crystal = null;
					if(spawner.getSpawnerLogic().isMobSet()){
						mob = spawner.getSpawnerLogic().getEntityNameToSpawn();
						crystal = new ItemStack(ForbiddenItems.mobCrystal, 1);
						NBTTagString mobTag = new NBTTagString("mob", mob);
						crystal.setTagInfo("mob", mobTag);
					}
					spawner.getSpawnerLogic().setMobID(nbttagstring.toString());
					spawner.getSpawnerLogic().mobIsSet(true);
					spawner.checkAspect();
					par1World.markBlockForUpdate(x, y, z);
					player.setCurrentItemOrArmor(0, crystal);
				}
				player.swingItem();
				return true;
			}
		}
		else if(held != null && held.itemID == ForbiddenItems.fork.itemID && Config.wrathCost > 0){
			TileEntityWrathCage spawner = (TileEntityWrathCage)par1World.getBlockTileEntity(x, y, z);
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
	public void breakBlock(World par1World, int x, int y, int z, int par5, int par6)
	{
		TileEntityWrathCage spawner = null;
		
		if(par1World.getBlockTileEntity(x, y, z) instanceof TileEntityWrathCage)
			spawner = (TileEntityWrathCage)par1World.getBlockTileEntity(x, y, z);

		if (spawner != null && spawner.getSpawnerLogic().isMobSet())
		{
			float f = par1World.rand.nextFloat() * 0.8F + 0.1F;
			float f1 = par1World.rand.nextFloat() * 0.8F + 0.1F;
			float f2 = par1World.rand.nextFloat() * 0.8F + 0.1F;

			ItemStack crystal = new ItemStack(ForbiddenItems.mobCrystal, 1);
			String mob = spawner.getSpawnerLogic().getEntityNameToSpawn();
			NBTTagString mobTag = new NBTTagString("mob", mob);
			crystal.setTagInfo("mob", mobTag);
			
			EntityItem entityitem = new EntityItem(par1World, (double)((float)x + f), (double)((float)y + f1), (double)((float)z + f2), crystal);

			entityitem.motionX = (double)((float)par1World.rand.nextGaussian() * 0.05F);
			entityitem.motionY = (double)((float)par1World.rand.nextGaussian() * 0.05F + 0.2F);
			entityitem.motionZ = (double)((float)par1World.rand.nextGaussian() * 0.05F);
			par1World.spawnEntityInWorld(entityitem);

		}

		super.breakBlock(par1World, x, y, z, par5, par6);
	}

	/**
	 * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
	 * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
	 */
	public boolean isOpaqueCube()
	{
		return false;
	}

	@SideOnly(Side.CLIENT)

	/**
	 * only called by clickMiddleMouseButton , and passed to inventory.setCurrentItem (along with isCreative)
	 */
	public int idPicked(World par1World, int par2, int par3, int par4)
	{
		return blockID;
	}
}
