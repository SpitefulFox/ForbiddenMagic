package fox.spiteful.forbidden;

import java.util.Iterator;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import thaumcraft.api.ItemApi;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.InfusionEnchantmentRecipe;
import thaumcraft.api.wands.WandCap;

import fox.spiteful.forbidden.blocks.ForbiddenBlocks;
import fox.spiteful.forbidden.enchantments.DarkEnchantments;
import fox.spiteful.forbidden.items.ForbiddenItems;

public class ForbiddenRecipes {
	public static void addRecipes() {
		ForbiddenResearch.recipes.put("TaintShovel", ThaumcraftApi.addInfusionCraftingRecipe("TAINTSHOVEL", new ItemStack(ForbiddenItems.taintShovel), 5, (new AspectList()).add(Aspect.HEAL, 8).add(Aspect.CRYSTAL, 24).add(Aspect.MINE, 12), ItemApi.getItem("itemShovelThaumium", 0), new ItemStack[] { ItemApi.getItem("itemShard", 4), ItemApi.getItem("itemShard", 4), new ItemStack(Items.diamond), ItemApi.getBlock("blockMagicalLog", 1) }));
		ForbiddenResearch.recipes.put("TaintPick", ThaumcraftApi.addInfusionCraftingRecipe("TAINTPICK", new ItemStack(ForbiddenItems.taintPickaxe), 1, (new AspectList()).add(Aspect.ENTROPY, 8).add(Aspect.MINE, 8).add(Aspect.TAINT, 12), ItemApi.getItem("itemPickThaumium", 0), new ItemStack[] { ItemApi.getItem("itemShard", 5), ItemApi.getItem("itemShard", 5), new ItemStack(Items.diamond), ItemApi.getBlock("blockMagicalLog", 0) }));
		ForbiddenResearch.recipes.put("WandRodTainted", ThaumcraftApi.addInfusionCraftingRecipe("ROD_tainted", new ItemStack(ForbiddenItems.wandCore, 1, 0), 5, (new AspectList()).add(Aspect.TAINT, 24).add(Aspect.MAGIC, 12).add(Aspect.ENTROPY, 12), ItemApi.getItem("itemResource", 12), new ItemStack[] { ItemApi.getItem("itemResource", 14), new ItemStack(ForbiddenItems.deadlyShards, 1, 2), new ItemStack(ForbiddenItems.deadlyShards, 1, 2), new ItemStack(ForbiddenItems.deadlyShards, 1, 2), new ItemStack(ForbiddenItems.deadlyShards, 1, 2), new ItemStack(ForbiddenItems.deadlyShards, 1, 2), new ItemStack(ForbiddenItems.deadlyShards, 1, 2) }));

		ForbiddenResearch.recipes.put("SkullAxe", ThaumcraftApi.addInfusionCraftingRecipe("SKULLAXE", new ItemStack(ForbiddenItems.skullAxe), 1, (new AspectList()).add(DarkAspects.WRATH, 8).add(Aspect.WEAPON, 8).add(DarkAspects.NETHER, 8), ItemApi.getItem("itemAxeThaumium", 0), new ItemStack[] { new ItemStack(ForbiddenItems.deadlyShards, 1, 0), new ItemStack(ForbiddenItems.deadlyShards, 1, 0), new ItemStack(Items.diamond), new ItemStack(Items.skull, 1, 1) }));
		ForbiddenResearch.recipes.put("ArcaneCake", ThaumcraftApi.addInfusionCraftingRecipe("ARCANECAKE", new ItemStack(ForbiddenItems.arcaneCakeItem), 3, (new AspectList()).add(DarkAspects.GLUTTONY, 12).add(Aspect.HUNGER, 24).add(Aspect.CRAFT, 24), new ItemStack(Items.cake), new ItemStack[] { ItemApi.getItem("itemResource", 14), new ItemStack(Items.egg), new ItemStack(Items.milk_bucket), new ItemStack(Items.egg), new ItemStack(Items.sugar), new ItemStack(Items.sugar) }));
		ForbiddenResearch.recipes.put("MorphPick", ThaumcraftApi.addInfusionCraftingRecipe("MORPHTOOLS", new ItemStack(ForbiddenItems.morphPickaxe), 6, (new AspectList()).add(Aspect.EXCHANGE, 32).add(DarkAspects.ENVY, 16).add(Aspect.TOOL, 16), ItemApi.getItem("itemPickThaumium", 0), new ItemStack[] { new ItemStack(ForbiddenItems.deadlyShards, 1, 1), new ItemStack(ForbiddenItems.deadlyShards, 1, 1), new ItemStack(Items.diamond), ItemApi.getItem("itemResource", 3), ItemApi.getBlock("blockMagicalLog", 1) }));
		ForbiddenResearch.recipes.put("MorphSword", ThaumcraftApi.addInfusionCraftingRecipe("MORPHTOOLS", new ItemStack(ForbiddenItems.morphSword), 6, (new AspectList()).add(Aspect.EXCHANGE, 32).add(DarkAspects.ENVY, 16).add(Aspect.WEAPON, 16), ItemApi.getItem("itemSwordThaumium", 0), new ItemStack[] { new ItemStack(ForbiddenItems.deadlyShards, 1, 1), new ItemStack(ForbiddenItems.deadlyShards, 1, 1), new ItemStack(Items.diamond), ItemApi.getItem("itemResource", 3), ItemApi.getBlock("blockMagicalLog", 1) }));
		ForbiddenResearch.recipes.put("MorphShovel", ThaumcraftApi.addInfusionCraftingRecipe("MORPHTOOLS", new ItemStack(ForbiddenItems.morphShovel), 6, (new AspectList()).add(Aspect.EXCHANGE, 32).add(DarkAspects.ENVY, 16).add(Aspect.TOOL, 16), ItemApi.getItem("itemShovelThaumium", 0), new ItemStack[] { new ItemStack(ForbiddenItems.deadlyShards, 1, 1), new ItemStack(ForbiddenItems.deadlyShards, 1, 1), new ItemStack(Items.diamond), ItemApi.getItem("itemResource", 3), ItemApi.getBlock("blockMagicalLog", 1) }));
		ForbiddenResearch.recipes.put("MorphAxe", ThaumcraftApi.addInfusionCraftingRecipe("MORPHTOOLS", new ItemStack(ForbiddenItems.morphAxe), 6, (new AspectList()).add(Aspect.EXCHANGE, 32).add(DarkAspects.ENVY, 16).add(Aspect.TOOL, 16), ItemApi.getItem("itemAxeThaumium", 0), new ItemStack[] { new ItemStack(ForbiddenItems.deadlyShards, 1, 1), new ItemStack(ForbiddenItems.deadlyShards, 1, 1), new ItemStack(Items.diamond), ItemApi.getItem("itemResource", 3), ItemApi.getBlock("blockMagicalLog", 1) }));
		ForbiddenResearch.recipes.put("WandRodInfernal", ThaumcraftApi.addInfusionCraftingRecipe("ROD_infernal", new ItemStack(ForbiddenItems.wandCore, 1, 1), 5, (new AspectList()).add(DarkAspects.NETHER, 32).add(Aspect.MAGIC, 12).add(DarkAspects.PRIDE, 12), new ItemStack(Items.blaze_rod), new ItemStack[] { ItemApi.getItem("itemResource", 14), new ItemStack(ForbiddenItems.deadlyShards, 1, 3), new ItemStack(ForbiddenItems.deadlyShards, 1, 3), new ItemStack(Blocks.soul_sand), new ItemStack(Items.skull, 1, 1), new ItemStack(Items.quartz), new ItemStack(Items.blaze_powder) }));
		if (Config.wrathCage) {
			ForbiddenResearch.recipes.put("WrathCage", ThaumcraftApi.addInfusionCraftingRecipe("WRATHCAGE", new ItemStack(ForbiddenBlocks.wrathCage, 1, 0), 10, (new AspectList()).add(DarkAspects.WRATH, 32).add(Aspect.MAGIC, 32).add(Aspect.BEAST, 32).add(Aspect.MECHANISM, 16), ItemApi.getBlock("blockCosmeticSolid", 4), new ItemStack[] { new ItemStack(ForbiddenItems.deadlyShards, 1, 0), new ItemStack(ForbiddenItems.deadlyShards, 1, 0), new ItemStack(ForbiddenItems.deadlyShards, 1, 0), new ItemStack(ForbiddenItems.deadlyShards, 1, 0), new ItemStack(Items.diamond, 1, 0), new ItemStack(Items.diamond, 1, 0), new ItemStack(Items.diamond, 1, 0), new ItemStack(Items.diamond, 1, 0), ItemApi.getBlock("blockJar", 0), ItemApi.getBlock("blockJar", 0), ItemApi.getBlock("blockJar", 0) }));
			ForbiddenResearch.recipes.put("MobCrystal", ThaumcraftApi.addCrucibleRecipe("WRATHCAGE", new ItemStack(ForbiddenItems.mobCrystal, 1, 0), new ItemStack(Items.diamond, 1), (new AspectList()).merge(Aspect.MIND, 10).merge(Aspect.ENERGY, 10)));
		}

		ForbiddenResearch.recipes.put("Fork", ThaumcraftApi.addInfusionCraftingRecipe("FORK", new ItemStack(ForbiddenItems.fork, 1, 0), 1, (new AspectList()).add(DarkAspects.NETHER, 8).add(Aspect.MECHANISM, 8).add(Aspect.ENERGY, 8), ItemApi.getItem("itemSwordThaumium", 0), new ItemStack[] { new ItemStack(Items.quartz), new ItemStack(Items.quartz), new ItemStack(Items.quartz), new ItemStack(Items.redstone) }));

		if (Config.emeraldTrans)
			ForbiddenResearch.recipes.put("TransEmerald", ThaumcraftApi.addCrucibleRecipe("TRANSEMERALD", new ItemStack(ForbiddenItems.resource, 4, 0), "nuggetEmerald", (new AspectList()).merge(Aspect.CRYSTAL, 2).merge(Aspect.GREED, 2)));
		ForbiddenResearch.recipes.put("BlackFlower", ThaumcraftApi.addCrucibleRecipe("BLACKFLOWER", new ItemStack(ForbiddenBlocks.roseBush, 1, 0), new ItemStack(Blocks.double_plant, 1, 4), (new AspectList()).merge(Aspect.DARKNESS, 8).merge(Aspect.LIFE, 5)));
		ForbiddenResearch.recipes.put("BlackInk", CraftingManager.getInstance().addRecipe(new ItemStack(ForbiddenItems.resource, 2, 1), new Object[] { "#", Character.valueOf('#'), new ItemStack(ForbiddenBlocks.blackFlower, 1, 0) }));

		ForbiddenResearch.recipes.put("Crystalwell", ThaumcraftApi.addShapelessArcaneCraftingRecipe("CRYSTALWELL", new ItemStack(ForbiddenItems.crystalwell, 1, 0), (new AspectList()).add(Aspect.EARTH, 2).add(Aspect.FIRE, 2).add(Aspect.AIR, 2).add(Aspect.WATER, 2).add(Aspect.ORDER, 2).add(Aspect.ENTROPY, 2), new Object[] { ItemApi.getItem("itemInkwell", 32767), "dyeBlack", ItemApi.getItem("itemShard", 32767), ItemApi.getItem("itemShard", 32767) }));

		ForbiddenResearch.recipes.put("Cluster", ThaumcraftApi.addInfusionEnchantmentRecipe("CLUSTER", DarkEnchantments.cluster, 3, (new AspectList()).add(Aspect.FIRE, 4).add(Aspect.METAL, 4).add(Aspect.GREED, 4), new ItemStack[] { ItemApi.getItem("itemPickElemental", 0), ItemApi.getItem("itemResource", 14) }));
		if (Config.greedyEnch)
			ForbiddenResearch.recipes.put("Greedy", ThaumcraftApi.addInfusionEnchantmentRecipe("GREEDY", DarkEnchantments.greedy, 4, (new AspectList()).add(DarkAspects.NETHER, 16).add(Aspect.WEAPON, 8).add(Aspect.GREED, 16), new ItemStack[] { new ItemStack(Items.golden_sword), new ItemStack(Items.diamond), new ItemStack(Blocks.gold_block), ItemApi.getItem("itemResource", 14) }));
		ForbiddenResearch.recipes.put("Consuming", ThaumcraftApi.addInfusionEnchantmentRecipe("CONSUMING", DarkEnchantments.consuming, 3, (new AspectList()).add(Aspect.VOID, 8).add(Aspect.TOOL, 8).add(Aspect.HUNGER, 8), new ItemStack[] { new ItemStack(Items.iron_pickaxe), new ItemStack(Items.lava_bucket), ItemApi.getItem("itemResource", 14) }));
		ForbiddenResearch.recipes.put("Pigbane", ThaumcraftApi.addInfusionEnchantmentRecipe("PIGBANE", DarkEnchantments.pigBane, 1, (new AspectList()).add(Aspect.HUNGER, 4).add(Aspect.WEAPON, 4).add(Aspect.BEAST, 4), new ItemStack[] { new ItemStack(Items.iron_sword), new ItemStack(Items.porkchop), ItemApi.getItem("itemResource", 14) }));
		ForbiddenResearch.recipes.put("Educational", ThaumcraftApi.addInfusionEnchantmentRecipe("EDUCATIONAL", DarkEnchantments.educational, 3, (new AspectList()).add(Aspect.MAGIC, 4).add(Aspect.WEAPON, 4).add(Aspect.MIND, 8), new ItemStack[] { ItemApi.getItem("itemResource", 5), new ItemStack(Items.book), ItemApi.getItem("itemResource", 14) }));
		ForbiddenResearch.recipes.put("Corrupting", ThaumcraftApi.addInfusionEnchantmentRecipe("CORRUPTING", DarkEnchantments.corrupting, 4, (new AspectList()).add(DarkAspects.NETHER, 16).add(Aspect.EXCHANGE, 16).add(Aspect.CRYSTAL, 8), new ItemStack[] { new ItemStack(Items.nether_wart), new ItemStack(Blocks.soul_sand), ItemApi.getItem("itemResource", 14) }));
        ForbiddenResearch.recipes.put("Eternal", ThaumcraftApi.addInfusionEnchantmentRecipe("ETERNAL", DarkEnchantments.eternal, 12, (new AspectList()).add(Aspect.CRAFT, 32).add(Aspect.TOOL, 64).add(DarkAspects.ENVY, 76).add(Aspect.MAGIC, 64), new ItemStack[] { new ItemStack(Items.nether_star), ItemApi.getItem("itemResource", 16), ItemApi.getItem("itemResource", 16), ItemApi.getItem("itemResource", 16), new ItemStack(ForbiddenItems.deadlyShards, 1, 1), new ItemStack(ForbiddenItems.deadlyShards, 1, 1), new ItemStack(ForbiddenItems.deadlyShards, 1, 1) }));

		CraftingManager.getInstance().addRecipe(new ItemStack(Items.emerald, 1, 0), new Object[] { "###", "###", "###", Character.valueOf('#'), new ItemStack(ForbiddenItems.resource, 1, 0) });
		CraftingManager.getInstance().addRecipe(new ItemStack(ForbiddenItems.resource, 9, 0), new Object[] { "#", Character.valueOf('#'), new ItemStack(Items.emerald, 1, 0) });
		CraftingManager.getInstance().addRecipe(new ItemStack(ForbiddenBlocks.starBlock, 1, 0), new Object[] { "###", "###", "###", Character.valueOf('#'), new ItemStack(Items.nether_star, 1, 0) });
		CraftingManager.getInstance().addRecipe(new ItemStack(Items.nether_star, 9, 0), new Object[] { "#", Character.valueOf('#'), new ItemStack(ForbiddenBlocks.starBlock, 1, 0) });
		ForbiddenResearch.recipes.put("RidingCrop", CraftingManager.getInstance().addRecipe(new ItemStack(ForbiddenItems.ridingCrop, 1, 0), new Object[] { "X", "#", "#", Character.valueOf('#'), Items.stick, Character.valueOf('X'), Items.leather }));
		ThaumcraftApi.addSmeltingBonus(new ItemStack(Items.emerald), new ItemStack(ForbiddenItems.resource, 0, 0));

		Iterator i$ = WandCap.caps.keySet().iterator();
		//while(i$.hasNext()) {
		//	String captag = (String)i$.next();
		//	Iterator i$1 = WandRod.rods.keySet().iterator();

		//	while(i$1.hasNext()) {
		//		String rodtag = (String)i$1.next();
		//		if((rodtag.equals("tainted") || rodtag.equals("infernal") || rodtag.equals("soul") || rodtag.equals("blood") || rodtag.equals("witchwood") || captag.equals("alchemical") || captag.equals("vinteum")) 
		//			&& !rodtag.equals("mercurial") && !rodtag.equals("neutronium") && !captag.equals("orichalcum"))
		//		{
		//			int cost = ((WandCap)WandCap.caps.get(captag)).getCraftCost() * ((WandRod)WandRod.rods.get(rodtag)).getCraftCost();
		//			ItemStack wand = ItemApi.getItem("itemWandCasting", cost);
		//			((ItemWandCasting)wand.getItem()).setCap(wand, (WandCap)WandCap.caps.get(captag));
		//			((ItemWandCasting)wand.getItem()).setRod(wand, (WandRod)WandRod.rods.get(rodtag));
		//			String key = "WAND_" + captag + "_" + rodtag;
		//			String res = "THAUMIUM";
		//			if(rodtag.equals("tainted"))
		//				res = "ROD_tainted";
		//			else if(rodtag.equals("infernal"))
		//				res = "ROD_infernal";
		//			else if(rodtag.equals("soul"))
		//				res = "ROD_soul";
		//			else if(rodtag.equals("blood"))
		//				res = "ROD_blood";
		//			else if(rodtag.equals("witchwood"))
		//				res = "ROD_witchwood";
		//			else if(captag.equals("alchemical"))
		//				res = "CAP_alchemical";
		//			else if(captag.equals("vinteum"))
		//				res = "CAP_vinteum";
		//			ForbiddenResearch.recipes.put(key, ThaumcraftApi.addArcaneCraftingRecipe(res, wand, (new AspectList()).add(Aspect.AIR, cost).add(Aspect.ORDER, cost).add(Aspect.EARTH, cost).add(Aspect.FIRE, cost).add(Aspect.WATER, cost).add(Aspect.ENTROPY, cost), new Object[]{"  C", " R ", "C  ", Character.valueOf('C'), ((WandCap)WandCap.caps.get(captag)).getItem(), Character.valueOf('R'), ((WandRod)WandRod.rods.get(rodtag)).getItem()}));
		//		}
		//	}
		//}
	}
}