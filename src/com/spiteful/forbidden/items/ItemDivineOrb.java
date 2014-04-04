package com.spiteful.forbidden.items;

import com.spiteful.forbidden.*;

import WayofTime.alchemicalWizardry.common.items.EnergyBattery;
import WayofTime.alchemicalWizardry.common.altarRecipeRegistry.AltarRecipeRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.ItemStack;

public class ItemDivineOrb extends EnergyBattery
{
	public ItemDivineOrb(int id)
	{
		super(id, 70000000);
		orbLevel = 5;
		setCreativeTab(Forbidden.tab);
		AltarRecipeRegistry.registerAltarOrbRecipe(new ItemStack(this),5,140);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister)
	{
		this.itemIcon = iconRegister.registerIcon("forbidden:divineorb");
	}
}