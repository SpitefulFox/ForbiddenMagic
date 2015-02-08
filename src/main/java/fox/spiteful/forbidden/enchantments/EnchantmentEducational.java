package fox.spiteful.forbidden.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.item.ItemStack;

public class EnchantmentEducational extends Enchantment {
    public EnchantmentEducational(int id) {
        super(id, 0, EnumEnchantmentType.weapon);
        setName("educational");
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    @Override
    public boolean canApplyTogether(Enchantment ench) {
        return super.canApplyTogether(ench) && ench.effectId != Enchantment.looting.effectId && ench.effectId != DarkEnchantments.greedy.effectId;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        return false;
    }
}