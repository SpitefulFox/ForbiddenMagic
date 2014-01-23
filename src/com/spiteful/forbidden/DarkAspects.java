package com.spiteful.forbidden;

import com.spiteful.forbidden.items.ForbiddenItems;
import com.spiteful.forbidden.blocks.ForbiddenBlocks;

import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.ItemApi;
import net.minecraft.util.ResourceLocation;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import java.util.Iterator;

public class DarkAspects
{
    public static Aspect NETHER;
    public static Aspect WRATH;
    public static Aspect ENVY;
    public static Aspect GLUTTONY;
    public static Aspect PRIDE;
    public static Aspect LUST;
    public static Aspect SLOTH;

    public static void initAspects()
	{
		NETHER = new Aspect("infernus", 0xff0000, new Aspect[] {Aspect.FIRE, Aspect.MAGIC}, new ResourceLocation("forbidden", "textures/aspects/infernus.png"), 771);
		if(!Config.noLust){
			LUST = new Aspect("luxuria", 0xffc1ce, new Aspect[] {Aspect.FLESH, Aspect.HUNGER}, new ResourceLocation("forbidden", "textures/aspects/luxuria.png"), 1);
		}
		PRIDE = new Aspect("superbia", 0x9639ff, new Aspect[] {Aspect.FLIGHT, Aspect.VOID}, new ResourceLocation("forbidden", "textures/aspects/superbia.png"), 1);
		GLUTTONY = new Aspect("gula", 0xd59c46, new Aspect[] {Aspect.HUNGER, Aspect.HUNGER}, new ResourceLocation("forbidden", "textures/aspects/gula.png"), 1);
		ENVY = new Aspect("invidia", 0x00ba00, new Aspect[] {Aspect.SENSES, Aspect.HUNGER}, new ResourceLocation("forbidden", "textures/aspects/invidia.png"), 1);
		SLOTH = new Aspect("desidia", 0x6e6e6e, new Aspect[] {Aspect.TRAP, Aspect.SOUL}, new ResourceLocation("forbidden", "textures/aspects/desidia.png"), 771);
		WRATH = new Aspect("ira", 0x870404, new Aspect[] {Aspect.WEAPON, Aspect.FIRE}, new ResourceLocation("forbidden", "textures/aspects/ira.png"), 771);
	}
	
