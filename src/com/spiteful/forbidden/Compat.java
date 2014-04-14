package com.spiteful.forbidden;

import com.spiteful.forbidden.items.ForbiddenItems;
import com.spiteful.forbidden.enchantments.DarkEnchantments;

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
import net.minecraft.item.crafting.CraftingManager;
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
import thaumcraft.api.crafting.InfusionEnchantmentRecipe;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchCategoryList;
import thaumcraft.api.research.ResearchItem;
import thaumcraft.api.research.ResearchPage;
import kihira.playerbeacons.common.PlayerBeacons;
import vazkii.botania.api.BotaniaAPI;
import WayofTime.alchemicalWizardry.api.altarRecipeRegistry.AltarRecipeRegistry;

public class Compat {
	
	public static boolean tt = false;
	public static boolean kami = false;
	public static boolean tx = false;
	public static boolean natura = false;
	public static boolean pb = false;
	public static boolean bm = false;
	public static boolean am2 = false;
	public static boolean totes = false;
	public static boolean botan = false;
	
	public static Item fennCrystal;
	
	public static void initiate()
	{
		if(Loader.isModLoaded("ThaumicTinkerer"))
			tt = true;
		if(tt && Loader.isModLoaded("ThaumicTinkererKami"))
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
		if(Loader.isModLoaded("totemic"))
			totes = true;
		if(Loader.isModLoaded("Botania"))
			botan = true;
	}
	
