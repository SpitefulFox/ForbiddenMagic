package com.spiteful.forbidden.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import thaumcraft.api.IRepairable;

import com.spiteful.forbidden.Config;
import com.spiteful.forbidden.Forbidden;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemTaintPickaxe extends ItemPickaxe implements IRepairable {
	public IIcon icon;

	public ItemTaintPickaxe(ToolMaterial enumtoolmaterial) {
		super(enumtoolmaterial);
		this.setCreativeTab(Forbidden.tab);
		this.setHarvestLevel("pickaxe", 5);
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister ir) {
		this.icon = ir.registerIcon("forbidden:taintpickaxe");
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int par1) {
		return this.icon;
	}

	@Override
	public EnumRarity getRarity(ItemStack itemstack) {
		return EnumRarity.rare;
	}

	@Override
	public boolean getIsRepairable(ItemStack stack, ItemStack stack2) {
		return stack2.isItemEqual(new ItemStack(Config.thaumcraftResource.getItem(), 1, 2)) ? true : super.getIsRepairable(stack, stack2);
	}
}
