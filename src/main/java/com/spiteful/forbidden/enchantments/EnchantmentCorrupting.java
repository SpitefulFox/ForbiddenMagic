package com.spiteful.forbidden.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;

public class EnchantmentCorrupting extends Enchantment
{
	public EnchantmentCorrupting(int id)
	{
		super(id, 0, EnumEnchantmentType.digger);
		setName("corrupting");
	}
	
	@Override
	/**
	 * Returns the maximum level that the enchantment can have.
	 */
	public int getMaxLevel()
	{
		return 1;
	}
	
	/**
	 * Returns the minimal value of enchantability needed on the enchantment level passed.
	 */
	public int getMinEnchantability(int par1)
	{
		return 20;
	}

	/**
	 * Returns the maximum value of enchantability nedded on the enchantment level passed.
	 */
	public int getMaxEnchantability(int par1)
	{
		return super.getMinEnchantability(par1) + 50;
	}
	
	@Override
	public boolean canApply(ItemStack item)
	{
		if(item.getItem() instanceof ItemPickaxe)
			return true;
		else
			return false;
	}
	
	@Override
	/**
	 * This applies specifically to applying at the enchanting table. The other method {@link #canApply(ItemStack)}
	 * applies for <i>all possible</i> enchantments.
	 * @param stack
	 * @return
	 */
	public boolean canApplyAtEnchantingTable(ItemStack stack)
	{
		return false;
	}
	
	
}