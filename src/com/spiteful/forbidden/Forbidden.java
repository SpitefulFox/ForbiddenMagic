package com.spiteful.forbidden;

import com.spiteful.forbidden.items.ForbiddenItems;
import com.spiteful.forbidden.blocks.ForbiddenBlocks;
import com.spiteful.forbidden.enchantments.DarkEnchantments;
import com.spiteful.forbidden.client.ClientProxy;

import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.ServerStarting;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLInterModComms.IMCMessage;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import thaumcraft.api.aspects.Aspect;
import java.util.logging.Level;

@Mod(
	modid = "ForbiddenMagic",
	name = "Forbidden Magic",
	version = "0.31",
	dependencies = "required-after:Thaumcraft;after:Natura;after:ThaumicTinkerer;after:ThaumicExploration"
)
@NetworkMod(
	clientSideRequired = true,
	serverSideRequired = false
	//channels = {"forbiddenmagic"},
	//packetHandler = PacketHandler.class
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

	@EventHandler
	public void prelude(FMLPreInitializationEvent event)
	{
		instance = this;
		Config.configurate(event.getSuggestedConfigurationFile());
		if(!Config.noHell)
			crysTab = new ForbiddenTab("mobcrystal", true);
		Compat.initiate();
		DarkAspects.initAspects();
		if(!Config.noHell)
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
	}
}