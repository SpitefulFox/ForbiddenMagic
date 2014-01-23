package com.spiteful.forbidden.enchantments;

import com.spiteful.forbidden.*;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.item.ItemStack;

public class EnchantmentPigBane extends Enchantment
{
	public EnchantmentPigBane(int id)
	{
		super(id, 0, EnumEnchantmentType.weapon);
		setName("pigbane");
	}
	
	@Override
	/**
	 * Returns the maximum level that the enchantment can have.
	 */
	public int getMaxLevel()
	{
		return 5;
	}
	
	/**
	 * Returns the minimal value of enchantability needed on the enchantment level passed.
	 */
	public int getMinEnchantability(int par1)
	{
		return 1 + (par1 - 1) * 8;
	}

	/**
	 * Returns the maximum value of enchantability nedded on the enchantment level passed.
	 */
	public int getMaxEnchantability(int par1)
	{
		return this.getMinEnchantability(par1) + 20;
	}
	
	@Override
	/**
	 * Determines if the enchantment passed can be applyied together with this enchantment.
	 */
	public boolean canApplyTogether(Enchantment ench)
	{
		return super.canApplyTogether(ench) && ench.effectId != Enchantment.sharpness.effectId && ench.effectId != Enchantment.smite.effectId && ench.effectId != Enchantment.baneOfArthropods.effectId;
	}
	
	/**
	 * Calculates de (magic) damage done by the enchantment on a living entity based on level and entity passed.
	 */
	public float calcModifierLiving(int level, EntityLivingBase victim)
	{
		if(victim instanceof EntityPig || victim instanceof EntityPigZombie)
			return (float)level * 4.0F;
		else
			return 0.0F;
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