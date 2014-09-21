package fox.spiteful.forbidden;

import java.util.HashMap;

import fox.spiteful.forbidden.blocks.ForbiddenBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import thaumcraft.api.ItemApi;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.CrucibleRecipe;
import thaumcraft.api.crafting.IArcaneRecipe;
import thaumcraft.api.crafting.InfusionEnchantmentRecipe;
import thaumcraft.api.crafting.InfusionRecipe;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchPage;

import fox.spiteful.forbidden.items.ForbiddenItems;

public class ForbiddenResearch {
	public static HashMap recipes = new HashMap();

	public static void addResearch() {
		ResearchCategories.registerCategory("FORBIDDEN", new ResourceLocation("forbidden", "textures/misc/forbidden.png"), new ResourceLocation("forbidden", "textures/misc/runecircle.png"));

		(new FauxResearchItem("INFAUXSION", "FORBIDDEN", "INFUSION", "ARTIFICE", -5, 0, ItemApi.getBlock("blockStoneDevice", 2))).registerResearchItem();
		if (ResearchCategories.researchCategories.containsKey("TT_ENCHANTING") && ResearchCategories.researchCategories.get("TT_ENCHANTING").research.containsKey("INFUSIONENCHANTMENT"))
			(new FauxResearchItem("INFAUXSIONENCHANTMENT", "FORBIDDEN", "INFUSIONENCHANTMENT", "TT_ENCHANTING", -3, 3, new ResourceLocation("thaumcraft", "textures/misc/r_enchant.png"))).setParents(new String[] { "INFAUXSION" }).registerResearchItem();
		else
			(new FauxResearchItem("INFAUXSIONENCHANTMENT", "FORBIDDEN", "INFUSIONENCHANTMENT", "ARTIFICE", -3, 3, new ResourceLocation("thaumcraft", "textures/misc/r_enchant.png"))).setParents(new String[] { "INFAUXSION" }).registerResearchItem();

		addInfernalism();
		addTaint();

		if (Config.emeraldTrans)
			(new DarkResearchItem("TRANSEMERALD", "FORBIDDEN", (new AspectList()).add(Aspect.CRYSTAL, 2).add(Aspect.EXCHANGE, 5).add(Aspect.GREED, 4), -6, 4, 3, new ItemStack(ForbiddenItems.resource, 1, 0))).setPages(new ResearchPage[] { new ResearchPage("forbidden.research_page.TRANSEMERALD.1"), new ResearchPage((CrucibleRecipe) recipes.get("TransEmerald")) }).setSecondary().setConcealed().setParents(new String[] { "TRANSGOLD" }).registerResearchItem();
		(new DarkResearchItem("BLACKFLOWER", "FORBIDDEN", (new AspectList()).add(Aspect.PLANT, 3).add(Aspect.SENSES, 2).add(Aspect.DARKNESS, 4), -7, 4, 1, new ItemStack(ForbiddenBlocks.blackFlower, 1, 0))).setPages(new ResearchPage[] { new ResearchPage("forbidden.research_page.BLACKFLOWER.1"), new ResearchPage((CrucibleRecipe) recipes.get("BlackFlower")), new ResearchPage((IRecipe) recipes.get("BlackInk")) }).setAspectTriggers(new Aspect[] { Aspect.SENSES }).registerResearchItem();

		(new DarkResearchItem("CRYSTALWELL", "FORBIDDEN", (new AspectList()).add(Aspect.MIND, 3).add(Aspect.CRYSTAL, 2).add(Aspect.MAGIC, 1), -9, 4, 1, new ItemStack(ForbiddenItems.crystalwell, 1, 0))).setPages(new ResearchPage[] { new ResearchPage("forbidden.research_page.CRYSTALWELL.1"), new ResearchPage((IArcaneRecipe) recipes.get("Crystalwell")) }).setParents(new String[] { "RESEARCH" }).setAspectTriggers(new Aspect[] { Aspect.MIND }).setConcealed().registerResearchItem();
        ThaumcraftApi.addWarpToResearch("CRYSTALWELL", 1);
		(new DarkResearchItem("CONSUMING", "FORBIDDEN", (new AspectList()).add(Aspect.VOID, 4).add(Aspect.ENTROPY, 3).add(Aspect.MAGIC, 2), -1, 2, 2, new ResourceLocation("forbidden", "textures/misc/consuming.png"))).setPages(new ResearchPage[] { new ResearchPage("forbidden.research_page.CONSUMING.1"), new ResearchPage((InfusionEnchantmentRecipe) recipes.get("Consuming")) }).setParents(new String[] { "INFAUXSIONENCHANTMENT" }).setSecondary().setConcealed().registerResearchItem();
		(new DarkResearchItem("PIGBANE", "FORBIDDEN", (new AspectList()).add(Aspect.HUNGER, 3).add(Aspect.WEAPON, 4).add(Aspect.MAGIC, 2), -1, 3, 1, new ResourceLocation("forbidden", "textures/misc/pigbane.png"))).setPages(new ResearchPage[] { new ResearchPage("forbidden.research_page.PIGBANE.1"), new ResearchPage((InfusionEnchantmentRecipe) recipes.get("Pigbane")) }).setParents(new String[] { "INFAUXSIONENCHANTMENT" }).setSecondary().setConcealed().registerResearchItem();
		(new DarkResearchItem("EDUCATIONAL", "FORBIDDEN", (new AspectList()).add(Aspect.MIND, 5).add(Aspect.WEAPON, 1).add(Aspect.MAGIC, 3), -1, 4, 2, new ResourceLocation("forbidden", "textures/misc/educational.png"))).setPages(new ResearchPage[] { new ResearchPage("forbidden.research_page.EDUCATIONAL.1"), new ResearchPage((InfusionEnchantmentRecipe) recipes.get("Educational")) }).setParents(new String[] { "INFAUXSIONENCHANTMENT" }).setSecondary().setConcealed().registerResearchItem();

	}

