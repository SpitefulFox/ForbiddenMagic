package fox.spiteful.forbidden.compat;

import fox.spiteful.forbidden.DarkAspects;
import fox.spiteful.forbidden.LogHandler;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.oredict.OreDictionary;

import org.apache.logging.log4j.Level;

import thaumcraft.api.ItemApi;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.IArcaneRecipe;
import thaumcraft.api.crafting.InfusionEnchantmentRecipe;
import thaumcraft.api.crafting.InfusionRecipe;
import thaumcraft.api.research.ResearchPage;
import vazkii.botania.api.BotaniaAPI;

import fox.spiteful.forbidden.DarkResearchItem;
import fox.spiteful.forbidden.enchantments.DarkEnchantments;
import fox.spiteful.forbidden.items.ForbiddenItems;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;

public class Compat {
	public static boolean tt = false;
	public static boolean kami = false;
	public static boolean tx = false;
	public static boolean natura = false;
	public static boolean pb = false;
	public static boolean bm = false;
	public static boolean am2 = false;
	public static boolean botan = false;
    public static boolean totes = false;

    public static void initiate() {
		if (Loader.isModLoaded("ThaumicTinkerer"))
			tt = true;
		if (tt && Loader.isModLoaded("ThaumicTinkererKami"))
			kami = true;
		if (Loader.isModLoaded("ThaumicExploration"))
			tx = true;
		if (Loader.isModLoaded("Natura"))
			natura = true;
		if (Loader.isModLoaded("PlayerBeacons"))
			pb = true;
		if (Loader.isModLoaded("AWWayofTime"))
			bm = true;
		if (Loader.isModLoaded("arsmagica2"))
			am2 = true;
		if (Loader.isModLoaded("Botania"))
			botan = true;
	}

