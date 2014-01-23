package com.spiteful.forbidden;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Level;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.FMLLog;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.ItemApi;

public class Config
{
	public static HashMap<String, Aspect> spawnerMobs = new HashMap<String, Aspect>();
	
	public static int shardID;
	public static int skullAxeID;
	public static int arcaneCakeItemID;
	public static int taintShovelID;
	public static int wandCoreID;
	public static int resourceID;
	public static int taintPickaxeID;
	public static int mobCrystalID;
	public static int forkID;
	public static int morphPickaxeID;
	public static int morphSwordID;
	public static int morphShovelID;
	public static int morphAxeID;

	public static int arcaneCakeBlockID;
	public static int blackFlowerBlockID;
	public static int wrathCageID;
	
	public static int clusterEnchID;
	public static int pigBaneEnchID;
	public static int greedyEnchID;
	public static int consumingEnchID;
	public static int educationalEnchID;

	public static int thaumcraftResourceID;
	public static int thaumcraftTaintBlockID;
	public static int thaumcraftTaintPotionID;
	public static int thaumcraftTaintBiomeID;
	
	public static boolean noLust = false;
	public static boolean silverfishEmeralds = true;
	public static boolean noMeddling = false;
	public static boolean tagResearch = true;
	public static boolean wrathCage = true;
	public static boolean spork = false;
	public static boolean greedyEnch = true;
	
	public static int wrathCost = 5;
	public static int wrathEff = 4;
	
	public static Material taintMaterial;

