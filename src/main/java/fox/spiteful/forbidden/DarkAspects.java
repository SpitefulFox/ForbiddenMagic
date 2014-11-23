package fox.spiteful.forbidden;

import java.util.Iterator;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.oredict.OreDictionary;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;

import fox.spiteful.forbidden.blocks.ForbiddenBlocks;
import fox.spiteful.forbidden.items.ForbiddenItems;

public class DarkAspects {
	public static Aspect NETHER;
	public static Aspect WRATH;
	public static Aspect ENVY;
	public static Aspect GLUTTONY;
	public static Aspect PRIDE;
	public static Aspect LUST;
	public static Aspect SLOTH;

	public static void initAspects() {
		if (!Config.noLust) {
			LUST = new Aspect("luxuria", 0xffc1ce, new Aspect[] { Aspect.FLESH, Aspect.HUNGER }, new ResourceLocation("forbidden", "textures/aspects/luxuria.png"), 1);
		}

		NETHER = new Aspect("infernus", 0xff0000, new Aspect[] { Aspect.FIRE, Aspect.MAGIC }, new ResourceLocation("forbidden", "textures/aspects/infernus.png"), 771);
		PRIDE = new Aspect("superbia", 0x9639ff, new Aspect[] { Aspect.FLIGHT, Aspect.VOID }, new ResourceLocation("forbidden", "textures/aspects/superbia.png"), 1);
		GLUTTONY = new Aspect("gula", 0xd59c46, new Aspect[] { Aspect.HUNGER, Aspect.VOID }, new ResourceLocation("forbidden", "textures/aspects/gula.png"), 1);
		ENVY = new Aspect("invidia", 0x00ba00, new Aspect[] { Aspect.SENSES, Aspect.HUNGER }, new ResourceLocation("forbidden", "textures/aspects/invidia.png"), 1);
		SLOTH = new Aspect("desidia", 0x6e6e6e, new Aspect[] { Aspect.TRAP, Aspect.SOUL }, new ResourceLocation("forbidden", "textures/aspects/desidia.png"), 771);
		WRATH = new Aspect("ira", 0x870404, new Aspect[] { Aspect.WEAPON, Aspect.FIRE }, new ResourceLocation("forbidden", "textures/aspects/ira.png"), 771);

	}

	private static AspectList getAspectList(ItemStack stack) {
		AspectList list = ThaumcraftApiHelper.getObjectAspects(stack);
		return list != null ? list : new AspectList();
	}