	public static void compatify()
	{
		ItemStack i;
		AspectList list;

		if(tt)
		{
			if(!kami)
			{
				try
				{
					kami = Class.forName("vazkii.tinkerer.common.core.handler.ConfigHandler").getField("enableKami").getBoolean(null);
				}
				catch(Exception e){}
			}
			
			if(kami)
			{
			
				try {
			
					Item kamiResource = (Item)(Class.forName("vazkii.tinkerer.common.item.ModItems").getField("kamiResource").get(null));
					list = new AspectList().add(DarkAspects.NETHER, 2).add(Aspect.MAGIC, 1).add(Aspect.CRYSTAL, 1);
					ThaumcraftApi.registerObjectTag(kamiResource.itemID, 6, list);
					list = new AspectList().add(Aspect.ELDRITCH, 2).add(Aspect.MAGIC, 1).add(Aspect.CRYSTAL, 1);
					ThaumcraftApi.registerObjectTag(kamiResource.itemID, 7, list);
					
					InfusionEnchantmentRecipe eternal = ThaumcraftApi.addInfusionEnchantmentRecipe("ETERNAL", DarkEnchantments.eternal, 12, (new AspectList()).add(Aspect.CRAFT, 32).add(Aspect.TOOL, 64).add(DarkAspects.ENVY, 76).add(Aspect.MAGIC, 64), new ItemStack[]{new ItemStack(kamiResource.itemID, 1, 0), new ItemStack(kamiResource.itemID, 1, 2), new ItemStack(kamiResource.itemID, 1, 2), new ItemStack(kamiResource.itemID, 1, 2), new ItemStack(kamiResource.itemID, 1, 6), new ItemStack(ForbiddenItems.deadlyShards.itemID, 1, 1), new ItemStack(ForbiddenItems.deadlyShards.itemID, 1, 1), new ItemStack(ForbiddenItems.deadlyShards.itemID, 1, 1)});
					(new DarkResearchItem("ETERNAL", "FORBIDDEN", "[TTKami]", (new AspectList()).add(Aspect.MAGIC, 16).add(Aspect.TOOL, 10).add(Aspect.CRAFT, 8).add(DarkAspects.ENVY, 32), -5, 7, 6, new ResourceLocation("forbidden", "textures/misc/eternal.png"))).setPages(new ResearchPage[]{new ResearchPage("forbidden.research_page.ETERNAL.1"), new ResearchPage(eternal)}).setParents(new String[]{"MORPHTOOLS", "ICHOR_TOOLS"}).setConcealed().registerResearchItem();
					
				}
				catch(Exception e)
				{
					FMLLog.log(Level.INFO, e, "Forbidden Magic doesn't have Thaumic Tinkerer's nose.");
					e.printStackTrace();
				}
			
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
				if(PlayerBeacons.config.enableThaumcraft)
				{
				
					InfusionRecipe soul_recipe = ThaumcraftApi.addInfusionCraftingRecipe("ROD_soul", new ItemStack(ForbiddenItems.wandCore, 1, 2), 5, (new AspectList()).add(Aspect.ELDRITCH, 32).add(Aspect.MAGIC, 12).add(Aspect.SOUL, 16), new ItemStack(PlayerBeacons.defiledSoulPylonBlock), new ItemStack[]{ItemApi.getItem("itemResource", 14), new ItemStack(PlayerBeacons.defiledSoulConductorBlock), new ItemStack(PlayerBeacons.defiledSoulConductorBlock), new ItemStack(PlayerBeacons.defiledSoulConductorBlock), new ItemStack(PlayerBeacons.defiledSoulConductorBlock), new ItemStack(Item.eyeOfEnder), new ItemStack(Item.eyeOfEnder)});
					(new DarkResearchItem("ROD_soul", "FORBIDDEN", "[PB]", (new AspectList()).add(Aspect.ELDRITCH, 4).add(Aspect.SOUL, 3).add(Aspect.TOOL, 2), -3, -3, 5, new ItemStack(ForbiddenItems.wandCore, 1, 2))).setPages(new ResearchPage[]{new ResearchPage("forbidden.research_page.ROD_soul.1"), new ResearchPage(soul_recipe)}).setParents(new String[]{"ROD_silverwood", "PB_CRYSTAL", "INFAUXSION"}).setConcealed().registerResearchItem();
					
					IArcaneRecipe soul_cap = ThaumcraftApi.addArcaneCraftingRecipe("CAP_soul", new ItemStack(ForbiddenItems.wandCap, 1, 2), (new AspectList()).add(Aspect.ENTROPY, 20).add(Aspect.FIRE, 5).add(Aspect.WATER, 5).add(Aspect.AIR, 5).add(Aspect.EARTH, 5), new Object[]{"NXN", "N N", Character.valueOf('N'), Item.enderPearl, Character.valueOf('X'), PlayerBeacons.crystalItem});
					(new DarkResearchItem("CAP_soul", "FORBIDDEN", "[PB]", (new AspectList()).add(Aspect.ELDRITCH, 3).add(Aspect.MAGIC, 2).add(Aspect.ENTROPY, 4), -3, -5, 3, new ItemStack(ForbiddenItems.wandCap, 1, 2))).setPages(new ResearchPage[]{new ResearchPage("forbidden.research_page.CAP_soul.1"), new ResearchPage(soul_cap)}).setParents(new String[]{"ROD_soul"}).setSecondary().setConcealed().registerResearchItem();
				
				}
				 
			}
			catch(Throwable e)
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
				bloodItems = Class.forName("WayofTime.alchemicalWizardry.ModItems");
				
				Item masterOrb = (Item)(bloodItems.getField("masterBloodOrb").get(null));
				Item archmasterOrb = (Item)(bloodItems.getField("archmageBloodOrb").get(null));
				Item imbuedSlate = (Item)(bloodItems.getField("imbuedSlate").get(null));
				Item magicales = (Item)(bloodItems.getField("magicales").get(null));
				Item aether = (Item)(bloodItems.getField("aether").get(null));
				Item terrae = (Item)(bloodItems.getField("terrae").get(null));
				Item aquasalus = (Item)(bloodItems.getField("aquasalus").get(null));
				Item incendium = (Item)(bloodItems.getField("incendium").get(null));
				Item sanctus = (Item)(bloodItems.getField("sanctus").get(null));
				Item tennebrae = (Item)(bloodItems.getField("tennebrae").get(null));
				Item crapOrb = (Item)(bloodItems.getField("weakBloodOrb").get(null));
				Item demonShard = (Item)(bloodItems.getField("demonBloodShard").get(null));
				
				bloodItems = Class.forName("WayofTime.alchemicalWizardry.AlchemicalWizardry");
				Item bloodBucket = (Item)(bloodItems.getField("bucketLife").get(null));
				
				AltarRecipeRegistry.registerAltarRecipe(new ItemStack(ForbiddenItems.wandCore, 1, 3), new ItemStack(ForbiddenItems.wandCore, 1, 6), 4, 20000, 15, 20, false);
				
				InfusionRecipe blood_recipe = ThaumcraftApi.addInfusionCraftingRecipe("ROD_blood", new ItemStack(ForbiddenItems.wandCore, 1, 6), 2, (new AspectList()).add(Aspect.LIFE, 32).add(Aspect.MAGIC, 12).add(Aspect.WATER, 16), new ItemStack(masterOrb), new ItemStack[]{new ItemStack(magicales), new ItemStack(imbuedSlate), new ItemStack(imbuedSlate), new ItemStack(tennebrae), new ItemStack(sanctus), new ItemStack(aquasalus), new ItemStack(incendium), new ItemStack(terrae), new ItemStack(aether)});
				(new DarkResearchItem("ROD_blood", "FORBIDDEN", "[BM]", (new AspectList()).add(Aspect.LIFE, 5).add(Aspect.WATER, 4).add(Aspect.WEAPON, 3), -5, -3, 3, new ItemStack(ForbiddenItems.wandCore, 1, 3))).setPages(new ResearchPage[]{new ResearchPage("forbidden.research_page.ROD_blood.1"), new ResearchPage(blood_recipe), new ResearchPage("forbidden.research_page.ROD_blood.2")}).setParents(new String[]{"ROD_silverwood", "INFAUXSION"}).setAspectTriggers(new Aspect[]{Aspect.LIFE}).setConcealed().registerResearchItem();
				
				InfusionRecipe alchemical_recipe = ThaumcraftApi.addInfusionCraftingRecipe("CAP_alchemical", new ItemStack(ForbiddenItems.wandCap, 1, 0), 3, (new AspectList()).add(Aspect.LIFE, 12).add(Aspect.WATER, 6), ItemApi.getItem("itemWandCap", 1), new ItemStack[]{new ItemStack(magicales), new ItemStack(magicales), new ItemStack(magicales)});
				(new DarkResearchItem("CAP_alchemical", "FORBIDDEN", "[BM]", (new AspectList()).add(Aspect.LIFE, 4).add(Aspect.TOOL, 1).add(Aspect.WATER, 3), -5, -5, 2, new ItemStack(ForbiddenItems.wandCap, 1, 0))).setPages(new ResearchPage[]{new ResearchPage("forbidden.research_page.CAP_alchemical.1"), new ResearchPage(alchemical_recipe)}).setParents(new String[]{"ROD_blood", "CAP_gold"}).setSecondary().setConcealed().registerResearchItem();
				
				IArcaneRecipe bloodwell_recipe = ThaumcraftApi.addShapelessArcaneCraftingRecipe("BLOODWELL", new ItemStack(ForbiddenItems.bloodwell, 1, 0), (new AspectList()).add(Aspect.WATER, 10).add(Aspect.EARTH, 10), new Object[]{new ItemStack(Item.feather, 1, 0), new ItemStack(bloodBucket, 1, 0), new ItemStack(Item.glassBottle, 1, 0), new ItemStack(crapOrb, 1, 0)});
				(new DarkResearchItem("BLOODWELL", "FORBIDDEN", "[BM]", (new AspectList()).add(Aspect.LIFE, 5).add(Aspect.MIND, 4).add(Aspect.SENSES, 3), -10, 4, 1, new ItemStack(ForbiddenItems.bloodwell, 1, 0))).setPages(new ResearchPage[]{new ResearchPage("forbidden.research_page.BLOODWELL.1"), new ResearchPage(bloodwell_recipe)}).setParents(new String[]{"RESEARCH"}).setAspectTriggers(new Aspect[]{Aspect.LIFE}).setConcealed().registerResearchItem();
				
				IArcaneRecipe blood_staff = ThaumcraftApi.addArcaneCraftingRecipe("ROD_blood_staff", new ItemStack(ForbiddenItems.wandCore, 1, 9), (new AspectList()).add(Aspect.ENTROPY, 26).add(Aspect.FIRE, 26).add(Aspect.WATER, 26).add(Aspect.AIR, 26).add(Aspect.EARTH, 26).add(Aspect.ORDER, 26), new Object[]{"__D", "_B_", "B__", Character.valueOf('B'), new ItemStack(ForbiddenItems.wandCore, 1, 3), Character.valueOf('D'), new ItemStack(demonShard)});
				(new DarkResearchItem("ROD_blood_staff", "FORBIDDEN", "[BM]", (new AspectList()).add(Aspect.LIFE, 8).add(Aspect.WATER, 7).add(Aspect.WEAPON, 6), -5, -7, 5, new ItemStack(ForbiddenItems.wandCore, 1, 9))).setPages(new ResearchPage[]{new ResearchPage("forbidden.research_page.ROD_blood_staff.1"), new ResearchPage(blood_staff)}).setParents(new String[]{"ROD_silverwood_staff", "ROD_blood"}).setSpecial().setConcealed().registerResearchItem();
				
				if(kami)
				{
					try {
			
						Item kamiResource = (Item)(Class.forName("vazkii.tinkerer.common.item.ModItems").getField("kamiResource").get(null));
												
						InfusionRecipe divine_orb = ThaumcraftApi.addInfusionCraftingRecipe("DIVINEORB", new ItemStack(ForbiddenItems.bloodOrb, 1, 0), 12, (new AspectList()).add(Aspect.LIFE, 64).add(Aspect.MAGIC, 48).add(Aspect.WATER, 32).add(Aspect.VOID, 64), new ItemStack(archmasterOrb), new ItemStack[]{new ItemStack(kamiResource.itemID, 1, 0), new ItemStack(kamiResource.itemID, 1, 0), new ItemStack(kamiResource.itemID, 1, 0), new ItemStack(kamiResource.itemID, 1, 2), new ItemStack(kamiResource.itemID, 1, 2), new ItemStack(kamiResource.itemID, 1, 1), new ItemStack(kamiResource.itemID, 1, 6), new ItemStack(kamiResource.itemID, 1, 7), new ItemStack(ForbiddenItems.gluttonyShard)});
						(new DarkResearchItem("DIVINEORB", "FORBIDDEN", "[BM] [TTKami]", (new AspectList()).add(Aspect.LIFE, 32).add(Aspect.VOID, 24).add(Aspect.CRYSTAL, 24).add(Aspect.MAGIC, 12), -9, 6, 6, new ItemStack(ForbiddenItems.bloodOrb, 1, 0))).setPages(new ResearchPage[]{new ResearchPage("forbidden.research_page.DIVINEORB.1"), new ResearchPage(divine_orb)}).setParents(new String[]{"ICHOR", "INFAUXSION", "ICHOR_CLOTH", "ICHORIUM"}).setAspectTriggers(new Aspect[]{Aspect.LIFE}).setConcealed().setSpecial().registerResearchItem();
						
					}
					catch(Exception e)
					{
						FMLLog.log(Level.INFO, e, "Forbidden Magic doesn't have Thaumic Tinkerer's nose.");
						e.printStackTrace();
					}
					
				}
				
				list = (new AspectList()).add(Aspect.LIFE, 2).add(Aspect.MAGIC, 2).add(Aspect.CRYSTAL, 4);
				ThaumcraftApi.registerObjectTag(crapOrb.itemID, -1, list);
				
				list = (new AspectList()).add(Aspect.LIFE, 4).add(Aspect.MAGIC, 4).add(Aspect.CRYSTAL, 4);
				aspectBloodItem("apprenticeBloodOrb", -1, list);
				
				list = (new AspectList()).add(Aspect.LIFE, 6).add(Aspect.MAGIC, 6).add(Aspect.CRYSTAL, 4);
				aspectBloodItem("magicianBloodOrb", -1, list);
				
				list = (new AspectList()).add(Aspect.LIFE, 16).add(Aspect.MAGIC, 8).add(Aspect.CRYSTAL, 4);
				ThaumcraftApi.registerObjectTag(masterOrb.itemID, -1, list);
				
				list = (new AspectList()).add(Aspect.LIFE, 16).add(DarkAspects.NETHER, 8).add(Aspect.CRYSTAL, 4).add(Aspect.FIRE, 1).add(Aspect.WATER, 1).add(Aspect.AIR, 1).add(Aspect.EARTH, 1).add(Aspect.LIGHT, 1).add(Aspect.DARKNESS, 1);
				ThaumcraftApi.registerObjectTag(demonShard.itemID, -1, list);
				
				list = (new AspectList()).add(Aspect.LIFE, 32).add(DarkAspects.NETHER, 4).add(Aspect.MAGIC, 8).add(Aspect.CRYSTAL, 4);
				aspectBloodItem("archmageBloodOrb", -1, list);
				
				list = (new AspectList()).add(Aspect.ELDRITCH, 5).add(Aspect.WEAPON, 5).add(DarkAspects.WRATH, 5).add(Aspect.POISON, 1);
				aspectBloodItem("energySword", -1, list);
				
				list = (new AspectList()).add(Aspect.ELDRITCH, 5).add(Aspect.WEAPON, 6).add(DarkAspects.WRATH, 6).add(Aspect.POISON, 1);
				aspectBloodItem("energyBlaster", -1, list);
				
				list = (new AspectList()).add(Aspect.ELDRITCH, 6).add(Aspect.MINE, 5).add(DarkAspects.WRATH, 4).add(Aspect.TRAP, 4);
				aspectBloodItem("boundPickaxe", -1, list);
				
				list = (new AspectList()).add(Aspect.ELDRITCH, 4).add(Aspect.TOOL, 5).add(DarkAspects.WRATH, 2).add(Aspect.TRAP, 2);
				aspectBloodItem("boundShovel", -1, list);
				
				list = (new AspectList()).add(Aspect.ELDRITCH, 6).add(Aspect.TOOL, 5).add(DarkAspects.WRATH, 4).add(Aspect.TRAP, 4);
				aspectBloodItem("boundAxe", -1, list);
				
				list = (new AspectList()).add(Aspect.METAL, 8).add(Aspect.VOID, 1).add(Aspect.LIFE, 4);
				ThaumcraftApi.registerObjectTag(bloodBucket.itemID, -1, list);
				
				list = (new AspectList()).add(Aspect.LIGHT, 6).add(Aspect.ENERGY, 3);
				ThaumcraftApi.registerObjectTag(sanctus.itemID, -1, list);
				
				list = (new AspectList()).add(Aspect.DARKNESS, 6).add(Aspect.ENERGY, 3);
				ThaumcraftApi.registerObjectTag(tennebrae.itemID, -1, list);
				
				list = (new AspectList()).add(Aspect.MAGIC, 6).add(Aspect.ENERGY, 3);
				ThaumcraftApi.registerObjectTag(magicales.itemID, -1, list);
				
				list = (new AspectList()).add(Aspect.AIR, 6).add(Aspect.ENERGY, 3);
				ThaumcraftApi.registerObjectTag(aether.itemID, -1, list);
				
				list = (new AspectList()).add(Aspect.EARTH, 6).add(Aspect.ENERGY, 3);
				ThaumcraftApi.registerObjectTag(terrae.itemID, -1, list);
				
				list = (new AspectList()).add(Aspect.WATER, 6).add(Aspect.ENERGY, 3);
				ThaumcraftApi.registerObjectTag(aquasalus.itemID, -1, list);
				
				list = (new AspectList()).add(Aspect.FIRE, 6).add(Aspect.ENERGY, 3);
				ThaumcraftApi.registerObjectTag(incendium.itemID, -1, list);
				
				list = (new AspectList()).add(Aspect.ICE, 6).add(Aspect.ENERGY, 3);
				aspectBloodItem("crystallos", -1, list);
				
				list = (new AspectList()).add(Aspect.ENTROPY, 6).add(Aspect.ENERGY, 3);
				aspectBloodItem("crepitous", -1, list);
			}
			catch(Throwable e)
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
				Block witchwood = (Block)(amBlocks.getField("witchwoodLog").get(null));
				
				InfusionRecipe witchwood_recipe = ThaumcraftApi.addInfusionCraftingRecipe("ROD_witchwood", new ItemStack(ForbiddenItems.wandCore, 1, 4), 5, (new AspectList()).add(Aspect.MAGIC, 16).add(Aspect.EARTH, 9).add(Aspect.WATER, 9).add(Aspect.FIRE, 9).add(Aspect.AIR, 9), new ItemStack(witchwood), new ItemStack[]{new ItemStack(essence.itemID, 1, 0), new ItemStack(amOre.itemID, 1, 3), new ItemStack(essence.itemID, 1, 1), new ItemStack(essence.itemID, 1, 2), new ItemStack(essence.itemID, 1, 3), new ItemStack(essence.itemID, 1, 4), new ItemStack(amOre.itemID, 1, 6), new ItemStack(amOre.itemID, 1, 7), new ItemStack(amOre.itemID, 1, 2)});
				(new DarkResearchItem("ROD_witchwood", "FORBIDDEN", "[AM2]", (new AspectList()).add(Aspect.MAGIC, 6).add(Aspect.TREE, 5).add(Aspect.MIND, 4), -4, -3, 3, new ItemStack(ForbiddenItems.wandCore, 1, 4))).setPages(new ResearchPage[]{new ResearchPage("forbidden.research_page.ROD_witchwood.1"), new ResearchPage(witchwood_recipe)}).setParents(new String[]{"ROD_silverwood", "INFAUXSION"}).setConcealed().registerResearchItem();
				
				InfusionRecipe vinteum_recipe = ThaumcraftApi.addInfusionCraftingRecipe("CAP_vinteum", new ItemStack(ForbiddenItems.wandCap, 1, 1), 5, (new AspectList()).add(Aspect.ENERGY, 12).add(Aspect.MAGIC, 6), ItemApi.getItem("itemWandCap", 6), new ItemStack[]{new ItemStack(amOre.itemID, 1, 0), new ItemStack(amOre.itemID, 1, 0), new ItemStack(amOre.itemID, 1, 0)});
				(new DarkResearchItem("CAP_vinteum", "FORBIDDEN", "[AM2]", (new AspectList()).add(Aspect.MAGIC, 4).add(Aspect.TOOL, 1).add(Aspect.ENERGY, 2), -4, -5, 1, new ItemStack(ForbiddenItems.wandCap, 1, 1))).setPages(new ResearchPage[]{new ResearchPage("forbidden.research_page.CAP_vinteum.1"), new ResearchPage(vinteum_recipe)}).setParents(new String[]{"ROD_witchwood", "CAP_thaumium"}).setSecondary().setConcealed().registerResearchItem();
				
				IArcaneRecipe witchwood_staff = ThaumcraftApi.addArcaneCraftingRecipe("ROD_witchwood_staff", new ItemStack(ForbiddenItems.wandCore, 1, 10), (new AspectList()).add(Aspect.ENTROPY, 26).add(Aspect.FIRE, 26).add(Aspect.WATER, 26).add(Aspect.AIR, 26).add(Aspect.EARTH, 26).add(Aspect.ORDER, 26), new Object[]{"__D", "_B_", "B__", Character.valueOf('B'), new ItemStack(ForbiddenItems.wandCore, 1, 4), Character.valueOf('D'), new ItemStack(essence.itemID, 1, 10)});
				(new DarkResearchItem("ROD_witchwood_staff", "FORBIDDEN", "[AM2]", (new AspectList()).add(Aspect.MAGIC, 8).add(Aspect.MIND, 7).add(Aspect.TOOL, 6), -4, -7, 5, new ItemStack(ForbiddenItems.wandCore, 1, 10))).setPages(new ResearchPage[]{new ResearchPage("forbidden.research_page.ROD_witchwood_staff.1"), new ResearchPage(witchwood_staff)}).setParents(new String[]{"ROD_silverwood_staff", "ROD_witchwood"}).setSpecial().setConcealed().registerResearchItem();
			}
			catch(Exception e)
			{
				FMLLog.log(Level.INFO, e, "Forbidden Magic was slain by a Hecate.");
				e.printStackTrace();
				am2 = false;
			}
		}
		if(totes)
		{
			try
			{
				Class totems = Class.forName("totemic_commons.pokefenn.ModItems");
				fennCrystal = (Item)(totems.getField("chlorophyllCrystal").get(null));
				Class pokeblocks = Class.forName("totemic_commons.pokefenn.ModBlocks");
				Block infusedLog = (Block)(pokeblocks.getField("totemWoods").get(null));
				
				IArcaneRecipe totem_rod = ThaumcraftApi.addArcaneCraftingRecipe("ROD_totem", new ItemStack(ForbiddenItems.wandCore, 1, 5), (new AspectList()).add(Aspect.ENTROPY, 12).add(Aspect.FIRE, 12).add(Aspect.WATER, 12).add(Aspect.AIR, 12).add(Aspect.EARTH, 12).add(Aspect.ORDER, 12), new Object[]{"PF", "FP", Character.valueOf('F'), new ItemStack(infusedLog.blockID, 1, 0)});
				(new DarkResearchItem("ROD_totem", "FORBIDDEN", "[T]", (new AspectList()).add(Aspect.TREE, 8).add(Aspect.MAGIC, 3).add(Aspect.PLANT, 6), -2, -3, 1, new ItemStack(ForbiddenItems.wandCore, 1, 5))).setPages(new ResearchPage[]{new ResearchPage("forbidden.research_page.ROD_totem.1"), new ResearchPage(totem_rod)}).setParents(new String[]{"ROD_greatwood"}).setConcealed().registerResearchItem();
				
				
			}
			catch(Exception e)
			{
				FMLLog.log(Level.INFO, e, "Forbidden Magic tried to catch Totemic with a pokeball, but didn't weaken it enough.");
				e.printStackTrace();
				totes = false;
			}
		}
		if(botan)
		{
			try
			{
				Class botanItems = Class.forName("vazkii.botania.common.item.ModItems");
				Item rune = (Item)(botanItems.getField("rune").get(null));
				Item resource = (Item)(botanItems.getField("manaResource").get(null));
				Class blocktania = Class.forName("vazkii.botania.common.block.ModBlocks");
				Block livingLog = (Block)(blocktania.getField("livingwood").get(null));
				
				CraftingManager.getInstance().addRecipe(new ItemStack(resource, 1, 0), new Object[]{"###", "###", "###", Character.valueOf('#'), new ItemStack(ForbiddenItems.resource, 1, 2)});
				CraftingManager.getInstance().addRecipe(new ItemStack(ForbiddenItems.resource, 9, 2), new Object[]{"#", Character.valueOf('#'), new ItemStack(resource, 1, 0)});
				
				InfusionRecipe livingwood_rod = ThaumcraftApi.addInfusionCraftingRecipe("ROD_livingwood", new ItemStack(ForbiddenItems.wandCore, 1, 8), 3, (new AspectList()).add(Aspect.MAGIC, 16).add(Aspect.TREE, 32).add(Aspect.LIFE, 16).add(Aspect.SENSES, 8).add(Aspect.PLANT, 8), new ItemStack(livingLog), new ItemStack[]{new ItemStack(resource, 1, 2), new ItemStack(rune, 1, 8), new ItemStack(rune, 1, 0), new ItemStack(rune, 1, 1), new ItemStack(rune, 1, 2), new ItemStack(rune, 1, 3), new ItemStack(rune, 1, 4), new ItemStack(rune, 1, 5), new ItemStack(rune, 1, 6), new ItemStack(rune, 1, 7)});
				(new DarkResearchItem("ROD_livingwood", "FORBIDDEN", "[B]", (new AspectList()).add(Aspect.TREE, 8).add(Aspect.MAGIC, 4).add(Aspect.PLANT, 6), -1, -3, 3, new ItemStack(ForbiddenItems.wandCore, 1, 7))).setPages(new ResearchPage[]{new ResearchPage("forbidden.research_page.ROD_livingwood.1"), new ResearchPage(livingwood_rod)}).setParents(new String[]{"ROD_silverwood", "INFAUXSION"}).setConcealed().registerResearchItem();
				BotaniaAPI.registerManaInfusionRecipe(new ItemStack(ForbiddenItems.wandCore, 1, 7), new ItemStack(ForbiddenItems.wandCore, 1, 8), 10000);
				
				IArcaneRecipe manasteel_cap = ThaumcraftApi.addArcaneCraftingRecipe("CAP_manasteel", new ItemStack(ForbiddenItems.wandCap, 1, 4), (new AspectList()).add(Aspect.ENTROPY, 6).add(Aspect.FIRE, 6).add(Aspect.WATER, 6).add(Aspect.AIR, 6).add(Aspect.EARTH, 6).add(Aspect.ORDER, 6), new Object[]{"NNN", "N N", Character.valueOf('N'), new ItemStack(ForbiddenItems.resource, 1, 2)});
				(new DarkResearchItem("CAP_manasteel", "FORBIDDEN", "[B]", (new AspectList()).add(Aspect.ELDRITCH, 3).add(Aspect.MAGIC, 2).add(Aspect.ENTROPY, 4), -1, -5, 2, new ItemStack(ForbiddenItems.wandCap, 1, 3))).setPages(new ResearchPage[]{new ResearchPage("forbidden.research_page.CAP_manasteel.1"), new ResearchPage(manasteel_cap)}).setParents(new String[]{"ROD_livingwood"}).setSecondary().setConcealed().registerResearchItem();
				BotaniaAPI.registerManaInfusionRecipe(new ItemStack(ForbiddenItems.wandCap, 1, 3), new ItemStack(ForbiddenItems.wandCap, 1, 4), 1000);
			}
			catch(Throwable e)
			{
				FMLLog.log(Level.INFO, e, "Forbidden Magic: Botania? Do you wanna build a snowman?");
				e.printStackTrace();
				botan = false;
			}
		}
	}
	
	private static void aspectBloodItem(String target, int damage, AspectList list)
	{
		try
		{
			Class bloodItems = Class.forName("WayofTime.alchemicalWizardry.ModItems");
			Item item = (Item)(bloodItems.getField(target).get(null));
			
			ThaumcraftApi.registerObjectTag(item.itemID, damage, list);
		}
		catch(Exception e)
		{
			FMLLog.log(Level.INFO, e, "Forbidden Magic was unable to add aspects to " + target);
		}
	}
	
	private static void aspectBloodBlock(String target, int damage, AspectList list)
	{
		try
		{
			Class bloodItems = Class.forName("WayofTime.alchemicalWizardry.ModBlocks");
			Block block = (Block)(bloodItems.getField(target).get(null));
			
			ThaumcraftApi.registerObjectTag(block.blockID, damage, list);
		}
		catch(Exception e)
		{
			FMLLog.log(Level.INFO, e, "Forbidden Magic was unable to add aspects to " + target);
		}
	}
}