	public static void addInfernalism() {
		if (!Config.noLust)
			(new DarkResearchItem("NETHERSHARDS", "FORBIDDEN", new AspectList(), -8, -2, 0, new ItemStack(ForbiddenItems.deadlyShards, 1, 0))).setPages(new ResearchPage[] { new ResearchPage("forbidden.research_page.NETHERSHARDS.1"), new ResearchPage("forbidden.research_page.NETHERSHARDS.2"), new ResearchPage("forbidden.research_page.NETHERSHARDS.3") }).setStub().setRound().setAutoUnlock().registerResearchItem();
		else
			(new DarkResearchItem("NETHERSHARDS", "FORBIDDEN", new AspectList(), -8, -2, 0, new ItemStack(ForbiddenItems.deadlyShards, 1, 0))).setPages(new ResearchPage[] { new ResearchPage("forbidden.research_page.NETHERSHARDS.1"), new ResearchPage("forbidden.research_page.NETHERSHARDS.2b"), new ResearchPage("forbidden.research_page.NETHERSHARDS.3") }).setStub().setRound().setAutoUnlock().registerResearchItem();

		(new DarkResearchItem("RIDINGCROP", "FORBIDDEN", new AspectList(), -8, -1, 0, new ItemStack(ForbiddenItems.ridingCrop))).setPages(new ResearchPage[] { new ResearchPage("forbidden.research_page.RIDINGCROP.1"), new ResearchPage((IRecipe) recipes.get("RidingCrop")) }).setStub().setRound().setAutoUnlock().registerResearchItem();
		(new DarkResearchItem("HELLFIRE", "FORBIDDEN", (new AspectList()).add(Aspect.FIRE, 3).add(DarkAspects.NETHER, 4).add(Aspect.TRAVEL, 2), -5, 2, 1, new ItemStack(Blocks.fire))).setPages(new ResearchPage[] { new ResearchPage("forbidden.research_page.HELLFIRE.1") }).setRound().registerResearchItem();

		(new DarkResearchItem("SKULLAXE", "FORBIDDEN", (new AspectList()).add(Aspect.WEAPON, 3).add(DarkAspects.WRATH, 4).add(DarkAspects.NETHER, 1), -8, 1, 2, new ItemStack(ForbiddenItems.skullAxe))).setPages(new ResearchPage[] { new ResearchPage("forbidden.research_page.SKULLAXE.1"), new ResearchPage((InfusionRecipe) recipes.get("SkullAxe")) }).setParents(new String[] { "THAUMIUM", "INFAUXSION" }).setConcealed().registerResearchItem();
        ThaumcraftApi.addWarpToResearch("SKULLAXE", 1);
		(new DarkResearchItem("ARCANECAKE", "FORBIDDEN", (new AspectList()).add(DarkAspects.GLUTTONY, 4).add(Aspect.HUNGER, 3).add(Aspect.CRAFT, 2), -8, 2, 3, new ItemStack(ForbiddenItems.arcaneCakeItem))).setPages(new ResearchPage[] { new ResearchPage("forbidden.research_page.ARCANECAKE.1"), new ResearchPage((InfusionRecipe) recipes.get("ArcaneCake")) }).setParents(new String[] { "INFAUXSION" }).setAspectTriggers(new Aspect[] { DarkAspects.GLUTTONY }).registerResearchItem();
		(new DarkResearchItem("MORPHTOOLS", "FORBIDDEN", (new AspectList()).add(Aspect.TOOL, 2).add(DarkAspects.ENVY, 3).add(Aspect.EXCHANGE, 2), -5, 5, 4, new ItemStack(ForbiddenItems.morphPickaxe))).setPages(new ResearchPage[] { new ResearchPage("forbidden.research_page.MORPHTOOLS.1"), new ResearchPage((InfusionRecipe) recipes.get("MorphPick")), new ResearchPage((InfusionRecipe) recipes.get("MorphSword")), new ResearchPage((InfusionRecipe) recipes.get("MorphShovel")), new ResearchPage((InfusionRecipe) recipes.get("MorphAxe")) }).setParentsHidden(new String[] { "THAUMIUM", "INFUSIONENCHANTMENT" }).setParents(new String[] { "INFAUXSIONENCHANTMENT" }).setConcealed().registerResearchItem();
        ThaumcraftApi.addWarpToResearch("MORPHTOOLS", 4);
		(new DarkResearchItem("ROD_infernal", "FORBIDDEN", (new AspectList()).add(DarkAspects.NETHER, 4).add(Aspect.FIRE, 3).add(Aspect.TOOL, 1), -6, -3, 4, new ItemStack(ForbiddenItems.wandCore, 1, 1))).setPages(new ResearchPage[] { new ResearchPage("forbidden.research_page.ROD_infernal.1"), new ResearchPage((InfusionRecipe) recipes.get("WandRodInfernal")), new ResearchPage("forbidden.research_page.ROD_infernal.2") }).setParents(new String[] { "ROD_silverwood", "INFAUXSION" }).setConcealed().registerResearchItem();
        ThaumcraftApi.addWarpToResearch("ROD_infernal", 2);
		(new DarkResearchItem("CLUSTER", "FORBIDDEN", (new AspectList()).add(Aspect.METAL, 1).add(Aspect.FIRE, 4).add(DarkAspects.ENVY, 3), -7, 7, 3, new ResourceLocation("forbidden", "textures/misc/lucrative.png"))).setPages(new ResearchPage[] { new ResearchPage("forbidden.research_page.CLUSTER.1"), new ResearchPage((InfusionEnchantmentRecipe) recipes.get("Cluster")) }).setParents(new String[] { "MORPHTOOLS", "ELEMENTALPICK" }).setSecondary().setConcealed().registerResearchItem();
		if (Config.greedyEnch)
			(new DarkResearchItem("GREEDY", "FORBIDDEN", (new AspectList()).add(Aspect.MAGIC, 2).add(Aspect.WEAPON, 1).add(Aspect.GREED, 3), -1, 5, 4, new ResourceLocation("forbidden", "textures/misc/greedy.png"))).setPages(new ResearchPage[] { new ResearchPage("forbidden.research_page.GREEDY.1"), new ResearchPage((InfusionEnchantmentRecipe) recipes.get("Greedy")) }).setParents(new String[] { "INFAUXSIONENCHANTMENT" }).setConcealed().registerResearchItem();
		(new DarkResearchItem("CORRUPTING", "FORBIDDEN", (new AspectList()).add(DarkAspects.NETHER, 5).add(Aspect.CRYSTAL, 2).add(Aspect.EXCHANGE, 1), -1, 6, 3, new ResourceLocation("forbidden", "textures/misc/corrupting.png"))).setPages(new ResearchPage[] { new ResearchPage("forbidden.research_page.CORRUPTING.1"), new ResearchPage((InfusionEnchantmentRecipe) recipes.get("Corrupting")) }).setParents(new String[] { "INFAUXSIONENCHANTMENT" }).setConcealed().registerResearchItem();

        (new DarkResearchItem("ETERNAL", "FORBIDDEN", (new AspectList()).add(Aspect.MAGIC, 16).add(Aspect.TOOL, 10).add(Aspect.CRAFT, 8).add(DarkAspects.ENVY, 32), -5, 7, 6, new ResourceLocation("forbidden", "textures/misc/eternal.png"))).setPages(new ResearchPage[] { new ResearchPage("forbidden.research_page.ETERNAL.1"), new ResearchPage((InfusionEnchantmentRecipe) recipes.get("Eternal")) }).setParents(new String[] { "MORPHTOOLS", "ICHOR_TOOLS" }).setConcealed().registerResearchItem();
        ThaumcraftApi.addWarpToResearch("ETERNAL", 5);

		if (Config.wrathCage) {
			if (Config.wrathCost > 0)
				(new DarkResearchItem("WRATHCAGE", "FORBIDDEN", (new AspectList()).add(DarkAspects.WRATH, 5).add(Aspect.MECHANISM, 3).add(Aspect.BEAST, 2), -2, 0, 6, new ItemStack(ForbiddenBlocks.wrathCage))).setPages(new ResearchPage[] { new ResearchPage("forbidden.research_page.WRATHCAGE.1"), new ResearchPage((InfusionRecipe) recipes.get("WrathCage")), new ResearchPage("forbidden.research_page.WRATHCAGE.2"), new ResearchPage("forbidden.research_page.WRATHCAGE.3"), new ResearchPage((CrucibleRecipe) recipes.get("MobCrystal")) }).setParents(new String[] { "INFAUXSION" }).setParentsHidden(new String[] { "THAUMIUM", "DISTILESSENTIA" }).setConcealed().setSiblings(new String[] { "FORK" }).registerResearchItem();
			else
				(new DarkResearchItem("WRATHCAGE", "FORBIDDEN", (new AspectList()).add(DarkAspects.WRATH, 5).add(Aspect.MECHANISM, 3).add(Aspect.BEAST, 2), -2, 0, 6, new ItemStack(ForbiddenBlocks.wrathCage))).setPages(new ResearchPage[] { new ResearchPage("forbidden.research_page.WRATHCAGE.1"), new ResearchPage((InfusionRecipe) recipes.get("WrathCage")), new ResearchPage("forbidden.research_page.WRATHCAGE.3"), new ResearchPage((CrucibleRecipe) recipes.get("MobCrystal")) }).setParents(new String[] { "INFAUXSION" }).setParentsHidden(new String[] { "THAUMIUM", "DISTILESSENTIA" }).setConcealed().setSiblings(new String[] { "FORK" }).registerResearchItem();
            ThaumcraftApi.addWarpToResearch("WRATHCAGE", 5);
		}

		(new DarkResearchItem("FORK", "FORBIDDEN", (new AspectList()), 0, 0, 0, new ItemStack(ForbiddenItems.fork))).setPages(new ResearchPage[] { new ResearchPage("forbidden.research_page.FORK.1"), new ResearchPage((InfusionRecipe) recipes.get("Fork")), new ResearchPage("WRATHCAGE", "forbidden.research_page.FORK.wc") }).setStub().setHidden().registerResearchItem();

	}

