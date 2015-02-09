package fox.spiteful.forbidden.enchantments;

import fox.spiteful.forbidden.items.tools.ItemMorphPickaxe;
import fox.spiteful.forbidden.items.tools.ItemMorphShovel;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.item.ItemStack;

public class EnchantmentImpact extends Enchantment {
    public EnchantmentImpact(int id) {
        super(id, 0, EnumEnchantmentType.digger);
        setName("impact");
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public int getMinEnchantability(int par1) {
        return 30;
    }

    @Override
    public int getMaxEnchantability(int par1) {
        return super.getMinEnchantability(par1) + 10;
    }

    @Override
    public boolean canApply(ItemStack item) {
        return (item.getItem() instanceof ItemMorphPickaxe || item.getItem() instanceof ItemMorphShovel);
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        return false;
    }
}