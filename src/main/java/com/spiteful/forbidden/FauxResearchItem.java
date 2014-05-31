package com.spiteful.forbidden;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchCategoryList;
import thaumcraft.api.research.ResearchItem;
import thaumcraft.api.research.ResearchPage;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class FauxResearchItem extends ResearchItem {
	public ResearchItem original;

	public FauxResearchItem(String name, String cat, String origin, String originCategory, int x, int y, ResourceLocation icon) {
		super(name, cat, new AspectList(), x, y, 1, icon);
		original = ((ResearchCategoryList) ResearchCategories.researchCategories.get(originCategory)).research.get(origin);
		bindToOriginal();
		setStub();
		setHidden();
	}

	public FauxResearchItem(String name, String cat, String origin, String originCategory, int x, int y, ItemStack icon) {
		super(name, cat, new AspectList(), x, y, 1, icon);
		original = ((ResearchCategoryList) ResearchCategories.researchCategories.get(originCategory)).research.get(origin);
		bindToOriginal();
		setStub();
		setHidden();
	}

	private void bindToOriginal() {
		if (original.siblings == null)
			original.setSiblings(new String[] { key });
		else {
			String[] family = original.siblings;
			String[] newFamily = new String[family.length + 1];
			for (int x = 0; x < family.length; x++) {
				newFamily[x] = family[x];
			}
			newFamily[family.length] = key;
			original.setSiblings(newFamily);
		}
	}

	public ResearchPage[] getPages() {
		return original.getPages();
	}

	@SideOnly(Side.CLIENT)
	public String getName() {
		return original.getName();
	}

	@SideOnly(Side.CLIENT)
	public String getText() {
		return original.getText();
	}

	public boolean isStub() {
		return true;
	}

	public boolean isHidden() {
		return true;
	}

	public int getComplexity() {
		return 1;
	}

}
