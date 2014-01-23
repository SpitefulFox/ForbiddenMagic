package com.spiteful.forbidden;

import com.spiteful.forbidden.items.ForbiddenItems;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;

public final class ForbiddenTab extends CreativeTabs
{
	private boolean crystal = false;
	
	public ForbiddenTab(String tabName)
	{
		super(tabName);
	}
	
	public ForbiddenTab(String tabName, boolean mobCrystal)
	{
		super(tabName);
		crystal = mobCrystal;
	}

	@SideOnly(Side.CLIENT)
	public int getTabIconItemIndex()
	{
		if(!crystal)
			return ForbiddenItems.fork.itemID;
		else
			return ForbiddenItems.mobCrystal.itemID;
	}
}
