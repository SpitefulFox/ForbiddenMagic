package com.spiteful.forbidden.items;

import com.spiteful.forbidden.Forbidden;
import com.spiteful.forbidden.Compat;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import thaumcraft.api.ItemApi;
import thaumcraft.api.wands.WandCap;
import thaumcraft.api.wands.WandRod;
import thaumcraft.common.items.wands.ItemWandCasting;

public class ItemWandCaps extends Item {

	public final String[] types = {"alchemical", "vinteum"};
	public Icon[] icon;


	public ItemWandCaps(int par1) {
		super(par1);
		this.setMaxStackSize(64);
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
		this.setCreativeTab(Forbidden.tab);
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister ir) {
		icon = new Icon[types.length];
		for(int x = 0; x < types.length; x++)
			this.icon[x] = ir.registerIcon("forbidden:wand_cap_" + types[x]);
	}

	@SideOnly(Side.CLIENT)
	public Icon getIconFromDamage(int meta) {
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
