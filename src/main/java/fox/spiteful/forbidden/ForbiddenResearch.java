package fox.spiteful.forbidden;

import java.util.HashMap;

import fox.spiteful.forbidden.blocks.ForbiddenBlocks;
import fox.spiteful.forbidden.compat.Compat;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.CrucibleRecipe;
import thaumcraft.api.crafting.IArcaneRecipe;
import thaumcraft.api.crafting.InfusionEnchantmentRecipe;
import thaumcraft.api.crafting.InfusionRecipe;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchPage;

import fox.spiteful.forbidden.items.ForbiddenItems;

public class ForbiddenResearch {
    public static HashMap recipes = new HashMap();

    public static void addResearch() {
        ResearchCategories.registerCategory("FORBIDDEN", new ResourceLocation("forbidden", "textures/misc/forbidden.png"), new ResourceLocation("forbidden", "textures/misc/runecircle.png"));

        addInfernalism();
        addTaint();

        if(thaumcraft.common.config.Config.researchDifficulty != -1)
            (new DarkResearchItem("CRYSTALWELL", "FORBIDDEN", (new AspectList()).add(Aspect.MIND, 3).add(Aspect.CRYSTAL, 2).add(Aspect.MAGIC, 1), -2, -8, 1, new ItemStack(ForbiddenItems.crystalwell, 1, 0))).setPages(new ResearchPage[] { new ResearchPage("forbidden.research_page.CRYSTALWELL.1"), new ResearchPage((IArcaneRecipe) recipes.get("Crystalwell")) }).setParents(new String[] { "RESEARCH" }).registerResearchItem();
        else
            (new DarkResearchItem("CRYSTALWELL", "FORBIDDEN", (new AspectList()).add(Aspect.MIND, 3).add(Aspect.CRYSTAL, 2).add(Aspect.MAGIC, 1), -2, -8, 1, new ItemStack(ForbiddenItems.crystalwell, 1, 0))).setPages(new ResearchPage[] { new ResearchPage("forbidden.research_page.CRYSTALWELL.1b"), new ResearchPage((IArcaneRecipe) recipes.get("Crystalwell")) }).setParents(new String[] { "RESEARCH" }).registerResearchItem();

        (new DarkResearchItem("PRIMEWELL", "FORBIDDEN", (new AspectList()).add(Aspect.MIND, 3).add(Aspect.ELDRITCH, 6).add(Aspect.CRAFT, 1), 2, -8, 1, new ItemStack(ForbiddenItems.primewell, 1, 0))).setPages(new ResearchPage[] { new ResearchPage("forbidden.research_page.PRIMEWELL.1"), new ResearchPage((IArcaneRecipe) recipes.get("Primewell")) }).setParents(new String[] { "PRIMPEARL" }).setConcealed().registerResearchItem();
        if (Config.emeraldTrans)
            (new DarkResearchItem("TRANSEMERALD", "FORBIDDEN", (new AspectList()).add(Aspect.CRYSTAL, 2).add(Aspect.EXCHANGE, 5).add(Aspect.GREED, 4), 0, -6, 3, new ItemStack(ForbiddenItems.resource, 1, 0))).setPages(new ResearchPage[] { new ResearchPage("forbidden.research_page.TRANSEMERALD.1"), new ResearchPage((CrucibleRecipe) recipes.get("TransEmerald")) }).setSecondary().setConcealed().setParents(new String[] { "TRANSGOLD" }).registerResearchItem();
        (new DarkResearchItem("BLACKFLOWER", "FORBIDDEN", (new AspectList()).add(Aspect.PLANT, 3).add(Aspect.SENSES, 2).add(Aspect.DARKNESS, 4), -2, -6, 1, new ItemStack(ForbiddenBlocks.blackFlower, 1, 0))).setPages(new ResearchPage[] { new ResearchPage("forbidden.research_page.BLACKFLOWER.1"), new ResearchPage((CrucibleRecipe) recipes.get("BlackFlower")), new ResearchPage((IRecipe) recipes.get("BlackInk")) }).setAspectTriggers(new Aspect[] { Aspect.SENSES }).registerResearchItem();
        (new DarkResearchItem("RIDINGCROP", "FORBIDDEN", new AspectList(), 0, -8, 0, new ItemStack(ForbiddenItems.ridingCrop))).setPages(new ResearchPage[] { new ResearchPage("forbidden.research_page.RIDINGCROP.1"), new ResearchPage((IRecipe) recipes.get("RidingCrop")) }).setStub().setRound().setAutoUnlock().registerResearchItem();


        if(Config.enchanting) {
            (new DarkResearchItem("CONSUMING", "FORBIDDEN", (new AspectList()).add(Aspect.VOID, 4).add(Aspect.ENTROPY, 3).add(Aspect.MAGIC, 2), -4, -8, 1, new ResourceLocation("forbidden", "textures/misc/consuming.png"))).setPages(new ResearchPage[]{new ResearchPage("forbidden.research_page.CONSUMING.1"), new ResearchPage((InfusionEnchantmentRecipe) recipes.get("Consuming"))}).setParents(new String[]{"INFUSIONENCHANTMENT"}).setSecondary().setConcealed().registerResearchItem();
            (new DarkResearchItem("EDUCATIONAL", "FORBIDDEN", (new AspectList()).add(Aspect.MIND, 5).add(Aspect.WEAPON, 1).add(Aspect.MAGIC, 3), -4, -7, 2, new ResourceLocation("forbidden", "textures/misc/educational.png"))).setPages(new ResearchPage[]{new ResearchPage("forbidden.research_page.EDUCATIONAL.1"), new ResearchPage((InfusionEnchantmentRecipe) recipes.get("Educational"))}).setParents(new String[]{"INFUSIONENCHANTMENT"}).setSecondary().setConcealed().registerResearchItem();
        }

        if(Compat.botan || Compat.bm || Compat.am2)
            (new DarkResearchItem("SCHOOLS", "FORBIDDEN", new AspectList(), -1, 1, 0, new ItemStack(Blocks.enchanting_table))).setPages(new ResearchPage[] { new ResearchPage("forbidden.research_page.SCHOOLS.1") }).setRound().setStub().setAutoUnlock().registerResearchItem();

    }

