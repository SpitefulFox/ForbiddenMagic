package com.spiteful.forbidden;

import com.spiteful.forbidden.items.ForbiddenItems;
import com.spiteful.forbidden.blocks.ForbiddenBlocks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagByte;
import net.minecraft.util.ResourceLocation;
import thaumcraft.api.ItemApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.CrucibleRecipe;
import thaumcraft.api.crafting.IArcaneRecipe;
import thaumcraft.api.crafting.InfusionEnchantmentRecipe;
import thaumcraft.api.crafting.InfusionRecipe;
import thaumcraft.api.nodes.NodeModifier;
import thaumcraft.api.nodes.NodeType;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchCategoryList;
import thaumcraft.api.research.ResearchItem;
import thaumcraft.api.research.ResearchPage;

public class ForbiddenResearch
{
	public static HashMap recipes = new HashMap();

	public static void addResearch()
	{
		addInfernalism();
		
		(new DarkResearchItem("TAINTSHOVEL", "ARTIFICE", (new AspectList()).add(Aspect.CRYSTAL, 1).add(Aspect.TAINT, 2).add(Aspect.TOOL, 1), -9, 6, 4, new ItemStack(ForbiddenItems.taintShovel))).setPages(new ResearchPage[]{new ResearchPage("forbidden.research_page.TAINTSHOVEL.1"), new ResearchPage((InfusionRecipe)recipes.get("TaintShovel")), new ResearchPage("forbidden.research_page.TAINTSHOVEL.2")}).setParentsHidden(new String[]{"THAUMIUM", "INFUSION", "ETHEREALBLOOM"}).setConcealed().registerResearchItem();
		(new DarkResearchItem("TAINTPICK", "ARTIFICE", (new AspectList()).add(Aspect.TOOL, 1).add(Aspect.TAINT, 2).add(Aspect.ENTROPY, 1), -9, 3, 3, new ItemStack(ForbiddenItems.taintPickaxe))).setPages(new ResearchPage[]{new ResearchPage("forbidden.research_page.TAINTPICK.1"), new ResearchPage((InfusionRecipe)recipes.get("TaintPick"))}).setParentsHidden(new String[]{"THAUMIUM", "INFUSION"}).setConcealed().registerResearchItem();
		(new DarkResearchItem("ROD_tainted", "THAUMATURGY", (new AspectList()).add(Aspect.MAGIC, 1).add(Aspect.TAINT, 2).add(Aspect.TOOL, 1), -3, 7, 4, new ItemStack(ForbiddenItems.wandCore, 1, 0))).setPages(new ResearchPage[]{new ResearchPage("forbidden.research_page.ROD_tainted.1"), new ResearchPage((InfusionRecipe)recipes.get("WandRodTainted"))}).setParents(new String[]{"ROD_silverwood", "TAINTSHOVEL"}).setConcealed().registerResearchItem();
		(new DarkResearchItem("TRANSEMERALD", "ALCHEMY", (new AspectList()).add(Aspect.CRYSTAL, 1).add(Aspect.EXCHANGE, 1).add(Aspect.GREED, 2), -6, 3, 3, new ItemStack(ForbiddenItems.resource, 1, 0))).setPages(new ResearchPage[]{new ResearchPage("forbidden.research_page.TRANSEMERALD.1"), new ResearchPage((CrucibleRecipe)recipes.get("TransEmerald"))}).setConcealed().setParents(new String[]{"TRANSGOLD"}).registerResearchItem();
		(new DarkResearchItem("BLACKFLOWER", "ALCHEMY", (new AspectList()).add(Aspect.PLANT, 1).add(Aspect.SENSES, 1).add(Aspect.DARKNESS, 2), -4, -1, 3, new ItemStack(ForbiddenBlocks.blackFlower, 1, 0))).setPages(new ResearchPage[]{new ResearchPage("forbidden.research_page.BLACKFLOWER.1"), new ResearchPage((CrucibleRecipe)recipes.get("BlackFlower")), new ResearchPage((IRecipe)recipes.get("BlackInk"))}).setParents(new String[]{"CRUCIBLE"}).registerResearchItem();
		
		if(Compat.tt)
		{
			(new DarkResearchItem("CONSUMING", "TT_ENCHANTING", (new AspectList()).add(Aspect.VOID, 2).add(Aspect.ENTROPY, 1).add(Aspect.MAGIC, 1), -6, -4, 3, new ResourceLocation("forbidden", "textures/misc/consuming.png"))).setPages(new ResearchPage[]{new ResearchPage("forbidden.research_page.CONSUMING.1"), new ResearchPage((InfusionEnchantmentRecipe)recipes.get("Consuming"))}).setParents(new String[]{"INFUSIONENCHANTMENT"}).setConcealed().registerResearchItem();
			(new DarkResearchItem("PIGBANE", "TT_ENCHANTING", (new AspectList()).add(Aspect.HUNGER, 2).add(Aspect.WEAPON, 1).add(Aspect.MAGIC, 1), -6, -3, 2, new ResourceLocation("forbidden", "textures/misc/pigbane.png"))).setPages(new ResearchPage[]{new ResearchPage("forbidden.research_page.PIGBANE.1"), new ResearchPage((InfusionEnchantmentRecipe)recipes.get("Pigbane"))}).setParents(new String[]{"INFUSIONENCHANTMENT"}).setConcealed().registerResearchItem();
			(new DarkResearchItem("EDUCATIONAL", "TT_ENCHANTING", (new AspectList()).add(Aspect.MIND, 2).add(Aspect.WEAPON, 1).add(Aspect.MAGIC, 1), -6, -2, 4, new ResourceLocation("forbidden", "textures/misc/educational.png"))).setPages(new ResearchPage[]{new ResearchPage("forbidden.research_page.EDUCATIONAL.1"), new ResearchPage((InfusionEnchantmentRecipe)recipes.get("Educational"))}).setParents(new String[]{"INFUSIONENCHANTMENT"}).setConcealed().registerResearchItem();
		}
		else
		{
			(new DarkResearchItem("CONSUMING", "ARTIFICE", (new AspectList()).add(Aspect.VOID, 2).add(Aspect.ENTROPY, 1).add(Aspect.MAGIC, 1), -8, 11, 3, new ResourceLocation("forbidden", "textures/misc/consuming.png"))).setPages(new ResearchPage[]{new ResearchPage("forbidden.research_page.CONSUMING.1"), new ResearchPage((InfusionEnchantmentRecipe)recipes.get("Consuming"))}).setParents(new String[]{"INFUSIONENCHANTMENT"}).setConcealed().registerResearchItem();
			(new DarkResearchItem("PIGBANE", "ARTIFICE", (new AspectList()).add(Aspect.HUNGER, 2).add(Aspect.WEAPON, 1).add(Aspect.MAGIC, 1), -8, 12, 2, new ResourceLocation("forbidden", "textures/misc/pigbane.png"))).setPages(new ResearchPage[]{new ResearchPage("forbidden.research_page.PIGBANE.1"), new ResearchPage((InfusionEnchantmentRecipe)recipes.get("Pigbane"))}).setParents(new String[]{"INFUSIONENCHANTMENT"}).setConcealed().registerResearchItem();
			(new DarkResearchItem("EDUCATIONAL", "ARTIFICE", (new AspectList()).add(Aspect.MIND, 2).add(Aspect.WEAPON, 1).add(Aspect.MAGIC, 1), -8, 13, 4, new ResourceLocation("forbidden", "textures/misc/educational.png"))).setPages(new ResearchPage[]{new ResearchPage("forbidden.research_page.EDUCATIONAL.1"), new ResearchPage((InfusionEnchantmentRecipe)recipes.get("Educational"))}).setParents(new String[]{"INFUSIONENCHANTMENT"}).setConcealed().registerResearchItem();
		}
		
		
	}
	
