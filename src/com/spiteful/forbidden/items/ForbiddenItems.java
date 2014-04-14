package com.spiteful.forbidden.items;

import com.spiteful.forbidden.*;
import com.spiteful.forbidden.items.wands.*;

import cpw.mods.fml.common.registry.GameRegistry;
import java.util.Arrays;
import net.minecraft.block.Block;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraft.util.ResourceLocation;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.wands.WandCap;
import thaumcraft.api.wands.WandRod;
import thaumcraft.api.wands.StaffRod;

public class ForbiddenItems
{
	public static Item deadlyShards;
	public static Item gluttonyShard;
	public static Item skullAxe;
	public static Item arcaneCakeItem;
	public static Item taintShovel;
	public static Item wandCore;
	public static Item wandCap;
	public static Item resource;
	public static Item taintPickaxe;
	public static Item mobCrystal;
	public static Item fork;
	public static Item morphPickaxe;
	public static Item morphSword;
	public static Item morphShovel;
	public static Item morphAxe;
	public static Item crystalwell;
	public static Item ridingCrop;
	
	public static Item bloodwell;
	public static Item bloodOrb;
	
	public static WandRod WAND_ROD_TAINTED;
	public static WandRod WAND_ROD_INFERNAL;
	public static WandRod WAND_ROD_NEUTRONIUM;
	public static WandRod WAND_ROD_SOUL;
	public static WandRod WAND_ROD_BLOOD;
	public static WandRod WAND_ROD_WITCHWOOD;
	public static WandRod WAND_ROD_TOTEM;
	public static WandRod WAND_ROD_LIVINGWOOD;
	public static StaffRod STAFF_ROD_BLOOD;
	public static StaffRod STAFF_ROD_NEUTRONIUM;
	public static StaffRod STAFF_ROD_WITCHWOOD;
	public static WandCap WAND_CAP_ORICHALCUM;
	public static WandCap WAND_CAP_ALCHEMICAL;
	public static WandCap WAND_CAP_VINTEUM;
	public static WandCap WAND_CAP_SOUL;
	public static WandCap WAND_CAP_MANASTEEL;

