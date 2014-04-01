package com.spiteful.forbidden.enchantments;

import com.spiteful.forbidden.*;
import com.spiteful.forbidden.items.*;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.item.ItemStack;

public class EnchantmentEternal extends Enchantment
{
	public EnchantmentEternal(int id)
	{
		super(id, 0, EnumEnchantmentType.digger);
		setName("eternal");
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
		return 70;
	}

	/**
	 * Returns the maximum value of enchantability nedded on the enchantment level passed.
	 */
	public int getMaxEnchantability(int par1)
	{
		return super.getMinEnchantability(par1) + 50;
	}
	
	@Override
	/**
	 * Determines if the enchantment passed can be applyied together with this enchantment.
	 */
	public boolean canApplyTogether(Enchantment ench)
	{
		return super.canApplyTogether(ench) && ench.effectId != Enchantment.unbreaking.effectId && ench.effectId != DarkEnchantments.educational.effectId;
	}
	
	@Override
	public boolean canApply(ItemStack item)
	{
		return (item.getItem() instanceof ItemMorphPickaxe || item.getItem() instanceof ItemMorphAxe || item.getItem() instanceof ItemMorphSword || item.getItem() instanceof ItemMorphShovel);
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