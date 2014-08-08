package com.spiteful.forbidden.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.item.ItemStack;

public class EnchantmentPigBane extends Enchantment {
	public EnchantmentPigBane(int id) {
		super(id, 0, EnumEnchantmentType.weapon);
		setName("pigbane");
	}

	@Override
	public int getMaxLevel() {
		return 5;
	}

	@Override
	public int getMinEnchantability(int par1) {
		return 1 + (par1 - 1) * 8;
	}

	@Override
	public int getMaxEnchantability(int par1) {
		return this.getMinEnchantability(par1) + 20;
	}

	@Override
	public boolean canApplyTogether(Enchantment ench) {
		return super.canApplyTogether(ench) && ench.effectId != Enchantment.sharpness.effectId && ench.effectId != Enchantment.smite.effectId && ench.effectId != Enchantment.baneOfArthropods.effectId;
	}

	public float calcModifierLiving(int level, EntityLivingBase victim) {
		if (victim instanceof EntityPig || victim instanceof EntityPigZombie)
			return (float) level * 4.0F;
		else
			return 0.0F;
	}

	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack) {
		return false;
	}
}