package fox.spiteful.forbidden.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class EnchantmentWrath extends Enchantment
{
    public EnchantmentWrath(int id)
    {
        super(id, 1, EnumEnchantmentType.weapon);
        setName("wrath");
    }

    /**
     * Returns the minimal value of enchantability needed on the enchantment level passed.
     */
    public int getMinEnchantability(int level)
    {
        return 5 + (level - 1) * 11;
    }

    /**
     * Returns the maximum value of enchantability nedded on the enchantment level passed.
     */
    public int getMaxEnchantability(int level)
    {
        return this.getMinEnchantability(level) + 20;
    }

    /**
     * Returns the maximum level that the enchantment can have.
     */
    public int getMaxLevel()
    {
        return 5;
    }

    //Amount of bonus damage added by the enchant on hit
    public float func_152376_a(int level, EnumCreatureAttribute target)
    {
        return (float)level * 1.25F;
    }

    public boolean canApply(ItemStack stack)
    {
        return stack.getItem() instanceof ItemAxe ? true : super.canApply(stack);
    }

    //Effect that enchant does on hit
    public void func_151368_a(EntityLivingBase wut, Entity who, int level)
    {

    }
}