package com.spiteful.forbidden.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
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
		// this.setHasSubtypes(true);
		this.setMaxDamage(0);
		this.setCreativeTab(Forbidden.crysTab);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean z) {
		if (stack.hasTagCompound()) {
			NBTTagCompound nbttagcompound = stack.getTagCompound();
			String string = nbttagcompound.getString("mob");

			if (string != null) {
				Aspect mobAspect = Config.spawnerMobs.get(string);
				if (mobAspect != null)
					list.add(mobAspect.getName());
			}
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister ir) {
		icons = new IIcon[2];

		icons[0] = ir.registerIcon("forbidden:emptycrystal");
		icons[1] = ir.registerIcon("forbidden:mobcrystal");
	}

	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(ItemStack stack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining) {
		if (stack.hasTagCompound()) {
			NBTTagCompound nbttagcompound = stack.getTagCompound();
			String string = nbttagcompound.getString("mob");

			if (string != null)
				return icons[1];
		}

		return icons[0];
	}

	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIconIndex(ItemStack stack) {
		if (stack.hasTagCompound()) {
			NBTTagCompound nbttagcompound = stack.getTagCompound();
			String string = nbttagcompound.getString("mob");

			if (string != null)
				return icons[1];
		}

		return icons[0];
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void getSubItems(Item item, CreativeTabs tab, List list) {
		list.add(new ItemStack(item, 1, 0));

		for (String mob : Config.spawnerMobs.keySet()) {
			ItemStack crystal = new ItemStack(item, 1, 0);
			crystal.setTagCompound(new NBTTagCompound());
			crystal.stackTagCompound.setString("mob", mob);
			list.add(crystal);
		}
	}

	@Override
	public String getItemStackDisplayName(ItemStack stack) {
		if (stack.hasTagCompound()) {
			NBTTagCompound nbttagcompound = stack.getTagCompound();
			String string = nbttagcompound.getString("mob");

			if (string != null)
				return ("" + StatCollector.translateToLocal("entity." + string + ".name") + " " + StatCollector.translateToLocal("item.MobCrystal.name")).trim();
		}

		return ("" + StatCollector.translateToLocal("item.MobCrystalEmpty.name")).trim();
	}
}
