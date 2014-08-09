package fox.spiteful.forbidden.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.item.ItemStack;

public class EnchantmentGreedy extends Enchantment {
	public EnchantmentGreedy(int id) {
		super(id, 0, EnumEnchantmentType.weapon);
		setName("greedy");
	}

	@Override
	public int getMaxLevel() {
		return 1;
	}

	@Override
	public int getMinEnchantability(int par1) {
		return 20;
	}

	@Override
	public int getMaxEnchantability(int par1) {
		return super.getMinEnchantability(par1) + 50;
	}

	@Override
	public boolean canApplyTogether(Enchantment ench) {
		return super.canApplyTogether(ench) && ench.effectId != Enchantment.looting.effectId && ench.effectId != DarkEnchantments.educational.effectId;
	}

	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack) {
		return false;
	}
}