package fox.spiteful.forbidden;

import java.io.File;
import java.util.HashMap;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;

import org.apache.logging.log4j.Level;

import thaumcraft.api.ItemApi;
import thaumcraft.api.aspects.Aspect;

public class Config {
    public static HashMap<String, Aspect> spawnerMobs = new HashMap<String, Aspect>();

    public static int clusterEnchID;
    public static int wrathEnchID;
    public static int greedyEnchID;
    public static int consumingEnchID;
    public static int educationalEnchID;
    public static int corruptingEnchID;
    public static int voidEnchID;
    public static int impactEnchID;

    public static int hellfireUpgradeID;
    public static int pandemoniumUpgradeID;

    public static ItemStack thaumcraftResource;
    public static ItemStack thaumcraftTaintBlock;
    public static ItemStack thaumcraftOre;
    public static ItemStack thaumcraftShard;

    public static boolean noLust = false;
    public static boolean silverfishEmeralds = true;
    public static boolean tagResearch = true;
    public static boolean wrathCage = true;
    public static boolean spork = false;
    public static boolean greedyEnch = true;
    public static boolean emeraldTrans = true;
    public static boolean wrathCrazy = false;
    public static byte gluttony = 0;

    public static boolean crossMod = true;
    public static boolean crossWand = true;
    public static boolean botan = true;
    public static boolean am2 = false;
    public static boolean tt = true;
    public static boolean tc = true;
    public static boolean special = true;
    public static boolean twilight = true;

    public static int wrathCost = 5;
    public static int wrathEff = 4;

    public static Material taintMaterial;

    public static void configurate(File targ) {
        Configuration conf = new Configuration(targ);

        try {
            conf.load();

            int enchCount = 66;
            clusterEnchID = conf.get("enchantments", "Fiery Core", enchCount++).getInt();
            wrathEnchID = conf.get("enchantments", "Wrath", enchCount++).getInt();
            greedyEnchID = conf.get("enchantments", "Capitalist", enchCount++).getInt();
            consumingEnchID = conf.get("enchantments", "Consuming", enchCount++).getInt();
            educationalEnchID = conf.get("enchantments", "Educational", enchCount++).getInt();
            corruptingEnchID = conf.get("enchantments", "Corrupting", enchCount++).getInt();
            voidEnchID = conf.get("enchantments", "Voidtouched", enchCount++).getInt();
            impactEnchID = conf.get("enchantments", "Impact", enchCount++).getInt();

            int upgradeCount = 42;
            hellfireUpgradeID = conf.get("focus upgrades", "Hellfire", upgradeCount).getInt(upgradeCount++);
            pandemoniumUpgradeID = conf.get("focus upgrades", "Pandemonium", upgradeCount).getInt(upgradeCount++);

            noLust = conf.get("general", "No Lust", noLust, "Enable to remove Luxuria aspect and related items.").getBoolean(false);
            silverfishEmeralds = conf.get("general", "Silverfish Drop Emerald Nuggets", silverfishEmeralds, "Disable to prevent Silverfish from dropping emerald nuggets.").getBoolean(true);
            greedyEnch = conf.get("general", "Capitalist Enchantment", greedyEnch, "Disable to remove the recipe and effects of the Capitalist enchantment.").getBoolean(true);
            emeraldTrans = conf.get("general", "Emerald Transmutation", emeraldTrans, "Disable to remove the Emerald Transmutation research and recipe.").getBoolean(true);
            tagResearch = conf.get("general", "Tag Research Items", tagResearch, "Disable to get rid of the [FM] tags in the Thaumonomicon.").getBoolean(true);
            wrathCage = conf.get("general", "Wrath Cage Enabled", wrathCage, "Disable if you don't want players using the Wrath Cage.").getBoolean(true);
            wrathCost = conf.get("general", "Wrath Cage Fuel Cost", wrathCost, "Cost of essentia per round of spawns in the Wrath Cage.  Raise to increase essentia costs.  Defaults to 5.  Set to 0 to remove the need to fuel the Wrath Cage.  Setting the cost above 64 is not recommended.").getInt(5);
            wrathEff = conf.get("general", "Wrath Cage Fuel Efficiency", wrathEff, "Number of spawns a Wrath Cage can get per fuel cost.  Defaults to 4.  Lower to make the cage less efficient and raise to make it more efficient.").getInt(4);
            if (wrathEff < 0)
                wrathEff = 4;
            wrathCrazy = conf.get("general", "Wrath Cage Cries Havoc", wrathCrazy, "Enable to let the Wrath Cage imprint on ANY non-boss mob.  May break your game or make your game Awesome.").getBoolean(false);
            spork = conf.get("silly", "Spork of Doom", spork, "What is this?  I don't even...").getBoolean(false);
            gluttony = (byte)conf.get("general", "Gluttony", gluttony, "Whether gluttony research is enabled. 0 = Enabled, 1 = Disabled, 2 = Hardcore").getInt(0);
            if(gluttony < 0 || gluttony > 2)
                gluttony = 0;

            crossMod = conf.get("compatibility", "Cross-Mod Interaction", crossMod, "Disable to keep mods segregated.").getBoolean(true);
            crossWand = conf.get("compatibility", "Conversion Wands", crossWand, "Disable to remove all conversion wands.").getBoolean(true);
            botan = conf.get("compatibility", "Botania Interaction", botan).getBoolean(true);
            am2 = conf.get("compatibility", "Ars Magica 2 Interaction", am2, "AM2 interaction is unsupported. Enable at your own risk.").getBoolean(false);
            tt = conf.get("compatibility", "Thaumic Tinkerer Interaction", tt).getBoolean(true);
            tc = conf.get("compatibility", "Tinkers Construct Interaction", tc).getBoolean(true);
            special = conf.get("compatibility", "Special Mobs Interaction", special).getBoolean(true);
            twilight = conf.get("compatibility", "Twilight Forest Interaction", twilight).getBoolean(true);
        } catch (Exception e) {
            LogHandler.log(Level.ERROR, e, "Had a problem loading its configuration.");
        } finally {
            conf.save();
        }

        try {
            thaumcraftResource = ItemApi.getItem("itemResource", 0);
            thaumcraftShard = ItemApi.getItem("itemShard", 0);
            thaumcraftTaintBlock = ItemApi.getBlock("blockTaint", 0);
            thaumcraftOre = ItemApi.getBlock("blockCustomOre", 0);
            taintMaterial = Block.getBlockFromItem(thaumcraftTaintBlock.getItem()).getMaterial();
        } catch (Exception e) {
            LogHandler.log(Level.ERROR, e, "There was problem when retrieving information from Thaumcraft.");
            e.printStackTrace();
        }
    }

