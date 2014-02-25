package com.spiteful.forbidden;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import thaumcraft.api.research.ResearchItem;

public class DarkResearchItem extends ResearchItem
{
	String inter = null;
	
	public DarkResearchItem(String par1, String par2)
	{
		super(par1, par2);
	}

	public DarkResearchItem(String par1, String par2, AspectList tags, int par3, int par4, int par5, ResourceLocation icon)
	{
		super(par1, par2, tags, par3, par4, par5, icon);
	}

	public DarkResearchItem(String par1, String par2, AspectList tags, int par3, int par4, int par5, ItemStack icon)
	{
		super(par1, par2, tags, par3, par4, par5, icon);
	}
	
	public DarkResearchItem(String par1, String par2, String mod, AspectList tags, int par3, int par4, int par5, ResourceLocation icon)
	{
		super(par1, par2, tags, par3, par4, par5, icon);
		inter = mod;
	}
	
	public DarkResearchItem(String par1, String par2, String mod, AspectList tags, int par3, int par4, int par5, ItemStack icon)
	{
		super(par1, par2, tags, par3, par4, par5, icon);
		inter = mod;
	}

	@SideOnly(Side.CLIENT)
	public String getName()
	{
		return StatCollector.translateToLocal("forbidden.research_name." + key);
	}

	@SideOnly(Side.CLIENT)
	public String getText()
	{
		if(Config.tagResearch)
		{
			if(inter == null)
				return "[FM] " + StatCollector.translateToLocal("forbidden.research_text." + key);
			else
				return "[FM] " + inter + " " + StatCollector.translateToLocal("forbidden.research_text." + key);
		}
		else
			return StatCollector.translateToLocal("forbidden.research_text." + key);
	}
}