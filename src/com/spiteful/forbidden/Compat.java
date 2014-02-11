package com.spiteful.forbidden;

import com.spiteful.forbidden.items.ForbiddenItems;

import java.util.logging.Level;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.util.ResourceLocation;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.ItemApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.IEssentiaContainerItem;
import thaumcraft.api.wands.WandCap;
import thaumcraft.api.wands.WandRod;
import thaumcraft.api.wands.WandTriggerRegistry;
import thaumcraft.api.crafting.IArcaneRecipe;
import thaumcraft.api.crafting.InfusionRecipe;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchCategoryList;
import thaumcraft.api.research.ResearchItem;
import thaumcraft.api.research.ResearchPage;
import playerbeacons.common.PlayerBeacons;

public class Compat {
	
	public static boolean tt = false;
	public static boolean kami = false;
	public static boolean tx = false;
	public static boolean natura = false;
	public static boolean pb = false;
	public static boolean bm = false;
	public static boolean am2 = false;
	
	public static int tabletID = -1;
	
	public static void initiate()
	{
		if(Loader.isModLoaded("ThaumicTinkerer"))
			tt = true;
		if(Loader.isModLoaded("ThaumicTinkererKami"))
			kami = true;
		if(Loader.isModLoaded("ThaumicExploration"))
			tx = true;
		if(Loader.isModLoaded("Natura"))
			natura = true;
		if(Loader.isModLoaded("PlayerBeacons"))
			pb = true;
		if(Loader.isModLoaded("AWWayofTime"))
			bm = true;
		if(Loader.isModLoaded("arsmagica2"))
			am2 = true;
	}
	
