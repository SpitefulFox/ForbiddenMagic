package com.spiteful.forbidden.compat;

import static com.spiteful.forbidden.compat.Compat.bm;
import static com.spiteful.forbidden.compat.Compat.kami;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import org.apache.logging.log4j.Level;

import thaumcraft.api.ItemApi;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.IArcaneRecipe;
import thaumcraft.api.crafting.InfusionRecipe;
import thaumcraft.api.research.ResearchPage;
import WayofTime.alchemicalWizardry.api.altarRecipeRegistry.AltarRecipeRegistry;

import com.spiteful.forbidden.DarkAspects;
import com.spiteful.forbidden.DarkResearchItem;
import com.spiteful.forbidden.LogHandler;
import com.spiteful.forbidden.items.ForbiddenItems;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.registry.GameRegistry;

public class BloodMagic {
	public static void stab() {
		try {
			ItemStack i;
			AspectList list;
			Item masterOrb = GameRegistry.findItem("AWWayofTime", "masterBloodOrb");
			Item archmasterOrb = GameRegistry.findItem("AWWayofTime", "archmageBloodOrb");
			Item imbuedSlate = GameRegistry.findItem("AWWayofTime", "imbuedSlate");
			Item magicales = GameRegistry.findItem("AWWayofTime", "magicales");
			Item aether = GameRegistry.findItem("AWWayofTime", "aether");
			Item terrae = GameRegistry.findItem("AWWayofTime", "terrae");
			Item aquasalus = GameRegistry.findItem("AWWayofTime", "aquasalus");
			Item incendium = GameRegistry.findItem("AWWayofTime", "incendium");
			Item sanctus = GameRegistry.findItem("AWWayofTime", "sanctus");
			Item tennebrae = GameRegistry.findItem("AWWayofTime", "tennebrae");
			Item crapOrb = GameRegistry.findItem("AWWayofTime", "weakBloodOrb");
			Item demonShard = GameRegistry.findItem("AWWayofTime", "demonBloodShard");
			Item bloodBucket = GameRegistry.findItem("AWWayofTime", "bucketLife");

			AltarRecipeRegistry.registerAltarRecipe(new ItemStack(ForbiddenItems.wandCore, 1, 3), new ItemStack(ForbiddenItems.wandCore, 1, 6), 4, 20000, 15, 20, false);

			InfusionRecipe blood_recipe = ThaumcraftApi.addInfusionCraftingRecipe("ROD_blood", new ItemStack(ForbiddenItems.wandCore, 1, 6), 2, (new AspectList()).add(Aspect.LIFE, 32).add(Aspect.MAGIC, 12).add(Aspect.WATER, 16), new ItemStack(masterOrb), new ItemStack[] { new ItemStack(magicales), new ItemStack(imbuedSlate), new ItemStack(imbuedSlate), new ItemStack(tennebrae), new ItemStack(sanctus), new ItemStack(aquasalus), new ItemStack(incendium), new ItemStack(terrae), new ItemStack(aether) });
			(new DarkResearchItem("ROD_blood", "FORBIDDEN", "[BM]", (new AspectList()).add(Aspect.LIFE, 5).add(Aspect.WATER, 4).add(Aspect.WEAPON, 3), -5, -3, 3, new ItemStack(ForbiddenItems.wandCore, 1, 3))).setPages(new ResearchPage[] { new ResearchPage("forbidden.research_page.ROD_blood.1"), new ResearchPage(blood_recipe), new ResearchPage("forbidden.research_page.ROD_blood.2") }).setParents(new String[] { "ROD_silverwood", "INFAUXSION" }).setAspectTriggers(new Aspect[] { Aspect.LIFE }).setConcealed().registerResearchItem();

			InfusionRecipe alchemical_recipe = ThaumcraftApi.addInfusionCraftingRecipe("CAP_alchemical", new ItemStack(ForbiddenItems.wandCap, 1, 0), 3, (new AspectList()).add(Aspect.LIFE, 12).add(Aspect.WATER, 6), ItemApi.getItem("itemWandCap", 1), new ItemStack[] { new ItemStack(magicales), new ItemStack(magicales), new ItemStack(magicales) });
			(new DarkResearchItem("CAP_alchemical", "FORBIDDEN", "[BM]", (new AspectList()).add(Aspect.LIFE, 4).add(Aspect.TOOL, 1).add(Aspect.WATER, 3), -5, -5, 2, new ItemStack(ForbiddenItems.wandCap, 1, 0))).setPages(new ResearchPage[] { new ResearchPage("forbidden.research_page.CAP_alchemical.1"), new ResearchPage(alchemical_recipe) }).setParents(new String[] { "ROD_blood", "CAP_gold" }).setSecondary().setConcealed().registerResearchItem();

			IArcaneRecipe bloodwell_recipe = ThaumcraftApi.addShapelessArcaneCraftingRecipe("BLOODWELL", new ItemStack(ForbiddenItems.bloodwell, 1, 0), (new AspectList()).add(Aspect.WATER, 10).add(Aspect.EARTH, 10), new Object[] { new ItemStack(Items.feather, 1, 0), new ItemStack(bloodBucket, 1, 0), new ItemStack(Items.glass_bottle, 1, 0), new ItemStack(crapOrb, 1, 0) });
			(new DarkResearchItem("BLOODWELL", "FORBIDDEN", "[BM]", (new AspectList()).add(Aspect.LIFE, 5).add(Aspect.MIND, 4).add(Aspect.SENSES, 3), -10, 4, 1, new ItemStack(ForbiddenItems.bloodwell, 1, 0))).setPages(new ResearchPage[] { new ResearchPage("forbidden.research_page.BLOODWELL.1"), new ResearchPage(bloodwell_recipe) }).setParents(new String[] { "RESEARCH" }).setAspectTriggers(new Aspect[] { Aspect.LIFE }).setConcealed().registerResearchItem();

			IArcaneRecipe blood_staff = ThaumcraftApi.addArcaneCraftingRecipe("ROD_blood_staff", new ItemStack(ForbiddenItems.wandCore, 1, 9), (new AspectList()).add(Aspect.ENTROPY, 26).add(Aspect.FIRE, 26).add(Aspect.WATER, 26).add(Aspect.AIR, 26).add(Aspect.EARTH, 26).add(Aspect.ORDER, 26), new Object[] { "__D", "_B_", "B__", Character.valueOf('B'), new ItemStack(ForbiddenItems.wandCore, 1, 3), Character.valueOf('D'), new ItemStack(demonShard) });
			(new DarkResearchItem("ROD_blood_staff", "FORBIDDEN", "[BM]", (new AspectList()).add(Aspect.LIFE, 8).add(Aspect.WATER, 7).add(Aspect.WEAPON, 6), -5, -7, 5, new ItemStack(ForbiddenItems.wandCore, 1, 9))).setPages(new ResearchPage[] { new ResearchPage("forbidden.research_page.ROD_blood_staff.1"), new ResearchPage(blood_staff) }).setParents(new String[] { "ROD_silverwood_staff", "ROD_blood" }).setSpecial().setConcealed().registerResearchItem();

			if (kami) {
				try {

					Item kamiResource = (Item) (Class.forName("vazkii.tinkerer.common.item.ModItems").getField("kamiResource").get(null));
					InfusionRecipe divine_orb = ThaumcraftApi.addInfusionCraftingRecipe("DIVINEORB", new ItemStack(ForbiddenItems.bloodOrb, 1, 0), 12, (new AspectList()).add(Aspect.LIFE, 64).add(Aspect.MAGIC, 48).add(Aspect.WATER, 32).add(Aspect.VOID, 64), new ItemStack(archmasterOrb), new ItemStack[] { new ItemStack(kamiResource, 1, 0), new ItemStack(kamiResource, 1, 0), new ItemStack(kamiResource, 1, 0), new ItemStack(kamiResource, 1, 2), new ItemStack(kamiResource, 1, 2), new ItemStack(kamiResource, 1, 1), new ItemStack(kamiResource, 1, 6), new ItemStack(kamiResource, 1, 7), new ItemStack(ForbiddenItems.gluttonyShard) });
					(new DarkResearchItem("DIVINEORB", "FORBIDDEN", "[BM] [TTKami]", (new AspectList()).add(Aspect.LIFE, 32).add(Aspect.VOID, 24).add(Aspect.CRYSTAL, 24).add(Aspect.MAGIC, 12), -9, 6, 6, new ItemStack(ForbiddenItems.bloodOrb, 1, 0))).setPages(new ResearchPage[] { new ResearchPage("forbidden.research_page.DIVINEORB.1"), new ResearchPage(divine_orb) }).setParents(new String[] { "ICHOR", "INFAUXSION", "ICHOR_CLOTH", "ICHORIUM" }).setAspectTriggers(new Aspect[] { Aspect.LIFE }).setConcealed().setSpecial().registerResearchItem();

				} catch (Exception e) {
					FMLLog.log(Level.INFO, e, "Forbidden Magic doesn't have Thaumic Tinkerer's nose.");
					e.printStackTrace();
				}

			}

			list = (new AspectList()).add(Aspect.LIFE, 2).add(Aspect.MAGIC, 2).add(Aspect.CRYSTAL, 4);
			ThaumcraftApi.registerObjectTag(new ItemStack(crapOrb, 1, OreDictionary.WILDCARD_VALUE), list);

			list = (new AspectList()).add(Aspect.LIFE, 4).add(Aspect.MAGIC, 4).add(Aspect.CRYSTAL, 4);
			aspectBloodItem("apprenticeBloodOrb", -1, list);

			list = (new AspectList()).add(Aspect.LIFE, 6).add(Aspect.MAGIC, 6).add(Aspect.CRYSTAL, 4);
			aspectBloodItem("magicianBloodOrb", -1, list);

			list = (new AspectList()).add(Aspect.LIFE, 16).add(Aspect.MAGIC, 8).add(Aspect.CRYSTAL, 4);
			ThaumcraftApi.registerObjectTag(new ItemStack(masterOrb, 1, OreDictionary.WILDCARD_VALUE), list);

			list = (new AspectList()).add(Aspect.LIFE, 16).add(DarkAspects.NETHER, 8).add(Aspect.CRYSTAL, 4).add(Aspect.FIRE, 1).add(Aspect.WATER, 1).add(Aspect.AIR, 1).add(Aspect.EARTH, 1).add(Aspect.LIGHT, 1).add(Aspect.DARKNESS, 1);
			ThaumcraftApi.registerObjectTag(new ItemStack(demonShard, 1, OreDictionary.WILDCARD_VALUE), list);

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
			ThaumcraftApi.registerObjectTag(new ItemStack(bloodBucket, 1, OreDictionary.WILDCARD_VALUE), list);

			list = (new AspectList()).add(Aspect.LIGHT, 6).add(Aspect.ENERGY, 3);
			ThaumcraftApi.registerObjectTag(new ItemStack(sanctus, 1, OreDictionary.WILDCARD_VALUE), list);

			list = (new AspectList()).add(Aspect.DARKNESS, 6).add(Aspect.ENERGY, 3);
			ThaumcraftApi.registerObjectTag(new ItemStack(tennebrae, 1, OreDictionary.WILDCARD_VALUE), list);

			list = (new AspectList()).add(Aspect.MAGIC, 6).add(Aspect.ENERGY, 3);
			ThaumcraftApi.registerObjectTag(new ItemStack(magicales, 1, OreDictionary.WILDCARD_VALUE), list);

			list = (new AspectList()).add(Aspect.AIR, 6).add(Aspect.ENERGY, 3);
			ThaumcraftApi.registerObjectTag(new ItemStack(aether, 1, OreDictionary.WILDCARD_VALUE), list);

			list = (new AspectList()).add(Aspect.EARTH, 6).add(Aspect.ENERGY, 3);
			ThaumcraftApi.registerObjectTag(new ItemStack(terrae, 1, OreDictionary.WILDCARD_VALUE), list);

			list = (new AspectList()).add(Aspect.WATER, 6).add(Aspect.ENERGY, 3);
			ThaumcraftApi.registerObjectTag(new ItemStack(aquasalus, 1, OreDictionary.WILDCARD_VALUE), list);

			list = (new AspectList()).add(Aspect.FIRE, 6).add(Aspect.ENERGY, 3);
			ThaumcraftApi.registerObjectTag(new ItemStack(incendium, 1, OreDictionary.WILDCARD_VALUE), list);

			list = (new AspectList()).add(Aspect.COLD, 6).add(Aspect.ENERGY, 3);
			aspectBloodItem("crystallos", -1, list);

			list = (new AspectList()).add(Aspect.ENTROPY, 6).add(Aspect.ENERGY, 3);
			aspectBloodItem("crepitous", -1, list);
		} catch (Throwable e) {
			LogHandler.log(Level.INFO, e, "Forbidden Magic tried to do some Blood Magic, but bled out.");
			bm = false;
		}
	}

	private static void aspectBloodItem(String target, int damage, AspectList list) {
		try {
			Item item = GameRegistry.findItem("AWWayofTime", target);
			ThaumcraftApi.registerObjectTag(new ItemStack(item, 1, damage), list);
		} catch (Exception e) {
			FMLLog.log(Level.INFO, e, "Forbidden Magic was unable to add aspects to " + target);
		}
	}
}
