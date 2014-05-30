package com.spiteful.forbidden.items.wands;

import java.util.List;

import javax.swing.Icon;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import com.spiteful.forbidden.Forbidden;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemWandCaps extends Item {

	public final String[] types = {"alchemical", "vinteum", "soul", "manasteel", "manasteel_inert"};
	public IIcon[] icon;


	public ItemWandCaps() {
		this.setMaxStackSize(64);
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
		this.setCreativeTab(Forbidden.tab);
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister ir) {
		icon = new IIcon[types.length];
		for(int x = 0; x < types.length; x++)
			this.icon[x] = ir.registerIcon("forbidden:wand_cap_" + types[x]);
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int meta) {
		return this.icon[meta];
	}

	@SideOnly(Side.CLIENT)
	public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List) {
		for(int x = 0; x < types.length; x++){
			par3List.add(new ItemStack(this, 1, x));
		}
	}

	public String getUnlocalizedName(ItemStack par1ItemStack) {
		return super.getUnlocalizedName() + "." + types[par1ItemStack.getItemDamage()];
	}
}