	public static void addItems()
	{
		deadlyShards = new ItemDeadlyShard(Config.shardID).setUnlocalizedName("NetherShard");
		GameRegistry.registerItem(deadlyShards, "NetherShard");
		OreDictionary.registerOre("shardNether", new ItemStack(deadlyShards, 1, 0));
		OreDictionary.registerOre("shardNether", new ItemStack(deadlyShards, 1, 1));
		OreDictionary.registerOre("shardNether", new ItemStack(deadlyShards, 1, 3));
		if(!Config.noLust)
			OreDictionary.registerOre("shardNether", new ItemStack(deadlyShards, 1, 4));
		OreDictionary.registerOre("shardNether", new ItemStack(deadlyShards, 1, 5));
		OreDictionary.registerOre("shardNether", new ItemStack(deadlyShards, 1, 6));
		
		gluttonyShard = new ItemGluttonyShard(Config.gluttonyShardID).setUnlocalizedName("GluttonyShard");
		GameRegistry.registerItem(gluttonyShard, "GluttonyShard");
		OreDictionary.registerOre("shardNether", new ItemStack(gluttonyShard, 1, 0));
		
		taintShovel = new ItemTaintShovel(Config.taintShovelID, ThaumcraftApi.toolMatElemental).setUnlocalizedName("TaintShovel");
		GameRegistry.registerItem(taintShovel, "TaintShovel");
		MinecraftForge.setToolClass(taintShovel, "shovel", 3);
		
		taintPickaxe = new ItemTaintPickaxe(Config.taintPickaxeID, ThaumcraftApi.toolMatElemental).setUnlocalizedName("TaintPickaxe");
		GameRegistry.registerItem(taintPickaxe, "TaintPickaxe");
		MinecraftForge.setToolClass(taintPickaxe, "pickaxe", 5);
		
		arcaneCakeItem = new ItemArcaneCake(Config.arcaneCakeItemID).setUnlocalizedName("ArcaneCake");
		GameRegistry.registerItem(arcaneCakeItem, "ArcaneCakeItem");
		
		skullAxe = new ItemSkullAxe(Config.skullAxeID, ThaumcraftApi.toolMatElemental).setUnlocalizedName("SkullAxe");
		GameRegistry.registerItem(skullAxe, "SkullAxe");
		
		morphPickaxe = new ItemMorphPickaxe(Config.morphPickaxeID, ThaumcraftApi.toolMatElemental).setUnlocalizedName("MorphPickaxe");
		GameRegistry.registerItem(morphPickaxe, "MorphPickaxe");
		MinecraftForge.setToolClass(morphPickaxe, "pickaxe", 4);
		
		morphSword = new ItemMorphSword(Config.morphSwordID, ThaumcraftApi.toolMatElemental).setUnlocalizedName("MorphSword");
		GameRegistry.registerItem(morphSword, "MorphSword");
		
		morphShovel = new ItemMorphShovel(Config.morphShovelID, ThaumcraftApi.toolMatElemental).setUnlocalizedName("MorphShovel");
		GameRegistry.registerItem(morphShovel, "MorphShovel");
		MinecraftForge.setToolClass(morphShovel, "shovel", 4);
		
		morphAxe = new ItemMorphAxe(Config.morphAxeID, ThaumcraftApi.toolMatElemental).setUnlocalizedName("MorphAxe");
		GameRegistry.registerItem(morphAxe, "MorphAxe");
		MinecraftForge.setToolClass(morphAxe, "axe", 4);
		
		if(Config.wrathCage)
		{
			mobCrystal = new ItemMobCrystal(Config.mobCrystalID).setUnlocalizedName("MobCrystal");
			GameRegistry.registerItem(mobCrystal, "MobCrystal");
		}
		
		fork = new ItemDiabolistFork(Config.forkID, ThaumcraftApi.toolMatThaumium).setUnlocalizedName("DiabolistFork");
		if(Config.spork)
			fork.setUnlocalizedName("DiabolistSpork");
		GameRegistry.registerItem(fork, "DiabolistFork");
		
		wandCore = new ItemWandCores(Config.wandCoreID).setUnlocalizedName("WandCores");
		GameRegistry.registerItem(wandCore, "WandCores");
		wandCap = new ItemWandCaps(Config.wandCapID).setUnlocalizedName("WandCaps");
		GameRegistry.registerItem(wandCap, "WandCaps");
		WAND_ROD_TAINTED = new WandRod("tainted", 150, new ItemStack(wandCore, 1, 0), 12, new TaintedWandUpdate(), new ResourceLocation("forbidden", "textures/models/wand_rod_tainted.png"));
		WAND_ROD_INFERNAL = new WandRod("infernal", 150, new ItemStack(wandCore, 1, 1), 12, new InfernalWandUpdate(), new ResourceLocation("forbidden", "textures/models/wand_rod_infernal.png"));
		WAND_ROD_NEUTRONIUM = new WandRod("neutronium", 9001, new ItemStack(Block.bedrock, 1), 1000, new CreativeWandUpdate(), new ResourceLocation("forbidden", "textures/models/wand_rod_neutronium.png"));
		WAND_ROD_NEUTRONIUM.setGlowing(true);
		WAND_ROD_SOUL = new WandRod("soul", 100, new ItemStack(wandCore, 1, 2), 12, new SoulWandUpdate(), new ResourceLocation("forbidden", "textures/models/wand_rod_soul.png"));
		WAND_ROD_SOUL.setGlowing(true);
		WAND_ROD_BLOOD = new WandRod("blood", 100, new ItemStack(wandCore, 1, 3), 12, new BloodWandUpdate(), new ResourceLocation("forbidden", "textures/models/wand_rod_blood.png"));
		WAND_ROD_WITCHWOOD = new WandRod("witchwood", 100, new ItemStack(wandCore, 1, 4), 12, new ManaWandUpdate(), new ResourceLocation("forbidden", "textures/models/wand_rod_witchwood.png"));
		WAND_ROD_TOTEM = new WandRod("totem", 50, new ItemStack(wandCore, 1, 5), 4, new TotemWandUpdate(), new ResourceLocation("forbidden", "textures/models/wand_rod_totem.png"));
		WAND_ROD_LIVINGWOOD = new WandRod("livingwood", 100, new ItemStack(wandCore, 1, 7), 12, new YandereWandUpdate(), new ResourceLocation("forbidden", "textures/models/wand_rod_livingwood.png"));
		STAFF_ROD_BLOOD = new StaffRod("blood", 50, new ItemStack(wandCore, 1, 9), 24, new BloodStaffUpdate(), new ResourceLocation("forbidden", "textures/models/wand_rod_blood.png"));
		STAFF_ROD_NEUTRONIUM = new StaffRod("neutronium", 9002, new ItemStack(Block.bedrock, 1), 1000, new CreativeWandUpdate(), new ResourceLocation("forbidden", "textures/models/wand_rod_neutronium.png"));
		STAFF_ROD_NEUTRONIUM.setGlowing(true);
		STAFF_ROD_WITCHWOOD = new StaffRod("witchwood", 50, new ItemStack(wandCore, 1, 10), 24, new ManaStaffUpdate(), new ResourceLocation("forbidden", "textures/models/wand_rod_witchwood.png"));
		WAND_CAP_ORICHALCUM = new DarkWandCap("orichalcum", 0.0F, new ItemStack(Block.commandBlock, 1), 1000, new ResourceLocation("forbidden", "textures/models/wand_cap_orichalcum.png"));
		WAND_CAP_ALCHEMICAL = new DarkWandCap("alchemical", 0.9F, Arrays.asList(new Aspect[]{Aspect.WATER}), 0.8F, new ItemStack(wandCap, 1, 0), 7, new ResourceLocation("forbidden", "textures/models/wand_cap_alchemical.png"));
		WAND_CAP_VINTEUM = new DarkWandCap("vinteum", 0.9F, new ItemStack(wandCap, 1, 1), 6, new ResourceLocation("forbidden", "textures/models/wand_cap_vinteum.png"));
		WAND_CAP_SOUL = new DarkWandCap("soul", 0.9F, Arrays.asList(new Aspect[]{Aspect.ENTROPY}), 0.8F, new ItemStack(wandCap, 1, 2), 7, new ResourceLocation("forbidden", "textures/models/wand_cap_soul.png"));
		WAND_CAP_MANASTEEL = new DarkWandCap("manasteel", 0.9F, new ItemStack(wandCap, 1, 3), 6, new ResourceLocation("forbidden", "textures/models/wand_cap_manasteel.png"));
		
		resource = new ItemResource(Config.resourceID).setUnlocalizedName("FMResource");
		GameRegistry.registerItem(resource, "FMResource");
		OreDictionary.registerOre("nuggetEmerald", new ItemStack(resource, 1, 0));
		OreDictionary.registerOre("dyeBlack", new ItemStack(resource, 1, 1));
		
		if(Compat.bm)
		{
			try
			{
				bloodwell = new ItemBloodwell(Config.bloodwellID).setUnlocalizedName("Bloodwell");
				GameRegistry.registerItem(bloodwell, "Bloodwell");
				
				bloodOrb = new ItemDivineOrb(Config.bloodOrbID).setUnlocalizedName("DivineOrb");
				GameRegistry.registerItem(bloodOrb, "DivineOrb");
			}
			catch(Throwable e)
			{
				e.printStackTrace();
			}
		}
		
		crystalwell = new ItemCrystalwell(Config.crystalwellID).setUnlocalizedName("Crystalwell");
		GameRegistry.registerItem(crystalwell, "Crystalwell");
		
		ridingCrop = new ItemRidingCrop(Config.ridingCropID, EnumToolMaterial.WOOD).setUnlocalizedName("RidingCrop");
		GameRegistry.registerItem(ridingCrop, "RidingCrop");
		
	}
}