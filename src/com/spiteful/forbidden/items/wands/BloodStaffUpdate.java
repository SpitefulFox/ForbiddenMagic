package com.spiteful.forbidden.items.wands;

import com.spiteful.forbidden.Config;
import com.spiteful.forbidden.Compat;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.wands.IWandRodOnUpdate;
import thaumcraft.common.items.wands.ItemWandCasting;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
//import WayofTime.alchemicalWizardry.common.EnergyItems;

public class BloodStaffUpdate implements IWandRodOnUpdate {

	Aspect primals[] = Aspect.getPrimalAspects().toArray(new Aspect[0]);
	Method checkOwner;
	Method syphon;

	public BloodStaffUpdate()
	{
		try
		{
			Class energyItems;
			energyItems = Class.forName("WayofTime.alchemicalWizardry.common.items.EnergyItems");

			checkOwner = energyItems.getDeclaredMethod("checkAndSetItemOwner", new Class[] {ItemStack.class, EntityPlayer.class});
			syphon = energyItems.getDeclaredMethod("syphonWhileInContainer", new Class[] {ItemStack.class, Integer.TYPE});
		}
		catch(Exception e){
		e.printStackTrace(); }
	}
	
	public void onUpdate(ItemStack itemstack, EntityPlayer player)
	{
		if(Compat.bm && player.ticksExisted % 25 == 0)
		{
			try
			{
				if(!checkHotbar(itemstack, player))
					return;
				
				checkOwner.invoke(null, itemstack, player);
				
				int cost;
				if(((ItemWandCasting)itemstack.getItem()).getCap(itemstack).getTag().equals("alchemical"))
					cost = 900;
				else
					cost = 1000;
				
				for(int x = 0;x < primals.length;x++)
				{
					if(((ItemWandCasting)itemstack.getItem()).getVis(itemstack, primals[x]) < ((ItemWandCasting)itemstack.getItem()).getMaxVis(itemstack))
					{
						if(player.capabilities.isCreativeMode)
							((ItemWandCasting)itemstack.getItem()).addVis(itemstack, primals[x], 1, true);
						else if(((Boolean)(syphon.invoke(null, itemstack, cost))).booleanValue())
							((ItemWandCasting)itemstack.getItem()).addVis(itemstack, primals[x], 1, true);
						else if(syphonHealth(player))
						{
							((ItemWandCasting)itemstack.getItem()).addVis(itemstack, primals[x], 1, true);
							return;
						}
						else
							return;
					}
				}
			
			}
			catch(Exception e)
			{
				return;
			}
		}
		
	}
	
	public boolean syphonHealth(EntityPlayer player)
	{
		if(player.getHealth() > 0)
		{
			player.setHealth(player.getHealth() - 3);
			return true;
		}
		else
			return false;
	}
	
	private boolean checkHotbar(ItemStack stack, EntityPlayer player)
	{
		for(int x = 0; x < 9; ++x)
		{
			ItemStack item = player.inventory.getStackInSlot(x);
			if(item == stack)
				return true;
		}
		return false;
	}
}