	public static void addAspects()
    {
        AspectList list;
        
		list = ThaumcraftApiHelper.getObjectAspects(new ItemStack(Block.netherrack));
		list.add(NETHER, 1);
		ThaumcraftApi.registerObjectTag(Block.netherrack.blockID, -1, list);

		list = ThaumcraftApiHelper.getObjectAspects(new ItemStack(Block.portal));
		list.add(NETHER, 4);
		ThaumcraftApi.registerObjectTag(Block.portal.blockID, -1, list);

		list = ThaumcraftApiHelper.getObjectAspects(new ItemStack(Block.oreNetherQuartz));
		list.add(NETHER, 2);
		ThaumcraftApi.registerObjectTag(Block.oreNetherQuartz.blockID, -1, list);

		list = ThaumcraftApiHelper.getObjectAspects(new ItemStack(Item.netherStar));
		list.add(NETHER, 8).add(PRIDE, 8);
		ThaumcraftApi.registerObjectTag(Item.netherStar.itemID, -1, list);

		list = ThaumcraftApiHelper.getObjectAspects(new ItemStack(Item.netherStalkSeeds));
		list.add(NETHER, 1);
		ThaumcraftApi.registerObjectTag(Item.netherStalkSeeds.itemID, -1, list);

		list = ThaumcraftApiHelper.getObjectAspects(new ItemStack(Item.netherrackBrick));
		list.add(NETHER, 1);
		ThaumcraftApi.registerObjectTag(Item.netherrackBrick.itemID, -1, list);

		list = ThaumcraftApiHelper.getObjectAspects(new ItemStack(Item.ghastTear));
		list.add(NETHER, 4);
		ThaumcraftApi.registerObjectTag(Item.ghastTear.itemID, -1, list);

		list = ThaumcraftApiHelper.getObjectAspects(new ItemStack(Item.skull.itemID, 1, 1));
		list.add(NETHER, 4);
		ThaumcraftApi.registerObjectTag(Item.skull.itemID, 1, list);

		list = ThaumcraftApiHelper.getObjectAspects(new ItemStack(Item.skull.itemID, 1, 4));
		list.add(WRATH, 2);
		ThaumcraftApi.registerObjectTag(Item.skull.itemID, 4, list);

		list = ThaumcraftApiHelper.getObjectAspects(new ItemStack(Item.enderPearl));
		list.add(PRIDE, 1);
		ThaumcraftApi.registerObjectTag(Item.enderPearl.itemID, -1, list);

		list = ThaumcraftApiHelper.getObjectAspects(new ItemStack(Item.rottenFlesh));
		list.add(GLUTTONY, 1);
		ThaumcraftApi.registerObjectTag(Item.rottenFlesh.itemID, -1, list);

		list = ThaumcraftApiHelper.getObjectAspects(new ItemStack(Block.slowSand));
		list.add(NETHER, 1);
		ThaumcraftApi.registerObjectTag(Block.slowSand.blockID, -1, list);

		list = ThaumcraftApiHelper.getObjectAspects(new ItemStack(Block.tnt));
		list.add(WRATH, 2);
		ThaumcraftApi.registerObjectTag(Block.tnt.blockID, -1, list);

		list = ThaumcraftApiHelper.getObjectAspects(new ItemStack(Item.helmetGold));
		list.add(PRIDE, 2);
		ThaumcraftApi.registerObjectTag(Item.helmetGold.itemID, -1, list);

		list = ThaumcraftApiHelper.getObjectAspects(new ItemStack(Item.plateGold));
		list.add(PRIDE, 1);
		ThaumcraftApi.registerObjectTag(Item.plateGold.itemID, -1, list);

		list = ThaumcraftApiHelper.getObjectAspects(new ItemStack(Item.legsGold));
		list.add(PRIDE, 1);
		ThaumcraftApi.registerObjectTag(Item.legsGold.itemID, -1, list);

		list = ThaumcraftApiHelper.getObjectAspects(new ItemStack(Item.bootsGold));
		list.add(PRIDE, 1);
		ThaumcraftApi.registerObjectTag(Item.bootsGold.itemID, -1, list);

		list = ThaumcraftApiHelper.getObjectAspects(new ItemStack(Item.swordGold));
		if(list != null)
			list.add(PRIDE, 2);
		ThaumcraftApi.registerObjectTag(Item.swordGold.itemID, -1, list);

		list = ThaumcraftApiHelper.getObjectAspects(new ItemStack(Item.bed));
		list.add(SLOTH, 2);
		ThaumcraftApi.registerObjectTag(Item.bed.itemID, -1, list);

		list = ThaumcraftApiHelper.getObjectAspects(new ItemStack(Item.eyeOfEnder));
		list.add(ENVY, 4);
		ThaumcraftApi.registerObjectTag(Item.eyeOfEnder.itemID, -1, list);

		list = ThaumcraftApiHelper.getObjectAspects(new ItemStack(Item.cake));
		list.add(GLUTTONY, 7);
		ThaumcraftApi.registerObjectTag(Item.cake.itemID, -1, list);

		list = ThaumcraftApiHelper.getObjectAspects(new ItemStack(Item.cookie));
		list.add(GLUTTONY, 1);
		ThaumcraftApi.registerObjectTag(Item.cookie.itemID, -1, list);

		Iterator mobCount = ThaumcraftApi.scanEntities.iterator();

		while (mobCount.hasNext())
		{
			ThaumcraftApi.EntityTags mobTag = (ThaumcraftApi.EntityTags)mobCount.next();

			if (mobTag.entityName.equals("BrainyZombie"))
			{
				mobTag.aspects.add(WRATH, 3);
			}

			else if (mobTag.entityName.equals("GiantBrainyZombie"))
			{
				mobTag.aspects.add(WRATH, 4);
			}

			else if (mobTag.entityName.equals("Firebat"))
			{
				mobTag.aspects.add(WRATH, 1).add(NETHER, 2);
			}

			else if (mobTag.entityName.equals("Skeleton") && mobTag.nbts.length > 0)
			{
				mobTag.aspects.add(NETHER, 3);
			}
			
			else if (mobTag.entityName.equals("Creeper"))
			{
				if(mobTag.nbts.length > 0)
					mobTag.aspects.add(WRATH, 4);
				else
					mobTag.aspects.add(WRATH, 2);
			}

			else if (mobTag.entityName.equals("WitherBoss"))
			{
				mobTag.aspects.add(NETHER, 7).add(WRATH, 7);
			}

			else if (mobTag.entityName.equals("Ozelot"))
			{
				mobTag.aspects.add(SLOTH, 3);
			}

			else if (mobTag.entityName.equals("Enderman"))
			{
				mobTag.aspects.add(PRIDE, 4).add(ENVY, 4);
			}

			else if (mobTag.entityName.equals("Ghast"))
			{
				mobTag.aspects.add(NETHER, 3).add(WRATH, 3);
			}
			
			else if (mobTag.entityName.equals("PigZombie"))
			{
				mobTag.aspects.add(WRATH, 6);
			}

			else if (mobTag.entityName.equals("EnderDragon"))
			{
				mobTag.aspects.add(PRIDE, 10);
			}

			else if (!Config.noLust && mobTag.entityName.equals("Taintacle"))
			{
				mobTag.aspects.add(LUST, 3);
			}

			else if (mobTag.entityName.equals("Pig"))
			{
				mobTag.aspects.add(GLUTTONY, 3);
			}
			
			else if(mobTag.entityName.equals("LavaSlime"))
			{
				mobTag.aspects.add(NETHER, 1);
			}
			
			else if(!Config.noLust && mobTag.entityName.equals("TwilightForest.Forest Bunny"))
			{
				mobTag.aspects.add(LUST, 5);
			}
			
			else if(mobTag.entityName.equals("TwilightForest.Mini Ghast"))
			{
				mobTag.aspects.add(NETHER, 1).add(WRATH, 1);
			}
			
			else if(mobTag.entityName.equals("TwilightForest.Tower Boss"))
			{
				mobTag.aspects.add(NETHER, 10).add(WRATH, 10);
			}
			
			else if(mobTag.entityName.equals("TwilightForest.Tower Ghast"))
			{
				mobTag.aspects.add(NETHER, 3).add(WRATH, 3);
			}
			
			else if(mobTag.entityName.equals("TwilightForest.Twilight Lich"))
			{
				mobTag.aspects.add(PRIDE, 10);
			}
			
			else if(!Config.noLust && mobTag.entityName.equals("TwilightForest.Pinch Beetle"))
			{
				mobTag.aspects.add(LUST, 3);
			}
			
			else if(mobTag.entityName.equals("TwilightForest.King Spider"))
			{
				mobTag.aspects.add(PRIDE, 4);
			}
		}

		ThaumcraftApi.registerObjectTag(ForbiddenItems.deadlyShards.itemID, 0, (new AspectList()).add(NETHER, 1).add(WRATH, 2).add(Aspect.CRYSTAL, 1));
		ThaumcraftApi.registerObjectTag(ForbiddenItems.deadlyShards.itemID, 1, (new AspectList()).add(NETHER, 1).add(ENVY, 2).add(Aspect.CRYSTAL, 1));
		ThaumcraftApi.registerObjectTag(ForbiddenItems.deadlyShards.itemID, 2, (new AspectList()).add(Aspect.TAINT, 3).add(Aspect.CRYSTAL, 1));
		ThaumcraftApi.registerObjectTag(ForbiddenItems.deadlyShards.itemID, 3, (new AspectList()).add(NETHER, 1).add(PRIDE, 2).add(Aspect.CRYSTAL, 1));
		if(!Config.noLust)
			ThaumcraftApi.registerObjectTag(ForbiddenItems.deadlyShards.itemID, 4, (new AspectList()).add(NETHER, 1).add(LUST, 2).add(Aspect.CRYSTAL, 1));
		else
			ThaumcraftApi.registerObjectTag(ForbiddenItems.deadlyShards.itemID, 4, (new AspectList()).add(Aspect.VOID, 1));
		ThaumcraftApi.registerObjectTag(ForbiddenItems.deadlyShards.itemID, 5, (new AspectList()).add(NETHER, 1).add(SLOTH, 2).add(Aspect.CRYSTAL, 1));
		ThaumcraftApi.registerObjectTag(ForbiddenItems.deadlyShards.itemID, 6, (new AspectList()).add(NETHER, 1).add(Aspect.GREED, 2).add(Aspect.CRYSTAL, 1));
		ThaumcraftApi.registerObjectTag(ForbiddenItems.resource.itemID, 0, (new AspectList()).add(Aspect.CRYSTAL, 1));
		ThaumcraftApi.registerObjectTag(ForbiddenItems.resource.itemID, 1, (new AspectList()).add(Aspect.DARKNESS, 1));
		ThaumcraftApi.registerObjectTag(ForbiddenBlocks.blackFlower.blockID, -1, (new AspectList()).add(Aspect.PLANT, 1).add(Aspect.LIFE, 1).add(Aspect.SENSES, 1).add(Aspect.DARKNESS, 1));
    }
}