    public static void spawnilify() {
        if (wrathCage) {
            spawnerMobs.put("Zombie", Aspect.FLESH);
            spawnerMobs.put("Skeleton", Aspect.DEATH);
            spawnerMobs.put("Creeper", Aspect.FIRE);
            spawnerMobs.put("EntityHorse", Aspect.BEAST);
            spawnerMobs.put("Pig", Aspect.BEAST);
            spawnerMobs.put("Sheep", Aspect.CLOTH);
            spawnerMobs.put("Cow", Aspect.BEAST);
            spawnerMobs.put("MushroomCow", Aspect.PLANT);
            spawnerMobs.put("Ozelot", Aspect.BEAST);
            spawnerMobs.put("Chicken", Aspect.FLIGHT);
            spawnerMobs.put("Squid", Aspect.SENSES);
            spawnerMobs.put("Wolf", Aspect.BEAST);
            spawnerMobs.put("Bat", Aspect.FLIGHT);
            spawnerMobs.put("Spider", Aspect.CLOTH);
            spawnerMobs.put("Slime", Aspect.SLIME);
            spawnerMobs.put("Ghast", DarkAspects.NETHER);
            spawnerMobs.put("PigZombie", Aspect.GREED);
            spawnerMobs.put("Enderman", Aspect.ELDRITCH);
            spawnerMobs.put("CaveSpider", Aspect.POISON);
            if (silverfishEmeralds)
                spawnerMobs.put("Silverfish", Aspect.GREED);
            else
                spawnerMobs.put("Silverfish", Aspect.BEAST);
            spawnerMobs.put("Blaze", Aspect.FIRE);
            spawnerMobs.put("LavaSlime", Aspect.FIRE);
            spawnerMobs.put("Witch", Aspect.MAGIC);
            spawnerMobs.put("Villager", Aspect.GREED);
            spawnerMobs.put("Thaumcraft.Firebat", Aspect.FIRE);
            spawnerMobs.put("Thaumcraft.Wisp", Aspect.AURA);
            spawnerMobs.put("Thaumcraft.ThaumSlime", Aspect.TAINT);
            spawnerMobs.put("Thaumcraft.BrainyZombie", Aspect.MIND);
            spawnerMobs.put("Thaumcraft.GiantBrainyZombie", Aspect.MIND);
            spawnerMobs.put("Thaumcraft.TaintSpider", Aspect.TAINT);
            spawnerMobs.put("Thaumcraft.TaintSwarm", Aspect.TAINT);
            spawnerMobs.put("Thaumcraft.TaintedPig", Aspect.TAINT);
            spawnerMobs.put("Thaumcraft.TaintedSheep", Aspect.TAINT);
            spawnerMobs.put("Thaumcraft.TaintedCow", Aspect.TAINT);
            spawnerMobs.put("Thaumcraft.TaintedChicken", Aspect.TAINT);
            spawnerMobs.put("Thaumcraft.TaintedVillager", Aspect.TAINT);
            spawnerMobs.put("Thaumcraft.CultistKnight", Aspect.ELDRITCH);
            spawnerMobs.put("Thaumcraft.CultistCleric", Aspect.ELDRITCH);
            spawnerMobs.put("Thaumcraft.EldritchCrab", Aspect.ELDRITCH);
            spawnerMobs.put("Thaumcraft.InhabitedZombie", Aspect.ELDRITCH);
            spawnerMobs.put("Thaumcraft.Pech", Aspect.GREED);
            spawnerMobs.put("Thaumcraft.Pech.1", Aspect.GREED);
            spawnerMobs.put("Thaumcraft.Pech.2", Aspect.GREED);
            spawnerMobs.put("Thaumcraft.EldritchGuardian", Aspect.ELDRITCH);
            // spawnerMobs.put("Taintacle", DarkAspects.LUST);
        }
    }
}