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

public class ItemWandCores extends Item {

	public final String[] types = {"tainted", "infernal", "soul", "blood"};
	public Icon[] icon = new Icon[4];


	public ItemWandCores(int par1) {
		super(par1);
		this.setMaxStackSize(64);
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
		this.setCreativeTab(Forbidden.tab);
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister ir) {
		for(int x = 0; x < types.length; x++)
			this.icon[x] = ir.registerIcon("forbidden:wand_rod_" + types[x]);
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

		ItemStack taint = ItemApi.getItem("itemWandCasting", 72);
		((ItemWandCasting)taint.getItem()).setCap(taint, (WandCap)WandCap.caps.get("thaumium"));
		((ItemWandCasting)taint.getItem()).setRod(taint, (WandRod)WandRod.rods.get("tainted"));
		par3List.add(taint);
		ItemStack infernal = ItemApi.getItem("itemWandCasting", 72);
		((ItemWandCasting)infernal.getItem()).setCap(infernal, (WandCap)WandCap.caps.get("thaumium"));
		((ItemWandCasting)infernal.getItem()).setRod(infernal, (WandRod)WandRod.rods.get("infernal"));
		par3List.add(infernal);
		ItemStack creative = ItemApi.getItem("itemWandCasting", 2000);
		((ItemWandCasting)creative.getItem()).setCap(creative, (WandCap)WandCap.caps.get("orichalcum"));
		((ItemWandCasting)creative.getItem()).setRod(creative, (WandRod)WandRod.rods.get("neutronium"));
		par3List.add(creative);
		if(Compat.pb)
		{
			ItemStack soul = ItemApi.getItem("itemWandCasting", 72);
			((ItemWandCasting)creative.getItem()).setCap(soul, (WandCap)WandCap.caps.get("thaumium"));
			((ItemWandCasting)creative.getItem()).setRod(soul, (WandRod)WandRod.rods.get("soul"));
			par3List.add(soul);
		}
		if(Compat.bm)
		{
			ItemStack blood = ItemApi.getItem("itemWandCasting", 72);
			((ItemWandCasting)creative.getItem()).setCap(blood, (WandCap)WandCap.caps.get("alchemical"));
			((ItemWandCasting)creative.getItem()).setRod(blood, (WandRod)WandRod.rods.get("blood"));
			par3List.add(blood);
		}
	}

	public String getUnlocalizedName(ItemStack par1ItemStack) {
		return super.getUnlocalizedName() + "." + types[par1ItemStack.getItemDamage()];
	}
}
