package com.spiteful.forbidden;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;
import thaumcraft.api.wands.WandTriggerRegistry;

import com.spiteful.forbidden.blocks.ForbiddenBlocks;
import com.spiteful.forbidden.enchantments.DarkEnchantments;
import com.spiteful.forbidden.items.ForbiddenItems;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(
	modid = "ForbiddenMagic",
	name = "Forbidden Magic",
	dependencies = "required-after:Thaumcraft;after:ThaumicTinkerer;after:AWWayofTime@[v1.0.1d,)"
)
public class Forbidden
{
	@Instance("ForbiddenMagic")
	public static Forbidden instance;
	public static ForbiddenTab tab = new ForbiddenTab("forbidden");
	public static ForbiddenTab crysTab;
	@SidedProxy(
		clientSide = "com.spiteful.forbidden.client.ClientProxy",
		serverSide = "com.spiteful.forbidden.CommonProxy"
	)
	public static CommonProxy proxy;
	public static WandOverlord wandLord;

	@EventHandler
	public void prelude(FMLPreInitializationEvent event)
	{
		instance = this;
		Config.configurate(event.getSuggestedConfigurationFile());
		if(Config.wrathCage)
			crysTab = new ForbiddenTab("mobcrystal", true);
		Compat.initiate();
		DarkAspects.initAspects();
		Config.spawnilify();
		ForbiddenItems.addItems();
		ForbiddenBlocks.addBlocks();
		DarkEnchantments.hex();
		proxy.registerRenderInfo();
	}

	@EventHandler
	public void crescendo(FMLInitializationEvent event)
	{
		MinecraftForge.EVENT_BUS.register(new FMEventHandler());
	}

	@EventHandler
	public void outro(FMLPostInitializationEvent event)
	{
		DarkAspects.addAspects();
		ForbiddenRecipes.addRecipes();
		ForbiddenResearch.addResearch();
		Compat.compatify();
		
		wandLord = new WandOverlord();
		WandTriggerRegistry.registerWandBlockTrigger(wandLord, 1, Blocks.obsidian, OreDictionary.WILDCARD_VALUE);
		WandTriggerRegistry.registerWandBlockTrigger(wandLord, 1, Blocks.netherrack, OreDictionary.WILDCARD_VALUE);
	}
}