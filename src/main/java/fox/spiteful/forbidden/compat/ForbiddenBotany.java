package fox.spiteful.forbidden.compat;

import cpw.mods.fml.common.registry.GameRegistry;
import fox.spiteful.forbidden.Config;
import fox.spiteful.forbidden.DarkResearchItem;
import fox.spiteful.forbidden.LogHandler;
import fox.spiteful.forbidden.items.ForbiddenItems;
import fox.spiteful.forbidden.tiles.SubTileEuclidaisy;
import fox.spiteful.forbidden.tiles.SubTileTainthistle;
import fox.spiteful.forbidden.tiles.SubTileWhisperweed;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.nbt.NBTTagCompound;
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
import vazkii.botania.api.recipe.RecipePetals;

import java.util.List;

public class ForbiddenBotany {
    public static void flowerPowerHippymancy(){
        try {
            Item rune = GameRegistry.findItem("Botania", "rune");
            Item resource = GameRegistry.findItem("Botania", "manaResource");
            Item livingLog = GameRegistry.findItem("Botania", "livingwood");
            Item flower = GameRegistry.findItem("Botania", "flower");
            Item manaPetal = GameRegistry.findItem("Botania", "manaPetal");

            CraftingManager.getInstance().addRecipe(new ItemStack(resource, 1, 0), new Object[] { "###", "###", "###", Character.valueOf('#'), new ItemStack(ForbiddenItems.resource, 1, 2) });
            CraftingManager.getInstance().addRecipe(new ItemStack(ForbiddenItems.resource, 9, 2), new Object[] { "#", Character.valueOf('#'), new ItemStack(resource, 1, 0) });

            if(Config.crossWand) {

                InfusionRecipe livingwood_rod = ThaumcraftApi.addInfusionCraftingRecipe("ROD_livingwood", new ItemStack(ForbiddenItems.wandCore, 1, 8), 3, (new AspectList()).add(Aspect.MAGIC, 16).add(Aspect.TREE, 32).add(Aspect.LIFE, 16).add(Aspect.SENSES, 8).add(Aspect.PLANT, 8), new ItemStack(livingLog), new ItemStack[]{new ItemStack(resource, 1, 2), new ItemStack(rune, 1, 8), new ItemStack(rune, 1, 0), new ItemStack(rune, 1, 1), new ItemStack(rune, 1, 2), new ItemStack(rune, 1, 3), new ItemStack(rune, 1, 4), new ItemStack(rune, 1, 5), new ItemStack(rune, 1, 6), new ItemStack(rune, 1, 7)});
                (new DarkResearchItem("ROD_livingwood", "FORBIDDEN", "[B]", (new AspectList()).add(Aspect.TREE, 8).add(Aspect.MAGIC, 4).add(Aspect.PLANT, 6), -1, -3, 3, new ItemStack(ForbiddenItems.wandCore, 1, 7))).setPages(new ResearchPage[]{new ResearchPage("forbidden.research_page.ROD_livingwood.1"), new ResearchPage(livingwood_rod)}).setParents(new String[]{"ROD_silverwood", "INFAUXSION"}).setConcealed().registerResearchItem();
                ThaumcraftApi.addWarpToResearch("ROD_livingwood", 2);
                BotaniaAPI.registerManaInfusionRecipe(new ItemStack(ForbiddenItems.wandCore, 1, 7), new ItemStack(ForbiddenItems.wandCore, 1, 8), 10000);

                IArcaneRecipe manasteel_cap = ThaumcraftApi.addArcaneCraftingRecipe("CAP_manasteel", new ItemStack(ForbiddenItems.wandCap, 1, 4), (new AspectList()).add(Aspect.ENTROPY, 6).add(Aspect.FIRE, 6).add(Aspect.WATER, 6).add(Aspect.AIR, 6).add(Aspect.EARTH, 6).add(Aspect.ORDER, 6), new Object[]{"NNN", "N N", Character.valueOf('N'), new ItemStack(ForbiddenItems.resource, 1, 2)});
                (new DarkResearchItem("CAP_manasteel", "FORBIDDEN", "[B]", (new AspectList()).add(Aspect.ELDRITCH, 3).add(Aspect.MAGIC, 2).add(Aspect.ENTROPY, 4), -1, -5, 2, new ItemStack(ForbiddenItems.wandCap, 1, 3))).setPages(new ResearchPage[]{new ResearchPage("forbidden.research_page.CAP_manasteel.1"), new ResearchPage(manasteel_cap)}).setParents(new String[]{"ROD_livingwood"}).setSecondary().setConcealed().registerResearchItem();
                BotaniaAPI.registerManaInfusionRecipe(new ItemStack(ForbiddenItems.wandCap, 1, 3), new ItemStack(ForbiddenItems.wandCap, 1, 4), 1000);
            }

            BotaniaAPI.registerSubTile("euclidaisy", SubTileEuclidaisy.class);
            BotaniaAPI.registerSubTileSignature(SubTileEuclidaisy.class, new DarkSignature("euclidaisy"));
            BotaniaAPI.addSubTileToCreativeMenu("euclidaisy");

            List<LexiconCategory> cats = BotaniaAPI.getAllCategories();
            LexiconCategory functional = null;
            LexiconCategory generating = null;
            for(LexiconCategory cat : cats){
                if(cat.getUnlocalizedName().equals("botania.category.functionalFlowers"))
                    functional = cat;
                else if(cat.getUnlocalizedName().equals("botania.category.generationFlowers"))
                    generating = cat;
            }
            SubTileEuclidaisy.lexicon = new ForbiddenLexicon("euclidaisy", functional);

            SubTileEuclidaisy.lexicon.addPage(BotaniaAPI.internalHandler.textPage("forbidden.lexicon.euclidaisy.0"));

            ItemStack euclidaisy = getFlower("euclidaisy");

            InfusionRecipe euclid =  ThaumcraftApi.addInfusionCraftingRecipe("EUCLIDAISY", euclidaisy, 8, (new AspectList()).add(Aspect.AURA, 8).add(Aspect.ELDRITCH, 10).add(Aspect.MAGIC, 8), new ItemStack(flower, 1, 6), new ItemStack[] { new ItemStack(ConfigItems.itemResource, 1, 14), new ItemStack(resource, 1, 1), new ItemStack(resource, 1, 6), new ItemStack(manaPetal, 1, 6), new ItemStack(manaPetal, 1, 6), new ItemStack(rune, 1, 12), new ItemStack(rune, 1, 11) });
            (new DarkResearchItem("EUCLIDAISY", "FORBIDDEN", "[B]", (new AspectList()).add(Aspect.PLANT, 8).add(Aspect.MAGIC, 4).add(Aspect.AURA, 12), -7, 5, 3, euclidaisy)).setPages(new ResearchPage[] { new ResearchPage("forbidden.research_page.EUCLIDAISY.1"), new ResearchPage(euclid) }).setParents(new String[] { "INFAUXSION" }).setConcealed().registerResearchItem();

            BotaniaAPI.registerSubTile("whisperweed", SubTileWhisperweed.class);
            BotaniaAPI.registerSubTileSignature(SubTileWhisperweed.class, new DarkSignature("whisperweed"));
            BotaniaAPI.addSubTileToCreativeMenu("whisperweed");

            SubTileWhisperweed.lexicon = new ForbiddenLexicon("whisperweed", functional);

            SubTileWhisperweed.lexicon.addPage(BotaniaAPI.internalHandler.textPage("forbidden.lexicon.whisperweed.0"));

            ItemStack whisperweed = getFlower("whisperweed");
            InfusionRecipe whispercraft =  ThaumcraftApi.addInfusionCraftingRecipe("WHISPERWEED", whisperweed, 4, (new AspectList()).add(Aspect.MIND, 8).add(Aspect.MAGIC, 4).add(Aspect.SENSES, 4), new ItemStack(Blocks.tallgrass, 1, 1), new ItemStack[] { new ItemStack(resource, 1, 2), new ItemStack(resource, 1, 6), new ItemStack(ConfigItems.itemResource, 1, 9), new ItemStack(manaPetal, 1, 7), new ItemStack(manaPetal, 1, 10), new ItemStack(rune, 1, 14), new ItemStack(ConfigItems.itemResource, 1, 6) });
            (new DarkResearchItem("WHISPERWEED", "FORBIDDEN", "[B]", (new AspectList()).add(Aspect.PLANT, 8).add(Aspect.MIND, 10).add(Aspect.SENSES, 4), -8, 5, 3, whisperweed)).setPages(new ResearchPage[] { new ResearchPage("forbidden.research_page.WHISPERWEED.1"), new ResearchPage(whispercraft) }).setParents(new String[] { "INFAUXSION" }).setConcealed().registerResearchItem();

            BotaniaAPI.registerSubTile("tainthistle", SubTileTainthistle.class);
            BotaniaAPI.registerSubTileSignature(SubTileTainthistle.class, new DarkSignature("tainthistle"));
            BotaniaAPI.addSubTileToCreativeMenu("tainthistle");

            SubTileTainthistle.lexicon = new ForbiddenLexicon("tainthistle", generating);

            SubTileTainthistle.lexicon.addPage(BotaniaAPI.internalHandler.textPage("forbidden.lexicon.tainthistle.0"));

            ThaumcraftApi.registerObjectTag(GameRegistry.findItemStack("Botania", "specialFlower", 1), (new AspectList()).add(Aspect.PLANT, 4).add(Aspect.MAGIC, 1));

        } catch (Throwable e) {
            LogHandler.log(Level.INFO, e, "Forbidden Magic: Botania? Do you wanna build a snowman?");
            Compat.botan = false;
        }
    }

    public static ItemStack getFlower(String type){
        Item specialFlower = GameRegistry.findItem("Botania", "specialFlower");
        ItemStack flower = new ItemStack(specialFlower, 1, 0);
        NBTTagCompound tag = new NBTTagCompound();
        tag.setString("type", type);
        flower.setTagCompound(tag);
        return flower;
    }
}
