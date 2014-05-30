package com.spiteful.forbidden.items;

import java.util.List;

import javax.swing.Icon;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import thaumcraft.api.aspects.Aspect;

import com.spiteful.forbidden.Config;
import com.spiteful.forbidden.Forbidden;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemMobCrystal extends Item {

	IIcon[] icons;
	
	public ItemMobCrystal() {
		this.setMaxStackSize(1);
		//this.setHasSubtypes(true);
		this.setMaxDamage(0);
		this.setCreativeTab(Forbidden.crysTab);
	}
	
	/**
	 * allows items to add custom lines of information to the mouseover description
	 */
	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
		if (par1ItemStack.hasTagCompound())
		{
			NBTTagCompound nbttagcompound = par1ItemStack.getTagCompound();
			String string = nbttagcompound.getString("mob");
			
			if(string != null)
			{
				Aspect mobAspect = Config.spawnerMobs.get(string);
				if(mobAspect != null)
					par3List.add(mobAspect.getName());
			}
		}
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister ir)
	{
		icons = new IIcon[2];

		icons[0] = ir.registerIcon("forbidden:emptycrystal");
		icons[1] = ir.registerIcon("forbidden:mobcrystal");
	}	
	
	/**
	 * Player, Render pass, and item usage sensitive version of getIconIndex.
	 *
	 * @param stack The item stack to get the icon for. (Usually this, and usingItem will be the same if usingItem is not null)
	 * @param renderPass The pass to get the icon for, 0 is default.
	 * @param player The player holding the item
	 * @param usingItem The item the player is actively using. Can be null if not using anything.
	 * @param useRemaining The ticks remaining for the active item.
	 * @return The icon index
	 */
	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(ItemStack stack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining)
	{
		if (stack.hasTagCompound())
		{
			NBTTagCompound nbttagcompound = stack.getTagCompound();
			String string = nbttagcompound.getString("mob");
			
			if(string != null)
				return icons[1];
		}
		
		return icons[0];
	}
	
	/**
	 * Returns the icon index of the stack given as argument.
	 */
	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIconIndex(ItemStack par1ItemStack)
	{
		if (par1ItemStack.hasTagCompound())
		{
			NBTTagCompound nbttagcompound = par1ItemStack.getTagCompound();
			String string = nbttagcompound.getString("mob");
			
			if(string != null)
				return icons[1];
		}
		
		return icons[0];
	}

	/**
	 * returns a list of items with the same ID, but different meta (eg: dye returns 16 items)
	 */
	@SideOnly(Side.CLIENT)
	@Override
	public void getSubItems(Item par1, CreativeTabs par2CreativeTabs, List par3List)
	{
		par3List.add(new ItemStack(par1, 1, 0));
		
		for (String mob: Config.spawnerMobs.keySet()){
			ItemStack crystal = new ItemStack(par1, 1, 0);
			crystal.setTagCompound(new NBTTagCompound());
			crystal.stackTagCompound.setString("mob", mob);
			par3List.add(crystal);
		}
	}
	
	public String getItemDisplayName(ItemStack par1ItemStack)
	{
		if (par1ItemStack.hasTagCompound())
		{
			NBTTagCompound nbttagcompound = par1ItemStack.getTagCompound();
			String string = nbttagcompound.getString("mob");
			
			if(string != null)
				return ("" + StatCollector.translateToLocal("entity." + string + ".name") + " " + StatCollector.translateToLocal("item.MobCrystal.name")).trim();
		}
		
		return ("" + StatCollector.translateToLocal("item.MobCrystalEmpty.name")).trim();
	}
}