	public static void addAspects() {
		AspectList list;

		list = getAspectList(new ItemStack(Blocks.netherrack));
		list.add(NETHER, 1);
		ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.netherrack, 1, OreDictionary.WILDCARD_VALUE), list);

		list = getAspectList(new ItemStack(Blocks.portal));
		list.add(NETHER, 4);
		ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.portal, 1, OreDictionary.WILDCARD_VALUE), list);

		// list = getAspectList(new ItemStack(Block.oreNetherQuartz));
		// list.add(NETHER, 2);
		// ThaumcraftApi.registerObjectTag(Block.oreNetherQuartz.blockID, -1,
		// list);
		ThaumcraftApi.registerObjectTag("oreQuartz", (new AspectList()).add(Aspect.CRYSTAL, 3).add(NETHER, 2));

		list = getAspectList(new ItemStack(Items.nether_star));
		list.add(NETHER, 8).add(PRIDE, 8);
		ThaumcraftApi.registerObjectTag(new ItemStack(Items.nether_star, 1, 0), list);

		list = getAspectList(new ItemStack(Items.nether_wart));
		list.add(NETHER, 1);
		ThaumcraftApi.registerObjectTag(new ItemStack(Items.nether_wart, 1, 0), list);

		list = getAspectList(new ItemStack(Items.netherbrick));
		list.add(NETHER, 1);
		ThaumcraftApi.registerObjectTag(new ItemStack(Items.netherbrick, 1, OreDictionary.WILDCARD_VALUE), list);

		list = getAspectList(new ItemStack(Blocks.nether_brick));
		list.add(NETHER, 2);
		ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.nether_brick, 1, 0), list);

		list = getAspectList(new ItemStack(Items.ghast_tear));
		list.add(WRATH, 4);
		ThaumcraftApi.registerObjectTag(new ItemStack(Items.ghast_tear, 1, OreDictionary.WILDCARD_VALUE), list);

		list = getAspectList(new ItemStack(Items.skull, 1, 1));
		list.add(NETHER, 4);
		ThaumcraftApi.registerObjectTag(new ItemStack(Items.skull, 1, 1), list);

		list = getAspectList(new ItemStack(Items.skull, 1, 4));
		list.add(WRATH, 2);
		ThaumcraftApi.registerObjectTag(new ItemStack(Items.skull, 1, 4), list);

		// list = getAspectList(new ItemStack(Item.enderPearl));
		// list.add(PRIDE, 1);
		// ThaumcraftApi.registerObjectTag(Item.enderPearl.itemID, -1, list);

		list = getAspectList(new ItemStack(Blocks.tnt));
		list.add(WRATH, 2);
		ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.tnt, 1, 0), list);

		list = getAspectList(new ItemStack(Items.golden_helmet));
		if (list != null) {
			list.add(PRIDE, 2);
			ThaumcraftApi.registerObjectTag(new ItemStack(Items.golden_helmet, 1, OreDictionary.WILDCARD_VALUE), list);
		}

		list = getAspectList(new ItemStack(Items.golden_chestplate));
		if (list != null) {
			list.add(PRIDE, 1);
			ThaumcraftApi.registerObjectTag(new ItemStack(Items.golden_chestplate, 1, OreDictionary.WILDCARD_VALUE), list);
		}

		list = getAspectList(new ItemStack(Items.golden_leggings));
		if (list != null) {
			list.add(PRIDE, 1);
			ThaumcraftApi.registerObjectTag(new ItemStack(Items.golden_leggings, 1, OreDictionary.WILDCARD_VALUE), list);
		}

		list = getAspectList(new ItemStack(Items.golden_boots));
		if (list != null) {
			list.add(PRIDE, 1);
			ThaumcraftApi.registerObjectTag(new ItemStack(Items.golden_boots, 1, OreDictionary.WILDCARD_VALUE), list);
		}

		list = getAspectList(new ItemStack(Items.golden_sword));
		if (list != null) {
			list.add(PRIDE, 2);
			ThaumcraftApi.registerObjectTag(new ItemStack(Items.golden_sword, 1, OreDictionary.WILDCARD_VALUE), list);
		}

		// list = getAspectList(new ItemStack(Item.bed));
		// list.add(SLOTH, 4);
		list = (new AspectList()).add(Aspect.CRAFT, 3).add(Aspect.CLOTH, 6).add(SLOTH, 4);
		ThaumcraftApi.registerObjectTag(new ItemStack(Items.bed, 1, OreDictionary.WILDCARD_VALUE), list);

		// list = getAspectList(new ItemStack(Item.eyeOfEnder)).copy();
		list = (new AspectList()).add(Aspect.SENSES, 4).add(Aspect.ELDRITCH, 4).add(Aspect.MAGIC, 3);
		list.add(ENVY, 4);
		ThaumcraftApi.registerObjectTag(new ItemStack(Items.ender_eye, 1, 0), list);
		// ThaumcraftApi.registerComplexObjectTag(Item.eyeOfEnder.itemID, 0,
		// (new AspectList()).add(Aspect.SENSES, 4).add(ENVY, 86));

		list = getAspectList(new ItemStack(Items.cake));
		list.add(GLUTTONY, 7);
		ThaumcraftApi.registerObjectTag(new ItemStack(Items.cake, 1, 0), list);

		list = getAspectList(new ItemStack(Items.cookie));
		list.add(GLUTTONY, 1);
		ThaumcraftApi.registerObjectTag(new ItemStack(Items.cookie, 1, OreDictionary.WILDCARD_VALUE), list);

		list = getAspectList(new ItemStack(Items.fire_charge));
		list.add(WRATH, 1);
		ThaumcraftApi.registerObjectTag(new ItemStack(Items.fire_charge, 1, 0), list);

		// list = getAspectList(new ItemStack(Item.comparator)).copy();
		// list.add(ENVY, 2);
		list = (new AspectList()).add(Aspect.MECHANISM, 2).add(Aspect.ORDER, 2).add(ENVY, 2);
		ThaumcraftApi.registerObjectTag(new ItemStack(Items.comparator, 1, OreDictionary.WILDCARD_VALUE), list);
		// ThaumcraftApi.registerComplexObjectTag(Block.redstoneComparatorIdle.blockID,
		// -1, (new AspectList()).merge(Aspect.MECHANISM, 2).merge(Aspect.ORDER,
		// 2).merge(ENVY, 2));
		// ThaumcraftApi.registerComplexObjectTag(Block.redstoneComparatorActive.blockID,
		// -1, (new AspectList()).merge(Aspect.MECHANISM, 2).merge(Aspect.ORDER,
		// 2).merge(ENVY, 2));

		list = (new AspectList()).add(Aspect.FLESH, 6).add(Aspect.LIFE, 6).add(Aspect.ENERGY, 6).add(Aspect.BEAST, 4).add(GLUTTONY, 6);
		ThaumcraftApi.registerObjectTag("itemBacon", list);

		if (!Config.noLust) {
			list = getAspectList(new ItemStack(Items.saddle));
			list.add(LUST, 2);
			ThaumcraftApi.registerObjectTag(new ItemStack(Items.saddle, 1, OreDictionary.WILDCARD_VALUE), list);

			// list = getAspectList(new ItemStack(Item.leash));
			list = (new AspectList()).add(Aspect.BEAST, 2).add(Aspect.CLOTH, 2).add(Aspect.SLIME, 1);
			list.add(LUST, 2);
			ThaumcraftApi.registerObjectTag(new ItemStack(Items.lead, 1, OreDictionary.WILDCARD_VALUE), list);
		}

		Iterator mobCount = ThaumcraftApi.scanEntities.iterator();

		while (mobCount.hasNext()) {
			ThaumcraftApi.EntityTags mobTag = (ThaumcraftApi.EntityTags) mobCount.next();
			if (mobTag.entityName.equals("BrainyZombie")) {
				mobTag.aspects.add(WRATH, 3);
			} else if (mobTag.entityName.equals("GiantBrainyZombie")) {
				mobTag.aspects.add(WRATH, 4);
			} else if (mobTag.entityName.equals("Firebat")) {
				mobTag.aspects.add(WRATH, 1).add(NETHER, 2);
			} else if (mobTag.entityName.equals("Skeleton") && mobTag.nbts.length > 0) {
				mobTag.aspects.add(NETHER, 3);
			} else if (mobTag.entityName.equals("Creeper")) {
				if (mobTag.nbts.length > 0)
					mobTag.aspects.add(WRATH, 4);
				else
					mobTag.aspects.add(WRATH, 2);
			} else if (mobTag.entityName.equals("WitherBoss")) {
				mobTag.aspects.add(NETHER, 7).add(WRATH, 7);
			} else if (mobTag.entityName.equals("Ozelot")) {
				mobTag.aspects.add(SLOTH, 3);
			} else if (mobTag.entityName.equals("Enderman")) {
				mobTag.aspects.add(PRIDE, 4).add(ENVY, 4);
			} else if (mobTag.entityName.equals("Ghast")) {
				mobTag.aspects.add(NETHER, 3).add(WRATH, 3);
			} else if (mobTag.entityName.equals("PigZombie")) {
				mobTag.aspects.add(WRATH, 6);
			} else if (mobTag.entityName.equals("EnderDragon")) {
				mobTag.aspects.add(PRIDE, 10);
			} else if (!Config.noLust && mobTag.entityName.equals("Taintacle")) {
				mobTag.aspects.add(LUST, 3);
			} else if (mobTag.entityName.equals("Pig")) {
				mobTag.aspects.add(GLUTTONY, 3);
			} else if (mobTag.entityName.equals("LavaSlime")) {
				mobTag.aspects.add(NETHER, 1);
			} else if (!Config.noLust && mobTag.entityName.equals("TwilightForest.Forest Bunny")) {
				mobTag.aspects.add(LUST, 5);
			} else if (mobTag.entityName.equals("TwilightForest.Mini Ghast")) {
				mobTag.aspects.add(NETHER, 1).add(WRATH, 1);
			} else if (mobTag.entityName.equals("TwilightForest.Tower Boss")) {
				mobTag.aspects.add(NETHER, 10).add(WRATH, 10);
			} else if (mobTag.entityName.equals("TwilightForest.Tower Ghast")) {
				mobTag.aspects.add(NETHER, 3).add(WRATH, 3);
			} else if (mobTag.entityName.equals("TwilightForest.Twilight Lich")) {
				mobTag.aspects.add(PRIDE, 10);
			} else if (!Config.noLust && mobTag.entityName.equals("TwilightForest.Pinch Beetle")) {
				mobTag.aspects.add(LUST, 3);
			} else if (mobTag.entityName.equals("TwilightForest.King Spider")) {
				mobTag.aspects.add(PRIDE, 4);
			} else if (mobTag.entityName.equals("witchery.demon")) {
				mobTag.aspects.add(NETHER, 8);
			}
		}

		ThaumcraftApi.registerObjectTag(new ItemStack(ForbiddenItems.deadlyShards, 1, 0), (new AspectList()).add(NETHER, 1).add(WRATH, 2).add(Aspect.CRYSTAL, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ForbiddenItems.deadlyShards, 1, 1), (new AspectList()).add(NETHER, 1).add(ENVY, 2).add(Aspect.CRYSTAL, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ForbiddenItems.deadlyShards, 1, 2), (new AspectList()).add(Aspect.TAINT, 3).add(Aspect.CRYSTAL, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ForbiddenItems.deadlyShards, 1, 3), (new AspectList()).add(NETHER, 1).add(PRIDE, 2).add(Aspect.CRYSTAL, 1));
		if (!Config.noLust)
			ThaumcraftApi.registerObjectTag(new ItemStack(ForbiddenItems.deadlyShards, 1, 4), (new AspectList()).add(NETHER, 1).add(LUST, 2).add(Aspect.CRYSTAL, 1));
		else
			ThaumcraftApi.registerObjectTag(new ItemStack(ForbiddenItems.deadlyShards, 1, 4), (new AspectList()).add(Aspect.VOID, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ForbiddenItems.gluttonyShard, 1, OreDictionary.WILDCARD_VALUE), (new AspectList()).add(NETHER, 1).add(GLUTTONY, 2).add(Aspect.CRYSTAL, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ForbiddenItems.deadlyShards, 1, 5), (new AspectList()).add(NETHER, 1).add(SLOTH, 2).add(Aspect.CRYSTAL, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ForbiddenItems.deadlyShards, 1, 6), (new AspectList()).add(NETHER, 1).add(Aspect.GREED, 2).add(Aspect.CRYSTAL, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ForbiddenItems.resource, 1, 0), (new AspectList()).add(Aspect.CRYSTAL, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ForbiddenItems.resource, 1, 1), (new AspectList()).add(Aspect.DARKNESS, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ForbiddenBlocks.blackFlower, 1, OreDictionary.WILDCARD_VALUE), (new AspectList()).add(Aspect.PLANT, 1).add(Aspect.LIFE, 1).add(Aspect.SENSES, 1).add(Aspect.DARKNESS, 1));
		if (!Config.noLust)
			ThaumcraftApi.registerObjectTag(new ItemStack(ForbiddenItems.ridingCrop, 1, OreDictionary.WILDCARD_VALUE), (new AspectList()).add(Aspect.TREE, 2).add(Aspect.CLOTH, 2).add(Aspect.BEAST, 1).add(LUST, 1).add(Aspect.WEAPON, 1));
	}
}