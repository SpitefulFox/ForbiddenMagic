package com.spiteful.forbidden.items;

import net.minecraft.block.Block;
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

public class ItemTaintPickaxe extends ItemPickaxe implements IRepairable
{
	public IIcon icon;

	public ItemTaintPickaxe(ToolMaterial enumtoolmaterial)
	{
		super(enumtoolmaterial);
		this.setCreativeTab(Forbidden.tab);
		this.setHarvestLevel("pickaxe", 5);
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister ir)
	{
		this.icon = ir.registerIcon("forbidden:taintpickaxe");
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int par1)
	{
		return this.icon;
	}

	public EnumRarity getRarity(ItemStack itemstack)
	{
		return EnumRarity.rare;
	}

	public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack)
	{
		return par2ItemStack.isItemEqual(new ItemStack(Config.thaumcraftResourceID.getItem(), 1, 2)) ? true : super.getIsRepairable(par1ItemStack, par2ItemStack);
	}
}
