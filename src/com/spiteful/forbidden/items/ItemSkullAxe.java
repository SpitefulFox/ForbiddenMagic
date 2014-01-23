package com.spiteful.forbidden.items;

import com.spiteful.forbidden.Forbidden;
import com.spiteful.forbidden.Config;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.block.Block;
import net.minecraft.util.Icon;
import thaumcraft.api.IRepairable;
import thaumcraft.api.ItemApi;

public class ItemSkullAxe extends ItemSword implements IRepairable
{
    public Icon icon;

    public ItemSkullAxe(int i, EnumToolMaterial enumtoolmaterial)
    {
        super(i, enumtoolmaterial);
        this.setCreativeTab(Forbidden.tab);
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister ir)
    {
        this.icon = ir.registerIcon("forbidden:skullaxe");
    }

    @SideOnly(Side.CLIENT)
    public Icon getIconFromDamage(int par1)
    {
        return this.icon;
    }

    public EnumRarity getRarity(ItemStack itemstack)
    {
        return EnumRarity.uncommon;
    }

    @Override
	/**
     * Return whether this item is repairable in an anvil.
     */
	public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack)
    {
        return par2ItemStack.isItemEqual(new ItemStack(Config.thaumcraftResourceID, 1, 2)) ? true : super.getIsRepairable(par1ItemStack, par2ItemStack);
    }

    @Override
    /**
     * Returns the strength of the stack against a given block. 1.0F base, (Quality+1)*2 if correct blocktype, 1.5F if
     * sword
     */
    public float getStrVsBlock(ItemStack par1ItemStack, Block par2Block)
    {
        return 1.0F;
    }
}
