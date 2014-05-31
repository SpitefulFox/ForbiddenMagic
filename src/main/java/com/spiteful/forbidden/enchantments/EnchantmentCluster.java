package com.spiteful.forbidden.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.item.ItemStack;

import com.spiteful.forbidden.items.ItemMorphPickaxe;

public class EnchantmentCluster extends Enchantment {
	public EnchantmentCluster(int id) {
		super(id, 0, EnumEnchantmentType.digger);
		setName("cluster");
	}

	@Override
	public int getMaxLevel() {
		return 4;
	}

	@Override
	public boolean canApplyTogether(Enchantment ench) {
		return super.canApplyTogether(ench) && ench.effectId != Enchantment.fortune.effectId;
	}

	@Override
	public boolean canApply(ItemStack item) {
		if (item.getItem() instanceof ItemMorphPickaxe)
			return true;
		else
			return false;
	}

	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack) {
		return false;
	}
}