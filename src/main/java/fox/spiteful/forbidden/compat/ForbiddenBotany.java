package fox.spiteful.forbidden.compat;

import WayofTime.alchemicalWizardry.api.altarRecipeRegistry.AltarRecipeRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import fox.spiteful.forbidden.Config;
import fox.spiteful.forbidden.DarkResearchItem;
import fox.spiteful.forbidden.LogHandler;
import fox.spiteful.forbidden.items.ForbiddenItems;
import fox.spiteful.forbidden.tiles.SubTileBloodthorn;
import fox.spiteful.forbidden.tiles.SubTileEuclidaisy;
import fox.spiteful.forbidden.tiles.SubTileTainthistle;
import fox.spiteful.forbidden.tiles.SubTileWhisperweed;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.oredict.OreDictionary;
import org.apache.logging.log4j.Level;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.IArcaneRecipe;
import thaumcraft.api.crafting.InfusionRecipe;
import thaumcraft.api.research.ResearchPage;
import thaumcraft.common.config.ConfigItems;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.lexicon.LexiconCategory;

import java.lang.reflect.Field;
import java.util.List;

public class ForbiddenBotany {
    public static void flowerPowerHippymancy(){
        try {
            Item rune = Compat.getItem("Botania", "rune");
            Item resource = Compat.getItem("Botania", "manaResource");
            Block livingLog = GameRegistry.findBlock("Botania", "livingwood");
            Item flower = Compat.getItem("Botania", "flower");
            Item petal = Compat.getItem("Botania", "petal");
            Item manaPetal = Compat.getItem("Botania", "manaPetal");
            Block dreamwood = GameRegistry.findBlock("Botania", "dreamwood");
            Item lexicon = Compat.getItem("Botania", "lexicon");

            try {
                Class stupidLib = Class.forName("vazkii.botania.common.lib.LibOreDict");
                Field nugget = stupidLib.getDeclaredField("MANASTEEL_NUGGET");

            }
            catch (Exception e){
                e.printStackTrace();
                LogHandler.log(Level.INFO, "Somebody's using an outdated version of Botania.");

                CraftingManager.getInstance().addRecipe(new ItemStack(resource, 1, 0), new Object[] { "###", "###", "###", Character.valueOf('#'), new ItemStack(ForbiddenItems.resource, 1, 2) });
                CraftingManager.getInstance().addRecipe(new ItemStack(ForbiddenItems.resource, 9, 2), new Object[] { "#", Character.valueOf('#'), new ItemStack(resource, 1, 0) });

                CraftingManager.getInstance().addRecipe(new ItemStack(resource, 1, 7), new Object[] { "###", "###", "###", Character.valueOf('#'), new ItemStack(ForbiddenItems.resource, 1, 4) });
                CraftingManager.getInstance().addRecipe(new ItemStack(ForbiddenItems.resource, 9, 4), new Object[] { "#", Character.valueOf('#'), new ItemStack(resource, 1, 7) });
            }

            OreDictionary.registerOre("nuggetManasteel", new ItemStack(ForbiddenItems.resource, 1, 2));
            OreDictionary.registerOre("nuggetElvenElementium", new ItemStack(ForbiddenItems.resource, 1, 4));

            (new DarkResearchItem("BOTANY", "FORBIDDEN", "[B]", new AspectList(), -3, 3, 0, new ItemStack(lexicon))).setPages(new ResearchPage[] { new ResearchPage("forbidden.research_page.BOTANY.1")}).setParents(new String [] {"SCHOOLS"}).setRound().setStub().setAutoUnlock().registerResearchItem();

            if(Config.crossWand) {

                InfusionRecipe livingwood_rod = ThaumcraftApi.addInfusionCraftingRecipe("ROD_livingwood", new ItemStack(ForbiddenItems.wandCore, 1, 8), 3, (new AspectList()).add(Aspect.MAGIC, 16).add(Aspect.TREE, 32).add(Aspect.LIFE, 16).add(Aspect.SENSES, 8).add(Aspect.PLANT, 8), new ItemStack(livingLog), new ItemStack[]{new ItemStack(resource, 1, 2), new ItemStack(rune, 1, 8), new ItemStack(rune, 1, 0), new ItemStack(rune, 1, 1), new ItemStack(rune, 1, 2), new ItemStack(rune, 1, 3), new ItemStack(rune, 1, 4), new ItemStack(rune, 1, 5), new ItemStack(rune, 1, 6), new ItemStack(rune, 1, 7)});
                (new DarkResearchItem("ROD_livingwood", "FORBIDDEN", "[B]", (new AspectList()).add(Aspect.TREE, 8).add(Aspect.MAGIC, 4).add(Aspect.PLANT, 6), -1, 3, 3, new ItemStack(ForbiddenItems.wandCore, 1, 7))).setPages(new ResearchPage[]{new ResearchPage("forbidden.research_page.ROD_livingwood.1"), new ResearchPage(livingwood_rod)}).setParents(new String[]{"BOTANY", "ROD_silverwood", "INFUSION"}).setConcealed().registerResearchItem();
                ThaumcraftApi.addWarpToResearch("ROD_livingwood", 2);
                BotaniaAPI.registerManaInfusionRecipe(new ItemStack(ForbiddenItems.wandCore, 1, 7), new ItemStack(ForbiddenItems.wandCore, 1, 8), 10000);

                IArcaneRecipe manasteel_cap = ThaumcraftApi.addArcaneCraftingRecipe("CAP_manasteel", new ItemStack(ForbiddenItems.wandCap, 1, 4), (new AspectList()).add(Aspect.ENTROPY, 6).add(Aspect.FIRE, 6).add(Aspect.WATER, 6).add(Aspect.AIR, 6).add(Aspect.EARTH, 6).add(Aspect.ORDER, 6), new Object[]{"NNN", "N N", Character.valueOf('N'), "nuggetManasteel"});
                (new DarkResearchItem("CAP_manasteel", "FORBIDDEN", "[B]", (new AspectList()).add(Aspect.AURA, 6).add(Aspect.MAGIC, 4).add(Aspect.METAL, 4), 1, 2, 2, new ItemStack(ForbiddenItems.wandCap, 1, 3))).setPages(new ResearchPage[]{new ResearchPage("forbidden.research_page.CAP_manasteel.1"), new ResearchPage(manasteel_cap)}).setParents(new String[]{"ROD_livingwood"}).setSecondary().setConcealed().registerResearchItem();
                BotaniaAPI.registerManaInfusionRecipe(new ItemStack(ForbiddenItems.wandCap, 1, 3), new ItemStack(ForbiddenItems.wandCap, 1, 4), 1000);

                InfusionRecipe dreamwood_rod = ThaumcraftApi.addInfusionCraftingRecipe("ROD_dreamwood", new ItemStack(ForbiddenItems.wandCore, 1, 12), 3, (new AspectList()).add(Aspect.MAGIC, 16).add(Aspect.TREE, 32).add(Aspect.LIFE, 16).add(Aspect.SENSES, 8).add(Aspect.PLANT, 8), new ItemStack(dreamwood), new ItemStack[]{new ItemStack(resource, 1, 2), new ItemStack(rune, 1, 8), new ItemStack(rune, 1, 0), new ItemStack(rune, 1, 1), new ItemStack(rune, 1, 2), new ItemStack(rune, 1, 3), new ItemStack(rune, 1, 4), new ItemStack(rune, 1, 5), new ItemStack(rune, 1, 6), new ItemStack(rune, 1, 7)});
                IArcaneRecipe dreamwood_staff = ThaumcraftApi.addArcaneCraftingRecipe("ROD_dreamwood_staff", new ItemStack(ForbiddenItems.wandCore, 1, 13), (new AspectList()).add(Aspect.ENTROPY, 26).add(Aspect.FIRE, 26).add(Aspect.WATER, 26).add(Aspect.AIR, 26).add(Aspect.EARTH, 26).add(Aspect.ORDER, 26), new Object[]{"__D", "_B_", "B__", Character.valueOf('B'), new ItemStack(ForbiddenItems.wandCore, 1, 11), Character.valueOf('D'), new ItemStack(resource, 1, 9)});
                BotaniaAPI.registerManaInfusionRecipe(new ItemStack(ForbiddenItems.wandCore, 1, 11), new ItemStack(ForbiddenItems.wandCore, 1, 12), 10000);
                (new DarkResearchItem("ROD_dreamwood_staff", "FORBIDDEN", "[B]", (new AspectList()).add(Aspect.TREE, 16).add(Aspect.MAGIC, 8).add(Aspect.ELDRITCH, 6), 1, 3, 2, new ItemStack(ForbiddenItems.wandCore, 1, 13))).setPages(new ResearchPage[]{new ResearchPage("forbidden.research_page.ROD_dreamwood_staff.1"), new ResearchPage(dreamwood_staff), new ResearchPage(dreamwood_rod)}).setParents(new String[]{"ROD_livingwood", "ROD_silverwood_staff"}).setSiblings(new String[]{"ROD_dreamwood"}).setConcealed().registerResearchItem();
                (new DarkResearchItem("ROD_dreamwood", "FORBIDDEN")).setVirtual().registerResearchItem();

                IArcaneRecipe elementium_cap_inert = ThaumcraftApi.addArcaneCraftingRecipe("CAP_elementium", new ItemStack(ForbiddenItems.wandCap, 1, 6), (new AspectList()).add(Aspect.ENTROPY, 27).add(Aspect.FIRE, 27).add(Aspect.WATER, 27).add(Aspect.AIR, 27).add(Aspect.EARTH, 27).add(Aspect.ORDER, 27), new Object[]{"NNN", "N N", Character.valueOf('N'), "nuggetElvenElementium"});
                InfusionRecipe elementium_cap = ThaumcraftApi.addInfusionCraftingRecipe("CAP_elementium", new ItemStack(ForbiddenItems.wandCap, 1, 5), 1, (new AspectList()).add(Aspect.EARTH, 18).add(Aspect.FIRE, 18).add(Aspect.WATER, 18).add(Aspect.AIR, 18), new ItemStack(ForbiddenItems.wandCap, 1, 6), new ItemStack[]{new ItemStack(resource, 1, 5), new ItemStack(rune, 1, 0), new ItemStack(rune, 1, 1), new ItemStack(rune, 1, 2), new ItemStack(rune, 1, 3)});
                (new DarkResearchItem("CAP_elementium", "FORBIDDEN", "[B]", (new AspectList()).add(Aspect.FIRE, 8).add(Aspect.MAGIC, 12).add(Aspect.WATER, 8).add(Aspect.AIR, 8).add(Aspect.EARTH, 8), 3, 3, 4, new ItemStack(ForbiddenItems.wandCap, 1, 5))).setPages(new ResearchPage[]{new ResearchPage("forbidden.research_page.CAP_elementium.1"), new ResearchPage(elementium_cap_inert), new ResearchPage(elementium_cap)}).setParents(new String[]{"ROD_dreamwood_staff"}).setConcealed().registerResearchItem();

            }

            BotaniaAPI.registerSubTile("euclidaisy", SubTileEuclidaisy.class);
            BotaniaAPI.registerSubTileSignature(SubTileEuclidaisy.class, new DarkSignature("euclidaisy"));
            BotaniaAPI.addSubTileToCreativeMenu("euclidaisy");

            SubTileEuclidaisy.lexicon = new ForbiddenLexicon("euclidaisy", BotaniaAPI.categoryFunctionalFlowers);

            SubTileEuclidaisy.lexicon.addPage(BotaniaAPI.internalHandler.textPage("forbidden.lexicon.euclidaisy.0"));

            ItemStack euclidaisy = getFlower("euclidaisy");
            SubTileEuclidaisy.lexicon.setIcon(euclidaisy);

            InfusionRecipe euclid =  ThaumcraftApi.addInfusionCraftingRecipe("EUCLIDAISY", euclidaisy, 8, (new AspectList()).add(Aspect.AURA, 8).add(Aspect.ELDRITCH, 10).add(Aspect.MAGIC, 8), new ItemStack(flower, 1, 6), new ItemStack[] { new ItemStack(ConfigItems.itemResource, 1, 14), new ItemStack(resource, 1, 1), new ItemStack(resource, 1, 6), new ItemStack(manaPetal, 1, 6), new ItemStack(manaPetal, 1, 6), new ItemStack(rune, 1, 12), new ItemStack(rune, 1, 11) });
            (new DarkResearchItem("EUCLIDAISY", "FORBIDDEN", "[B]", (new AspectList()).add(Aspect.PLANT, 8).add(Aspect.MAGIC, 4).add(Aspect.AURA, 12), -3, 5, 2, euclidaisy)).setPages(new ResearchPage[] { new ResearchPage("forbidden.research_page.EUCLIDAISY.1"), new ResearchPage(euclid) }).setParents(new String[] { "BOTANY", "INFUSION" }).setConcealed().registerResearchItem();

            BotaniaAPI.registerSubTile("whisperweed", SubTileWhisperweed.class);
            BotaniaAPI.registerSubTileSignature(SubTileWhisperweed.class, new DarkSignature("whisperweed"));
            BotaniaAPI.addSubTileToCreativeMenu("whisperweed");

            SubTileWhisperweed.lexicon = new ForbiddenLexicon("whisperweed", BotaniaAPI.categoryFunctionalFlowers);

            SubTileWhisperweed.lexicon.addPage(BotaniaAPI.internalHandler.textPage("forbidden.lexicon.whisperweed.0"));

            ItemStack whisperweed = getFlower("whisperweed");
            SubTileWhisperweed.lexicon.setIcon(whisperweed);

            IArcaneRecipe whispercraft =  ThaumcraftApi.addShapelessArcaneCraftingRecipe("WHISPERWEED", whisperweed, (new AspectList()).add(Aspect.FIRE, 10).add(Aspect.WATER, 10).add(Aspect.EARTH, 10).add(Aspect.AIR, 10).add(Aspect.ORDER, 10).add(Aspect.ENTROPY, 10), new ItemStack[] { new ItemStack(Blocks.tallgrass, 1, 1), new ItemStack(resource, 1, 2), new ItemStack(resource, 1, 6), new ItemStack(ConfigItems.itemResource, 1, 9), new ItemStack(manaPetal, 1, 7), new ItemStack(manaPetal, 1, 10), new ItemStack(rune, 1, 14), new ItemStack(ConfigItems.itemResource, 1, 6) });
            (new DarkResearchItem("WHISPERWEED", "FORBIDDEN", "[B]", (new AspectList()).add(Aspect.PLANT, 8).add(Aspect.MIND, 10).add(Aspect.SENSES, 4), -4, 5, 1, whisperweed)).setPages(new ResearchPage[] { new ResearchPage("forbidden.research_page.WHISPERWEED.1"), new ResearchPage(whispercraft) }).setParents(new String[] { "BOTANY" }).setConcealed().registerResearchItem();

            BotaniaAPI.registerSubTile("tainthistle", SubTileTainthistle.class);
            BotaniaAPI.registerSubTileSignature(SubTileTainthistle.class, new DarkSignature("tainthistle"));
            BotaniaAPI.addSubTileToCreativeMenu("tainthistle");

            SubTileTainthistle.lexicon = new ForbiddenLexicon("tainthistle", BotaniaAPI.categoryGenerationFlowers);

            SubTileTainthistle.lexicon.addPage(BotaniaAPI.internalHandler.textPage("forbidden.lexicon.tainthistle.0"));

            ItemStack tainthistle = getFlower("tainthistle");
            SubTileTainthistle.lexicon.setIcon(tainthistle);

            InfusionRecipe thistlecraft =  ThaumcraftApi.addInfusionCraftingRecipe("TAINTHISTLE", tainthistle, 4, (new AspectList()).add(Aspect.TAINT, 8).add(Aspect.HUNGER, 4).add(Aspect.AIR, 4).add(Aspect.WATER, 4), new ItemStack(ConfigItems.itemResource, 1, 12), new ItemStack[] { new ItemStack(petal, 1, 10), new ItemStack(manaPetal, 1, 10), new ItemStack(manaPetal, 1, 10), new ItemStack(rune, 1, 10), new ItemStack(rune, 1, 8)});
            (new DarkResearchItem("TAINTHISTLE", "FORBIDDEN", "[B]", (new AspectList()).add(Aspect.PLANT, 8).add(Aspect.TAINT, 10).add(Aspect.MAGIC, 4), -2, 5, 2, tainthistle)).setPages(new ResearchPage[] { new ResearchPage("forbidden.research_page.TAINTHISTLE.1"), new ResearchPage(thistlecraft) }).setParents(new String[] { "BOTANY", "INFUSION" }).setConcealed().registerResearchItem();

            if(Compat.bm){
                BotaniaAPI.registerSubTile("bloodthorn", SubTileBloodthorn.class);
                BotaniaAPI.registerSubTileSignature(SubTileBloodthorn.class, new DarkSignature("bloodthorn"));
                BotaniaAPI.addSubTileToCreativeMenu("bloodthorn");

                SubTileBloodthorn.lexicon = new ForbiddenLexicon("bloodthorn", BotaniaAPI.categoryFunctionalFlowers){
                    @Override
                    public String getSubtitle(){
                        return "[Forbidden Magic][Blood Magic]";
                    }
                };

                SubTileBloodthorn.lexicon.addPage(BotaniaAPI.internalHandler.textPage("forbidden.lexicon.bloodthorn.0"));

                ItemStack bloodthorn = getFlower("bloodthorn");
                SubTileBloodthorn.lexicon.setIcon(bloodthorn);
                try{
                    AltarRecipeRegistry.registerAltarRecipe(bloodthorn, new ItemStack(flower, 1, 14), 4, 15000, 25, 50, false);
                }
                catch(Throwable e){
                    LogHandler.log(Level.INFO, e, "Forbidden Magic tried to do some Blood Magic, but bled out.");
                }
            }

            ThaumcraftApi.registerObjectTag(new ItemStack(Compat.getItem("Botania", "specialFlower"), 1), (new AspectList()).add(Aspect.PLANT, 4).add(Aspect.MAGIC, 1));

        } catch (Throwable e) {
            LogHandler.log(Level.INFO, e, "Forbidden Magic: Botania? Do you wanna build a snowman?");
            Compat.botan = false;
        }
    }

    private static ItemStack getFlower(String type) throws Compat.ItemNotFoundException {
        Item specialFlower = Compat.getItem("Botania", "specialFlower");
        ItemStack flower = new ItemStack(specialFlower, 1, 0);
        NBTTagCompound tag = new NBTTagCompound();
        tag.setString("type", type);
        flower.setTagCompound(tag);
        return flower;
    }
}
