package com.spiteful.forbidden.enchantments;

import com.spiteful.forbidden.*;

import net.minecraft.enchantment.Enchantment;
import net.minecraftforge.common.MinecraftForge;
//import vazkii.tinkerer.common.enchantment.core.*;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;

public class DarkEnchantments
{
	public static Enchantment cluster;
	public static Enchantment pigBane;
	public static Enchantment greedy;
	public static Enchantment consuming;
	
	public static void hex()
	{
		cluster = new EnchantmentCluster(Config.clusterEnchID);
		pigBane = new EnchantmentPigBane(Config.pigBaneEnchID);
		greedy = new EnchantmentGreedy(Config.greedyEnchID);
		consuming = new EnchantmentConsuming(Config.consumingEnchID);
		
		//EnchantmentManager.registerExponentialCostData(pigBane, "forbidden:textures/misc/pigbane.png", false, (new AspectList()).add(Aspect.FIRE, 1).add(Aspect.ENTROPY, 1), "PIGBANE");
		//EnchantmentManager.registerExponentialCostData(cluster, "forbidden:textures/misc/lucrative.png", false, (new AspectList()).add(Aspect.FIRE, 5).add(Aspect.EARTH, 5).add(Aspect.ORDER, 5), "CLUSTER");
	}

}