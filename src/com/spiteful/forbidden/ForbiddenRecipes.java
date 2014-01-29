package com.spiteful.forbidden;

import com.spiteful.forbidden.items.ForbiddenItems;
import com.spiteful.forbidden.blocks.ForbiddenBlocks;
import com.spiteful.forbidden.enchantments.DarkEnchantments;

import cpw.mods.fml.common.registry.GameRegistry;
import java.util.Arrays;
import java.util.Iterator;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagByte;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.ItemApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.IEssentiaContainerItem;
import thaumcraft.api.wands.WandCap;
import thaumcraft.api.wands.WandRod;
import thaumcraft.api.wands.WandTriggerRegistry;
import thaumcraft.api.crafting.InfusionRecipe;
import thaumcraft.common.items.wands.ItemWandCasting;

public class ForbiddenRecipes
{
	public static void addRecipes()
	{
		ForbiddenResearch.recipes.put("SkullAxe", ThaumcraftApi.addInfusionCraftingRecipe("SKULLAXE", new ItemStack(ForbiddenItems.skullAxe), 1, (new AspectList()).add(DarkAspects.WRATH, 8).add(Aspect.WEAPON, 8).add(DarkAspects.NETHER, 8), ItemApi.getItem("itemAxeThaumium", 0), new ItemStack[] {new ItemStack(ForbiddenItems.deadlyShards.itemID, 1, 0), new ItemStack(ForbiddenItems.deadlyShards.itemID, 1, 0), new ItemStack(Item.diamond), new ItemStack(Item.skull.itemID, 1, 1)}));
		ForbiddenResearch.recipes.put("ArcaneCake", ThaumcraftApi.addInfusionCraftingRecipe("ARCANECAKE", new ItemStack(ForbiddenItems.arcaneCakeItem), 3, (new AspectList()).add(DarkAspects.GLUTTONY, 12).add(Aspect.HUNGER, 24).add(Aspect.CRAFT, 24), new ItemStack(Item.cake), new ItemStack[] {ItemApi.getItem("itemResource", 14), new ItemStack(Item.egg), new ItemStack(Item.bucketMilk), new ItemStack(Item.egg), new ItemStack(Item.sugar), new ItemStack(Item.sugar)}));
		ForbiddenResearch.recipes.put("TaintShovel", ThaumcraftApi.addInfusionCraftingRecipe("TAINTSHOVEL", new ItemStack(ForbiddenItems.taintShovel), 5, (new AspectList()).add(Aspect.HEAL, 8).add(Aspect.CRYSTAL, 24).add(Aspect.MINE, 12), ItemApi.getItem("itemShovelThaumium", 0), new ItemStack[]{ItemApi.getItem("itemShard", 4), ItemApi.getItem("itemShard", 4), new ItemStack(Item.diamond), ItemApi.getBlock("blockMagicalLog", 1)}));
		ForbiddenResearch.recipes.put("TaintPick", ThaumcraftApi.addInfusionCraftingRecipe("TAINTPICK", new ItemStack(ForbiddenItems.taintPickaxe), 1, (new AspectList()).add(Aspect.ENTROPY, 8).add(Aspect.MINE, 8).add(Aspect.TAINT, 12), ItemApi.getItem("itemPickThaumium", 0), new ItemStack[]{ItemApi.getItem("itemShard", 5), ItemApi.getItem("itemShard", 5), new ItemStack(Item.diamond), ItemApi.getBlock("blockMagicalLog", 0)}));
		ForbiddenResearch.recipes.put("MorphPick", ThaumcraftApi.addInfusionCraftingRecipe("MORPHTOOLS", new ItemStack(ForbiddenItems.morphPickaxe), 6, (new AspectList()).add(Aspect.EXCHANGE, 32).add(DarkAspects.ENVY, 16).add(Aspect.TOOL, 16), ItemApi.getItem("itemPickThaumium", 0), new ItemStack[]{new ItemStack(ForbiddenItems.deadlyShards.itemID, 1, 1), new ItemStack(ForbiddenItems.deadlyShards.itemID, 1, 1), new ItemStack(Item.diamond), ItemApi.getItem("itemResource", 3), ItemApi.getBlock("blockMagicalLog", 1)}));
		ForbiddenResearch.recipes.put("MorphSword", ThaumcraftApi.addInfusionCraftingRecipe("MORPHTOOLS", new ItemStack(ForbiddenItems.morphSword), 6, (new AspectList()).add(Aspect.EXCHANGE, 32).add(DarkAspects.ENVY, 16).add(Aspect.WEAPON, 16), ItemApi.getItem("itemSwordThaumium", 0), new ItemStack[]{new ItemStack(ForbiddenItems.deadlyShards.itemID, 1, 1), new ItemStack(ForbiddenItems.deadlyShards.itemID, 1, 1), new ItemStack(Item.diamond), ItemApi.getItem("itemResource", 3), ItemApi.getBlock("blockMagicalLog", 1)}));
		ForbiddenResearch.recipes.put("MorphShovel", ThaumcraftApi.addInfusionCraftingRecipe("MORPHTOOLS", new ItemStack(ForbiddenItems.morphShovel), 6, (new AspectList()).add(Aspect.EXCHANGE, 32).add(DarkAspects.ENVY, 16).add(Aspect.TOOL, 16), ItemApi.getItem("itemShovelThaumium", 0), new ItemStack[]{new ItemStack(ForbiddenItems.deadlyShards.itemID, 1, 1), new ItemStack(ForbiddenItems.deadlyShards.itemID, 1, 1), new ItemStack(Item.diamond), ItemApi.getItem("itemResource", 3), ItemApi.getBlock("blockMagicalLog", 1)}));
		ForbiddenResearch.recipes.put("MorphAxe", ThaumcraftApi.addInfusionCraftingRecipe("MORPHTOOLS", new ItemStack(ForbiddenItems.morphAxe), 6, (new AspectList()).add(Aspect.EXCHANGE, 32).add(DarkAspects.ENVY, 16).add(Aspect.TOOL, 16), ItemApi.getItem("itemAxeThaumium", 0), new ItemStack[]{new ItemStack(ForbiddenItems.deadlyShards.itemID, 1, 1), new ItemStack(ForbiddenItems.deadlyShards.itemID, 1, 1), new ItemStack(Item.diamond), ItemApi.getItem("itemResource", 3), ItemApi.getBlock("blockMagicalLog", 1)}));
		ForbiddenResearch.recipes.put("WandRodTainted", ThaumcraftApi.addInfusionCraftingRecipe("ROD_tainted", new ItemStack(ForbiddenItems.wandCore, 1, 0), 5, (new AspectList()).add(Aspect.TAINT, 24).add(Aspect.MAGIC, 12).add(Aspect.ENTROPY, 12), ItemApi.getItem("itemResource", 12), new ItemStack[]{ItemApi.getItem("itemResource", 14), new ItemStack(ForbiddenItems.deadlyShards, 1, 2), new ItemStack(ForbiddenItems.deadlyShards, 1, 2), new ItemStack(ForbiddenItems.deadlyShards, 1, 2), new ItemStack(ForbiddenItems.deadlyShards, 1, 2), new ItemStack(ForbiddenItems.deadlyShards, 1, 2), new ItemStack(ForbiddenItems.deadlyShards, 1, 2)}));
		ForbiddenResearch.recipes.put("WandRodInfernal", ThaumcraftApi.addInfusionCraftingRecipe("ROD_infernal", new ItemStack(ForbiddenItems.wandCore, 1, 1), 5, (new AspectList()).add(DarkAspects.NETHER, 32).add(Aspect.MAGIC, 12).add(DarkAspects.PRIDE, 12), new ItemStack(Item.blazeRod), new ItemStack[]{ItemApi.getItem("itemResource", 14), new ItemStack(ForbiddenItems.deadlyShards, 1, 3), new ItemStack(ForbiddenItems.deadlyShards, 1, 3), new ItemStack(Block.slowSand), new ItemStack(Item.skull.itemID, 1, 1), new ItemStack(Item.netherQuartz), new ItemStack(Item.blazePowder)}));
		if(Config.wrathCage){
			ForbiddenResearch.recipes.put("WrathCage", ThaumcraftApi.addInfusionCraftingRecipe("WRATHCAGE", new ItemStack(ForbiddenBlocks.wrathCage, 1, 0), 10, (new AspectList()).add(DarkAspects.WRATH, 32).add(Aspect.MAGIC, 32).add(Aspect.BEAST, 32).add(Aspect.MECHANISM, 16), ItemApi.getBlock("blockCosmeticSolid", 4), new ItemStack[]{new ItemStack(ForbiddenItems.deadlyShards, 1, 0), new ItemStack(ForbiddenItems.deadlyShards, 1, 0), new ItemStack(ForbiddenItems.deadlyShards, 1, 0), new ItemStack(ForbiddenItems.deadlyShards, 1, 0), new ItemStack(Item.diamond, 1, 0), new ItemStack(Item.diamond, 1, 0), new ItemStack(Item.diamond, 1, 0), new ItemStack(Item.diamond, 1, 0), ItemApi.getBlock("blockJar", 0), ItemApi.getBlock("blockJar", 0), ItemApi.getBlock("blockJar", 0)}));
			ForbiddenResearch.recipes.put("MobCrystal", ThaumcraftApi.addCrucibleRecipe("WRATHCAGE", new ItemStack(ForbiddenItems.mobCrystal, 1, 0), new ItemStack(Item.diamond, 1), (new AspectList()).merge(Aspect.MIND, 10).merge(Aspect.ENERGY, 10)));
		}
		
		ForbiddenResearch.recipes.put("Fork", ThaumcraftApi.addInfusionCraftingRecipe("FORK", new ItemStack(ForbiddenItems.fork, 1, 0), 1, (new AspectList()).add(DarkAspects.NETHER, 8).add(Aspect.MECHANISM, 8).add(Aspect.ENERGY, 8), ItemApi.getItem("itemSwordThaumium", 0), new ItemStack[]{new ItemStack(Item.netherQuartz), new ItemStack(Item.netherQuartz), new ItemStack(Item.netherQuartz), new ItemStack(Item.redstone)}));
		
		if(Config.emeraldTrans)
			ForbiddenResearch.recipes.put("TransEmerald", ThaumcraftApi.addCrucibleRecipe("TRANSEMERALD", new ItemStack(ForbiddenItems.resource, 4, 0), "nuggetEmerald", (new AspectList()).merge(Aspect.CRYSTAL, 2).merge(Aspect.GREED, 2)));
		ForbiddenResearch.recipes.put("BlackFlower", ThaumcraftApi.addCrucibleRecipe("BLACKFLOWER", new ItemStack(ForbiddenBlocks.blackFlower, 1, 0), new ItemStack(Block.plantRed), (new AspectList()).merge(Aspect.DARKNESS, 5).merge(Aspect.LIFE, 5)));
		ForbiddenResearch.recipes.put("BlackInk", CraftingManager.getInstance().addRecipe(new ItemStack(ForbiddenItems.resource, 2, 1), new Object[]{"#", Character.valueOf('#'), new ItemStack(ForbiddenBlocks.blackFlower, 1, 0)}));
		
		ForbiddenResearch.recipes.put("Cluster", ThaumcraftApi.addInfusionEnchantmentRecipe("CLUSTER", DarkEnchantments.cluster, 3, (new AspectList()).add(Aspect.FIRE, 4).add(Aspect.METAL, 4).add(Aspect.GREED, 4), new ItemStack[]{ItemApi.getItem("itemPickElemental", 0), ItemApi.getItem("itemResource", 14)}));
		if(Config.greedyEnch)
			ForbiddenResearch.recipes.put("Greedy", ThaumcraftApi.addInfusionEnchantmentRecipe("GREEDY", DarkEnchantments.greedy, 4, (new AspectList()).add(DarkAspects.NETHER, 16).add(Aspect.WEAPON, 8).add(Aspect.GREED, 16), new ItemStack[]{new ItemStack(Item.swordGold), new ItemStack(Item.diamond), new ItemStack(Block.blockGold), ItemApi.getItem("itemResource", 14)}));
		ForbiddenResearch.recipes.put("Consuming", ThaumcraftApi.addInfusionEnchantmentRecipe("CONSUMING", DarkEnchantments.consuming, 3, (new AspectList()).add(Aspect.VOID, 8).add(Aspect.TOOL, 8).add(Aspect.HUNGER, 8), new ItemStack[]{new ItemStack(Item.pickaxeIron), new ItemStack(Item.bucketLava), ItemApi.getItem("itemResource", 14)}));
		ForbiddenResearch.recipes.put("Pigbane", ThaumcraftApi.addInfusionEnchantmentRecipe("PIGBANE", DarkEnchantments.pigBane, 1, (new AspectList()).add(Aspect.HUNGER, 4).add(Aspect.WEAPON, 4).add(Aspect.BEAST, 4), new ItemStack[]{new ItemStack(Item.swordIron), new ItemStack(Item.porkRaw), ItemApi.getItem("itemResource", 14)}));
		ForbiddenResearch.recipes.put("Educational", ThaumcraftApi.addInfusionEnchantmentRecipe("EDUCATIONAL", DarkEnchantments.educational, 3, (new AspectList()).add(Aspect.MAGIC, 4).add(Aspect.WEAPON, 4).add(Aspect.MIND, 8), new ItemStack[]{ItemApi.getItem("itemResource", 5), new ItemStack(Item.book), ItemApi.getItem("itemResource", 14)}));
		
		CraftingManager.getInstance().addRecipe(new ItemStack(Item.emerald, 1, 0), new Object[]{"###", "###", "###", Character.valueOf('#'), new ItemStack(ForbiddenItems.resource, 1, 0)});
		CraftingManager.getInstance().addRecipe(new ItemStack(ForbiddenItems.resource, 9, 0), new Object[]{"#", Character.valueOf('#'), new ItemStack(Item.emerald, 1, 0)});
		ThaumcraftApi.addSmeltingBonus(new ItemStack(Item.emerald), new ItemStack(ForbiddenItems.resource, 0, 0));
		
		Iterator i$ = WandCap.caps.keySet().iterator();

		while(i$.hasNext()) {
			String captag = (String)i$.next();
			Iterator i$1 = WandRod.rods.keySet().iterator();

			while(i$1.hasNext()) {
				String rodtag = (String)i$1.next();
				if((rodtag.equals("tainted") || rodtag.equals("infernal") || rodtag.equals("soul") || rodtag.equals("blood") || rodtag.equals("witchwood") || captag.equals("alchemical") || captag.equals("vinteum")) 
					&& !rodtag.equals("mercurial") && !rodtag.equals("neutronium") && !captag.equals("orichalcum"))
				{
					int cost = ((WandCap)WandCap.caps.get(captag)).getCraftCost() * ((WandRod)WandRod.rods.get(rodtag)).getCraftCost();
					ItemStack wand = ItemApi.getItem("itemWandCasting", cost);
					((ItemWandCasting)wand.getItem()).setCap(wand, (WandCap)WandCap.caps.get(captag));
					((ItemWandCasting)wand.getItem()).setRod(wand, (WandRod)WandRod.rods.get(rodtag));
					String key = "WAND_" + captag + "_" + rodtag;
					String res = "THAUMIUM";
					if(rodtag.equals("tainted"))
						res = "ROD_tainted";
					else if(rodtag.equals("infernal"))
						res = "ROD_infernal";
					else if(rodtag.equals("soul"))
						res = "ROD_soul";
					else if(rodtag.equals("blood"))
						res = "ROD_blood";
					else if(rodtag.equals("witchwood"))
						res = "ROD_witchwood";
					else if(captag.equals("alchemical"))
						res = "CAP_alchemical";
					else if(captag.equals("vinteum"))
						res = "CAP_vinteum";
					ForbiddenResearch.recipes.put(key, ThaumcraftApi.addArcaneCraftingRecipe(res, wand, (new AspectList()).add(Aspect.AIR, cost).add(Aspect.ORDER, cost).add(Aspect.EARTH, cost).add(Aspect.FIRE, cost).add(Aspect.WATER, cost).add(Aspect.ENTROPY, cost), new Object[]{"  C", " R ", "C  ", Character.valueOf('C'), ((WandCap)WandCap.caps.get(captag)).getItem(), Character.valueOf('R'), ((WandRod)WandRod.rods.get(rodtag)).getItem()}));
				}
			}
		}
		
		if(!Config.noMeddling){
		
			i$ = ThaumcraftApi.getCraftingRecipes().iterator();
			
			while(i$.hasNext()){
				Object recipe = i$.next();
				if(recipe instanceof InfusionRecipe){
					InfusionRecipe fuse = (InfusionRecipe)recipe;
					if(fuse.getResearch().equals("FOCUSHELLBAT")){
						fuse.aspects = (new AspectList()).add(Aspect.FIRE, 25).add(DarkAspects.WRATH, 15).add(DarkAspects.NETHER, 15).add(Aspect.ENTROPY, 25);
						fuse.recipeInput = new ItemStack(ForbiddenItems.deadlyShards, 1, 0);
						ForbiddenResearch.recipes.put("FocusHellbat", fuse);
						break;
					
					}
				}
			}
			
			ForbiddenResearch.recipes.put("InfernalFurnace", Arrays.asList(new Object[]{(new AspectList()).add(Aspect.FIRE, 50).add(Aspect.EARTH, 50), Integer.valueOf(3), Integer.valueOf(3), Integer.valueOf(3), Arrays.asList(new ItemStack[]{new ItemStack(Block.netherBrick), new ItemStack(Block.obsidian), new ItemStack(Block.netherBrick), new ItemStack(Block.obsidian), ItemApi.getBlock("blockHole", 15), new ItemStack(Block.obsidian), new ItemStack(Block.netherBrick), new ItemStack(Block.obsidian), new ItemStack(Block.netherBrick), new ItemStack(Block.netherBrick), new ItemStack(Block.obsidian), new ItemStack(Block.netherBrick), new ItemStack(Block.obsidian), new ItemStack(Block.lavaMoving), new ItemStack(Block.fenceIron), new ItemStack(Block.netherBrick), new ItemStack(Block.obsidian), new ItemStack(Block.netherBrick), new ItemStack(Block.netherBrick), new ItemStack(Block.obsidian), new ItemStack(Block.netherBrick), new ItemStack(Block.obsidian), new ItemStack(Block.obsidian), new ItemStack(Block.obsidian), new ItemStack(Block.netherBrick), new ItemStack(Block.obsidian), new ItemStack(Block.netherBrick)})}));
		}
	}
}