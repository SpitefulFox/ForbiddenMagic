package com.spiteful.forbidden.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;

import com.spiteful.forbidden.Config;
import com.spiteful.forbidden.Forbidden;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemDeadlyShard extends Item {
	public static final String[] vices = new String[] { "wrath", "envy", "taint", "pride", "lust", "sloth", "greed" };

	@SideOnly(Side.CLIENT)
	public IIcon[] icons;

	public ItemDeadlyShard() {
		this.setMaxStackSize(64);
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
		this.setCreativeTab(Forbidden.tab);
		if (Config.noLust)
			vices[4] = "despair";
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister ir) {
		icons = new IIcon[7];

		for (int x = 0; x < 7; x++) {
			icons[x] = ir.registerIcon("forbidden:" + vices[x] + "shard");
		}
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int dam) {
		return this.icons[dam];
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		int i = MathHelper.clamp_int(stack.getItemDamage(), 0, 6);
		return super.getUnlocalizedName() + "." + vices[i];
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void getSubItems(Item item, CreativeTabs tab, List list) {
		for (int j = 0; j < 7; ++j) {
			list.add(new ItemStack(item, 1, j));
		}
	}
}
