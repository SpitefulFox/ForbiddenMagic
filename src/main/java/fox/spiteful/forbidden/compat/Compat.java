package fox.spiteful.forbidden.compat;

import fox.spiteful.forbidden.*;
import fox.spiteful.forbidden.tiles.SubTileEuclidaisy;
import fox.spiteful.forbidden.tiles.SubTileWhisperweed;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.oredict.OreDictionary;

import org.apache.logging.log4j.Level;

import thaumcraft.api.ItemApi;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.IArcaneRecipe;
import thaumcraft.api.crafting.InfusionEnchantmentRecipe;
import thaumcraft.api.crafting.InfusionRecipe;
import thaumcraft.api.research.ResearchPage;
import thaumcraft.common.config.ConfigItems;
import vazkii.botania.api.BotaniaAPI;

import fox.spiteful.forbidden.enchantments.DarkEnchantments;
import fox.spiteful.forbidden.items.ForbiddenItems;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
import vazkii.botania.api.lexicon.LexiconCategory;
import vazkii.botania.api.lexicon.LexiconPage;
import vazkii.botania.api.recipe.RecipePetals;

import java.lang.reflect.Constructor;
import java.util.List;

public class Compat {
	public static boolean tt = false;
	public static boolean kami = false;
	public static boolean natura = false;
	public static boolean pb = false;
	public static boolean bm = false;
	public static boolean am2 = false;
	public static boolean botan = false;
    public static boolean tc = false;