	public static void configurate(File targ)
	{
		Configuration conf = new Configuration(targ);

		try
		{
			conf.load();
			int idCount = 9342;
			shardID = conf.getItem("Nether Shards", idCount++).getInt();
			skullAxeID = conf.getItem("Axe of the Skulltaker", idCount++).getInt();
			arcaneCakeItemID = conf.getItem("Thaumic Cake", idCount++).getInt();
			taintShovelID = conf.getItem("Shovel of the Purifier", idCount++).getInt();
			wandCoreID = conf.getItem("Wand Cores", idCount++).getInt();
			resourceID = conf.getItem("Various Resources", idCount++).getInt();
			taintPickaxeID = conf.getItem("Pickaxe of Distortion", idCount++).getInt();
			mobCrystalID = conf.getItem("Imprinting Crystal", idCount++).getInt();
			forkID = conf.getItem("Diabolist Fork", idCount++).getInt();
			morphPickaxeID = conf.getItem("Chameleon Pickaxe", idCount++).getInt();
			morphSwordID = conf.getItem("Chameleon Sword", idCount++).getInt();
			morphShovelID = conf.getItem("Chameleon Shovel", idCount++).getInt();
			morphAxeID = conf.getItem("Chameleon Axe", idCount++).getInt();
			
			int blockCount = 3000;
			arcaneCakeBlockID = conf.getBlock("Thaumic Cake", blockCount++).getInt();
			blackFlowerBlockID = conf.getBlock("Umbral Rose", blockCount++).getInt();
			wrathCageID = conf.getBlock("Wrath Cage", blockCount++).getInt();
			
			int enchCount = 66;
			clusterEnchID = conf.get("enchantments", "Fiery Core", enchCount++).getInt();
			pigBaneEnchID = conf.get("enchantments", "Porcivore", enchCount++).getInt();
			greedyEnchID = conf.get("enchantments", "Greedy", enchCount++).getInt();
			consumingEnchID = conf.get("enchantments", "Consuming", enchCount++).getInt();
			educationalEnchID = conf.get("enchantments", "Educational", enchCount++).getInt();
			
			Property nl = conf.get("general", "No Lust", noLust);
			nl.comment = "Enable to remove Luxuria aspect and related items.";
			noLust = nl.getBoolean(false);
			Property sf = conf.get("general", "Silverfish Drop Emerald Nuggets", silverfishEmeralds);
			sf.comment = "Disable to prevent Silverfish from dropping emerald nuggets.";
			silverfishEmeralds = sf.getBoolean(true);
			Property ge = conf.get("general", "Greedy Enchantment", greedyEnch);
			ge.comment = "Disable to remove the recipe and effects of the Greedy enchantment.";
			greedyEnch = ge.getBoolean(true);
			Property nm = conf.get("general", "Don't Be That Guy", noMeddling);
			nm.comment = "Enable to turn the Infernal Furnace and Wand Focus: Nine Hells back to normal.";
			noMeddling = nm.getBoolean(true);
			Property tr = conf.get("general", "Tag Research Items", tagResearch);
			tr.comment = "Disable to get rid of the [FM] tags in the Thaumonomicon.";
			tagResearch = tr.getBoolean(true);
			Property wc = conf.get("general", "Wrath Cage Enabled", wrathCage);
			wc.comment = "Disable if you don't want players using the Wrath Cage.";
			wrathCage = wc.getBoolean(true);
			Property wf = conf.get("general", "Wrath Cage Fuel Cost", wrathCost);
			wf.comment = "Cost of essentia per round of spawns in the Wrath Cage.  Raise to increase essentia costs.  Defaults to 1.  Set to 0 to remove the need to fuel the Wrath Cage.  Setting the cost above 64 is not recommended.";
			wrathCost = wf.getInt(5);
			Property we = conf.get("general", "Wrath Cage Fuel Efficiency", wrathEff);
			we.comment = "Number of spawns a Wrath Cage can get per fuel cost.  Defaults to 5.  Lower to make the cage less efficient and raise to make it more efficient.";
			wrathEff = we.getInt(4);
			if(wrathEff < 0)
				wrathEff = 4;
			Property sd = conf.get("general", "Spork of Doom", spork);
			sd.comment = "What is this?  I don't even...";
			spork = sd.getBoolean(false);
		}
		catch (Exception e)
		{
			FMLLog.log(Level.SEVERE, e, "Forbidden Magic has a problem loading its configuration.");
		}
		finally
		{
			conf.save();
		}
		
		try {
		
			thaumcraftResourceID = ItemApi.getItem("itemResource", 0).itemID;
			thaumcraftTaintBlockID = ItemApi.getBlock("blockTaint", 0).itemID;
			taintMaterial = Block.blocksList[thaumcraftTaintBlockID].blockMaterial;
			
			thaumcraftTaintPotionID = Class.forName("thaumcraft.common.config.Config").getField("potionFluxTaintID").getInt(null);
			thaumcraftTaintBiomeID = Class.forName("thaumcraft.common.config.Config").getField("biomeTaintID").getInt(null);
		}
		catch(Exception e)
		{
			FMLLog.log(Level.SEVERE, e, "Forbidden Magic is having a problem retrieving information from Thaumcraft.");
			e.printStackTrace();
		}
		
	}
	
	public static void spawnilify()
	{
		if(wrathCage){
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
			if(silverfishEmeralds)
				spawnerMobs.put("Silverfish", Aspect.GREED);
			else
				spawnerMobs.put("Silverfish", Aspect.STONE);
			spawnerMobs.put("Blaze", Aspect.FIRE);
			spawnerMobs.put("LavaSlime", Aspect.FIRE);
			spawnerMobs.put("Witch", Aspect.MAGIC);
			spawnerMobs.put("Firebat", Aspect.FIRE);
			spawnerMobs.put("Wisp", Aspect.AURA);
			spawnerMobs.put("ThaumSlime", Aspect.TAINT);
			spawnerMobs.put("BrainyZombie", Aspect.MIND);
			spawnerMobs.put("TaintSpider", Aspect.TAINT);
			spawnerMobs.put("TaintSwarm", Aspect.TAINT);
			spawnerMobs.put("TaintedPig", Aspect.TAINT);
			//spawnerMobs.put("TaintedSheep", Aspect.TAINT);
			spawnerMobs.put("TaintedCow", Aspect.TAINT);
			spawnerMobs.put("TaintedChicken", Aspect.TAINT);
			spawnerMobs.put("TaintedVillager", Aspect.TAINT);
			//spawnerMobs.put("Taintacle", DarkAspects.LUST);
			
		}
	}
}