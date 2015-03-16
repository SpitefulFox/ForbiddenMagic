package fox.spiteful.forbidden.enchantments;

import fox.spiteful.forbidden.items.tools.ItemMorphAxe;
import fox.spiteful.forbidden.items.tools.ItemMorphPickaxe;
import fox.spiteful.forbidden.items.tools.ItemMorphSword;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.item.ItemStack;

import fox.spiteful.forbidden.items.tools.ItemMorphShovel;

public class EnchantmentVoid extends Enchantment {
    public EnchantmentVoid(int id) {
        super(id, 1, EnumEnchantmentType.digger);
        setName("voidtouched");
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
        return super.getMinEnchantability(par1) + 30;
    }

    @Override
    public boolean canApply(ItemStack item) {
        return (item.getItem() instanceof ItemMorphPickaxe || item.getItem() instanceof ItemMorphAxe || item.getItem() instanceof ItemMorphSword || item.getItem() instanceof ItemMorphShovel);
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        return false;
    }
}