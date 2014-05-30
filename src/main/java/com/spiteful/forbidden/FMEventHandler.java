package com.spiteful.forbidden;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Random;

import org.apache.logging.log4j.Level;

import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.BreakSpeed;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumcraft.common.lib.Utils;

import com.google.common.collect.Multimap;
import com.spiteful.forbidden.blocks.BlockBlackFlower;
import com.spiteful.forbidden.blocks.ForbiddenBlocks;
import com.spiteful.forbidden.enchantments.DarkEnchantments;
import com.spiteful.forbidden.items.ForbiddenItems;
import com.spiteful.forbidden.items.ItemTaintPickaxe;

import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class FMEventHandler
{
	Random randy = new Random();
	final Aspect[] primals = {Aspect.ENTROPY, Aspect.ORDER, Aspect.FIRE, Aspect.WATER, Aspect.EARTH, Aspect.AIR};
	
	@SubscribeEvent
	public void onPlayerBreaking(BreakSpeed event) {
		if(event.entityPlayer.getCurrentEquippedItem() != null) {
			ItemStack stack = event.entityPlayer.getCurrentEquippedItem();
			if(stack.getItem() instanceof ItemTaintPickaxe) {
				World world = event.entityPlayer.worldObj;
				int x = event.x;
				int y = event.y;
				int z = event.z;
				float hardness = world.getBlock(x, y, z).getBlockHardness(world, x, y, z);
				if(hardness == 0.0F)
					event.newSpeed = 0.0F;
				else if(hardness < 5.0F)
					event.newSpeed = 0.1F;
				else if(hardness < 20.0F)
					event.newSpeed = hardness / 2.0F;
				else event.newSpeed = 5.0F + hardness;
			}
		}
	}

	@SubscribeEvent
	public void onLivingDrops(LivingDropsEvent event)
	{
		if(Config.silverfishEmeralds && event.entityLiving instanceof EntitySilverfish){
			
			if(event.entityLiving.worldObj.getBiomeGenForCoords((int)event.entityLiving.posX, (int)event.entityLiving.posZ).biomeID ==
				BiomeGenBase.extremeHills.biomeID){
					if(event.recentlyHit && event.source.getEntity() != null
						&& event.source.getEntity() instanceof EntityPlayer	&& randy.nextInt(30) <= (2 + event.lootingLevel * 2))
						addDrop(event, new ItemStack(ForbiddenItems.resource, 1, 0));
			}
			else if(event.recentlyHit && event.source.getEntity() != null && event.source.getEntity() instanceof EntityPlayer
				&& !(event.source.getEntity() instanceof FakePlayer) && randy.nextInt(70) <= (1 + event.lootingLevel * 3)){
				
				addDrop(event, new ItemStack(ForbiddenItems.resource, 1, 0));
			}
				
		}
		
		if(Config.greedyEnch && (event.entityLiving instanceof EntityVillager || event.entityLiving instanceof IMob) &&
			event.recentlyHit && event.source.getEntity() != null && event.source.getEntity() instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer)event.source.getEntity();
			ItemStack equip = player.getCurrentEquippedItem();
			if(equip != null && EnchantmentHelper.getEnchantmentLevel(DarkEnchantments.greedy.effectId, equip) > 0
				&& event.lootingLevel <= 0)
			{
				if(event.entityLiving instanceof EntityVillager)
					addDrop(event, new ItemStack(Items.emerald, 1));
				else if(randy.nextInt(35) < 3)
					addDrop(event, new ItemStack(ForbiddenItems.resource, 1, 0));
			}
		}
		
		if (event.entityLiving.getClass() == EntitySkeleton.class && event.recentlyHit && event.source.getEntity() != null
				&& event.source.getEntity() instanceof EntityPlayer)
		{
			ItemStack weap = ((EntityPlayer)event.source.getEntity()).getCurrentEquippedItem();

			if (weap != null && weap.getItem() == ForbiddenItems.skullAxe && randy.nextInt(26) <= (3 + EnchantmentHelper.getEnchantmentLevel(Enchantment.looting.effectId, weap)))
			{
				addDrop(event, new ItemStack(Items.skull, 1, ((EntitySkeleton)event.entityLiving).getSkeletonType()));
			}
		}

		if (event.entityLiving.getClass() == EntityZombie.class && event.recentlyHit && event.source.getEntity() != null
				&& event.source.getEntity() instanceof EntityPlayer)
		{
			ItemStack weap = ((EntityPlayer)event.source.getEntity()).getCurrentEquippedItem();

			if (weap != null && weap.getItem() == ForbiddenItems.skullAxe && randy.nextInt(26) <= (2 + 2 * EnchantmentHelper.getEnchantmentLevel(Enchantment.looting.effectId, weap)))
			{
				addDrop(event, new ItemStack(Items.skull, 1, 2));
			}
		}

		if (event.entityLiving.getClass() == EntityCreeper.class && event.recentlyHit && event.source.getEntity() != null
				&& event.source.getEntity() instanceof EntityPlayer)
		{
			ItemStack weap = ((EntityPlayer)event.source.getEntity()).getCurrentEquippedItem();

			if (weap != null && weap.getItem() == ForbiddenItems.skullAxe && randy.nextInt(26) <= (2 + 2 * EnchantmentHelper.getEnchantmentLevel(Enchantment.looting.effectId, weap)))
			{
				addDrop(event, new ItemStack(Items.skull, 1, 4));
			}
		}

		if (event.entityLiving instanceof EntityPlayer && event.recentlyHit && event.source.getEntity() != null
				&& event.source.getEntity() instanceof EntityPlayer)
		{
			ItemStack weap = ((EntityPlayer)event.source.getEntity()).getCurrentEquippedItem();

			if (weap != null && weap.getItem() == ForbiddenItems.skullAxe && randy.nextInt(11) <= (1 + EnchantmentHelper.getEnchantmentLevel(Enchantment.looting.effectId, weap)))
			{
				ItemStack head = new ItemStack(Items.skull, 1, 3);
				NBTTagCompound nametag = new NBTTagCompound();
				nametag.setString("SkullOwner", ((EntityPlayer)event.entityLiving).getDisplayName());
				head.setTagInfo("SkullOwner", nametag);
				addDrop(event, head);
			}
		}

		if (event.entityLiving.worldObj.provider.dimensionId != -1)
			return;

		if (!event.recentlyHit || event.source.getEntity() == null || !(event.source.getEntity() instanceof EntityPlayer)
				|| event.source.getEntity() instanceof FakePlayer)
		{
			if (randy.nextInt(30) < 4)
			{
				addDrop(event, new ItemStack(ForbiddenItems.deadlyShards, 1 + randy.nextInt(3), 5));
			}
		}

		if (event.recentlyHit && event.source.getEntity() != null && event.source.getEntity() instanceof EntityPlayer
			&& !(event.source.getEntity() instanceof FakePlayer))
		{
			if (event.entityLiving instanceof IMob)
			{
				int wrath = 2;
				ItemStack heldItem = ((EntityPlayer)event.source.getEntity()).getCurrentEquippedItem();

				if (heldItem != null)
				{
					if (heldItem.getItem() instanceof ItemTool)
					{
						Multimap map = heldItem.getItem().getAttributeModifiers(heldItem);
						Collection collect = map.get(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName());
						if(collect instanceof AttributeModifier) {
							wrath += (int)((AttributeModifier) collect).getAmount();
						}						
					}
					else if (heldItem.getItem() instanceof ItemSword)
					{
						wrath += (int)(((ItemSword)heldItem.getItem()).func_150931_i() + 4.0F);
					}

					wrath += EnchantmentHelper.getEnchantmentLevel(Enchantment.sharpness.effectId, heldItem);
					wrath += EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, heldItem);

					if (!event.entityLiving.isImmuneToFire())
					{
						wrath += EnchantmentHelper.getEnchantmentLevel(Enchantment.fireAspect.effectId, heldItem);
						wrath += EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, heldItem);
					}

					if (event.entityLiving.isEntityUndead())
					{
						wrath += EnchantmentHelper.getEnchantmentLevel(Enchantment.smite.effectId, heldItem);
					}

					if (event.entityLiving.getCreatureAttribute() == EnumCreatureAttribute.ARTHROPOD)
					{
						wrath += EnchantmentHelper.getEnchantmentLevel(Enchantment.baneOfArthropods.effectId, heldItem);
					}
				}

				if (randy.nextInt(61) <= wrath)
				{
					addDrop(event, new ItemStack(ForbiddenItems.deadlyShards, 1, 0));
				}
				
				if(event.entityLiving instanceof IBossDisplayData){
					addDrop(event, new ItemStack(ForbiddenItems.deadlyShards, 2 + randy.nextInt(1 + event.lootingLevel), 3));
				}
			}

			if (event.entityLiving instanceof EntityPigZombie && !(event.source.getEntity() instanceof FakePlayer))
			{
				if (event.entityLiving.getHeldItem() != null && event.entityLiving.getHeldItem().getItem() == ForbiddenItems.deadlyShards
					&& event.entityLiving.getHeldItem().getItemDamage() == 1)
				{
					addDrop(event, new ItemStack(ForbiddenItems.deadlyShards, 1, 1));
				}
			}
		}
	}

	public void addDrop(LivingDropsEvent event, ItemStack drop)
	{
		EntityItem entityitem = new EntityItem(event.entityLiving.worldObj, event.entityLiving.posX, event.entityLiving.posY, event.entityLiving.posZ, drop);
		entityitem.delayBeforeCanPickup = 10;
		event.drops.add(entityitem);
	}
	
	@SubscribeEvent
	public void onDeath(LivingDeathEvent event){
		if(event.source.getEntity() != null && event.source.getEntity() instanceof EntityPlayer
			&& ((EntityPlayer)event.source.getEntity()).getCurrentEquippedItem() != null)
		{
			ItemStack equip = ((EntityPlayer)event.source.getEntity()).getCurrentEquippedItem();
			
			if(equip.getItem() == ForbiddenItems.fork){
				String name = null;
				try {
					name = EntityList.getEntityString(event.entityLiving);
				} catch(Exception e){
					try {
						name = event.entityLiving.getCommandSenderName();
					}
					catch(Exception ee){
						return;
					}
				}
				finally {
					if(name == null)
						return;
				}
				
				if(Config.spawnerMobs.containsKey(name) ||(Config.wrathCrazy && !(event.entityLiving instanceof IBossDisplayData)))
					imprintCrystal((EntityPlayer)(event.source.getEntity()), name);
			}
			
			if(EnchantmentHelper.getEnchantmentLevel(DarkEnchantments.educational.effectId, equip) > 0 && event.entityLiving instanceof EntityLiving
				&& EnchantmentHelper.getEnchantmentLevel(Enchantment.looting.effectId, equip) == 0)
			{
				try {
					int learning = 3 * ExperienceFixer.getXP(((EntityLiving)event.entityLiving)) * EnchantmentHelper.getEnchantmentLevel(DarkEnchantments.educational.effectId, equip);
					while(learning > 0)
					{
						int xp = EntityXPOrb.getXPSplit(learning);
						learning -= xp;
						event.entityLiving.worldObj.spawnEntityInWorld(new EntityXPOrb(event.entityLiving.worldObj, event.entityLiving.posX, event.entityLiving.posY, event.entityLiving.posZ, xp));
					}
				} catch (Exception e) {
					LogHandler.log(Level.ERROR, "Failed to educate!");
				}
			}
		}
	}
	
	public void imprintCrystal(EntityPlayer player, String mob){		
		for(int x = 0; x < player.inventory.getSizeInventory(); ++x) {
			ItemStack item = player.inventory.getStackInSlot(x);
			if(item != null && item.getItem() == ForbiddenItems.mobCrystal
				&& (!item.hasTagCompound() || !item.getTagCompound().hasKey("mob"))){
				if(!item.hasTagCompound())
					item.setTagCompound(new NBTTagCompound());
				item.stackTagCompound.setString("mob", mob);
				return;
			}
		}
	}
	
	@SubscribeEvent
	public void onHarvest(HarvestDropsEvent event)
	{
		EntityPlayer player = event.harvester;
		if(player != null && player.inventory.getCurrentItem() != null)
		{
			ItemStack equip = player.inventory.getCurrentItem();
			if(equip.getItem() == ForbiddenItems.taintShovel
			&& event.block.getMaterial() == Config.taintMaterial && !(event.block == Block.getBlockFromItem(Config.thaumcraftTaintBlockID.getItem()) && event.blockMetadata == 2))
			{
				int fortune = EnchantmentHelper.getFortuneModifier(player);
				int chance = 1 + (2 * fortune);
				if(event.isSilkTouching)
					chance += 6;

				if(randy.nextInt(51) <= chance) {
					event.drops.add(new ItemStack(ForbiddenItems.deadlyShards, 1, 2));
				}
			}
			if(EnchantmentHelper.getEnchantmentLevel(DarkEnchantments.cluster.effectId, equip) > 0 && EnchantmentHelper.getFortuneModifier(player) == 0){
				if(event.drops != null && event.drops.size() > 0) {
					int cluster = EnchantmentHelper.getEnchantmentLevel(DarkEnchantments.cluster.effectId, equip);
					float chance = 0.2F + (float)cluster * 0.075F;

					for(int a = 0; a < event.drops.size(); ++a)
					{
						ItemStack is = (ItemStack)event.drops.get(a);
						ItemStack smr = Utils.findSpecialMiningResult(is, chance, event.world.rand);
						if(!is.isItemEqual(smr))
						{
							event.drops.set(a, smr);
							if(!event.world.isRemote)
							{
								event.world.playSoundEffect((double)((float)event.x + 0.5F), (double)((float)event.y + 0.5F), (double)((float)event.z + 0.5F), "random.orb", 0.2F, 0.7F + event.world.rand.nextFloat() * 0.2F);
							}
						}
					}
				}
			}
			if(EnchantmentHelper.getEnchantmentLevel(DarkEnchantments.consuming.effectId, equip) > 0)
			{
				for(int x = 0;x < event.drops.size();x++)
				{
					ItemStack drop = (ItemStack)event.drops.get(x);
					if(drop != null && (drop.getItem() == Item.getItemFromBlock(Blocks.cobblestone) || drop.getItem() ==Item.getItemFromBlock(Blocks.dirt) || drop.getItem() == Item.getItemFromBlock(Blocks.netherrack)
						|| drop.getItem() == Item.getItemFromBlock(Blocks.gravel)))
						event.drops.remove(x);
				}
			}
			if(EnchantmentHelper.getEnchantmentLevel(DarkEnchantments.corrupting.effectId, equip) > 0 && event.block == Block.getBlockFromItem(Config.thaumcraftOreID.getItem()) &&  randy.nextInt(3) == 1)
			{
				ArrayList<ItemStack> moarDrops = new ArrayList<ItemStack>();
				ArrayList<ItemStack> deadDrops = new ArrayList<ItemStack>();
				Iterator current = event.drops.iterator();
				while(current.hasNext())
				{
					ItemStack curDrop = (ItemStack)current.next();
					if(curDrop != null && curDrop.getItem() == Config.thaumcraftShardID.getItem())
					{
						int x = curDrop.stackSize;
						for(int i = 0;i < x;i++)
						{
							moarDrops.add(getSinShard());
						}
						deadDrops.add(curDrop);
					}
				}
				current = deadDrops.iterator();
				while(current.hasNext())
				{
					event.drops.remove((ItemStack)(current.next()));
				}
				current = moarDrops.iterator();
				while(current.hasNext())
				{
					event.drops.add((ItemStack)(current.next()));
				}
			}
		}
	}
	
	public ItemStack getSinShard()
	{
		int x = randy.nextInt(7);
		switch(x)
		{
			case 0:
			case 1:
			case 3:
			case 5:
			case 6: return new ItemStack(ForbiddenItems.deadlyShards, 1, x);
			case 2: return new ItemStack(ForbiddenItems.gluttonyShard, 1, 0);
			case 4:	if(Config.noLust)
						return getSinShard();
					else
						return new ItemStack(ForbiddenItems.deadlyShards, 1, 4);
		}
		return null;
	}
	
	@SubscribeEvent
	public void onBonemeal(BonemealEvent event){
		if(event.block == ForbiddenBlocks.blackFlower){
			((BlockBlackFlower)ForbiddenBlocks.blackFlower).spreadFlowers(event.world, event.x, event.y, event.z, randy);
			event.setResult(Result.ALLOW);
		}
	}
	
	@SubscribeEvent
	public void onSpawn(LivingSpawnEvent event){
		if(event.entityLiving.worldObj.provider.dimensionId == -1 && event.entityLiving instanceof EntityPigZombie
			&& randy.nextInt(175) == 1)
			event.entityLiving.setCurrentItemOrArmor(0, new ItemStack(ForbiddenItems.deadlyShards, 1, 1));
	}
	
	@SubscribeEvent
	public void onGetHurt(LivingHurtEvent event){
		if(event.entityLiving instanceof EntityPlayer && ((EntityPlayer)event.entityLiving).getCurrentEquippedItem() != null
			&& ((EntityPlayer)event.entityLiving).getCurrentEquippedItem().getItem() instanceof ItemWandCasting){
			
			ItemStack wand = ((EntityPlayer)event.entityLiving).getCurrentEquippedItem();
			if(event.source.getDamageType().equals("taint") && ((ItemWandCasting)wand.getItem()).getRod(wand).getTag().equals("tainted")){
				
				for(int x = 0;x < 3;x++){
					((ItemWandCasting)wand.getItem()).addVis(wand, primals[randy.nextInt(6)], 1, true);
				}
				
			}
			
			if((event.source.getDamageType().equals("wither") || event.source.isFireDamage())
				&& ((ItemWandCasting)wand.getItem()).getRod(wand).getTag().equals("infernal")){
				
				event.setCanceled(true);
			}
		}
		
		if(event.source.getEntity() != null && event.source.getEntity() instanceof EntityPlayer)
		{
			ItemStack equip = ((EntityPlayer)event.source.getEntity()).getCurrentEquippedItem();
			if(equip != null && equip.getItem() instanceof ItemWandCasting)
			{
				if(((ItemWandCasting)equip.getItem()).getCap(equip).getTag().equals("alchemical")
					&& ((ItemWandCasting)equip.getItem()).getRod(equip).getTag().startsWith("blood"))
				{
					event.entityLiving.addPotionEffect(new PotionEffect(Potion.weakness.id, 60, 2));
				}
			}
		}
	}
	
	@SubscribeEvent
	public void onTooltip(ItemTooltipEvent event)
	{
		if(event.itemStack.getItem() instanceof ItemWandCasting){
			if (((ItemWandCasting)event.itemStack.getItem()).getRod(event.itemStack).getTag().startsWith("blood") && !event.itemStack.stackTagCompound.getString("ownerName").equals(""))
			{
				event.toolTip.add("Current owner: " + event.itemStack.stackTagCompound.getString("ownerName"));
			}
			else if(((ItemWandCasting)event.itemStack.getItem()).getRod(event.itemStack).getTag().startsWith("neutronium"))
			{
				event.toolTip.add("Creative Only");
			}
		}
	}
	
}