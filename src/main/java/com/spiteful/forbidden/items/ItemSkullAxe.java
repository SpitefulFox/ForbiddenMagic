package com.spiteful.forbidden.items;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.IIcon;
import thaumcraft.api.IRepairable;

import com.spiteful.forbidden.Config;
import com.spiteful.forbidden.Forbidden;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemSkullAxe extends ItemSword implements IRepairable
{
	public IIcon icon;

	public ItemSkullAxe(ToolMaterial enumtoolmaterial)
	{
		super(enumtoolmaterial);
		this.setCreativeTab(Forbidden.tab);
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister ir)
	{
		this.icon = ir.registerIcon("forbidden:skullaxe");
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int par1)
	{
		return this.icon;
	}

	public EnumRarity getRarity(ItemStack itemstack)
	{
		return EnumRarity.uncommon;
	}

	@Override
	/**
	 * Return whether this item is repairable in an anvil.
	 */
	public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack)
	{
		return par2ItemStack.isItemEqual(new ItemStack(Config.thaumcraftResourceID.getItem(), 1, 2)) ? true : super.getIsRepairable(par1ItemStack, par2ItemStack);
	}

	@Override
	/**
	 * Returns the strength of the stack against a given block. 1.0F base, (Quality+1)*2 if correct blocktype, 1.5F if
	 * sword
	 */
	public float getDigSpeed(ItemStack stack, Block block, int meta)
	{
		return 1.0F;
	}
}