	public static void compatify()
	{
		ItemStack i;
		AspectList list;

		if(tt && pb)
		{
			try
			{
				tabletID = ((Block)(Class.forName("vazkii.tinkerer.common.block.ModBlocks").getField("animationTablet").get(null))).blockID;
			}
			catch(Exception e)
			{
				FMLLog.log(Level.INFO, e, "Forbidden Magic doesn't have Thaumic Tinkerer's nose.");
				e.printStackTrace();
			}
		}
		
		if(tt && kami)
		{
			try {
		
				Item kamiShards = (Item)(Class.forName("vazkii.tinkerer.common.item.ModItems").getField("kamiResource").get(null));
				list = new AspectList().add(DarkAspects.NETHER, 2).add(Aspect.MAGIC, 1).add(Aspect.CRYSTAL, 1);
				ThaumcraftApi.registerObjectTag(kamiShards.itemID, 6, list);
				list = new AspectList().add(Aspect.ELDRITCH, 2).add(Aspect.MAGIC, 1).add(Aspect.CRYSTAL, 1);
				ThaumcraftApi.registerObjectTag(kamiShards.itemID, 7, list);
				
			}
			catch(Exception e)
			{
				FMLLog.log(Level.INFO, e, "Forbidden Magic doesn't have Thaumic Tinkerer's nose.");
				e.printStackTrace();
			}
		}
		
		if(natura)
		{
			try
			{
				list = (new AspectList()).add(Aspect.BEAST, 2).add(DarkAspects.NETHER, 4).add(DarkAspects.SLOTH, 2);
				ThaumcraftApi.registerEntityTag("Natura.Imp", list);
				list = (new AspectList()).add(Aspect.BEAST, 3).add(Aspect.FIRE, 3).add(DarkAspects.NETHER, 2);
				ThaumcraftApi.registerEntityTag("Natura.HeatscarSpider", list);
				ThaumcraftApi.registerEntityTag("Natura.FlameSpider", list);
				list = (new AspectList()).add(Aspect.BEAST, 1).add(Aspect.FIRE, 2).add(DarkAspects.NETHER, 1);
				ThaumcraftApi.registerEntityTag("Natura.BabyHeatscarSpider", list);
				ThaumcraftApi.registerEntityTag("Natura.FlameSpiderBaby", list);
				list = (new AspectList()).add(Aspect.BEAST, 2).add(Aspect.FIRE, 4).add(DarkAspects.NETHER, 2).add(Aspect.ENTROPY, 4);
				ThaumcraftApi.registerEntityTag("Natura.NitroCreeper", list);
				
				i = GameRegistry.findItemStack("Natura", "Dark Tree", 1);
				if(i != null){
					list = (new AspectList()).add(Aspect.TREE, 3).add(DarkAspects.WRATH, 2);
					ThaumcraftApi.registerObjectTag(i.itemID, 1, list);
					list = (new AspectList()).add(Aspect.TREE, 3).add(Aspect.DARKNESS, 2);
					ThaumcraftApi.registerObjectTag(i.itemID, 0, list);
				}
				i = GameRegistry.findItemStack("Natura", "soil.tainted", 1);
				if(i != null){
					list = ThaumcraftApiHelper.getObjectAspects(new ItemStack(i.itemID, 1, 0));
					list.add(DarkAspects.NETHER, 1);
					ThaumcraftApi.registerObjectTag(i.itemID, -1, list);
				}
				i = GameRegistry.findItemStack("Natura", "heatsand", 1);
				if(i != null){
					list = ThaumcraftApiHelper.getObjectAspects(new ItemStack(i.itemID, 1, 0));
					list.add(DarkAspects.NETHER, 1);
					ThaumcraftApi.registerObjectTag(i.itemID, -1, list);
				}
				i = GameRegistry.findItemStack("Natura", "rawImphide", 1);
				if(i != null){
					list = (new AspectList()).add(Aspect.FLESH, 4).add(Aspect.HUNGER, 1).add(DarkAspects.NETHER, 1);
					ThaumcraftApi.registerObjectTag(i.itemID, 0, list);
					list = (new AspectList()).add(Aspect.FLESH, 4).add(Aspect.POISON, 2).add(Aspect.FIRE, 2).add(DarkAspects.NETHER, 1);
					ThaumcraftApi.registerObjectTag(i.itemID, 1, list);
				}
			}
			catch(Exception e)
			{
				FMLLog.log(Level.INFO, e, "Forbidden Magic had an allergic reaction to Natura.");
				e.printStackTrace();
			}
		}
		if(pb)
		{
			try
			{
				InfusionRecipe soul_recipe = ThaumcraftApi.addInfusionCraftingRecipe("ROD_soul", new ItemStack(ForbiddenItems.wandCore, 1, 2), 5, (new AspectList()).add(Aspect.ELDRITCH, 32).add(Aspect.MAGIC, 12).add(Aspect.SOUL, 16), new ItemStack(PlayerBeacons.defiledSoulPylonBlock), new ItemStack[]{ItemApi.getItem("itemResource", 14), new ItemStack(PlayerBeacons.defiledSoulConductorBlock), new ItemStack(PlayerBeacons.defiledSoulConductorBlock), new ItemStack(PlayerBeacons.defiledSoulConductorBlock), new ItemStack(PlayerBeacons.defiledSoulConductorBlock), new ItemStack(Item.eyeOfEnder), new ItemStack(Item.eyeOfEnder)});
				(new DarkResearchItem("ROD_soul", "FORBIDDEN", (new AspectList()).add(Aspect.ELDRITCH, 2).add(Aspect.SOUL, 1).add(Aspect.TOOL, 1), -3, -3, 5, new ItemStack(ForbiddenItems.wandCore, 1, 2))).setPages(new ResearchPage[]{new ResearchPage("forbidden.research_page.ROD_soul.1"), new ResearchPage(soul_recipe)}).setParents(new String[]{"ROD_silverwood", "PB_CRYSTAL"}).setConcealed().registerResearchItem();
				
				IArcaneRecipe soul_cap = ThaumcraftApi.addArcaneCraftingRecipe("CAP_soul", new ItemStack(ForbiddenItems.wandCap, 1, 2), (new AspectList()).add(Aspect.ENTROPY, 20).add(Aspect.FIRE, 5).add(Aspect.WATER, 5).add(Aspect.AIR, 5).add(Aspect.EARTH, 5), new Object[]{"NXN", "N N", Character.valueOf('N'), Item.enderPearl, Character.valueOf('X'), PlayerBeacons.crystalItem});
				(new DarkResearchItem("CAP_soul", "FORBIDDEN", (new AspectList()).add(Aspect.ELDRITCH, 1).add(Aspect.MAGIC, 1).add(Aspect.ENTROPY, 2), -3, -5, 3, new ItemStack(ForbiddenItems.wandCap, 1, 2))).setPages(new ResearchPage[]{new ResearchPage("forbidden.research_page.CAP_soul.1"), new ResearchPage(soul_cap)}).setParents(new String[]{"ROD_soul"}).setConcealed().registerResearchItem();
				 
			}
			catch(LinkageError e)
			{
				FMLLog.log(Level.INFO, e, "Player Beacons showed Forbidden Magic who the Alpha Fox is.");
				//e.printStackTrace();
				pb = false;
			}
		}
		if(bm)
		{
			try
			{
				Class bloodItems;
				try
				{
					bloodItems = Class.forName("WayofTime.alchemicalWizardry.common.ModItems");
				}
				catch(Exception e)
				{
					bloodItems = Class.forName("WayofTime.alchemicalWizardry.AlchemicalWizardry");
				}
				Item masterOrb = (Item)(bloodItems.getField("masterBloodOrb").get(null));
				Item imbuedSlate = (Item)(bloodItems.getField("imbuedSlate").get(null));
				Item magicales = (Item)(bloodItems.getField("magicales").get(null));
				Item aether = (Item)(bloodItems.getField("aether").get(null));
				Item terrae = (Item)(bloodItems.getField("terrae").get(null));
				Item aquasalus = (Item)(bloodItems.getField("aquasalus").get(null));
				Item incendium = (Item)(bloodItems.getField("incendium").get(null));
				Item sanctus = (Item)(bloodItems.getField("sanctus").get(null));
				Item tennebrae = (Item)(bloodItems.getField("tennebrae").get(null));
				
				InfusionRecipe blood_recipe = ThaumcraftApi.addInfusionCraftingRecipe("ROD_blood", new ItemStack(ForbiddenItems.wandCore, 1, 3), 2, (new AspectList()).add(Aspect.LIFE, 32).add(Aspect.MAGIC, 12).add(Aspect.WATER, 16), new ItemStack(masterOrb), new ItemStack[]{new ItemStack(magicales), new ItemStack(imbuedSlate), new ItemStack(imbuedSlate), new ItemStack(tennebrae), new ItemStack(sanctus), new ItemStack(aquasalus), new ItemStack(incendium), new ItemStack(terrae), new ItemStack(aether)});
				(new DarkResearchItem("ROD_blood", "FORBIDDEN", (new AspectList()).add(Aspect.LIFE, 2).add(Aspect.WATER, 1).add(Aspect.WEAPON, 1), -5, -3, 4, new ItemStack(ForbiddenItems.wandCore, 1, 3))).setPages(new ResearchPage[]{new ResearchPage("forbidden.research_page.ROD_blood.1"), new ResearchPage(blood_recipe)}).setParents(new String[]{"ROD_silverwood"}).setConcealed().registerResearchItem();
				
				InfusionRecipe alchemical_recipe = ThaumcraftApi.addInfusionCraftingRecipe("CAP_alchemical", new ItemStack(ForbiddenItems.wandCap, 1, 0), 3, (new AspectList()).add(Aspect.LIFE, 12).add(Aspect.WATER, 6), ItemApi.getItem("itemWandCap", 1), new ItemStack[]{new ItemStack(magicales), new ItemStack(magicales), new ItemStack(magicales)});
				(new DarkResearchItem("CAP_alchemical", "FORBIDDEN", (new AspectList()).add(Aspect.METAL, 1).add(Aspect.LIFE, 2).add(Aspect.TOOL, 1).add(Aspect.WATER, 1), -5, -5, 5, new ItemStack(ForbiddenItems.wandCap, 1, 0))).setPages(new ResearchPage[]{new ResearchPage("forbidden.research_page.CAP_alchemical.1"), new ResearchPage(alchemical_recipe)}).setParents(new String[]{"ROD_blood"}).setConcealed().registerResearchItem();
				
			}
			catch(Exception e)
			{
				FMLLog.log(Level.INFO, e, "Forbidden Magic tried to do some Blood Magic, but bled out.");
				e.printStackTrace();
				bm = false;
			}
		}
		if(am2)
		{
			try
			{
				Class amItems = Class.forName("am2.items.ItemsCommonProxy");
				Class amBlocks = Class.forName("am2.blocks.BlocksCommonProxy");
				Item essence = (Item)(amItems.getField("essence").get(null));
				Item amOre = (Item)(amItems.getField("itemOre").get(null));
				Item rune = (Item)(amItems.getField("rune").get(null));
				Block witchwood = (Block)(amBlocks.getField("witchwoodLog").get(null));
				
				InfusionRecipe witchwood_recipe = ThaumcraftApi.addInfusionCraftingRecipe("ROD_witchwood", new ItemStack(ForbiddenItems.wandCore, 1, 4), 5, (new AspectList()).add(Aspect.MAGIC, 16).add(Aspect.EARTH, 9).add(Aspect.WATER, 9).add(Aspect.FIRE, 9).add(Aspect.AIR, 9), new ItemStack(witchwood), new ItemStack[]{new ItemStack(amOre.itemID, 1, 3), new ItemStack(essence.itemID, 1, 1), new ItemStack(essence.itemID, 1, 2), new ItemStack(essence.itemID, 1, 3), new ItemStack(essence.itemID, 1, 4), new ItemStack(rune.itemID, 1, 7), new ItemStack(rune.itemID, 1, 8), new ItemStack(amOre.itemID, 1, 2)});
				(new DarkResearchItem("ROD_witchwood", "FORBIDDEN", (new AspectList()).add(Aspect.MAGIC, 2).add(Aspect.TREE, 1).add(Aspect.TOOL, 1), -4, -3, 4, new ItemStack(ForbiddenItems.wandCore, 1, 4))).setPages(new ResearchPage[]{new ResearchPage("forbidden.research_page.ROD_witchwood.1"), new ResearchPage(witchwood_recipe)}).setParents(new String[]{"ROD_silverwood"}).setConcealed().registerResearchItem();
				
				InfusionRecipe vinteum_recipe = ThaumcraftApi.addInfusionCraftingRecipe("CAP_vinteum", new ItemStack(ForbiddenItems.wandCap, 1, 1), 5, (new AspectList()).add(Aspect.ENERGY, 12).add(Aspect.MAGIC, 6), ItemApi.getItem("itemWandCap", 6), new ItemStack[]{new ItemStack(amOre.itemID, 1, 0), new ItemStack(amOre.itemID, 1, 0), new ItemStack(amOre.itemID, 1, 0)});
				(new DarkResearchItem("CAP_vinteum", "FORBIDDEN", (new AspectList()).add(Aspect.METAL, 1).add(Aspect.MAGIC, 2).add(Aspect.TOOL, 1).add(Aspect.ENERGY, 1), -4, -5, 5, new ItemStack(ForbiddenItems.wandCap, 1, 1))).setPages(new ResearchPage[]{new ResearchPage("forbidden.research_page.CAP_vinteum.1"), new ResearchPage(vinteum_recipe)}).setParents(new String[]{"ROD_witchwood"}).setConcealed().registerResearchItem();
			}
			catch(Exception e)
			{
				FMLLog.log(Level.INFO, e, "Forbidden Magic was slain by a Hecate.");
				e.printStackTrace();
				am2 = false;
			}
		}
	}
}