    public static void addInfernalism() {
        if (!Config.noLust)
            (new DarkResearchItem("NETHERSHARDS", "FORBIDDEN", new AspectList(), -8, -5, 0, new ItemStack(ForbiddenItems.deadlyShards, 1, 0))).setPages(new ResearchPage[] { new ResearchPage("forbidden.research_page.NETHERSHARDS.1"), new ResearchPage("forbidden.research_page.NETHERSHARDS.2"), new ResearchPage("forbidden.research_page.NETHERSHARDS.3") }).setStub().setRound().setAutoUnlock().registerResearchItem();
        else
            (new DarkResearchItem("NETHERSHARDS", "FORBIDDEN", new AspectList(), -8, -5, 0, new ItemStack(ForbiddenItems.deadlyShards, 1, 0))).setPages(new ResearchPage[] { new ResearchPage("forbidden.research_page.NETHERSHARDS.1"), new ResearchPage("forbidden.research_page.NETHERSHARDS.2b"), new ResearchPage("forbidden.research_page.NETHERSHARDS.3") }).setStub().setRound().setAutoUnlock().registerResearchItem();

        (new DarkResearchItem("HELLFIRE", "FORBIDDEN", (new AspectList()).add(Aspect.FIRE, 3).add(DarkAspects.NETHER, 4).add(Aspect.TRAVEL, 2), -8, -8, 1, new ItemStack(Blocks.fire))).setPages(new ResearchPage[] { new ResearchPage("forbidden.research_page.HELLFIRE.1") }).setRound().registerResearchItem().setHidden().setAspectTriggers(new Aspect[]{DarkAspects.NETHER}).setParents(new String[] { "NETHERSHARDS" });
        (new DarkResearchItem("ROD_profane", "FORBIDDEN", (new AspectList()), -9, -8, 0, new ItemStack(ForbiddenItems.wandCore, 1, 5))).setPages(new ResearchPage[] { new ResearchPage("forbidden.research_page.ROD_profane.1") }).setRound().registerResearchItem().setHidden().setParents(new String[] { "NETHERSHARDS" }).setStub();
        if(!Config.noLust)
            (new DarkResearchItem("SUBCOLLAR", "FORBIDDEN", (new AspectList().add(Aspect.AURA, 3).add(DarkAspects.LUST, 8).add(Aspect.MAGIC, 3).add(Aspect.TRAP, 6).add(Aspect.FLESH, 4)), -11, -5, 2, new ItemStack(ForbiddenItems.subCollar))).setPages(new ResearchPage[] { new ResearchPage("forbidden.research_page.SUBCOLLAR.1"), new ResearchPage((InfusionRecipe) recipes.get("SubCollar")) }).setParents(new String[] { "NETHERSHARDS", "VISAMULET", "INFUSION" }).setHidden().setAspectTriggers(new Aspect[]{DarkAspects.LUST}).registerResearchItem();

        (new DarkResearchItem("SKULLAXE", "FORBIDDEN", (new AspectList()).add(Aspect.WEAPON, 3).add(DarkAspects.WRATH, 4).add(DarkAspects.NETHER, 1), -11, -7, 2, new ItemStack(ForbiddenItems.skullAxe))).setPages(new ResearchPage[] { new ResearchPage("forbidden.research_page.SKULLAXE.1"), new ResearchPage((InfusionRecipe) recipes.get("SkullAxe")) }).setParents(new String[] { "NETHERSHARDS", "THAUMIUM", "INFUSION" }).setConcealed().registerResearchItem();
        ThaumcraftApi.addWarpToResearch("SKULLAXE", 1);
        if(Config.gluttony != 1) {
            (new DarkResearchItem("RINGFOOD", "FORBIDDEN", (new AspectList()).add(DarkAspects.GLUTTONY, 4).add(Aspect.HUNGER, 3).add(Aspect.LIFE, 2), -11, -6, 2, new ItemStack(ForbiddenItems.ringFood))).setPages(new ResearchPage[]{new ResearchPage("forbidden.research_page.RINGFOOD.1"), new ResearchPage((IArcaneRecipe) recipes.get("RingFood"))}).setParents(new String[]{"NETHERSHARDS"}).registerResearchItem();
            (new DarkResearchItem("ARCANECAKE", "FORBIDDEN", (new AspectList()).add(DarkAspects.GLUTTONY, 6).add(Aspect.HUNGER, 3).add(Aspect.CRAFT, 2), -12, -6, 2, new ItemStack(ForbiddenItems.arcaneCakeItem))).setPages(new ResearchPage[]{new ResearchPage("forbidden.research_page.ARCANECAKE.1"), new ResearchPage((InfusionRecipe) recipes.get("ArcaneCake"))}).setParents(new String[]{"NETHERSHARDS", "INFUSION"}).setAspectTriggers(new Aspect[]{DarkAspects.GLUTTONY}).setConcealed().registerResearchItem();
        }
        (new DarkResearchItem("FOCUSBLINK", "FORBIDDEN", (new AspectList()).add(Aspect.TRAVEL, 3).add(Aspect.ENTROPY, 3).add(DarkAspects.NETHER, 3).add(DarkAspects.SLOTH, 6), -11, -3, 2, new ItemStack(ForbiddenItems.blinkFocus))).setPages(new ResearchPage[]{new ResearchPage("forbidden.research_page.FOCUSBLINK.1"), new ResearchPage((InfusionRecipe) recipes.get("FocusBlink"))}).setConcealed().setParents(new String[]{"NETHERSHARDS", "INFUSION"}).registerResearchItem();
        (new DarkResearchItem("MORPHTOOLS", "FORBIDDEN", (new AspectList()).add(Aspect.TOOL, 2).add(DarkAspects.ENVY, 3).add(Aspect.EXCHANGE, 2), -9, -3, 4, new ItemStack(ForbiddenItems.morphPickaxe))).setPages(new ResearchPage[] { new ResearchPage("forbidden.research_page.MORPHTOOLS.1"), new ResearchPage((InfusionRecipe) recipes.get("MorphPick")), new ResearchPage((InfusionRecipe) recipes.get("MorphSword")), new ResearchPage((InfusionRecipe) recipes.get("MorphShovel")), new ResearchPage((InfusionRecipe) recipes.get("MorphAxe")) }).setParentsHidden(new String[] { "THAUMIUM", "INFUSIONENCHANTMENT" }).setParents(new String[] { "NETHERSHARDS" }).setConcealed().registerResearchItem();
        ThaumcraftApi.addWarpToResearch("MORPHTOOLS", 4);
        (new DarkResearchItem("ROD_infernal", "FORBIDDEN", (new AspectList()).add(DarkAspects.NETHER, 4).add(Aspect.FIRE, 3).add(Aspect.TOOL, 1), -11, -4, 3, new ItemStack(ForbiddenItems.wandCore, 1, 1))).setPages(new ResearchPage[] { new ResearchPage("forbidden.research_page.ROD_infernal.1"), new ResearchPage((InfusionRecipe) recipes.get("WandRodInfernal")), new ResearchPage("forbidden.research_page.ROD_infernal.2") }).setParents(new String[] { "ROD_silverwood", "INFUSION", "NETHERSHARDS" }).setConcealed().registerResearchItem();
        ThaumcraftApi.addWarpToResearch("ROD_infernal", 2);

        if(Config.enchanting) {
            (new DarkResearchItem("WRATH", "FORBIDDEN", (new AspectList()).add(DarkAspects.WRATH, 16).add(Aspect.WEAPON, 20).add(Aspect.MAGIC, 10), -6, -3, 4, new ResourceLocation("forbidden", "textures/misc/wrath.png"))).setPages(new ResearchPage[]{new ResearchPage("forbidden.research_page.WRATH.1"), new ResearchPage((InfusionEnchantmentRecipe) recipes.get("Wrath"))}).setParents(new String[]{"NETHERSHARDS", "INFUSIONENCHANTMENT"}).setConcealed().registerResearchItem();
            ThaumcraftApi.addWarpToResearch("WRATH", 3);
            if (Config.greedyEnch)
                (new DarkResearchItem("GREEDY", "FORBIDDEN", (new AspectList()).add(Aspect.MAGIC, 2).add(Aspect.WEAPON, 1).add(Aspect.GREED, 3), -6, -5, 2, new ResourceLocation("forbidden", "textures/misc/greedy.png"))).setPages(new ResearchPage[]{new ResearchPage("forbidden.research_page.GREEDY.1"), new ResearchPage((InfusionEnchantmentRecipe) recipes.get("Greedy"))}).setParents(new String[]{"NETHERSHARDS", "INFUSIONENCHANTMENT"}).setConcealed().registerResearchItem();
            (new DarkResearchItem("CORRUPTING", "FORBIDDEN", (new AspectList()).add(DarkAspects.NETHER, 5).add(Aspect.CRYSTAL, 2).add(Aspect.EXCHANGE, 1), -6, -4, 1, new ResourceLocation("forbidden", "textures/misc/corrupting.png"))).setPages(new ResearchPage[]{new ResearchPage("forbidden.research_page.CORRUPTING.1"), new ResearchPage((InfusionEnchantmentRecipe) recipes.get("Corrupting"))}).setParents(new String[]{"NETHERSHARDS", "INFUSIONENCHANTMENT"}).setConcealed().registerResearchItem();

            (new DarkResearchItem("CLUSTER", "FORBIDDEN", (new AspectList()).add(Aspect.METAL, 1).add(Aspect.FIRE, 4).add(DarkAspects.ENVY, 3), -10, -1, 3, new ResourceLocation("forbidden", "textures/misc/lucrative.png"))).setPages(new ResearchPage[]{new ResearchPage("forbidden.research_page.CLUSTER.1"), new ResearchPage((InfusionEnchantmentRecipe) recipes.get("Cluster"))}).setParents(new String[]{"MORPHTOOLS", "ELEMENTALPICK"}).setSecondary().setConcealed().registerResearchItem();
            (new DarkResearchItem("IMPACT", "FORBIDDEN", (new AspectList()).add(Aspect.ENTROPY, 8).add(Aspect.TOOL, 10).add(Aspect.MINE, 16).add(DarkAspects.ENVY, 10), -9, -1, 3, new ResourceLocation("forbidden", "textures/misc/impact.png"))).setPages(new ResearchPage[]{new ResearchPage("forbidden.research_page.IMPACT.1"), new ResearchPage((InfusionEnchantmentRecipe) recipes.get("Impact"))}).setParents(new String[]{"MORPHTOOLS", "ELEMENTALSHOVEL"}).setConcealed().registerResearchItem();
            (new DarkResearchItem("VOIDTOUCHED", "FORBIDDEN", (new AspectList()).add(Aspect.ELDRITCH, 16).add(Aspect.TOOL, 10).add(Aspect.CRAFT, 8).add(DarkAspects.ENVY, 32), -8, -1, 4, new ResourceLocation("forbidden", "textures/misc/voidtouched.png"))).setPages(new ResearchPage[]{new ResearchPage("forbidden.research_page.VOIDTOUCHED.1"), new ResearchPage((InfusionEnchantmentRecipe) recipes.get("Voidtouched"))}).setParents(new String[]{"MORPHTOOLS", "VOIDMETAL"}).setConcealed().registerResearchItem();
            ThaumcraftApi.addWarpToResearch("VOIDTOUCHED", 3);
        }

        if (Config.wrathCage) {
            (new DarkResearchItem("FORK", "FORBIDDEN", (new AspectList()).add(DarkAspects.NETHER, 3).add(Aspect.MECHANISM, 1).add(Aspect.TOOL, 1), -6, -7, 0, new ItemStack(ForbiddenItems.fork))).setPages(new ResearchPage[] { new ResearchPage("forbidden.research_page.FORK.1"), new ResearchPage((InfusionRecipe) recipes.get("Fork")), new ResearchPage("WRATHCAGE", "forbidden.research_page.FORK.wc") }).setParents(new String[] {"INFUSION", "NETHERSHARDS", "THAUMIUM"}).setSecondary().setConcealed().registerResearchItem();
            if (Config.wrathCost > 0)
                (new DarkResearchItem("WRATHCAGE", "FORBIDDEN", (new AspectList()).add(DarkAspects.WRATH, 5).add(Aspect.MECHANISM, 3).add(Aspect.BEAST, 2), -6, -9, 4, new ItemStack(ForbiddenBlocks.wrathCage))).setPages(new ResearchPage[]{new ResearchPage("forbidden.research_page.WRATHCAGE.1"), new ResearchPage((InfusionRecipe) recipes.get("WrathCage")), new ResearchPage("forbidden.research_page.WRATHCAGE.2"), new ResearchPage("forbidden.research_page.WRATHCAGE.3"), new ResearchPage((CrucibleRecipe) recipes.get("MobCrystal"))}).setParents(new String[]{"FORK"}).setParentsHidden(new String[]{"INFUSION", "THAUMIUM", "DISTILESSENTIA"}).setConcealed().registerResearchItem();
            else
                (new DarkResearchItem("WRATHCAGE", "FORBIDDEN", (new AspectList()).add(DarkAspects.WRATH, 5).add(Aspect.MECHANISM, 3).add(Aspect.BEAST, 2), -6, -9, 4, new ItemStack(ForbiddenBlocks.wrathCage))).setPages(new ResearchPage[]{new ResearchPage("forbidden.research_page.WRATHCAGE.1"), new ResearchPage((InfusionRecipe) recipes.get("WrathCage")), new ResearchPage("forbidden.research_page.WRATHCAGE.3"), new ResearchPage((CrucibleRecipe) recipes.get("MobCrystal"))}).setParents(new String[]{"FORK"}).setParentsHidden(new String[]{"INFUSION", "THAUMIUM", "DISTILESSENTIA"}).setConcealed().registerResearchItem();
            ThaumcraftApi.addWarpToResearch("WRATHCAGE", 3);
            ThaumcraftApi.addWarpToItem(new ItemStack(ForbiddenBlocks.wrathCage, 1), 5);
        }

    }

