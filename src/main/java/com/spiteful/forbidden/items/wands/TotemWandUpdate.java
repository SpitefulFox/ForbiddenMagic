package com.spiteful.forbidden.items.wands;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.wands.IWandRodOnUpdate;
import thaumcraft.common.items.wands.ItemWandCasting;

import com.spiteful.forbidden.Compat;

public class TotemWandUpdate implements IWandRodOnUpdate {

	Aspect primals[] = Aspect.getPrimalAspects().toArray(new Aspect[0]);
	
	public void onUpdate(ItemStack itemstack, EntityPlayer player)
	{
		if(Compat.totes && Compat.fennCrystal != null && player.ticksExisted % 30 == 0)
		{
			try
			{
				ArrayList unfilled = new ArrayList();
				for(int x = 0;x < primals.length;x++)
				{
					if(((ItemWandCasting)itemstack.getItem()).getVis(itemstack, primals[x]) < ((ItemWandCasting)itemstack.getItem()).getMaxVis(itemstack))
					{
						unfilled.add(primals[x]);
					}
				}
				if(unfilled.size() > 0 && siphonPlant(player))
						((ItemWandCasting)itemstack.getItem()).addVis(itemstack, (Aspect)(unfilled.get(player.worldObj.rand.nextInt(unfilled.size()))), 1, true);
			
			}
			catch(Exception e)
			{
				return;
			}
		}
	
	}
	
	public boolean siphonPlant(EntityPlayer player)
	{
		for(int x = 0; x < player.inventory.getSizeInventory(); ++x)
		{
			ItemStack item = player.inventory.getStackInSlot(x);
			if(item != null && item.getItem() == Compat.fennCrystal)
			{
				if(item.getItemDamage() <= 995)
				{
					item.setItemDamage(item.getItemDamage() + 5);
					return true;
				}
			}
		}
		return false;
	}
}
