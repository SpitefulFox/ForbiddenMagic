package fox.spiteful.forbidden.compat;

import com.pahimar.ee3.api.exchange.EnergyValueRegistryProxy;
import fox.spiteful.forbidden.*;
import fox.spiteful.forbidden.blocks.ForbiddenBlocks;
import net.minecraft.block.Block;
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
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.config.ConfigItems;
import fox.spiteful.forbidden.items.ForbiddenItems;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;


public class Compat {
    public static boolean tt = false;
    public static boolean kami = false;
    public static boolean natura = false;
    public static boolean bm = false;
    public static boolean am2 = false;
    public static boolean botan = false;
    public static boolean special = false;
    public static boolean twilight = false;
    public static boolean ee3 = false;

    public static void initiate() {
        if(!Config.crossMod)
            return;
        tt = Config.tt && Loader.isModLoaded("ThaumicTinkerer");
        kami = tt && Loader.isModLoaded("ThaumicTinkererKami");
        natura = Loader.isModLoaded("Natura");
        bm = Config.bloodMagic && Loader.isModLoaded("AWWayofTime");
        am2 = Config.am2 && Loader.isModLoaded("arsmagica2");
        botan = Config.botan && Loader.isModLoaded("Botania");
        special = Config.wrathCage && Config.special && Loader.isModLoaded("SpecialMobs");
        twilight = Config.wrathCage && Config.twilight && Loader.isModLoaded("TwilightForest");
        ee3 = (Config.emc /* || Config.eewand*/) && Loader.isModLoaded("EE3");
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
                    Item kamiResource = getItem("ThaumicTinkerer", "kamiResource");
                    list = new AspectList().add(DarkAspects.NETHER, 2).add(Aspect.MAGIC, 1).add(Aspect.CRYSTAL, 1);
                    ThaumcraftApi.registerObjectTag(new ItemStack(kamiResource, 1, 6), list);
                    list = new AspectList().add(Aspect.ELDRITCH, 2).add(Aspect.MAGIC, 1).add(Aspect.CRYSTAL, 1);
                    ThaumcraftApi.registerObjectTag(new ItemStack(kamiResource, 1, 7), list);

                } catch (Exception e) {
                    LogHandler.log(Level.INFO, e, "Forbidden Magic doesn't have Thaumic Tinkerer's nose.");
                    kami = false;
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

        if (bm) {
            try {
                BloodMagic.stab();
            } catch (Throwable e) {
                LogHandler.log(Level.INFO, e, "Forbidden Magic tried to do some Blood Magic, but bled out.");
                Compat.bm = false;
            }
        }

        if (Config.crossWand && am2) {
            try {
                Class amItems = Class.forName("am2.items.ItemsCommonProxy");
                Class amBlocks = Class.forName("am2.blocks.BlocksCommonProxy");
                Item essence = (Item)(amItems.getField("essence").get(null));
                Item amOre = (Item)(amItems.getField("itemOre").get(null));
                Block witchwood = (Block)(amBlocks.getField("witchwoodLog").get(null));

                InfusionRecipe witchwood_recipe = ThaumcraftApi.addInfusionCraftingRecipe("ROD_witchwood", new ItemStack(ForbiddenItems.wandCore, 1, 4), 5, (new AspectList()).add(Aspect.MAGIC, 16).add(Aspect.EARTH, 9).add(Aspect.WATER, 9).add(Aspect.FIRE, 9).add(Aspect.AIR, 9), new ItemStack(witchwood), new ItemStack[]{new ItemStack(essence, 1, 0), new ItemStack(amOre, 1, 3), new ItemStack(essence, 1, 1), new ItemStack(essence, 1, 2), new ItemStack(essence, 1, 3), new ItemStack(essence, 1, 4), new ItemStack(amOre, 1, 6), new ItemStack(amOre, 1, 7), new ItemStack(amOre, 1, 2)});
                (new DarkResearchItem("ROD_witchwood", "FORBIDDEN", "[AM2]", (new AspectList()).add(Aspect.MAGIC, 6).add(Aspect.TREE, 5).add(Aspect.MIND, 4), -3, 1, 3, new ItemStack(ForbiddenItems.wandCore, 1, 4))).setPages(new ResearchPage[]{new ResearchPage("forbidden.research_page.ROD_witchwood.1"), new ResearchPage(witchwood_recipe)}).setParents(new String[]{ "SCHOOLS", "ROD_silverwood", "INFUSION" }).setConcealed().registerResearchItem();
                ThaumcraftApi.addWarpToResearch("ROD_witchwood", 2);

                InfusionRecipe vinteum_recipe = ThaumcraftApi.addInfusionCraftingRecipe("CAP_vinteum", new ItemStack(ForbiddenItems.wandCap, 1, 1), 5, (new AspectList()).add(Aspect.ENERGY, 12).add(Aspect.MAGIC, 6), ItemApi.getItem("itemWandCap", 6), new ItemStack[]{new ItemStack(amOre, 1, 0), new ItemStack(amOre, 1, 0), new ItemStack(amOre, 1, 0)});
                (new DarkResearchItem("CAP_vinteum", "FORBIDDEN", "[AM2]", (new AspectList()).add(Aspect.MAGIC, 4).add(Aspect.TOOL, 1).add(Aspect.ENERGY, 2), -4, -1, 1, new ItemStack(ForbiddenItems.wandCap, 1, 1))).setPages(new ResearchPage[]{new ResearchPage("forbidden.research_page.CAP_vinteum.1"), new ResearchPage(vinteum_recipe)}).setParents(new String[]{"ROD_witchwood", "CAP_thaumium"}).setSecondary().setConcealed().registerResearchItem();

                IArcaneRecipe witchwood_staff = ThaumcraftApi.addArcaneCraftingRecipe("ROD_witchwood_staff", new ItemStack(ForbiddenItems.wandCore, 1, 10), (new AspectList()).add(Aspect.ENTROPY, 26).add(Aspect.FIRE, 26).add(Aspect.WATER, 26).add(Aspect.AIR, 26).add(Aspect.EARTH, 26).add(Aspect.ORDER, 26), new Object[]{"__D", "_B_", "B__", Character.valueOf('B'), new ItemStack(ForbiddenItems.wandCore, 1, 4), Character.valueOf('D'), new ItemStack(essence, 1, 10)});
                (new DarkResearchItem("ROD_witchwood_staff", "FORBIDDEN", "[AM2]", (new AspectList()).add(Aspect.MAGIC, 8).add(Aspect.MIND, 7).add(Aspect.TOOL, 6), -3, -1, 2, new ItemStack(ForbiddenItems.wandCore, 1, 10))).setPages(new ResearchPage[]{new ResearchPage("forbidden.research_page.ROD_witchwood_staff.1"), new ResearchPage(witchwood_staff)}).setParents(new String[]{"ROD_silverwood_staff", "ROD_witchwood"}).setSpecial().setConcealed().registerResearchItem();
            } catch (Exception e) {
                LogHandler.log(Level.INFO, e, "Forbidden Magic was slain by a Hecate.");
                am2 = false;
            }
        }

        if (botan) {
            try {
                ForbiddenBotany.flowerPowerHippymancy();
            }
            catch (Throwable e){
                LogHandler.log(Level.INFO, e, "Forbidden Magic: Botania? Do you wanna build a snowman?");
                botan = false;
            }
        }

        if(ee3){
            if(Config.emc){
                try {
                    for(int x = 0;x < 6;x++) {
                        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new ItemStack(ConfigItems.itemShard, 1, x), 256);
                    }
                    //EnergyValueRegistryProxy.addPreAssignedEnergyValue(new ItemStack(ConfigItems.itemShard, 1, 6), 354);
                    //EnergyValueRegistryProxy.addPreAssignedEnergyValue(new ItemStack(ConfigItems.itemResource, 1, 0), 83);
                    //EnergyValueRegistryProxy.addPreAssignedEnergyValue(new ItemStack(ConfigItems.itemResource, 1, 1), 459);
                    //EnergyValueRegistryProxy.addPreAssignedEnergyValue(new ItemStack(ConfigItems.itemResource, 1, 2), 272);
                    EnergyValueRegistryProxy.addPreAssignedEnergyValue(new ItemStack(ConfigItems.itemResource, 1, 3), 64);
                    EnergyValueRegistryProxy.addPreAssignedEnergyValue(new ItemStack(ConfigItems.itemResource, 1, 4), 32);
                    EnergyValueRegistryProxy.addPreAssignedEnergyValue(new ItemStack(ConfigItems.itemResource, 1, 6), 32);
                    EnergyValueRegistryProxy.addPreAssignedEnergyValue(new ItemStack(ConfigItems.itemResource, 1, 7), 112);
                    EnergyValueRegistryProxy.addPreAssignedEnergyValue(new ItemStack(ConfigItems.itemResource, 1, 8), 2056);
                    EnergyValueRegistryProxy.addPreAssignedEnergyValue(new ItemStack(ConfigItems.itemResource, 1, 9), 5120);
                    EnergyValueRegistryProxy.addPreAssignedEnergyValue(new ItemStack(ConfigItems.itemResource, 1, 11), 32);
                    EnergyValueRegistryProxy.addPreAssignedEnergyValue(new ItemStack(ConfigItems.itemResource, 1, 12), 32);
                    //EnergyValueRegistryProxy.addPreAssignedEnergyValue(new ItemStack(ConfigItems.itemResource, 1, 14), 354);
                    //EnergyValueRegistryProxy.addPreAssignedEnergyValue(new ItemStack(ConfigItems.itemResource, 1, 15), 5986);
                    EnergyValueRegistryProxy.addPreAssignedEnergyValue(new ItemStack(ConfigItems.itemResource, 1, 18), 227.556F);
                    EnergyValueRegistryProxy.addPreAssignedEnergyValue(ConfigItems.itemZombieBrain, 1024);
                    EnergyValueRegistryProxy.addPreAssignedEnergyValue(ConfigItems.itemWispEssence, 1024);
                    EnergyValueRegistryProxy.addPreAssignedEnergyValue(ConfigItems.itemManaBean, 32);
                    EnergyValueRegistryProxy.addPreAssignedEnergyValue(ConfigItems.itemSwordCrimson, 24580);
                    EnergyValueRegistryProxy.addPreAssignedEnergyValue(ConfigItems.itemHelmetCultistLeaderPlate, 49152);
                    EnergyValueRegistryProxy.addPreAssignedEnergyValue(ConfigItems.itemChestCultistLeaderPlate, 73728);
                    EnergyValueRegistryProxy.addPreAssignedEnergyValue(ConfigItems.itemLegsCultistLeaderPlate, 65536);
                    EnergyValueRegistryProxy.addPreAssignedEnergyValue(ConfigItems.itemBootsCultist, 1280);
                    EnergyValueRegistryProxy.addPreAssignedEnergyValue(ConfigItems.itemChestCultistPlate, 2048);
                    EnergyValueRegistryProxy.addPreAssignedEnergyValue(ConfigItems.itemHelmetCultistPlate, 1280);
                    EnergyValueRegistryProxy.addPreAssignedEnergyValue(ConfigItems.itemLegsCultistPlate, 1792);
                    EnergyValueRegistryProxy.addPreAssignedEnergyValue(ConfigItems.itemChestCultistRobe, 2304);
                    EnergyValueRegistryProxy.addPreAssignedEnergyValue(ConfigItems.itemHelmetCultistRobe, 1536);
                    EnergyValueRegistryProxy.addPreAssignedEnergyValue(ConfigItems.itemLegsCultistRobe, 2048);

                    EnergyValueRegistryProxy.addPreAssignedEnergyValue(new ItemStack(ConfigBlocks.blockCosmeticSolid, 1, 0), 64);
                    EnergyValueRegistryProxy.addPreAssignedEnergyValue(new ItemStack(ConfigBlocks.blockCosmeticSolid, 1, 2), 392);
                    EnergyValueRegistryProxy.addPreAssignedEnergyValue(new ItemStack(ConfigBlocks.blockCosmeticSolid, 1, 3), 392);
                    EnergyValueRegistryProxy.addPreAssignedEnergyValue(new ItemStack(ConfigBlocks.blockCosmeticSolid, 1, 6), 264);
                    EnergyValueRegistryProxy.addPreAssignedEnergyValue(new ItemStack(ConfigBlocks.blockCosmeticSolid, 1, 7), 264);
                    EnergyValueRegistryProxy.addPreAssignedEnergyValue(new ItemStack(ConfigBlocks.blockCosmeticSolid, 1, 11), 1);
                    EnergyValueRegistryProxy.addPreAssignedEnergyValue(new ItemStack(ConfigBlocks.blockCosmeticSolid, 1, 12), 1);
                    EnergyValueRegistryProxy.addPreAssignedEnergyValue(new ItemStack(ConfigBlocks.blockCosmeticSolid, 1, 14), 1);
                    EnergyValueRegistryProxy.addPreAssignedEnergyValue(new ItemStack(ConfigBlocks.blockCosmeticSolid, 1, 15), 1);
                    EnergyValueRegistryProxy.addPreAssignedEnergyValue(new ItemStack(ConfigBlocks.blockCustomPlant, 1, 0), 64);
                    EnergyValueRegistryProxy.addPreAssignedEnergyValue(new ItemStack(ConfigBlocks.blockCustomPlant, 1, 1), 256);
                    EnergyValueRegistryProxy.addPreAssignedEnergyValue(new ItemStack(ConfigBlocks.blockCustomPlant, 1, 2), 64);
                    EnergyValueRegistryProxy.addPreAssignedEnergyValue(new ItemStack(ConfigBlocks.blockCustomPlant, 1, 3), 768);
                    EnergyValueRegistryProxy.addPreAssignedEnergyValue(new ItemStack(ConfigBlocks.blockCustomPlant, 1, 5), 16);
                    EnergyValueRegistryProxy.addPreAssignedEnergyValue(new ItemStack(ConfigBlocks.blockMetalDevice, 1, 0), 1792);
                    EnergyValueRegistryProxy.addPreAssignedEnergyValue(new ItemStack(ConfigBlocks.blockTable, 1, 0), 28);
                    EnergyValueRegistryProxy.addPreAssignedEnergyValue(new ItemStack(ConfigBlocks.blockTable, 1, 15), 28);
                    EnergyValueRegistryProxy.addPreAssignedEnergyValue(new ItemStack(ConfigBlocks.blockEldritch, 1, 4), 4);
                    for(int x = 0;x < 7;x++) {
                        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new ItemStack(ConfigBlocks.blockCrystal, 1, x), 1536);
                    }
                    EnergyValueRegistryProxy.addPreAssignedEnergyValue(new ItemStack(ConfigBlocks.blockMagicalLog, 1, 1), 64);
                    EnergyValueRegistryProxy.addPreAssignedEnergyValue(new ItemStack(ConfigBlocks.blockMagicalLeaves, 1, 0), 1);
                    EnergyValueRegistryProxy.addPreAssignedEnergyValue(new ItemStack(ConfigBlocks.blockMagicalLeaves, 1, 1), 1);

                    EnergyValueRegistryProxy.addPreAssignedEnergyValue(ForbiddenBlocks.starBlock, 221184);

                    for(int x = 0;x < 7;x++) {
                        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new ItemStack(ForbiddenItems.deadlyShards, 1, x), 256);
                    }
                    EnergyValueRegistryProxy.addPreAssignedEnergyValue(new ItemStack(ForbiddenItems.gluttonyShard, 1), 256);
                }
                catch(Throwable e){
                    LogHandler.log(Level.INFO, e, "Forbidden Magic appears to be having trouble with EE3, eh?");
                    ee3 = false;
                }
            }

        }

        if(special){
            Config.spawnerMobs.put("SpecialMobs.SpecialCaveSpider", Aspect.POISON);
            Config.spawnerMobs.put("SpecialMobs.BabyCaveSpider", Aspect.POISON);
            Config.spawnerMobs.put("SpecialMobs.FlyingCaveSpider", Aspect.POISON);
            Config.spawnerMobs.put("SpecialMobs.MotherCaveSpider", Aspect.POISON);
            Config.spawnerMobs.put("SpecialMobs.ToughCaveSpider", Aspect.POISON);
            Config.spawnerMobs.put("SpecialMobs.WebCaveSpider", Aspect.POISON);
            Config.spawnerMobs.put("SpecialMobs.WitchCaveSpider", Aspect.POISON);

            Config.spawnerMobs.put("SpecialMobs.SpecialCreeper", Aspect.FIRE);
            Config.spawnerMobs.put("SpecialMobs.ArmorCreeper", Aspect.FIRE);
            Config.spawnerMobs.put("SpecialMobs.DarkCreeper", Aspect.FIRE);
            Config.spawnerMobs.put("SpecialMobs.DeathCreeper", Aspect.FIRE);
            Config.spawnerMobs.put("SpecialMobs.DirtCreeper", Aspect.FIRE);
            Config.spawnerMobs.put("SpecialMobs.DoomCreeper", Aspect.FIRE);
            Config.spawnerMobs.put("SpecialMobs.DrowningCreeper", Aspect.FIRE);
            Config.spawnerMobs.put("SpecialMobs.EnderCreeper", Aspect.FIRE);
            Config.spawnerMobs.put("SpecialMobs.FireCreeper", Aspect.FIRE);
            Config.spawnerMobs.put("SpecialMobs.JumpingCreeper", Aspect.FIRE);
            Config.spawnerMobs.put("SpecialMobs.LightningCreeper", Aspect.FIRE);
            Config.spawnerMobs.put("SpecialMobs.MiniCreeper", Aspect.FIRE);
            Config.spawnerMobs.put("SpecialMobs.SplittingCreeper", Aspect.FIRE);

            Config.spawnerMobs.put("SpecialMobs.SpecialEnderman", Aspect.ELDRITCH);
            Config.spawnerMobs.put("SpecialMobs.BlindingEnderman", Aspect.ELDRITCH);
            Config.spawnerMobs.put("SpecialMobs.CursedEnderman", Aspect.ELDRITCH);
            Config.spawnerMobs.put("SpecialMobs.IcyEnderman", Aspect.ELDRITCH);
            Config.spawnerMobs.put("SpecialMobs.LightningEnderman", Aspect.ELDRITCH);
            Config.spawnerMobs.put("SpecialMobs.MiniEnderman", Aspect.ELDRITCH);
            Config.spawnerMobs.put("SpecialMobs.MirageEnderman", Aspect.ELDRITCH);
            Config.spawnerMobs.put("SpecialMobs.ThiefEnderman", Aspect.ELDRITCH);

            Config.spawnerMobs.put("SpecialMobs.SpecialGhast", DarkAspects.NETHER);
            Config.spawnerMobs.put("SpecialMobs.BabyGhast", DarkAspects.NETHER);
            Config.spawnerMobs.put("SpecialMobs.FaintGhast", DarkAspects.NETHER);
            Config.spawnerMobs.put("SpecialMobs.FighterGhast", DarkAspects.NETHER);
            Config.spawnerMobs.put("SpecialMobs.KingGhast", DarkAspects.NETHER);
            Config.spawnerMobs.put("SpecialMobs.MiniGhast", DarkAspects.NETHER);
            Config.spawnerMobs.put("SpecialMobs.QueenGhast", DarkAspects.NETHER);
            Config.spawnerMobs.put("SpecialMobs.UnholyGhast", DarkAspects.NETHER);

            Config.spawnerMobs.put("SpecialMobs.SpecialPigZombie", Aspect.GREED);
            Config.spawnerMobs.put("SpecialMobs.BrutishPigZombie", Aspect.GREED);
            Config.spawnerMobs.put("SpecialMobs.FishingPigZombie", Aspect.GREED);
            Config.spawnerMobs.put("SpecialMobs.GiantPigZombie", Aspect.GREED);
            Config.spawnerMobs.put("SpecialMobs.HungryPigZombie", Aspect.GREED);
            Config.spawnerMobs.put("SpecialMobs.PlaguePigZombie", Aspect.GREED);
            Config.spawnerMobs.put("SpecialMobs.VampirePigZombie", Aspect.GREED);

            Aspect silver;
            if(Config.silverfishEmeralds)
                silver = Aspect.GREED;
            else
                silver = Aspect.BEAST;

            Config.spawnerMobs.put("SpecialMobs.SpecialSilverfish", silver);
            Config.spawnerMobs.put("SpecialMobs.BlindingSilverfish", silver);
            Config.spawnerMobs.put("SpecialMobs.FishingSilverfish", silver);
            Config.spawnerMobs.put("SpecialMobs.FlyingSilverfish", silver);
            Config.spawnerMobs.put("SpecialMobs.PoisonSilverfish", silver);
            Config.spawnerMobs.put("SpecialMobs.ToughSilverfish", silver);

            Config.spawnerMobs.put("SpecialMobs.SpecialSkeleton", Aspect.DEATH);
            Config.spawnerMobs.put("SpecialMobs.BrutishSkeleton", Aspect.DEATH);
            Config.spawnerMobs.put("SpecialMobs.FireSkeleton", Aspect.DEATH);
            Config.spawnerMobs.put("SpecialMobs.GatlingSkeleton", Aspect.DEATH);
            Config.spawnerMobs.put("SpecialMobs.GiantSkeleton", Aspect.DEATH);
            Config.spawnerMobs.put("SpecialMobs.PoisonSkeleton", Aspect.DEATH);
            Config.spawnerMobs.put("SpecialMobs.SniperSkeleton", Aspect.DEATH);
            Config.spawnerMobs.put("SpecialMobs.SpitfireSkeleton", Aspect.DEATH);
            Config.spawnerMobs.put("SpecialMobs.ThiefSkeleton", Aspect.DEATH);

            Config.spawnerMobs.put("SpecialMobs.SpecialSpider", Aspect.CLOTH);
            Config.spawnerMobs.put("SpecialMobs.BabySpider", Aspect.CLOTH);
            Config.spawnerMobs.put("SpecialMobs.DesertSpider", Aspect.CLOTH);
            Config.spawnerMobs.put("SpecialMobs.FlyingSpider", Aspect.CLOTH);
            Config.spawnerMobs.put("SpecialMobs.GhostSpider", Aspect.CLOTH);
            Config.spawnerMobs.put("SpecialMobs.GiantSpider", Aspect.CLOTH);
            Config.spawnerMobs.put("SpecialMobs.HungrySpider", Aspect.CLOTH);
            Config.spawnerMobs.put("SpecialMobs.MotherSpider", Aspect.CLOTH);
            Config.spawnerMobs.put("SpecialMobs.PaleSpider", Aspect.CLOTH);
            Config.spawnerMobs.put("SpecialMobs.PoisonSpider", Aspect.CLOTH);
            Config.spawnerMobs.put("SpecialMobs.SmallSpider", Aspect.CLOTH);
            Config.spawnerMobs.put("SpecialMobs.ToughSpider", Aspect.CLOTH);
            Config.spawnerMobs.put("SpecialMobs.WebSpider", Aspect.CLOTH);
            Config.spawnerMobs.put("SpecialMobs.WitchSpider", Aspect.CLOTH);

            Config.spawnerMobs.put("SpecialMobs.SpecialZombie", Aspect.FLESH);
            Config.spawnerMobs.put("SpecialMobs.BrutishZombie", Aspect.FLESH);
            Config.spawnerMobs.put("SpecialMobs.FireZombie", Aspect.FLESH);
            Config.spawnerMobs.put("SpecialMobs.FishingZombie", Aspect.FLESH);
            Config.spawnerMobs.put("SpecialMobs.GiantZombie", Aspect.FLESH);
            Config.spawnerMobs.put("SpecialMobs.HungryZombie", Aspect.FLESH);
            Config.spawnerMobs.put("SpecialMobs.PlagueZombie", Aspect.FLESH);

        }

        if(twilight){
            Config.spawnerMobs.put("TwilightForest.Wild Boar", Aspect.BEAST);
            Config.spawnerMobs.put("TwilightForest.Bighorn Sheep", Aspect.CLOTH);
            Config.spawnerMobs.put("TwilightForest.Wild Deer", Aspect.BEAST);
            Config.spawnerMobs.put("TwilightForest.Redcap", Aspect.METAL);
            Config.spawnerMobs.put("TwilightForest.Swarm Spider", Aspect.CLOTH);
            Config.spawnerMobs.put("TwilightForest.Skeleton Druid", Aspect.DEATH);
            Config.spawnerMobs.put("TwilightForest.Hostile Wolf", Aspect.BEAST);
            Config.spawnerMobs.put("TwilightForest.Twilight Wraith", Aspect.UNDEAD);
            Config.spawnerMobs.put("TwilightForest.Hedge Spider", Aspect.CLOTH);
            Config.spawnerMobs.put("TwilightForest.Penguin", Aspect.FLIGHT);
            Config.spawnerMobs.put("TwilightForest.Tiny Bird", Aspect.FLIGHT);
            Config.spawnerMobs.put("TwilightForest.Forest Squirrel", Aspect.BEAST);
            Config.spawnerMobs.put("TwilightForest.Forest Bunny", Aspect.BEAST);
            Config.spawnerMobs.put("TwilightForest.Forest Raven", Aspect.FLIGHT);
            Config.spawnerMobs.put("TwilightForest.Twilight Kobold", Aspect.GREED);
            Config.spawnerMobs.put("TwilightForest.Mosquito Swarm", DarkAspects.GLUTTONY);
            Config.spawnerMobs.put("TwilightForest.Death Tome", Aspect.MIND);
            Config.spawnerMobs.put("TwilightForest.Minotaur", Aspect.BEAST);
            Config.spawnerMobs.put("TwilightForest.Minoshroom", Aspect.PLANT);
            Config.spawnerMobs.put("TwilightForest.Fire Beetle", Aspect.FIRE);
            Config.spawnerMobs.put("TwilightForest.Slime Beetle", Aspect.SLIME);
            if(!Config.noLust)
                Config.spawnerMobs.put("TwilightForest.Pinch Beetle", DarkAspects.LUST);
            else
                Config.spawnerMobs.put("TwilightForest.Pinch Beetle", Aspect.BEAST);
            Config.spawnerMobs.put("TwilightForest.Maze Slime", Aspect.SLIME);
            Config.spawnerMobs.put("TwilightForest.Mist Wolf", Aspect.BEAST);
            Config.spawnerMobs.put("TwilightForest.King Spider", DarkAspects.PRIDE);
            Config.spawnerMobs.put("TwilightForest.Mini Ghast", DarkAspects.NETHER);
            Config.spawnerMobs.put("TwilightForest.Tower Ghast", DarkAspects.NETHER);
            Config.spawnerMobs.put("TwilightForest.Tower Golem", Aspect.METAL);
            Config.spawnerMobs.put("TwilightForest.Tower Termite", Aspect.MAGIC);
            Config.spawnerMobs.put("TwilightForest.Upper Goblin Knight", Aspect.METAL);
            Config.spawnerMobs.put("TwilightForest.Lower Goblin Knight", Aspect.METAL);
            Config.spawnerMobs.put("TwilightForest.Helmet Crab", Aspect.METAL);
            Config.spawnerMobs.put("TwilightForest.Knight Phantom", Aspect.METAL);
            Config.spawnerMobs.put("TwilightForest.Yeti", Aspect.COLD);
            Config.spawnerMobs.put("TwilightForest.WinterWolf", Aspect.COLD);
        }
    }

    public static Item getItem(String mod, String item) throws ItemNotFoundException {
        Item target = GameRegistry.findItem(mod, item);
        if(target == null)
            throw new ItemNotFoundException(mod, item);
        return target;
    }

    public static class ItemNotFoundException extends Exception {
        public ItemNotFoundException(String mod, String item){
            super("Unable to find item " + item + " in mod " + mod + "! Are you using the correct version of the mod?");
        }
    }
}