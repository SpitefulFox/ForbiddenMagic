package com.spiteful.forbidden.items.wands;

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

	public final String[] types = {"tainted", "infernal", "soul", "blood", "witchwood", "totem", "blood_inert", "livingwood", "livingwood_inert", "blood_staff"};
	public Icon[] icon;


	public ItemWandCores(int par1) {
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

		ItemStack wand = ItemApi.getItem("itemWandCasting", 72);
		((ItemWandCasting)wand.getItem()).setCap(wand, (WandCap)WandCap.caps.get("thaumium"));
		((ItemWandCasting)wand.getItem()).setRod(wand, (WandRod)WandRod.rods.get("tainted"));
		par3List.add(wand);
		wand = ItemApi.getItem("itemWandCasting", 72);
		((ItemWandCasting)wand.getItem()).setCap(wand, (WandCap)WandCap.caps.get("thaumium"));
		((ItemWandCasting)wand.getItem()).setRod(wand, (WandRod)WandRod.rods.get("infernal"));
		par3List.add(wand);
		wand = ItemApi.getItem("itemWandCasting", 2000);
		((ItemWandCasting)wand.getItem()).setCap(wand, (WandCap)WandCap.caps.get("orichalcum"));
		((ItemWandCasting)wand.getItem()).setRod(wand, (WandRod)WandRod.rods.get("neutronium"));
		par3List.add(wand);
		if(Compat.pb)
		{
			wand = ItemApi.getItem("itemWandCasting", 84);
			((ItemWandCasting)wand.getItem()).setCap(wand, (WandCap)WandCap.caps.get("soul"));
			((ItemWandCasting)wand.getItem()).setRod(wand, (WandRod)WandRod.rods.get("soul"));
			par3List.add(wand);
		}
		if(Compat.bm)
		{
			wand = ItemApi.getItem("itemWandCasting", 84);
			((ItemWandCasting)wand.getItem()).setCap(wand, (WandCap)WandCap.caps.get("alchemical"));
			((ItemWandCasting)wand.getItem()).setRod(wand, (WandRod)WandRod.rods.get("blood"));
			par3List.add(wand);
		}
		if(Compat.am2)
		{
			wand = ItemApi.getItem("itemWandCasting", 72);
			((ItemWandCasting)wand.getItem()).setCap(wand, (WandCap)WandCap.caps.get("vinteum"));
			((ItemWandCasting)wand.getItem()).setRod(wand, (WandRod)WandRod.rods.get("witchwood"));
			par3List.add(wand);
		}
		if(Compat.totes)
		{
			wand = ItemApi.getItem("itemWandCasting", 24);
			((ItemWandCasting)wand.getItem()).setCap(wand, (WandCap)WandCap.caps.get("thaumium"));
			((ItemWandCasting)wand.getItem()).setRod(wand, (WandRod)WandRod.rods.get("totem"));
			par3List.add(wand);
		}
		if(Compat.botan)
		{
			wand = ItemApi.getItem("itemWandCasting", 84);
			((ItemWandCasting)wand.getItem()).setCap(wand, (WandCap)WandCap.caps.get("manasteel"));
			((ItemWandCasting)wand.getItem()).setRod(wand, (WandRod)WandRod.rods.get("livingwood"));
			par3List.add(wand);
		}
	}

	public String getUnlocalizedName(ItemStack par1ItemStack) {
		return super.getUnlocalizedName() + "." + types[par1ItemStack.getItemDamage()];
	}
}