	public static void addTaint() {
		(new DarkResearchItem("TAINTSHOVEL", "FORBIDDEN", (new AspectList()).add(Aspect.CRYSTAL, 3).add(Aspect.TAINT, 2).add(Aspect.TOOL, 1), -7, 0, 3, new ItemStack(ForbiddenItems.taintShovel))).setPages(new ResearchPage[] { new ResearchPage("forbidden.research_page.TAINTSHOVEL.1"), new ResearchPage((InfusionRecipe) recipes.get("TaintShovel")), new ResearchPage("forbidden.research_page.TAINTSHOVEL.2") }).setParents(new String[] { "THAUMIUM", "INFAUXSION", "ETHEREALBLOOM" }).setConcealed().registerResearchItem();
		(new DarkResearchItem("TAINTPICK", "FORBIDDEN", (new AspectList()).add(Aspect.TOOL, 2).add(Aspect.TAINT, 4).add(Aspect.ENTROPY, 3), -8, 0, 2, new ItemStack(ForbiddenItems.taintPickaxe))).setPages(new ResearchPage[] { new ResearchPage("forbidden.research_page.TAINTPICK.1"), new ResearchPage((InfusionRecipe) recipes.get("TaintPick")) }).setParents(new String[] { "THAUMIUM", "INFAUXSION" }).setConcealed().registerResearchItem();
		(new DarkResearchItem("ROD_tainted", "FORBIDDEN", (new AspectList()).add(Aspect.MAGIC, 4).add(Aspect.TAINT, 5).add(Aspect.TOOL, 2), -7, -3, 4, new ItemStack(ForbiddenItems.wandCore, 1, 0))).setPages(new ResearchPage[] { new ResearchPage("forbidden.research_page.ROD_tainted.1"), new ResearchPage((InfusionRecipe) recipes.get("WandRodTainted")) }).setParents(new String[] { "ROD_silverwood", "TAINTSHOVEL", "INFAUXSION" }).setConcealed().registerResearchItem();
        ThaumcraftApi.addWarpToResearch("ROD_tainted", 2);
	}
}