	public static void compatify() {
		ItemStack i;
		AspectList list;

		if (tt) {
			if (!kami) {
				try {
					kami = Class.forName("thaumic.tinkerer.common.core.handler.ConfigHandler").getField("enableKami").getBoolean(null);
				} catch (Exception e) {
				}
			}

			if (kami) {
				try {
					Item kamiResource = GameRegistry.findItem("ThaumicTinkerer", "kamiResource");
					list = new AspectList().add(DarkAspects.NETHER, 2).add(Aspect.MAGIC, 1).add(Aspect.CRYSTAL, 1);
					ThaumcraftApi.registerObjectTag(new ItemStack(kamiResource, 1, 6), list);
					list = new AspectList().add(Aspect.ELDRITCH, 2).add(Aspect.MAGIC, 1).add(Aspect.CRYSTAL, 1);
					ThaumcraftApi.registerObjectTag(new ItemStack(kamiResource, 1, 7), list);

					InfusionEnchantmentRecipe eternal = ThaumcraftApi.addInfusionEnchantmentRecipe("ETERNAL", DarkEnchantments.eternal, 12, (new AspectList()).add(Aspect.CRAFT, 32).add(Aspect.TOOL, 64).add(DarkAspects.ENVY, 76).add(Aspect.MAGIC, 64), new ItemStack[] { new ItemStack(kamiResource, 1, 0), new ItemStack(kamiResource, 1, 2), new ItemStack(kamiResource, 1, 2), new ItemStack(kamiResource, 1, 2), new ItemStack(kamiResource, 1, 6), new ItemStack(ForbiddenItems.deadlyShards, 1, 1), new ItemStack(ForbiddenItems.deadlyShards, 1, 1), new ItemStack(ForbiddenItems.deadlyShards, 1, 1) });
					(new DarkResearchItem("ETERNAL", "FORBIDDEN", "[TTKami]", (new AspectList()).add(Aspect.MAGIC, 16).add(Aspect.TOOL, 10).add(Aspect.CRAFT, 8).add(DarkAspects.ENVY, 32), -5, 7, 6, new ResourceLocation("forbidden", "textures/misc/eternal.png"))).setPages(new ResearchPage[] { new ResearchPage("forbidden.research_page.ETERNAL.1"), new ResearchPage(eternal) }).setParents(new String[] { "MORPHTOOLS", "ICHOR_TOOLS" }).setConcealed().registerResearchItem();
                    ThaumcraftApi.addWarpToResearch("ETERNAL", 5);

					InfusionRecipe thoth = ThaumcraftApi.addInfusionCraftingRecipe("DIVINEWELL", new ItemStack(ForbiddenItems.divinewell, 1, 0), 5, (new AspectList()).add(Aspect.MIND, 32).add(Aspect.BEAST, 12).add(Aspect.EARTH, 16), ItemApi.getItem("itemInkwell", 0), new ItemStack[] { new ItemStack(kamiResource, 1, 2), new ItemStack(kamiResource, 1, 0), new ItemStack(kamiResource, 1, 0), new ItemStack(Blocks.bookshelf), new ItemStack(Blocks.bookshelf), new ItemStack(Blocks.bookshelf), ItemApi.getItem("itemResource", 5), ItemApi.getItem("itemResource", 5), ItemApi.getItem("itemResource", 5) });
					(new DarkResearchItem("DIVINEWELL", "FORBIDDEN", "[TTKami]", (new AspectList()).add(Aspect.MIND, 6).add(Aspect.EARTH, 3).add(Aspect.MAGIC, 5), -11, 4, 3, new ItemStack(ForbiddenItems.divinewell, 1, 0))).setPages(new ResearchPage[] { new ResearchPage("forbidden.research_page.DIVINEWELL.1"), new ResearchPage(thoth) }).setParents(new String[] { "ICHORIUM" }).setConcealed().registerResearchItem();
				} catch (Exception e) {
					LogHandler.log(Level.INFO, e, "We don't have Thaumic Tinkerer's nose.");
				}

			}
		}

		if (natura) {
			try {
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
				if (i != null) {
					list = (new AspectList()).add(Aspect.TREE, 3).add(DarkAspects.WRATH, 2);
					ThaumcraftApi.registerObjectTag(new ItemStack(i.getItem(), 1, 1), list);
					list = (new AspectList()).add(Aspect.TREE, 3).add(Aspect.DARKNESS, 2);
					ThaumcraftApi.registerObjectTag(new ItemStack(i.getItem(), 1, 0), list);
				}

				i = GameRegistry.findItemStack("Natura", "soil.tainted", 1);
				if (i != null) {
					//list = ThaumcraftApiHelper.getObjectAspects(new ItemStack(i.getItem(), 1, 0));
                    list = new AspectList().add(Aspect.EARTH, 1);
					list.add(DarkAspects.NETHER, 1);
					ThaumcraftApi.registerObjectTag(new ItemStack(i.getItem(), 1, OreDictionary.WILDCARD_VALUE), list);
				}

				i = GameRegistry.findItemStack("Natura", "heatsand", 1);
				if (i != null) {
					//list = ThaumcraftApiHelper.getObjectAspects(new ItemStack(i.getItem(), 1, 0));
                    list = new AspectList().add(Aspect.FIRE, 1).add(Aspect.EARTH, 1);
					list.add(DarkAspects.NETHER, 1);
					ThaumcraftApi.registerObjectTag(new ItemStack(i.getItem(), 1, OreDictionary.WILDCARD_VALUE), list);
				}

				i = GameRegistry.findItemStack("Natura", "rawImphide", 1);
				if (i != null) {
					list = (new AspectList()).add(Aspect.FLESH, 4).add(Aspect.HUNGER, 1).add(DarkAspects.NETHER, 1);
					ThaumcraftApi.registerObjectTag(new ItemStack(i.getItem(), 1, 0), list);
					list = (new AspectList()).add(Aspect.FLESH, 4).add(Aspect.POISON, 2).add(Aspect.FIRE, 2).add(DarkAspects.NETHER, 1);
					ThaumcraftApi.registerObjectTag(new ItemStack(i.getItem(), 1, 1), list);
				}
			} catch (Exception e) {
				LogHandler.log(Level.INFO, e, "Forbidden Magic had an allergic reaction to Natura.");
			}
		}

		// TODO: Readd Player Beacons Support
		/*
		 * if (pb) { try { if (PlayerBeacons.config.enableThaumcraft) {
		 * InfusionRecipe soul_recipe =
		 * ThaumcraftApi.addInfusionCraftingRecipe("ROD_soul", new
		 * ItemStack(ForbiddenItems.wandCore, 1, 2), 5, (new
		 * AspectList()).add(Aspect.ELDRITCH, 32).add(Aspect.MAGIC,
		 * 12).add(Aspect.SOUL, 16), new
		 * ItemStack(PlayerBeacons.defiledSoulPylonBlock), new ItemStack[] {
		 * ItemApi.getItem("itemResource", 14), new
		 * ItemStack(PlayerBeacons.defiledSoulConductorBlock), new
		 * ItemStack(PlayerBeacons.defiledSoulConductorBlock), new
		 * ItemStack(PlayerBeacons.defiledSoulConductorBlock), new
		 * ItemStack(PlayerBeacons.defiledSoulConductorBlock), new
		 * ItemStack(Items.ender_eye), new ItemStack(Items.ender_eye) }); (new
		 * DarkResearchItem("ROD_soul", "FORBIDDEN", "[PB]", (new
		 * AspectList()).add(Aspect.ELDRITCH, 4).add(Aspect.SOUL,
		 * 3).add(Aspect.TOOL, 2), -3, -3, 5, new
		 * ItemStack(ForbiddenItems.wandCore, 1, 2))).setPages(new
		 * ResearchPage[] { new
		 * ResearchPage("forbidden.research_page.ROD_soul.1"), new
		 * ResearchPage(soul_recipe) }).setParents(new String[] {
		 * "ROD_silverwood", "PB_CRYSTAL", "INFAUXSION"
		 * }).setConcealed().registerResearchItem();
		 * 
		 * IArcaneRecipe soul_cap =
		 * ThaumcraftApi.addArcaneCraftingRecipe("CAP_soul", new
		 * ItemStack(ForbiddenItems.wandCap, 1, 2), (new
		 * AspectList()).add(Aspect.ENTROPY, 20).add(Aspect.FIRE,
		 * 5).add(Aspect.WATER, 5).add(Aspect.AIR, 5).add(Aspect.EARTH, 5), new
		 * Object[] { "NXN", "N N", Character.valueOf('N'), Items.ender_pearl,
		 * Character.valueOf('X'), PlayerBeacons.crystalItem }); (new
		 * DarkResearchItem("CAP_soul", "FORBIDDEN", "[PB]", (new
		 * AspectList()).add(Aspect.ELDRITCH, 3).add(Aspect.MAGIC,
		 * 2).add(Aspect.ENTROPY, 4), -3, -5, 3, new
		 * ItemStack(ForbiddenItems.wandCap, 1, 2))).setPages(new ResearchPage[]
		 * { new ResearchPage("forbidden.research_page.CAP_soul.1"), new
		 * ResearchPage(soul_cap) }).setParents(new String[] { "ROD_soul"
		 * }).setSecondary().setConcealed().registerResearchItem(); }
		 * 
		 * } catch (Throwable e) { LogHandler.log(Level.INFO, e,
		 * "Player Beacons showed Forbidden Magic who the Alpha Fox is."); pb =
		 * false; } }
		 */

		if (bm) {
			BloodMagic.stab();
		}

		// TODO: Readd ArsMagica2 Support when it's updated
		/*
		 * if (am2) { try { Class amItems =
		 * Class.forName("am2.items.ItemsCommonProxy"); Class amBlocks =
		 * Class.forName("am2.blocks.BlocksCommonProxy"); Item essence = (Item)
		 * (amItems.getField("essence").get(null)); Item amOre = (Item)
		 * (amItems.getField("itemOre").get(null)); Block witchwood = (Block)
		 * (amBlocks.getField("witchwoodLog").get(null));
		 * 
		 * InfusionRecipe witchwood_recipe =
		 * ThaumcraftApi.addInfusionCraftingRecipe("ROD_witchwood", new
		 * ItemStack(ForbiddenItems.wandCore, 1, 4), 5, (new
		 * AspectList()).add(Aspect.MAGIC, 16).add(Aspect.EARTH,
		 * 9).add(Aspect.WATER, 9).add(Aspect.FIRE, 9).add(Aspect.AIR, 9), new
		 * ItemStack(witchwood), new ItemStack[] { new ItemStack(essence, 1, 0),
		 * new ItemStack(amOre, 1, 3), new ItemStack(essence, 1, 1), new
		 * ItemStack(essence, 1, 2), new ItemStack(essence, 1, 3), new
		 * ItemStack(essence, 1, 4), new ItemStack(amOre, 1, 6), new
		 * ItemStack(amOre, 1, 7), new ItemStack(amOre, 1, 2) }); (new
		 * DarkResearchItem("ROD_witchwood", "FORBIDDEN", "[AM2]", (new
		 * AspectList()).add(Aspect.MAGIC, 6).add(Aspect.TREE,
		 * 5).add(Aspect.MIND, 4), -4, -3, 3, new
		 * ItemStack(ForbiddenItems.wandCore, 1, 4))).setPages(new
		 * ResearchPage[] { new
		 * ResearchPage("forbidden.research_page.ROD_witchwood.1"), new
		 * ResearchPage(witchwood_recipe) }).setParents(new String[] {
		 * "ROD_silverwood", "INFAUXSION"
		 * }).setConcealed().registerResearchItem();
		 * 
		 * InfusionRecipe vinteum_recipe =
		 * ThaumcraftApi.addInfusionCraftingRecipe("CAP_vinteum", new
		 * ItemStack(ForbiddenItems.wandCap, 1, 1), 5, (new
		 * AspectList()).add(Aspect.ENERGY, 12).add(Aspect.MAGIC, 6),
		 * ItemApi.getItem("itemWandCap", 6), new ItemStack[] { new
		 * ItemStack(amOre, 1, 0), new ItemStack(amOre, 1, 0), new
		 * ItemStack(amOre, 1, 0) }); (new DarkResearchItem("CAP_vinteum",
		 * "FORBIDDEN", "[AM2]", (new AspectList()).add(Aspect.MAGIC,
		 * 4).add(Aspect.TOOL, 1).add(Aspect.ENERGY, 2), -4, -5, 1, new
		 * ItemStack(ForbiddenItems.wandCap, 1, 1))).setPages(new ResearchPage[]
		 * { new ResearchPage("forbidden.research_page.CAP_vinteum.1"), new
		 * ResearchPage(vinteum_recipe) }).setParents(new String[] {
		 * "ROD_witchwood", "CAP_thaumium"
		 * }).setSecondary().setConcealed().registerResearchItem();
		 * 
		 * IArcaneRecipe witchwood_staff =
		 * ThaumcraftApi.addArcaneCraftingRecipe("ROD_witchwood_staff", new
		 * ItemStack(ForbiddenItems.wandCore, 1, 10), (new
		 * AspectList()).add(Aspect.ENTROPY, 26).add(Aspect.FIRE,
		 * 26).add(Aspect.WATER, 26).add(Aspect.AIR, 26).add(Aspect.EARTH,
		 * 26).add(Aspect.ORDER, 26), new Object[] { "__D", "_B_", "B__",
		 * Character.valueOf('B'), new ItemStack(ForbiddenItems.wandCore, 1, 4),
		 * Character.valueOf('D'), new ItemStack(essence, 1, 10) }); (new
		 * DarkResearchItem("ROD_witchwood_staff", "FORBIDDEN", "[AM2]", (new
		 * AspectList()).add(Aspect.MAGIC, 8).add(Aspect.MIND,
		 * 7).add(Aspect.TOOL, 6), -4, -7, 5, new
		 * ItemStack(ForbiddenItems.wandCore, 1, 10))).setPages(new
		 * ResearchPage[] { new
		 * ResearchPage("forbidden.research_page.ROD_witchwood_staff.1"), new
		 * ResearchPage(witchwood_staff) }).setParents(new String[] {
		 * "ROD_silverwood_staff", "ROD_witchwood"
		 * }).setSpecial().setConcealed().registerResearchItem(); } catch
		 * (Exception e) { LogHandler.log(Level.INFO, e,
		 * "Forbidden Magic was slain by a Hecate."); am2 = false; } }
		 */

		if (botan) {
			try {
				Item rune = GameRegistry.findItem("Botania", "rune");
				Item resource = GameRegistry.findItem("Botania", "manaResource");
				Item livingLog = GameRegistry.findItem("Botania", "livingwood");

				CraftingManager.getInstance().addRecipe(new ItemStack(resource, 1, 0), new Object[] { "###", "###", "###", Character.valueOf('#'), new ItemStack(ForbiddenItems.resource, 1, 2) });
				CraftingManager.getInstance().addRecipe(new ItemStack(ForbiddenItems.resource, 9, 2), new Object[] { "#", Character.valueOf('#'), new ItemStack(resource, 1, 0) });

				InfusionRecipe livingwood_rod = ThaumcraftApi.addInfusionCraftingRecipe("ROD_livingwood", new ItemStack(ForbiddenItems.wandCore, 1, 8), 3, (new AspectList()).add(Aspect.MAGIC, 16).add(Aspect.TREE, 32).add(Aspect.LIFE, 16).add(Aspect.SENSES, 8).add(Aspect.PLANT, 8), new ItemStack(livingLog), new ItemStack[] { new ItemStack(resource, 1, 2), new ItemStack(rune, 1, 8), new ItemStack(rune, 1, 0), new ItemStack(rune, 1, 1), new ItemStack(rune, 1, 2), new ItemStack(rune, 1, 3), new ItemStack(rune, 1, 4), new ItemStack(rune, 1, 5), new ItemStack(rune, 1, 6), new ItemStack(rune, 1, 7) });
				(new DarkResearchItem("ROD_livingwood", "FORBIDDEN", "[B]", (new AspectList()).add(Aspect.TREE, 8).add(Aspect.MAGIC, 4).add(Aspect.PLANT, 6), -1, -3, 3, new ItemStack(ForbiddenItems.wandCore, 1, 7))).setPages(new ResearchPage[] { new ResearchPage("forbidden.research_page.ROD_livingwood.1"), new ResearchPage(livingwood_rod) }).setParents(new String[] { "ROD_silverwood", "INFAUXSION" }).setConcealed().registerResearchItem();
                ThaumcraftApi.addWarpToResearch("ROD_livingwood", 3);
				BotaniaAPI.registerManaInfusionRecipe(new ItemStack(ForbiddenItems.wandCore, 1, 7), new ItemStack(ForbiddenItems.wandCore, 1, 8), 10000);

				IArcaneRecipe manasteel_cap = ThaumcraftApi.addArcaneCraftingRecipe("CAP_manasteel", new ItemStack(ForbiddenItems.wandCap, 1, 4), (new AspectList()).add(Aspect.ENTROPY, 6).add(Aspect.FIRE, 6).add(Aspect.WATER, 6).add(Aspect.AIR, 6).add(Aspect.EARTH, 6).add(Aspect.ORDER, 6), new Object[] { "NNN", "N N", Character.valueOf('N'), new ItemStack(ForbiddenItems.resource, 1, 2) });
				(new DarkResearchItem("CAP_manasteel", "FORBIDDEN", "[B]", (new AspectList()).add(Aspect.ELDRITCH, 3).add(Aspect.MAGIC, 2).add(Aspect.ENTROPY, 4), -1, -5, 2, new ItemStack(ForbiddenItems.wandCap, 1, 3))).setPages(new ResearchPage[] { new ResearchPage("forbidden.research_page.CAP_manasteel.1"), new ResearchPage(manasteel_cap) }).setParents(new String[] { "ROD_livingwood" }).setSecondary().setConcealed().registerResearchItem();
				BotaniaAPI.registerManaInfusionRecipe(new ItemStack(ForbiddenItems.wandCap, 1, 3), new ItemStack(ForbiddenItems.wandCap, 1, 4), 1000);
			} catch (Throwable e) {
				LogHandler.log(Level.INFO, e, "Forbidden Magic: Botania? Do you wanna build a snowman?");
				botan = false;
			}
		}
	}
}