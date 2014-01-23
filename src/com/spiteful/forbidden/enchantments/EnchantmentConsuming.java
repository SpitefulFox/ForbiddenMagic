package com.spiteful.forbidden.enchantments;

import com.spiteful.forbidden.*;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;

public class EnchantmentConsuming extends Enchantment
{
	public EnchantmentConsuming(int id)
	{
		super(id, 0, EnumEnchantmentType.digger);
		setName("consuming");
	}
	
	@Override
	/**
	 * Returns the maximum level that the enchantment can have.
	 */
	public int getMaxLevel()
	{
		return 1;
	}
	
	@Override
	/**
	 * Determines if the enchantment passed can be applyied together with this enchantment.
	 */
	public boolean canApplyTogether(Enchantment ench)
	{
		return super.canApplyTogether(ench) && ench.effectId != Enchantment.silkTouch.effectId;
	}
	
	@Override
	public boolean canApply(ItemStack item)
	{
		if(item.getItem() instanceof ItemPickaxe || item.getItem() instanceof ItemSpade)
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