	public static void addInfernalism()
	{
		ResearchCategories.registerCategory("INFERNALISM", new ResourceLocation("forbidden", "textures/misc/infernalism.png"), new ResourceLocation("thaumcraft", "textures/gui/gui_researchback.png"));
		(new FauxResearchItem("INFAUXSION", "INFERNALISM", "INFUSION", "ARTIFICE", -5, 0, ItemApi.getBlock("blockStoneDevice", 2))).registerResearchItem();
		if(!Compat.tt)
			(new FauxResearchItem("INFAUXSIONENCHANTMENT", "INFERNALISM", "INFUSIONENCHANTMENT", "ARTIFICE", -3, 3, new ResourceLocation("thaumcraft", "textures/misc/r_enchant.png"))).setParents(new String[]{"INFAUXSION"}).registerResearchItem();
		else
			(new FauxResearchItem("INFAUXSIONENCHANTMENT", "INFERNALISM", "INFUSIONENCHANTMENT", "TT_ENCHANTING", -3, 3, new ResourceLocation("thaumcraft", "textures/misc/r_enchant.png"))).setParents(new String[]{"INFAUXSION"}).registerResearchItem();
		
		if(!Config.noLust)
			(new DarkResearchItem("NETHERSHARDS", "INFERNALISM", new AspectList(), 0, 0, 0, new ItemStack(ForbiddenItems.deadlyShards, 1, 0))).setPages(new ResearchPage[] {new ResearchPage("forbidden.research_page.NETHERSHARDS.1"), new ResearchPage("forbidden.research_page.NETHERSHARDS.2"), new ResearchPage("forbidden.research_page.NETHERSHARDS.3")}).setStub().setRound().setAutoUnlock().registerResearchItem();
		else
			(new DarkResearchItem("NETHERSHARDS", "INFERNALISM", new AspectList(), 0, 0, 0, new ItemStack(ForbiddenItems.deadlyShards, 1, 0))).setPages(new ResearchPage[] {new ResearchPage("forbidden.research_page.NETHERSHARDS.1"), new ResearchPage("forbidden.research_page.NETHERSHARDS.2b"), new ResearchPage("forbidden.research_page.NETHERSHARDS.3")}).setStub().setRound().setAutoUnlock().registerResearchItem();
			
		(new DarkResearchItem("SKULLAXE", "INFERNALISM", (new AspectList()).add(Aspect.WEAPON, 1).add(DarkAspects.WRATH, 2).add(DarkAspects.NETHER, 1), -8, -2, 4, new ItemStack(ForbiddenItems.skullAxe))).setPages(new ResearchPage[] {new ResearchPage("forbidden.research_page.SKULLAXE.1"), new ResearchPage((InfusionRecipe)recipes.get("SkullAxe"))}).setParents(new String[] {"THAUMIUM", "INFAUXSION"}).setConcealed().registerResearchItem();
		(new DarkResearchItem("ARCANECAKE", "INFERNALISM", (new AspectList()).add(DarkAspects.GLUTTONY, 2).add(Aspect.HUNGER, 1).add(Aspect.CRAFT, 1), -8, 1, 3, new ItemStack(ForbiddenItems.arcaneCakeItem))).setPages(new ResearchPage[] {new ResearchPage("forbidden.research_page.ARCANECAKE.1"), new ResearchPage((InfusionRecipe)recipes.get("ArcaneCake"))}).setParents(new String[] {"INFAUXSION"}).setHidden().registerResearchItem();
		(new DarkResearchItem("MORPHTOOLS", "INFERNALISM", (new AspectList()).add(Aspect.TOOL, 2).add(DarkAspects.ENVY, 3).add(Aspect.EXCHANGE, 2), -5, 5, 5, new ItemStack(ForbiddenItems.morphPickaxe))).setPages(new ResearchPage[]{new ResearchPage("forbidden.research_page.MORPHTOOLS.1"), new ResearchPage((InfusionRecipe)recipes.get("MorphPick")), new ResearchPage((InfusionRecipe)recipes.get("MorphSword")), new ResearchPage((InfusionRecipe)recipes.get("MorphShovel")), new ResearchPage((InfusionRecipe)recipes.get("MorphAxe"))}).setParentsHidden(new String[]{"THAUMIUM", "INFUSIONENCHANTMENT"}).setParents(new String[]{"INFAUXSIONENCHANTMENT"}).setConcealed().registerResearchItem();
		(new DarkResearchItem("ROD_infernal", "INFERNALISM", (new AspectList()).add(DarkAspects.NETHER, 2).add(Aspect.FIRE, 1).add(Aspect.TOOL, 1), -8, 0, 5, new ItemStack(ForbiddenItems.wandCore, 1, 1))).setPages(new ResearchPage[]{new ResearchPage("forbidden.research_page.ROD_infernal.1"), new ResearchPage((InfusionRecipe)recipes.get("WandRodInfernal")),  new ResearchPage("forbidden.research_page.ROD_infernal.2")}).setParents(new String[]{"ROD_silverwood", "INFAUXSION"}).setConcealed().registerResearchItem();
		(new DarkResearchItem("CLUSTER", "INFERNALISM", (new AspectList()).add(Aspect.METAL, 1).add(Aspect.FIRE, 2).add(DarkAspects.ENVY, 1), -7, 7, 3, new ResourceLocation("forbidden", "textures/misc/lucrative.png"))).setPages(new ResearchPage[]{new ResearchPage("forbidden.research_page.CLUSTER.1"), new ResearchPage((InfusionEnchantmentRecipe)recipes.get("Cluster"))}).setParents(new String[]{"MORPHTOOLS", "ELEMENTALPICK"}).setConcealed().registerResearchItem();
		if(Config.greedyEnch)
			(new DarkResearchItem("GREEDY", "INFERNALISM", (new AspectList()).add(Aspect.MAGIC, 1).add(Aspect.WEAPON, 1).add(Aspect.GREED, 2), -5, 3, 4, new ResourceLocation("forbidden", "textures/misc/greedy.png"))).setPages(new ResearchPage[]{new ResearchPage("forbidden.research_page.GREEDY.1"), new ResearchPage((InfusionEnchantmentRecipe)recipes.get("Greedy"))}).setParents(new String[]{"INFAUXSIONENCHANTMENT"}).setConcealed().registerResearchItem();
		
		if(Config.wrathCage){
			if(Config.wrathCost > 0)
				(new DarkResearchItem("WRATHCAGE", "INFERNALISM", (new AspectList()).add(DarkAspects.WRATH, 2).add(Aspect.MECHANISM, 1).add(Aspect.BEAST, 1), -3, 0, 6, new ItemStack(ForbiddenBlocks.wrathCage))).setPages(new ResearchPage[]{new ResearchPage("forbidden.research_page.WRATHCAGE.1"), new ResearchPage((InfusionRecipe)recipes.get("WrathCage")), new ResearchPage("forbidden.research_page.WRATHCAGE.2"), new ResearchPage("forbidden.research_page.WRATHCAGE.3"), new ResearchPage((CrucibleRecipe)recipes.get("MobCrystal"))}).setParents(new String[]{"INFAUXSION"}).setParentsHidden(new String[]{"THAUMIUM"}).setSiblings(new String[]{"FORK"}).setHidden().registerResearchItem();
			else
				(new DarkResearchItem("WRATHCAGE", "INFERNALISM", (new AspectList()).add(DarkAspects.WRATH, 2).add(Aspect.MECHANISM, 1).add(Aspect.BEAST, 1), -3, 0, 6, new ItemStack(ForbiddenBlocks.wrathCage))).setPages(new ResearchPage[]{new ResearchPage("forbidden.research_page.WRATHCAGE.1"), new ResearchPage((InfusionRecipe)recipes.get("WrathCage")), new ResearchPage("forbidden.research_page.WRATHCAGE.3"), new ResearchPage((CrucibleRecipe)recipes.get("MobCrystal"))}).setParents(new String[]{"INFAUXSION"}).setParentsHidden(new String[]{"THAUMIUM"}).setSiblings(new String[]{"FORK"}).setHidden().registerResearchItem();

		}
		
		(new DarkResearchItem("FORK", "INFERNALISM", (new AspectList()), -1, 2, 0, new ItemStack(ForbiddenItems.fork))).setPages(new ResearchPage[] {new ResearchPage("forbidden.research_page.FORK.1"), new ResearchPage((InfusionRecipe)recipes.get("Fork")), new ResearchPage("WRATHCAGE", "forbidden.research_page.FORK.wc")}).setStub().setHidden().registerResearchItem();
		
		if(!Config.noMeddling){
			((ResearchCategoryList)ResearchCategories.researchCategories.get("THAUMATURGY")).research.remove("FOCUSHELLBAT");
			(new ResearchItem("FOCUSHELLBAT", "INFERNALISM", (new AspectList()).add(Aspect.TRAVEL, 1).add(DarkAspects.NETHER, 2).add(Aspect.FIRE, 1).add(DarkAspects.WRATH, 1), -8, -1, 4, ItemApi.getItem("itemFocusHellbat", 0))).setPages(new ResearchPage[]{new ResearchPage("tc.research_page.FOCUSHELLBAT.1"), new ResearchPage((InfusionRecipe)recipes.get("FocusHellbat"))}).setConcealed().setParents(new String[]{"FOCUSTRADE", "INFAUXSION"}).registerResearchItem();
			((ResearchCategoryList)ResearchCategories.researchCategories.get("ARTIFICE")).research.remove("INFERNALFURNACE");
			(new ResearchItem("INFERNALFURNACE", "INFERNALISM", (new AspectList()).add(Aspect.FIRE, 2).add(DarkAspects.NETHER, 1).add(Aspect.CRAFT, 1).add(Aspect.AURA, 1), -1, -2, 4, new ResourceLocation("thaumcraft", "textures/misc/r_infernalfurnace.png"))).setPages(new ResearchPage[]{new ResearchPage("tc.research_page.INFERNALFURNACE.1"), new ResearchPage((List)recipes.get("InfernalFurnace")), new ResearchPage("tc.research_page.INFERNALFURNACE.2")}).setParents(new String[]{"NITOR", "ALUMENTUM"}).setConcealed().registerResearchItem();
		}
	}
}