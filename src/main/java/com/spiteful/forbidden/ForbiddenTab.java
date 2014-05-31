package com.spiteful.forbidden;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import com.spiteful.forbidden.items.ForbiddenItems;

public final class ForbiddenTab extends CreativeTabs {
	private boolean crystal = false;

	public ForbiddenTab(String tabName) {
		super(tabName);
	}

	public ForbiddenTab(String tabName, boolean mobCrystal) {
		super(tabName);
		crystal = mobCrystal;
	}

	@Override
	public Item getTabIconItem() {
		if (!crystal)
			return ForbiddenItems.fork;
		else
			return ForbiddenItems.mobCrystal;
	}
}