    public static void addTaint() {
        (new DarkResearchItem("TAINTSHOVEL", "FORBIDDEN", (new AspectList()).add(Aspect.CRYSTAL, 3).add(Aspect.TAINT, 2).add(Aspect.TOOL, 1), -8, 1, 2, new ItemStack(ForbiddenItems.taintShovel))).setPages(new ResearchPage[] { new ResearchPage("forbidden.research_page.TAINTSHOVEL.1"), new ResearchPage((InfusionRecipe) recipes.get("TaintShovel")), new ResearchPage("forbidden.research_page.TAINTSHOVEL.2") }).setParents(new String[] { "THAUMIUM", "INFUSION", "ETHEREALBLOOM" }).setConcealed().registerResearchItem();
        (new DarkResearchItem("TAINTPICK", "FORBIDDEN", (new AspectList()).add(Aspect.TOOL, 2).add(Aspect.TAINT, 4).add(Aspect.ENTROPY, 3), -10, 1, 2, new ItemStack(ForbiddenItems.taintPickaxe))).setPages(new ResearchPage[] { new ResearchPage("forbidden.research_page.TAINTPICK.1"), new ResearchPage((InfusionRecipe) recipes.get("TaintPick")) }).setParents(new String[] { "THAUMIUM", "INFUSION" }).setConcealed().registerResearchItem();
        (new DarkResearchItem("ROD_tainted", "FORBIDDEN", (new AspectList()).add(Aspect.MAGIC, 4).add(Aspect.TAINT, 5).add(Aspect.TOOL, 2), -8, 3, 3, new ItemStack(ForbiddenItems.wandCore, 1, 0))).setPages(new ResearchPage[] { new ResearchPage("forbidden.research_page.ROD_tainted.1"), new ResearchPage((InfusionRecipe) recipes.get("WandRodTainted")) }).setParents(new String[] { "ROD_silverwood", "TAINTSHOVEL", "INFUSION" }).setConcealed().registerResearchItem();
        (new DarkResearchItem("TAINTTREE", "FORBIDDEN", (new AspectList()).add(Aspect.TREE, 4).add(Aspect.TAINT, 6).add(Aspect.POISON, 2).add(Aspect.PLANT, 3), -11, 3, 3, new ItemStack(ForbiddenBlocks.taintSapling))).setPages(new ResearchPage[] { new ResearchPage("forbidden.research_page.TAINTTREE.1"), new ResearchPage((CrucibleRecipe) recipes.get("TaintTree")), new ResearchPage((IRecipe) recipes.get("TaintPlank")), new ResearchPage(new ItemStack(ForbiddenBlocks.taintLog, 1, 0)) }).setParents(new String[]{"THAUMIUM", "INFUSION", "ETHEREALBLOOM"}).setConcealed().registerResearchItem();
        ThaumcraftApi.addWarpToResearch("TAINTTREE", 1);
        ThaumcraftApi.addWarpToItem(new ItemStack(ForbiddenBlocks.taintSapling, 1, 0), 1);
        (new DarkResearchItem("TAINTSTONE", "FORBIDDEN", (new AspectList()).add(Aspect.EARTH, 4).add(Aspect.TAINT, 6).add(Aspect.ORDER, 2), -10, 3, 2, new ItemStack(ForbiddenBlocks.taintStone, 1, 1))).setPages(new ResearchPage[]{new ResearchPage("forbidden.research_page.TAINTSTONE.1"), new ResearchPage((IArcaneRecipe) recipes.get("TaintStone")), new ResearchPage((IRecipe) recipes.get("TaintBrick"))}).setParents(new String[]{"THAUMIUM", "INFUSION", "ETHEREALBLOOM"}).setConcealed().registerResearchItem();
        ThaumcraftApi.addWarpToResearch("ROD_tainted", 2);
    }
}