    public static void initiate() {
		if(!Config.crossMod)
            return;
        if (Config.tt && Loader.isModLoaded("ThaumicTinkerer"))
			tt = true;
		if (tt && Loader.isModLoaded("ThaumicTinkererKami"))
			kami = true;
		if (Loader.isModLoaded("Natura"))
			natura = true;
		if (Loader.isModLoaded("PlayerBeacons"))
			pb = true;
		if (Config.bloodMagic && Loader.isModLoaded("AWWayofTime"))
			bm = true;
		if (Config.am2 && Loader.isModLoaded("arsmagica2"))
			am2 = true;
		if (Config.botan && Loader.isModLoaded("Botania"))
			botan = true;
        if (Config.tc && Loader.isModLoaded("TConstruct"))
            tc = true;
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

				} catch (Exception e) {
					LogHandler.log(Level.INFO, e, "Forbidden Magic doesn't have Thaumic Tinkerer's nose.");
				}

			}
		}

		if(tc){
            try {
                Item hammerHead = GameRegistry.findItem("TConstruct", "hammerHead");
                InfusionEnchantmentRecipe impact = ThaumcraftApi.addInfusionEnchantmentRecipe("IMPACT", DarkEnchantments.impact, 4, (new AspectList()).add(Aspect.ENTROPY, 16).add(Aspect.MINE, 16), new ItemStack[] { ItemApi.getItem("itemResource", 14), new ItemStack(ForbiddenItems.deadlyShards, 1, 1), new ItemStack(ForbiddenItems.deadlyShards, 1, 1), new ItemStack(Blocks.tnt, 1), new ItemStack(hammerHead, 1, 1), new ItemStack(hammerHead, 1, 1) });
                (new DarkResearchItem("IMPACT", "FORBIDDEN", (new AspectList()).add(Aspect.ENTROPY, 8).add(Aspect.TOOL, 10).add(Aspect.MINE, 16).add(DarkAspects.ENVY, 10), -6, 7, 4, new ResourceLocation("forbidden", "textures/misc/impact.png"))).setPages(new ResearchPage[] { new ResearchPage("forbidden.research_page.IMPACT.1"), new ResearchPage(impact) }).setParents(new String[] { "MORPHTOOLS" }).setConcealed().registerResearchItem();
            } catch (Exception e) {
                LogHandler.log(Level.INFO, e, "Forbidden Magic fell into Tinkers Construct's smeltery.");
                tc = false;
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

        if (Config.crossWand && am2) {
            try {
                Class amItems = Class.forName("am2.items.ItemsCommonProxy");
                Class amBlocks = Class.forName("am2.blocks.BlocksCommonProxy");
                Item essence = (Item)(amItems.getField("essence").get(null));
                Item amOre = (Item)(amItems.getField("itemOre").get(null));
                Block witchwood = (Block)(amBlocks.getField("witchwoodLog").get(null));

                InfusionRecipe witchwood_recipe = ThaumcraftApi.addInfusionCraftingRecipe("ROD_witchwood", new ItemStack(ForbiddenItems.wandCore, 1, 4), 5, (new AspectList()).add(Aspect.MAGIC, 16).add(Aspect.EARTH, 9).add(Aspect.WATER, 9).add(Aspect.FIRE, 9).add(Aspect.AIR, 9), new ItemStack(witchwood), new ItemStack[]{new ItemStack(essence, 1, 0), new ItemStack(amOre, 1, 3), new ItemStack(essence, 1, 1), new ItemStack(essence, 1, 2), new ItemStack(essence, 1, 3), new ItemStack(essence, 1, 4), new ItemStack(amOre, 1, 6), new ItemStack(amOre, 1, 7), new ItemStack(amOre, 1, 2)});
                (new DarkResearchItem("ROD_witchwood", "FORBIDDEN", "[AM2]", (new AspectList()).add(Aspect.MAGIC, 6).add(Aspect.TREE, 5).add(Aspect.MIND, 4), -4, -3, 3, new ItemStack(ForbiddenItems.wandCore, 1, 4))).setPages(new ResearchPage[]{new ResearchPage("forbidden.research_page.ROD_witchwood.1"), new ResearchPage(witchwood_recipe)}).setParents(new String[]{ "ROD_silverwood", "INFAUXSION" }).setConcealed().registerResearchItem();

                InfusionRecipe vinteum_recipe = ThaumcraftApi.addInfusionCraftingRecipe("CAP_vinteum", new ItemStack(ForbiddenItems.wandCap, 1, 1), 5, (new AspectList()).add(Aspect.ENERGY, 12).add(Aspect.MAGIC, 6), ItemApi.getItem("itemWandCap", 6), new ItemStack[]{new ItemStack(amOre, 1, 0), new ItemStack(amOre, 1, 0), new ItemStack(amOre, 1, 0)});
                (new DarkResearchItem("CAP_vinteum", "FORBIDDEN", "[AM2]", (new AspectList()).add(Aspect.MAGIC, 4).add(Aspect.TOOL, 1).add(Aspect.ENERGY, 2), -4, -5, 1, new ItemStack(ForbiddenItems.wandCap, 1, 1))).setPages(new ResearchPage[]{new ResearchPage("forbidden.research_page.CAP_vinteum.1"), new ResearchPage(vinteum_recipe)}).setParents(new String[]{"ROD_witchwood", "CAP_thaumium"}).setSecondary().setConcealed().registerResearchItem();

                IArcaneRecipe witchwood_staff = ThaumcraftApi.addArcaneCraftingRecipe("ROD_witchwood_staff", new ItemStack(ForbiddenItems.wandCore, 1, 10), (new AspectList()).add(Aspect.ENTROPY, 26).add(Aspect.FIRE, 26).add(Aspect.WATER, 26).add(Aspect.AIR, 26).add(Aspect.EARTH, 26).add(Aspect.ORDER, 26), new Object[]{"__D", "_B_", "B__", Character.valueOf('B'), new ItemStack(ForbiddenItems.wandCore, 1, 4), Character.valueOf('D'), new ItemStack(essence, 1, 10)});
                (new DarkResearchItem("ROD_witchwood_staff", "FORBIDDEN", "[AM2]", (new AspectList()).add(Aspect.MAGIC, 8).add(Aspect.MIND, 7).add(Aspect.TOOL, 6), -4, -7, 5, new ItemStack(ForbiddenItems.wandCore, 1, 10))).setPages(new ResearchPage[]{new ResearchPage("forbidden.research_page.ROD_witchwood_staff.1"), new ResearchPage(witchwood_staff)}).setParents(new String[]{"ROD_silverwood_staff", "ROD_witchwood"}).setSpecial().setConcealed().registerResearchItem();
            } catch (Exception e) {
                LogHandler.log(Level.INFO, e, "Forbidden Magic was slain by a Hecate.");
                am2 = false;
            }
        }

		if (botan) {
            ForbiddenBotany.flowerPowerHippymancy();
		}
	}
}