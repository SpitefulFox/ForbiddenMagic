package com.spiteful.forbidden.items;

import com.spiteful.forbidden.Forbidden;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;

public class ItemResource extends Item
{
	@SideOnly(Side.CLIENT)
	public Icon[] icons;

	public ItemResource(int par1)
	{
		super(par1);
		this.setMaxStackSize(64);
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
		this.setCreativeTab(Forbidden.tab);
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister ir)
	{
		icons = new Icon[2];

		icons[0] = ir.registerIcon("forbidden:emerald_nugget");
		icons[1] = ir.registerIcon("forbidden:dye_powder_black");
	}

	@SideOnly(Side.CLIENT)
	public Icon getIconFromDamage(int dam)
	{
		return this.icons[dam];
	}

	/**
	 * Returns the unlocalized name of this item. This version accepts an ItemStack so different stacks can have
	 * different names based on their damage or NBT.
	 */
	public String getUnlocalizedName(ItemStack par1ItemStack)
	{
		int i = MathHelper.clamp_int(par1ItemStack.getItemDamage(), 0, 6);
		return super.getUnlocalizedName() + "." + i;
	}

	@SideOnly(Side.CLIENT)
	/**
	 * returns a list of items with the same ID, but different meta (eg: dye returns 16 items)
	 */
	public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List)
	{
		for (int j = 0; j < 2; ++j)
		{
			par3List.add(new ItemStack(par1, 1, j));
		}
	}
	
	@Override
	/**
	 * Returns true if the item can be used on the given entity, e.g. shears on sheep.
	 */
	public boolean itemInteractionForEntity(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, EntityLivingBase par3EntityLivingBase)
	{
		if (par3EntityLivingBase instanceof EntitySheep && par1ItemStack.getItemDamage() == 1)
		{
			EntitySheep entitysheep = (EntitySheep)par3EntityLivingBase;

			if (!entitysheep.getSheared() && entitysheep.getFleeceColor() != 15)
			{
				entitysheep.setFleeceColor(15);
				--par1ItemStack.stackSize;
			}

			return true;
		}
		else
		{
			return false;
		}
	}
}
